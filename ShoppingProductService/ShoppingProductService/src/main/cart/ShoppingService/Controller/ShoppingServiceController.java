package com.cart.ShoppingService.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cart.ShoppingService.Model.Product;
import com.cart.ShoppingService.Service.ShoppingCartService;


@RestController
public class ShoppingServiceController {
	
	@Autowired
	public ShoppingCartService shoppingCartService;

	 
    // Home Page
    @GetMapping("/")
    public String welcome()
    {
        return "<html><body>"
            + "<h1>WELCOME</h1>"
            + "</body></html>";
    }
  
	
//	
//	@PostMapping("/addProducts}")
//	public void addProductsToCart(@PathVariable List<String> productIds) {
//		List<Product> products = shoppingCartService.addProductToCart(productIds);
//	}
}
