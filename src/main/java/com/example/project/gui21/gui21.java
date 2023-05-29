package com.example.project.gui21;

import com.example.project.gui32.gui32;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.scene.Node;

public class gui21 {
    @FXML
    private AnchorPane anchorpane1;
    @FXML
    private ScrollPane scrollpane1;
    @FXML
    private CheckBox alsoquestion1;

    @FXML
    void Category(ActionEvent event) {
        try {
            Stage ag0r1 = (Stage) ((Node) event.getSource()).getScene().getWindow();
            Parent root = FXMLLoader.load(getClass().getResource("/com/example/project/gui33/gui(3.3).fxml"));
            Scene scene = new Scene(root);
            ag0r1.setScene(scene);
            ag0r1.show();

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    @FXML
    void Import(ActionEvent event) {
        try {
            Stage ag0r1 = (Stage) ((Node) event.getSource()).getScene().getWindow();
            Parent root = FXMLLoader.load(getClass().getResource("/com/example/project/gui34/gui(3.4).fxml"));
            Scene scene = new Scene(root);
            ag0r1.setScene(scene);
            ag0r1.show();

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    @FXML
    void alsoquestion(ActionEvent event) {
        if (alsoquestion1.isSelected()) {
            scrollpane1.setVisible(true);
            anchorpane1.setVisible(true);
        } else {
            scrollpane1.setVisible(false);
            anchorpane1.setVisible(false);
        }
    }

    @FXML
    void Edit1(ActionEvent event) {
        try {
            Stage ag0r1 = (Stage) ((Node) event.getSource()).getScene().getWindow();
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/project/gui32/gui(3.2).fxml"));
            Parent root = loader.load();
            Scene scene = new Scene(root);
            ag0r1.setScene(scene);
            ag0r1.show();
            gui32 controller = loader.getController();
            controller.setaddingamultipe("Editing Multiple choice question");

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    @FXML
    void createquestion(ActionEvent event) {
        try {
            Stage ag0r1 = (Stage) ((Node) event.getSource()).getScene().getWindow();
            Parent root = FXMLLoader.load(getClass().getResource("/com/example/project/gui32/gui(3.2).fxml"));
            Scene scene = new Scene(root);
            ag0r1.setScene(scene);
            ag0r1.show();

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

}
