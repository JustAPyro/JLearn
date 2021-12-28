package com.pyredevelopment.regression;

import com.pyredevelopment.data.DataFrame;
import com.pyredevelopment.math.matrix.Matrix;

/**
 * Multiple Linear Regression Class
 */
public class MultipleLinearRegression {

    public static void main(String[] args) {

        double[][] matrixY = new double[][]{
                { 411},
                { 881},
                { 920},
                { 693},
                {1136},
                { 717},
                { 724},
                { 766},
                {1199},
                { 824}};

        double[][] matrixX = new double[][] {
                {1,  4,  4, 52},
                {1,  8, 54, 34},
                {1, 16, 34, 75},
                {1, 34, 23, 64},
                {1, 17, 76, 34},
                {1,  6, 46, 24},
                {1,  2, 34, 45},
                {1, 38, 23, 75},
                {1, 19, 75, 45},
                {1, 21, 34, 62}};

        // Perfect data provided, matrixY and matrixX
        // The correct answers are b=3, b1=-1, b2=12, b3=7

        // Save the transpose X matrix
        double[][] xTranspose = Matrix.matrixTranspose(matrixX);

        // Create a results' matrix using the transposition times original
        double[][] results = Matrix.matrixMultiply(xTranspose, matrixX);

        // Inverse the results
        results = Matrix.inverse(results);

        // Multiply those by the xT again
        results = Matrix.matrixMultiply(results, xTranspose);

        // Multiply those by matrixY
        results = Matrix.matrixMultiply(results, matrixY);

        Matrix.round(results); // round to eliminate trailing errors

        print(results);

    }

    public MultipleLinearRegression() {

    }

    private static void print(double[][] matrix){
        for (int row = 0; row < matrix.length; row++) {
            for (int col = 0; col < matrix[row].length; col++) {
                System.out.print(matrix[row][col] + ", ");
            }
            System.out.println();
        }
    }

    public void fit(DataFrame data) {

    }


}
