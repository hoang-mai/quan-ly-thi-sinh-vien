package com.example.project.gui11;

import javafx.fxml.FXML;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;

import java.net.URL;
import java.util.ResourceBundle;


public class gui11 implements Initializable {


    @FXML
    private Label it;
    @FXML
    void kiemtracuoikimonit1(ActionEvent event) {
        try {
            Stage ag0r1 = (Stage) ((Node) event.getSource()).getScene().getWindow();
            Parent root = FXMLLoader.load(getClass().getResource("/com/example/project/gui61/gui(6.1).fxml"));
            Scene scene = new Scene(root);
            ag0r1.setScene(scene);
            ag0r1.show();

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
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
            Stage a = (Stage) it.getScene().getWindow();
            a.hide();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
    @FXML
    void caidat(ActionEvent event) {
        try {
            Stage a = (Stage) it.getScene().getWindow();
            FXMLLoader loader= new FXMLLoader(getClass().getResource("/com/example/project/popup/popup.fxml"));
            Parent root = loader.load();
            Scene scene = new Scene(root);
            Stage ag0r1=new Stage();
            ag0r1.setScene(scene);
            ag0r1.initModality(Modality.APPLICATION_MODAL);
            ag0r1.initOwner(a);
            ag0r1.setOnCloseRequest(WindowEvent :: consume);
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





    


