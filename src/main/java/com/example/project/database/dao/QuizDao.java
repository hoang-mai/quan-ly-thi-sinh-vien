package com.example.project.database.dao;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import com.example.project.database.entities.Choice;
import com.example.project.database.entities.Questions;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;

import com.example.project.database.entities.Quiz;
import com.example.project.database.utils.HibernateUtils;

public class QuizDao {
	private static QuizDao instance;
	private Quiz quiz;

	public Quiz getQuiz() {
		return quiz;
	}

	public void setQuiz(Quiz quiz) {
		this.quiz = quiz;
	}

	private QuizDao() {
	}

	public static QuizDao getInstance() {
		if (instance == null) {
			instance = new QuizDao();
		}
		return instance;
	}

	// lưu quiz xuống
	public boolean save(Quiz quiz) {
		Session session = null;
		Transaction transaction = null;

		try {
			session = HibernateUtils.getSessionFactory().openSession();
			transaction = session.beginTransaction();

			Serializable result = session.save(quiz);

			transaction.commit();

			return (result != null);

		} finally {
			if (session != null) {
				session.close();
			}
		}
	}

	// lấy danh sách quiz
	public List<Quiz> selectALl() {
		List<Quiz> quiz = new ArrayList<>();
		Session session = null;
		Transaction transaction = null;

		try {
			session = HibernateUtils.getSessionFactory().openSession();
			transaction = session.beginTransaction();
			quiz = session.createQuery("FROM Quiz", Quiz.class).getResultList();

			transaction.commit();

			return quiz;


		} finally {
			if (session != null) {
				session.close();
			}
		}
	}

	// lấy quiz theo tên quiz
	public Quiz selectByName(String quizName) {
		Quiz quiz = null;
		Session session = null;
		Transaction transaction = null;

		try {
			session = HibernateUtils.getSessionFactory().openSession();
			transaction = session.beginTransaction();
			quiz = session.createQuery("FROM Quiz WHERE quizName = :quizName", Quiz.class).setParameter("quizName", quizName)
					.getSingleResult();

			transaction.commit();

			return quiz;

		} finally {
			if (session != null) {
				session.close();
			}
		}
	}

	//in ra danh sách câu hỏi có trong quiz với input là quizName
	public List<Questions> selectQuestion(String quizName) {
		List<Questions> questions = new ArrayList<>();
		quiz = QuizDao.getInstance().selectByName(quizName);
		int quiz_id = quiz.getQuizId();
		Session session = null;
		Transaction transaction = null;

		try {
			session = HibernateUtils.getSessionFactory().openSession();
			transaction = session.beginTransaction();
			questions = session.createQuery("SELECT q FROM Questions q " +
							"JOIN q.quiz quiz " +
							"WHERE quiz.quizId = :quiz_id",
					Questions.class).setParameter("quiz_id",quiz_id).getResultList();
			transaction.commit();

			return questions;

		} finally {
			if (session != null) {
				session.close();
			}
		}
	}

	//test
	public static void main(String[] args) {
		List<Quiz> quiz = new ArrayList<>();
		quiz = QuizDao.getInstance().selectALl();
		//for (Quiz quiz1 : quiz) {
		//	System.out.println(quiz1.getQuizName());
		//}
		List<Questions> questions = new ArrayList<>();
		questions = QuizDao.getInstance().selectQuestion("Quiz1");
		for (Questions question : questions) {
			QuestionsDao.getInstance().printQuestioninforbyId(question.getQuestionId());
		}
	}

}






