package com.pyredevelopment.regression;

import com.pyredevelopment.data.DataFrame;
import com.pyredevelopment.data.Instance;

/**
 * A Linear Regression Class
 *
 * <h3>Overview:</h3>
 * A Linear regression is a linear model (e.g. a model that assumes a linear relationship
 * between a single input variable (x) and a singly output variable (y)). More specifically, that
 * y can be calculated from a linear combination of the input variables, using an equation of the form:
 * <br><code>Y = &Beta;<sub>0</sub> + &Beta;<sub>1</sub>X</code>
 * where:
 * <ul>
 *     <li><code>y</code> is the output or dependent variable.</li>
 *     <li><code>&Beta;<sub>0</sub></code> is a constant that can be interpreted as the y-intercept.</li>
 *     <li><code>&Beta;<sub>1</sub></code> is a constant that can be interpreted as the slope of the predictor.</li>
 *     <li><code>X</code> is the input or independent variable.</li>
 * </ul>
 * <br>
 * <h3>Usage:</h3>
 * Linear regression can only be applied to a DataObject with one feature/independent variable/input and
 * one target/dependent variable/output. Before using it, make sure your data set matches this format.
 * If you know your DataObject is good, you can simple instantiate a Linear Regression using the usual
 * <code>LinearRegression lr = new LinearRegression();</code> code. This creates the model, but to train it
 * to predict using your data set you must call the <code>lr.fit(dataObject)</code> method afterwards.
 * This trains the model by calculating the line of best fit for the model. Once that is done, you can access the data
 * in a few ways:
 * <ol>
 *     <li>If you only need the line of best fit, you can find it using the <code>lr.toString()</code> method.</li>
 *     <li>If you want to see what it looks like compared to the rest of the data, you can use
 *     the <code>com.pyredevelopment.graphical</code> module to generate a plot of the data
 *     (with the <code>Window</code> class and the <code>ScatterPlot</code> class), then use
 *     <code>Plot.plot(lr)</code> to plot the LinearRegression onto the scatter plot.</li>
 *     <li>If you want to use your model to do predictions, you can use the
 *     <code>lr.predict()</code> method. You can do this on a single independent variable,
 *     or you can use it on a single column vector of multiple inputs to predict them all at once.</li>
 * </ol>
 * Please note that at the current state in the project, there is no way to save a LinearRegression model,
 * so you will have to re-calculate it at run-time for each execution, or save the string representing
 * the equation and then manually perform the calculations after the first fitting.
 * <br>
 * <br>
 * <h3>Technical Information:</h3>
 * This implementation goes through and tracks the sum of each x, y, xy, and x-squared
 * during the fitting. It then uses the Least Squares method to calculate the final values using:
 * <ul>
 *     <li>&Beta;<sub>0</sub> = (&Sigma;y - &Beta;<sub>1</sub>(&Sigma;x)) / n</li>
 *     <li>&Beta;<sub>1</sub> = ( n (&Sigma;xy) - (&Sigma;x)(&Sigma;y)) / ( n (&Sigma;x<sup>2</sup>)-(&Sigma;x)<sup>2</sup>)</li>
 * </ul>
 * It does not save the sum between fittings, so each time <code>.fit()</code> it iterates through the entire
 * provided dataset. Once it has processed each sample it calculates the variables and saves them for the model.
 * <br> For more information on how linear regression works, and how to calculate it, consider checking out
 * some of these resources:
 * <ul>
 *     <li><a href="https://towardsdatascience.com/linear-regression-by-hand-ee7fe5a751bf">Towards Data Science: Linear Regression by Hand.</a></li>
 *     <li><a href="https://www.youtube.com/watch?v=GhrxgbQnEEU">Youtube: How To... Perform Simple LinearRegression by Hand.</a></li>
 *     <li><a href="https://www.statology.org/linear-regression-by-hand/">Statology: How To Perform Linear Regression by Hand.</a></li>
 *     <li><a href="http://www.stat.yale.edu/Courses/1997-98/101/linreg.htm">Yale Stats: Linear Regression</a></li>
 *     <li><a href="https://www.khanacademy.org/math/statistics-probability/describing-relationships-quantitative-data/more-on-regression/v/regression-line-example">Khan Academy: Linear Regression Example</a></li>
 * </ul>
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
