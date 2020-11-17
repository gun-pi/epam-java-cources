package com.epam.university.java.core.task067;

import java.util.Deque;
import java.util.LinkedList;

public class Task067Impl implements Task067 {
    /**
     * <p>
     * The Chess Knight.
     * You will be given the location of a knight, and an end location.
     * The knight can move in a "L" shape.
     * "L" shape movement means that the knight can change it's x coordinate
     * by 2 and it's y coordinate by 1
     * or it can change it's y coordinate by 2 and it's x coordinate by 1.
     * </p>
     * For example, if the knight is at the position (0, 0), it can move to:
     * (1,2), (1,-2), (2,1), (2,-1), (-1,2), (-1,-2), (-2,1), (-2, -1)
     * <p>
     * Return the least amount of steps needed to go from
     * the position START (x1, y1) to END (x2, y2).
     * </p>
     * <p>
     * Examples
     * </p>
     * (x1, y1, x2, y2) -> result
     * <p>
     * (1, 1, 8, 8) -> 6
     * (1, 1, 3, 2) -> 1
     * (8, 8, 3, 3) -> 4
     *
     * </p>
     *
     * @param x1 y start position int
     * @param y1 x start position int
     * @param x2 y end position int
     * @param y2 x end position int
     * @return int -  least amount of steps needed to go from (x1, y1) to (x2, y2).
     */
    @Override
    public int knightMovements(int x1, int y1, int x2, int y2) {
        int[] knightPos = {x1, y1};
        int[] targetPos = {x2, y2};

        return minStepToReachTarget(knightPos, targetPos);
    }

    private int minStepToReachTarget(int[] startPosition, int[] targetPosition) {
        int sizeOfField = 8;
        int[] dx = {-2, -1, 1, 2, -2, -1, 1, 2};
        int[] dy = {-1, -2, -2, -1, 1, 2, 2, 1};

        Deque<Cell> queue = new LinkedList<>();
        boolean[][] fieldOfVisitedPoints = new boolean[sizeOfField + 1][sizeOfField + 1];

        fieldOfVisitedPoints[startPosition[0]][startPosition[1]] = true;
        queue.add(new Cell(startPosition[0], startPosition[1], 0));
        Cell cell;
        int x;
        int y;
        while (!queue.isEmpty()) {
            cell = queue.pollFirst();

            if (cell.getX() == targetPosition[0] && cell.getY() == targetPosition[1]) {
                return cell.getDistance();
            }

            for (int i = 0; i < 8; i++) {
                x = cell.getX() + dx[i];
                y = cell.getY() + dy[i];

                if (x >= 1 && x <= 8
                        && y >= 1 && y <= 8
                        && !fieldOfVisitedPoints[x][y]) {
                    fieldOfVisitedPoints[x][y] = true;
                    queue.add(new Cell(x, y, cell.getDistance() + 1));
                }
            }
        }

        return Integer.MAX_VALUE;
    }
}


