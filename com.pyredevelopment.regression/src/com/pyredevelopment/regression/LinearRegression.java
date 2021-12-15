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

    private double[] x;
    private double[] y;
    private double[] xy;
    private double[] xSquared;
    private double[] ySquared;

    private final int SUM = 0;


    public void fit(DataFrame data) {

        // Quick error handling, make sure we have two features
        if (data.numFeatures() != 2)
            throw new RuntimeException("REQUIRE 2 FEATURES");

        // Get the number of rows/instances in the data
        int n = data.numInstances();



        // Create lists that are just as long to hold the data (Plus one row for sums)
        x = new double[n+1];
        y = new double[n+1];
        xy = new double[n+1];
        xSquared = new double[n+1];
        ySquared = new double[n+1];

        int index = 1;
        for (Instance instance : data) {

            x[index] = instance.feature(0);
            x[SUM] += instance.feature(0);

            y[index] = instance.feature(1);
            y[SUM] += instance.feature(1);

            xy[index] = instance.feature(0) * instance.feature(1);
            xy[SUM] += instance.feature(0) * instance.feature(1);

            xSquared[index] = Math.pow(instance.feature(0), 2);
            xSquared[SUM] += Math.pow(instance.feature(0), 2);

            ySquared[index] = Math.pow(instance.feature(1), 2);
            ySquared[SUM] += Math.pow(instance.feature(1), 2);

            index++;
        }

        // Calculate the value of a/b using the linear regression equation
        a = (y[SUM]*xSquared[SUM]-x[SUM]*xy[SUM])/(n*xSquared[SUM]-Math.pow(x[SUM], 2));
        b = (n*xy[SUM]-x[SUM]*y[SUM])/(n*xSquared[SUM]-Math.pow(x[SUM], 2));

        System.out.println("Linear Regression is: y = " + b + "x + " + a);


    }

}
