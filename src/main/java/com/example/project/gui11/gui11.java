package com.example.project.gui11;

import com.example.project.database.dao.QuizDao;
import com.example.project.database.entities.Quiz;
import javafx.fxml.FXML;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.Stage;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;


public class gui11 implements Initializable {


    @FXML
    private Label it;
    @FXML
    void kiemtracuoikimonit1(ActionEvent event) {
        try {
            Stage ag0r1 = (Stage) ((Node) event.getSource()).getScene().getWindow();
            Parent root = FXMLLoader.load(getClass().getResource("/com/example/project/gui61/gui(6.1).fxml"));
            Scene scene = new Scene(root);
            ag0r1.setScene(scene);
            ag0r1.show();

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
    @FXML
    private VBox vbox1;

    @FXML
    void turneditingon(ActionEvent event) {
        try {

            Parent root = FXMLLoader.load(getClass().getResource("/com/example/project/gui51/gui(5.1).fxml"));
            Scene scene = new Scene(root);
            Stage ag0r = new Stage();
            ag0r.setScene(scene);
            ag0r.show();
            Stage a = (Stage) it.getScene().getWindow();
            a.hide();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    @FXML
    void handle(ActionEvent event) {
        Pane pane=new Pane();
        pane.setPrefSize(600,300);
        Button button=new Button("Question");
        Button button1=new Button("Categories");
        Button button2=new Button("Import");
        Button button3=new Button("Export");
        VBox vBox=new VBox();
        vBox.setLayoutY(30);
        vBox.setLayoutX(429);
        vBox.getChildren().addAll(button,button1,button2,button3);
        vBox.setAlignment(Pos.CENTER);
        vBox.setSpacing(10);
        Label label =new Label("Question bank");
        label.setLayoutX(43);
        label.setLayoutY(30);
        label.setFont(Font.font("Arial", 16));
        pane.getChildren().addAll(label,vBox);
        Scene scenepane=new Scene(pane);
        Stage stage=new Stage();
        stage.setScene(scenepane);
        stage.show();
        button2.setOnAction(actionEvent -> {
            try {

                Parent root = FXMLLoader.load(getClass().getResource("/com/example/project/gui34/gui(3.4).fxml"));
                Scene scene = new Scene(root);
                Stage ag0r1=new Stage();
                ag0r1.setScene(scene);
                ag0r1.show();
                stage.hide();
                Stage a = (Stage) it.getScene().getWindow();
                a.hide();
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        });
        button1.setOnAction(actionEvent -> {
            try {

                Parent root = FXMLLoader.load(getClass().getResource("/com/example/project/gui34/gui(3.4).fxml"));
                Scene scene = new Scene(root);
                Stage ag0r1=new Stage();
                ag0r1.setScene(scene);
                ag0r1.show();
                stage.hide();
                Stage a = (Stage) it.getScene().getWindow();
                a.hide();
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        });
        button.setOnAction(actionEvent -> {
            try {
                Parent root = FXMLLoader.load(getClass().getResource("/com/example/project/gui21/gui(2.1).fxml"));
                Scene scene = new Scene(root);
                Stage ag0r1=new Stage();
                ag0r1.setScene(scene);
                ag0r1.show();
                stage.hide();
                Stage a = (Stage) it.getScene().getWindow();
                a.hide();
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        });
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        List<Quiz> danhsachquiz= QuizDao.getInstance().selectALl();
for(Quiz quiz : danhsachquiz){
    Button button=new Button(quiz.getQuizName());
    vbox1.getChildren().add(button);
}
    }
}





    


