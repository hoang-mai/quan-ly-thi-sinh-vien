package com.example.project.gui61;

import com.example.project.gui51.gui51;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Dialog;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ResourceBundle;

public class gui61 implements Initializable {

    @FXML
    private Label timelimit1;
    @FXML
    void caidat(ActionEvent event) {

    }
    @FXML
    void previewquiznow(ActionEvent event) {
        Dialog<Void> dialog=new Dialog<>();
        dialog.setHeaderText("Start attempt");
        Label mainContentLabel = new Label("Time limit");
        Label additionalLabel = new Label("Your attempt will have a time limit of"+"When you start,the timer will begin" +"\n"+
                " to count down and cannot be paused. You must finish your attempt before it " +"\n"+
                "expires.Are you sure you wish to start now ?");

        ButtonType button1= new ButtonType("START ATTEMPT");
        ButtonType button2= new ButtonType("CANCEL",ButtonType.CANCEL.getButtonData());
        dialog.getDialogPane().getButtonTypes().addAll(button1,button2);
        dialog.setResultConverter(dialogbutton->{
            if(dialogbutton==button1){
                try {
                    Stage ag0r1 = (Stage) ((Node) event.getSource()).getScene().getWindow();
                    Parent root = FXMLLoader.load(getClass().getResource("/com/example/project/gui11/gui(1.1).fxml"));
                    Scene scene = new Scene(root);
                    ag0r1.setScene(scene);
                    ag0r1.show();

                    scene.getStylesheets().add(getClass().getResource("subtest.css").toExternalForm());

                } catch (Exception e) {
                    System.out.println(e.getMessage());
                }
            }
            return null;
        });
        VBox content = new VBox(mainContentLabel, additionalLabel);
        content.setSpacing(10);
        content.setPadding(new Insets(10));
        dialog.getDialogPane().setContent(content);
        dialog.showAndWait();


    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }
}

