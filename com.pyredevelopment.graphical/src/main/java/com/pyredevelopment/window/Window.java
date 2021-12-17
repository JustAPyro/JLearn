package com.pyredevelopment.window;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.stage.Stage;

import static javafx.application.Application.launch;

/**
 * This class provides an easy wrapper/API class to easily create windows
 */
public class Window {


    public Window() {

        System.out.println("First");
        WindowManager.myInit();
        System.out.println("Mid");
        WindowManager.newFrame();
        System.out.println("Last");

    }




}
