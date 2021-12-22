package com.pyredevelopment.chart;

import com.pyredevelopment.data.DataFrame;
import com.pyredevelopment.data.Instance;
import com.pyredevelopment.graphics.Point;
import javafx.geometry.Insets;
import javafx.geometry.VPos;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.text.*;

import java.util.ArrayList;

/**
 * This class allows for easy drawing of a scatter plot onto a Window
 */
public class ScatterPlot implements Chart{

    // This contains a list of the points in the scatter-plots
    ArrayList<Point> scatterPoints = new ArrayList<>();

    // Graphical Information
    private Insets chartPadding = new Insets(0, 0, 0, 0);
    private Insets plotPadding = new Insets (8, 8, 8, 8);

    // Logical/Organization Information
    String title = "Scatter Plot";
    Font titleFont = Font.font("verdana", FontWeight.BOLD, FontPosture.REGULAR, 20);

    String xLabel = "BSA Concentration";
    String yLabel = "Absorbance";
    Font labelFont = Font.font("verdana", FontWeight.NORMAL, FontPosture.REGULAR, 16);

    public ScatterPlot(DataFrame data) {

        // First check that this is data we can scatter plot
        if (data.numFeatures() != 2)
            throw new RuntimeException("Scatter plot requires 2 features");

        // For each data instance
        for (Instance inst : data) {

            // Create a new point associated with the data point
            scatterPoints.add(new Point(inst.feature(0), inst.feature(1), 10));
        }
    }

    /**
     * Allows you to set the title (Primary header) of the Scatter plot
     * @param title The title you would like given to the scatter plot
     */
    public void setTitle(String title) {
        this.title = title;
    }

    /**
     * Draws the scatter-plot onto the provided canvas, using available parameters
     * @param canvas The canvas you want to draw to
     */
    public void draw(Canvas canvas) {
        // Get the graphics Context
        GraphicsContext gc = canvas.getGraphicsContext2D();

        gc.setTextBaseline(VPos.TOP);
        gc.setTextAlign(TextAlignment.CENTER);
        gc.setFont(titleFont);




        gc.fillText(title, canvas.getWidth()/2, 0);

        gc.setFont(labelFont);
        gc.setTextBaseline(VPos.BOTTOM);

        gc.fillText(xLabel, canvas.getWidth()/2, canvas.getHeight());


        gc.save();

        gc.setTextBaseline(VPos.TOP);
        gc.translate(0, canvas.getHeight()/2);
        gc.rotate(-90);
        gc.fillText(yLabel, 0, 0);

        gc.restore();

        Insets adjPadding = new Insets(titleFont.getSize()+plotPadding.getTop(),
                plotPadding.getRight(),
                labelFont.getSize() + plotPadding.getBottom(),
                labelFont.getSize() + plotPadding.getLeft());



        // Stroke the border of the box
        gc.strokeRect(adjPadding.getLeft(), adjPadding.getTop(),                    // Start in top left based on left/top padding
                canvas.getWidth()-adjPadding.getLeft()-adjPadding.getRight(),         // Width is canvas width - padding on left/right
                canvas.getHeight()-adjPadding.getTop()-adjPadding.getBottom());       // Height is canvas height - padding on top/bottom


        // For each point
        for (Point p : scatterPoints) {

            // Get the size
            double size = p.getSize();

            //gc.fillOval(50, 50, 20, 20);
            // Draw the point
            System.out.println(p.getX());
            gc.fillOval(p.getX()*500+size, canvas.getHeight()-(p.getY()*500+size), size, size);
        }
    }


}
