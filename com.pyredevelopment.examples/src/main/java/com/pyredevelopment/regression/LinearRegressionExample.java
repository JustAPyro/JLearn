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

        // Create a linear regression object and fit the model to data
        LinearRegression lr = new LinearRegression();
        lr.fit(data);

        // Print our linear regression
        System.out.println(lr);

        // Create a GUI and ScatterPlot based on our data
        Window window = new Window();

        // Create a plot and label it with our data
        ScatterPlot plot = new ScatterPlot(data);
        plot.setTitle("Bradford Assay BSA Standard Curve");
        plot.setDependentLabel("Absorbance");
        plot.setIndependentLabel("BSA Concentration");

        // Show plot
        window.show(plot);


    }

}
