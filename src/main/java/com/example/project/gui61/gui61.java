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
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;

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
        try {
            Stage a = (Stage) timelimit1.getScene().getWindow();
            FXMLLoader loader= new FXMLLoader(getClass().getResource("/com/example/project/gui61/dialog.fxml"));
            Parent root = loader.load();
            Scene scene = new Scene(root);
            Stage ag0r1=new Stage();
            ag0r1.setScene(scene);
            ag0r1.initModality(Modality.APPLICATION_MODAL);
            ag0r1.initOwner(a);
            ag0r1.setOnCloseRequest(WindowEvent:: consume);
            ag0r1.showAndWait();
            a.hide();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }
}

