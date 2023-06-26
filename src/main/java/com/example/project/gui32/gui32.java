package com.example.project.gui32;



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

    @FXML
    private VBox vbox;

    @FXML
    private Pane originalPane;

    @FXML
    private void addMoreChoices() {
        for (int i = 0; i < 3; i++) {
            Pane newPane = createNewPane();
            vbox.getChildren().add(newPane);
        }
    }

    private Pane createNewPane() {
        Pane newPane = new Pane();
        newPane.getStyleClass().addAll(originalPane.getStyleClass());
        newPane.setStyle(originalPane.getStyle());
        newPane.setPrefSize(originalPane.getPrefWidth(), originalPane.getPrefHeight());
        // Copy các thành phần con của originalPane vào newPane
        newPane.getChildren().addAll(originalPane.getChildren());
        return newPane;
    }
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
            Questions questions = new Questions();
            questions.setQuestionName(questtionname1.getText());
            questions.setQuestionText(questiontext1.getText());
            questions.setDefaultmark(Integer.parseInt(defaultmark.getText()));
            //questions.setImage(imageView.getImage());

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
            Questions questions = new Questions();
            questions.setQuestionName(questtionname1.getText());
            questions.setQuestionText(questiontext1.getText());
            questions.setDefaultmark(Integer.parseInt(defaultmark.getText()));
            //questions.setImage(imageView.getImage());

            QuestionsDao.getInstance().save(questions);
            Stage ag0r = (Stage) ((Node) event.getSource()).getScene().getWindow();
            Parent root = FXMLLoader.load(getClass().getResource("/com/example/project/gui32/gui(3.2).fxml"));
            Scene scene = new Scene(root);
            ag0r.setScene(scene);

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

