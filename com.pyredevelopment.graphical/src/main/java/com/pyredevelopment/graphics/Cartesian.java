package com.pyredevelopment.graphics;

import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;

/**
 * - INTERNAL CLASS -
 * This class is used to display and do math on a cartesian coordinate system.
 */
public class Cartesian {

    // - - - - - - - - - - Instance Variables - - - - - - - - - -

    // Location and size of the cartesian
    double x, y, width, height;

    // Start/end range and scale for each axis
    double xStart, xEnd, xScale;
    double yStart, yEnd, yScale;


    // - - - - - - - - - - Constructors - - - - - - - - - -

    /**
     * Create a cartesian starting at X, Y, with Width and Height
     * @param x The horizontal location of top left corner of the cartesian
     * @param y The vertical location of the top left corner of cartesian
     * @param width Pixel width of the cartesian
     * @param height Pixel height of cartesian
     */
    public Cartesian(double x, double y, double width, double height) {

        // Pass the provided parameters into the function
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;

    }

    // - - - - - - - - - - Setters - - - - - - - - - -

    /**
     * Allows you to set the x parameters of the cartesian graph
     * @param xStart The starting value of the coordinate system
     * @param xEnd The end value of the coordinate system
     * @param xScale The number of lines (And therefor scale/increment) of coordinate system
     */
    public void setXParameters(double xStart, double xEnd, double xScale) {

        // Pass the provided variables to instance
        this.xStart = xStart;
        this.xEnd = xEnd;
        this.xScale = xScale;

    }

    /**
     * Allows you to set the y parameters of the cartesian graph
     * @param yStart The starting value of the coordinate system
     * @param yEnd The end value of the coordinate system
     * @param yScale The number of lines (And therefor scale/increment) of coordinate system
     */
    public void setYParameters(double yStart, double yEnd, double yScale) {

        // Pass the provided variables to instance
        this.yStart = yStart;
        this.yEnd = yEnd;
        this.yScale = yScale;

    }

    // - - - - - - - - - - Other Methods / Functions - - - - - - - - - -

    public double translateX(Point p) {
        return (width / (xEnd - xStart)) * p.getX() + x - p.getSize()/2;
    }

    public double translateY(Point p) {
        return height - ((height / (yEnd - yStart)) * p.getY() - p.getSize()/2);
    }

    /**
     * Draws the cartesian onto the provided canvas using instance variables
     * @param canvas The canvas you want to draw to
     */
    public void draw(Canvas canvas) {

        GraphicsContext gc = canvas.getGraphicsContext2D();

        // The space between coordinate lines
        double scaleWidth = width/xScale;
        double scaleHeight = height/yScale;

        // Stroke the vertical (x) guides
        for (int i = 1; i < xScale; i++) {
            gc.strokeLine(x+scaleWidth*i, y, x+scaleWidth*i, y+height);
        }

        // Stroke the horizontal (y) guides
        for (int i = 1; i < yScale; i++) {
            gc.strokeLine(x, y+scaleHeight*i, x+width, y+scaleHeight*i);
        }

        gc.strokeRect(x, y, width, height);

    }

}
