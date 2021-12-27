package com.pyredevelopment.math.matrix;

import com.pyredevelopment.math.exceptions.NoInverseMatrixException;

public final class Matrix {

    /**
     * Constructor is private as Matrix is a helper class that cannot be instantiated.
     */
    private Matrix() {}

    public static double[][] inverse(double[][] matrix) {

        // Save the length of the matrix (nxn)
        int n = matrix.length;

        // Check to make sure the array is square, if not then there is no inverse, so throw appropriate exception
        for (double[] array : matrix)
            if (array.length != n) throw new NoInverseMatrixException();

        // Step 1 : Find the Matrix Of Minors
        double[][] matrixOfMinors = new double[n][n];



        return null;
    }

    /**
     * This method calculates the determinant of a matrix, the determinant is a special value calculate that can
     * be used for specific matrix calculations or linear regressions. This uses the laplace calculation.
     * For more basic understanding about determinants or how they are calculated, consider reading:
     * https://www.mathsisfun.com/algebra/matrix-determinant.html
     * @param matrix The matrix you wish to calculate the determinate of
     * @return The value of the determinant
     */
    public static double determinant(double[][] matrix) {
        return determinantCalculator(matrix, matrix.length);
    }

    /**
     * Private recursive method that handles the determinate calculator.
     * This is the function that {@code determinant} calls.
     * @param matrix The matrix
     * @param size The size of the matrix
     * @return
     */
    private static double determinantCalculator(double[][] matrix, int size) {

        // Exit case
        if (size == 2) {
            // If the matrix is size two, just return the smaller calculation
            return matrix[0][0]*matrix[1][1]-matrix[1][0]*matrix[0][1];
        }

        double result = 0;

        // To solve a determinate for size 'size' you have to calculate as many sub matricies
        for (int i = 0; i < size; i++) {

            double[][] subMatrix = new double[size-1][size-1];

            for (int r = 1; r < size; r++) { // Row starts at 1 because we don't consider the top row in sub-matrix
                int shift = 0;
                for (int c = 0; c < size; c++) {

                    if (c == i) {
                        shift++;
                        continue;
                    }

                    subMatrix[r-1][c-shift] = matrix[r][c];

                }
            }

            double d = matrix[0][i] * determinantCalculator(subMatrix, size - 1);
            if (i%2 == 0) {
                result += d;
            }
            else {
                result -= d;
            }

        }
        return result;
    }

}
