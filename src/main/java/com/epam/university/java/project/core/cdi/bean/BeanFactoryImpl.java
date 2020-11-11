package com.epam.university.java.project.core.cdi.bean;

import com.epam.university.java.project.core.cdi.structure.ListDefinition;
import com.epam.university.java.project.core.cdi.structure.ListDefinitionImpl;
import com.epam.university.java.project.core.cdi.structure.MapDefinition;
import com.epam.university.java.project.core.cdi.structure.MapDefinitionImpl;
import com.epam.university.java.project.core.cdi.structure.StructureDefinition;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class BeanFactoryImpl implements BeanFactory {
    private BeanDefinitionRegistry beanDefinitionRegistry;
    private Map<BeanDefinition, Object> singletons = new HashMap<>();

    public BeanFactoryImpl(BeanDefinitionRegistry beanDefinitionRegistry) {
        this.beanDefinitionRegistry = beanDefinitionRegistry;
    }

    /**
     * Get bean instances by class.
     *
     * @param beanClass bean class to get
     * @return bean instance
     */
    @Override
    public <T> T getBean(Class<T> beanClass) {
        return (T) getBean(beanClass.getName());
    }

    /**
     * Get bean instance by  definition name.
     *
     * @param beanName bean definition name
     * @return bean instance
     */
    @Override
    public Object getBean(String beanName) {
        return getBean(beanDefinitionRegistry.getBeanDefinition(beanName));
    }

    /**
     * Get bean instance by definition name and target class.
     *
     * @param beanName  bean definition name
     * @param beanClass target bean class
     * @return bean instance
     */
    @Override
    public <T> T getBean(String beanName, Class<T> beanClass) {
        return (T) getBean(beanName);
    }

    /**
     * Get bean instances by definition.
     *
     * @param definition meta information about bean in context environment
     * @param <T> bean type
     * @return bean instance
     */
    public <T> T getBean(BeanDefinition definition) {
        try {
            Class<T> beanClass = (Class<T>) Class.forName(definition.getClassName());
            String beanScope = definition.getScope() == null ? "" : definition.getScope();
            T instance;

            if (beanScope.equals("singleton") && singletons.containsKey(definition)) {
                instance = (T) singletons.get(definition);
                return instance;
            } else {
                instance = beanClass.getDeclaredConstructor().newInstance();
                if (beanScope.equals("singleton")) {
                    singletons.put(definition, instance);
                }
            }

            Collection<BeanPropertyDefinition> properties = definition.getProperties();
            if (properties == null) {
                return instance;
            } else {
                checkProperties(properties);
            }

            for (BeanPropertyDefinition property : properties) {
                Field beanField = beanClass.getDeclaredField(property.getName());
                beanField.setAccessible(true);

                String propertyValue = property.getValue();
                if (propertyValue != null) {
                    if (propertyValue.matches("\\d+")) {
                        beanField.set(instance, Integer.parseInt(property.getValue()));
                    } else {
                        beanField.set(instance, property.getValue());
                    }
                }

                String propertyRef = property.getRef();
                if (propertyRef != null) {
                    beanField.set(instance, getBean(propertyRef));
                }

                StructureDefinition propertyData = property.getData();
                if (propertyData != null) {
                    if (propertyData.getClass() == ListDefinitionImpl.class) {
                        List<String> items = new ArrayList<>();
                        for (ListDefinition.ListItemDefinition itemDefinition
                                : ((ListDefinition) propertyData).getItems()) {
                            if (itemDefinition.getValue() != null) {
                                items.add(itemDefinition.getValue());
                            }
                        }
                        beanField.set(instance, items);
                    }

                    if (propertyData.getClass() == MapDefinitionImpl.class) {
                        Map<String, Object> entries = new HashMap<>();
                        for (MapDefinition.MapEntryDefinition entryDefinition
                                : ((MapDefinition) propertyData).getValues()) {
                            if (entryDefinition.getValue() != null
                                    && entryDefinition.getRef() != null) {
                                throw new RuntimeException();
                            }

                            if (entryDefinition.getValue() != null) {
                                entries.put(entryDefinition.getKey(), entryDefinition.getValue());
                            }

                            if (entryDefinition.getRef() != null) {
                                entries.put(entryDefinition.getKey(),
                                        getBean(entryDefinition.getRef()));
                            }
                        }
                        beanField.set(instance, entries);
                    }
                }
            }

            return instance;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    private void checkProperties(Collection<BeanPropertyDefinition> properties) {
        for (BeanPropertyDefinition property : properties) {
            if (property.getValue() == null
                    && property.getRef() == null
                    && property.getData() == null) {
                throw new RuntimeException();
            }
        }
    }
}
