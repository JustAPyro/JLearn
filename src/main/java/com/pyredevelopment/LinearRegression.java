package com.pyredevelopment;

public class LinearRegression {


    public LinearRegression(Dataset inputs, Dataset output) {

        Double[][] matrixXtX = multiplyMatrixLinear(inputs);





    }

    private Double calcDeterminant() {
    /*
    1 2 3 4
    4 5 6 5
    7 8 9 6
    7 8 9 6
    */
        Double[][] input = new Double[][]{};
        determinant(input, 0, -1, 0, 3);

    }

    private Double determinant(Double[][] input, int row, int col, int startCol, int endCol) {

        // TODO: Base case

        int determinant = 0;    // We store the running value of this determinant here
        boolean add = true;     // This gets flipped and determines if the next value is added or subtracted
        for (int i = startCol; i < endCol; i++) {

            // If this is the ignore column continue to the next index
            if (i == col)
                continue;

            // Otherwise, iterate through each and multi
            determinant += input[row][col] * (determinant() * (add? 1 : -1));
            add = !add;


        }




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
