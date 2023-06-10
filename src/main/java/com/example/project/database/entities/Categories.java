package com.example.project.database.entities;

import java.util.Set;

import javax.persistence.*;

@Entity
@Table(name = "Categories", schema = "dbo")
public class Categories {
	@Id
	@Column(name = "category_id")
	@GeneratedValue
	private int categoryId;

	@Column(name = "category_name", nullable = false, columnDefinition = "TEXT")
	private String categoryName;

	@Column(name = "category_info", nullable = false, columnDefinition = "TEXT")
	private String categoryInfo;

	@Column(name = "question_count")
	private int questionCount;

	@ManyToMany(mappedBy = "categories")
	private Set<Quiz> quiz;

	
	public Categories() {
		
	}
	
	public Categories(String categoryName, String categoryInfo, int questionCount, Set<Quiz> quiz) {
		this.categoryName = categoryName;
		this.categoryInfo = categoryInfo;
		this.questionCount = questionCount;
		this.quiz = quiz;
	}

	public int getCategoryId() {
		return categoryId;
	}

	public void setCategoryId(int categoryId) {
		this.categoryId = categoryId;
	}

	public String getCategoryName() {
		return categoryName;
	}

	public void setCategoryName(String categoryName) {
		this.categoryName = categoryName;
	}

	public String getCategoryInfo() {
		return categoryInfo;
	}

	public void setCategoryInfo(String categoryInfo) {
		this.categoryInfo = categoryInfo;
	}

	public int getQuestionCount() {
		return questionCount;
	}

	public void setQuestionCount(int questionCount) {
		this.questionCount = questionCount;
	}

	public Set<Quiz> getQuiz() {
		return quiz;
	}

	public void setQuiz(Set<Quiz> quiz) {
		this.quiz = quiz;
	}

}
