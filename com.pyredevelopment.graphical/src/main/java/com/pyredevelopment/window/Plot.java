package com.pyredevelopment.window;

import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;

import java.util.ArrayList;
import java.util.List;

public class Plot implements Drawable{

    // - - - - - - - - - - Instance Variables - - - - - - - - - -

    // reference variables for readability
    private final int Y = 0;
    private final int X = 1;

    // - - - - - - - - - - Constructors - - - - - - - - - -

    int[][] intArray;

    // - - - - - - - - - - Getters / Setters - - - - - - - - - -

    public void setYLabel(String yLabel) {

    }

    // - - - - - - - - - - Other Misc. Methods - - - - - - - - - -

    /**
     * Y-value plot. If you provide a single list this method will be called, which will assume
     * it's a sequence of y values and automatically generate the x values for you.
     * Do note that since this IS 0 indexed, the generated x values will start from 0-n.
     * @param yValues Y values you would like to plot
     */
    public void plot(int[] yValues) {

        // Create a list of the assumed xValues
        int[] xValues = new int[yValues.length];

        // Call the parent plot function
        plot(xValues, yValues);

    }

    /**
     * Standard x/y value plot, with both provided. This will plot it using the format string b-
     * which is a solid blue line through them.
     * @param xValues The x/independent values of your data set.
     * @param yValues The y/dependent values of your data set.
     */
    public void plot(int[] xValues, int[] yValues) {

        // Call the super method
        plot(xValues, yValues, "b-");

    }

    public void plot(int[] xValues, int[] yValues, String format) {

        // Initialize the int array and assign the values
        intArray = new int[][]{xValues, yValues};

    }

    public void show() {

        // Create a new window and have it draw this object
        Window win = new Window();  // Creating new Window instance
        win.draw(this);      // Requesting that it call this draw method
    }

    public void draw(Canvas canvas) {

        // Get the graphics context associated with the canvas
        GraphicsContext gc = canvas.getGraphicsContext2D();

        int left = 25;
        int top = 25;
        int width = 250;
        int height = 250;

        int tickNums = 7;
        int tickLength = 4;
        double tickSpacing = width/((double) tickNums-0.5);

        // Stroke the outside box
        gc.strokeRect(left, top, width, height);

        // For each of the number of ticks you would like on this Plot
        for (int i = 0; i < tickNums; i++) {

            // Calculate the increment for this tick
            double tickIncrement = tickSpacing*0.25+tickSpacing*i;

            // Stroke in the left (Vertical, Y marker) indicators
            gc.strokeLine(left, top+tickIncrement, left-tickLength, top+tickIncrement);

            // Stroke in the bottom (Horizontal, X Marker) indicators
            gc.strokeLine(left+tickIncrement, top+height, left+tickIncrement, top+height+tickLength);

        }




    }


    // - - - - - - - - - - MatPlotLib Methods - - - - - - - - - -

    public void ylabel(String yLabel) {
    }

}
