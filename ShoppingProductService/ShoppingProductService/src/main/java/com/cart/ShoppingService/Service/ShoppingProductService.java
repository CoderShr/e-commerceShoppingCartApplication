package com.cart.ShoppingService.Service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cart.ShoppingService.Model.Product;
import com.cart.ShoppingService.Repository.ProductRepository;

@Service
public class ShoppingProductService {
	
	@Autowired
	public ProductRepository productRepository;

	public List<Product> getProducts() {
		return productRepository.findAll();
	}
	
	public Product getProductById(Integer productId) throws Exception{
		Optional<Product> productDto = productRepository.findById(productId);
		if(!productDto.isPresent()) {
			throw new Exception("product not found");
		}
		return productDto.get();
		
	}

	public Product getProductsByName(String productName) throws Exception {
		Optional<Product> product = productRepository.findByProductName(productName);
		return getProduct(product);
	}

	public List<Product> getProductsByCatagory(String productCatagory) throws Exception {
		Optional<List<Product>> productList = productRepository.findByCatagory(productCatagory);
		if(!productList.isPresent()) {
			throw new Exception("product not found");
		}
		return productList.get();
	}
	
	private Product getProduct(Optional<Product> product) throws Exception {
		if(!product.isPresent()) {
			throw new Exception("product not found");
		}
		return product.get();
	}
	
}
