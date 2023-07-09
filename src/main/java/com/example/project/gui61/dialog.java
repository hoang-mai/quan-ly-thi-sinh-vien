package com.example.project.gui61;

import com.example.project.database.dao.QuestionsDao;
import com.example.project.database.dao.QuizDao;
import com.example.project.database.entities.Choice;
import com.example.project.database.entities.Questions;
import com.example.project.database.entities.Quiz;
import com.example.project.gui71.gui71;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.text.Font;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.PDPageContentStream;
import org.apache.pdfbox.pdmodel.common.PDRectangle;
import org.apache.pdfbox.pdmodel.font.PDType0Font;
import org.apache.pdfbox.pdmodel.font.PDType1Font;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
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
@FXML
void exportToPDF(ActionEvent event){
    FileChooser fileChooser = new FileChooser();
    fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("PDF Files", "*.pdf"));
    File selectedFile = fileChooser.showSaveDialog(timelimit.getScene().getWindow());

    if (selectedFile != null) {

        try (PDDocument document = new PDDocument()) {

            PDPage page = new PDPage(PDRectangle.A4);
            document.addPage(page);
            PDPageContentStream contentStream = new PDPageContentStream(document, page);
            contentStream.setFont(PDType0Font.load(document, getClass().getResourceAsStream("/arial.ttf")), 12);
            contentStream.beginText();
            float maxY = page.getMediaBox().getHeight(); // Lấy tọa độ y lớn nhất trên trang
            contentStream.newLineAtOffset(50, maxY - 50); // Đặt văn bản cách viền trên của trang 50 đơn vị

            List<Questions> questionsList=QuizDao.getInstance().selectQuestion(QuizDao.getInstance().getQuiz().getQuizName());
            // Replace the following line with code to get the content from the exam questions
            for(int i=0;i<questionsList.size();i++){
                contentStream.showText(questionsList.get(i).getQuestionText());
                contentStream.newLineAtOffset(0, -20);
                List<Choice> choiceList=QuestionsDao.getInstance().selectChoicebyQuestionId(questionsList.get(i).getQuestionId());
                for(int j=0;j<choiceList.size();j++){
                    contentStream.showText(choiceList.get(j).getChoiceText());
                    contentStream.newLineAtOffset(0, -15);
                }

            }

            // Write each line to the PDF


            contentStream.endText();
            contentStream.close();

            document.save(selectedFile);
            System.out.println("PDF exported successfully to: " + selectedFile.getAbsolutePath());
        } catch (IOException e) {
            System.err.println(e.getMessage());
        }
    }
}

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        label1.setText(String.format("Your attempt will have a time limit of %02d minutes. When you start,the timer will begin"+"\n"+
                "to count down and cannot be paused. You must finish your attempt before it "+"\n"+
                "expires.Are you sure you wish to start now ?",minutes));
    }
}
