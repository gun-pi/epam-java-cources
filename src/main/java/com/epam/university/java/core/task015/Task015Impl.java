package com.epam.university.java.core.task015;

import com.epam.university.java.core.task013.Vertex;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

public class Task015Impl implements Task015 {
    /**
     * Get area of intersection of two squares. Squares are defined as two
     * opposite points in 2-dimensional area.
     *
     * <p>
     * Example:
     * square 1 = (2, 2) and (4, 4)
     * square 2 = (3, 3) and (5, 5)
     * area is 1 = square (3, 3) and (4, 4)
     * </p>
     * <p>
     * Tip: paint it in the notebook.
     * </p>
     *
     * @param first  first square definition
     * @param second second square definition
     * @return value of area
     */
    @Override
    public double getArea(Square first, Square second) {
        boolean regExpFirstX = Double.compare(first.getFirst().getX(), first.getSecond().getX()) != 0;
        boolean regExpFirstY = Double.compare(first.getFirst().getY(), first.getSecond().getY()) != 0;
        boolean regExpSecondX = Double.compare(second.getFirst().getX(), second.getSecond().getX()) != 0;
        boolean regExpSecondY = Double.compare(second.getFirst().getY(), second.getSecond().getY()) != 0;

        if (regExpFirstX && regExpFirstY && regExpSecondX && regExpSecondY) {
            return getIntersectionOfSimpleSquares(first, second);
        }

        PointImpl[] firstSquare = getSquareCoordinates(first);
        PointImpl[] secondSquare = getSquareCoordinates(second);

        Set<PointImpl> pointsAndIntersections = new HashSet<>();

        pointsAndIntersections.addAll(getPointsInsideSquare(firstSquare, secondSquare));
        pointsAndIntersections.addAll(getPointsInsideSquare(secondSquare, firstSquare));
        pointsAndIntersections.addAll(getIntersections(firstSquare, secondSquare));

        if (pointsAndIntersections.size() < 3) {
            return 0;
        } else if (pointsAndIntersections.size() == 3) {
            return triangleSquare(pointsAndIntersections.toArray(new PointImpl[3]));
        }

        return getPolygonSquare(pointsAndIntersections);
    }

    public double getIntersectionOfSimpleSquares(Square first, Square second) {
        first = sortSquarePoints(first);
        second = sortSquarePoints(second);

        //left-bottom point of intersection rectangle
        double firstX = Math.max(first.getFirst().getX(), second.getFirst().getX());
        double firstY = Math.max(first.getFirst().getY(), second.getFirst().getY());

        //right-top point of intersection rectangle
        double secondX = Math.min(first.getSecond().getX(), second.getSecond().getX());
        double secondY = Math.min(first.getSecond().getY(), second.getSecond().getY());

        // no intersection
        if (firstX > secondX || firstY > secondY) {
            return 0;
        } else {
            return (secondY - firstY) * (secondX - firstX);
        }
    }

    public Square sortSquarePoints (Square square) {
        //check if the square has same X or Y in first and second points
        boolean expX = Double.compare(square.getFirst().getX(), square.getSecond().getX()) != 0;
        boolean expY = Double.compare(square.getFirst().getY(), square.getSecond().getY()) != 0;

        if (square.getFirst().getY() > square.getSecond().getY()
                && expX && expY) {
            Square temp = new SquareImpl();

            double topLeftX = square.getFirst().getX();
            double topLeftY = square.getFirst().getY();

            double bottomRightX = square.getSecond().getX();
            double bottomRightY = square.getSecond().getY();

            Point bottomLeft = new PointImpl();
            bottomLeft.setX(topLeftX);
            bottomLeft.setY(bottomRightY);

            Point topRight = new PointImpl();
            topRight.setX(bottomRightX);
            topRight.setY(topLeftY);

            temp.setFirst(bottomLeft);
            temp.setSecond(topRight);

            return temp;
        } else if (!expX) {
            double newFirstAndSecondY = (square.getFirst().getY() + square.getSecond().getY()) / 2;
            double newFirstX = square.getFirst().getX() - (square.getFirst().getY() - square.getSecond().getY()) / 2;
            double newSecondX = square.getFirst().getX() + (square.getFirst().getY() - square.getSecond().getY()) / 2;

            Point newFirst = new PointImpl(newFirstX, newFirstAndSecondY);
            Point newSecond = new PointImpl(newSecondX, newFirstAndSecondY);

            return new SquareImpl(newFirst, newSecond);
        } else {
            return square;
        }
    }

    private PointImpl[] getSquareCoordinates (Square square) {
        PointImpl[] squareCoordinates = new PointImpl[4];

        square = sortSquarePoints(square);

        double xOfA = square.getFirst().getX();
        double yOfA = square.getFirst().getY();

        double xOfC = square.getSecond().getX();
        double yOfC = square.getSecond().getY();

        //check if the square has same X or Y in first and second points
        boolean expX = Double.compare(square.getFirst().getX(), square.getSecond().getX()) != 0;
        boolean expY = Double.compare(square.getFirst().getY(), square.getSecond().getY()) != 0;

        double xOfB = 0;
        double yOfB = 0;
        double xOfD = 0;
        double yOfD = 0;

        if (expX && expY) {
            xOfB = xOfA;
            yOfB = yOfC;
            xOfD = xOfC;
            yOfD = yOfA;
        } else {
            xOfB = (xOfA + xOfC) / 2;
            yOfB = yOfA + (xOfC - xOfA) / 2;
            xOfD = xOfB;
            yOfD = yOfA - (xOfC - xOfA) / 2;
        }

        squareCoordinates[0] = new PointImpl(xOfA, yOfA);
        squareCoordinates[1] = new PointImpl(xOfB, yOfB);
        squareCoordinates[2] = new PointImpl(xOfC, yOfC);
        squareCoordinates[3] = new PointImpl(xOfD, yOfD);

        return squareCoordinates;
    }

    private List<PointImpl> getPointsInsideSquare(PointImpl[] insideSquare, PointImpl[] pointsOfThisSquare) {
        List<PointImpl> list = new ArrayList<>();

        for (int i = 0; i < pointsOfThisSquare.length; i++) {
            double xOfPoint = pointsOfThisSquare[i].getX();
            double yOfPoint = pointsOfThisSquare[i].getY();

            for (int j = 0; j < insideSquare.length; j++) {
                double[] xOfVectors = new double[4];
                double[] yOfVectors = new double[4];

                for (int k = 0; k < 3; k++) {
                    xOfVectors[k] = insideSquare[k + 1].getX() - insideSquare[k].getX();
                    yOfVectors[k] = insideSquare[k + 1].getY() - insideSquare[k].getY();
                }

                xOfVectors[3] = insideSquare[0].getX() - insideSquare[3].getX();
                yOfVectors[3] = insideSquare[0].getY() - insideSquare[3].getY();

                int countOfMinuses = 0;

                double firstProduct = xOfVectors[3] * (yOfPoint - insideSquare[3].getY())
                        - (xOfPoint - insideSquare[3].getX()) * yOfVectors[3];
                double firstTempSign = Math.signum(firstProduct);
                if (firstTempSign < 0) {
                    countOfMinuses++;
                }

                for (int k = 0; k < 3; k++) {
                    double product = xOfVectors[k] * (yOfPoint - insideSquare[k + 1].getY())
                            - (xOfPoint - insideSquare[k + 1].getX()) * yOfVectors[k];
                    double tempSign = Math.signum(product);
                    if (tempSign < 0) {
                        countOfMinuses++;
                    }
                }

                if (countOfMinuses == 4) {
                    list.add(pointsOfThisSquare[i]);
                }

            }
        }

        return list;
    }


    private Collection<PointImpl> getIntersections(PointImpl[] firstSquare,
                                                   PointImpl[] secondSquare) {
        System.out.println("I was born");
        List<PointImpl> resultList = new ArrayList<>();
        for (int i = 0; i < firstSquare.length; i++) {
            int j = i == firstSquare.length - 1 ? 0 : i + 1;
            PointImpl aPoint = firstSquare[i];
            PointImpl bPoint = firstSquare[j];
            for (int k = 0; k < secondSquare.length; k++) {
                int l = k == secondSquare.length - 1 ? 0 : k + 1;
                PointImpl cPoint = secondSquare[k];
                PointImpl dPoint = secondSquare[l];

                double devider = (aPoint.getX() - bPoint.getX()) * (dPoint.getY() - cPoint.getY())
                        - (aPoint.getY() - bPoint.getY()) * (dPoint.getX() - cPoint.getX());

                double dividendA = (aPoint.getX() - cPoint.getX()) * (dPoint.getY() - cPoint.getY())
                        - (aPoint.getY() - cPoint.getY()) * (dPoint.getX() - cPoint.getX());

                double dividendB = (aPoint.getX() - bPoint.getX()) * (aPoint.getY() - cPoint.getY())
                        - (aPoint.getY() - bPoint.getY()) * (aPoint.getX() - cPoint.getX());

                double resultA = dividendA / devider;
                double resultB = dividendB / devider;

                if (resultA >= 0 && resultA <= 1 && resultB >= 0 && resultB <= 1) {
                    double interX = aPoint.getX() + resultA * (bPoint.getX() - aPoint.getX());
                    double interY = aPoint.getY() + resultA * (bPoint.getY() - aPoint.getY());

                    resultList.add(new PointImpl(interX, interY));
                    //System.out.println(interX + " " + interY);
                }

            }
        }


        return resultList;
    }

    private Collection<PointImpl> getIntersections2(PointImpl[] firstSquare,
                                                     PointImpl[] secondSquare) {
        System.out.println("I was born");
        List<PointImpl> intersections = new ArrayList<>();
        for (int i = 0; i < firstSquare.length; i++) {
            int j;

            if (i == firstSquare.length - 1) {
                j = 0;
            } else {
                j = i + 1;
            }

            PointImpl aPointOfFirstSquare = firstSquare[i];
            PointImpl bPointOfFirstSquare = firstSquare[j];

            for (int k = 0; k < secondSquare.length; k++) {
                int l;

                if (k == firstSquare.length - 1) {
                    l = 0;
                } else {
                    l = k + 1;
                }

                PointImpl aPointOfSecondSquare = secondSquare[k];
                PointImpl bPointOfSecondSquare = secondSquare[l];

                double devider = (aPointOfFirstSquare.getX() - bPointOfFirstSquare.getX()) * (bPointOfSecondSquare.getY() - aPointOfSecondSquare.getY())
                        - (aPointOfFirstSquare.getY() - bPointOfFirstSquare.getY()) * (bPointOfSecondSquare.getX() - aPointOfSecondSquare.getX());

                double dividendA = (aPointOfFirstSquare.getX() - aPointOfSecondSquare.getX()) * (bPointOfSecondSquare.getY() - aPointOfSecondSquare.getY())
                        - (aPointOfFirstSquare.getY() - aPointOfSecondSquare.getY()) * (bPointOfSecondSquare.getX() - aPointOfSecondSquare.getX());

                double dividendB = (aPointOfFirstSquare.getX() - bPointOfFirstSquare.getX()) * (aPointOfFirstSquare.getY() - aPointOfSecondSquare.getY())
                        - (aPointOfFirstSquare.getY() - bPointOfFirstSquare.getY()) * (aPointOfFirstSquare.getX() - aPointOfSecondSquare.getX());

                double resultA = dividendA / devider;
                double resultB = dividendB / devider;

                if (resultA >= 0 && resultA <= 1 && resultB >= 0 && resultB <= 1) {
                    double interX = aPointOfFirstSquare.getX() + resultA * (bPointOfFirstSquare.getX() - aPointOfFirstSquare.getX());
                    double interY = aPointOfFirstSquare.getY() + resultA * (bPointOfFirstSquare.getY() - aPointOfFirstSquare.getY());

                    intersections.add(new PointImpl(interX, interY));
                    //System.out.println(interX + " " + interY);
                }
            }
        }

        System.out.println("----");
        for (PointImpl each : intersections) {
            System.out.println(each.getX() + " " + each.getY());
        }
        System.out.println("---");

        return intersections;
    }

    public PointImpl calculateInterceptionPoint(PointImpl aPoint, PointImpl bPoint, PointImpl cPoint, PointImpl dPoint) {

        double devider = (aPoint.getX() - bPoint.getX()) * (dPoint.getY() - cPoint.getY())
                - (aPoint.getY() - bPoint.getY()) * (dPoint.getX() - cPoint.getX());

        double dividendA = (aPoint.getX() - cPoint.getX()) * (dPoint.getY() - cPoint.getY())
                - (aPoint.getY() - cPoint.getY()) * (dPoint.getX() - cPoint.getX());

        double dividendB = (aPoint.getX() - bPoint.getX()) * (aPoint.getY() - cPoint.getY())
                - (aPoint.getY() - bPoint.getY()) * (aPoint.getX() - cPoint.getX());

        double resultA = dividendA / devider;
        double resultB = dividendB / devider;

        if (resultA >= 0 && resultA <= 1 && resultB >= 0 && resultB <= 1) {
            double interX = aPoint.getX() + resultA * (bPoint.getX() - aPoint.getX());
            double interY = aPoint.getY() + resultA * (bPoint.getY() - aPoint.getY());

            return new PointImpl(interX, interY);
        } else {
            return null;
        }
    }

    private double triangleSquare(PointImpl[] triangle) {
        double[] ribs = new double[3];
        for (int i = 0; i < triangle.length; i++) {
            int j = i == triangle.length - 1 ? 0 : i + 1;
            ribs[i] = Math.sqrt(Math.pow(triangle[j].getX() - triangle[i].getX(), 2)
                    + Math.pow(triangle[j].getY() - triangle[i].getY(), 2));
        }

        double semiPer = 0.5 * (ribs[0] + ribs[1] + ribs[2]);
        return Math.sqrt(semiPer * (semiPer - ribs[0]) * (semiPer - ribs[1])
                * (semiPer - ribs[2]));
    }

    private double getPolygonSquare(Collection<PointImpl> polygon) {
        ArrayList<PointImpl> list = new ArrayList<>(polygon);
        LinkedList<PointImpl> ordered = new LinkedList<>();
        ordered.add(list.remove(0));
        while (!list.isEmpty()) {
            if (list.size() == 1) {
                ordered.add(list.remove(0));
                break;
            }
            label:
            for (int i = 0; i < list.size(); i++) {
                double xOfA = ordered.getLast().getX();
                double yOfA = ordered.getLast().getY();

                double xOfB = list.get(i).getX();
                double yOfB = list.get(i).getY();

                for (int j = 0; j < list.size(); j++) {
                    if (j == i) {
                        continue;
                    }
                    double xOfC = list.get(j).getX();
                    double yOfC = list.get(j).getY();
                    double a = (xOfB - xOfA) * (yOfC - yOfB);
                    double b = (yOfB - yOfA) * (xOfC - xOfB);
                    double result = a - b;
                    if (result > 0) {
                        continue label;
                    }
                }
                ordered.add(list.remove(i));
            }
        }
        double square = 0.0;
        PointImpl main = ordered.remove(0);
        for (int i = 0; i < ordered.size() - 1; i++) {
            square += triangleSquare(new PointImpl[]{main, ordered.get(i), ordered.get(i + 1)});
        }
        return square;
    }

}
