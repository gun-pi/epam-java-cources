package com.epam.university.java.core.task046;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class Task046Impl implements Task046 {
    /**
     * List of strings , each containing k numbers of dolls from smaller to larger
     * separated by space.
     *
     * @param k the number of dolls that can be taken at a time
     * @param n total number of nesting dolls
     * @return List of strings , each containing k numbers of dolls from smaller to larger
     */
    @Override
    public List<String> assembleMatryoshka(Integer k, Integer n) {
        if (k == null || n == null) {
            throw new IllegalArgumentException();
        }
        if (k == 1 && n == 1) {
            return Collections.singletonList("0");
        }

        List<Integer> digits = IntStream.range(0, n)
                .boxed()
                .collect(Collectors.toList());

        List<String> combinations = IntStream.range(1, (int)Math.pow(10, k - 1) * (n - 1))
                .mapToObj(x -> String.format("%0" + k + "d", x))
                .collect(Collectors.toList());

        List<String> result = new ArrayList<>();

        POINT:
        for (String each : combinations) {
            for (int i = 1; i < k; i++) {
                int actualDigit = Integer.parseInt(each.substring(i, i + 1));
                int previousDigit = Integer.parseInt(each.substring(i - 1, i));
                if (actualDigit <= previousDigit) {
                    continue POINT;
                }
                if (!digits.contains(actualDigit)) {
                    continue POINT;
                }
            }
            StringBuilder resultString = new StringBuilder();
            for (int i = 0; i < k; i++) {
                if (i == k - 1) {
                    resultString.append(each.charAt(i));
                } else {
                    resultString.append(each.charAt(i)).append(" ");
                }
            }
            result.add(resultString.toString());
        }

        return result;
    }
}
