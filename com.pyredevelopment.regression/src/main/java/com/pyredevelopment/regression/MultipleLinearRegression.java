package com.pyredevelopment.regression;

import com.pyredevelopment.math.matrix.Matrix;

/**
 * A Multiple Linear Regression Class<br>
 * <h3>Overview:</h3>
 * This class provides a simple way of performing multiple linear regression method. This is done using the
 * Least Squares Estimate and matrix mathematics (Implemented in the JLearn math package). This can allow you to
 * perform machine learning on linear data with multiple independent variables that fit into the formula<br>
 * <code>Y = &Beta;<sub>0</sub> + &Beta;<sub>1</sub>X<sub>1</sub> + &Beta;<sub>2</sub>X<sub>2</sub> + ... + &Beta;<sub>n-1</sub>X<sub>n-1</sub> + &Epsilon;</code><br>
 * where: <br>
 * <ul>
 * <li><code>&Beta;<sub>i</sub></code> is a constant coefficient calculated by the regression.</li>
 * <li><code>X<sub>i</sub></code> is the associated independent variable.</li>
 * <li><code>&Epsilon;</code> is the standard error.</li>
 * </ul>
 * <br>
 *
 * <h3>Usage:</h3>
 * Usage is relatively simple. To apply a multiple linear regression you need an existing data set
 * that contains 2 or more independent variables (inputs) and a single dependent variable (output).
 * Instantiate a regression object using <code>MultipleLinearRegression reg = new MultipleLinearRegression()</code>
 * and then pass in a DataObject using <code>reg.fit(dataObject)</code>. Once the model is trained you
 * can make predictions using the <code>reg.predict(value, value)</code> function. For general information
 * about how to structure machine learning or multiple linear regression, refer to #GENERALRESOURCES#.
 * To see an example usage, consider checking out the <code>com.pyredevelopment.examples</code> module.
 * <br><br>
 *
 * <h3>Technical Information: </h3>
 * This class implements the Least Squares Estimate via matrix math, as noted in the general information.
 * For more information on Multiple Linear Regression and the implementation of matrix formulation used here,
 * consider taking a look at these resources(arranged roughly in order of complexity)<br>
 * <ul>
 * <li><a href="https://www.youtube.com/watch?v=qdOG7YMolmA">Day 4: Multiple Linear Regression with Matrices (12 min youtube video)</a></li>
 * <li><a href="https://online.stat.psu.edu/stat462/node/132/">PennState STAT 462: 5.4 A Matrix Formulation of the Multiple Regression Model</a></li>
 * <li><a href="https://www.stat.purdue.edu/~boli/stat512/lectures/topic3.pdf">Purdue Stat 512: Applied Linear Models</a></li>
 * </ul>
 *
 * <br> For more information on how the matrix calculations are performed and the actual back-end implementation of this
 * class, you should refer mostly to the <code>com.pyredevelopment.math.Matrix</code> class as well as the associated docs.
 */
public class MultipleLinearRegression {

    /*

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

    /**
     * Fits the data to the provided data sets. In this method
     * you must provide two DataObjects, the first being 2+ col x n rows of independent
     * (input values), the second being 1 col x n rows of dependent variables (output values).
     * @param independent A 2+xN DataObject of independent(input) data points.
     * @param dependent A single column DataObject of dependent(output) data points.

    public void fit(DataObject independent, DataObject dependent) {

    }

    private static void print(double[][] matrix){
        for (int row = 0; row < matrix.length; row++) {
            for (int col = 0; col < matrix[row].length; col++) {
                System.out.print(matrix[row][col] + ", ");
            }
            System.out.println();
        }
    }
*/
}
