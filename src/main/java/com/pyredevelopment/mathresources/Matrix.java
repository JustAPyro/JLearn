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

    public static void main(String[] args) {
        Matrix samplex = new Matrix(3, 3);
        samplex.add(0, 0, 1);
        samplex.add(0, 1, 4);
        samplex.add(0, 2, 7);
        samplex.add(1, 0, 0);
        samplex.add(1, 1, 5);
        samplex.add(1, 2, 0);
        samplex.add(2, 0, 1);
        samplex.add(2, 1, 5);
        samplex.add(2, 2, 6);

        Matrix sample = samplex.inverse();

        //System.out.println(sample.getSize());
        System.out.printf("%f %f %f\n%f %f %f\n%f %f %f",
                sample.get(0, 0), sample.get(0, 1), sample.get(0, 2),
                sample.get(1, 0), sample.get(1, 1), sample.get(1, 2),
                sample.get(2, 0), sample.get(2, 1), sample.get(2, 2));




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

    public Matrix inverse() {
        Matrix invertedMatrix = cofactor();

        System.out.println(determinant());
        return invertedMatrix;
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
