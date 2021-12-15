package com.pyredevelopment.regression;

import com.pyredevelopment.data.DataFrame;
import com.pyredevelopment.data.Instance;

/**
 * This class handles linear regressions
 * TODO: Add extra info here
 */
public class LinearRegression {

    // y = a + bx
    private double a;   // y-intercept
    private double b;   // slope of line

    /**
     * Allows you to fit this linear regression model to a dataframe provided
     * @param data The data you want to fit/train the model on
     */
    public void fit(DataFrame data) {

        // Quick error handling, make sure we have two features
        if (data.numFeatures() != 2)
            throw new RuntimeException("REQUIRE 2 FEATURES");

        // Get the number of rows/instances in the data
        int n = data.numInstances();

        // This is where we will store the sums as we perform the regression
        double xSUM = 0;
        double ySUM = 0;
        double xySUM = 0;
        double xSquaredSUM = 0;

        // For each instance of data
        for (Instance instance : data) {

            // Add the features and calculations to appropriate locations
            xSUM += instance.feature(0);
            ySUM += instance.feature(1);
            xySUM += instance.feature(0) * instance.feature(1);
            xSquaredSUM += Math.pow(instance.feature(0), 2);

        }

        // Calculate the value of a/b using the linear regression equation
        double divisor = n * xSquaredSUM - Math.pow(xSUM, 2);
        a = (ySUM*xSquaredSUM-xSUM*xySUM)/ divisor;
        b = (n*xySUM-xSUM*ySUM)/ divisor;

    }

    /**
     *
     * @return A string representation of the object
     */
    @Override
    public String toString() {
        return "y = " + b + "x + " + a;
    }



}
