package com.pyredevelopment.window;

/**
 * This class provides an easy wrapper/API class to easily create windows
 */
public class Window {

    // This flag indicates if the WindowManager has been initialized
    private static boolean wmReady = false;

    /**
     * This constructor allows library users to easily create JavaFX GUI's without worrying about the underlying
     * abstraction or implementation. Note: If for some reason the WindowManager fails to initialize, following methods
     * can get DeadLocked. TODO: Set timeout function for that issue
     */
    public Window() {

        // If it's not ready
        if (!wmReady) {
            // Initialize WindowManager and set wmReady to true
            WindowManager.initialize();
            wmReady = true;
        }

        // Then create a new window, passing in this object as a key
        WindowManager.newWindow(this);

    }

    /**
     * Allows you to set the title of the window object
     * @param title The title you want the window set to
     */
    public void setTitle(String title) {
        // Use the window manager to set the title, passing in this window and the desired title
        WindowManager.setTitle(this, title);
    }


}
