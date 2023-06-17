package com.example.project.database.entities;

import java.util.Set;

import javax.persistence.*;

@Entity
@Table(name = "Question", schema = "dbo")
public class Questions {
	@Id
	@GeneratedValue
	@Column(name = "question_id")
	private int questionId;

	@Column(name = "question_name", nullable = false, columnDefinition = "TEXT")
	private String questionName = "";

	@Column(name = "question_text", nullable = false, columnDefinition = "TEXT")
	private String questionText = "";

	@Lob
	@Column(name = "image")
	private Byte[] image;

	@Column(name = "answer", nullable = false, columnDefinition = "NVARCHAR(50)")
	private String answer = "A";
	
	@ManyToMany
	@JoinTable(name = "question_quiz", schema = "dbo", joinColumns = {
			@JoinColumn(name = "question_id") }, inverseJoinColumns = { @JoinColumn(name = "quiz_id") })
	private Set<Quiz> quiz;

	@OneToMany(mappedBy = "questions", fetch = FetchType.LAZY)
	private Set<Choice> choice;
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "category_id")
	private Categories categories;

	public Questions() {
		
	}
	
	public Questions(String questionName, String questionText, Byte[] image, String answer,Categories categories, Set<Quiz> quiz,
			Set<Choice> choice) {
		super();
		this.questionName = questionName;
		this.questionText = questionText;
		this.image = image;
		this.answer = answer;
		this.quiz = quiz;
		this.choice = choice;
		this.categories=categories;
	}

	public Categories getCategories() {
		return categories;
	}

	public void setCategories(Categories categories) {
		this.categories = categories;
	}

	public int getQuestionId() {
		return questionId;
	}

	public void setQuestionId(int questionId) {
		this.questionId = questionId;
	}

	public String getQuestionName() {
		return questionName;
	}

	public void setQuestionName(String questionName) {
		this.questionName = questionName;
	}

	public String getQuestionText() {
		return questionText;
	}

	public void setQuestionText(String questionText) {
		this.questionText = questionText;
	}

	public Byte[] getImage() {
		return image;
	}

	public void setImage(Byte[] image) {
		this.image = image;
	}

	public String getAnswer() {
		return answer;
	}

	public void setAnswer(String answer) {
		this.answer = answer;
	}

	public Set<Quiz> getQuiz() {
		return quiz;
	}

	public void setQuiz(Set<Quiz> quiz) {
		this.quiz = quiz;
	}

	public Set<Choice> getChoice() {
		return choice;
	}

	public void setChoice(Set<Choice> choice) {
		this.choice = choice;
	}

}
