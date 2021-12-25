package com.pyredevelopment.regression;

import com.pyredevelopment.chart.ScatterPlot;
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
        DataFrame data = DataFrame.readCSV("C:/Users/Luke/Programming/DataSets/Kaggle/carsales_km_price.csv");

        // Create a linear regression object and fit the model to data
        LinearRegression lr = new LinearRegression();
        lr.fit(data);

        // Print our linear regression
        System.out.println(lr);

        // Create a GUI and ScatterPlot based on our data
        Window window = new Window();
        ScatterPlot plot = new ScatterPlot(data);

        // Label our plot
        plot.setTitle("Bradford Assay BSA Standard Curve");
        plot.setDependentLabel("Absorbance");
        plot.setIndependentLabel("BSA Concentration");

        // Show plot
        window.show(plot);

    }

}
