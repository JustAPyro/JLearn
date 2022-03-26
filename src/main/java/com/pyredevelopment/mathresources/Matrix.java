package com.pyredevelopment.mathresources;

public class Matrix {

    private int nRows;  // Number of rows
    private int nCols;  // Number of columns
    private Number[][] data;    // Main dataset

    public Matrix(int nRows, int nCols) {
        this.nRows = nRows;
        this.nCols = nCols;
        data = new Number[nCols][nRows];
    }

    public void add(int r, int c, Number value) {
        data[r][c] = value;
    }

    public Number get(int r, int c) {
        return data[r][c];
    }

    public boolean isSquare() {
        return nRows == nCols;
    }

    public int getSize() {
        if (!isSquare())
            throw new RuntimeException("Non-square matrix doesn't have integer size.");

        return nRows;
    }

    public Number determinant() {

        if (!isSquare())
            throw new RuntimeException("Non-square matrix doesn't have determinant.");

        // If the matrix is only one cell, then that is the determinant
        if (getSize() == 1)
            return data[0][0];

        // If the matrix is two cells, then matrix is (ac-bd)
        if (getSize() == 2)
            return (multiply(get(0, 0), get(1, 1))) - (multiply(get(1, 0), get(0, 1)));

        // If the matrix is larger, recursively calculate down
        double sum = 0.0;
        for (int i = 0; i < nCols; i++) {
            int multiple = ((i % 2) == 0) ? 1 : -1;
            //sum += multiple * matrix.getValueAt(0, i) * determinant(submatrix(matrix, 0, i));
        }
        return sum;

    }


    public Matrix newSubMatrix(int row, int col) {



    }

    /* Multiply two Numbers by getting the Double value and returning the result as a Number */
    private static Double multiply(Number a, Number b) {
        return a.doubleValue() * b.doubleValue();
    }




}
