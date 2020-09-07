package com.epam.university.java.core.task022;

import java.util.ArrayList;
import java.util.Collection;

public class Task022Impl implements Task022 {
    /**
     * Given collection of n integers, find the maximum value that can be calculated
     * by summing (n-1) integers.
     *
     * @param numbers collection of numbers
     * @return maximum value
     */
    @Override
    public int maxSum(Collection<Integer> numbers) {
        if (numbers == null || numbers.size() == 0) {
            throw new IllegalArgumentException();
        }

        ArrayList<Integer> array = new ArrayList<>(numbers);
        Integer min = 999999999;
        int sum = 0;
        for (Integer each : array) {
            if (each < min) {
                min = each;
            }
        }
        for (Integer each : array) {
            if (!each.equals(min)) {
                sum += each;
            }
        }

        return sum;
    }

    /**
     * Given collection of n integer, find the minimum value that can ba calculated
     * by summing (n-1) integers.
     *
     * @param numbers collection of numbers
     * @return minimum value
     */
    @Override
    public int minSum(Collection<Integer> numbers) {
        if (numbers == null || numbers.size() == 0) {
            throw new IllegalArgumentException();
        }

        ArrayList<Integer> array = new ArrayList<>(numbers);
        Integer max = -999999999;
        int sum = 0;
        for (Integer each : array) {
            if (each > max) {
                max = each;
            }
        }
        for (Integer each : array) {
            if (!each.equals(max)) {
                sum += each;
            }
        }

        return sum;
    }
}
