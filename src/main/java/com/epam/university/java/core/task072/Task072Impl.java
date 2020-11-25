package com.epam.university.java.core.task072;

import java.util.Arrays;
import java.util.Objects;

public class Task072Impl implements Task072 {
    /**
     * <p>
     * Task072.
     * In this task you need to implement some methods
     * using varargs.
     * I hope you will get in touch
     * with it and the process of getting the topic
     * won't be hard and boring.
     * </p>
     *
     * <p>
     * Have fun :)
     * </p>
     * <p>
     * Method that finds an average number.
     * </p>
     *
     * @param numbers some numbers
     * @returns an average number
     */
    @Override
    public double averageNum(Integer... numbers) {
        if (numbers == null) {
            throw new IllegalArgumentException();
        }
        if (Arrays.stream(numbers).anyMatch(Objects::isNull)) {
            throw new IllegalArgumentException();
        }

        return Arrays.stream(numbers).mapToInt(x -> x).average().getAsDouble();
    }

    /**
     * Method that finds the longest word.
     *
     * @param words some words
     * @returns the longest word
     */
    @Override
    public String theLongestWord(String... words) {
        if (words == null) {
            throw new IllegalArgumentException();
        }

        int maxLength = 0;
        String result = null;
        for (String word : words) {
            if (word == null) {
                throw new IllegalArgumentException();
            }
            if (word.length() > maxLength) {
                maxLength = word.length();
                result = word;
            }
        }

        return result;
    }

    /**
     * Method that finds a result of boolean
     * operation.
     *
     * @param operation logical operation
     * @param values    boolean values
     * @return a result of operation
     */
    @Override
    public boolean logicalOperations(String operation, Boolean... values) {
        if (operation == null || values == null) {
            throw new IllegalArgumentException();
        }
        if (Arrays.stream(values).anyMatch(Objects::isNull)) {
            throw new IllegalArgumentException();
        }

        switch (operation) {
            case "AND":
                return Arrays.stream(values).allMatch(x -> x);
            case "OR":
                return Arrays.stream(values).anyMatch(x -> x);
            case "XOR":
                return Arrays.asList(values).containsAll(Arrays.asList(true, false));
            default:
                return false;
        }
    }
}
