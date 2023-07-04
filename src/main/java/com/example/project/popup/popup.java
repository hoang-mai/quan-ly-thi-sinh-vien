package com.example.project.popup;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import java.io.IOException;

public class popup {

    @FXML
    private TabPane tabPane;
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
    void import1(ActionEvent event) {
        try {
            // Tạo FXMLLoader và tải GUI mới
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/project/gui21/gui(2.1).fxml"));
            Parent newRoot = loader.load();

            // Lấy TabPane mới từ FXMLLoader
            TabPane newTabPane = (TabPane) newRoot.lookup("#tabPane");

            // Lấy Tab cần chuyển đến
            Tab desiredTab = newTabPane.getTabs().get(2);

            // Lấy Stage hiện tại
            Stage currentStage = (Stage) ((Node) event.getSource()).getScene().getWindow();

            // Tạo Scene mới với TabPane mới
            Scene newScene = new Scene(newRoot);

            // Đặt Scene mới cho Stage hiện tại
            currentStage.setScene(newScene);

            // Chọn Tab cần chuyển đến trong TabPane mới
            newTabPane.getSelectionModel().select(desiredTab);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }}
