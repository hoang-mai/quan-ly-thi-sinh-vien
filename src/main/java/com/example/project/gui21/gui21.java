package com.example.project.gui21;

import com.example.project.database.dao.CategoriesDao;
import com.example.project.database.entities.Categories;
import com.example.project.database.entities.Questions;
import com.example.project.gui32.gui32;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.scene.Node;

import java.net.URL;
import java.sql.*;
import java.util.List;
import java.util.ResourceBundle;

public class gui21 implements Initializable {
    @FXML
    private static ComboBox<String> combobox;

    @FXML
    private AnchorPane anchorpane1;
    @FXML
    private ScrollPane scrollpane1;
    @FXML
    private CheckBox alsoquestion1;
    private Connection connection;


    @FXML
    void Category(ActionEvent event) {
        try {
            Stage ag0r1 = (Stage) ((Node) event.getSource()).getScene().getWindow();
            Parent root = FXMLLoader.load(getClass().getResource("/com/example/project/gui33/gui(3.3).fxml"));
            Scene scene = new Scene(root);
            ag0r1.setScene(scene);
            ag0r1.show();

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    @FXML
    void Import(ActionEvent event) {
        try {
            Stage ag0r1 = (Stage) ((Node) event.getSource()).getScene().getWindow();
            Parent root = FXMLLoader.load(getClass().getResource("/com/example/project/gui34/gui(3.4).fxml"));
            Scene scene = new Scene(root);
            ag0r1.setScene(scene);
            ag0r1.show();

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    @FXML
    void alsoquestion(ActionEvent event) {
        if (alsoquestion1.isSelected()) {
            scrollpane1.setVisible(true);
            anchorpane1.setVisible(true);
        } else {
            scrollpane1.setVisible(false);
            anchorpane1.setVisible(false);
        }
    }

    @FXML
    void Edit1(ActionEvent event) {
        try {
            Stage ag0r1 = (Stage) ((Node) event.getSource()).getScene().getWindow();
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/project/gui32/gui(3.2).fxml"));
            Parent root = loader.load();
            Scene scene = new Scene(root);
            ag0r1.setScene(scene);
            ag0r1.show();
            gui32 controller = loader.getController();
            controller.setaddingamultipe("Editing Multiple choice question");

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    @FXML
    void createquestion(ActionEvent event) {
        try {
            Stage ag0r1 = (Stage) ((Node) event.getSource()).getScene().getWindow();
            Parent root = FXMLLoader.load(getClass().getResource("/com/example/project/gui32/gui(3.2).fxml"));
            Scene scene = new Scene(root);
            ag0r1.setScene(scene);
            ag0r1.show();

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    @Override
    public void initialize(URL url1, ResourceBundle resourceBundle) {

    }
    //gán giá trị cho combo box
    public void setcombobox() {
        List<Categories> danhsachcategories= CategoriesDao.getInstance().selectALl();
        ObservableList<String> list = FXCollections.observableArrayList();
        for(Categories categories : danhsachcategories){
            list.add(categories.getCategoryName());
        }
        combobox.setItems(list);
    }
    //in ra list câu hỏi trong category

}
