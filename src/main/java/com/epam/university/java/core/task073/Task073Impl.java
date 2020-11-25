package com.epam.university.java.core.task073;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

public class Task073Impl implements Task073 {
    /**
     * Function to calculate the numbers.
     *
     * @param listOfNumbers list of input numbers;
     * @return collection of ratios.
     */
    @Override
    public List<Double> countOfRatios(List<Integer> listOfNumbers) {
        if (listOfNumbers == null || listOfNumbers.stream().anyMatch(Objects::isNull)) {
            throw new IllegalArgumentException();
        }
        if (Collections.singletonList(0).containsAll(listOfNumbers)) {
            return Arrays.asList(0.0, 0.0, 1.0);
        }

        double positive = listOfNumbers.stream().filter(x -> x > 0).count();
        double negative = listOfNumbers.stream().filter(x -> x < 0).count();
        double zeros = listOfNumbers.stream().filter(x -> x == 0).count();
        double size = listOfNumbers.size();

        return Arrays.asList(positive / size, negative / size, zeros / size);
    }
}
