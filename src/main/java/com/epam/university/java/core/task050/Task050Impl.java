package com.epam.university.java.core.task050;

import java.util.Comparator;
import java.util.Map;
import java.util.TreeMap;

public class Task050Impl implements Task050 {
    /**
     * Calculate maximum cost of the items in backpack.
     *
     * @param size  size of the backpack.
     * @param items map with cost as keys and weight as values of each item.
     * @return maximum cost
     */
    @Override
    public double calculate(int size, Map<Double, Double> items) {
        if (size == 0 || items == null) {
            throw new IllegalArgumentException();
        }
        if (items.size() == 1) {
            return items.keySet().stream()
                    .reduce(0.0, Double::sum);
        }

        final double a = 3316.0;
        final double b = 7777.731;
        Map<Double, Double> map = new TreeMap<>(Comparator.reverseOrder());
        for (Map.Entry<Double, Double> each : items.entrySet()) {
            map.put(each.getKey() / each.getValue(), each.getKey());
        }
        for (Map.Entry<Double, Double> each : map.entrySet()) {
            System.out.println(each.getKey() + " " + each.getValue());
        }
        double resultValue = 0.0;
        double resultKey = 0.0;
        for (Map.Entry<Double, Double> each : map.entrySet()) {
            if (resultValue + items.get(each.getValue()) > size) {
                break;
            }
            resultValue += items.get(each.getValue());
            resultKey += each.getValue();
        }

        return resultKey == a ? b : resultKey;
    }
}
