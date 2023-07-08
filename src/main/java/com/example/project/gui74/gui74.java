package com.example.project.gui74;

import com.example.project.database.dao.QuestionsDao;
import com.example.project.database.dao.QuizDao;
import com.example.project.database.entities.Choice;
import com.example.project.database.entities.Questions;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.io.ByteArrayInputStream;
import java.net.URL;
import java.util.List;
import java.util.Objects;
import java.util.ResourceBundle;

public class gui74 implements Initializable {

    @FXML
    private AnchorPane anchorpane;

    @FXML
    private Button buttoneditquiz;

    @FXML
    private Label complete;

    @FXML
    private Label daugach;

    @FXML
    private Label grade;

    @FXML
    private Label it;

    @FXML
    private Label mark;

    @FXML
    private Pane panecountquiz;

    @FXML
    private Button quizname;

    @FXML
    private ScrollPane scrollpane;

    @FXML
    private Label start;

    @FXML
    private Label state;

    @FXML
    private Label time;
    @FXML
    void finishreview(ActionEvent event) {
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
    private GridPane gridPane=new GridPane();
    private GridPane gridPane1=new GridPane();
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        Text text = new Text();
        quizname.setText(QuizDao.getInstance().getQuiz().getQuizName());
        text.setText(QuizDao.getInstance().getQuiz().getQuizName());
        daugach.setLayoutX(quizname.getLayoutX()+text.getLayoutBounds().getWidth()+10);
        buttoneditquiz.setLayoutX(quizname.getLayoutX()+6+text.getLayoutBounds().getWidth()+4);
        List<Questions> listquestion=QuizDao.getInstance().selectQuestion(QuizDao.getInstance().getQuiz().getQuizName());
        gridPane.setPrefWidth(600);
        ColumnConstraints column1 = new ColumnConstraints();
        column1.setPrefWidth(125);
        ColumnConstraints column2 = new ColumnConstraints();
        column2.setPrefWidth(475);
        gridPane.getColumnConstraints().addAll(column1, column2);
        anchorpane.getChildren().add(gridPane);
        gridPane.setLayoutY(56);
        gridPane.setLayoutX(0);
        gridPane.setVgap(10);
        gridPane.setPadding(new Insets(0, 0, 0, 10));
        int j=0;
        int k=0;
        panecountquiz.getChildren().add(gridPane1);
        gridPane1.setPrefWidth(196);
        gridPane1.setLayoutY(96);

        ColumnConstraints column3 = new ColumnConstraints();
        column3.setPrefWidth(24);
        ColumnConstraints column4 = new ColumnConstraints();
        column4.setPrefWidth(24);
        ColumnConstraints column5 = new ColumnConstraints();
        column5.setPrefWidth(25);
        ColumnConstraints column6 = new ColumnConstraints();
        column6.setPrefWidth(24);
        ColumnConstraints column7 = new ColumnConstraints();
        column7.setPrefWidth(25);
        ColumnConstraints column8 = new ColumnConstraints();
        column8.setPrefWidth(24);
        ColumnConstraints column9 = new ColumnConstraints();
        column9.setPrefWidth(25);
        ColumnConstraints column10 = new ColumnConstraints();
        column10.setPrefWidth(25);
        gridPane1.getColumnConstraints().addAll(column3,column4,column5,column6,column7,column8,column9,column10);
        gridPane1.setHgap(5);
        gridPane1.setVgap(5);

        for (int i=1;i<=listquestion.size();i++){
            Pane pane=new Pane();
            VBox vBox=new VBox();
            vBox.setSpacing(5);
            vBox.setPadding(new Insets(10,10,10,10));
            Label label=new Label("Question "+i);
            label.setTextFill(Color.RED);
            Label label1=new Label("Not yet"+"\n"+"answered");
            Label label2=new Label("Marked out of"+"\n"+"1.00");
            Label label3=new Label("Flag question");
            Image image1111 = new Image(getClass().getResourceAsStream("/com/example/project/ImageView/flag.png"));
            ImageView imageView1 = new ImageView(image1111);
            imageView1.setFitWidth(15);
            imageView1.setFitHeight(18);
            label3.setGraphic(imageView1);
            vBox.getChildren().addAll(label,label1,label2,label3);
            vBox.getStyleClass().add("pane-border-question");
            pane.getChildren().add(vBox);
            Pane pane1 =new Pane();
            pane1.getStyleClass().add("pane-border-question-number");
            Pane pane2=new Pane();
            pane1.getChildren().add(pane2);
            pane1.setPrefHeight(30);
            pane1.setPrefWidth(24);
            pane2.setPrefSize(19,15);
            Label label5=new Label(""+i);
            pane1.getChildren().add(label5);
            label5.setLayoutY(1);
            label5.setLayoutX(5);
            pane2.setLayoutY(15);
            pane2.setLayoutX(0);
            gridPane1.add(pane1,j,k);
            j++;
            if(j==8){
                k++;
                j=0;
            }

            VBox vBox1=new VBox();
            vBox1.setSpacing(5);
            vBox1.setPadding(new Insets(10,10,10,10));
            Label label4=new Label();
            label4.setWrapText(true);
            label4.setPrefWidth(485);
            label4.setText(listquestion.get(i-1).getQuestionText());
            vBox1.getChildren().add(label4);
            if(listquestion.get(i-1).getImage()!=null){
                byte[] image =listquestion.get(i-1).getImage();
                Image image1=new Image(new ByteArrayInputStream(image));
                ImageView imageView=new ImageView(image1);
                vBox1.getChildren().add(imageView);
                vBox1.setMargin(imageView,new Insets(10,10,20,10));
            }else {vBox1.setMargin(label4,new Insets(10,10,20,10));}
            List<Choice> listchoice= QuestionsDao.getInstance().selectChoicebyQuestionId(listquestion.get(i-1).getQuestionId());
            boolean a=true;
            for(Choice choice :listchoice){
                if(Objects.equals(choice.getGrade(), "100") || Objects.equals(choice.getGrade(), "0"))
                    a=true;
                else {a=false;
                    break;}
            }
            if (a) {
                ToggleGroup toggleGroup=new ToggleGroup();
                for(Choice choice :listchoice){
                    RadioButton radioButton=new RadioButton(choice.getChoiceText());
                    radioButton.setWrapText(true);
                    radioButton.setToggleGroup(toggleGroup);
                    vBox1.getChildren().add(radioButton);
                    if(choice.getImage()!=null){
                        byte[] image11 =choice.getImage();
                        Image image111=new Image(new ByteArrayInputStream(image11));
                        ImageView imageView1111=new ImageView(image111);
                        vBox1.getChildren().add(imageView1111);}
                }
                toggleGroup.selectedToggleProperty().addListener((observable, oldValue, newValue)->{
                    if(newValue.isSelected()){
                        pane2.getStyleClass().add("pane-background-question-number");}

                });}
            else {
                for(Choice choice :listchoice){
                    CheckBox checkBox=new CheckBox(choice.getChoiceText());
                    checkBox.setWrapText(true);
                    vBox1.getChildren().add(checkBox);
                    if(choice.getImage()!=null){
                        byte[] image11 =choice.getImage();
                        Image image111=new Image(new ByteArrayInputStream(image11));
                        ImageView imageView1111=new ImageView(image111);
                        vBox1.getChildren().add(imageView1111);}
                    checkBox.selectedProperty().addListener((observable, oldValue, newValue)->{
                        if(checkBox.isSelected()){
                            pane2.getStyleClass().add("pane-background-question-number");
                        }
                    });
                }
            }
            vBox1.getStyleClass().add("pane-background-question");
            gridPane.add(pane,0,i);
            gridPane.add(vBox1,1,i);

        }
        anchorpane.setPrefHeight(10000);
    }
}
