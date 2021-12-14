package com.pyredevelopment.regression;

import com.pyredevelopment.data.DataFrame;

import java.io.File;

/**
 * This class gives an example of simple Linear Regression using JLearn
 */
public class LinearRegressionExample {

    public static void main(String[] args) {

        // First we load the data into a dataframe using CSV
        DataFrame data = DataFrame.readCSV("com.pyredevelopment.examples/src/resources/CarDetails.csv");
        data.printHeaders();

    }

}
