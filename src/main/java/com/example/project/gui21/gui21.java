package com.example.project.gui21;

import com.example.project.database.dao.QuestionsDao;
import com.example.project.gui32.gui32;
import com.example.project.database.dao.CategoriesDao;
import com.example.project.database.entities.Categories;
import com.example.project.database.entities.Questions;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.ImageView;
import javafx.scene.input.*;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.collections.ObservableList;
import java.io.File;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

public class gui21 implements Initializable {

    private File file = new File("");
    @FXML
    private Button addcategory1;

    @FXML
    private CheckBox alsoquestion1;

    @FXML
    private AnchorPane anchorpane1;

    @FXML
    private ImageView canhbao_1;

    @FXML
    private ImageView canhbao__1;

    @FXML
    private TextArea categoryinfor1;

    @FXML
    private ImageView chamhoi2;

    @FXML
    private ComboBox<String> combobox;


    private GridPane gridpane1=new GridPane();

    @FXML
    private TextField idnumber1;

    @FXML
    private Button import11;

    @FXML
    private TextField name1;

    @FXML
    private ScrollPane scrollpane1;

    @FXML
    private Pane youcandraganddrop1;

    @FXML
    private Label youcandraganddrop5;



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
    void chooseafile(ActionEvent event) {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Chọn tệp");
        file = fileChooser.showOpenDialog(canhbao_1.getScene().getWindow());

        youcandraganddrop5.setText(file.getName());
        canhbao_1.setVisible(false);
        import11.setDisable(false);
    }

    @FXML
    void comboBoxChanged(ActionEvent event) {

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

    @FXML
    void import1(ActionEvent event) {
        if (file.getName().endsWith(".txt") ) {
            AikenFormatTxt.getInstance().checkFormat(file.getPath());
        } else if (file.getName().endsWith(".docx")) {
            AikenFormatDocx.getInstance().checkFormat(file.getPath());
        } else {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText(null);
            alert.setContentText("Wrong format");
            alert.show();
        }
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
            canhbao_1.setVisible(false);
            import11.setDisable(false);
            file = db.getFiles().get(0);
            youcandraganddrop5.setText(file.getName());

        }

        event.setDropCompleted(success);
        event.consume();

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
    void addcategory(ActionEvent event) {
        try {
            Categories categories = new Categories();
            categories.setCategoryName(name1.getText());
            categories.setCategoryId(Integer.parseInt(idnumber1.getText()));
            categories.setCategoryInfo(categoryinfor1.getText());
            CategoriesDao.getInstance().save(categories);
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
            List<Categories> listcate = CategoriesDao.getInstance().selectALl();
            ObservableList<String> list = FXCollections.observableArrayList();
            for (Categories categories : listcate) {
                list.add(categories.getCategoryName());
            }
            combobox.setItems(list);

            combobox.getSelectionModel().selectedItemProperty().addListener((observableValue, oldvalue, newvalue) -> {
                gridpane1.getChildren().clear();
                List<Questions> danhsachquestion = CategoriesDao.getInstance().selectQuestion(combobox.getValue());
                anchorpane1.setPrefHeight(46+40*danhsachquestion.size());
                for (int i = 0; i < danhsachquestion.size(); i++) {
                    CheckBox checkBox = new CheckBox(danhsachquestion.get(i).getQuestionName());
                    gridpane1.add(checkBox, 0, i);
                    Button button = new Button("Edit");
                    gridpane1.add(button, 1, i);

                    int finalI = i;
                    button.setOnAction(event2 -> {
                        try {
                            Stage ag0r1 = (Stage) ((Node) event2.getSource()).getScene().getWindow();
                            FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/project/gui32/gui(3.2).fxml"));
                            Parent root = loader.load();
                            Scene scene = new Scene(root);
                            ag0r1.setScene(scene);
                            ag0r1.show();
                            gui32 controller = loader.getController();
                            QuestionsDao.getInstance().setQuestions(danhsachquestion.get(finalI));
                            controller.setedit("Editing Multiple choice question",combobox.getValue());


                        } catch (Exception e) {
                            System.out.println(e.getMessage());
                        }
                    });
                }
            });

            categoryinfor1.setWrapText(true);
            name1.textProperty().addListener((Observable, oldvalue, newValue) -> {
                if (newValue.isEmpty()) {
                    canhbao__1.setVisible(true);
                    addcategory1.setDisable(true);
                } else {
                    canhbao__1.setVisible(false);
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
