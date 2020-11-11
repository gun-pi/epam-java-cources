package com.epam.university.java.project.core.cdi.bean;

import java.util.HashMap;
import java.util.Map;

public class BeanDefinitionRegistryImpl implements BeanDefinitionRegistry {
    private Map<String, BeanDefinition> registry = new HashMap<>();

    /**
     * Add bean definition to registry.
     *
     * @param definition bean definition object
     */
    @Override
    public void addBeanDefinition(BeanDefinition definition) {
        try {
            registry.put(definition.getId(), definition);
            registry.put(definition.getClassName(), definition);

            Class<?>[] interfaces = Class.forName(definition.getClassName()).getInterfaces();

            for (Class<?> eachInterface : interfaces) {
                if (eachInterface != null) {
                    registry.put(eachInterface.getName(), definition);
                }
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Get bean definition by id from registry.
     *
     * @param beanId bean id
     * @return bean definition
     */
    @Override
    public BeanDefinition getBeanDefinition(String beanId) {
        return registry.get(beanId);
    }
}
