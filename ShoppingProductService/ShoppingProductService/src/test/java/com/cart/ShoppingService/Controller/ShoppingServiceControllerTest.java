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

import com.cart.ShoppingService.Dto.AddToCartDto;
import com.cart.ShoppingService.Dto.CartDto;
import com.cart.ShoppingService.Dto.CartItemDto;
import com.cart.ShoppingService.Model.Apparal;
import com.cart.ShoppingService.Model.Book;
import com.cart.ShoppingService.Model.Cart;
import com.cart.ShoppingService.Model.Product;
import com.cart.ShoppingService.Model.User;
import com.cart.ShoppingService.Service.ShoppingCartService;
import com.fasterxml.jackson.databind.ObjectMapper;
 
@WebMvcTest
public class ShoppingServiceControllerTest {
 
    @Autowired
    private MockMvc mockMvc;
 
    @MockBean
    private ShoppingCartService shoppingCartService;
 
    private static ObjectMapper mapper = new ObjectMapper();
 
    @Test
    public void testGetExample() throws Exception {
        Mockito.when(shoppingCartService.getCartItems(111)).thenReturn(getCartDto());
        mockMvc.perform(get("/getMapping").param("user-id", "111")).andExpect(status().isOk()).andExpect(jsonPath("$", Matchers.hasSize(1)))
                .andExpect(jsonPath("$.id", Matchers.equalTo(2)))
		.andExpect(jsonPath("$.product.catagory", Matchers.equalTo("Apparal")))
                .andExpect(jsonPath("$.quantity", Matchers.equalTo(4)));
    }
 
    @Test
    public void testPostExample() throws Exception {
        Mockito.when(shoppingCartService.addProductToCart(getAddToCartDto(), 111)).thenReturn(getCart());
        String json = mapper.writeValueAsString(getAddToCartDto());
        mockMvc.perform(post("/postMapping").param("user-id", "111").contentType(MediaType.APPLICATION_JSON).characterEncoding("utf-8")
                .content(json).accept(MediaType.APPLICATION_JSON)).andExpect(status().isCreated())
                .andExpect(jsonPath("$.id", Matchers.equalTo(1)));
    }
 
    @Test
    public void testPutExample() throws Exception {
    AddToCartDto dto = new AddToCartDto();
    dto.setQuantity(11);
    Mockito.when(shoppingCartService.updateCartItem(dto, 111)).thenReturn("updated");
	AddToCartDto cartDto = getAddToCartDto();
	cartDto.setQuantity(10);
        String json = mapper.writeValueAsString(cartDto);
        mockMvc.perform(put("/putMapping").param("user-id", "111").contentType(MediaType.APPLICATION_JSON).characterEncoding("utf-8")
                .content(json).accept(MediaType.APPLICATION_JSON)).andExpect(status().isOk());
    }
 
    @Test
    public void testDeleteExample() throws Exception {
        Mockito.when(shoppingCartService.deleteCartItem(ArgumentMatchers.anyInt(), ArgumentMatchers.anyInt()))
		.thenReturn(true);
        MvcResult requestResult = mockMvc.perform(delete("/deleteMapping").param("user-id", "111"))
                .andExpect(status().isOk()).andExpect(status().isOk()).andReturn();
        String result = requestResult.getResponse().getContentAsString();
        assertEquals(result, true);
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
	product3.setPublications("DC Books");
	product3.setGenre("Romcom");
	product3.setAuthor("preeti shinoy");

	productList.add(product3);

 	Apparal product4 = new Apparal();
	product4.setId(4);
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
    return productList;

    }

	private CartDto getCartDto(){
    List<CartItemDto> cartItemDtoList = new ArrayList<>();

	CartItemDto cartItemDto = new CartItemDto();
	
	Apparal apparal = new Apparal();
    apparal.setId(6);
    apparal.setProductName("Womans party wear");
    apparal.setPrice(860);
    apparal.setCatagory("Apparal");
    apparal.setDesign("Flared");
    apparal.setType("Gown");
    apparal.setBrand("vera moda");
	
	cartItemDto.setQuantity(4);
	cartItemDto.setId(1);
	cartItemDto.setProduct(apparal);

	cartItemDtoList.add(cartItemDto);

	CartDto dto = new CartDto(cartItemDtoList, 1236);

	return dto;

	}

 public AddToCartDto getAddToCartDto(){

    AddToCartDto addToCartDto = new AddToCartDto();
    addToCartDto.setProductId(4);
    addToCartDto.setQuantity(6);

    return addToCartDto;
}
 
 public Cart getCart(){

	    Cart cart = new Cart();
	    cart.setId(1);
	    cart.setQuantity(4);
	    cart.setUser(new User(111, "ram@gmail.com", "Ram"));
	 	Apparal product4 = new Apparal();
		product4.setId(4);
		product4.setProductName("Mens casuals");
		product4.setPrice(760);
		product4.setCatagory("Apparal");
		product4.setDesign("Skin fit");
		product4.setType("Jeans");
		product4.setBrand("LP");
	    cart.setProduct(product4);

	    return cart;
	}
}