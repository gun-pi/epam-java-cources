package com.epam.university.java.core.task067;

public class Cell {
    private int x;
    private int y;
    private int distance;

    /**
     * Constructor.
     *
     * @param x x
     * @param y y
     * @param distance distance
     */
    public Cell(int x, int y, int distance) {
        this.x = x;
        this.y = y;
        this.distance = distance;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public int getDistance() {
        return distance;
    }

    public void setDistance(int distance) {
        this.distance = distance;
    }
}
