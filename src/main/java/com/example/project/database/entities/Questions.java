package com.example.project.database.entities;

import javafx.scene.image.Image;

import java.util.Set;

import javax.persistence.*;

@Entity
@Table(name = "Question", schema = "dbo")
public class Questions {
	@Id
	@GeneratedValue
	@Column(name = "question_id")
	private int questionId;
	//@Column(name = "question_default_mark", nullable = false, columnDefinition = "DOUBLE")
	//private double questionDefaultMark ;

	//public double getQuestionDefaultMark() {
	//	return questionDefaultMark;
	//}

	//public void setQuestionDefaultMark(double questionDefaultMark) {
	//	this.questionDefaultMark = questionDefaultMark;
	//}

	@Column(name = "question_name", nullable = false, columnDefinition = "NVARCHAR(100)")
	private String questionName = "";

	@Column(name = "question_text", nullable = false, columnDefinition = "NVARCHAR(1000)")
	private String questionText = "";

	@Lob
	@Column(name = "image", nullable = true,columnDefinition = "VARBINARY(MAX)")
	private byte[] image;

		@ManyToMany
	@JoinTable(name = "question_quiz", schema = "dbo", joinColumns = {
			@JoinColumn(name = "question_id") }, inverseJoinColumns = { @JoinColumn(name = "quiz_id") })
	private Set<Quiz> quiz;

	@OneToMany(mappedBy = "questions", fetch = FetchType.LAZY)
	private Set<Choice> choice;
		@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "category_id")
	private Categories categories;
	@Column(name = "DefaultMark", nullable = false, columnDefinition = "INT")
	private int defaultmark;

	public Questions() {
		
	}
	
	public Questions(String questionName, String questionText, byte[] image, String answer,Categories categories, Set<Quiz> quiz,
			Set<Choice> choice) {
		super();
		this.questionName = questionName;
		this.questionText = questionText;
		this.image = image;
		//this.answer = answer;
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

	public byte[] getImage() {
		return image;
	}

	public void setImage(byte[] image) {
		this.image = image;
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

	public int getDefaultmark() {
		return defaultmark;
	}

	public void setDefaultmark(int defaultmark) {
		this.defaultmark = defaultmark;
	}
}
