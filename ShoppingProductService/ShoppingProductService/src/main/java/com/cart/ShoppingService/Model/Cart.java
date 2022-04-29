package com.cart.ShoppingService.Model;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name="cart")
public class Cart{

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	@JsonIgnore
	@OneToOne(targetEntity=User.class)
	@JoinColumn(nullable=false,name="user_id")
	private User user;
	
//	@OneToMany
//	private Collection<ProductItem> productItems = new ArrayList<ProductItem>();
	
	private int quantity;
//	
//	@JsonIgnore
//	@OneToMany
//	private Product product;
	
	 @ManyToOne
	 @JoinColumn(name = "product_id", referencedColumnName = "id")
	 private Product product;
	
	public Cart() {
		
	}
	
	public Cart(User user, Product product, int quantity) {
		this.quantity = quantity;
		this.user = user;
		this.product = product;
		
	}
	
	public Product getProduct() {
		return product;
	}

	public void setProduct(Product product) {
		this.product = product;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	

	
//	public Collection<ProductItem> getProductItems() {
//		return productItems;
//	}
//	public void setProductItems(Collection<ProductItem> productItems) {
//		this.productItems = productItems;
//	} 

}