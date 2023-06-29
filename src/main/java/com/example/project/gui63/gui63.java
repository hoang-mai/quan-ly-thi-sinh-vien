package com.example.project.gui63;

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
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

public class gui63 implements Initializable {
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
            Stage ag0r1 = (Stage) ((Node) event.getSource()).getScene().getWindow();
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/project/gui61/gui(6.1).fxml"));
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
        List<Categories> listcate = CategoriesDao.getInstance().selectALl();
        ObservableList<String> list = FXCollections.observableArrayList();
        for (Categories categories : listcate) {
            list.add(categories.getCategoryName());
        }
        combobox.setItems(list);

        combobox.getSelectionModel().selectedItemProperty().addListener((observableValue, oldvalue, newvalue) -> {
            vBox.getChildren().clear();
            List<Questions> danhsachquestion = CategoriesDao.getInstance().selectQuestion(combobox.getValue());
            for (int i = 0; i < danhsachquestion.size(); i++) {
                CheckBox checkBox = new CheckBox(danhsachquestion.get(i).getQuestionName());
                vBox.getChildren().add(checkBox);
            }
        });
    }
}
