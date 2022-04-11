package com.pyredevelopment;

import com.pyredevelopment.mathresources.Matrix;

public class LinearRegression {

    private int features;   // Number of features in the algorithm
    private int samples;    // Number of samples currently trained on

    private double[] betaValues; // This is the actual results of the regression and can be used for prediction

    // These are so we don't have to repeat matrix multiplication every time we add a sample
    private double[][] matrixXtX;   // Carry the running values of the XtX matrix
    private double[] vectorXtY;     // Carries the running values of XtY matrix

    public LinearRegression(Dataset inputs, Dataset output) {

        // Start by checking to make sure we haven't received garbage
        if (inputs.rows() != output.rows())
            throw new IllegalArgumentException("Missing data samples, input and output sets are not the same length");
        if (output.columns() != 1)
            throw new IllegalArgumentException("Output dataset should be a single column");

        // Save the number of features and samples
        features = inputs.columns();
        samples = inputs.rows();

        inputs.padRight(1);

        // Note that since these can be reused, they are assigned to instance variables
        calculateXtX(inputs);           // Calculate transpose(X)*X
        calculateXtY(inputs, output);   // Calculate transpose(X)*Y

        // Now that we've calculated / loaded both XtX and XtY, we update the betavalues
        calculateBetaValues(); // Calculates inverse(XtX) * XtY and assigns it to betaValues[] variable

    }


    private void calculateBetaValues() {

        // Even though this is a vector convert it to a matrix to do the calculation
        double[][] matrixXtY = new double[][]{vectorXtY};

        // Perform the actual calculation
        double[][] tempBetas = Matrix.multiply(Matrix.inverse(matrixXtX), matrixXtY);

        // Now assign it into the betaValues vector
        for (int i = 0; i < betaValues.length; i++)
            betaValues[i] = tempBetas[i][0];

    }

    private void calculateXtY(Dataset inputs, Dataset outputs) {

        // Initialize the vector variable
        vectorXtY = new double[inputs.columns()];

        // For each row and column
        for (int row = 0; row < inputs.rows(); row++) {
            for (int col = 0; col < inputs.columns(); col++) {
                // Multiply them and add to appropriate indexes
                vectorXtY[col] += inputs.getValue(row, col) * outputs.getValue(row, 0);
            }
        }

    }

    /**
     * T
     * @param inputs
     * @return
     */
    private void calculateXtX(Dataset inputs) {

        // The number of features in the input dataset
        int features = inputs.columns();

        // Create a matrix of doubles that represents the transposition of X multiplied by itself
        matrixXtX = new double[features][features];

        // Iterate through each point in the matrix and calculate the appropriate value
        // TODO: This can be multithreaded at a later point if performance becomes an issue
        for (int m = 0; m < matrixXtX.length; m++) {
            for (int n = 0; n < matrixXtX[m].length; n++){

                matrixXtX[m][n] = 0f;
                for (int i = 0; i < inputs.rows(); i++) {
                    matrixXtX[m][n] += (inputs.getValue(m, i) * inputs.getValue(n, i));
                }

            }
        }

    }


}
