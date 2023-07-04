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

	//đưa ra category có nhiều câu hỏi nhất
	public Categories selectCategoryMaxQuestion() {
		Categories categories = new Categories();
		Session session = null;
		Transaction transaction = null;

		try {
			session = HibernateUtils.getSessionFactory().openSession();
			transaction = session.beginTransaction();
			categories = session.createQuery("FROM Categories c ORDER BY c.questions.size DESC",
					Categories.class).setMaxResults(1).getSingleResult();
			transaction.commit();

			return categories;

		} finally {
			if (session != null) {
				session.close();
			}
		}
	}

	//đưa ra số lượng câu hỏi có trong 1 category
	public int CountQuestion(String categoryName) {
		Categories categories = CategoriesDao.getInstance().selectCategorybyName(categoryName);
		return CategoriesDao.getInstance().selectQuestion(categories.getCategoryName()).size();
	}


	//test
	public static void main(String[] args) {
		Categories categories = new Categories();
		categories = CategoriesDao.getInstance().selectCategoryMaxQuestion();
        System.out.println(categories.getCategoryName());
		System.out.println(CategoriesDao.getInstance().CountQuestion(categories.getCategoryName()));
	}





}


