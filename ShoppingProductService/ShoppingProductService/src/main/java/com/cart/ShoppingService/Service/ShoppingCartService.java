package com.cart.ShoppingService.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cart.ShoppingService.Dto.AddToCartDto;
import com.cart.ShoppingService.Dto.CartDto;
import com.cart.ShoppingService.Dto.CartItemDto;
import com.cart.ShoppingService.Dto.ProductDto;
import com.cart.ShoppingService.Model.Apparal;
import com.cart.ShoppingService.Model.Book;
import com.cart.ShoppingService.Model.Cart;
import com.cart.ShoppingService.Model.Product;
import com.cart.ShoppingService.Model.User;
import com.cart.ShoppingService.Repository.ProductRepository;
import com.cart.ShoppingService.Repository.ShoppingCartRepository;

@Service
public class ShoppingCartService {
	
	public UserService userService;
	public ShoppingCartRepository shoppingCartRepository;
	public ProductRepository productRepository;
	public ShoppingProductService shoppingProductService;

	@Autowired
	public ShoppingCartService(ShoppingCartRepository shoppingCartRepository,
		ProductRepository productRepository, ShoppingProductService shoppingProductService,
			UserService userService) {
		this.shoppingCartRepository = shoppingCartRepository;
		this.productRepository = productRepository;
		this.userService = userService;
		this.shoppingProductService = shoppingProductService;
	}

	public Cart addProductToCart(AddToCartDto addToCartDto, Integer userId) throws Exception{
		User user = userService.getUser(userId);
		Cart cart = null;
		if(Objects.nonNull(user)) {
			enrichCartAndSave(addToCartDto, user, cart);
		} else {
			throw new Exception("User not found");
			
		}
		return cart;
    }
	
	public CartDto getCartItems(Integer userId) {
	        List<CartItemDto> cartItems = new ArrayList<>();
	        for (Cart cart:getCartItemsOfUser(userId)){
//    			Optional<Product> productDto = productRepository.findById(cart.getProduct().getId()); 
//        		if(cart.getProduct().getCatagory() =="Book") {   
//        			enrichBookModel(productDto, cart); 
//            	} else if(cart.getProduct().getCatagory() =="Apparal") {
//            		enrichApparalModel(cart, productDto);
//            	}
//        		
	            CartItemDto cartItemDto = new CartItemDto(cart);
	            cartItems.add(cartItemDto);
	        }
	        double totalCost = 0;
	        for (CartItemDto cartItemDto :cartItems){
	            totalCost += (cartItemDto.getProduct().getPrice()* cartItemDto.getQuantity());
	        }
	        return new CartDto(cartItems, totalCost);
			
	}

	private Cart enrichApparalModel(Cart cart, Optional<ProductDto> apparal) {
		if(apparal.isPresent()) {
		Apparal product = new Apparal(apparal.get().getApparal(), apparal.get());
//		product.setBrand(apparal.get()..getBrand());
//		product.setDesign(apparal.get().getDesign());
//		product.setType(apparal.get().getType());
//		product.setCatagory(apparal.get().getCatagory());
//		product.setId(apparal.get().getId());
//		product.setProductName(apparal.get().getProductName());
//		product.setPrice(apparal.get().getPrice());
		cart.setProduct(product);
		}
		return cart;
	}

	private Cart enrichBookModel(Optional<ProductDto> book, Cart cart) {
		if(book.isPresent()) {
			Book product = new Book(book.get().getBook(),book.get());
//    		product.setAuthor(book.get().getAuthor());
//    		product.setGenre(book.get().getGenre());
//    		product.setPublications(book.get().getPublications());
//    		product.setCatagory(book.get().getCatagory());
//    		product.setId(book.get().getId());
//    		product.setProductName(book.get().getProductName());
//    		product.setPrice(book.get().getPrice());
    		cart.setProduct(product);
		}
		return cart;
	}

	@Transactional
	public Boolean deleteCartItem(int userId,int productId) throws Exception {
        if (!findUserCartItemByProductId(productId, userId).isPresent()) {
            throw new Exception("Cart item doesnot exist exception for the product : " + productId);      
        }
        shoppingCartRepository.deleteByProductId(productId);
        return true;

    }

	@Transactional
    public void deleteUserCartItems(int user) {
    	shoppingCartRepository.deleteAllByUserId(user);
    }


	public String updateCartItem(AddToCartDto cartDto, Integer userId) throws Exception {				
        if(cartDto.getQuantity() == 0) {
        	return deleteIfZeroQuantity(cartDto, userId);
        } else {       	
        return updateCart(cartDto, userId);
    
        }
	}

	private String updateCart(AddToCartDto cartDto, Integer userId) throws Exception {
		Optional<Cart> cart;
		try {
			cart = findUserCartItemByProductId(cartDto.getProductId(), userId);
		} catch (Exception e) {
			throw new Exception("cart item not found for the product, failed to update");
		}
        cart.get().setQuantity(cartDto.getQuantity());		
        shoppingCartRepository.save(cart.get());
        return "updated";
	}

	private String deleteIfZeroQuantity(AddToCartDto cartDto, Integer userId) throws Exception {
		try {
			deleteCartItem(cartDto.getProductId(), userId);
		} catch (Exception e) {
			throw new Exception("cart item not found for the product, failed to delete");
		}
		return "deleted";
	}

	private Optional<Cart> findUserCartItemByProductId(Integer productId, Integer userId) throws Exception {
		Optional<Cart> cart = getCartItemsOfUser(userId).stream()
        			.filter(cartItem -> cartItem.getProduct().getProductId().equals(productId)).findFirst();
        if(!cart.isPresent()){  
        	throw new Exception("product not found");
        }
		return cart;
	}
	
	private List<Cart> getCartItemsOfUser(Integer userId) {
		List<Cart> cartList = shoppingCartRepository.findAllByUser(getUser(userId));
		return cartList;
	}

	private User getUser(Integer userId) {
		User user = userService.getUser(userId);
		return user;
	}
	

	private void enrichCartAndSave(AddToCartDto addToCartDto, User user, Cart cart) throws Exception {
		
		Optional<List<Cart>> cartExisting = Optional.ofNullable(shoppingCartRepository.findAllByUser(user));			
			Optional<Cart> cartItemExisting = cartExisting.isPresent() 
					? (cartExisting.get().stream()
					.filter(cartItem -> cartItem.getProduct().getProductId().equals(addToCartDto.getProductId())).findFirst())
					:(null);
			
			if(cartItemExisting.isPresent()) {
				cartItemExisting.get().setQuantity(addToCartDto.getQuantity());
				cart = cartItemExisting.get();
			}
			else {
				Product product = shoppingProductService.getProductById(addToCartDto.getProductId());
				cart = new Cart();
				cart.setProduct(product);
				cart.setUser(user);
				cart.setQuantity(addToCartDto.getQuantity());
		 	}			
			shoppingCartRepository.save(cart);
	}
}
