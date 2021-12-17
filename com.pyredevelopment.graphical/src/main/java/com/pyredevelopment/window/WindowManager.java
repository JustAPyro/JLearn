package com.pyredevelopment.window;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import java.util.HashMap;

/**
 * This is a primarily static class that handles all the back end and JavaFX Thread for the Window object wrappers.
 * <b><u>YOU ARE RECOMMENDED AGAINST USING THIS CLASS DIRECTLY</u></b>
 */
public class WindowManager extends Application{

    // This is a volatile flag indicating if the JFX toolkit is instantiated
    private static volatile boolean javaFxActive = false;

    // This allows Window objects to pass themselves in to indicate which stage they want to modify
    private static final HashMap<Window, Stage> windows = new HashMap<>();

    /**
     * This has to be called before any other WindowManager functions can be called,
     * it will initialize the JavaFX Thread and prepare the class to manage Stage-Window interactions.
     * NOTE: If for some reason this fails, following methods may DeadLock trying to access specific Stages
     */
    public static void initialize()
    {
        // Create a new thread and launch the JFX application on it
        Thread t = new Thread(Application::launch);

        // Start the thread
        t.start();
    }

    /**
     * This allows the Window wrapper class to create a new Stage
     * @param window The Window wrapper that will control this stage
     */
    public static void newWindow(Window window) {

        // Wait for the volatile flag to be true
        while (!javaFxActive)
            Thread.onSpinWait();

        // Run this on the JavaFX Thread whenever possible
        Platform.runLater(() -> {
            Stage stage = new Stage();      // Create a new stage

            VBox root = new VBox();
            Canvas canvas = new Canvas(500, 500);
            canvas.getGraphicsContext2D().fillRect(50, 50, 50, 50);
            root.getChildren().add(canvas);

            stage.setScene(new Scene(root));    // Set the root into the scene
            windows.put(window, stage);         // Insert it into the window index hashmap
            stage.show();                       // Set it to display
        });

    }

    /**
     * Allows a Window wrapper to set the title of a window
     * @param window The window object handler
     * @param title The title they want the window set to
     */
    public static void setTitle(Window window, String title) {
        getStage(window).setTitle(title);
    }

    /**
     * Allows a window wrapper to set the size of a window
     * @param window The window who's size you want to chage
     * @param width The desired width
     * @param height The desired height
     */
    public static void setSize(Window window, int width, int height) {
        Stage s = getStage(window); // Get the stage we're working on
        s.setWidth(width);          // Set the width of it
        s.setHeight(height);        // Set the height of it
    }

    // - - - - - - - - - - Private Methods - - - - - - - - - -

    /**
     * This allows class methods to get the stage they should be modifying but ensures that initialization has completed
     * first.
     * @param window The window associated with the stage you want to access
     * @return The stage that is managed by {@param window}
     */
    private static Stage getStage(Window window) {

        // While there isn't a window in the hashmap, let the thread wait
        //noinspection LoopConditionNotUpdatedInsideLoop
        while (!windows.containsKey(window)) {
            Thread.onSpinWait();
        }


        // Once we know that the hashmap contains the key, pass the window to retrieve the stage and return it
        return windows.get(window);
    }

    // - - - - - - - - - - Overridden Methods - - - - - - - - - -

    /**
     * Entry point for the JavaFX Thread. This tells the JavaFX to remain open and waiting to handle any other functions
     * @param stage The stage provided by default
     */
    @Override
    public void start(Stage stage) {
        // Set the javaFX thread/toolkit to remain open ad set our flag to true
        Platform.setImplicitExit(false);
        javaFxActive = true;
    }

}
