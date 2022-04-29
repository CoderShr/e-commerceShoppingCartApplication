package com.cart.ShoppingService.Model;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.MappedSuperclass;
import javax.persistence.OneToMany;

import com.cart.ShoppingService.Dto.ProductDto;
import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class Product {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	private
	// @NotNull
	String productName;
	private
	// @NotNull
	float price;
//	@ManyToOne
//	@JsonIgnore
//	@JoinColumn(name="id",nullable=false)
//	public Cart cart;
//	
	String catagory;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getCatagory() {
		return catagory;
	}

	public void setCatagory(String catagory) {
		this.catagory = catagory;
	}

	@JsonIgnore
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "product")
	private List<Cart> carts;

	public Product() {
	}

	public Product(Integer productId, String productName, float price) {
		this.id = productId;
		this.productName = productName;
		this.price = price;
	}

	public Product(ProductDto productDto, String category) {
		this.catagory = productDto.getCategory();
		this.id = productDto.getId();
		this.productName = productDto.getName();
		this.price = productDto.getPrice();
	}

	public Integer getProductId() {
		return id;
	}

	public void setProductId(Integer productId) {
		this.id = productId;
	}

	public String getProductName() {
		return productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}

	public float getPrice() {
		return price;
	}

	public void setPrice(float price) {
		this.price = price;
	}
}
