package com.pyredevelopment;

public class LinearRegression {


    public LinearRegression(Dataset inputs, Dataset output) {

        Double[][] matrixXtX = multiplyMatrixLinear(inputs);





    }


    private Double[][] multiplyMatrixLinear(Dataset input) {

        int features = input.getHeaders().size();

        Double[][] matrixXtX = new Double[features][features];

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
