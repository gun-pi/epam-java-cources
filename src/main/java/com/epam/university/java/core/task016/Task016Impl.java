package com.epam.university.java.core.task016;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class Task016Impl implements  Task016 {
    /**
     * Given a 2-dimensional system of coordinates. There is a circle with
     * center in (0, 0) point. You should return coordinates of squares with side of 0.5
     * which fully inside the circle.
     *
     * <p>
     * Example: radius is 1 and only points (1, 1), (-1, 1), (-1, -1) and (1, -1) inside.
     * Tip: here we have two scales - one for circle and one for squares. 0.5 in circle
     * equals to 1 in square scale. It was made to simplify presentation.
     * </p>
     *
     * @param radius radius of circle
     * @return collection square coordinates
     */
    @Override
    public Collection<Coordinate> getSquaresInsideCircle(int radius) {
        if (radius <= 0) {
            throw new IllegalArgumentException();
        }

        Collection<Coordinate> list = new ArrayList<>();

        int radiusSquareScale = radius * 2;

        for (int i = radiusSquareScale; i >= 0; i--) {
            if (i == 0) {
                continue;
            }

            for (int j = - radiusSquareScale; j <= radiusSquareScale; j++) {
                if (j == 0) {
                    continue;
                }
                if (Math.sqrt((double)(i * i + j * j)) < radiusSquareScale) {
                    list.add(new CoordinateImpl(j, i));
                }
            }

            for (int j = - radiusSquareScale; j <= radiusSquareScale; j++) {
                if (j == 0) {
                    continue;
                }
                if (Math.sqrt((double)(i * i + j * j)) < radiusSquareScale) {
                    list.add(new CoordinateImpl(j, -i));
                }
            }
        }

        return list;
    }

}
