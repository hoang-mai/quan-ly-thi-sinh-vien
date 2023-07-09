package com.example.project.gui74;

import com.example.project.database.dao.QuestionsDao;
import com.example.project.database.dao.QuizDao;
import com.example.project.database.entities.Choice;
import com.example.project.database.entities.Questions;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
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
import java.time.Duration;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.ResourceBundle;

public class gui74 implements Initializable {
    public  void finsh(String finish, LocalDateTime dateTime,double diem){
        List<Questions> listquestion=QuizDao.getInstance().selectQuestion(QuizDao.getInstance().getQuiz().getQuizName());
        complete.setText(finish);
        Duration duration= Duration.between(QuestionsDao.getInstance().getDuring(), dateTime);
        long hours = duration.toHours();
        long minutes = duration.toMinutesPart();
        long secon=duration.getSeconds();
        time.setText(hours+" hour "+minutes+" min "+secon+" secs ");
        String fom=String.format("%.2f",diem);
        mark.setText(fom+"/"+listquestion.size()+".00");
        String foma=String.format("%.2f",diem/listquestion.size()*10);
        grade.setText(foma+" out of 10.00 ("+foma+"%)");
    }

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


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        start.setText(QuestionsDao.getInstance().getTime());
        Map<Integer,String> listan=QuestionsDao.getInstance().getAnswer();
        Text text = new Text();
        quizname.setText(QuizDao.getInstance().getQuiz().getQuizName());
        text.setText(QuizDao.getInstance().getQuiz().getQuizName());
        daugach.setLayoutX(quizname.getLayoutX()+text.getLayoutBounds().getWidth()+10);
        buttoneditquiz.setLayoutX(quizname.getLayoutX()+6+text.getLayoutBounds().getWidth()+4);
        GridPane gridPane =QuestionsDao.getInstance().getGridPane();
        anchorpane.getChildren().add(gridPane);
        gridPane.setLayoutY(132);
        GridPane gridPane1 = QuestionsDao.getInstance().getGridPane1();
        panecountquiz.getChildren().add(gridPane1);
        ObservableList<Node> panelist=gridPane1.getChildren();
        for(Node node :panelist){
            if(node instanceof  Pane){
                Pane pane = (Pane) node;
                Pane pane1=(Pane) pane.getChildren().get(0);
                pane1.getStyleClass().add("pane-background-question-number");
            }
        }
        ObservableList<Node> gripanelist = FXCollections.observableArrayList();
        for(int i=1;i<gridPane.getChildren().size();i+=2){
            gripanelist.add(gridPane.getChildren().get(i));
        }
        for(int i=0;i<gripanelist.size();i++){
            if(gripanelist.get(i) instanceof GridPane) {
                GridPane gridPane2 = (GridPane) gripanelist.get(i);
                VBox vBox = (VBox) gridPane2.getChildren().get(0);

                for (int j = 1; j < vBox.getChildren().size(); j++) {
                    vBox.getChildren().get(j).setDisable(true);
                }
                Label label = new Label(listan.get(i+1));
                label.getStyleClass().add("pane-background-question");
                label.setPrefWidth(480);

                label.setWrapText(true);
                gridPane2.setVgap(10);
                gridPane2.add(label,0,1);

            }
        }


        anchorpane.setPrefHeight(10000);
    }
}
