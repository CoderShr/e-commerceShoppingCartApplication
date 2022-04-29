package com.cart.ShoppingService.Model;

import org.springframework.stereotype.Component;

@Component
public class Book extends Product{
	
	public String genre;
	public String author;
	public String publications;
	
	public String getGenre() {
		return genre;
	}
	public void setGenre(String genre) {
		this.genre = genre;
	}
	public String getAuthor() {
		return author;
	}
	public void setAuthor(String author) {
		this.author = author;
	}
	public String getPublications() {
		return publications;
	}
	public void setPublications(String publications) {
		this.publications = publications;
	}

}
