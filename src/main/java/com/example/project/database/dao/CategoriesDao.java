package com.example.project.database.dao;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
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
					Questions.class).setParameter("categoryName", categoryName).getResultList();
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
					Categories.class).setParameter("categoryName", categoryName).getSingleResult();
			transaction.commit();

			return categories;

		} finally {
			if (session != null) {
				session.close();
			}
		}
	}
	//đưa ra category theo id
	public Categories selectCategorybyId(int categoryId) {
		Categories categories = new Categories();
		Session session = null;
		Transaction transaction = null;

		try {
			session = HibernateUtils.getSessionFactory().openSession();
			transaction = session.beginTransaction();
			categories = session.createQuery("FROM Categories c WHERE c.categoryId = :categoryId",
					Categories.class).setParameter("categoryId", categoryId).getSingleResult();
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
			if (categories == null) {
				categories.setCategoryId(0);
				// Lấy ngày tháng năm hiện tại
				LocalDate currentDate = LocalDate.now();

				// Định dạng ngày tháng
				DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");

				// Chuyển đổi thành chuỗi
				String dateString = currentDate.format(formatter);
				//lưu vào category
				categories.setCategoryName(dateString);
				categories.setCategoryInfo("Tạo vào " + dateString);
				CategoriesDao.getInstance().save(categories);
			}
			return categories;

		} catch (Exception e) {
			throw new RuntimeException(e);
		} finally {
			if (session != null) {
				session.close();
			}
		}
	}

	//đưa ra category cha
	public Categories selectCategoryparent(String categoryName) {
		Categories tmp = CategoriesDao.getInstance().selectCategorybyName(categoryName);
		if (tmp.getCategories_parent() != null) {
			Categories categories = new Categories();
			Session session = null;
			Transaction transaction = null;

			try {
				session = HibernateUtils.getSessionFactory().openSession();
				transaction = session.beginTransaction();
				categories = session.createQuery("FROM Categories c WHERE c.categoryId = :categoryId",
						Categories.class).setParameter("categoryId", tmp.getCategories_parent().getCategoryId()).getSingleResult();
				transaction.commit();

				return categories;

			} finally {
				if (session != null) {
					session.close();
				}
			}
		}
		return null;
	}

	//đưa ra số lượng câu hỏi có trong 1 category
	public int CountQuestion(String categoryName) {
		Categories categories = CategoriesDao.getInstance().selectCategorybyName(categoryName);
		return CategoriesDao.getInstance().selectQuestion(categories.getCategoryName()).size();
	}

	//get Child category
	public List<Categories> getChildCategories(String categoryName) {
		List<Categories> categories = new ArrayList<>();
		Session session = null;
		Transaction transaction = null;

		try {
			session = HibernateUtils.getSessionFactory().openSession();
			transaction = session.beginTransaction();
			categories = session.createQuery("FROM Categories c WHERE c.categories_parent.categoryName = :categoryName", Categories.class)
					.setParameter("categoryName", categoryName).getResultList();
			transaction.commit();

			return categories;

		} finally {
			if (session != null) {
				session.close();
			}
		}
	}

	// show all questions from subcategory
	public List<Questions> selectQuestionfromSubCategory(String categoryName) {
		List<Questions> questions = new ArrayList<>();
		questions = CategoriesDao.getInstance().selectQuestion(categoryName);
		Session session = null;
		Transaction transaction = null;

		try {
			session = HibernateUtils.getSessionFactory().openSession();
			transaction = session.beginTransaction();
			List<Questions> tmp = session.createQuery("FROM Questions q WHERE (q.categories.categoryName = :categoryName) OR (q.categories.categories_parent.categoryName = :categoryName)",
					Questions.class).setParameter("categoryName", categoryName).getResultList();
			transaction.commit();
            questions.addAll(tmp);
			return questions;

		} finally {
			if (session != null) {
				session.close();
			}
		}
	}


	//test
	public static void main(String[] args) throws Exception {
		/* List<Categories> categories = CategoriesDao.getInstance().selectALl();
		for (int i = 0; i < categories.size(); i++) {
			if (CategoriesDao.getInstance().getChildCategories(categories.get(i).getCategoryName()) != null) {
				List<Categories> categoriesList = CategoriesDao.getInstance().getChildCategories(categories.get(i).getCategoryName());
				for (Categories categories1 : categoriesList) {
					categories.removeIf(categories2 -> categories2.getCategoryName().equals(categories1.getCategoryName()));
					categories.add(i+1,categories1);
				}

			}
		}
			for (Categories categories1 : categories) {
				System.out.println(categories1.getCategoryName());
			}*/

		List<Questions> questions = CategoriesDao.getInstance().selectQuestionfromSubCategory("Loại 1");
		for(Questions questions1 : questions){
			System.out.println(questions1.getQuestionName());
		}
	}
}







