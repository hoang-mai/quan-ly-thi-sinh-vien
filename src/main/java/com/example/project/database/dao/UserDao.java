package com.example.project.database.dao;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import com.example.project.database.entities.Choice;
import org.hibernate.Session;
import org.hibernate.Transaction;

import com.example.project.database.entities.User;
import com.example.project.database.utils.HibernateUtils;

public class UserDao {
	public static UserDao getInstance() {
		return new UserDao();
	}
	
	public boolean save(User user) throws Exception {
		Session session = null;
		Transaction transaction = null;

		try {
			session = HibernateUtils.getSessionFactory().openSession();
			transaction = session.beginTransaction();

			Serializable result = session.save(user);

			transaction.commit();

			return (result != null);

		} finally {
			if (session != null) {
				session.close();
			}
		}
	}
	//lấy danh sách user
	public List<User> selectALl(){
		List<User> users =new ArrayList<>();
		Session session = null;
		Transaction transaction = null;

		try {
			session = HibernateUtils.getSessionFactory().openSession();
			transaction = session.beginTransaction();
			users=session.createQuery("FROM User", User.class).getResultList();

			transaction.commit();

			return  users;


		} finally {
			if (session != null) {
				session.close();
			}
		}
	}
}
