package com.example.project.database.dao;

import java.io.Serializable;

import org.hibernate.Session;
import org.hibernate.Transaction;

import com.example.project.database.entities.Categories;
import com.example.project.database.utils.HibernateUtils;

public class CategoriesDao {
	public static CategoriesDao getInstance() {
		return new CategoriesDao();
	}
	
	public boolean save(Categories categories) throws Exception {
		Session session = null;
		Transaction transaction = null;

		try {
			session = HibernateUtils.getSessionFactory().openSession();
			transaction = session.beginTransaction();

			Serializable result = session.save(categories);

			transaction.commit();

			return (result != null);

		} finally {
			if (session != null) {
				session.close();
			}
		}
	}
}
