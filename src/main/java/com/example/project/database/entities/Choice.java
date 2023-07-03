package com.example.project.database.entities;

import javax.persistence.*;

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

	@Column(name = "Grade", nullable = false, columnDefinition = "NVARCHAR(6)")
	private String grade;
	@Lob
	@Column(name = "image", nullable = true,columnDefinition = "VARBINARY(MAX)")
	private byte[] image;

	public byte[] getImage() {
		return image;
	}

	public void setImage(byte[] image) {
		this.image = image;
	}

	public Choice() {
		
	}
	
	public Choice(String choiceText, Questions questions, String grade, byte[] image) {
		this.choiceText = choiceText;
		this.questions = questions;
		this.grade = grade;
		this.image = image;
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

	public String getGrade() {
		return grade;
	}

	public void setGrade(String grade) {
		this.grade = grade;
	}
}
