package com.pyredevelopment.chart;

import com.pyredevelopment.data.DataFrame;
import com.pyredevelopment.data.Instance;
import com.pyredevelopment.graphics.Point;

import java.util.ArrayList;

/**
 * This class allows for easy drawing of a scatter plot onto a Window
 */
public class ScatterPlot {

    // This contains a list of the points in the scatterplots
    ArrayList<Point> scatterPoints = new ArrayList<>();

    public ScatterPlot(DataFrame data) {

        // First check that this is data we can scatter plot
        if (data.numFeatures() != 2)
            throw new RuntimeException("Scatter plot requires 2 features");

        // For each data instance
        for (Instance inst : data) {

            // Create a new point associated with the data point
            scatterPoints.add(new Point(inst.feature(0), inst.feature(1), 5));
        }
    }

}
