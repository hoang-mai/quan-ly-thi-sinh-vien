package com.example.project.popup;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class popup {

    @FXML
    private Pane popup;
public int b=0;
    @FXML
    void categories(ActionEvent event) {
        try {
            Parent root = FXMLLoader.load(getClass().getResource("/com/example/project/gui21/gui(2.1).fxml"));
            Scene scene = new Scene(root);
            Stage ag0r = new Stage();
            ag0r.setScene(scene);
            ag0r.show();
            Stage a = (Stage) popup.getScene().getWindow();
            a.hide();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    @FXML
    void questions(ActionEvent event) {b=1;
        try {
            Parent root = FXMLLoader.load(getClass().getResource("/com/example/project/gui21/gui(2.1).fxml"));
            Scene scene = new Scene(root);
            Stage ag0r = new Stage();
            ag0r.setScene(scene);
            ag0r.show();
            Stage a = (Stage) popup.getScene().getWindow();
            a.hide();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    @FXML
    void import1(ActionEvent event) {b=1;
        try {
            Parent root = FXMLLoader.load(getClass().getResource("/com/example/project/gui21/gui(2.1).fxml"));
            Scene scene = new Scene(root);
            Stage ag0r = new Stage();
            ag0r.setScene(scene);
            ag0r.show();
            Stage a = (Stage) popup.getScene().getWindow();
            a.hide();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

}