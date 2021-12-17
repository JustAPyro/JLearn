package com.pyredevelopment.window;

import javafx.stage.Stage;

import static java.lang.Thread.sleep;
import static javafx.application.Application.launch;

/**
 * This class provides an easy wrapper/API class to easily create windows
 */
public class Window {

    // This flag indicates if the WindowManager has been initialized
    private static boolean wmReady = false;

    public Window() {

        // If it's not ready
        if (!wmReady) {
            // Initialize WindowManager and set wmReady to true
            WindowManager.initialize();
            wmReady = true;
        }


        WindowManager.newWindow(this);

    }

    public void setTitle(String title) {
        WindowManager.setTitle(this, title);
    }





}
