package com.example.project.gui34;

import javafx.scene.control.Alert;
import org.apache.poi.xwpf.usermodel.*;

import java.io.File;
import java.io.FileInputStream;

import java.util.ArrayList;
import java.util.List;

public class AikenFormatDocx {
    public static AikenFormatDocx getInstance(){return new AikenFormatDocx();}

    public int checkLine(String choiceLine) {
        if (choiceLine.isEmpty()) {
            return -1;
        } else if (Character.isUpperCase(choiceLine.charAt(0)) && choiceLine.charAt(1) == '.' && choiceLine.charAt(2) == ' ') {
            return 0;
        } else if (choiceLine.length() == 9 && choiceLine.startsWith("ANSWER: ")) {
            return 2;
        }  else {
            return 1;
        }
    }

    public void checkFormat(String path) {
        File file = new File(path);

        try {
            FileInputStream fileInputStream = new FileInputStream(file);
            XWPFDocument document = new XWPFDocument(fileInputStream);

            int numberQuestions = 0;
            int countChoice = -2;
            int result = 1;
            List<String> allLine = new ArrayList<>();
            for (int i = 0; i < document.getParagraphs().size(); i++) {
                XWPFParagraph paragraph = document.getParagraphs().get(i);
                // Lấy văn bản của đoạn
                String paragraphText = paragraph.getText();
                // Kiểm tra xem đoạn có chứa ảnh không
                if (paragraph.getRuns().size() > 0 && paragraph.getRuns().get(0).getEmbeddedPictures().size() > 0) {
                    // Lấy vị trí dòng của ảnh
                    String pictureName = paragraph.getRuns().get(0).getEmbeddedPictures().get(0).getPictureData().getFileName();
                    allLine.add(pictureName);
                }
                else allLine.add(paragraphText);
            }
            ArrayList<Character> keyChoice = new ArrayList<Character>();
            int numberLine = allLine.size();
                for (int i = 0; i < numberLine; i++) {
                    String checkLine1 = allLine.get(i);
                    if (AikenFormatTxt.getInstance().checkLine(checkLine1) == 1 && countChoice == -2) {
                        countChoice = 0;
                        keyChoice.clear();
                    } else if (checkLine1.startsWith("image") && countChoice == 0) {


                    } else if (AikenFormatTxt.getInstance().checkLine(checkLine1) == 0 && countChoice >= 0) {
                        countChoice++;
                        keyChoice.add(checkLine1.charAt(0));

                    } else if (AikenFormatTxt.getInstance().checkLine(checkLine1) == 2 && countChoice >= 2) {
                        if (keyChoice.contains(checkLine1.charAt(8))) {
                            countChoice = -1;
                            numberQuestions++;

                            continue;
                        } else {
                            Alert alert = new Alert(Alert.AlertType.ERROR);
                            alert.setTitle("Error");
                            alert.setHeaderText(null);
                            alert.setContentText("Error at " + (i + 1));
                            alert.show();
                            result = 0;

                            break;
                        }
                    } else if (AikenFormatTxt.getInstance().checkLine(checkLine1) == -1 && countChoice == -1) {
                        countChoice = -2;

                    } else {
                        System.out.println(checkLine1);
                        Alert alert = new Alert(Alert.AlertType.ERROR);
                        alert.setTitle("Error");
                        alert.setHeaderText(null);
                        alert.setContentText("Error at " + (i + 1));
                        alert.show();
                        result = 0;
                        break;
                    }
                }

            if (result == 1) {
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Success");
                alert.setHeaderText(null);
                alert.setContentText("Success: " + numberQuestions);
                alert.show();
            }
            document.close();
            fileInputStream.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
