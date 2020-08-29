package com.epam.university.java.core.task015;

import java.util.Objects;

public class PointImpl implements Point {
    private double x;
    private double y;

    /**
     * Constructor.
     *
     */
    public PointImpl() {
        this.x = 0;
        this.y = 0;
    }

    public PointImpl(double x, double y) {
        this.x = x;
        this.y = y;
    }

    /**
     * Get x value of point.
     *
     * @return value
     */
    @Override
    public double getX() {
        return x;
    }

    /**
     * Get y value of point.
     *
     * @return value
     */
    @Override
    public double getY() {
        return y;
    }

    /**
     * Set x value of point.
     *
     * @param x value
     */
    @Override
    public void setX(double x) {
        this.x = x;
    }

    /**
     * Set y value of point.
     *
     * @param y value
     */
    @Override
    public void setY(double y) {
        this.y = y;
    }
}
