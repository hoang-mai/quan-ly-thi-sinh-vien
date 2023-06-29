package com.example.project.gui62;

import com.example.project.database.dao.QuizDao;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ResourceBundle;

public class gui62 implements Initializable {
    @FXML
    private ImageView hoicham1;
    @FXML
    private Label editquiz;

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

    }
}
