package com.example.project.gui63;

import com.example.project.database.dao.CategoriesDao;
import com.example.project.database.dao.QuestionsDao;
import com.example.project.database.dao.QuizDao;
import com.example.project.database.entities.Categories;
import com.example.project.database.entities.Questions;
import com.example.project.database.entities.Quiz;
import com.example.project.gui32.gui32;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.net.URL;
import java.util.HashSet;
import java.util.List;
import java.util.ResourceBundle;
import java.util.Set;

public class gui63 implements Initializable {
    @FXML
    private AnchorPane anchorpane;

    @FXML
    private ComboBox<String> combobox;
    @FXML
    private CheckBox alsoshowquestion1;

    private VBox vBox=new VBox();
    @FXML
    void alsoshowquestion(ActionEvent event) {
        if (alsoshowquestion1.isSelected()) {
            vBox.setVisible(true);

        } else {
            vBox.setVisible(false);
        }
    }

    @FXML
    void addselectedquestiontothequiz(ActionEvent event) {
        try {
            for (Node node : vBox.getChildren()) {
                if (node instanceof CheckBox) {
                    CheckBox checkBox = (CheckBox) node;
                    if(checkBox.isSelected()) {
                        // chọn ra đối tượng question tương ứng với checkbox đó
                        Questions questions = QuestionsDao.selectQuestionbyName(checkBox.getText());
                        // thêm vào list question của quiz
                        Set<Quiz> quiz = new HashSet<Quiz>();
                        quiz.add(QuizDao.getInstance().selectByName(QuizDao.getInstance().getQuiz().getQuizName()));
                        questions.setQuiz(quiz);
                        // thêm vào database
                        QuestionsDao.getInstance().update(questions);
                    }
                }
            }
            Stage ag0r1 = (Stage) ((Node) event.getSource()).getScene().getWindow();
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/project/gui62/gui(6.2).fxml"));
            Parent root = loader.load();
            Scene scene = new Scene(root);
            ag0r1.setScene(scene);
            ag0r1.show();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        anchorpane.getChildren().add(vBox);
        vBox.setLayoutX(27);
        vBox.setLayoutY(235);
        vBox.setSpacing(10);
        vBox.setPrefWidth(512);
        List<Categories> listcate = CategoriesDao.getInstance().selectALl();
        ObservableList<String> list = FXCollections.observableArrayList();
        for (Categories categories : listcate) {
            list.add(categories.getCategoryName());
        }
        combobox.setItems(list);

        combobox.getSelectionModel().selectedItemProperty().addListener((observableValue, oldvalue, newvalue) -> {
            vBox.getChildren().clear();
            List<Questions> danhsachquestion = CategoriesDao.getInstance().selectQuestion(combobox.getValue());
            anchorpane.setPrefHeight(235+40*danhsachquestion.size());
            for (int i = 0; i < danhsachquestion.size(); i++) {
                CheckBox checkBox = new CheckBox(danhsachquestion.get(i).getQuestionName());
                vBox.getChildren().add(checkBox);
            }
        });
    }
}
