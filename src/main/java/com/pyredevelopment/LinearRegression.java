package com.pyredevelopment;

import com.pyredevelopment.mathresources.Matrix;

import java.util.Arrays;

public class LinearRegression {

    private int features;   // Number of features in the algorithm
    private int samples;    // Number of samples currently trained on

    private double[] betaValues; // This is the actual results of the regression and can be used for prediction

    // These are so we don't have to repeat matrix multiplication every time we add a sample
    private double[][] matrixXtX;   // Carry the running values of the XtX matrix
    private double[] vectorXtY;     // Carries the running values of XtY matrix

    public static void main(String[] args) {

        // Load data set
        Dataset xTrain = Dataset.readCSV("src/main/resources/inputs.csv");
        Dataset yTrain = Dataset.readCSV("src/main/resources/out.csv");


        System.out.println(xTrain.rows());

        // Train model
        LinearRegression model = new LinearRegression(xTrain, yTrain);



    }

    public LinearRegression(Dataset inputs, Dataset output) {

        // Start by checking to make sure we haven't received garbage
        if (inputs.rows() != output.rows())
            throw new IllegalArgumentException("Missing data samples, input and output sets are not the same length");
        if (output.columns() != 1)
            throw new IllegalArgumentException("Output dataset should be a single column");

        // Save the number of features and samples
        features = inputs.columns();
        samples = inputs.rows();

        inputs.padLeft(1);

        // Note that since these can be reused, they are assigned to instance variables
        calculateXtX(inputs);           // Calculate transpose(X)*X

        System.out.println("Matrix XtX");
        for (int i = 0; i < matrixXtX.length; i++) {
            System.out.println(Arrays.toString(matrixXtX[i]));
        }

        calculateXtY(inputs, output);   // Calculate transpose(X)*Y

        System.out.println("Vector XtY");
        for (int i = 0; i < vectorXtY.length; i++) {
            System.out.println(vectorXtY[i]);
        }

        // Now that we've calculated / loaded both XtX and XtY, we update the betavalues
        calculateBetaValues(); // Calculates inverse(XtX) * XtY and assigns it to betaValues[] variable


        System.out.println(Arrays.toString(betaValues));

    }

    public double predict(double[] inputs) {

        double prediction = 0;

        return 1;

    };

    public Dataset predict(Dataset values) {

        // Create a vector to hold predictions
        double[] predictions = new double[values.rows()];

        for (int i = 0; i < values.rows(); i++) {
            //predictions[i] = predict(values.getRow(i));
        }
        return null;

    }


    // Both XtX and XtY are now confirmed, so there must be an error here in the beta calculations
    private void calculateBetaValues() {

        // Initialize the list
        betaValues = new double[features+1];

        // Even though this is a vector convert it to a matrix to do the calculation
        double[][] matrixXtY = new double[vectorXtY.length][1];
        for (int i = 0; i < vectorXtY.length; i++)
            matrixXtY[i][0] = vectorXtY[i];

        // Perform the actual calculation
        System.out.println("XtX: " + matrixXtX.length + ", " + matrixXtX[0].length);
        System.out.println("XtY: " + matrixXtY.length + ", " + matrixXtY[0].length);

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


        System.out.println(inputs.rows());
        for (int i = 0; i < inputs.rows(); i++) {

            for (int row = 0; row < matrixXtX.length; row++) {
                for (int col = 0; col < matrixXtX[row].length; col++) {
                    matrixXtX[row][col] += (inputs.getValue(i, col) * inputs.getValue(i, row));

                }
            }

        }

    }




}
