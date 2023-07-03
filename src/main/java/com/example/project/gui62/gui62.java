package com.example.project.gui62;

import com.example.project.database.dao.QuizDao;
import com.example.project.database.entities.Questions;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

public class gui62 implements Initializable {
    @FXML
    private AnchorPane anchorpane1;

    @FXML
    private Label countquestion;

    @FXML
    private Label editquiz;

    @FXML
    private ImageView hoicham1;

    @FXML
    private Label total;

    @FXML
    private VBox vbox1;

    @FXML
    void repaginate(ActionEvent event) {

    }

    @FXML
    void selectmultipleitems(ActionEvent event) {

    }

    @FXML
    void anewquestion(ActionEvent event) {
        try {
            Stage ag0r1 = (Stage) ((Node) event.getSource()).getScene().getWindow();
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/project/gui63/gui(6.3).fxml"));
            Parent root = loader.load();
            Scene scene = new Scene(root);
            ag0r1.setScene(scene);
            ag0r1.show();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    @FXML
    void arandomquestion(ActionEvent event) {

    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        Text text = new Text();
        editquiz.setGraphic(text);
        text.setText("Editing quiz: "+ QuizDao.getInstance().getQuiz().getQuizName());
        hoicham1.setX(34+text.getLayoutBounds().getWidth());
List<Questions> listquestion=QuizDao.getInstance().selectQuestion(QuizDao.getInstance().getQuiz().getQuizName());
countquestion.setText("Question: "+listquestion.size());
total.setText("Total of  marks: "+listquestion.size()+".00");
for(Questions questions : listquestion){


}
    }
}
