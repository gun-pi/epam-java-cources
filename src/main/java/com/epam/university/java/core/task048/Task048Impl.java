package com.epam.university.java.core.task048;

import java.util.ArrayList;
import java.util.Collection;

public class Task048Impl implements Task048 {
    /**
     * Return collection of Armstrong numbers.
     *
     * @param from int value of start range number
     * @param to   int value of end range number
     * @return collection of Armstrong numbers
     * @throws IllegalArgumentException if input parameters are not set or not valid
     */
    @Override
    public Collection<Integer> getArmstrongNumbers(Integer from, Integer to) {
        if (from == null || to == null
                || from <= 0 || to <= 0) {
            throw new IllegalArgumentException();
        }

        Collection<Integer> armstrongNumbers = new ArrayList<>();
        for (int i = from; i <= to; i++) {
            String number = String.format("%d", i);
            int result = 0;
            for (int j = 0; j < number.length(); j++) {
                result += Math.pow(
                        Integer.parseInt(number.substring(j, j + 1)),
                        number.length()
                );
            }
            if (i == result) {
                armstrongNumbers.add(i);
            }
        }

        return armstrongNumbers;
    }
}
