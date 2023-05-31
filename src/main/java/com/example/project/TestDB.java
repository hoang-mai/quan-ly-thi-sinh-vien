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
		quiz1.setTimeLimit(new Date(System.currentTimeMillis()));
		quiz1.setTimeOpen(new Date(System.currentTimeMillis()));
		quiz1.setUser(user);

		quiz2.setCategories(categories);
		quiz2.setDescription("Đây là quiz 2");
		quiz2.setGrade(2.5);
		quiz2.setQuestions(questions);
		quiz2.setQuizName("Quiz2");
		quiz2.setTimeClose(new Date(System.currentTimeMillis()));
		quiz2.setTimeLimit(new Date(System.currentTimeMillis()));
		quiz2.setTimeOpen(new Date(System.currentTimeMillis()));
		quiz2.setUser(user);

		user.setFullName("Nguyen Van A");
		user.setPassword("123456");
		user.setQuiz(quizs);
		user.setUsername("Tài khoản 1");

		choice1.setChoiceText("Lựa chọn 1");
		choice1.setQuestions(questions1);

		choice2.setChoiceText("Lựa chọn 2");
		choice2.setQuestions(questions2);

		categories1.setCategoryInfo("Đây là thể loại 1");
		categories1.setCategoryName("Loại 1");
		categories1.setQuestionCount(4);
		categories1.setQuiz(quizs);

		categories2.setCategoryInfo("Đây là thể loại 2");
		categories2.setCategoryName("Loại 2");
		categories2.setQuestionCount(2);
		categories2.setQuiz(quizs);

		questions1.setAnswer("A");
		questions1.setQuestionName("Câu hỏi 1");
		questions1.setQuestionText("Đây là câu hỏi 1");
		questions1.setQuestionText("Nội dung câu hỏi 1");
		questions1.setQuiz(quizs);

		questions1.setAnswer("B");
		questions1.setQuestionName("Câu hỏi 2");
		questions1.setQuestionText("Đây là câu hỏi 2");
		questions1.setQuestionText("Nội dung câu hỏi 2");
		questions1.setQuiz(quizs);
		///////////////////////////////////////////////////////
		UserDao.getInstance().save(user);

		CategoriesDao.getInstance().save(categories1);
		CategoriesDao.getInstance().save(categories2);

		QuizDao.getInstance().save(quiz1);
		QuizDao.getInstance().save(quiz2);

		QuestionsDao.getInstance().save(questions1);
		QuestionsDao.getInstance().save(questions2);

		ChoiceDao.getInstance().save(choice1);
		ChoiceDao.getInstance().save(choice2);

	}
}
