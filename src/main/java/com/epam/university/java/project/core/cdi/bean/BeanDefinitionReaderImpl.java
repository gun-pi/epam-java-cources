package com.epam.university.java.project.core.cdi.bean;

import com.epam.university.java.project.core.cdi.io.Resource;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import java.util.Collection;

public class BeanDefinitionReaderImpl implements BeanDefinitionReader {
    private final BeanDefinitionRegistry beanDefinitionRegistry;

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
            final JAXBContext jaxbContext = JAXBContext.newInstance(
                    BeansDefinition.class,
                    BeanDefinitionImpl.class,
                    BeanPropertyDefinitionImpl.class
            );
            final Unmarshaller unmarshaller = jaxbContext.createUnmarshaller();
            final BeansDefinition beansDefinitionFromJaxb = (BeansDefinition) unmarshaller
                    .unmarshal(resource.getFile());
            for (BeanDefinition beanDefinition : beansDefinitionFromJaxb.getDefinitions()) {
                beanDefinitionRegistry.addBeanDefinition(beanDefinition);
            }
            return beansDefinitionFromJaxb.getDefinitions().size();
        } catch (JAXBException e) {
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
        int beansLoaded = 0;
        for (Resource resource : resources) {
            beansLoaded += loadBeanDefinitions(resource);
        }
        return beansLoaded;
    }
}
