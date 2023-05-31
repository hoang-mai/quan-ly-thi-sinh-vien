package com.example.project.database.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "choice", schema = "dbo")
public class Choice {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "chocie_id")
	private int choiceId;

	@Column(name = "choice_text", nullable = false, columnDefinition = "TEXT")
	private String choiceText;

	@ManyToOne()
	@JoinColumn(name = "questions_id")
	private Questions questions;
	
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

}
