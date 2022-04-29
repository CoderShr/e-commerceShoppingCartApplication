package com.cart.ShoppingService.Dto;

import com.cart.ShoppingService.Model.Apparal;
import com.cart.ShoppingService.Model.Book;
import com.cart.ShoppingService.Model.Product;

public class ProductDto {

    private  Integer id;
    private  String name;
    private  float price;
    private  String category;
    private Book book;
	private Apparal apparal;

    public Book getBook() {
		return book;
	}

	public void setBook(Book book) {
		this.book = book;
	}

	public Apparal getApparal() {
		return apparal;
	}

	public void setApparal(Apparal apparal) {
		this.apparal = apparal;
	}

    public ProductDto(Product product) {
        this.setId(product.getId());
        this.setName(product.getProductName());
        this.setPrice(product.getPrice());
        this.setCategory(product.getCatagory());
    }

    public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public ProductDto( String name, float price) {
        this.name = name;
        this.price = price;
    }

    public ProductDto() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

}
