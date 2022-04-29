package com.cart.ShoppingService.Controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
 
import java.util.ArrayList;
import java.util.List;
 
import org.hamcrest.Matchers;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentMatchers;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import com.cart.ShoppingService.Model.Apparal;
import com.cart.ShoppingService.Model.Book;
import com.cart.ShoppingService.Model.Product;
import com.cart.ShoppingService.Service.ShoppingProductService;
import com.fasterxml.jackson.databind.ObjectMapper;
 
@WebMvcTest
public class ProductControllerTest {
 
    @Autowired
    private MockMvc mockMvc;
 
    @MockBean
    private ShoppingProductService shoppingProductService;
 
    private static ObjectMapper mapper = new ObjectMapper();
 
    @Test
    public void testGetProduct() throws Exception {
        Mockito.when(shoppingProductService.getProducts()).thenReturn(getMockedProducts());
        mockMvc.perform(get("/getMapping")).andExpect(status().isOk()).andExpect(jsonPath("$", Matchers.hasSize(6)))
                .andExpect(jsonPath("$[0].name", Matchers.equalTo("God of little things")));
    }


 private List<Product> getMockedProducts(){
    List<Product> productList = new ArrayList<>();

    Book product1 = new Book();
	product1.setId(1);
	product1.setProductName("God of little things");
	product1.setPrice(560);
	product1.setCatagory("Book");
	product1.setPublications("DC Books");
	product1.setGenre("Fiction");
	product1.setAuthor("Arundadi roy");
	
	productList.add(product1);


	Book product2 = new Book();
	product2.setId(2);
	product2.setProductName("Meluha");
	product2.setPrice(450);
	product2.setCatagory("Book");
	product2.setPublications("SKM publications");
	product2.setGenre("Historical Fiction");
	product2.setAuthor("Amit");

	productList.add(product2);

 	Book product3 = new Book();
	product3.setId(3);
	product3.setProductName("you are beautiful");
	product3.setPrice(360);
	product3.setCatagory("Book");
	product3.setPublications("DC Book");
	product3.setGenre("Romcom");
	product3.setAuthor("preeti shinoy");

	productList.add(product3);

 	Apparal product4 = new Apparal();
	product4.setProductId(4);
	product4.setProductName("Mens casuals");
	product4.setPrice(760);
	product4.setCatagory("Apparal");
	product4.setDesign("Skin fit");
	product4.setType("Jeans");
	product4.setBrand("LP");

	productList.add(product4);

	Apparal product5 = new Apparal();
	product5.setId(5);
	product5.setProductName("Womans casuals");
	product5.setPrice(460);
	product5.setCatagory("Apparal");
	product5.setDesign("Pleated");
	product5.setType("Skirt");
	product5.setBrand("Lara");

	productList.add(product5);

	Apparal product6 = new Apparal();
	product6.setId(6);
	product6.setProductName("Womans party wear");
	product6.setPrice(860);
	product6.setCatagory("Apparal");
	product6.setDesign("Flared");
	product6.setType("Gown");
	product6.setBrand("vera moda");

	productList.add(product6);

	return productList;

    }

}
