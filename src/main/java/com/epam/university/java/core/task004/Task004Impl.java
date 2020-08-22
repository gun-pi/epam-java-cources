package com.epam.university.java.core.task004;

import java.util.HashSet;
import java.util.Set;
import java.util.Arrays;
import java.util.LinkedHashSet;

public class Task004Impl implements Task004 {
    /**
     * Find intersection of two arrays.
     *
     * @param first  first array
     * @param second second array
     * @return array of common elements
     * @throws IllegalArgumentException if parameters not provided
     */
    @Override
    public String[] intersection(String[] first, String[] second) {
        if (first == null || second == null) {
            throw new IllegalArgumentException();
        }
        Set<String> firstSet = new HashSet<String>(Arrays.asList(first));
        Set<String> secondSet = new HashSet<String>(Arrays.asList(second));
        firstSet.retainAll(secondSet);

        return firstSet.toArray(new String[firstSet.size()]);
    }

    /**
     * Find union of two arrays.
     *
     * @param first  first array
     * @param second second array
     * @return array of all elements of array
     * @throws IllegalArgumentException if parameters not provided
     */
    @Override
    public String[] union(String[] first, String[] second) {
        if (first == null || second == null) {
            throw new IllegalArgumentException();
        }
        Set<String> result = new LinkedHashSet<>();
        Set<String> firstSet = new LinkedHashSet<String>(Arrays.asList(first));
        Set<String> secondSet = new LinkedHashSet<String>(Arrays.asList(second));

        result.addAll(firstSet);
        result.addAll(secondSet);

        return result.toArray(new String[firstSet.size()]);
    }
}
