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

    public Matrix inverse() {
        cofactor().transpose().multiply
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
        return calculateDeterminant(this);
    }

    public Number calculateDeterminant(Matrix input) {

        // If it's not square
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
            int multiple = ((i % 2) == 0) ? 1 : -1; // If it's even subtract, if odd add
            sum += multiply(multiple, get(0, i), calculateDeterminant(newSubMatrix(0, i)));
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
                int colMultiple = ((c & 2) == 0) ? 1 : -1;
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
