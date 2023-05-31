package com.example.project.database.dao;

import java.io.Serializable;

import org.hibernate.Session;
import org.hibernate.Transaction;

import com.example.project.database.entities.Choice;
import com.example.project.database.utils.HibernateUtils;

public class ChoiceDao {
	public static ChoiceDao getInstance() {
		return new ChoiceDao();
	}
	
	public boolean save(Choice choice) throws Exception {
		Session session = null;
		Transaction transaction = null;

		try {
			session = HibernateUtils.getSessionFactory().openSession();
			transaction = session.beginTransaction();

			Serializable result = session.save(choice);

			transaction.commit();

			return (result != null);

		} finally {
			if (session != null) {
				session.close();
			}
		}
	}
}
