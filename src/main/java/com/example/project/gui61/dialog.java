package com.example.project.gui61;

import com.example.project.database.dao.QuestionsDao;
import com.example.project.database.dao.QuizDao;
import com.example.project.database.entities.Quiz;
import com.example.project.gui71.gui71;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.stage.Stage;

import java.net.URL;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Locale;
import java.util.ResourceBundle;

public class dialog implements Initializable {


    @FXML
    private Label label1;
    @FXML
    private Label timelimit;
    private int minutes=QuizDao.getInstance().getQuiz().getTimeLimit();
    private int b=0;


    public int getB() {
        return b;
    }

    @FXML
    void cancel(ActionEvent event) {
        Stage a = (Stage) timelimit.getScene().getWindow();
        a.hide();
    }

    @FXML
    void startattempt(ActionEvent event) {
        b=1;
        try {
            FXMLLoader loader=new FXMLLoader(getClass().getResource("/com/example/project/gui71/gui(7.1).fxml"));
            Parent root = loader.load();
            Scene scene = new Scene(root);
            Stage ag0r = new Stage();
            ag0r.setScene(scene);
            ag0r.show();
            Stage a = (Stage) timelimit.getScene().getWindow();
            a.hide();
            LocalDateTime currentDateTime = LocalDateTime.now();
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("EEEE, dd MMMM yyyy, h:mm a", Locale.ENGLISH);
            String formattedDateTime = currentDateTime.format(formatter);
            QuestionsDao.getInstance().setTime(formattedDateTime);
            QuestionsDao.getInstance().setDuring(currentDateTime);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        label1.setText(String.format("Your attempt will have a time limit of %02d minutes. When you start,the timer will begin"+"\n"+
                "to count down and cannot be paused. You must finish your attempt before it "+"\n"+
                "expires.Are you sure you wish to start now ?",minutes));
    }
}
