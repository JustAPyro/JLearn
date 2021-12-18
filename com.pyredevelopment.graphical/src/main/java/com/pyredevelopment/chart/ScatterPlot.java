package com.pyredevelopment.chart;

import com.pyredevelopment.data.DataFrame;
import com.pyredevelopment.data.Instance;
import com.pyredevelopment.graphics.Point;
import javafx.geometry.Insets;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;

import java.util.ArrayList;

/**
 * This class allows for easy drawing of a scatter plot onto a Window
 */
public class ScatterPlot implements Chart{

    // This contains a list of the points in the scatter-plots
    ArrayList<Point> scatterPoints = new ArrayList<>();

    // Graphical Information
    private Insets chartPadding = new Insets(20, 10, 10, 10);

    // Logical/Organization Information
    String title = "Scatter Plot";

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

    public void setTitle(String title) {
        this.title = title;
    }

    public void draw(Canvas canvas) {
        // Get the graphics Context
        GraphicsContext gc = canvas.getGraphicsContext2D();

        int $this = 0;
        int _isweird;

        gc.setTextAlign(TextAlignment.CENTER);
        Text titleText = new Text(title);
        titleText.getLayoutBounds().getHeight(); // Get the height of the text
        gc.strokeText(title, canvas.getWidth()/2, 10);

        // Stroke the border of the box
        gc.strokeRect(chartPadding.getLeft(), chartPadding.getTop(),                    // Start in top left based on left/top padding
                canvas.getWidth()- chartPadding.getLeft()- chartPadding.getRight(),         // Width is canvas width - padding on left/right
                canvas.getHeight()- chartPadding.getTop()- chartPadding.getBottom());       // Height is canvas height - padding on top/bottom





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
