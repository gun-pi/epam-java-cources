package com.epam.university.java.core.task038;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;



public class GraphImpl implements Graph {
    private Map<Integer, List<Integer>> adjacencyMatrix;
    private List<VertexImpl> vertexList;
    private int vertexCount;

    public GraphImpl() {
    }

    /**
     * Constructor of VertexImpl.
     * @param vertexCount count of vertices
     */
    public GraphImpl(int vertexCount) {
        this.vertexCount = vertexCount;
        this.vertexList = new ArrayList<>(vertexCount);
        adjacencyMatrix = new HashMap<>();
    }

    /**
     * Create vertex with given id, x and y coordinates.
     *
     * @param id vertex id
     * @param x  x coordinate
     * @param y  y coordinate
     */
    @Override
    public void createVertex(int id, int x, int y) {
        vertexList.add(new VertexImpl(id, x, y));
        adjacencyMatrix.put(id, new ArrayList<>());
        if (vertexList.size() > vertexCount) {
            throw new IllegalArgumentException();
        }
    }

    /**
     * Add connection directed from source vertex to target vertex.
     *
     * @param fromId id of source vertex
     * @param toId   id of target vertex
     */
    @Override
    public void connectVertices(int fromId, int toId) {
        adjacencyMatrix.get(fromId).add(toId);
    }

    public Map<Integer, List<Integer>> getAdjacencyMatrix() {
        return adjacencyMatrix;
    }

    public List<VertexImpl> getVertexList() {
        return vertexList;
    }
}
