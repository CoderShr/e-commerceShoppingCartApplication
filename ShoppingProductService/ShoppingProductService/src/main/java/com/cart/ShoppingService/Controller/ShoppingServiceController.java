package com.cart.ShoppingService.Controller;

import java.util.Objects;

import javax.websocket.server.PathParam;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.cart.ShoppingService.Dto.AddToCartDto;
import com.cart.ShoppingService.Dto.CartDto;
import com.cart.ShoppingService.Model.Cart;
import com.cart.ShoppingService.Model.Product;
import com.cart.ShoppingService.Model.User;
import com.cart.ShoppingService.Service.ShoppingCartService;
import com.cart.ShoppingService.Service.ShoppingProductService;
import com.cart.ShoppingService.Service.UserService;

import io.swagger.annotations.ApiResponse;
import net.bytebuddy.dynamic.DynamicType.Builder.FieldDefinition.Optional;


@RestController
@RequestMapping("/cart")
public class ShoppingServiceController {
	
	@Autowired
	public ShoppingCartService shoppingCartService;
	
	@Autowired
	public ShoppingProductService shoppingProductService;

	@Autowired
	UserService userService;
	 
    // Home Page
    @GetMapping("/")
    public String welcome()
    {
        return "<html><body>"
            + "<h1>WELCOME</h1>"
            + "</body></html>";
    }
  
	
	// Add product to cart
    @PostMapping("/add")
    public ResponseEntity<Cart> addToCart(@RequestBody AddToCartDto addToCartDto,
                                                 @RequestParam("token") Integer userId) throws Exception {
//        authenticationService.authenticate(token);
//        User user = authenticationService.getUser(token);
        Cart cart = shoppingCartService.addProductToCart(addToCartDto, userId);
        return new ResponseEntity<Cart>(cart, HttpStatus.CREATED);

    }
    
    // to list cart items
    @GetMapping("/cartItems")
    public ResponseEntity<CartDto> getCartItems(@RequestParam("token") Integer userId)  {
        CartDto cart = shoppingCartService.getCartItems(userId);
        return new ResponseEntity<CartDto>(cart,HttpStatus.OK);
    }
    
    @PutMapping("/update")
    public ResponseEntity<String> updateCartItem(@RequestBody 
    		//@Valid 
    	AddToCartDto cartDto,@RequestParam("token") Integer userId) throws Exception{ 
        String cart = shoppingCartService.updateCartItem(cartDto, userId);
        return new ResponseEntity<String>(cart,HttpStatus.OK);
    }

    @DeleteMapping("/delete")
    public ResponseEntity<Boolean> deleteCartItem(@RequestParam("userId") int userId,@RequestParam("productId") int productId) throws Exception{
        Boolean deleted = shoppingCartService.deleteCartItem(userId, productId);
        return new ResponseEntity<Boolean>(deleted,HttpStatus.OK);
    }
    
    //delete all cart item
    @DeleteMapping("/deleteAllCartItems")
    public ResponseEntity<String> deleteAllCartItems(@RequestParam("userId") Integer userId) {
			 shoppingCartService.deleteUserCartItems(userId);
			 return new ResponseEntity<String>("deleted all",HttpStatus.OK);
	}
     
    @GetMapping("/users/{userId}")
   	public String showUser(@PathVariable Integer userId) {
    	User user = userService.getUser(userId);
   		{

   			 return "<html><body>"
		            + "<h1>" + "cart by prod catagory = " +
		            user.toString() 
		        		+"</h1>"
		            + "</body></html>";
   		        		
   		 }
       }
    
}
