package com.example.project.database.dao;

import java.io.Serializable;

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
}
