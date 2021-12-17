package com.pyredevelopment.regression;

import com.pyredevelopment.chart.ScatterPlot;
import com.pyredevelopment.window.Window;
import com.pyredevelopment.data.DataFrame;


/**
 * This class gives an example of simple Linear Regression using JLearn
 */
public class LinearRegressionExample {

    public static void main(String[] args) {

        // Load the data
        DataFrame data = DataFrame.readCSV("C:/Users/Luke/Programming/DataSets/Daniel/Absorbance_BSA");

        // Create a linear regression object
        LinearRegression lr = new LinearRegression();

        // fit the model to our data
        lr.fit(data);

        // Print our linear regression
        System.out.println(lr);

        // Create a GUI!
        Window window = new Window();

        // Create a plot to see your data visually
        ScatterPlot plot = new ScatterPlot(data);

        window.show(plot);


    }

}
