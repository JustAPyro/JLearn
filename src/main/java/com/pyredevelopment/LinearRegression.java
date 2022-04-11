package com.pyredevelopment;

public class LinearRegression {

    double[][] matrixXtX;

    public LinearRegression(Dataset inputs, Dataset output) {

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

        // Now that we have X.transposed * X, we calculate the inverse




    }


    /**
     * This method will take a dataset and generate a double[][] that represents the
     * @param input
     * @return
     */
    private double[][] multiplyMatrixBySelfTransposed(Dataset input) {

        int features = input.getHeaders().size();

        double[][] matrixXtX = new double[features][features];

        for (int m = 0; m < matrixXtX.length; m++) {
            for (int n = 0; n < matrixXtX[m].length; n++) {

                matrixXtX[m][n] = (double) 0;
                for (int i = 0; i < input.getEntries(); i++) {
                    matrixXtX[m][n] += (input.getColumn(m).get(i) * input.getColumn(n).get(i));
                }


            }
        }

        return matrixXtX;

    }


}
