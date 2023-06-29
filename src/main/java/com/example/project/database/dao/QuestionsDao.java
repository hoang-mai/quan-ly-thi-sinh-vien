package com.example.project.database.dao;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import com.example.project.database.entities.Categories;
import com.example.project.database.entities.Choice;
import org.hibernate.Session;
import org.hibernate.Transaction;

import com.example.project.database.entities.Questions;
import com.example.project.database.utils.HibernateUtils;

public class QuestionsDao {
	public static QuestionsDao getInstance() {
		return new QuestionsDao();
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

	public static void main(String[] args) throws Exception {
		//QuestionsDao questionsDao = new QuestionsDao();
		//List<Questions> questions = questionsDao.selectALl();
		//for (Questions questions1 : questions) {
		//	System.out.println(questions1.getQuestionName());
		/*String questionName = "Câu hỏi 1";
		Questions question = selectQuestionbyName(questionName);
		System.out.println(question.getQuestionText()); */
		//thêm 1 loạt câu hỏi
		List<Categories> categories = CategoriesDao.getInstance().selectALl();
		for (int i = 3; i <= 10; i++) {
			Questions question1 = new Questions();
			question1.setQuestionName("Câu hỏi " + i);
			question1.setQuestionText("Nội dung câu hỏi " + i);
			question1.setCategories(categories.get(0));
			question1.setDefaultmark(1);
			QuestionsDao.getInstance().save(question1);
		}
		QuestionsDao questionsDao = new QuestionsDao();
		List<Questions> questions = questionsDao.selectALl();
		for (Questions questions1 : questions) {
			System.out.println(questions1.getQuestionName());

		}

	}
}
