package com.example.project.database.dao;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.Transaction;

import com.example.project.database.entities.Quiz;
import com.example.project.database.utils.HibernateUtils;

public class QuizDao {
	public static QuizDao getInstance() {
		return new QuizDao();
	}
	// lưu quiz xuống
	public boolean save(Quiz quiz) throws Exception {
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
	public List<Quiz> selectALl(){
		List<Quiz> ketqua =new ArrayList<>();
		Session session = null;
		Transaction transaction = null;

		try {
			session = HibernateUtils.getSessionFactory().openSession();
			transaction = session.beginTransaction();
			ketqua=session.createQuery("FROM Quiz", Quiz.class).getResultList();

			transaction.commit();

			return  ketqua;


		} finally {
			if (session != null) {
				session.close();
			}
		}
	}
}
