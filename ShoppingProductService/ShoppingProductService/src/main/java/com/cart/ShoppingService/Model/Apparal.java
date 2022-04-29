package com.cart.ShoppingService.Model;

import javax.persistence.AttributeOverride;
import javax.persistence.AttributeOverrides;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import org.springframework.stereotype.Component;

import com.cart.ShoppingService.Dto.ProductDto;

@Entity
@Table(name="apparal")
@AttributeOverrides({
    @AttributeOverride(name="productId", column=@Column(name="PRODUCTID")),
    @AttributeOverride(name="productName", column=@Column(name="PRODCUCTNAME")),
    @AttributeOverride(name="price", column=@Column(name="PRICE"))
})
public class Apparal extends Product{

	private String type;
	private String brand;
	private String design;
	
	public Apparal(Integer productId, String productName, float price) {
		super(productId, productName, price);
		// TODO Auto-generated constructor stub
	}
	
	public Apparal(Apparal apparal, ProductDto productDto) {
		// TODO Auto-generated constructor stub
		super(productDto.getId(), productDto.getName(), productDto.getPrice());
		this.brand = apparal.getBrand();
		this.design = apparal.getDesign();
		this.type = apparal.getType();
	}

	public Apparal() {
		// TODO Auto-generated constructor stub
	}

	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getBrand() {
		return brand;
	}
	public void setBrand(String brand) {
		this.brand = brand;
	}
	public String getDesign() {
		return design;
	}
	public void setDesign(String design) {
		this.design = design;
	}
}
