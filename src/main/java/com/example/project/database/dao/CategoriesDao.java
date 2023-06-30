package com.example.project.database.dao;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import com.example.project.database.entities.Questions;
import com.example.project.database.entities.Quiz;
import org.hibernate.Session;
import org.hibernate.Transaction;

import com.example.project.database.entities.Categories;
import com.example.project.database.utils.HibernateUtils;

public class  CategoriesDao {


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
	//chỉnh sửa categories
	public boolean update(Categories categories) throws Exception {
		Session session = null;
		Transaction transaction = null;

		try {
			session = HibernateUtils.getSessionFactory().openSession();
			transaction = session.beginTransaction();

			session.update(categories);

			transaction.commit();

			return true;

		} finally {
			if (session != null) {
				session.close();
			}
		}
	}
	//lấy danh sách categories
	public List<Categories> selectALl() {
		List<Categories> categories = new ArrayList<>();
		Session session = null;
		Transaction transaction = null;

		try {
			session = HibernateUtils.getSessionFactory().openSession();
			transaction = session.beginTransaction();
			categories = session.createQuery("FROM Categories", Categories.class).getResultList();

			transaction.commit();

			return categories;

		} finally {
			if (session != null) {
				session.close();
			}
		}
	}
	//truy vấn ra danh sách câu hỏi
	public List<Questions> selectQuestion(String categoryName) {
		List<Questions> questions = new ArrayList<>();
		Session session = null;
		Transaction transaction = null;

		try {
			session = HibernateUtils.getSessionFactory().openSession();
			transaction = session.beginTransaction();
			questions = session.createQuery("FROM Questions q WHERE q.categories.categoryName = :categoryName",
					Questions.class).setParameter("categoryName",categoryName).getResultList();
			transaction.commit();

			return questions;

		} finally {
			if (session != null) {
				session.close();
			}
		}
	}
	//Đưa ra category theo tên
public Categories selectCategorybyName(String categoryName) {
		Categories categories = new Categories();
		Session session = null;
		Transaction transaction = null;

		try {
			session = HibernateUtils.getSessionFactory().openSession();
			transaction = session.beginTransaction();
			categories = session.createQuery("FROM Categories c WHERE c.categoryName = :categoryName",
					Categories.class).setParameter("categoryName",categoryName).getSingleResult();
			transaction.commit();

			return categories;

		} finally {
			if (session != null) {
				session.close();
			}
		}
	}






}


