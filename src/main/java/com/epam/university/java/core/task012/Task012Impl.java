package com.epam.university.java.core.task012;

import java.util.Arrays;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;
import java.util.LinkedList;

public class Task012Impl implements Task012 {
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
        if (sourceGraph == null || actions == null || actions.equals(Arrays.asList())) {
            throw new IllegalArgumentException();
        }
        for (GraphAction current: actions) {
            current.run(sourceGraph);
        }
        return sourceGraph;
    }

    /**
     * Check is there path in <code>graph</code> between <code>from</code> and
     * <code>to</code> vertexes.
     *
     * @param graph graph instance
     * @param from  source vertex
     * @param to    target vertex
     * @return is path exists
     */

    @Override
    public boolean pathExists(Graph graph, int from, int to) {
        if (graph == null) {
            throw new IllegalArgumentException();
        }
        GraphImpl graphCasted = (GraphImpl) graph;
        if (!graphCasted.getAdjacencyMatrix().containsKey(from)
                || !graphCasted.getAdjacencyMatrix().containsKey(to)) {
            throw new IllegalArgumentException();
        }
        LinkedList<Integer> stack = new LinkedList<>();
        Set<Integer> isVisited = new HashSet<>();

        stack.push(from);

        while (stack.size() != 0) {
            Collection<Integer> set = graph.getAdjacent(stack.peek());
            isVisited.add(stack.peek());
            stack.pop();

            if (set == null) {
                return false;
            }

            for (Integer i: set) {
                if (i.equals(to)) {
                    return true;
                }

                if (!isVisited.contains(i)) {
                    stack.push(i);
                }
            }
        }
        return false;
    }
}
