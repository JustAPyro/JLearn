package com.pyredevelopment.math.matrix;

import com.pyredevelopment.math.exceptions.MatrixNotSquareException;
import com.pyredevelopment.math.exceptions.NoInverseMatrixException;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.security.InvalidParameterException;

public final class Matrix {

    private static int rounding = 10;

    /**
     * Constructor is private as Matrix is a helper class that cannot be instantiated.
     */
    private Matrix() {}

    public static double[][] round(double[][] matrix) {
        for (int r = 0; r < matrix.length; r++) {
            for (int c = 0; c < matrix[r].length; c++) {
                matrix[r][c] = round(matrix[r][c], rounding);
            }
        }
        return matrix;
    }

    public static double[][] inverse(double[][] matrix) {

        // Save the length of the matrix (nxn)
        int n = matrix.length;

        // Check to make sure the array is square, if not then there is no inverse, so throw appropriate exception
        for (double[] array : matrix)
            if (array.length != n) throw new NoInverseMatrixException();

        // Create a matrix to hold output
        double[][] matrixOut;

        // Step 1 : Find the Matrix Of Minors
        matrixOut = matrixOfMinors(matrix);

        // Step 2 : Get Matrix of Cofactors
        matrixOut = matrixOfCofactors(matrixOut);

        // Step 3 : Adjoint (Flip on y=-x axis)
        matrixOut = matrixAdjoint(matrixOut);

        // Step 4: Multiply by 1/Determinant of original
        double multiple = 1/determinant(matrix);
        matrixOut = matrixMultiply(matrixOut, multiple);

        return matrixOut;
    }

    public static double[][] matrixTranspose(double [][] matrix) {

        // save rows/cols of matrix
        int r = matrix.length;
        int c = matrix[0].length;

        // Ensure it's a safe matrix (All rows have same number of columns
        for (double[] arr : matrix)
            if (arr.length != c) throw new MatrixNotSquareException("Transpose"); // TODO: Correct this error to a unsafe matrix

        // Create output matrix
        double[][] matrixOut = new double[c][r]; // Create an inverted matrix

        // For each unit in original matrix
        for (int row = 0; row < r; row++) {
            for (int col = 0; col < c; col++) {
                matrixOut[col][row] = matrix[row][col]; // Insert the mirrored element
            }
        }

        // Return matrix out
        return matrixOut;




    }

    public static double[][] matrixMultiply(double[][] matrixA, double[][] matrixB) {

        int mar = matrixA.length;
        int mac = matrixA[0].length;
        for (double[] arr : matrixA)
            if (arr.length != mac) throw new MatrixNotSquareException("reg3"); // TODO, change exception type

        int mbr = matrixB.length;
        int mbc = matrixB[0].length;
        for(double[] arr : matrixB)
            if (arr.length != mbc) throw new MatrixNotSquareException("reg2"); // TODO: CHange this as well

        if (mac != mbr) throw new MatrixNotSquareException("reg1");

        // Size of matrix output is equal to the equal shape
        int n = mar;


        double[][] matrixOut = new double[mar][mbc];

        for (int row = 0; row < matrixOut.length; row++) {
            for (int col = 0; col < matrixOut[row].length; col++) {
                matrixOut[row][col] = matrixMultiplicationCalculator(row, col, matrixA, matrixB);
            }
        }

        return matrixOut;
    }

    private static double matrixMultiplicationCalculator(int row, int col, double[][] mA, double[][] mB) {
        double output = 0;
        for (int i = 0; i < mB.length; i++) {
            output += mA[row][i]*mB[i][col];
        }
        return output;
    }

    public static double[][] matrixMultiply(double[][] matrix, double multiple) {

        int rows = matrix.length;
        int cols = matrix[0].length;

        double[][] matrixOut = new double[rows][cols];

        for (int row = 0; row < rows; row++) {
            for (int col = 0; col < cols; col++) {
                matrixOut[row][col] = matrix[row][col]*multiple;
            }
        }

        return matrixOut;

    }

    public static double[][] matrixAdjoint(double[][] matrix) {

        int n = matrix.length; // Start by assuming n

        // Check to make sure matrix is square, if not throw error
        for (double[] arr : matrix)
            if (arr.length != n) throw new MatrixNotSquareException("Matrix Adjoint");

        // If the matrix is a single unit, just return that unchanged
        if (n == 1)
            return new double[][] {{matrix[0][0]}};

        double[][] outMatrix = new double[n][n];
        for (int row = 0; row < n; row++) {
            for (int col = 0; col < n; col++) {
                outMatrix[row][col] = matrix[col][row];
            }
        }

        return outMatrix;
    }


    public static double[][] matrixOfCofactors(double[][] matrixOfMinors) {

        int n = matrixOfMinors.length;

        for (double[] arr : matrixOfMinors)
            if (arr.length != n) throw new MatrixNotSquareException("Matrix of Cofactors"); // TODO: Adjust this exception

        double[][] matrixOut = new double[n][n];

        boolean invert = false;
        for (int row = 0; row < n; row++) {
            for (int col = 0; col < n; col++) {

                if (!invert)
                    matrixOut[row][col] = matrixOfMinors[row][col];
                else
                    matrixOut[row][col] = matrixOfMinors[row][col]*-1;
                invert = !invert;
            }
            if (matrixOfMinors.length%2 == 0)
                invert = !invert;
        }

        return matrixOut;

    }

    /**
     * TODO: Fill Docs
     * @param matrix
     * @return
     */
    public static double[][] matrixOfMinors(double[][] matrix) {

        // Start by assuming n to be equal to the number of rows
        int n = matrix.length;

        // Error handle to make sure each row length = number of rows
        for (double[] arr : matrix)
            if (arr.length != n) throw new MatrixNotSquareException("Matrix of Minors"); // TODO: Create new exception

        double[][] outputMatrix = new double[n][n];

        // FOR EACH CELL OF THE OUTPUT ARRAY
        for (int row = 0; row < n; row++) {
            for (int col = 0; col < n; col++) {

                double[][] determinateMatrix = new double[n-1][n-1];

                // Calculate the output matrix
                int rowShift = 0;
                for (int ri = 0; ri < n; ri++) {
                    if (ri == row) { rowShift++; continue; }
                    int colShift = 0;
                    for (int ci = 0; ci < n; ci++) {
                        if (ci == col) {colShift++; continue; }

                        determinateMatrix[ri-rowShift][ci-colShift] = matrix[ri][ci];

                    }
                }

                outputMatrix[row][col] = determinant(determinateMatrix);

            }
        }

        return outputMatrix;

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

        // If size is 1, just return that element
        if (size == 1) {
            return matrix[0][0];
        }

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

    public static double round(double value, int places) {
        if (places < 0) throw new IllegalArgumentException();

        BigDecimal bd = new BigDecimal(Double.toString(value));
        bd = bd.setScale(places, RoundingMode.HALF_UP);
        return bd.doubleValue();
    }

}
