package com.epam.university.java.core.task015;

public class SquareImpl implements Square {
    private Point first;
    private Point second;

    /**
     * Constructor.
     *
     */
    public SquareImpl() {
        this.first = null;
        this.second = null;
    }

    public SquareImpl(Point first, Point second) {
        this.first = first;
        this.second = second;
    }

    /**
     * Get first point of square.
     *
     * @return point value
     */
    @Override
    public Point getFirst() {
        return first;
    }

    /**
     * Get second point of square.
     *
     * @return point value
     */
    @Override
    public Point getSecond() {
        return second;
    }

    /**
     * Set first point of square.
     *
     * @param first point value
     */
    @Override
    public void setFirst(Point first) {
        this.first = first;
    }

    /**
     * Set second point of square.
     *
     * @param second point value
     */
    @Override
    public void setSecond(Point second) {
        this.second = second;
    }
}
