package com.epam.university.java.core.task069;

import java.util.Arrays;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.Set;
import java.util.stream.Collectors;

public class Task069Impl implements Task069 {
    /**
     * This function takes a positive number and returns the
     * next bigger number that can be formed by rearranging its digits.
     * If the digits can't be rearranged to form a bigger number, return -1
     * For example:
     * 12 ==> 21
     * 513 ==> 531
     * 2017 ==> 2071
     * 9 ==> -1
     * 111 ==> -1
     * 531 ==> -1
     *
     * @param num given number
     * @return next bigger number with same digits
     * @throws IllegalArgumentException if the number is negative
     */
    @Override
    public long nextSameDigitsNumber(long num) {
        String number = String.valueOf(num);
        if (num < 0) {
            throw new IllegalArgumentException();
        }
        if (uniqueDigitsNumber(number) == 1) {
            return -1;
        }

        List<Integer> originalNumberDigits = Arrays.stream(number.split(""))
                .filter(x -> !x.equals(""))
                .mapToInt(Integer::parseInt)
                .sorted()
                .boxed()
                .collect(Collectors.toList());

        for (long i = num + 1; i < Math.pow(10, number.length()); i++) {
            List<Integer> biggerNumberDigits = Arrays.stream(String.valueOf(i).split(""))
                    .filter(x -> !x.equals(""))
                    .mapToInt(Integer::parseInt)
                    .sorted()
                    .boxed()
                    .collect(Collectors.toList());
            if (biggerNumberDigits.equals(originalNumberDigits)) {
                return i;
            }
        }

        return 0;
    }

    private int uniqueDigitsNumber(String num) {
        Set<Character> set = new HashSet<>();
        for (int i = 0; i < num.length(); i++) {
            set.add(num.charAt(i));
        }

        return set.size();
    }
}
