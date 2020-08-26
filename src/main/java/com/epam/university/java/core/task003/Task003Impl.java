package com.epam.university.java.core.task003;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.Set;
import java.util.TreeSet;

public class Task003Impl implements Task003 {
    /**
     * Invert array.
     *
     * @param source source array
     * @return inverted array
     * @throws IllegalArgumentException if array not provided
     */
    @Override
    public String[] invert(String[] source) {
        if (source == null) {
            throw new IllegalArgumentException();
        }

        for (int i = 0; i < source.length / 2; i++) {
            String temp = source[i];
            source[i] = source[source.length - i - 1];
            source[source.length - i - 1] = temp;
        }

        return source;
    }

    /**
     * Join two arrays.
     *
     * @param first  first array
     * @param second second array
     * @return new array which contains items from first and second arrays
     * @throws IllegalArgumentException if arrays not provided
     */
    @Override
    public String[] join(String[] first, String[] second) {
        if (first == null || second == null) {
            throw new IllegalArgumentException();
        }

        int length = first.length + second.length;
        String[] mergedArray = new String[length];
        int pos = 0;
        for (String element : first) {
            mergedArray[pos] = element;
            pos++;
        }
        for (String element : second) {
            mergedArray[pos] = element;
            pos++;
        }

        return mergedArray;
    }

    /**
     * Find max element in array.
     *
     * @param source source array
     * @return value of maximal element in array
     * @throws IllegalArgumentException if array not provided
     */
    @Override
    public int findMax(int[] source) {
        if (source == null || source.length == 0) {
            throw new IllegalArgumentException();
        }

        int maxElement = source[0];
        for (int element : source) {
            if (element > maxElement) {
                maxElement = element;
            }
        }

        return maxElement;
    }

    /**
     * Filter array elements in accordance with condition.
     *
     * @param source    source array
     * @param condition condition instance
     * @return filtered array
     * @throws IllegalArgumentException if array or condition not provided
     */
    @Override
    public String[] filter(String[] source, FilteringCondition condition) {
        if (source == null || condition == null) {
            throw new IllegalArgumentException();
        }

        ArrayList<String> result = new ArrayList<>();
        for (String eachElementFromSource : source) {
            if (condition.isValid(eachElementFromSource)) {
                result.add(eachElementFromSource);
            }
        }

        return result.toArray(new String[result.size()]);
    }

    /**
     * Remove elements from source array.
     *
     * @param source   source array
     * @param toRemote elements to remove
     * @return new array without removed elements
     * @throws IllegalArgumentException if parameters not provided
     */
    @Override
    public String[] removeElements(String[] source, String[] toRemote) {
        if (source == null || toRemote == null) {
            throw new IllegalArgumentException();
        }

        ArrayList<String> result = new ArrayList<>();
        for (String elementFromSource : source) {
            boolean flag = true;
            for (String elementFromToRemote : toRemote) {
                if (elementFromSource.equals(elementFromToRemote)) {
                    flag = false;
                    break;
                }
            }
            if (flag) {
                result.add(elementFromSource);
            }
        }

        result.trimToSize();
        return result.toArray(new String[result.size()]);
    }

    /**
     * Convert source array in accordance with provided operation.
     *
     * @param source    source array
     * @param operation operation instance
     * @return converted array
     * @throws IllegalArgumentException if parameters not provided
     */
    @Override
    public String[] map(String[] source, MappingOperation operation) {
        if (source == null || operation == null) {
            throw new IllegalArgumentException();
        }

        for (int i = 0; i < source.length; i++) {
            source[i] = operation.map(source[i]);
        }

        return source;
    }

    /**
     * Convert source array in accordance with provided operation.
     *
     * @param source    source array
     * @param operation operation instance
     * @return converted array
     * @throws IllegalArgumentException if parameters not provided
     */
    @Override
    public String[] flatMap(String[] source, FlatMappingOperation operation) {
        if (source == null || operation == null) {
            throw new IllegalArgumentException();
        }

        TreeSet<String> result = new TreeSet<>(Collections.reverseOrder());
        for (String eachElementFromSource : source) {
            String[] arrayFromElement = operation.flatMap(eachElementFromSource);
            Set<String> setFromArrayFromElement =
                    new HashSet<String>(Arrays.asList(arrayFromElement));
            result.addAll(setFromArrayFromElement);
        }

        TreeSet<Integer> resultInInteger = new TreeSet<>(Collections.reverseOrder());
        LinkedHashSet<String> resultInString = new LinkedHashSet<>();

        for (String eachElementFromResult : result) {
            resultInInteger.add(Integer.parseInt(eachElementFromResult));
        }

        for (Integer eachElementFromResultInInteger : resultInInteger) {
            resultInString.add(Integer.toString(eachElementFromResultInInteger));
        }

        return resultInString.toArray(new String[result.size()]);
    }
}
