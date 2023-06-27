package com.example.project.gui32;


import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import com.example.project.database.dao.QuestionsDao;
import com.example.project.database.entities.Questions;
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
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.stage.FileChooser;
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
    private ImageView ImageChoice1;
    @FXML
    private ImageView ImageChoice2;
    @FXML
    private ImageView imageView;

    @FXML
    public void insertImage() {
        FileChooser fileChooser = new FileChooser();
        FileChooser.ExtensionFilter imageFilter = new FileChooser.ExtensionFilter("Image Files", "*.png", "*.jpg", "*.jpeg", "*.gif");
        fileChooser.getExtensionFilters().add(imageFilter);
        File selectedFile = fileChooser.showOpenDialog(null);
        if (selectedFile != null) {
            try {
                Image image = new Image(new FileInputStream(selectedFile));
                imageView.setImage(image);
                imageView.setVisible(true);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
    @FXML
    public void InsertImageChoice2() {
        FileChooser fileChooser = new FileChooser();
        FileChooser.ExtensionFilter imageFilter = new FileChooser.ExtensionFilter("Image Files", "*.png", "*.jpg", "*.jpeg", "*.gif");
        fileChooser.getExtensionFilters().add(imageFilter);
        File selectedFile = fileChooser.showOpenDialog(null);
        if (selectedFile != null) {
            try {
                Image image = new Image(new FileInputStream(selectedFile));
                ImageChoice2.setImage(image);
                ImageChoice2.setVisible(true);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
    @FXML
    public void InsertImageChoice1() {
        FileChooser fileChooser = new FileChooser();
        FileChooser.ExtensionFilter imageFilter = new FileChooser.ExtensionFilter("Image Files", "*.png", "*.jpg", "*.jpeg", "*.gif");
        fileChooser.getExtensionFilters().add(imageFilter);
        File selectedFile = fileChooser.showOpenDialog(null);
        if (selectedFile != null) {
            try {
                Image image = new Image(new FileInputStream(selectedFile));
                ImageChoice1.setImage(image);
                ImageChoice1.setVisible(true);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

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
        try {

            Stage ag0r = (Stage) ((Node) event.getSource()).getScene().getWindow();
            Parent root = FXMLLoader.load(getClass().getResource("/com/example/project/gui32/gui(3.2).fxml"));
            Scene scene = new Scene(root);
            ag0r.setScene(scene);

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        questtionname1.setWrapText(true);
        questiontext1.setWrapText(true);

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
