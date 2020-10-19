package com.epam.university.java.project.core.cdi.bean;

import com.epam.university.java.project.core.cdi.structure.ListDefinition;
import com.epam.university.java.project.core.cdi.structure.MapDefinition;

import java.lang.reflect.Field;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

public class BeanFactoryImpl implements BeanFactory {
    private final BeanDefinitionRegistry beanDefinitionRegistry;
    private final Map<BeanDefinition, Object> singletons = new HashMap<>();

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
    public <T> T getBean(final BeanDefinition definition) {
        for (BeanPropertyDefinition each : definition.getProperties()) {
            if (each.getValue() == null && each.getRef() == null
                    && each.getData() == null) {
                throw new RuntimeException();
            }
        }

        try {
            final T instance;
            final Class<T> beanClass = (Class<T>) Class.forName(definition.getClassName());
            if ("singleton".equals(definition.getScope())
                    && singletons.containsKey(definition)) {
                instance = (T) singletons.get(definition);

                return instance;
            } else {
                instance = beanClass.getDeclaredConstructor().newInstance();
                if ("singleton".equals(definition.getScope())) {
                    singletons.put(definition, instance);
                }
            }

            for (BeanPropertyDefinition property : definition.getProperties()) {
                final Field beanField = beanClass.getDeclaredField(property.getName());
                beanField.setAccessible(true);

                if (property.getValue() != null) {
                    try {
                        beanField.set(instance, Integer.parseInt(property.getValue()));
                    } catch (Exception e) {
                        beanField.set(instance, property.getValue());
                    }
                }

                if (property.getRef() != null) {
                    Object object = getBean(property.getRef());
                    beanField.set(instance, object);
                }

                if (property.getData() == null) {
                    continue;
                }

                if (property.getData() instanceof ListDefinition) {
                    ListDefinition listDefinition = (ListDefinition) property.getData();
                    Collection<String> items = listDefinition.getItems().stream()
                                                .map(ListDefinition.ListItemDefinition::getValue)
                                                .collect(Collectors.toList());
                    beanField.set(instance, items);
                }

                if (property.getData() instanceof MapDefinition) {
                    MapDefinition mapDefinition = (MapDefinition) property.getData();
                    Map<String, Object> entries = new HashMap<>();
                    for (MapDefinition.MapEntryDefinition entryDefinition
                            : mapDefinition.getValues()) {
                        if (entryDefinition.getValue() != null
                                && entryDefinition.getRef() != null) {
                            throw new RuntimeException();
                        }
                        if (entryDefinition.getValue() != null) {
                            entries.put(entryDefinition.getKey(), entryDefinition.getValue());
                        }
                        if (entryDefinition.getRef() != null) {
                            Object object = getBean(entryDefinition.getRef());
                            entries.put(entryDefinition.getKey(), object);
                        }
                    }
                    beanField.set(instance, entries);
                }
            }

            return instance;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
