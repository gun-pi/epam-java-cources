package com.epam.university.java.core.task066;

public class Task066Impl implements Task066 {
    /**
     * <p>
     * Given an long limiter and String, find the number
     * of letter 'a' in the first limiter letters of infinite input String.
     * <p>
     * For example, if the String s = "abcac", and int limiter = 10,
     * the substring we consider is "abcacabcac", the first 10 characters
     * of this infinite string. There are 4 occurrences of 'a' in the substring.
     * <p>
     * Ex.2  ("layla", 9) -> laylalayl -> output 3;
     * Ex.3 ("pancho", 20) -> panchopanchopanchopa -> output 4;
     *
     * </p>
     *
     * @param infiniteString String
     * @param limiter        long
     * @return long - the count of 'a's.
     */
    @Override
    public long repeatString(String infiniteString, long limiter) {
        if (infiniteString == null || limiter < 0) {
            throw new IllegalArgumentException();
        }
        if (limiter == 0) {
            return 0;
        }

        return (limiter / infiniteString.length()) * getNumberOfA(infiniteString)
                + getNumberOfA(infiniteString.substring(0, (int)(limiter % infiniteString.length())));
    }

    private long getNumberOfA(String string) {
        long counter = 0;
        for (String each : string.split("")) {
            if (each.equals("a")) {
                counter++;
            }
        }
        return counter;
    }
}
