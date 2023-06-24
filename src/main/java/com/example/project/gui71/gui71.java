package com.example.project.gui71;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.net.URL;
import java.util.ResourceBundle;

public class gui71 implements Initializable {

    @FXML
    private Label timeleft;
    private String formatTime(int timeInSeconds) {
        int hours = timeInSeconds / 3600;
        int minutes = (timeInSeconds % 3600) / 60;
        int seconds = timeInSeconds % 60;
        return String.format("Time left %02d:%02d:%02d", hours, minutes, seconds);
    }
    @FXML
    void finishattempt(ActionEvent event) {
        try {
            Stage ag0r = (Stage) ((Node) event.getSource()).getScene().getWindow();
            Parent root = FXMLLoader.load(getClass().getResource("/com/example/project/gui11/gui(1.1).fxml"));
            Scene scene = new Scene(root);
            ag0r.setScene(scene);

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
    private  int timeRemaining;
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
         timeRemaining = 3600;
        timeleft.setText(formatTime(timeRemaining));
        Timeline timeline = new Timeline();
        timeline.setCycleCount(Timeline.INDEFINITE);
        timeline.getKeyFrames().add(
                new KeyFrame(Duration.seconds(1), new EventHandler<ActionEvent>() {
                    @Override
                    public void handle(ActionEvent event) {
                        timeRemaining--;
                        timeleft.setText(formatTime(timeRemaining));
                        if (timeRemaining<= 0) {
                            timeline.stop();
                        }
                    }
                })
        );
        timeline.play();

    }
}
