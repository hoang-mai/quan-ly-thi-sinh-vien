package com.example.project.database.entities;

import java.sql.Date;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "Quiz", schema = "dbo")
public class Quiz {
	@Id
	@GeneratedValue
	@Column(name = "quiz_id")
	private int quizId;

	@Column(name = "quiz_name", nullable = false, unique = true, columnDefinition = "TEXT")
	private String quizName;

	@Column(name = "description", nullable = false, columnDefinition = "TEXT")
	private String description;

	@Column(name = "time_open", nullable = false, columnDefinition = "DATETIME")
	private Date timeOpen;

	@Column(name = "time_close", nullable = false, columnDefinition = "DATETIME")
	private Date timeClose;

	@Column(name = "time_limit", nullable = false, columnDefinition = "DATETIME")
	private Date timeLimit;

	@Column(name = "grade", nullable = false, columnDefinition = "REAL")
	private double grade;

	@ManyToOne
	@JoinColumn(name = "user_id")
	private User user;

	@ManyToMany(mappedBy = "quiz")
	private Set<Questions> questions;

	@ManyToMany
	@JoinTable(name = "quiz_categories", schema = "dbo", joinColumns = {
			@JoinColumn(name = "quiz_id") }, inverseJoinColumns = { @JoinColumn(name = "category_id") })
	private Set<Categories> categories;
	
	public Quiz() {
		
	}
	
	public Quiz(String quizName, String description, Date timeOpen, Date timeClose, Date timeLimit, double grade,
			User user, Set<Questions> questions, Set<Categories> categories) {
		this.quizName = quizName;
		this.description = description;
		this.timeOpen = timeOpen;
		this.timeClose = timeClose;
		this.timeLimit = timeLimit;
		this.grade = grade;
		this.user = user;
		this.questions = questions;
		this.categories = categories;
	}

	public int getQuizId() {
		return quizId;
	}

	public void setQuizId(int quizId) {
		this.quizId = quizId;
	}

	public String getQuizName() {
		return quizName;
	}

	public void setQuizName(String quizName) {
		this.quizName = quizName;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Date getTimeOpen() {
		return timeOpen;
	}

	public void setTimeOpen(Date timeOpen) {
		this.timeOpen = timeOpen;
	}

	public Date getTimeClose() {
		return timeClose;
	}

	public void setTimeClose(Date timeClose) {
		this.timeClose = timeClose;
	}

	public Date getTimeLimit() {
		return timeLimit;
	}

	public void setTimeLimit(Date timeLimit) {
		this.timeLimit = timeLimit;
	}

	public double getGrade() {
		return grade;
	}

	public void setGrade(double grade) {
		this.grade = grade;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Set<Questions> getQuestions() {
		return questions;
	}

	public void setQuestions(Set<Questions> questions) {
		this.questions = questions;
	}

	public Set<Categories> getCategories() {
		return categories;
	}

	public void setCategories(Set<Categories> categories) {
		this.categories = categories;
	}

}
