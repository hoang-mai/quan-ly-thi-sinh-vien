package com.example.project;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main1 extends Application {
    @Override
    public void start(Stage arg0) {
        try {
            Parent root = FXMLLoader.load(getClass().getResource("/com/example/project/gui11/gui(1.1).fxml"));
            Scene scene = new Scene(root);
            arg0.setScene(scene);
            arg0.show();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
    public static void main(String[] args) {
        launch(args);
    }
}