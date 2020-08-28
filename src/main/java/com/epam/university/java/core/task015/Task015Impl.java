package com.epam.university.java.core.task015;

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
        return getIntersectionOfSimpleSquares(first, second);
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
        if (square.getFirst().getY() > square.getSecond().getY()) {
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
        } else {
            return square;
        }
    }
}
