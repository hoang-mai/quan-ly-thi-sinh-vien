package com.example.project;

import java.sql.Date;
import java.util.HashSet;
import java.util.Set;

import com.example.project.database.dao.CategoriesDao;
import com.example.project.database.dao.ChoiceDao;
import com.example.project.database.dao.QuestionsDao;
import com.example.project.database.dao.QuizDao;
import com.example.project.database.dao.UserDao;
import com.example.project.database.entities.Categories;
import com.example.project.database.entities.Choice;
import com.example.project.database.entities.Questions;
import com.example.project.database.entities.Quiz;
import com.example.project.database.entities.User;

public class TestDB {
	public static void main(String[] args) throws Exception {
		Quiz quiz1 = new Quiz();
		Quiz quiz2 = new Quiz();

		Set<Quiz> quizs = new HashSet<Quiz>();
		quizs.add(quiz1);
		quizs.add(quiz2);

		User user = new User();

		Choice choice1 = new Choice();
		Choice choice2 = new Choice();

		Categories categories1 = new Categories();
		Categories categories2 = new Categories();

		Set<Categories> categories = new HashSet<Categories>();
		categories.add(categories1);
		categories.add(categories2);

		Questions questions1 = new Questions();
		Questions questions2 = new Questions();

		Set<Questions> questions = new HashSet<Questions>();
		questions.add(questions1);
		questions.add(questions2);
		//////////////////////////////////////
		quiz1.setCategories(categories);
		quiz1.setDescription("Đây là quiz 1");
		quiz1.setGrade(2.3);
		quiz1.setQuestions(questions);
		quiz1.setQuizName("Quiz1");
		quiz1.setTimeClose(new Date(System.currentTimeMillis()));
		//quiz1.setTimeLimit(new Date(System.currentTimeMillis()));
		quiz1.setTimeOpen(new Date(System.currentTimeMillis()));
		quiz1.setUser(user);

		quiz2.setCategories(categories);
		quiz2.setDescription("Đây là quiz 2");
		quiz2.setGrade(2.5);
		quiz2.setQuestions(questions);
		quiz2.setQuizName("Quiz2");
		quiz2.setTimeClose(new Date(System.currentTimeMillis()));
		//quiz2.setTimeLimit(new Date(System.currentTimeMillis()));
		quiz2.setTimeOpen(new Date(System.currentTimeMillis()));
		quiz2.setUser(user);

		user.setFullName("Nguyen Van A");
		user.setPassword("123456");
		user.setQuiz(quizs);
		user.setUsername("Tài khoản 1");

		choice1.setChoiceText("Lựa chọn 1");
		choice1.setQuestions(questions1);
		choice1.setGrade("100");

		choice2.setChoiceText("Lựa chọn 2");
		choice2.setQuestions(questions2);
		choice2.setGrade("0");

		categories1.setCategoryInfo("Đây là thể loại 1");
		categories1.setCategoryId(1);
		categories1.setCategoryName("Loại 1");
		categories1.setQuiz(quizs);

		categories2.setCategoryInfo("Đây là thể loại 2");
		categories2.setCategoryId(2);
		categories2.setCategoryName("Loại 2");

		categories2.setQuiz(quizs);


		//questions1.setAnswer("A");
		questions1.setQuestionName("Câu hỏi 1");
		questions1.setQuestionText("Đây là câu hỏi 1");
		questions1.setQuestionText("Nội dung câu hỏi 1");
		questions1.setCategories(categories1);
		questions1.setQuiz(quizs);

		//questions1.setAnswer("B");
		questions2.setQuestionName("Câu hỏi 2");
		questions2.setQuestionText("Đây là câu hỏi 2");
		questions2.setQuestionText("Nội dung câu hỏi 2");
		questions2.setCategories(categories1);
		questions2.setQuiz(quizs);

		///////////////////////////////////////////////////////
		UserDao.getInstance().save(user);
		for(int i=3;i<10;i++){
Categories categories3=new Categories();
categories3.setCategoryId(i);
categories3.setCategoryInfo("Đây là thể loại"+i);
			categories3.setCategoryName("Loại"+i);
			categories3.setQuiz(quizs);
			CategoriesDao.getInstance().save(categories3);

		}
		CategoriesDao.getInstance().save(categories1);
		CategoriesDao.getInstance().save(categories2);
		Categories categories4=new Categories();
		categories4.setCategoryId(10);
		categories4.setCategoryInfo("Đây là thể loại"+10);
		categories4.setCategoryName("Loại"+10);
		categories4.setQuiz(quizs);
		categories4.setCategories_parent(categories1);
		CategoriesDao.getInstance().save(categories4);




		QuizDao.getInstance().save(quiz1);
		QuizDao.getInstance().save(quiz2);
		for (int i = 3; i <= 10; i++) {
			Questions questiontmp = new Questions();
			questiontmp.setQuestionName("Câu hỏi " + i);
			questiontmp.setQuestionText("Nội dung câu hỏi " + i);
			questiontmp.setCategories(categories1);
			questiontmp.setDefaultmark(1);
			QuestionsDao.getInstance().save(questiontmp);
		}
		QuestionsDao.getInstance().save(questions1);
		QuestionsDao.getInstance().save(questions2);

		ChoiceDao.getInstance().save(choice1);
		ChoiceDao.getInstance().save(choice2);

	}
}
