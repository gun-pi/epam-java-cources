package com.epam.university.java.core.task058;

public class Task058Impl implements Task058 {
    /**
     * This method fills the matrix of order n in the suitable spiral way.
     *
     * @param n order of matrix.
     * @return filled 2-d array or matrix.
     */
    @Override
    public int[][] fillSpiral(int n) {
        if (n <= 0) {
            throw new IllegalArgumentException();
        }
        if (n == 1) {
            return new int[][]{{1}};
        }

        int[][] matrix = new int[n][n];
        for (int step = 0, a = 0, size; step < n / 2; step++) {
            size = (n - step * 2 - 1);
            for (int i = 0, chunk, chunkIndex, chunkOffset; i < 4 * size; i++) {
                chunk = i / size;
                chunkIndex = i % size;
                chunkOffset = n - step - 1;
                switch (chunk) {
                    case 0:
                        matrix[step][chunkIndex + step] = a + 1;
                        break;
                    case 1:
                        matrix[chunkIndex + step][chunkOffset] = a + 1;
                        break;
                    case 2:
                        matrix[chunkOffset][chunkOffset - chunkIndex] = a + 1;
                        break;
                    case 3:
                        matrix[chunkOffset - chunkIndex][step] = a + 1;
                        break;
                }
                a++;
            }
            if (n % 2 == 1) {
                matrix[n / 2][n / 2] = n * n;
            }
        }

        return matrix;
    }
}
