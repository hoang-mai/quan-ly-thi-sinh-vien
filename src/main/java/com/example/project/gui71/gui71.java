package com.example.project.gui71;

import com.example.project.database.dao.QuizDao;
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
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.net.URL;
import java.util.ResourceBundle;

public class gui71 implements Initializable {
    @FXML
    private ToggleGroup toggleGroup;
    @FXML
    private Button one1;

    @FXML
    private AnchorPane anchorpane;
    @FXML
    private VBox vbox1;
    @FXML
    private ScrollPane scrollpane;
    @FXML
    void one(ActionEvent event) {


    }

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
        Dialog<Void> dialog=new Dialog<>();
        dialog.setContentText("Do you want to finish ?");
        ButtonType buttonType=new ButtonType("FINISH", ButtonBar.ButtonData.FINISH);
        ButtonType buttonType1=new ButtonType("NO", ButtonBar.ButtonData.NO);
        dialog.getDialogPane().getButtonTypes().addAll(buttonType1,buttonType);
        dialog.setResultConverter(buttontype->{
            if(buttontype==buttonType){

            } else if (buttontype==buttonType1) {
                dialog.close();
            }
            return null;
        });
        dialog.show();
    }
    private  int timeRemaining=QuizDao.getInstance().getQuiz().getTimeLimit()*60;
    @FXML
    void one1(MouseEvent event) {
        scrollpane.setVvalue(vbox1.getLayoutY()/anchorpane.getHeight());
    }
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
toggleGroup.selectedToggleProperty().addListener((observable,oldvalue,newvalue)->{
    if (newvalue != null) {
        // Cập nhật giao diện cho các Button
        one1.getStyleClass().add("black-border-button");
    }
});
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
