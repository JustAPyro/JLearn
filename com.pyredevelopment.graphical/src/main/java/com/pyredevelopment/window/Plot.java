package com.pyredevelopment.window;

import javafx.geometry.VPos;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import javafx.scene.text.TextAlignment;

public class Plot implements Drawable{

    private enum LineStyle {
        NONE,
        SOLID
    }

    private enum MarkerStyle {
        NONE,
        CIRCLE,
        SQUARE
    }

    // - - - - - - - - - - Instance Variables - - - - - - - - - -

    // Shows the scale of the plot & viewport
    private double xMin, xMax;
    private double yMin, yMax;

    // The label headers for each axis
    private String xLabel;
    private String yLabel;

    // The format values
    Color color;
    LineStyle line;
    MarkerStyle marker;



    // - - - - - - - - - - Constructors - - - - - - - - - -

    int[][] intArray;

    // - - - - - - - - - - Getters / Setters - - - - - - - - - -

    /**
     * Allows you to set the y-axis label
     * @param yLabel The desired label as String
     */
    public void setYLabel(String yLabel) {

        this.yLabel = yLabel;

    }

    // - - - - - - - - - - Other Misc. Methods - - - - - - - - - -

    /**
     * Y-value plot. If you provide a single list this method will be called, which will assume
     * it's a sequence of y values and automatically generate the x values for you.
     * Do note that since this IS 0 indexed, the generated x values will start from 0-n.
     * @param yValues Y values you would like to plot
     */
    public void plot(int[] yValues) {

        // Create a list for the assumed xValues
        int[] xValues = new int[yValues.length];

        // Populate the list with the assumed values
        for (int i = 0; i < yValues.length; i++)
            xValues[i] = i;

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
        intArray = new int[][]{yValues, xValues};

        // Create variables to hold scaling values
        int yMin = Integer.MAX_VALUE;
        int yMax = Integer.MIN_VALUE;
        int xMin = Integer.MAX_VALUE;
        int xMax = Integer.MIN_VALUE;

        for (int i = 0; i < xValues.length; i++) {

            yMin = Math.min(yValues[i], yMin);
            yMax = Math.max(yValues[i], yMax);
            xMin = Math.min(xValues[i], xMin);
            xMax = Math.max(xValues[i], xMax);

        }

        axis(xMin, xMax, yMin, yMax);

    }

    public void show() {

        // Create a new window and have it draw this object
        Window win = new Window(625, 525);  // Creating new Window instance
        win.draw(this);      // Requesting that it call this draw method
    }

    public void draw(Canvas canvas) {

        // reference variables for readability
        final int Y = 0;
        final int X = 1;

        // Get the graphics context associated with the canvas
        GraphicsContext gc = canvas.getGraphicsContext2D();

        int left = 50;
        int top = 50;
        int width = 500;
        int height = 400;

        int tickNums = 7;
        int tickLength = 4;
        double tickSpacingX = width/((double) tickNums-0.5);
        double tickSpacingY = height/((double) tickNums - 0.5);

        double startScaleX = xMin;
        double endScaleX = xMax;

        double startScaleY = yMin;
        double endScaleY = yMax;

        double zeroX = left + tickSpacingX*0.25;
        double zeroY = top + height - tickSpacingX*0.25;

        double xScale = (width - (tickSpacingX/2)) / (endScaleX - startScaleX);
        double yScale = (height - (tickSpacingX/2)) / (endScaleY  - startScaleY) * -1;

        // Stroke the outside box
        gc.strokeRect(left, top, width, height);


        // For each of the number of ticks you would like on this Plot
        for (int i = 0; i < tickNums; i++) {

            // Calculate the increment for this tick
            double tickIncrementX = tickSpacingX*0.25+tickSpacingX*i;
            double tickIncrementY = tickSpacingY*0.25+tickSpacingY*i;


            String xIndicator = String.format("%.1f", (endScaleX / (tickNums-1)) * i);
            String yIndicator = String.format("%.1f", ((endScaleY - startScaleY) / (tickNums-1)) * (tickNums-i-1) + startScaleY);

            // Stroke in the left (Vertical, Y marker) indicators
            gc.setTextAlign(TextAlignment.RIGHT);
            gc.setTextBaseline(VPos.CENTER);

            gc.strokeLine(left, top+tickIncrementY, left-tickLength, top+tickIncrementY);
            gc.strokeText(yIndicator, left-tickLength - 3, top+tickIncrementY);


            // Stroke in the bottom (Horizontal, X Marker) indicators and the text
            gc.setTextAlign(TextAlignment.CENTER);
            gc.setTextBaseline(VPos.TOP);

            gc.strokeLine(left+tickIncrementX, top+height, left+tickIncrementX, top+height+tickLength);
            gc.strokeText(xIndicator, left+tickIncrementX, top+height+tickLength);


        }


        gc.save();
        gc.setTextAlign(TextAlignment.CENTER);
        gc.setTextBaseline(VPos.CENTER);
        gc.translate(left-40, top + (double) height / 2);

        gc.rotate(-90);
        gc.fillText(yLabel, 0, 0);
        gc.restore();

        gc.setLineWidth(3);
        gc.setStroke(Color.BLUE);
        // For each of the x values, graph them
        for (int i = 0; i < intArray[X].length-1; i++) {
            gc.strokeLine(zeroX + (intArray[X][i]-startScaleX)*xScale, zeroY + (intArray[Y][i]-startScaleY)*yScale, zeroX + (intArray[X][i+1]-startScaleX)*xScale, zeroY + (intArray[Y][i+1]-startScaleY)*yScale);
            System.out.println(intArray[Y][i]);
        }


    }


    // - - - - - - - - - - MatPlotLib Methods - - - - - - - - - -

    /**
     * This method allows you to set the y-axis label.
     * It is identical to {@Code setYLabel()} but has been provided as a replica of the
     * MatPlotLib method, so anyone who is more comfortable calling it this way can.
     * @param yLabel
     */
    public void ylabel(String yLabel) {

        setYLabel(yLabel);

    }

    // TODO: Fill
    public void axis(double xmin, double xmax, double ymin, double ymax) {

        xMin = xmin;
        xMax = xmax;
        yMin = ymin;
        yMax = ymax;

    }

    public static double getBestIncrement(double input) {

        double workingNumber = input;
        int counter = 0;

        while (workingNumber < 1) {
            counter++;
            workingNumber *=  10;
        }

        double result = 5*Math.round(workingNumber/5);
        result /= Math.pow(10, counter);

        return result;
    }


    

}
