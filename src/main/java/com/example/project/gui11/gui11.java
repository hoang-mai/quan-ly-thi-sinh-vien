package com.example.project.gui11;

import com.example.project.database.dao.QuizDao;
import com.example.project.database.entities.Quiz;

import com.example.project.popup.popup;
import javafx.fxml.FXML;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
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
            Parent root = FXMLLoader.load(getClass().getResource("/com/example/project/gui61/gui(6.1).fxml"));
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
    void caidat(ActionEvent event) {
        try {
            Stage a = (Stage) it.getScene().getWindow();
            FXMLLoader loader= new FXMLLoader(getClass().getResource("/com/example/project/popup/popup.fxml"));
            Parent root = loader.load();
            Scene scene = new Scene(root);
            Stage ag0r1=new Stage();
            ag0r1.setScene(scene);
            ag0r1.initModality(Modality.APPLICATION_MODAL);
            ag0r1.initOwner(a);
            ag0r1.showAndWait();
            popup controller =loader.getController();
           if(controller.b==1) a.hide();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

    }
void quiz(ActionEvent event){
    try {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/project/gui61/gui(6.1).fxml"));
        Parent root = loader.load();
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
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        List<Quiz> listquiz=QuizDao.getInstance().selectALl();
        for (Quiz quiz : listquiz) {
            Image image = new Image(getClass().getResourceAsStream("/com/example/project/ImageView/select.png"));
            ImageView imageView = new ImageView(image);
            Button button = new Button(quiz.getQuizName());
            imageView.setFitWidth(15);
            imageView.setFitHeight(18);
button.setGraphic(imageView);
            button.getStyleClass().add("button-text-red");
            vbox1.getChildren().add(button);
            button.setOnAction(event -> {
                QuizDao.getInstance().setQuiz(quiz);
                quiz(event);

            });
        }
    }
}





    


