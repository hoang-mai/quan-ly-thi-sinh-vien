package com.example.project.gui32;


import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.stage.FileChooser;
import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;

import com.example.project.database.dao.QuestionsDao;
import com.example.project.database.entities.Questions;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
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

    private byte[] imageData;

    @FXML
    public void insertImage() {
        FileChooser fileChooser = new FileChooser();
        FileChooser.ExtensionFilter imageFilter = new FileChooser.ExtensionFilter("Image Files", "*.png", "*.jpg", "*.jpeg", "*.gif");
        fileChooser.getExtensionFilters().add(imageFilter);
        File selectedFile = fileChooser.showOpenDialog(null);
        if (selectedFile != null) {
            try {
                BufferedImage bufferedImage = ImageIO.read(selectedFile);
                ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
                ImageIO.write(bufferedImage, "png", byteArrayOutputStream);
                imageData = byteArrayOutputStream.toByteArray();
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
            Questions questions = new Questions();
            questions.setQuestionName(questtionname1.getText());
            questions.setQuestionText(questiontext1.getText());
            questions.setDefaultmark(Integer.parseInt(defaultmark.getText()));
            questions.setImage(imageData);
            QuestionsDao.getInstance().save(questions);
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

    @FXML
    private VBox vbox;

    @FXML
    private Pane originalPane;
    @FXML
    private Pane originalPane1;
    @FXML
    private Pane originalPane2;
    @FXML
    private Button addButton;
    @FXML
    private void addMoreChoices() {
        for(int i=3;i<6;i++) {
            Pane newPane = clonePane(originalPane,i);
            int lastIndex = vbox.getChildren().size(); // Lấy kích thước hiện tại của VBox
            vbox.getChildren().add(lastIndex - 1, newPane); // Thêm newPane vào trước vị trí cuối cùng
        }
    }

    private Pane clonePane(Pane originalPane,int i) {
        Pane newPane = new Pane();
        newPane.getStyleClass().addAll(originalPane.getStyleClass());
        newPane.setStyle(originalPane.getStyle());
        newPane.setPrefSize(originalPane.getPrefWidth(), originalPane.getPrefHeight());

        String numberString = Integer.toString(i);
        Label label1 = new Label("Choice "+ numberString);
        label1.setLayoutX(10);
        label1.setLayoutY(64);

        Label label2 = new Label("Grade");
        label2.setLayoutX(22);
        label2.setLayoutY(97);

        TextArea textArea = new TextArea();
        textArea.setLayoutX(62);
        textArea.setLayoutY(11);
        textArea.setPrefSize(198, 68);

        ComboBox<String> comboBox = new ComboBox<>();
        comboBox.setLayoutX(62);
        comboBox.setLayoutY(93);
        comboBox.getItems().addAll("Option 1", "Option 2", "Option 3");

        Button button = new Button("Image");
        button.setLayoutX(12);
        button.setLayoutY(136);

        ImageView imageView = new ImageView();
        imageView.setLayoutX(72);
        imageView.setLayoutY(136);
        imageView.setFitWidth(200); // Đặt chiều rộng của ImageView
        imageView.setFitHeight(77); // Đặt chiều cao của ImageView
        button.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
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
        });
        newPane.getChildren().addAll(label1, label2, textArea, comboBox, button, imageView);

        return newPane;
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
