package com.epam.university.java.core.task013;

import java.util.Collection;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.function.ToDoubleFunction;

public class FigureImpl implements Figure {
    private List<Vertex> vertices;
    private int desiredVertexCount;
    private int actualVertexCount;

    /**
     * Constructor.
     */
    public FigureImpl(int vertexCount) {
        if (vertexCount < 3) {
            throw new IllegalArgumentException();
        }
        vertices = new ArrayList<Vertex>();
        desiredVertexCount = vertexCount;
        actualVertexCount = 0;
    }

    /**
     * Add vertex to figure with designated coordinates.
     *
     * @param vertex vertex to add
     */
    @Override
    public void addVertex(Vertex vertex) {
        vertices.add(vertex);
        actualVertexCount++;

        if (actualVertexCount > desiredVertexCount) {
            throw new IllegalArgumentException();
        }

        if (actualVertexCount == desiredVertexCount) {
            double averageX = 0;
            double averageY = 0;

            for (Vertex each : vertices) {
                averageX += each.getX();
                averageY += each.getY();
            }

            averageX /= vertices.size();
            averageY /= vertices.size();

            double finalAverageY = averageY;
            double finalAverageX = averageX;

            vertices.sort(Comparator.comparingDouble(
                    new ToDoubleFunction<Vertex>() {
                        public double applyAsDouble(Vertex p) {
                            return Math.atan2(p.getY() - finalAverageY, p.getX() - finalAverageX);
                        }
                    }
            ));
        }
    }

    /**
     * Get all vertexes of figure.
     *
     * @return collection of vertexes
     */
    @Override
    public Collection<Vertex> getVertexes() {
        return vertices;
    }
}
