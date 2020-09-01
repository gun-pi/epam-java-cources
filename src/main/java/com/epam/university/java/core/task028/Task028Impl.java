package com.epam.university.java.core.task028;

public class Task028Impl implements Task028 {
    /**
     * Find the number of ways that a given integer <code>value</code> can be expressed as the
     * sum of the Nth power of unique, natural numbers.
     *
     * <p>
     * Examples:
     * value is 10, power is 2, result is 1 because 10 = 1^2 + 3^2
     * value is 100, power is 2, result is 3 because 100 = 10^2 =
     * 6^2 + 8^2 = 1^2 + 3^2 + 4^2 + 5^2 + 7^2
     * </p>
     *
     * @param value value number
     * @param power power
     * @return number of ways
     */
    @Override
    public int getWays(int value, int power) {
        return checkRecursive(value, power, 1, 0);
    }

    /**
     * Recursive method that finds number of ways.
     *
     * @param value value number
     * @param power power
     * @param currentNumber current number
     * @param currentSum current sum
     * @return number of ways
     */
    public int checkRecursive(int value, int power, int currentNumber,int currentSum) {
        int numberOfWays = 0;

        int powOfI = (int)Math.pow(currentNumber, power);
        while (powOfI + currentSum < value) {
            numberOfWays += checkRecursive(value, power, currentNumber + 1, powOfI + currentSum);
            currentNumber++;
            powOfI = (int)Math.pow(currentNumber, power);
        }

        if (powOfI + currentSum == value) {
            numberOfWays++;
        }

        return numberOfWays;
    }

}
