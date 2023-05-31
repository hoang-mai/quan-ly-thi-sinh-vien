package com.example.project.database.entities;

import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "Users", schema = "dbo")
public class User {
	@Id
	@GeneratedValue
	@Column(name = "user_id")
	private int userId;

	@Column(name = "username", nullable = false, unique = true, columnDefinition = "NVARCHAR(50)")
	private String username;

	@Column(name = "password", nullable = false, columnDefinition = "NVARCHAR(50)")
	private String password;

	@Column(name = "full_name", columnDefinition = "NVARCHAR(50)")
	private String fullName;

	@OneToMany(mappedBy = "user")
	private Set<Quiz> quiz;

	public User() {

	}

	public User(String username, String password, String fullName, Set<Quiz> quiz) {
		this.username = username;
		this.password = password;
		this.fullName = fullName;
		this.quiz = quiz;
	}

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getFullName() {
		return fullName;
	}

	public void setFullName(String fullName) {
		this.fullName = fullName;
	}

	public Set<Quiz> getQuiz() {
		return quiz;
	}

	public void setQuiz(Set<Quiz> quiz) {
		this.quiz = quiz;
	}

}
