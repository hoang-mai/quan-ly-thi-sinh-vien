package com.example.project.gui65;

import com.example.project.database.dao.CategoriesDao;
import com.example.project.database.entities.Categories;
import com.example.project.database.entities.Questions;
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
import javafx.scene.control.Label;
import javafx.scene.control.Pagination;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class gui65 implements Initializable {
    @FXML
    private Pagination questionsmatching;


    @FXML
    private ComboBox<String> comboboxcategory;

    @FXML
    private ComboBox<Integer> comboboxrandomquestion;

    @FXML
    private CheckBox includequestions;
    @FXML
    void addrandomquestiontothequiz(ActionEvent event) {
        try {
            Stage ag0r1 = (Stage) ((Node) event.getSource()).getScene().getWindow();
            Parent root = FXMLLoader.load(getClass().getResource("/com/example/project/gui62/gui(6.2).fxml"));
            Scene scene = new Scene(root);
            ag0r1.setScene(scene);
            ag0r1.show();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
    @FXML
    void changecomboboxcategory(ActionEvent event) {
        String cate=comboboxcategory.getValue().trim();
        int mongoac=cate.length();
        if(cate.contains("(")){
            mongoac=cate.indexOf("(");}
        List<Questions> danhsachquestion=new ArrayList<>();
        if(includequestions.isSelected()){
            danhsachquestion = CategoriesDao.getInstance().selectQuestionfromSubCategory(cate.substring(0,mongoac));
        }
        else  {
            danhsachquestion = CategoriesDao.getInstance().selectQuestion(cate.substring(0,mongoac));
        }
        System.out.println(danhsachquestion.size());
        ObservableList<Integer> listcomboboxrandomquestion = FXCollections.observableArrayList();
        for(int i = 1; i<=danhsachquestion.size(); i++){
            listcomboboxrandomquestion.add(Integer.valueOf(i));
        }
        comboboxrandomquestion.setItems(listcomboboxrandomquestion);
        int pagecount =danhsachquestion.size()/10;
        if(danhsachquestion.size()%10!=0)pagecount++;
        questionsmatching.setPageCount(pagecount);
        List<Questions> finalDanhsachquestion = danhsachquestion;
        questionsmatching.setPageFactory(pageIndex -> {
            VBox page = new VBox(10);
            page.setSpacing(10);
            int startIndex = pageIndex * 10;
            int endIndex = Math.min(startIndex + 10, finalDanhsachquestion.size());

            for (int i = startIndex; i < endIndex; i++) {
                Label label = new Label(finalDanhsachquestion.get(i).getQuestionId()+": "+finalDanhsachquestion.get(i).getQuestionName());
                page.getChildren().add(label);
            }
            return page;
        });
    }
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        List<Categories> listcate = CategoriesDao.getInstance().selectALl();
        for (int i = 0; i < listcate.size(); i++) {
            if (CategoriesDao.getInstance().getChildCategories(listcate.get(i).getCategoryName()) != null) {
                List<Categories> categoriesList = CategoriesDao.getInstance().getChildCategories(listcate.get(i).getCategoryName());
                for (Categories categories1 : categoriesList) {
                    listcate.removeIf(categories2 -> categories2.getCategoryName().equals(categories1.getCategoryName()));
                    listcate.add(i+1,categories1);
                }

            }
        }
        ObservableList<String> list = FXCollections.observableArrayList();
        for (Categories categories : listcate) {
            if(categories.getCategories_parent()!=null){
                String textcate = null;
                for(String list1 : list){
                    if(list1.trim().startsWith(CategoriesDao.getInstance().selectCategoryparent(categories.getCategoryName()).getCategoryName())){
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
        comboboxcategory.setItems(list);
    }
}
