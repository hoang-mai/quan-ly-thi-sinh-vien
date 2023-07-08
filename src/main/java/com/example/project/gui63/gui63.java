package com.example.project.gui63;

import com.example.project.database.dao.CategoriesDao;
import com.example.project.database.dao.QuestionsDao;
import com.example.project.database.dao.QuizDao;
import com.example.project.database.entities.Categories;
import com.example.project.database.entities.Questions;
import com.example.project.database.entities.Quiz;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.net.URL;
import java.util.*;

public class gui63 implements Initializable {
    @FXML
    private AnchorPane anchorpane;

    @FXML
    private ComboBox<String> combobox;
    @FXML
    private CheckBox alsoshowquestion1;

    private VBox vBox=new VBox();

    @FXML
    void changecombobox(ActionEvent event) {
        String cate=combobox.getValue().trim();
        int mongoac=cate.length();
        if(cate.contains("(")){
            mongoac=cate.indexOf("(");}
        List<Questions> danhsachquestion=new ArrayList<>();
        if(alsoshowquestion1.isSelected()){
            danhsachquestion = CategoriesDao.getInstance().selectQuestionfromSubCategory(cate.substring(0,mongoac));
        }
        else  {
            danhsachquestion = CategoriesDao.getInstance().selectQuestion(cate.substring(0,mongoac));
        }
        vBox.getChildren().clear();
        anchorpane.setPrefHeight(235+30*danhsachquestion.size());
        for (int i = 0; i < danhsachquestion.size(); i++) {
            CheckBox checkBox = new CheckBox(danhsachquestion.get(i).getQuestionName());
            vBox.getChildren().add(checkBox);
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
                if(CategoriesDao.getInstance().CountQuestion(categories.getCategoryName())!=0) {
                    list.add(whitespace+"   "+categories.getCategoryName()+'('+CategoriesDao.getInstance().CountQuestion(categories.getCategoryName())+')');}
                else list.add(whitespace+"   "+categories.getCategoryName());
            }
            else {
                if(CategoriesDao.getInstance().CountQuestion(categories.getCategoryName())!=0)
                    list.add(categories.getCategoryName() + '(' + CategoriesDao.getInstance().CountQuestion(categories.getCategoryName()) + ')');
                else list.add(categories.getCategoryName());
            }
        }

        combobox.setItems(list);
    }
}
