package com.epam.university.java.core.task012;

import java.util.Collection;
import java.util.Set;
import java.util.Map;
import java.util.HashMap;
import java.util.HashSet;

public class GraphImpl implements Graph {
    private Map<Integer,Set> adjacencyMatrix;
    private int verticesCount;


    GraphImpl(int v) {
        if (v == 0) {
            throw new IllegalArgumentException();
        }
        adjacencyMatrix = new HashMap<>();
        this.verticesCount = v;
    }

    /**
     * Create edge between <code>from</code> and <code>to</code> vertexes.
     *
     * @param from vertex edge starts from
     * @param to   vertex edge ends with
     */

    @Override
    public void createEdge(int from, int to) {
        if (from > verticesCount || to > verticesCount) {
            throw new IllegalArgumentException();
        }
        if (adjacencyMatrix.containsKey(from)) {
            adjacencyMatrix.get(from).add(to);

        } else {
            Set<Integer> set = new HashSet<>();
            set.add(to);
            adjacencyMatrix.put(from, set);
        }
        
        if (adjacencyMatrix.containsKey(to)) {
            adjacencyMatrix.get(to).add(from);
        } else {
            Set<Integer> set  = new HashSet<>();
            set.add(from);
            adjacencyMatrix.put(to, set);
        }
    }

    /**
     * Check is there edge between <code>from</code> and <code>to</code> vertexes.
     *
     * @param from vertex edge starts from
     * @param to   vertex edge ends with
     * @return is there edge between vertexes
     */

    @Override
    public boolean edgeExists(int from, int to) {
        return adjacencyMatrix.get(from).contains(to) || adjacencyMatrix.get(to).contains(from);
    }

    /**
     * Remove edge between <code>from</code> and <code>to</code> vertexes.
     *
     * @param from vertex edge starts from
     * @param to   vertex edge ends with
     */

    @Override
    public void removeEdge(int from, int to) {
        if (!adjacencyMatrix.containsKey(from) || !adjacencyMatrix.get(from).contains(to)) {
            throw new IllegalArgumentException();
        }
        adjacencyMatrix.get(from).remove(to);
        if (adjacencyMatrix.get(from).size() == 0) {
            adjacencyMatrix.remove(from);
        }

        adjacencyMatrix.get(to).remove(from);
        if (adjacencyMatrix.get(to).size() == 0) {
            adjacencyMatrix.remove(to);
        }

    }

    /**
     * Get collection of vertexes which is available from <code>from</code>.
     *
     * @param from vertex from
     * @return collection of available vertexes
     */

    @Override
    public Collection<Integer> getAdjacent(int from) {
        return adjacencyMatrix.get(from);
    }

    public Map<Integer,Set> getAdjacencyMatrix() {
        return adjacencyMatrix;
    }
    
}
