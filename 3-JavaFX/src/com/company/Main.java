package com.company;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 * Class for running main method of the program used to analyze the text given by the user (text mining).
 *
 * @author Hanna Gościniak
 * @version 1.0
 * */
public class Main extends Application {

    /**
     * Public method setting primary stage of the program
     * @param primaryStage initial stage of program
     * @throws Exception if fxml won't be found in resources
     * */
    @Override
    public void start(Stage primaryStage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("controller/startWindow.fxml"));
        primaryStage.setTitle("My Program");

        primaryStage.setScene(new Scene(root, 500, 350));
        primaryStage.show();

    }

    /**
     * Public method main launching arguments of the program
     * @param args argument of the program
     * */
    public static void main(String[] args) {
        launch(args);
    }
}