package com.example.project.gui21;

import java.io.File;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.List;

import com.example.project.database.dao.CategoriesDao;
import com.example.project.database.dao.ChoiceDao;
import com.example.project.database.dao.QuestionsDao;
import com.example.project.database.entities.Categories;
import com.example.project.database.entities.Choice;
import com.example.project.database.entities.Questions;
import javafx.scene.control.Alert;


public class AikenFormatTxt {
    public static AikenFormatTxt getInstance() {
        return new AikenFormatTxt();
    }

    /**
     * Kiểm tra dòng văn bản thuộc loại gì
     *
     * @param choiceLine Dòng văn bản cần kiểm tra
     * @return 0 nếu là đáp án câu hỏi, 1 nếu là tiêu đề câu hỏi, -1 nếu là dòng
     * trống, 2 nếu là dòng ANSWER
     */
    public int checkLine(String choiceLine) {
        if (choiceLine.isEmpty()) {
            return -1;
        } else if (Character.isUpperCase(choiceLine.charAt(0)) && choiceLine.charAt(1) == '.'
                && choiceLine.charAt(2) == ' ') {
            return 0;
        } else if (choiceLine.length() == 9 && choiceLine.startsWith("ANSWER: ")) {
            return 2;
        } else {
            return 1;
        }
    }

    /**
     * Kiểm tra file .txt có đúng định dạng Aiken format không
     *
     * @param path Đường dẫn tới file .txt cần kiểm tra định dạng
     */
    public void checkFormat(String path) {
        File file = new File(path);

        try {
            int numberQuestions = 0;
            int countChoice = -2;
            int result = 1;

            List<String> allLine = Files.readAllLines(file.toPath(), StandardCharsets.UTF_8);
            ArrayList<Character> keyChoice = new ArrayList<Character>();

            int numberLine = allLine.size();

                for (int i = 0; i < numberLine; i++) {
                    String checkLine = allLine.get(i);
                    if (AikenFormatTxt.getInstance().checkLine(checkLine) == 1 && countChoice == -2) {
                        countChoice = 0;
                        keyChoice.clear();

                    } else if (AikenFormatTxt.getInstance().checkLine(checkLine) == 0 && countChoice >= 0) {
                        countChoice++;
                        keyChoice.add(checkLine.charAt(0));

                    } else if (AikenFormatTxt.getInstance().checkLine(checkLine) == 2 && countChoice >= 2) {
                        if (keyChoice.contains(checkLine.charAt(8))) {
                            countChoice = -1;
                            numberQuestions++;


                        } else {
                            Alert alert = new Alert(Alert.AlertType.ERROR);
                            alert.setTitle("Error");
                            alert.setHeaderText(null);
                            alert.setContentText("Error at " + (i + 1));
                            alert.show();
                            result = 0;

                            break;
                        }
                    } else if (AikenFormatTxt.getInstance().checkLine(checkLine) == -1 && countChoice == -1) {
                        countChoice = -2;

                    } else {
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
                for (int i = 0; i < numberLine; i++) {
                    String checkLine = allLine.get(i);
                    System.out.println(checkLine);
                    if (AikenFormatTxt.getInstance().checkLine(checkLine) == 1 ) {
                        countChoice = 0;
                    } else if (AikenFormatTxt.getInstance().checkLine(checkLine) == 0 ) {
                        countChoice++;
                    } else if (AikenFormatTxt.getInstance().checkLine(checkLine) == 2 ) {
                        Questions questions=new Questions();
                        questions.setDefaultmark(1);
                        Categories categories = CategoriesDao.getInstance().selectCategorybyName("Loại 1");
                        questions.setCategories(categories);
                           questions.setQuestionText(allLine.get(i-countChoice-1));
                        questions.setQuestionName(allLine.get(i-countChoice-1));
                        System.out.println(allLine.get(i-countChoice-1));
                            QuestionsDao.getInstance().save(questions);
                            Choice choice=new Choice();
                            for (int j=0;j<countChoice;j++){
                                choice.setQuestions(questions);
                                choice.setChoiceText(allLine.get(i-countChoice+j));
                                if(checkLine.charAt(8)==allLine.get(i-countChoice+j).charAt(0)){
                                    choice.setGrade(100);
                                }
                                else choice.setGrade(0);
                                ChoiceDao.getInstance().save(choice);
                            }
                    } else if (AikenFormatTxt.getInstance().checkLine(checkLine) == -1 ) {
                        countChoice = -2;
                    }
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
