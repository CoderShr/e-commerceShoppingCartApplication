package com.cart.ShoppingService.Controller;

import java.util.List;
import java.util.Optional;

import javax.websocket.server.PathParam;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cart.ShoppingService.Dto.ProductDto;
import com.cart.ShoppingService.Model.Product;
import com.cart.ShoppingService.Service.ShoppingProductService;
import com.cart.ShoppingService.Service.productService;

import io.swagger.annotations.ApiResponse;

@RestController
@RequestMapping("/products")
public class ProductController {
	
	@Autowired
	public ShoppingProductService shoppingProductService;
	
	@Autowired
	public productService pproductService;
	
	//show all products
	@GetMapping("/")
    public ResponseEntity<List<Product>> getProducts() {
        List<Product> body = shoppingProductService.getProducts();
        return new ResponseEntity<List<Product>>(body, HttpStatus.OK);
    }
	
	// search and show particular item by product ID
    @GetMapping("/productsById/{productId}")
	public ResponseEntity<Product> showProductItemByID(@PathVariable(value = "productId") Integer productId) throws Exception {
    	Product body =  shoppingProductService.getProductById(productId);
        return new ResponseEntity<Product>(body, HttpStatus.OK);
      
	}

	
    
    // Get particular item by product category
    @GetMapping("/productsByCatagory/{productCatagory}")
	public ResponseEntity<List<Product>> showProductItemByCatagory(@PathVariable String productCatagory) throws Exception {  
    	List<Product> body = shoppingProductService.getProductsByCatagory(productCatagory);     
    	 return new ResponseEntity<List<Product>>(body, HttpStatus.OK);	        	
	}	
    
    
	// Get particular item by product name
	@GetMapping("/productsByName/{productName}")
	public ResponseEntity<Product> showProductItemByName(@PathVariable String productName) throws Exception {
		Product body = shoppingProductService.getProductsByName(productName);    
   	 	return new ResponseEntity<Product>(body, HttpStatus.OK);
	}
	

	//add products
    @PostMapping("/add")
    public ResponseEntity<String> addProduct(@RequestBody ProductDto productDto) {
        pproductService.addProduct(productDto);
        return new ResponseEntity<String>( "Product has been added", HttpStatus.CREATED);
    }
    
    
//    INSERT into PRODUCT values(0,23,540,'skirt','Vanesa','stripped','western',' ',' ',' ');
//    INSERT into USER_PROFILES values(111,'Ram@gmail.com','Ram');
//    INSERT into CART values(2,7,24,112);
//
//    SELECT * FROM PRODUCT ;
//    SELECT * FROM USER_PROFILES ;
//    SELECT * FROM CART ;
}
