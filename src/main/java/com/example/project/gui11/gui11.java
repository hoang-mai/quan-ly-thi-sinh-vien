package com.example.project.gui11;

import com.example.project.gui51.savedata;
import javafx.fxml.FXML;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class gui11  {


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
    public void initialize() {
        List<String> labelValues = savedata.getLabelValues();
        String labelTag = "oldLabel"; // Đánh dấu cho các label cũ đã được thêm vào VBox trước đó

        // Xóa các label cũ từ VBox
        List<Node> nodesToRemove = new ArrayList<>();
        for (Node node : vbox1.getChildren()) {
            if (node instanceof Button && labelTag.equals(node.getUserData())) {
                nodesToRemove.add(node);
            }
        }
        vbox1.getChildren().removeAll(nodesToRemove);

        // Thêm các label mới vào VBox
        for (String value : labelValues) {
            Button button = new Button(value);
            button.setOnAction(actionEvent -> {
                try {
                    Stage ag0r1 = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
                    Parent root = FXMLLoader.load(getClass().getResource("/com/example/project/gui61/gui(6.1).fxml"));
                    Scene scene = new Scene(root);
                    ag0r1.setScene(scene);
                    ag0r1.show();

                } catch (Exception e) {
                    System.out.println(e.getMessage());
                }
            });
            button.setUserData(labelTag); // Đánh dấu label mới để xác định là label mới
            vbox1.getChildren().add(button); // Thêm label vào VBox
        }
    }



}




    


