package com.example.project.gui32;


import java.io.*;
import java.net.URL;
import java.util.*;

import com.example.project.database.dao.CategoriesDao;
import com.example.project.database.dao.ChoiceDao;
import com.example.project.database.entities.Categories;
import com.example.project.database.entities.Choice;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.image.PixelReader;
import javafx.scene.layout.AnchorPane;
import javafx.scene.effect.ColorAdjust;
import javafx.stage.FileChooser;
import javax.imageio.ImageIO;
import javax.imageio.stream.ImageInputStream;
import java.awt.image.BufferedImage;

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
import javafx.stage.Stage;

public class gui32 implements Initializable {
    private Boolean a;
    @FXML
    private ComboBox<String> combobox;
    @FXML
    private ComboBox<String> comboboxchoice1;

    @FXML
    private ComboBox<String> comboboxchoice2;
    @FXML
    private Label addingamultipe;
    private byte[] imageData;
    public void setedit(String adding) {
        anchorPane.setPrefHeight(2200);
        vbox.setPrefHeight(1250);
        addMoreChoicesEnabled = false;
        int id=QuestionsDao.getInstance().getQuestions().getCategories().getCategoryId();
        a=true;
        addingamultipe.setText(adding);
        questtionname1.setText(QuestionsDao.getInstance().getQuestions().getQuestionName());
        questiontext1.setText(QuestionsDao.getInstance().getQuestions().getQuestionText());
        defaultmark.setText(String.valueOf(QuestionsDao.getInstance().getQuestions().getDefaultmark()));
        combobox.setValue(CategoriesDao.getInstance().selectCategorybyId(id).getCategoryName());
        if(QuestionsDao.getInstance().getQuestions().getImage()!=null){
            imageData=QuestionsDao.getInstance().getQuestions().getImage();
        Image image=new Image(new ByteArrayInputStream(QuestionsDao.getInstance().getQuestions().getImage()));
        imageView.setImage(image);}
        int j=QuestionsDao.getInstance().getQuestions().getQuestionId();
        List<Choice> choices=QuestionsDao.getInstance().selectChoicebyQuestionId(j);
ChoiceDao.getInstance().setChoices(choices);
        for (int i = 3; i < 6; i++) {
            Pane newPane = clonePane(originalPane, i);
            int lastIndex = vbox.getChildren().size(); // Lấy kích thước hiện tại của VBox
            vbox.getChildren().add(lastIndex - 1, newPane); // Thêm newPane vào trước vị trí cuối cùng
        }
        ObservableList<Node> children = vbox.getChildren();

        for (int i = 0; i < choices.size(); i++) {
            imageData1.put(i+1,choices.get(i).getImage());
            Node node = children.get(i);
            if (node instanceof Pane) {
                Pane pane = (Pane) node;

                TextArea textArea = (TextArea) pane.getChildren().get(2);
                ComboBox<String> comboBox = (ComboBox<String>) pane.getChildren().get(3);
                Choice choice = choices.get(i);
                textArea.setText(choice.getChoiceText());
                comboBox.setValue(choice.getGrade());
                if(choice.getImage()!=null){
Image image1 =new Image(new ByteArrayInputStream(choice.getImage()));
ImageView imageView1=(ImageView) pane.getChildren().get(5);
imageView1.setImage(image1);}
                // Tiếp tục xử lý dữ liệu lấy được

            }
        }

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


    private Map<Integer,byte[]> imageData1=new HashMap<>();
    @FXML
    private TextArea choicetext1;
    @FXML
    private TextArea choicetext2;

    @FXML
    void questionbank(ActionEvent event) {
        try {
            Stage ag0r1 = (Stage) ((Node) event.getSource()).getScene().getWindow();
            Parent root = FXMLLoader.load(getClass().getResource("/com/example/project/gui21/gui(2.1).fxml"));
            Scene scene = new Scene(root);
            ag0r1.setScene(scene);
            ag0r1.show();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
    @FXML
    void home(ActionEvent event) {
        try {
            Stage ag0r1 = (Stage) ((Node) event.getSource()).getScene().getWindow();
            Parent root = FXMLLoader.load(getClass().getResource("/com/example/project/gui11/gui(1.1).fxml"));
            Scene scene = new Scene(root);
            ag0r1.setScene(scene);
            ag0r1.show();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
    @FXML
    public void insertImage() {
        FileChooser fileChooser = new FileChooser();
        FileChooser.ExtensionFilter imageFilter = new FileChooser.ExtensionFilter("Image Files", "*.png", "*.jpg", "*.jpeg", "*.gif");
        fileChooser.getExtensionFilters().add(imageFilter);
        File selectedFile = fileChooser.showOpenDialog(null);
        if (selectedFile != null) {
            try {
                imageData = readImageAsByteArray(selectedFile);
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

                imageData1.put(2,readImageAsByteArray(selectedFile));
                Image image = new Image(new FileInputStream(selectedFile));
                ImageChoice2.setImage(image);
                ImageChoice2.setPreserveRatio(true);
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

                imageData1.put(1,readImageAsByteArray(selectedFile));
                Image image = new Image(new FileInputStream(selectedFile));
                ImageChoice1.setImage(image);
                ImageChoice1.setPreserveRatio(true);
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
    private byte[] readImageAsByteArray(File file) throws IOException {
        try (FileInputStream fis = new FileInputStream(file);
             ByteArrayOutputStream bos = new ByteArrayOutputStream()) {

            byte[] buffer = new byte[4096];
            int bytesRead;
            while ((bytesRead = fis.read(buffer)) != -1) {
                bos.write(buffer, 0, bytesRead);
            }

            return bos.toByteArray();
        }
    }

    private void saveChoice(Questions questions, String choiceText, String grade,byte[] imageData,int i) {
        try {
            Choice choice = new Choice();
            choice.setChoiceText(choiceText);
            choice.setQuestions(questions);
            choice.setGrade(grade);
            choice.setImage(imageData);
            if(addingamultipe.getText().startsWith("Add")){
                if(!Objects.equals(choiceText, "")) ChoiceDao.getInstance().save(choice);}
            else {
                if(i<ChoiceDao.getInstance().getChoices().size()){
                int j=ChoiceDao.getInstance().getChoices().get(i).getChoiceId();
                choice.setChoiceId(j);
                if(Objects.equals(choiceText, "")) ChoiceDao.getInstance().Delete(choice);
                ChoiceDao.getInstance().update(choice);}
                else {
                    if(!Objects.equals(choiceText, "")) ChoiceDao.getInstance().save(choice);
                        }
            }

        } catch (Exception e) {
            // Xử lý ngoại lệ tại đây
        }
    }

    @FXML
    void savechanges(ActionEvent event) {

        try {
            String cate = combobox.getValue().trim();
            int mongoac = cate.length();
            if (cate.contains("(")) {
                mongoac = cate.indexOf("(");
            }
            // Lấy text của choice
            Questions questions = new Questions();
            questions.setQuestionName(questtionname1.getText());
            questions.setQuestionText(questiontext1.getText());
            questions.setDefaultmark(Integer.parseInt(defaultmark.getText()));
            questions.setImage(imageData);

            Categories categories = CategoriesDao.getInstance().selectCategorybyName(cate.substring(0, mongoac));
            questions.setCategories(categories);
            if(addingamultipe.getText().startsWith("Add")){
                QuestionsDao.getInstance().save(questions);}
            else {
                int j=QuestionsDao.getInstance().getQuestions().getQuestionId();
                questions.setQuestionId(j);
                QuestionsDao.getInstance().update(questions);
            }
            ObservableList<Node> children = vbox.getChildren();

            for (int i = 0; i < children.size() -1; i++) {
                Node node = children.get(i);
                if (node instanceof Pane) {
                    Pane pane = (Pane) node;
                    // Kiểm tra điều kiện để xác định Pane có dữ liệu
                    if (pane.getChildren().size() > 0) {
                        // Lấy dữ liệu từ các thành phần con trong Pane

                        TextArea textArea = (TextArea) pane.getChildren().get(2);
                        ComboBox<String> comboBox = (ComboBox<String>) pane.getChildren().get(3);

                        // Tiếp tục lấy dữ liệu từ các thành phần khác nếu cần

                        // Sử dụng dữ liệu đã lấy để thực hiện các tác vụ khác
                        String choiceText = textArea.getText();
                        String grade = comboBox.getValue();
                        byte[] image=imageData1.get(i+1);
                        saveChoice(questions, choiceText, grade,image,i);
                        // Tiếp tục xử lý dữ liệu lấy được
                    }
                }
            }

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
            String cate = combobox.getValue().trim();
            int mongoac = cate.length();
            if (cate.contains("(")) {
                mongoac = cate.indexOf("(");
            }

            // Lấy text của choice
            Questions questions = new Questions();
            questions.setQuestionName(questtionname1.getText());
            questions.setQuestionText(questiontext1.getText());
            questions.setDefaultmark(Integer.parseInt(defaultmark.getText()));
            questions.setImage(imageData);
            Categories categories = CategoriesDao.getInstance().selectCategorybyName(cate.substring(0, mongoac));
            questions.setCategories(categories);

            if(addingamultipe.getText().startsWith("Add")){
            QuestionsDao.getInstance().save(questions);}
            else {
                int j=QuestionsDao.getInstance().getQuestions().getQuestionId();
                questions.setQuestionId(j);
                QuestionsDao.getInstance().update(questions);
            }
            ObservableList<Node> children = vbox.getChildren();

            for (int i = 0; i < children.size()-1 ; i++) {
                Node node = children.get(i);
                if (node instanceof Pane) {
                    Pane pane = (Pane) node;
                    // Kiểm tra điều kiện để xác định Pane có dữ liệu
                    if (pane.getChildren().size() > 0) {
                        // Lấy dữ liệu từ các thành phần con trong Pane
                        TextArea textArea = (TextArea) pane.getChildren().get(2);
                        ComboBox<String> comboBox = (ComboBox<String>) pane.getChildren().get(3);
                        // Tiếp tục lấy dữ liệu từ các thành phần khác nếu cần

                        // Sử dụng dữ liệu đã lấy để thực hiện các tác vụ khác
                        String choiceText = textArea.getText();
                        String grade = comboBox.getValue();
                        byte[] image=imageData1.get(i+1);
                        saveChoice(questions, choiceText, grade,image,i);
                        // Tiếp tục xử lý dữ liệu lấy được
                    }
                }
            }
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
    private AnchorPane anchorPane;
    private boolean addMoreChoicesEnabled = true;

    @FXML
    private void addMoreChoices() {
        anchorPane.setPrefHeight(2200);
        vbox.setPrefHeight(1250);
        if (addMoreChoicesEnabled) {
            // Thực hiện hành động
            for (int i = 3; i < 6; i++) {
                Pane newPane = clonePane(originalPane, i);
                int lastIndex = vbox.getChildren().size(); // Lấy kích thước hiện tại của VBox
                vbox.getChildren().add(lastIndex-1 , newPane); // Thêm newPane vào trước vị trí cuối cùng
            }

            // Vô hiệu hóa sự kiện addMoreChoices()
            addMoreChoicesEnabled = false;
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
        textArea.setWrapText(true);
        textArea.setLayoutX(62);
        textArea.setLayoutY(11);
        textArea.setPrefSize(198, 68);

        ComboBox<String> comboBox = new ComboBox<>();
        comboBox.setLayoutX(62);
        comboBox.setLayoutY(93);
        comboBox.getItems().addAll("None", "100%", "90%","83,33333%","80%","75%","70%","66.66667%","60%","50%","40%","33.33333%","30%","25%","20%","16.66667%","14.28571%","12.5%","11.11111%","10%","5%","-5%","-10%","-11.11111%","-12.5%","-14.28571%","-16.66667%","-20%","-25%","-30%","-33.33333%","-40%","-50%","-60%","-66.66667%","-70%","-75%","-80%","-83,33333%");
comboBox.setValue("None");
        Button button = new Button("Image");
        button.setLayoutX(12);
        button.setLayoutY(136);

        ImageView imageView = new ImageView();
        imageView.setLayoutX(72);
        imageView.setLayoutY(136);
        imageView.setFitWidth(103); // Đặt chiều rộng của ImageView
        imageView.setFitHeight(50); // Đặt chiều cao của ImageView
        button.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                FileChooser fileChooser = new FileChooser();
                FileChooser.ExtensionFilter imageFilter = new FileChooser.ExtensionFilter("Image Files", "*.png", "*.jpg", "*.jpeg", "*.gif");
                fileChooser.getExtensionFilters().add(imageFilter);
                File selectedFile = fileChooser.showOpenDialog(null);
                if (selectedFile != null) {
                    try {
                        imageData1.put(i,readImageAsByteArray(selectedFile));
                        Image image = new Image(new FileInputStream(selectedFile));
                        imageView.setImage(image);
                        imageView.setPreserveRatio(true);
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
        comboboxchoice1.getItems().addAll("None","100%","90%","83,33333%","80%","75%","70%","66.66667%","60%","50%","40%","33.33333%","30%","25%","20%","16.66667%","14.28571%","12.5%","11.11111%","10%","5%","-5%","-10%","-11.11111%","-12.5%","-14.28571%","-16.66667%","-20%","-25%","-30%","-33.33333%","-40%","-50%","-60%","-66.66667%","-70%","-75%","-80%","-83,33333%");

        comboboxchoice2.getItems().addAll("None","100%","90%","83,33333%","80%","75%","70%","66.66667%","60%","50%","40%","33.33333%","30%","25%","20%","16.66667%","14.28571%","12.5%","11.11111%","10%","5%","-5%","-10%","-11.11111%","-12.5%","-14.28571%","-16.66667%","-20%","-25%","-30%","-33.33333%","-40%","-50%","-60%","-66.66667%","-70%","-75%","-80%","-83,33333%");

        List<Categories> listcate = CategoriesDao.getInstance().selectALl();
        for (int i = 0; i < listcate.size(); i++) {
            if (listcate.get(i).getCategories_parent()!=null){
                for(int j=0;j<i;j++){
                    if(listcate.get(i).getCategories_parent()==listcate.get(j)){
                        Categories categories1=listcate.get(i);
                        listcate.remove(i);
                        listcate.add(j+1,categories1);
                    }
                }
            }
        }
        ObservableList<String> list = FXCollections.observableArrayList();
        for (Categories categories : listcate) {
            int j=CategoriesDao.getInstance().CountQuestion(categories.getCategoryName());
            if(categories.getCategories_parent()!=null){
                String textcate = null;
                for(String list1 : list){
                    if(list1.trim().startsWith(categories.getCategories_parent().getCategoryName())){
                        textcate=list1;
                        break;
                    }
                }
                int count=0;
                while (textcate.charAt(count)==' ') {
                    count++;
                }
                String whitespace=textcate.substring(0,count);

                if(j!=0) {
                    list.add(whitespace+"   "+categories.getCategoryName()+'('+j+')');}
                else list.add(whitespace+"   "+categories.getCategoryName());
            }
            else {
                if(j!=0)
                    list.add(categories.getCategoryName() + '(' + j + ')');
                else list.add(categories.getCategoryName());
            }
        }
        choicetext1.setWrapText(true);
        choicetext2.setWrapText(true);

        combobox.setItems(list);
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
