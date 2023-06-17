package com.example.project.gui33;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.stage.Stage;
import javafx.scene.Node;

public class gui33 implements Initializable {

    @FXML
    private ImageView canhbao1;

    @FXML
    private ImageView chamhoi2;

    @FXML
    private TextField idnumber1;

    @FXML
    private TextField name1;
    @FXML
    private TextArea categoryinfor1;

    @FXML
    void addcategory(ActionEvent event) {
        try {
            Stage ag0r = (Stage) ((Node) event.getSource()).getScene().getWindow();
            Parent root = FXMLLoader.load(getClass().getResource("/com/example/project/gui21/gui(2.1).fxml"));
            Scene scene = new Scene(root);
            ag0r.setScene(scene);

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
    @FXML
    private Button addcategory1;
    @FXML
    void categoryinfor(KeyEvent event) {
        if (event.getCode() == KeyCode.ENTER) {
            String content = categoryinfor1.getText();
            content += "\n"; // Thêm ký tự xuống dòng
            categoryinfor1.setText(content);
            categoryinfor1.positionCaret(content.length());
        }
    }
@FXML
void cancel(ActionEvent event){
    try {
        Stage ag0r = (Stage) ((Node) event.getSource()).getScene().getWindow();
        Parent root = FXMLLoader.load(getClass().getResource("/com/example/project/gui21/gui(2.1).fxml"));
        Scene scene = new Scene(root);
        ag0r.setScene(scene);

    } catch (Exception e) {
        System.out.println(e.getMessage());
    }
}
    @Override
    public void initialize(URL arg0, ResourceBundle arg1) {
        categoryinfor1.setWrapText(true);
        name1.textProperty().addListener((Observable, oldvalue, newValue) -> {
            if (newValue.isEmpty()) {
                canhbao1.setVisible(true);
                addcategory1.setDisable(true);
            } else {
                canhbao1.setVisible(false);
                addcategory1.setDisable(false);
            }
        });
        idnumber1.textProperty().addListener((Observable, oldvalue, newValue) -> {
            if (newValue.isEmpty()) {
                chamhoi2.setVisible(true);
            } else {
                chamhoi2.setVisible(false);
            }
        });
    }

}
