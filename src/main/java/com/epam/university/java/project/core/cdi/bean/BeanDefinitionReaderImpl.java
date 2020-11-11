package com.epam.university.java.project.core.cdi.bean;

import com.epam.university.java.project.core.cdi.io.Resource;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.Unmarshaller;
import java.util.Collection;

public class BeanDefinitionReaderImpl implements BeanDefinitionReader {
    private BeanDefinitionRegistry beanDefinitionRegistry;

    public BeanDefinitionReaderImpl(BeanDefinitionRegistry beanDefinitionRegistry) {
        this.beanDefinitionRegistry = beanDefinitionRegistry;
    }

    /**
     * Load bean definitions from designated resource.
     *
     * @param resource resource instance
     * @return amount of loaded definitions
     */
    @Override
    public int loadBeanDefinitions(Resource resource) {
        try {
            JAXBContext jaxbContext = JAXBContext.newInstance(
                    BeansDefinitionImpl.class,
                    BeanDefinitionImpl.class,
                    BeanPropertyDefinitionImpl.class
            );

            Unmarshaller unmarshaller = jaxbContext.createUnmarshaller();
            BeansDefinition beansDefinition =
                    (BeansDefinition) unmarshaller.unmarshal(resource.getFile());
            Collection<BeanDefinition> beanDefinitions = beansDefinition.getBeanDefinitions();

            for (BeanDefinition beanDefinition : beanDefinitions) {
                beanDefinitionRegistry.addBeanDefinition(beanDefinition);
            }

            return beanDefinitions.size();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Load bean definitions from collection of resources.
     *
     * @param resources collection of resources
     * @return amount of loaded definitions
     */
    @Override
    public int loadBeanDefinitions(Collection<Resource> resources) {
        return 0;
    }
}
