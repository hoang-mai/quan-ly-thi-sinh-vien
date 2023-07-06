package com.example.project.gui71;

import com.example.project.database.dao.QuizDao;
import com.example.project.database.entities.Questions;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.*;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

public class gui71 implements Initializable {
    @FXML
    private Button quizname;
    @FXML
    private Button buttoneditquiz;

    @FXML
    private Label daugach;
    @FXML
    private Button one1;

    @FXML
    private AnchorPane anchorpane;

    @FXML
    private ScrollPane scrollpane;
    private GridPane gridPane=new GridPane();

    @FXML
    private Label timeleft;
    private String formatTime(int timeInSeconds) {
        int hours = timeInSeconds / 3600;
        int minutes = (timeInSeconds % 3600) / 60;
        int seconds = timeInSeconds % 60;
        return String.format("Time left %02d:%02d:%02d", hours, minutes, seconds);
    }
    @FXML
    void finishattempt(ActionEvent event) {
        Dialog<Void> dialog=new Dialog<>();
        dialog.setContentText("Do you want to finish ?");
        ButtonType buttonType=new ButtonType("FINISH", ButtonBar.ButtonData.FINISH);
        ButtonType buttonType1=new ButtonType("NO", ButtonBar.ButtonData.NO);
        dialog.getDialogPane().getButtonTypes().addAll(buttonType1,buttonType);
        dialog.setResultConverter(buttontype->{
            if(buttontype==buttonType){
                try {
                    FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/project/gui61/gui(6.1).fxml"));
                    Parent root = loader.load();
                    Scene scene = new Scene(root);
                    Stage ag0r = new Stage();
                    ag0r.setScene(scene);
                    ag0r.show();
                    Stage a = (Stage) quizname.getScene().getWindow();
                    a.hide();
                } catch (Exception e) {
                    System.out.println(e.getMessage());
                }
            } else if (buttontype==buttonType1) {
                dialog.close();
            }
            return null;
        });
        dialog.show();
    }
    private  int timeRemaining=QuizDao.getInstance().getQuiz().getTimeLimit()*60;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        Text text = new Text();
        quizname.setText(QuizDao.getInstance().getQuiz().getQuizName());
        text.setText(QuizDao.getInstance().getQuiz().getQuizName());
        daugach.setLayoutX(quizname.getLayoutX()+text.getLayoutBounds().getWidth()+10);
        buttoneditquiz.setLayoutX(quizname.getLayoutX()+6+text.getLayoutBounds().getWidth()+4);
        List<Questions> listquestion=QuizDao.getInstance().selectQuestion(QuizDao.getInstance().getQuiz().getQuizName());
        gridPane.setPrefWidth(618);
        ColumnConstraints column1 = new ColumnConstraints();
        column1.setPrefWidth(142);
        ColumnConstraints column2 = new ColumnConstraints();
        column2.setPrefWidth(476);
        gridPane.getColumnConstraints().addAll(column1, column2);
        anchorpane.getChildren().add(gridPane);
        gridPane.setLayoutY(56);
        gridPane.setLayoutX(0);
        gridPane.setVgap(10);
        gridPane.setPadding(new Insets(0, 0, 0, 10));

        for (int i=1;i<=listquestion.size();i++){
            VBox vBox=new VBox();
            vBox.setSpacing(5);
            Label label=new Label("Question"+i);
            Label label1=new Label("Not yet"+"\n"+"answered");
            Label label2=new Label("Marked out of"+"\n"+"1.00");
            Label label3=new Label("Flag question");
            vBox.getChildren().addAll(label,label1,label2,label3);
            gridPane.add(vBox,0,i);
        }
        timeleft.setText(formatTime(timeRemaining));
        Timeline timeline = new Timeline();
        timeline.setCycleCount(Timeline.INDEFINITE);
        timeline.getKeyFrames().add(
                new KeyFrame(Duration.seconds(1), new EventHandler<ActionEvent>() {
                    @Override
                    public void handle(ActionEvent event) {
                        timeRemaining--;
                        timeleft.setText(formatTime(timeRemaining));
                        if (timeRemaining<= 0) {
                            timeline.stop();
                        }
                    }
                })
        );
        timeline.play();

    }
}
