package com.epam.university.java.core.task055;

import javax.xml.bind.annotation.XmlAnyElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.Collection;

@XmlRootElement(name = "dataset")
public class DatasetDefinitionImpl implements DatasetDefinition {
    @XmlAnyElement(lax = true)
    private Collection<HouseDefinition> houseDefinitions;

    @Override
    public Collection<HouseDefinition> getHouseDefinitions() {
        return houseDefinitions;
    }
}
