package com.pyredevelopment.mathresources;

import java.util.Arrays;

public class Matrix {

    private static int rounding = 8;
    private int nRows;  // Number of rows
    private int nCols;  // Number of columns
    private Number[][] data;    // Main dataset

    public Matrix(int nRows, int nCols) {
        this.nRows = nRows;
        this.nCols = nCols;
        data = new Number[nCols][nRows];
    }

    public static double[][] inverse(double[][] matrix) {

        guardMatrixSquare(matrix); // Requires that the matrix must be square
        guardMatrixInverse(matrix); // Requires that the matrix HAS an inverse (det != 0)

        double[][] output;

        for (double[] row : matrix)
            System.out.println(Arrays.toString(row));

        // First get the cofactor
        output = cofactor(matrix);



        // Then transpose it
        output = transpose(output);



        // Then multiply by the constant 1/determinant(matrix)
        double constant = 1.0/determinant(matrix);
        output = multiply(output, constant);



        // Return the result
        return output;

    }

    public static double[][] multiply(double[][] matrix, double constant) {

        guardMatrixRectangular(matrix); // Ensures the data is rectangular (all rows equal)

        // create an output matrix
        double[][] output = new double[matrix.length][matrix[0].length];

        // Then for each entry
        for (int row = 0; row < matrix.length; row++) {
            for (int col = 0; col < matrix.length; col++) {
                // Insert the relevant value multiplied by the constant
                output[row][col] = matrix[row][col] * constant;
            }
        }

        // Return the results
        return output;


    }

    public static double[][] transpose(double[][] matrix) {

        guardMatrixRectangular(matrix); // Ensures the data is rectangular (all rows equal)

        // Create a new transposed double that is swaps the size of rows and cols
        double[][] transposedMatrix = new double[matrix[0].length][matrix.length];

        // For each entry in the matrix
        for (int row = 0; row < matrix.length; row++) {
            for (int col = 0; col < matrix[0].length; col++) {
                // Insert the data at the flipped position
                transposedMatrix[col][row] = matrix[row][col];
            }
        }

        // Return the result
        return transposedMatrix;

    }

    // Thanks to Daniel for this solution (mostly)
    public static double determinant(double[][] matrix) {

        guardMatrixSquare(matrix); // Guard against non-square matrices

        // Save the length
        int n = matrix.length;

        // Create a duplicate for us to work with
        double[][] A = new double[n][n];

        // Copy each row of values into A
        for (int row = 0; row < n; row++)
            System.arraycopy(matrix[row], 0, A[row], 0, n);

        // If the matrix is 1x1 the determinant is the single value
        if (n == 1) return Math.abs(A[0][0]);

        /* Below is an implementation of Gauss–Jordan elimination I credit to my friend Daniel */
        int swaps = 0;
        for(int i = 0; i < n - 1; i++){
            int pivotRow = i;
            for(int j = i + 1; j < n; j++){
                if(Math.abs(A[j][i]) > Math.abs(A[pivotRow][i])){
                    pivotRow = j;
                }
            }
            if (pivotRow > i)
                swaps++;
            for(int k = i; k < n; k++){
                double temp = A[pivotRow][k];
                A[pivotRow][k] = A[i][k];
                A[i][k] = temp;
            }
            for(int j = i + 1; j < n; j++){
                double temp = A[j][i] / A[i][i];
                for(int k = i; k < n; k++){
                    A[j][k] = A[j][k] - A[i][k] * temp;
                }
            }
        }
        /* End Gauss-Jordan elimination, A is now row-echelon form */

        // Create a variable to hold the output
        double determinant = 1;

        // Now get the product of the main diagonal
        for (int i = 0; i < n; i++) {
            determinant *= A[i][i];
        }

        // If we have an odd number of swaps, multiply by -1
        determinant *= (swaps%2 == 0) ? 1 : -1;

        // For now, round the value to remove floating point error
        determinant = Math.round(determinant * Math.pow(10, rounding) / Math.pow(10, rounding));

        // Return final results
        return determinant;

    }

    public static double[][] cofactor(double[][] matrix) {

        guardMatrixRectangular(matrix); // Ensures the double[][] array is rectangular (all rows equal)

        // Create an output array for the cofactor matrix
        double[][] cofactorMatrix = new double[matrix.length][matrix[0].length];

        // For each entry in the matrix
        for (int row = 0; row < matrix.length; row++) {
            for (int col = 0; col < matrix[0].length; col++) {

                // insert the determinant of the sub-matrix created by ignoring the row and column we are on
                cofactorMatrix[row][col] = determinant(subMatrix(matrix, row, col));

            }
        }

        // Then we iterate through and flip the signs on every other one
        for (int i = 0; i < matrix.length*matrix[0].length; i++) {
            // If it's an even iteration do nothing, otherwise flip signs
            int multiple = (i%2 == 0)? 1 : -1;
            // Actually apply to the cofactor matrix
            cofactorMatrix[i/matrix[0].length][i%matrix[0].length] *= multiple;
        }

        // Return the resulting matrix
        return cofactorMatrix;

    }

    private static void guardMatrixRectangular(double[][] matrix) {

        // Save the length of the first row
        int rowLength = matrix[0].length;

        // Ensure that every row after is the same length, if not throw exception
        for (double[] row : matrix)
            if (rowLength != row.length)
                throw new IllegalArgumentException("Matrix non-rectangular!");

    }

    private static void guardMatrixSquare(double[][] matrix) {

        // For each row in the matrix
        for (double[] row : matrix)
            if (row.length != matrix.length)
                // Check if the length of the row equals columns, if not throw error
                throw new IllegalArgumentException("Matrix non-square!");


    }

    private static void guardMatrixInverse(double [][] matrix) {
        // If the determinant of the matrix is 0
        if (determinant(matrix) == 0)
            // Then there is no inverse
            throw new IllegalArgumentException("This matrix has no inverse: Determinant is 0");
    }


    private static double[][] subMatrix(double[][] matrix, int excluding_row, int excluding_col) {

        guardMatrixRectangular(matrix); // Make sure this dataset is a real matrix (aka, all rows are same length)

        // Create an output matrix of one less length and width
        double[][] output = new double[matrix.length-1][matrix[0].length-1];

        // Helps map the rows after we skip
        int inc = 0;

        // Iterate through each row
        for (int row = 0; row < matrix.length; row++) {

            // If this is the row we're excluding, set the increment and then skip the rest to continue loop
            if (row == excluding_row) { inc = -1; continue; }

            // Copy the parts of the array up until the excluding_col and then everything after, ignoring the excluding_col
            System.arraycopy(matrix[row], 0, output[row+inc], 0, excluding_col);
            System.arraycopy(matrix[row], excluding_col+1, output[row+inc], excluding_col, matrix[row].length-excluding_col-1);

        }

        return output;
    }

    public void add(int r, int c, Number value) {
        data[r][c] = value;
    }

    public Double get(int r, int c) {
        return data[r][c].doubleValue();
    }

    public boolean isSquare() {
        return nRows == nCols;
    }

    public int getSize() {
        if (!isSquare())
            throw new RuntimeException("Non-square matrix doesn't have integer size.");

        return nRows;
    }



    /*
    public static Matrix transpose(Matrix matrix) {
    Matrix transposedMatrix = new Matrix(matrix.getNcols(), matrix.getNrows());
    for (int i=0;i<matrix.getNrows();i++) {
        for (int j=0;j<matrix.getNcols();j++) {
            transposedMatrix.setValueAt(j, i, matrix.getValueAt(i, j));
        }
    }
    return transposedMatrix;
}
     */
    public Matrix transpose() {
        Matrix outMatrix = new Matrix(nRows, nCols);
        for (int r = 0; r < nRows; r++) {
            for (int c = 0; c < nCols; c++) {
                outMatrix.add(r, c, get(c, r));
            }
        }
        return outMatrix;
    }

    public Number determinant() {
        System.out.println("Starting with " + nCols);
        return calculateDeterminant(this);
    }

    public Number calculateDeterminant(Matrix input) {

        // If it's not square
        if (!input.isSquare())
            throw new RuntimeException("Non-square matrix doesn't have determinant.");

        // If the matrix is only one cell, then that is the determinant
        if (input.getSize() == 1)
            return data[0][0];

        // If the matrix is two cells, then matrix is (ac-bd)
        if (input.getSize() == 2)
            return (multiply(input.get(0, 0), input.get(1, 1))) - (multiply(input.get(1, 0), input.get(0, 1)));

        // If the matrix is larger, recursively calculate down
        double sum = 0.0;
        for (int i = 0; i < input.getSize(); i++) {
            int multiple = ((i % 2) == 0) ? 1 : -1; // If it's even subtract, if odd add
            sum += multiply(multiple, input.get(0, i), calculateDeterminant(input.newSubMatrix(0, i)));
        }
        return sum;

    }

    /*
    Matrix mat = new Matrix(matrix.getNrows(), matrix.getNcols());
    for (int i=0;i<matrix.getNrows();i++) {
        for (int j=0; j<matrix.getNcols();j++) {
            mat.setValueAt(i, j, changeSign(i) * changeSign(j) *
                             determinant(createSubMatrix(matrix, i, j)));
        }
    }

    return mat;
     */
    public Matrix cofactor() {
        Matrix outMatrix = new Matrix(nRows, nCols);
        for (int r = 0; r < nRows; r++) {
            for (int c = 0; c < nCols; c++) {
                int rowMultiple = ((r % 2) == 0) ? 1 : -1;
                int colMultiple = ((c % 2) == 0) ? 1 : -1;
                outMatrix.add(r, c, multiply(rowMultiple, colMultiple, newSubMatrix(r, c).determinant()));
            }
        }
        return outMatrix;
    }

    public void multiply(Number constant) {
        for (int r = 0; r < nRows; r++) {
            for (int c = 0; c < nCols; c++) {
                add(r, c, multiply(get(r, c), constant));
            }
        }
    }

    public Number divide(Number a, Number b) {
        return a.doubleValue() / b.doubleValue();
    }

    public Matrix newSubMatrix(int removeRow, int removeCol) {

        Matrix outMatrix = new Matrix(nRows-1, nCols-1);

        int rAdd = -1;
        for (int r = 0; r < nRows; r++) {

            if (r == removeRow)
                continue;

            rAdd++;
            int cAdd = -1;
            for (int c = 0; c < nCols; c++) {

                if (c == removeCol)
                    continue;

                cAdd++;
                outMatrix.add(rAdd, cAdd, get(r, c));
            }
        }

        return outMatrix;

    }

    /* Multiply two Numbers by getting the Double value and returning the result as a Number */
    private static Double multiply(Number ... nums) {
        double product = 1.0;
        for (Number num : nums)
            product *= num.doubleValue();

        return product;
    }




}
