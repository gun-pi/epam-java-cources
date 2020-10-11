package com.epam.university.java.core.task044;

import java.util.List;

public class Task044Impl implements Task044 {
    /**
     * count of traces in the snow.
     *
     * @param points total number of points
     * @param lines  each element is a two points separated by space
     * @return number of traces
     */
    @Override
    public int findCountOfTraces(Integer points, List<String> lines) {
        if (lines == null || points == null) {
            throw new IllegalArgumentException();
        }
        if (lines.isEmpty()) {
            return points;
        }

        int counter = 0;
        int max = 0;
        POINT:
        for (int i = 0; i < lines.size(); i++) {
            int localMax = Math.max(
                    Integer.parseInt(lines.get(i).substring(0, 1)),
                    Integer.parseInt(lines.get(i).substring(2, 3))
            );
            if (max < localMax) {
                max = localMax;
            }
            for (int j = 1; j < lines.size(); j++) {
                if (lines.get(i).charAt(2) == lines.get(j).charAt(0)) {
                    continue POINT;
                }
            }
            counter++;
        }

        if (max != points) {
            return counter + (points - max);
        } else {
            return counter;
        }
    }
}
