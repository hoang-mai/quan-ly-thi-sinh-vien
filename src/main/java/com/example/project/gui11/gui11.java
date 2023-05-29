package com.example.project.gui11;

import javafx.fxml.FXML;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class gui11 {

    @FXML
    private Button kiemtracuoikimonit1;
    @FXML
    private VBox vbox1;

    @FXML
    void turneditingon(ActionEvent event) {
        try {

            Parent root = FXMLLoader.load(getClass().getResource("/com/example/project/gui51/gui(5.1).fxml"));
            Scene scene = new Scene(root);
            Stage ag0r = new Stage();
            ag0r.setScene(scene);
            ag0r.show();
            Stage a = (Stage) kiemtracuoikimonit1.getScene().getWindow();
            a.hide();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    @FXML
    void handle(ActionEvent event) {
        try {

            Parent root = FXMLLoader.load(getClass().getResource("/com/example/project/popup/popup.fxml"));
            Scene scene = new Scene(root);
            Stage arg01 = new Stage();
            arg01.setScene(scene);
            arg01.show();

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }


}




    


