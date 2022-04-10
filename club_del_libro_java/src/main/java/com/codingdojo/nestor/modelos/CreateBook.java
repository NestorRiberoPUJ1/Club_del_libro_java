package com.codingdojo.nestor.modelos;

import javax.validation.constraints.NotEmpty;

public class CreateBook {

	@NotEmpty(message = "El titulo no puede ser vacío")
	private String name;

	@NotEmpty(message = "El autor no puede ser vacío")
	private String author;

	@NotEmpty(message = "La reseña no puede ser vacía")
	private String thought;

	private User user;

	public CreateBook() {

	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getAuthor() {
		return author;
	}

	public void setAuthor(String author) {
		this.author = author;
	}

	public String getThought() {
		return thought;
	}

	public void setThought(String thought) {
		this.thought = thought;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

}
