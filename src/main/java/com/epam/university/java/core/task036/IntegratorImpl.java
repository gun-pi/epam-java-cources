package com.epam.university.java.core.task036;

import java.util.function.Function;

public class IntegratorImpl implements Integrator {
    /**
     * Integration algorithm implementation.
     *
     * @param left     left limit
     * @param right    right limit
     * @param function function to integrate
     * @return integration results
     */
    @Override
    public double integrate(double left, double right, Function<Double, Double> function) {
        int n = 400000;
        double a = left;
        double b = right;
        double h = (b - a) / n;
        double s1 = 0;
        double s2 = 0;
        double result = 0;

        for (int i = 0; i < n; i++) {
            if (i % 2 == 0) {
                s1 += function.apply(a + i * h);
            } else {
                s2 += function.apply(a + i * h);
            }
        }

        result = (h / 3) * (function.apply(a) + function.apply(b) + 2 * s1 + 4 * s2);

        return result;
    }
}
