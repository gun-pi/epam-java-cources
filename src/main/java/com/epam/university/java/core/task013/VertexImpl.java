package com.epam.university.java.core.task013;

public class VertexImpl implements Vertex {
    int x;
    int y;

    public VertexImpl(int x, int y) {
        this.x = x;
        this.y = y;
    }

    /**
     * Get x coordinate of vertex.
     *
     * @return coordinate value
     */
    @Override
    public int getX() {
        return x;
    }

    /**
     * Set x coordinate of vertex.
     *
     * @param value coordinate value
     */
    @Override
    public void setX(int value) {
        x = value;
    }

    /**
     * Get y coordinate of vertex.
     *
     * @return coordinate value
     */
    @Override
    public int getY() {
        return y;
    }

    /**
     * Set y coordinate of vertex.
     *
     * @param value coordinate value
     */
    @Override
    public void setY(int value) {
        y = value;
    }
}
