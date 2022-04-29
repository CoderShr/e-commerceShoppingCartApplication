package com.cart.ShoppingService.Service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.cart.ShoppingService.Model.Product;
import com.cart.ShoppingService.Repository.ShoppingCartRepository;

@Service
public class ShoppingCartService {
	
	@Autowired
	public ShoppingCartRepository shoppingCartRepository;

	public List<Product> addProductToCart(List<String> productIds) {
		List<Product> products = shoppingCartRepository.save(productIds);
		return products;
	}

}
