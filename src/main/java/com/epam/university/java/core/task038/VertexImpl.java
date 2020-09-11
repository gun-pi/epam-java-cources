package com.epam.university.java.core.task038;

public class VertexImpl implements Vertex {
    private int id;
    private int x;
    private int y;

    public VertexImpl() {
    }

    /**
     * Constructor of VertexImpl.
     *
     * @param id id
     * @param x x
     * @param y y
     */
    public VertexImpl(int id, int x, int y) {
        this.id = id;
        this.x = x;
        this.y = y;
    }

    /**
     * Get id of vertex.
     *
     * @return id value
     */
    @Override
    public int getId() {
        return id;
    }

    /**
     * Get x coordinate of vertex.
     *
     * @return x coordinate value
     */
    @Override
    public int getX() {
        return x;
    }

    /**
     * Get y coordinate of vertex.
     *
     * @return y coordinate value
     */
    @Override
    public int getY() {
        return y;
    }
}
