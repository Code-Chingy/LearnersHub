package com.techupstudio.Application;

import javafx.application.Application;
import javafx.stage.Stage;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception {
       primaryStage = StageManager.getStage("StoryLane : Home", "activity_home");
       StageManager.setPrimaryStage(primaryStage);
    }

    public static void main(String[] args) { launch(args); }
}
