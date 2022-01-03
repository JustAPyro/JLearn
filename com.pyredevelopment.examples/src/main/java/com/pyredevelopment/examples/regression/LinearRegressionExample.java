package com.pyredevelopment.examples.regression;

import com.pyredevelopment.regression.LinearRegression;
import com.pyredevelopment.window.Plot;
import com.pyredevelopment.data.DataFrame;



/**
 * This class gives an example of simple Linear Regression using JLearn
 */
public class LinearRegressionExample {

    public static void main(String[] args) {

        // Load the data
        DataFrame data = DataFrame.readCSV("C:/Users/Luke/Programming/DataSets/Daniel/Absorbance_BSA.csv");

        // Create a linear regression object and fit the model to data
        LinearRegression lr = new LinearRegression();
        lr.fit(data);

        // Print our linear regression
        System.out.println(lr);

        Plot plt = new Plot();


    }

}
