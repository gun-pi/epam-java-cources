package com.epam.university.java.core.task016;

public class CoordinateFactoryImpl implements CoordinateFactory {
    /**
     * Create new coordinate instance with the following values.
     *
     * @param x x coordinate
     * @param y y coordinate
     * @return coordinate instance
     */
    @Override
    public Coordinate newInstance(int x, int y) {
        CoordinateImpl temp = new CoordinateImpl();
        temp.setX(x);
        temp.setY(y);
        return temp;
    }
}
