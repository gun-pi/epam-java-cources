package com.epam.university.java.core.task070;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class Task070Impl implements Task070 {
    /**
     * Return collection of Smith numbers.
     *
     * @param from int value of start range number
     * @param to   int value of end range number
     * @return collection of Smith numbers
     * @throws IllegalArgumentException if input parameters are not provided
     */
    @Override
    public Collection<Integer> getSmithNumbers(Integer from, Integer to) {
        if (from == null || to == null) {
            throw new IllegalArgumentException();
        }

        Collection<Integer> collection = new ArrayList<>();
        for (int i = from; i <= to; i++) {
            if (findPrimeFactors(i).size() == 1) {
                continue;
            }

            String currentNumber = String.valueOf(i);
            int sumOfDigits = 0;
            for (int j = 0; j < currentNumber.length(); j++) {
                sumOfDigits += Integer.parseInt(currentNumber.charAt(j) + "");
            }


            int sumOfDigitsInFactorization = 0;
            for (Integer primeFactor : findPrimeFactors(i)) {
                String primeNumber = String.valueOf(primeFactor);
                for (int j = 0; j < primeNumber.length(); j++) {
                    sumOfDigitsInFactorization += Integer.parseInt(primeNumber.charAt(j) + "");
                }
            }

            if (sumOfDigitsInFactorization == sumOfDigits) {
                collection.add(i);
            }
        }

        return collection;
    }

    private static List<Integer> findPrimeFactors(int i) {
        List<Integer> list = new ArrayList<Integer>();
        for (int n = 2; n <= i; n++) {
            while (i % n == 0) {
                list.add(n);
                i /= n;
            }
        }
        return list;
    }
}
