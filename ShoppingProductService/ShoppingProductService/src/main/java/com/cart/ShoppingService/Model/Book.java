package com.cart.ShoppingService.Model;

import javax.persistence.AttributeOverride;
import javax.persistence.AttributeOverrides;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import org.springframework.stereotype.Component;

import com.cart.ShoppingService.Dto.ProductDto;

@Entity
@Table(name="book")
@AttributeOverrides({
    @AttributeOverride(name="productId", column=@Column(name="PRODUCTID")),
    @AttributeOverride(name="productName", column=@Column(name="PRODCUCTNAME")),
    @AttributeOverride(name="price", column=@Column(name="PRICE"))
})
public class Book extends Product{
	
	private String genre;
	private String author;
	private String publications;
	

	public Book() {}
	public Book(Book book, ProductDto productDto) {
		super(productDto.getId(), productDto.getName(), productDto.getPrice());
		this.genre = book.getGenre();
		this.author = book.getAuthor();
		this.publications = book.getPublications();
		// TODO Auto-generated constructor stub
	}
	public Book(Integer productId, String productName, float price,
			String genre, String author, String publications) {
		super(productId, productName, price);
		this.author = author;
		this.genre = genre;
		this.publications = publications;
	}
	
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
