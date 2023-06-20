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
	public static List<Questions> selectQuestion(String categoryName) {
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
	//test xuất dữ liệu
/*	public static void main(String[] args) {
		CategoriesDao categoriesDao = new CategoriesDao();
		List<Categories> categories = categoriesDao.selectALl();
		for (Categories categories1 : categories) {
			System.out.println(categories1.getCategoryName());
		}
	}*/
	//test xuất danh sách câu hỏi
	public static void main(String[] args) {
		String categoryname="Loại 1";
		int questionCount=0;
		List<Questions> listquestion=CategoriesDao.selectQuestion(categoryname);
		for(Questions question : listquestion){
			System.out.println(question.getQuestionName());
            questionCount++;
		}
		System.out.println("Số lượng câu hỏi: "+questionCount);
	}
}

