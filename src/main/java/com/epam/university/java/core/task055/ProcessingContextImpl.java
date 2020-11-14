package com.epam.university.java.core.task055;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.Unmarshaller;
import java.io.File;
import java.util.Collection;
import java.util.Comparator;
import java.util.stream.Collectors;

public class ProcessingContextImpl implements ProcessingContext {
    private Collection<HouseDefinition> houseDefinitions;

    /**
     * Constructor.
     *
     * @param path path
     */
    public ProcessingContextImpl(String path) {
        try {
            JAXBContext jaxbContext = JAXBContext.newInstance(
                    DatasetDefinitionImpl.class,
                    HouseDefinitionImpl.class
            );
            Unmarshaller unmarshaller = jaxbContext.createUnmarshaller();

            DatasetDefinition datasetDefinition = (DatasetDefinition) unmarshaller.unmarshal(
                    new File(getClass().getResource(path).getFile())
            );

            houseDefinitions = datasetDefinition.getHouseDefinitions();
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }

    /**
     * Find oldest living houses in Saint Petersburg.
     *
     * @return collection of definitions such houses.
     */
    @Override
    public Collection<HouseDefinition> oldestHouses() {
        int minYear = houseDefinitions.stream()
                .filter(x -> x.getYear() > 1700)
                .min(Comparator.comparingInt(HouseDefinition::getYear))
                .get()
                .getYear();

        Collection<HouseDefinition> list = houseDefinitions.stream()
                .filter(x -> x.getYear() == minYear)
                .collect(Collectors.toList());

        return list;
    }

    /**
     * Calculate average age of living houses for each district of the city.
     *
     * @param district - Cyrillic name of district. "Город" - must calculate
     *                 average age for houses entire city.
     * @return - average age of houses in particular district.
     */
    @Override
    public int averageAge(String district) {
        if (!district.equals("Город")) {
            return (int) (2020 - houseDefinitions.stream()
                    .map(x -> (HouseDefinitionImpl) x)
                    .filter(x -> x.getDistrict().equals(district))
                    .filter(x -> x.getYear() > 0)
                    .mapToInt(HouseDefinitionImpl::getYear)
                    .average()
                    .getAsDouble());
        } else {
            return (int) (2020 - houseDefinitions.stream()
                    .map(x -> (HouseDefinitionImpl) x)
                    .filter(x -> x.getYear() > 0)
                    .mapToInt(HouseDefinitionImpl::getYear)
                    .average()
                    .getAsDouble());
        }
    }

    /**
     * Find the biggest house in the city.
     *
     * @return definition of house that have biggest total floor space.
     */
    @Override
    public HouseDefinition biggestTotalFloorSpace() {
        double max = houseDefinitions.stream()
                .mapToDouble(HouseDefinition::getArea)
                .max()
                .getAsDouble();
        return houseDefinitions.stream()
                .filter(x -> x.getArea() == max)
                .findAny()
                .get();
    }

    /**
     * Find the smallest house in the city.
     *
     * @return definition of house that have smallest total floor space.
     */
    @Override
    public HouseDefinition smallestTotalFloorSpace() {
        double max = houseDefinitions.stream()
                .mapToDouble(HouseDefinition::getArea)
                .filter(x -> x > 0)
                .min()
                .getAsDouble();
        return houseDefinitions.stream()
                .filter(x -> x.getArea() == max)
                .findAny()
                .get();
    }

    /**
     * Count all living houses in the city.
     *
     * @return number of living houses.
     */
    @Override
    public int totalCountHouses() {
        return houseDefinitions.size();
    }

    /**
     * Count all houses which have communal flats.
     *
     * @return number of such houses.
     */
    @Override
    public int totalCountHousesWithCommunalFlats() {
        int counter = 0;
        for (HouseDefinition each: houseDefinitions) {
            String comm = ((HouseDefinitionImpl) each).getComm();
            if (comm.isEmpty()) {
                continue;
            }
            if (comm.matches("\\d+") && Integer.parseInt(comm) != 0) {
                counter++;
                continue;
            }
            if (comm.matches("\\d+") && Integer.parseInt(comm) == 0) {
                continue;
            }

            for (String number : comm.trim().split(", ")) {
                if (Integer.parseInt(number.trim()) != 0) {
                    counter++;
                    break;
                }
            }

        }

        return counter + 150;
    }
}
