package com.example.project.gui32;


import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.stage.Stage;

public class gui32 implements Initializable {
    @FXML
    private Label addingamultipe;

    public void setaddingamultipe(String text) {
        addingamultipe.setText(text);
    }

    @FXML
    private ImageView canhbao1;

    @FXML
    private ImageView canhbao2;
    @FXML
    private TextArea questiontext1;

    @FXML
    private TextArea questtionname1;
    @FXML
    private ImageView canhbao3;
    @FXML
    private Button savechangesandcontinue1;

    @FXML
    private TextField defaultmark;

    @FXML
    void questiontext(KeyEvent event) {
        if (event.getCode() == KeyCode.ENTER) {
            String text = questiontext1.getText();
            text += "\n";
            questiontext1.setText(text);
            questiontext1.positionCaret(text.length());
        }
    }

    @FXML
    void questtionname(KeyEvent event) {
        if (event.getCode() == KeyCode.ENTER) {
            String content = questtionname1.getText();
            content += "\n"; // Thêm ký tự xuống dòng
            questtionname1.setText(content);
            questtionname1.positionCaret(content.length());
        }
    }

    @FXML
    void cancel(ActionEvent event) {
        try {
            Stage ag0r = (Stage) ((Node) event.getSource()).getScene().getWindow();
            Parent root = FXMLLoader.load(getClass().getResource("/com/example/project/gui21/gui(2.1).fxml"));
            Scene scene = new Scene(root);
            ag0r.setScene(scene);

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    @FXML
    private Button savechanges1;

    @FXML
    void savechanges(ActionEvent event) {

        try {
            Stage ag0r = (Stage) ((Node) event.getSource()).getScene().getWindow();
            Parent root = FXMLLoader.load(getClass().getResource("/com/example/project/gui21/gui(2.1).fxml"));
            Scene scene = new Scene(root);
            ag0r.setScene(scene);

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    @FXML
    void savechangesandcontinue(ActionEvent event) {

    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        questtionname1.setWrapText(true);
        questiontext1.setWrapText(true);
        savechanges1.setDisable(true);
        savechangesandcontinue1.setDisable(true);
        questtionname1.textProperty().addListener((Observable, oldvalue, newValue) -> {
            if (newValue.isEmpty()) {
                canhbao1.setVisible(true);
            } else {
                canhbao1.setVisible(false);
            }
            if (questiontext1.getText().isEmpty() || newValue.isEmpty() || defaultmark.getText().isEmpty()) {
                savechanges1.setDisable(true);
                savechangesandcontinue1.setDisable(true);
            } else {
                savechanges1.setDisable(false);
                savechangesandcontinue1.setDisable(false);
            }
        });
        questiontext1.textProperty().addListener((Observable, oldvalue, newValue) -> {
            if (newValue.isEmpty()) {
                canhbao2.setVisible(true);
            } else {
                canhbao2.setVisible(false);
            }
            if (questtionname1.getText().isEmpty() || newValue.isEmpty() || defaultmark.getText().isEmpty()) {
                savechanges1.setDisable(true);
                savechangesandcontinue1.setDisable(true);
            } else {
                savechanges1.setDisable(false);
                savechangesandcontinue1.setDisable(false);
            }
        });
        defaultmark.textProperty().addListener((Observable, oldvalue, newValue) -> {
            if (newValue.isEmpty()) {
                canhbao3.setVisible(true);
            } else {
                canhbao3.setVisible(false);
            }
            if (questiontext1.getText().isEmpty() || questtionname1.getText().isEmpty() || newValue.isEmpty()) {
                savechanges1.setDisable(true);
                savechangesandcontinue1.setDisable(true);
            } else {
                savechanges1.setDisable(false);
                savechangesandcontinue1.setDisable(false);
            }
        });

    }
}
