package com.epam.university.java.project.core.cdi.bean;

import java.util.Collection;

/**
 * Meta information about beans in context environment.
 */
public interface BeansDefinition {
    /**
     * Get bean definitions.
     * @return collection of bean definitions
     */
    Collection<BeanDefinition> getDefinitions();
}
