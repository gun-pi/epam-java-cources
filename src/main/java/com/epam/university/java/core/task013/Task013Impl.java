package com.epam.university.java.core.task013;

import com.epam.university.java.core.task012.GraphAction;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.ArrayList;

public class Task013Impl implements Task013 {
    /**
     * Invoke actions with <code>figure</code> instance.
     *
     * @param figure  source figure
     * @param actions collection of actions
     * @return modified figure
     */
    @Override
    public Figure invokeActions(Figure figure, Collection<FigureAction> actions) {
        if (actions == null || figure == null) {
            throw new IllegalArgumentException();
        }
        if (actions.equals(Arrays.asList())) {
            throw new IllegalArgumentException();
        }
        for (FigureAction current: actions) {
            current.run(figure);
        }

        return figure;
    }

    /**
     * Check if figure is convex polygon. Convex polygon is a simple polygon in which
     * no line segment between two points on the boundary goes outside the polygon.
     *
     * @param figure figure go check
     * @return is figure convex polygon
     */
    @Override
    public boolean isConvexPolygon(Figure figure) {
        if (figure == null) {
            throw new IllegalArgumentException();
        }
        List<Vertex> vertices = new ArrayList<>(figure.getVertexes());
        int vertexCount = vertices.size();
        
        int[] xOfVectors = new int[vertexCount];
        int[] yOfVectors = new int[vertexCount];

        for (int i = 0; i < vertexCount - 1; i++) {
            xOfVectors[i] = vertices.get(i + 1).getX() - vertices.get(i).getX();
            yOfVectors[i] = vertices.get(i + 1).getY() - vertices.get(i).getY();
        }

        xOfVectors[vertexCount - 1] = vertices.get(0).getX() - vertices.get(vertexCount - 1).getX();
        yOfVectors[vertexCount - 1] = vertices.get(0).getY() - vertices.get(vertexCount - 1).getY();

        int firstProduct = xOfVectors[vertexCount - 1] * yOfVectors[0]
                - xOfVectors[0] * yOfVectors[vertexCount - 1];
        double signOfFirstProduct = Math.signum(firstProduct);
        double tempSign = 1;
        for (int i = 0; i < vertexCount - 1; i++) {
            int product = xOfVectors[i] * yOfVectors[i + 1] - xOfVectors[i + 1] * yOfVectors[i];
            tempSign = tempSign * signOfFirstProduct * Math.signum(product);
            if (tempSign < 0) {
                return false;
            }
        }
        return true;
    }
}
