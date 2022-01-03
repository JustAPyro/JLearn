package com.pyredevelopment.window;

import javafx.scene.canvas.Canvas;

/**
 * This interface declares that an object can be drawn on a JFX canvas. The calling party
 * can use call the draw() method with the canvas they want drawn on.
 */
public interface Drawable {

    // The abstracted details of what will be drawn, requiring the canvas to draw to
    public void draw(Canvas canvas);

}
