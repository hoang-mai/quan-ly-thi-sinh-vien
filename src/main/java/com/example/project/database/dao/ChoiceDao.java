package com.example.project.database.dao;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import com.example.project.database.entities.Categories;
import com.example.project.database.entities.Questions;
import com.example.project.database.entities.Quiz;
import org.hibernate.Session;
import org.hibernate.Transaction;

import com.example.project.database.entities.Choice;
import com.example.project.database.utils.HibernateUtils;

public class ChoiceDao {
	private static ChoiceDao instance;
	private ChoiceDao() {
	}
	public static ChoiceDao getInstance() {
		if (instance == null) {
			instance = new ChoiceDao();
		}
		return instance;
	}
	private List<Choice> choices;

	public List<Choice> getChoices() {
		return choices;
	}

	public void setChoices(List<Choice> choices) {
		this.choices = choices;
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
	public boolean update(Choice choice) throws Exception {
		Session session = null;
		Transaction transaction = null;

		try {
			session = HibernateUtils.getSessionFactory().openSession();
			transaction = session.beginTransaction();

			session.update(choice);

			transaction.commit();

			return true;

		} finally {
			if (session != null) {
				session.close();
			}
		}
	}
	public boolean Delete(Choice choice) throws Exception {
		Session session = null;
		Transaction transaction = null;

		try {
			session = HibernateUtils.getSessionFactory().openSession();
			transaction = session.beginTransaction();

			session.delete(choice);

			transaction.commit();

			return true;

		} finally {
			if (session != null) {
				session.close();
			}
		}
	}
	//lấy danh sách các choice
	public List<Choice> selectALl(){
		List<Choice> choices =new ArrayList<>();
		Session session = null;
		Transaction transaction = null;

		try {
			session = HibernateUtils.getSessionFactory().openSession();
			transaction = session.beginTransaction();
			choices=session.createQuery("FROM Choice", Choice.class).getResultList();

			transaction.commit();

			return  choices;


		} finally {
			if (session != null) {
				session.close();
			}
		}
	}
	public static void main(String[] args) {
		ChoiceDao choicesDao = new ChoiceDao();
		List<Choice> choices = choicesDao.selectALl();
		for (Choice choice1 : choices) {
			System.out.println(choice1.getChoiceText());
		}
	}
}


