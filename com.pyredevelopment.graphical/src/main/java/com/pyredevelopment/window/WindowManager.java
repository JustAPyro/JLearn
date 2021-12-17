package com.pyredevelopment.window;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.stage.Stage;

import java.util.ArrayList;
import java.util.HashMap;

import static java.lang.Thread.sleep;

public class WindowManager extends Application{

    // This is a volatile flag indicating if the JFX toolkit is instantiated
    private static volatile boolean javaFxActive = false;

    static HashMap<Window, Stage> windows = new HashMap<>();

    public static void newWindow(Window window) {

        // Wait for the volatile flag to be true
        while (!javaFxActive)
            Thread.onSpinWait();

        // Create a new frame
        Platform.runLater(() -> {

            Stage stage = new Stage();

            windows.put(window, stage);


            stage.show();
        });

    }

    public static void setTitle(Window window, String title) {
        getStage(window).setTitle(title);
    }

    private static Stage getStage(Window window) {
        while (!windows.containsKey(window))
            Thread.onSpinWait();

        return windows.get(window);
    }



    public static void initialize()
    {
        // Create a new thread and launch the JFX application on it
        Thread t = new Thread(Application::launch);

        // Start the thread
        t.start();
    }

    @Override
    public void start(Stage stage) {
        // Set the javaFX thread/toolkit to remain open ad set our flag to true
        Platform.setImplicitExit(false);
        javaFxActive = true;
    }

}
