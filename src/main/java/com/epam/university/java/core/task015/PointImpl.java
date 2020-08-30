package com.epam.university.java.core.task015;

import java.util.Objects;

/**
 * Point in 2-dimensional area.
 */
public class PointImpl implements Point {
    double x;
    double y;

    public PointImpl() {
        x = 0;
        y = 0;
    }

    public PointImpl(double x, double y) {
        this.x = x;
        this.y = y;
    }

    /**
     * Get x value of point.
     * @return value
     */
    public double getX() {
        return x;
    }

    /**
     * Get y value of point.
     * @return value
     */
    public double getY() {
        return y;
    }

    /**
     * Set x value of point.
     * @param x value
     */
    public void setX(double x) {
        this.x = x;
    }

    /**
     * Set y value of point.
     * @param y value
     */
    public void setY(double y) {
        this.y = y;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof PointImpl)) {
            return false;
        }
        PointImpl point = (PointImpl) o;
        return Double.compare(point.getX(), getX()) == 0
                && Double.compare(point.getY(), getY()) == 0;
    }

    @Override
    public int hashCode() {
        return Objects.hash(getX(), getY());
    }
}
