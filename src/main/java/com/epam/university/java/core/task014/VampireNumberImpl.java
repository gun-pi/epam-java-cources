package com.epam.university.java.core.task014;

import java.util.Objects;

public class VampireNumberImpl implements VampireNumber {
    int multiplication;
    int first;
    int second;

    /**
     * Constructor.
     */
    public VampireNumberImpl(int multiplication, int first, int second) {
        this.multiplication = multiplication;
        this.first = first;
        this.second = second;
    }

    /**
     * Get multiplication of numbers.
     *
     * @return value of multiplication
     */
    @Override
    public int getMultiplication() {
        return multiplication;
    }

    /**
     * Get first part of vampire number.
     *
     * @return value if first part
     */
    @Override
    public int getFirst() {
        return first;
    }

    /**
     * Get second part of vampire number.
     *
     * @return value of second part
     */
    @Override
    public int getSecond() {
        return second;
    }

    @Override
    public boolean equals(Object value) {
        if (this == value) {
            return true;
        }
        if (value == null || getClass() != value.getClass()) {
            return false;
        }
        VampireNumberImpl that = (VampireNumberImpl) value;
        boolean exp1 = getMultiplication() == that.getMultiplication()
                && getFirst() == that.getFirst()
                && getSecond() == that.getSecond();
        boolean exp2 = getMultiplication() == that.getMultiplication()
                && getFirst() == that.getSecond()
                && getSecond() == that.getFirst();
        return exp1 || exp2;
    }
}
