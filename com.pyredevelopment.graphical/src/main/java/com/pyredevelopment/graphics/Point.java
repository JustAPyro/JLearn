package com.pyredevelopment.graphics;

public class Point {

    // Location Variables
    double x;
    double y;

    // Size and color variables
    double size;
    double[] color;

    public Point(double x, double y, int size) {
        this.x = x;
        this.y = y;
        this.size = size;
        this.color = new double[]{1, 0, 0};
    }
}
