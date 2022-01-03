package com.pyredevelopment.regression;

import com.pyredevelopment.chart.ScatterPlot;
import com.pyredevelopment.window.Plot;
import com.pyredevelopment.window.Window;
import com.pyredevelopment.data.DataFrame;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;


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
