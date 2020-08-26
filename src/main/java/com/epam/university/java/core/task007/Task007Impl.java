package com.epam.university.java.core.task007;

import java.util.Collection;
import java.util.List;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Arrays;

public class Task007Impl implements Task007 {
    /**
     * Multiply polynomials. Each collection contains coefficients near i-th member, ex:
     * <p>
     * 3x^3 + 2x^2 - 5x corresponds to collection [3, 2, -5, 0]
     * </p>
     * <p>
     * Task is to multiply two polynomials: ex:
     * </p>
     * <p>
     * (3x^3 + 2x^2 - 5x) * (4x^4 + 2x^2) == multiplyPolynomial([3, 2, -5, 0], [4, 0, 2, 0, 0])
     * </p>
     * <p>
     * If polynomial is degenerating to zero, return [0]
     * </p>
     *
     * @param first  collection with coefficients near-th member of first polynomial
     * @param second collection with coefficients near-th member of second polynomial
     * @return collection of members in multiplied polynomials
     */
    @Override
    public Collection<Integer> multiplyPolynomial(Collection<Integer> first,
                                                  Collection<Integer> second) {
        if (first == null || second == null) {
            throw new IllegalArgumentException();
        }

        int m = first.size();
        int n = second.size();

        Collections.reverse((List)first);
        Integer[] firstArray = first.toArray(new Integer[first.size()]);
        Collections.reverse((List)second);
        Integer[] secondArray = second.toArray(new Integer[second.size()]);

        Integer[] resultArray = new Integer[m + n - 1];
        for (int i = 0; i < m + n - 1; i++) {
            resultArray[i] = 0;
        }

        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                resultArray[i + j] += firstArray[i] * secondArray[j];
            }
        }

        List<Integer> result = Arrays.asList(resultArray);
        Collections.reverse(result);


        return result;
    }
}
