package com.epam.university.java.core.task005;

public class PiHolderImpl implements PiHolder {
    int first;
    int second;
    /**
     * Numerator value.
     *
     * @return value of numerator
     */

    @Override
    public int getFirst() {
        return first;
    }

    /**
     * Denominator value.
     *
     * @return value of denominator
     */

    @Override
    public int getSecond() {
        return second;
    }

    public void setFirst(int first) {
        this.first = first;
    }

    public void setSecond(int second) {
        this.second = second;
    }
}
