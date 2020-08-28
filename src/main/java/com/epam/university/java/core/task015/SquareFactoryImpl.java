package com.epam.university.java.core.task015;

public class SquareFactoryImpl implements SquareFactory {
    /**
     * Build square of two points.
     *
     * @param first  first point
     * @param second second point
     * @return square instance
     */
    @Override
    public Square newInstance(Point first, Point second) {
        Square temp = new SquareImpl();
        temp.setFirst(first);
        temp.setSecond(second);
        return temp;
    }
}
