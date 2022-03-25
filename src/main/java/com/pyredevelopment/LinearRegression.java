package com.pyredevelopment;

public class LinearRegression {


    public LinearRegression(Dataset inputs, Dataset output) {

        int features = inputs.getHeaders().size();

        Double[][] matrixXtX = new Double[features][features];




    }

    private Double[] multiplyMatrixLinear(Dataset input) {

        int features = input.getHeaders().size();

        Double[][] matrixXtX = new Double[features][features];

        for (int m = 0; m < matrixXtX.length; m++) {
            for (int n = 0; n < matrixXtX[m].length; n++) {

                for (int i = 0; i < input.getEntries(); i++) {

                }


            }
        }




    }


}
