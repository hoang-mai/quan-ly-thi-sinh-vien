package com.example.project.database.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "Choice", schema = "dbo")
public class Choice {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "choice_id")
	private int choiceId;

	@Column(name = "choice_text", nullable = false, columnDefinition = "nvarchar(1000)")
	private String choiceText;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "questions_id")
	private Questions questions;

	@Column(name = "Grade", nullable = false, columnDefinition = "int")
	private int grade;
	public Choice() {
		
	}
	
	public Choice(String choiceText, Questions questions) {
		this.choiceText = choiceText;
		this.questions = questions;
	}

	public int getChoiceId() {
		return choiceId;
	}

	public void setChoiceId(int choiceId) {
		this.choiceId = choiceId;
	}

	public String getChoiceText() {
		return choiceText;
	}

	public void setChoiceText(String choiceText) {
		this.choiceText = choiceText;
	}

	public Questions getQuestions() {
		return questions;
	}

	public void setQuestions(Questions questions) {
		this.questions = questions;
	}

	public int getGrade() {
		return grade;
	}

	public void setGrade(int grade) {
		this.grade = grade;
	}
}
