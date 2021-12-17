package com.pyredevelopment.window;

import com.pyredevelopment.chart.Chart;

/**
 * This class provides an easy wrapper/API class to easily create windows.<br>
 * <h3>Overview:</h3>
 * There are multiple constructors to allow you to create a window as easily as possible, for constructors that do not
 * specify certain parameters (i.e. size) the following will be used as defaults.<br><br>
 *
 * <u>Default Parameters for Window Class</u><br>
 * Title: "JLearn"<br>
 * Size: 500 x 500<br>
 *
 * <h3>How to Use:</h3>
 * Quite frankly, it's as easy as can be. All you have to do is instantiate a new object using<br>
 * <code>Window variable = new Window(parameters)</code><br>
 * and the window will automatically be created and immediately displayed on the screen.
 * You can check the methods section of this Doc for information on how you can modify the window
 * after it is created.
 *
 * <h3>Technical Information:</h3>
 * This class uses a static {@link WindowManager} to create a JavaFX Thread, and then manage all of the
 * different windows objects from that single thread. There are a few things to note about this.
 * <ol>
 * <li>All {@link Window} objects are ultimately managed through one JFX Thread, so it's possible that creating
 * multiple different objects will cause significant slow-down/lag.
 * </li>
 * <li>If the constructor fails to call initialize from the {@link WindowManager}, it will not fail to throw an
 * error, but following calls requesting modifications to the JavaFX Stage may get hung up / deadlocked
 * waiting for the WindowManager to respond.
 * </li>
 * </ol>
 *
 */
public class Window {

    // This flag indicates if the WindowManager has been initialized
    private static boolean wmReady = false;

    // - - - - - - - - - - Constructors - - - - - - - - - -

    /**
     * -PRIMARY CONSTRUCTOR-<br>
     * This constructor requires all possible parameters, other constructors
     * will ultimately call this, but you're welcome to use it directly as well!
     *
     * @param title The title given to the window
     * @param width The width given to the window
     * @param height The height given to the window
     */
    public Window(String title, int width, int height) {

        // If it's not ready
        if (!wmReady) {
            // Initialize WindowManager and set wmReady to true
            WindowManager.initialize();
            wmReady = true;
        }

        // Then create a new window, passing in this object as a key
        WindowManager.newWindow(this);

        // Set parameters as provided
        WindowManager.setTitle(this, title);
        WindowManager.setSize(this, width, height);

    }

    /**
     * Creates a window without requiring any parameters
     */
    public Window() {
        this("JLearn", 500, 500);
    }

    /**
     * Constructor if you'd like to create a window with just a title, allowing other parameters to default.<br><br>
     *
     * @param title The title you'd like to give the window.
     */
    public Window(String title) {
        this(title, 500, 500);
    }

    /**
     * Constructor to create a window with just the size, allowing other parameters to default.<br><br>
     *
     * @param width The width of the desired window
     * @param height The height of the desired widnow
     */
    public Window(int width, int height) {
        this("JLearn", width, height);
    }

    // - - - - - - - - - - Setters - - - - - - - - - -

    /**
     * Allows you to set the title of the window object
     * @param title The title you want the window set to
     */
    public void setTitle(String title) {
        // Use the window manager to set the title, passing in this window and the desired title
        WindowManager.setTitle(this, title);
    }

    /**
     * Allows you to set the size of the window object
     * @param width The width of the window
     * @param height The height of the window
     */
    public void setSize(int width, int height) {
        // Call the window manager to change size, passing in the object and desired size
        WindowManager.setSize(this, width, height);
    }

    // - - - - - - - - - - Other Methods - - - - - - - - - -

    public void show(Chart chart) {

    }


}
