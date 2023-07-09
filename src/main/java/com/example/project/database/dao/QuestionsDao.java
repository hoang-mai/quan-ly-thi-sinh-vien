package com.example.project.database.dao;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.example.project.database.entities.Choice;
import javafx.scene.layout.GridPane;
import org.hibernate.Session;
import org.hibernate.Transaction;

import com.example.project.database.entities.Questions;
import com.example.project.database.utils.HibernateUtils;

public class QuestionsDao {
	private LocalDateTime during;

	public LocalDateTime getDuring() {
		return during;
	}

	public void setDuring(LocalDateTime during) {
		this.during = during;
	}

	private String time;

	public String getTime() {
		return time;
	}

	public void setTime(String time) {
		this.time = time;
	}

	private GridPane gridPane;
	private GridPane gridPane1;
	private Map<Integer,String> answer=new HashMap<>();

	public Map<Integer, String> getAnswer() {
		return answer;
	}

	public void setAnswer(Map<Integer, String> answer) {
		this.answer = answer;
	}

	public GridPane getGridPane1() {
		return gridPane1;
	}

	public void setGridPane1(GridPane gridPane1) {
		this.gridPane1 = gridPane1;
	}

	public GridPane getGridPane() {
		return gridPane;
	}

	public void setGridPane(GridPane gridPane) {
		this.gridPane = gridPane;
	}

	private Questions questions;

	public Questions getQuestions() {
		return questions;
	}

	public void setQuestions(Questions questions) {
		this.questions = questions;
	}
	private static QuestionsDao instance;
	private QuestionsDao() {
	}

	public static QuestionsDao getInstance() {
		if (instance == null) {
			instance = new QuestionsDao();
		}
		return instance;
	}

	public boolean save(Questions questions) throws Exception {
		Session session = null;
		Transaction transaction = null;

		try {
			session = HibernateUtils.getSessionFactory().openSession();
			transaction = session.beginTransaction();

			Serializable result = session.save(questions);

			transaction.commit();

			return (result != null);

		} finally {
			if (session != null) {
				session.close();
			}
		}
	}
	//update 1 question
	public boolean update(Questions questions) throws Exception {
		Session session = null;
		Transaction transaction = null;

		try {
			session = HibernateUtils.getSessionFactory().openSession();
			transaction = session.beginTransaction();

			session.update(questions);

			transaction.commit();

			return true;

		} finally {
			if (session != null) {
				session.close();
			}
		}
	}

	//lấy danh sách questions

	public List<Questions> selectALl() {
		List<Questions> questions = new ArrayList<>();
		Session session = null;
		Transaction transaction = null;

		try {
			session = HibernateUtils.getSessionFactory().openSession();
			transaction = session.beginTransaction();
			questions = session.createQuery("FROM Questions", Questions.class).getResultList();

			transaction.commit();

			return questions;


		} finally {
			if (session != null) {
				session.close();
			}
		}
	}

	public List<Questions> printQuestion() {
		List<Questions> questions = new ArrayList<>();
		Session session = null;
		Transaction transaction = null;

		try {
			session = HibernateUtils.getSessionFactory().openSession();
			transaction = session.beginTransaction();
			questions = session.createQuery("FROM Questions",
					Questions.class).getResultList();

			transaction.commit();

			return questions;

		} finally {
			if (session != null) {
				session.close();
			}
		}
	}
    //đưa ra 1 question với input là questionName
	public static Questions selectQuestionbyName(String questionName) {
		Questions question = new Questions();
		Session session = null;
		Transaction transaction = null;

		try {
			session = HibernateUtils.getSessionFactory().openSession();
			transaction = session.beginTransaction();
			question = session.createQuery("FROM Questions WHERE questionName = :questionName",
					Questions.class).setParameter("questionName", questionName).getSingleResult();
			transaction.commit();

			return question;

		} finally {
			if (session != null) {
				session.close();
			}
		}
	}
	//đưa ra question với input là questionId
	public static Questions selectQuestionbyId(int questionId) {
		Questions question = new Questions();
		Session session = null;
		Transaction transaction = null;

		try {
			session = HibernateUtils.getSessionFactory().openSession();
			transaction = session.beginTransaction();
			question = session.createQuery("FROM Questions WHERE questionId = :questionId",
					Questions.class).setParameter("questionId", questionId).getSingleResult();
			transaction.commit();

			return question;

		} finally {
			if (session != null) {
				session.close();
			}
		}
	}
	//đưa ra list choice theo questionId
	public List<Choice> selectChoicebyQuestionId(int questionId) {
		List<Choice> choices = new ArrayList<>();
		Session session = null;
		Transaction transaction = null;

		try {
			session = HibernateUtils.getSessionFactory().openSession();
			transaction = session.beginTransaction();
			choices = session.createQuery("FROM Choice WHERE questions.questionId = :questionId",
					Choice.class).setParameter("questionId", questionId).getResultList();
			transaction.commit();

			return choices;

		} finally {
			if (session != null) {
				session.close();
			}
		}
	}
	//ỉn ra màn hình questionName, questionText, choiceText với input là questionId
	public void printQuestioninforbyId(int questionId) {
		Questions question = new Questions();
		List<Choice> choices = new ArrayList<>();
		Session session = null;
		Transaction transaction = null;

		try {
			session = HibernateUtils.getSessionFactory().openSession();
			transaction = session.beginTransaction();
			question = session.createQuery("FROM Questions WHERE questionId = :questionId",
					Questions.class).setParameter("questionId", questionId).getSingleResult();
			choices = session.createQuery("FROM Choice WHERE questions.questionId = :questionId",
					Choice.class).setParameter("questionId", questionId).getResultList();
			transaction.commit();
			System.out.println(question.getQuestionName());
			System.out.println(question.getQuestionText());
			for (Choice choice : choices) {
				System.out.println(choice.getChoiceText());
			}

		} finally {
			if (session != null) {
				session.close();
			}
		}
	}







}
