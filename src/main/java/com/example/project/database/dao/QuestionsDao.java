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
			questions = session.createQuery("FROM Questions where id = 1",
					Questions.class).getResultList();

			transaction.commit();

			return questions;

		} finally {
			if (session != null) {
				session.close();
			}
		}
	}

	public static void main(String[] args) {
		QuestionsDao questionsDao = new QuestionsDao();
		List<Questions> questions = questionsDao.printQuestion();
		for (Questions questions1 : questions) {
			System.out.println(questions1.getQuestionName());
		}

	}
}