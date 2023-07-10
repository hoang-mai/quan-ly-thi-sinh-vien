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
import org.apache.pdfbox.pdmodel.graphics.image.PDImageXObject;

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
    private PDPage createNewPage(PDDocument document) {
        PDPage page = new PDPage(PDRectangle.A4);
        document.addPage(page);
        return page;
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
            float maxY = page.getMediaBox().getHeight();
            contentStream.newLineAtOffset(10, maxY - 50); // Đặt văn bản cách viền trên của trang 50 đơn vị
            maxY-=50;
            List<Questions> questionsList=QuizDao.getInstance().selectQuestion(QuizDao.getInstance().getQuiz().getQuizName());
            // Replace the following line with code to get the content from the exam questions
            for(int i=0;i<questionsList.size();i++){
                if(maxY<150||( questionsList.get(i).getImage()!=null&& maxY<250)){
                    contentStream.endText();
                    contentStream.close();
                    PDPage nextPage = createNewPage(document);
                    contentStream = new PDPageContentStream(document, nextPage);
                    contentStream.setFont(PDType0Font.load(document, getClass().getResourceAsStream("/arial.ttf")), 12);
                    maxY=page.getMediaBox().getHeight();
                    contentStream.beginText();
                    contentStream.newLineAtOffset(10, maxY - 50);
                    maxY=maxY-50;
                }
                String text=questionsList.get(i).getQuestionText();
                int k=0;
               while (text.length()>85){
                   if(k+85>text.length()){
                       contentStream.showText(text.substring(k,text.length()-1));
                       contentStream.newLineAtOffset(0, -20);
                       maxY-=20;
                       break;
                       }
                    else {
                       String text1=text.substring(k,k+85);
                       int l=text1.lastIndexOf(" ")+k;
                        contentStream.showText(text.substring(k,l));
                       contentStream.newLineAtOffset(0, -15);
                       maxY-=15;
                    k=l;
                    }
                }
               if(text.length()<=85) {contentStream.showText(text);
                   contentStream.newLineAtOffset(0, -20);
                   maxY-=20;
               }
               if(questionsList.get(i).getImage()!=null){
                   contentStream.endText();
                   PDImageXObject image = PDImageXObject.createFromByteArray(document, questionsList.get(i).getImage(), null);
                   contentStream.drawImage(image, 50, maxY-150,140,150); // Vẽ ảnh vào trang PDF
                    contentStream.beginText();
                   contentStream.newLineAtOffset(0, maxY-150-15);
                   maxY=maxY-150-15;

               }
                List<Choice> choiceList=QuestionsDao.getInstance().selectChoicebyQuestionId(questionsList.get(i).getQuestionId());
                for(int j=0;j<choiceList.size();j++){
                    String text2=choiceList.get(j).getChoiceText();
                    int m=0;
                    while (text2.length()>85){
                        if(m+85>text2.length()){
                            contentStream.showText(text2.substring(m,text2.length()-1));
                            contentStream.newLineAtOffset(0, -20);
                            maxY-=20;
                            break;
                        }
                        else {
                            String text3=text2.substring(m,m+85);
                            int n=text3.lastIndexOf(" ")+m;
                            contentStream.showText(text2.substring(m,n));
                            contentStream.newLineAtOffset(0, -15);
                            maxY-=15;
                           m=n;
                        }
                    }
                    if(text2.length()<=85) {contentStream.showText(text2);
                        contentStream.newLineAtOffset(0, -15);
                        maxY-=15;
                    }
                    if(choiceList.get(j).getImage()!=null){
                        contentStream.endText();
                        PDImageXObject image = PDImageXObject.createFromByteArray(document, choiceList.get(j).getImage(), null);
                        contentStream.drawImage(image, 50, maxY-150,140,150); // Vẽ ảnh vào trang PDF
                        contentStream.beginText();
                        contentStream.newLineAtOffset(0, maxY-150-15);
                        maxY=maxY-150-15;

                    }
                }

            }


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
        label1.setText(String.format("Your attempt will have a time limit of %02d minutes. When you start, the timer will begin"+"\n"+
                "to count down and cannot be paused. You must finish your attempt before it expires."+"\n"+
                "Are you sure you wish to start now ?",minutes));
    }
}
