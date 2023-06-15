package com.example.project.gui61;

import java.net.URL;
import java.util.ResourceBundle;
import com.example.project.database.dao.QuizDao;
import com.example.project.database.entities.Quiz;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.scene.Node;
public class dialog implements Initializable {
    @FXML
    private DialogPane dialogpane;
    @FXML
    private ButtonType start;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        Label label=new Label("Time limit");

        Label label1=new Label("Your attempt will have a time limit of "+"When you start,the timer will begin" +"\n"+
                " to count down and cannot be paused. You must finish your attempt before it " +"\n"+
                "expires.Are you sure you wish to start now ?");
        VBox vBox=new VBox();
        vBox.getChildren().addAll(label,label1);
        dialogpane.setContent(vBox);

    }
}
