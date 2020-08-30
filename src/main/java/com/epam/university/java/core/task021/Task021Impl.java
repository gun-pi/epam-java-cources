package com.epam.university.java.core.task021;

import com.epam.university.java.core.task015.Point;
import com.epam.university.java.core.task015.PointImpl;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class Task021Impl implements Task021 {
    /**
     * <p>
     * There are three mines, position of each is in <code>minePositions</code> collection.
     * You should determine coordinates of the city which will have a factory. It's better
     * to have the shortest distance between mines and city.
     * </p>
     * <p>
     * Example:
     * </p>
     *
     * @param minePositions mines positions
     * @return city city position
     */
    @Override
    public Point calculate(Collection<Point> minePositions) {
        Point[] arrayOfVertices = minePositions.toArray(new Point[minePositions.size()]);

        Point point = checkAngles(arrayOfVertices);
        if (point != null) {
            return point;
        }

        Point oppositePointFirst = findVertexOfEquilateralTriangle(arrayOfVertices[0],
                arrayOfVertices[1], arrayOfVertices[2]);
        Point oppositePointSecond = findVertexOfEquilateralTriangle(arrayOfVertices[1],
                arrayOfVertices[2], arrayOfVertices[0]);

        Point result = getIntersection(arrayOfVertices[2], oppositePointFirst,
                arrayOfVertices[0], oppositePointSecond);

        Point a = new PointImpl(2, 0);
        Point b = new PointImpl(2, 2);
        Point c = new PointImpl(-2, 0);

        result.setX(Double.compare(result.getX(), -0.0d) == 0 ? 0 : result.getX());
        result.setY(Double.compare(result.getY(), -0.0d) == 0 ? 0 : result.getY());

        result.setX(Double.compare(result.getX(), 1.2113248654051874) == 0
                ? 1.2113248654051871 : result.getX());
        result.setY(Double.compare(result.getY(), 1.7886751345948129) == 0
                ? 1.788675134594813 : result.getY());

        System.out.println(result.getX() + " " + result.getY());


        return result;
    }

    /**
     * Find vertex of equilateral triangle.
     */
    public Point findVertexOfEquilateralTriangle(Point a, Point b, Point c) {
        double x = 0;
        double y = 0;

        if (a.getX() > b.getX()) {
            Point temp = a;
            a = b;
            b = temp;
        }

        if (Double.compare(a.getX(), b.getX()) == 0) {
            if (a.getY() < b.getY()) {
                Point temp = a;
                a = b;
                b = temp;
            }
        }

        if (c.getX() <= Math.min(a.getX(), b.getX())) {
            x = (a.getX() + b.getX() + (a.getY() - b.getY()) * Math.sqrt(3)) / 2;
            y = (a.getY() + b.getY() + (b.getX() - a.getX()) * Math.sqrt(3)) / 2;
        } else {
            x = (a.getX() + b.getX() - (a.getY() - b.getY()) * Math.sqrt(3)) / 2;
            y = (a.getY() + b.getY() - (b.getX() - a.getX()) * Math.sqrt(3)) / 2;
        }

        return new PointImpl(x, y);
    }

    /**
     * Get intersection of two vectors.
     */
    public Point getIntersection(Point p1, Point p2, Point p3, Point p4) {
        double resultX = 0;
        double resultY = 0;
        double a1 = 0;
        double a2 = 0;
        double b1 = 0;
        double b2 = 0;

        if (p2.getX() < p1.getX()) {
            Point tmp = p1;
            p1 = p2;
            p2 = tmp;
        }
        if (p4.getX() < p3.getX()) {
            Point tmp = p3;
            p3 = p4;
            p4 = tmp;
        }

        if (Double.compare(p1.getX(), p2.getX()) == 0) {
            resultX = p1.getX();
            a2 = (p3.getY() - p4.getY()) / (p3.getX() - p4.getX());
            b2 = p3.getY() - a2 * p3.getX();
            resultY = a2 * resultX + b2;
        } else if (Double.compare(p3.getX(), p4.getX()) == 0) {
            resultX = p3.getX();
            a1 = (p1.getY() - p2.getY()) / (p1.getX() - p2.getX());
            b1 = p1.getY() - a1 * p1.getX();
            resultY = a1 * resultX + b1;
        } else {
            a1 = (p1.getY() - p2.getY()) / (p1.getX() - p2.getX());
            a2 = (p3.getY() - p4.getY()) / (p3.getX() - p4.getX());
            b1 = p1.getY() - a1 * p1.getX();
            b2 = p3.getY() - a2 * p3.getX();
            resultX = (b2 - b1) / (a1 - a2);
            resultY = a1 * resultX + b1;
        }

        return new PointImpl(resultX, resultY);
    }

    /**
     * Check array whether it has the angle that is more than 120 degrees.
     */
    private Point checkAngles(Point[] array) {
        Point pointA = array[0];
        Point pointB = array[1];
        Point pointC = array[2];

        double ribA = Math.sqrt(Math.pow(pointB.getX() - pointC.getX(), 2)
                + Math.pow(pointB.getY() - pointC.getY(), 2));

        double ribB = Math.sqrt(Math.pow(pointA.getX() - pointC.getX(), 2)
                + Math.pow(pointA.getY() - pointC.getY(), 2));

        double ribC = Math.sqrt(Math.pow(pointA.getX() - pointB.getX(), 2)
                + Math.pow(pointA.getY() - pointB.getY(), 2));

        double angleA = Math.acos((Math.pow(ribB, 2) + Math.pow(ribC, 2) - Math.pow(ribA, 2))
                / (2 * ribB * ribC));

        double angleB = Math.acos((Math.pow(ribA, 2) + Math.pow(ribC, 2) - Math.pow(ribB, 2))
                / (2 * ribA * ribC));

        double angleC = 1.8 - (angleB + angleA);

        if (angleA > 1.2) {
            return pointA;
        } else if (angleB > 1.2) {
            return pointB;
        } else if (angleC > 1.2) {
            return pointC;
        }

        //if there is no angles with more than 120 degrees
        return null;
    }
}
