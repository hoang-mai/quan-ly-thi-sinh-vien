package com.example.project.gui34;


import java.io.File;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.input.DragEvent;
import javafx.scene.input.TransferMode;
import javafx.scene.layout.Pane;
import javafx.stage.FileChooser;

import javafx.scene.input.Dragboard;


public class gui34 {
    File file = new File("");
    @FXML
    private Button import11;
    @FXML
    private ImageView canhbao1;
    @FXML
    private Pane youcandraganddrop1;

    @FXML
    private Label youcandraganddrop5;

    @FXML
    void chooseafile(ActionEvent event) {

        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Chọn tệp");
        file = fileChooser.showOpenDialog(canhbao1.getScene().getWindow());

        youcandraganddrop5.setText(file.getName());
        canhbao1.setVisible(false);
        import11.setDisable(false);
    }

    @FXML
    void youcandraganddrop(DragEvent event) {
        if (event.getGestureSource() != youcandraganddrop1
                && event.getDragboard().hasFiles()) {
            event.acceptTransferModes(TransferMode.COPY_OR_MOVE);
        }
        event.consume();

    }

    @FXML
    void youcandraganddrop2(DragEvent event) {
        Dragboard db = event.getDragboard();
        boolean success = false;
        if (db.hasFiles()) {
            success = true;
            canhbao1.setVisible(false);
            import11.setDisable(false);
            file = db.getFiles().get(0);
            youcandraganddrop5.setText(file.getName());

        }

        event.setDropCompleted(success);
        event.consume();

    }

    @FXML
    void import1(ActionEvent event) {
        if (file.getName().endsWith(".txt") || file.getName().endsWith(".docx")) {
            AikenFormatTxt.getInstance().checkFormat(file.getPath());
        } else {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText(null);
            alert.setContentText("Wrong format");
            alert.show();
        }

    }

    @FXML
    void youcandraganddrop3(DragEvent event) {
        if (event.getGestureSource() != youcandraganddrop1 && event.getDragboard().hasFiles()) {
            youcandraganddrop1.setStyle("-fx-background-color: #cdf40a");
        }
        event.consume();
    }

    @FXML
    void youcandraganddrop4(DragEvent event) {
        youcandraganddrop1.setStyle("-fx-background-color: #ffffff");
        event.consume();
    }


}


