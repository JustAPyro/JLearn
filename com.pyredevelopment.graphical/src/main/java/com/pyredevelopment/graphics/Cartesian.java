package com.pyredevelopment.graphics;

/**
 * - INTERNAL CLASS -
 * This class is used to display and do math on a cartesian coordinate system.
 */
public class Cartesian {

    // - - - - - - - - - - Instance Variables - - - - - - - - - -

    // Location and size of the cartesian
    int x, y, width, height;

    // Start/end range and scale for each axis
    int xStart, xEnd, xScale;
    int yStart, yEnd, yScale;


    // - - - - - - - - - - Constructors - - - - - - - - - -

    /**
     * Create a cartesian starting at X, Y, with Width and Height
     * @param x The horizontal location of top left corner of the cartesian
     * @param y The vertical location of the top left corner of cartesian
     * @param width Pixel width of the cartesian
     * @param height Pixel height of cartesian
     */
    public Cartesian(int x, int y, int width, int height) {

        // Pass the provided parameters into the function
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;

    }

    // - - - - - - - - - - Setters - - - - - - - - - -


}
