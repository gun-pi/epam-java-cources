package com.epam.university.java.core.task020;

import java.util.Collection;

public class Task020Impl implements Task020 {
    /**
     * You have a collection of gemstones, each stone is one line in the <code>stones</code>
     * collection. Each stone consists of several parts, each part is a character
     * in a stone string. You should determine amount of common parts which all stones
     * contains.
     * <p>
     * Try to do it in a functional approach.
     * </p>
     * <p>
     * Example: stones are [abc, bcd, cde], result is 1 because only part 'c' is in all stones.
     * Example: stones are [abc, cde, efg], result is 0 because there are no common parts.
     * </p>
     *
     * @param stones stones collection
     * @return amount of common parts
     */
    @Override
    public int calculate(Collection<String> stones) {
        if (stones == null || stones.size() == 0) {
            throw new IllegalArgumentException();
        }

        Operationable operation;
        operation = (ztonez) -> {
            String[] stonesAsArray = ztonez.toArray(new String[ztonez.size()]);
            String templateString = (String) stonesAsArray[1];
            StringBuilder thatString = new StringBuilder(templateString);
            for (String each : ztonez) {
                int size = templateString.length() - 1;
                for (int i = size; i >= 0; i--) {
                    if (!each.contains(String.format("%c", templateString.charAt(i)))) {
                        System.out.println(String.format("%c", templateString.charAt(i)));
                        templateString = templateString.replaceFirst(
                                String.format("%c", templateString.charAt(i)), "");
                    }
                }
            }
            return templateString.length();
        };

        int result = operation.calculate(stones);
        System.out.println(result);

        return result;
    }

    interface Operationable {
        int calculate(Collection<String> stones);
    }
}
