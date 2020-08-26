package com.epam.university.java.core.task014;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

public class Task014Impl implements Task014 {
    /**
     * A vampire number has an even number of digits and is formed by
     * multiplying a pair of numbers containing half the number of digits
     * of the result. The digits are taken from the original number
     * in any order. Pairs of trailing zeroes are not allowed.
     *
     * <p>
     * Example: 1260 = 21 * 60
     * </p>
     * <p>
     * {@see https://en.wikipedia.org/wiki/Vampire_number}
     * </p>
     *
     * @return collection of vampire numbers
     */
    @Override
    public Collection<VampireNumber> getVampireNumbers() {
        Set<VampireNumber> collection = new HashSet<>();

        for (int i = 10; i <= 99; i++) {
            for (int j = 10; j <= 99; j++) {
                if (checkVampirism(i,j)) {
                    VampireNumberImpl vampireNumber = new VampireNumberImpl(i * j, i, j);
                    boolean isThisNumberUnique = true;
                    for (VampireNumber each : collection) {
                        if (vampireNumber.equals(each)) {
                            isThisNumberUnique = false;
                            break;
                        }
                    }

                    if (isThisNumberUnique) {
                        collection.add(vampireNumber);
                    }
                }
            }
        }

        return collection;
    }

    /**
     * Check vampirism of a number.
     */
    public boolean checkVampirism(int first, int second) {
        int multiplication = first * second;

        String firstInString = String.format("%d", first);
        String secondInString = String.format("%d", second);
        String multiplicationInString = String.format("%d", multiplication);


        for (int i = 0; i < firstInString.length(); i++) {
            if (!multiplicationInString.contains(String.format("%c", firstInString.charAt(i)))) {
                return false;
            }
            multiplicationInString = multiplicationInString.replaceFirst(
                    String.format("%c", firstInString.charAt(i)), "");

        }

        for (int i = 0; i < secondInString.length(); i++) {
            if (!multiplicationInString.contains(String.format("%c", secondInString.charAt(i)))) {
                return false;
            }
            multiplicationInString = multiplicationInString.replaceFirst(
                    String.format("%c", secondInString.charAt(i)), "");
        }

        return true;
    }
}
