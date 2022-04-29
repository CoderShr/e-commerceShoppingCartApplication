package com.cart.ShoppingService.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cart.ShoppingService.Dto.ProductDto;
import com.cart.ShoppingService.Model.Apparal;
import com.cart.ShoppingService.Model.Book;
import com.cart.ShoppingService.Model.Product;
import com.cart.ShoppingService.Repository.ProductRepository;

@Service
public class productService {
	
    @Autowired
    private ProductRepository productRepository;
	
	public void addProduct(ProductDto productDto) {
		//Product product = new Product();
	if(productDto.getCategory().equals("Book")) {
			 Book book = new Book();
			 book.setId(productDto.getId());
		     book.setProductName(productDto.getName());
		     book.setPrice(productDto.getPrice());
		     book.setCatagory(productDto.getCategory());
		     book.setAuthor(productDto.getBook().getAuthor());
		     book.setGenre(productDto.getBook().getGenre());
		     book.setPublications(productDto.getBook().getPublications());
		     productRepository.save(book);
		} else {
			Apparal apparal = new Apparal();
			apparal.setId(productDto.getId());
			apparal.setProductName(productDto.getName());
			apparal.setPrice(productDto.getPrice());
			apparal.setCatagory(productDto.getCategory());
			apparal.setBrand(productDto.getApparal().getBrand());
			apparal.setDesign(productDto.getApparal().getDesign());
			apparal.setType(productDto.getApparal().getType());
		    productRepository.save(apparal);
		}
	}
//	
//	[{"id":1,"productName":"Mens casual jeans","price":508.0,"catagory":"Apparal","type":"jeans","brand":"LP","design":"Stripped","productId":1},{"id":2,"productName":"shoot the moon","price":508.0,"catagory":"Book","genre":"fiction","author":"butler","publications":"DC books","productId":2},{"id":3,"productName":"Meluha","price":448.0,"catagory":"Book","genre":"fiction","author":"Amit","publications":"DC books","productId":3},{"id":4,"productName":"You are beautiful","price":203.0,"catagory":"Book","genre":"romantic","author":"Preet","publications":"VMR publishers","productId":4},{"id":5,"productName":"Woman causal Jumpsuits","price":900.0,"catagory":"Apparal","type":"Jumpsuit","brand":"Lara","design":"zigzag","productId":5},{"id":6,"productName":"Woman causal dress","price":450.0,"catagory":"Apparal","type":"dress","brand":"Diva","design":"Hem neck","productId":6},{"id":7,"productName":"Woman party wear skirt","price":750.0,"catagory":"Apparal","type":"Skirt","brand":"Nyka","design":"Short kength","productId":7}]
//}
}
