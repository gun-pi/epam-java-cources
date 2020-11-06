package com.epam.university.java.core.task050;

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

        TreeMap<Double, Map.Entry<Double, Double>> sortedItemsMap = new TreeMap<>();
        for (Map.Entry<Double, Double> entry : items.entrySet()) {
            sortedItemsMap.put(entry.getKey() / entry.getValue(), entry);
        }

        double result = 0;
        while (size > 0) {
            Map.Entry<Double, Map.Entry<Double, Double>> entryFromSortedItemsMap
                    = sortedItemsMap.pollLastEntry();
            if (entryFromSortedItemsMap.getValue().getValue() < size) {
                result += entryFromSortedItemsMap.getValue().getKey();
                size -= entryFromSortedItemsMap.getValue().getValue();
            } else {
                result += size * entryFromSortedItemsMap.getKey();
                break;
            }
        }

        return Double.parseDouble(
                String.format("%.3f", result)
        );
    }
}
