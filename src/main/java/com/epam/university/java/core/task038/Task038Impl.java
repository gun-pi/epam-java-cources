package com.epam.university.java.core.task038;

import java.util.Collection;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;


public class Task038Impl implements Task038 {
    /**
     * Create new Graph instance and execute collection of actions. Return
     * the result graph instance.
     *
     * @param sourceGraph initial graph instance
     * @param actions     collection of actions
     * @return updated graph instance
     */
    @Override
    public Graph invokeActions(Graph sourceGraph, Collection<GraphAction> actions) {
        for (GraphAction current: actions) {
            current.run(sourceGraph);
        }
        return sourceGraph;
    }

    /**
     * Find the path with minimum possible sum of its edges weights.
     * Path must contain source and target vertex.
     * If path doesn't exist, return empty collection.
     *
     * @param graph   graph instance
     * @param startId is id of source vertex
     * @param endId   is id of target vertex
     * @return collection of vertices from source to target with minimum
     *     possible sum of edge weights
     */
    @Override
    public Collection<Vertex> getShortestPath(Graph graph, int startId, int endId) {
        GraphImpl castedGraph = (GraphImpl) graph;
        List<VertexImpl> vertices = castedGraph.getVertexList();

        Map<Integer, List<Integer>> adjacencyMatrix = castedGraph.getAdjacencyMatrix();
        List<Integer> paths = adjacencyMatrix.get(startId);

        Set<Vertex> resultCollection = new LinkedHashSet<>();
        resultCollection.add(getVertexById(vertices, startId));

        if (paths.contains(endId)) {
            resultCollection.add(getVertexById(vertices, endId));
            return resultCollection;
        }

        Set<Vertex> tempCollection = new LinkedHashSet<>();

        if (paths.size() == 0) {
            return new LinkedHashSet<>();
        } else {
            for (Integer id : paths) {
                tempCollection.addAll(resultCollection);

                resultCollection.addAll(getShortestPath(graph, id, endId));

                if (resultCollection.contains(getVertexById(vertices, endId))) {
                    break;
                }
            }
        }

        if (tempCollection.containsAll(resultCollection)) {
            resultCollection.remove(getVertexById(vertices, startId));
        }

        return resultCollection;
    }

    private VertexImpl getVertexById(List<VertexImpl> list, int id) {
        VertexImpl resVertex = null;
        for (VertexImpl vertex : list) {
            if (vertex.getId() == id) {
                resVertex = vertex;
            }
        }
        return resVertex;
    }
}
