package com.cart.ShoppingService.Model;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.Table;

@Entity
@Table(name="user_profiles")
public class User {

	@Id
	private Integer id;
	
	private String name;
	
	private String email;
	
//	@JsonIgnore
//	@OneToOne(targetEntity=Cart.class)
//	@JoinColumn(nullable=false,name="cart_id")
//	private Cart cart;


	public User(int id, String email, String name) {
		this.id = id;
		this.name = name;
		this.email = email;
		
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}
	
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	@Override
	public String toString() {
		return "User [userId=" + id + ", name=" + name + ", email=" + email + "]";
	}
	
}
