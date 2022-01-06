package com.pyredevelopment.window;

import com.pyredevelopment.data.DataObject;
import javafx.geometry.VPos;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import javafx.scene.text.TextAlignment;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Plot implements Drawable{

    // reference variables for readability
    final private int Y = 0;
    final private int X = 1;

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
    LineStyle line = LineStyle.NONE;
    MarkerStyle marker = MarkerStyle.NONE;





    // - - - - - - - - - - Constructors - - - - - - - - - -

    int[][] intArray;
    ArrayList<Double> doubleList = new ArrayList<>();

    // - - - - - - - - - - Getters / Setters - - - - - - - - - -

    /**
     * Allows you to set the y-axis label
     * @param yLabel The desired label as String
     */
    public void setYLabel(String yLabel) {

        this.yLabel = yLabel;

    }

    /**
     * This method allows you to set the scale of the plot by providing
     * the minimum and maximum values you would like shown in the viewport.
     * @param xMin Minimum value on the x (horizontal) axis
     * @param xMax Maximum value on the x (horizontal) axis
     * @param yMin Minimum value on the y (vertical) axis
     * @param yMax Maximum value on the y (vertical) axis
     */
    public void setAxis(double xMin, double xMax, double yMin, double yMax) {

        // Set the provided parameters
        this.xMin = xMin;
        this.xMax = xMax;
        this.yMin = yMin;
        this.yMax = yMax;
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

        // And finally, parse the format string to adjust those parameters
        parseFormat(format);

    }

    public void plot(DataObject data, String xLabel, String yLabel, String format) {

        List<Object> xObjects = data.getFeature(xLabel);
        List<Object> yObjects = data.getFeature(yLabel);


        int[] xArray = new int[xObjects.size()];
        int[] yArray = new int[yObjects.size()];

        for (int i = 0; i < xObjects.size(); i++) {
            xArray[i] = Math.round(Float.parseFloat((String) xObjects.get(i)));
            yArray[i] = Math.round(Float.parseFloat((String) yObjects.get(i)));
        }

        plot(xArray, yArray, format);


    }

    public void show() {

        // Create a new window and have it draw this object
        Window win = new Window(625, 525);  // Creating new Window instance
        win.draw(this);      // Requesting that it call this draw method
    }

    public void draw(Canvas canvas) {



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


            String xIndicator = String.format("%.1f", ((endScaleX - startScaleX) / (tickNums-1)) * i + startScaleX);
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
        gc.setStroke(color);

        // Create an array to save all the points
        double[][] pointLocations = new double[intArray[X].length][2];

        // For each point in the data array, calculate pixel location and insert in new array
        for (int i = 0; i < intArray[X].length; i++) {
            pointLocations[i][X] = zeroX + (intArray[X][i]-startScaleX)*xScale;
            pointLocations[i][Y] = zeroY + (intArray[Y][i]-startScaleY)*yScale;
        }

        // For each of the x values, graph them
        for (int i = 0; i < intArray[X].length-1; i++) {
            if (line != LineStyle.NONE)
                gc.strokeLine(zeroX + (intArray[X][i]-startScaleX)*xScale, zeroY + (intArray[Y][i]-startScaleY)*yScale, zeroX + (intArray[X][i+1]-startScaleX)*xScale, zeroY + (intArray[Y][i+1]-startScaleY)*yScale);

        }

        // If we're drawing the marker
        gc.setFill(color);
        if (marker == MarkerStyle.CIRCLE)
            drawCircles(gc, pointLocations);
        else if (marker == MarkerStyle.SQUARE)
            drawSquares(gc, pointLocations);


    }

    private void drawSquares(GraphicsContext gc, double[][] pointLocations) {

        // Declare the size of the squares
        double size = 8;

        // For each of the points
        for (double[] point : pointLocations)
            // Draw the point at provided location
            gc.fillRect(point[X]-size/2, point[Y]-size/2, size, size);
    }

    private void drawCircles(GraphicsContext gc, double[][] pointLocations) {

        // Declare the size of the circles
        double size = 8;

        // For each of the points
        for (double[] point : pointLocations)
            // Draw the point at provided location
            gc.fillOval(point[X]-size/2, point[Y]-size/2, size, size);

    }

    // - - - - - - - - - - MatPlotLib Methods - - - - - - - - - -

    /**
     * This method allows you to set the y-axis label.
     * It is identical to <code>.setYLabel()</code> but has been provided as a replica of the
     * MatPlotLib method, so anyone who is more comfortable calling it this way can.
     * @param yLabel The label title you want given to the y-axis
     */
    public void ylabel(String yLabel) {

        setYLabel(yLabel);

    }

    /**
     * This is a wrapper method that is identical to the <code>.setAxis()</code> method.
     * It was provided to allow the library to feel a little more comfortable and usable
     * for anyone more familiar with MatPlotLibs API. This method can be called identically
     * to that (Except that this method does not require a list to wrap the values).
     * @param xMin Minimum value on the x (horizontal) axis
     * @param xMax Maximum value on the x (horizontal) axis
     * @param yMin Minimum value on the y (vertical) axis
     * @param yMax Maximum value on the y (vertical) axis
     */
    public void axis(double xMin, double xMax, double yMin, double yMax) {
        setAxis(xMin, xMax, yMin, yMax);
    }

    private void parseFormat(String string) {

        // Hashmap representing all line styles
        HashMap<Character, LineStyle> lines = new HashMap<>();
        lines.put('-', LineStyle.SOLID);

        // Hashmap representing possible marker styles
        HashMap<Character, MarkerStyle> markers = new HashMap<>();
        markers.put('s', MarkerStyle.SQUARE);
        markers.put('o', MarkerStyle.CIRCLE);

        // HashMap representing possible color styles
        HashMap<Character, Color> colors = new HashMap<>();
        colors.put('b', Color.BLUE);
        colors.put('g', Color.GREEN);
        colors.put('r', Color.RED);
        colors.put('c', Color.CYAN);
        colors.put('m', Color.MAGENTA);
        colors.put('y', Color.YELLOW);
        colors.put('k', Color.BLACK);
        colors.put('w', Color.WHITE);

        // For each character, see which map it's in and then assign the correct value
        for (char c : string.toCharArray()) {
            if (lines.containsKey(c))
                this.line = lines.get(c);
            if (markers.containsKey(c))
                this.marker = markers.get(c);
            if (colors.containsKey(c))
                this.color = colors.get(c);

        }

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
