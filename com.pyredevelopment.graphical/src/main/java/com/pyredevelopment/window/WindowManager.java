package com.pyredevelopment.window;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.stage.Stage;

import static java.lang.Thread.sleep;

public class WindowManager extends Application{


    public static void newFrame() {
        System.out.println("Sup");
        try {
            sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        Platform.runLater(() -> {

            System.out.println("Runnin later");
            Stage s = new Stage();
            s.show();
        });

    }

    public static void myInit()
    {
        Thread t = new Thread(() -> {
            Application.launch(WindowManager.class);
        });
        t.start();
    }

    @Override
    public void start(Stage stage) {
        Platform.setImplicitExit(false);
        stage.show();
        System.out.println("Started");
    }

}
