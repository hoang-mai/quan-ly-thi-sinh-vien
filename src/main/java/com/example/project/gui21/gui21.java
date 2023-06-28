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
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import javafx.scene.Node;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

public class gui21 implements Initializable {

    private GridPane gridpane1=new GridPane();

    @FXML
    private ComboBox<String> combobox;
    @FXML
    private AnchorPane anchorpane1;
    @FXML
    private ScrollPane scrollpane1;
    @FXML
    private CheckBox alsoquestion1;



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
        gridpane1.setPrefWidth(500);
        gridpane1.setVgap(10);
        anchorpane1.getChildren().add(gridpane1);
        gridpane1.setLayoutX(14);
        gridpane1.setLayoutY(46);
        ColumnConstraints column1 = new ColumnConstraints();
        column1.setPrefWidth(445);
        ColumnConstraints column2 = new ColumnConstraints();
        column2.setPrefWidth(55);
        gridpane1.getColumnConstraints().addAll(column1, column2);
List<Categories> listcate=CategoriesDao.getInstance().selectALl();
        ObservableList<String> list=FXCollections.observableArrayList();
for(Categories categories : listcate){
   list.add(categories.getCategoryName());
}
        combobox.setItems(list);

combobox.getSelectionModel().selectedItemProperty().addListener((observableValue, oldvalue, newvalue) ->{
    gridpane1.getChildren().clear();
    List<Questions> danhsachquiz=CategoriesDao.getInstance().selectQuestion(combobox.getValue());
    for(int i=0;i<danhsachquiz.size();i++){
        CheckBox checkBox=new CheckBox(danhsachquiz.get(i).getQuestionName());
        gridpane1.add(checkBox,0,i);
        Button button=new Button("Edit");
        gridpane1.add(button,1,i);
        button.setOnAction(event2 -> {
            try {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/project/gui32/gui(3.2).fxml"));
                Parent root = loader.load();
                Scene scene = new Scene(root);
                Stage ag0r1 = (Stage) ((Node) event2.getSource()).getScene().getWindow();
                ag0r1.setScene(scene);
                ag0r1.show();
                Stage stage=(Stage) anchorpane1.getScene().getWindow();
                stage.close();
                gui32 controller = loader.getController();
                controller.setaddingamultipe("Editing Multiple choice question");



            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        });
    }
} );



    }
}
