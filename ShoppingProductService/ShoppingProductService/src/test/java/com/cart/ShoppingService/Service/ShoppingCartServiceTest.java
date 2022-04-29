//
//package com.cart.ShoppingService.Service;
//
//import static org.mockito.Mockito.when;
//
//import java.util.ArrayList;
//import java.util.Collection;
//import java.util.List;
//import java.util.Optional;
//
//import javax.persistence.EntityExistsException;
//import javax.persistence.NoResultException;
//import javax.transaction.Transactional;
//
//import org.junit.After;
//import org.junit.Assert;
//import org.junit.Before;
//import org.junit.Test;
//import org.mockito.InjectMocks;
//import org.mockito.MockitoAnnotations;
//import org.springframework.beans.factory.annotation.Autowired;
//
//import com.cart.ShoppingService.AbstractTest;
//import com.cart.ShoppingService.Dto.AddToCartDto;
//import com.cart.ShoppingService.Dto.CartDto;
//import com.cart.ShoppingService.Model.Apparal;
//import com.cart.ShoppingService.Model.Book;
//import com.cart.ShoppingService.Model.Cart;
//import com.cart.ShoppingService.Model.Product;
//import com.cart.ShoppingService.Model.User;
//import com.cart.ShoppingService.Repository.ProductRepository;
//import com.cart.ShoppingService.Repository.ShoppingCartRepository;
//
//@Transactional
//public class ShoppingCartServiceTest {
//
//	@Autowired
//	public ShoppingProductService shoppingProductService;
//	@Autowired
//	public ProductRepository productRepository;
//	@Autowired
//	public UserService userService;
//	@Autowired
//	public ShoppingCartRepository shoppingCartRepository;
//	@Autowired
//	public AddToCartDto addToCartDto;
//	
//	@InjectMocks
//	@Autowired
//	public ShoppingCartService shoppingCartService;
//
//	@Before
//    	public void setUp() {
//            MockitoAnnotations.openMocks(this);
//    	}
//
//    	@Test
//    	public void testAddToCart() {
//
//        System.out.println("adding Products to cart");
// 	User user = new User(111, "ella", "Ella@gmail.com");
//	addToCartDto.setQuantity(10);
//	addToCartDto.setProductId(3);
//
//    when(userService.getUser(111)).thenReturn(user);
//	when(shoppingCartRepository.findAllByUser(user)).thenReturn(getMockedProducts());
//	when(shoppingProductService.getProductById(3)).thenReturn(getProduct());
//
//        Cart result = shoppingCartRepository.addProductToCart(addToCartDto, 111);
//     
//        // verify expected results
//
//	verify(userService, times(1)).getUser(111);
//	verifyshoppingCartRepository, times(1)).findAllByUser(user);
//	verify(addToCartDto, atLeastOnce()).getProductId();
//	verify(addToCartDto, atLeastOnce()).getQuantity();
//	verify(shoppingProductService, times(1)).getProductById(3);
//
//
//
//	verify(shoppingCartRepository, times(1)).save(new Cart());
//
//   	}
//
//	@Test
//    	public void testAddToCartExistingProductItemIncart() {
//
//        System.out.println("adding to cart with existing product item in cart");
// 	User user = new User(111, "ella", "Ella@gmail.com");
//	addToCartDto.setQuantity(10);
//	addToCartDto.setProductId(3);
//        when(userService.getUser(111)).thenReturn(user);
//	when(shoppingCartRepository.findAllByUser(user)).thenReturn(getCart());
//	when(shoppingProductService.getProductById(3)).thenReturn(getProduct());
//
//        Cart result = shoppingCartService.addProductToCart(addToCartDto, 111);
//     
//        // verify expected results
//	verify(userService, times(1)).getUser(111);
//	verifyshoppingCartRepository, times(1)).findAllByUser(user);
//	verify(addToCartDto, atLeastOnce()).getProductId();
//	verify(addToCartDto, atLeastOnce()).getQuantity();
//	verify(shoppingProductService, times(0)).getProductById(3);
//
//	verify(shoppingCartRepository, times(1)).save(new Cart());
//
//   	}
//
//	@Test (expected = Exception.class)
//    	public void testAddToCartUserNotFoundExceptionThrown() {
//            System.out.println("adding Products to cart, but user not found");
// 
//            when(userService.getUser(111)).thenThrow(Exception.class);
//
//            try {
//                List<Product> result = shoppingCartRepository.addProductToCart(addToCartDto, 151);
//   	    } catch (Exception e) {
//                Assert.assertEquals("Failed to fetch data.", e.getMessage());
//    	    }
//   	}
//
//	@Test
//    	public void testUpdateCartItem() {
//
//        System.out.println("update cart item");
// 	User user = new User(111, "ella", "Ella@gmail.com");
//	addToCartDto.setQuantity(10);
//	addToCartDto.setProductId(3);
//        when(userService.getUser(111)).thenReturn(user);
//	when(shoppingCartRepository.findAllByUser(user)).thenReturn(getCart());
//
//        String result = shoppingCartService.updateCartItem(addToCartDto,111);
//     
//        // assert and verify expected results
//	Assert.assertNotNull(result);
//	Assert.assertEquals("updated", result);
//	verify(shoppingCartRepository, times(1)).findAllByUser(user);
//	verify(shoppingCartRepository, times(1)).save(new Cart());
//
//   	}
//
//	@Test
//    	public void testUpdateCartItemForZeroQuantity() {
//
//        System.out.println("update cart item for quantity as zero");
// 	User user = new User(111, "ella", "Ella@gmail.com");
//	addToCartDto.setQuantity(0);
//	addToCartDto.setProductId(3);
//        when(userService.getUser(111)).thenReturn(user);
//	when(shoppingCartRepository.findAllByUser(user)).thenReturn(getCart());
//
//        String result = shoppingCartService.updateCartItem(addToCartDto,111);
//     
//        //assert and verify expected results
//	Assert.assertNotNull(result);
//	Assert.assertEquals("deleted", result);
//	verify(shoppingCartRepository, times(1)).findAllByUser(user);
//	verify(shoppingCartRepository, times(1)).deleteByProductId(3);
//
//   	}
//
//	@Test(expected = Exception.class)
//    	public void testUpdateCartItemThrowException() {
//
//        System.out.println("update cart, item not found exception");
// 	User user = new User(111, "ella", "Ella@gmail.com");
//	addToCartDto.setQuantity(0);
//	addToCartDto.setProductId(30);
//        when(userService.getUser(111)).thenReturn(user);
//	when(shoppingCartRepository.findAllByUser(user)).thenReturn(null);
//
//        String result = shoppingCartService.updateCartItem(addToCartDto,111);
//     
//        // assert and verify expected results
//	verify(shoppingCartRepository, times(1)).findAllByUser(user);
//	verify(shoppingCartRepository, times(0)).save(new Cart());
//
//   	}
//
//	@Test(expected = Exception.class)
//    	public void testDeleteCartItemsThrowExc() {
//
//        System.out.println("Delete cart item");
//         shoppingCartService.deleteUserCartItems(111);
//
//   	}
//
//	@Test
//    	public void testDeleteCartItems() {
//        System.out.println("Delete cart item");
//
//        Boolean result = shoppingCartService.deleteCartItem(111, 3);
//     
//        // assert and verify expected results
//        verify(shoppingCartRepository, times(1)).deleteAllByUserId(111);
//
//   	}
//
//     @Test
//    public void testGetCartItem() {
//        System.out.println("testing get cart item of a user");
//        Apparal product4 = new Apparal();
//		product4.setId(4);
//		product4.setProductName("Mens casuals");
//		product4.setPrice(760);
//		product4.setCatagory("Apparal");
//		product4.setDesign("Skin fit");
//		product4.setType("Jeans");
//		product4.setBrand("LP");
//	    when(productRepository.findById(4)).thenReturn(Optional.of(product4)); 
//	    when(shoppingCartRepository.findAllByUser(new User(111, "Ram@gmail.com", "Ram")))
//			.thenReturn(getCart());
//
//        CartDto result = shoppingCartService.getCartItems(111);
//     
//        // Assert expected results
//        Assert.assertNotNull( result);
//        Assert.assertNotNull( result.getTotalCost());
//   	}
//
//    private List<Product> getMockedProducts(){
//      List<Product> productList = new ArrayList<>();
//
//      Book product1 = new Book();
//	product1.setId(1);
//	product1.setProductName("God of little things");
//	product1.setPrice(560);
//	product1.setCatagory("Book");
//	product1.setPublications("DC Books");
//	product1.setGenre("Fiction");
//	product1.setAuthor("Arundadi roy");
//	
//	productList.add(product1);
//
//
//	Book product2 = new Book();
//	product2.setId(2);
//	product2.setProductName("Meluha");
//	product2.setPrice(450);
//	product2.setCatagory("Book");
//	product2.setPublications("SKM publications");
//	product2.setGenre("Historical Fiction");
//	product2.setAuthor("Amit");
//
//	productList.add(product2);
//
// 	Book product3 = new Book();
//	product3.setId(3);
//	product3.setProductName("you are beautiful");
//	product3.setPrice(360);
//	product3.setCatagory("Book");
//	product3.setPublications("DC Books");
//	product3.setGenre("Romcom");
//	product3.setAuthor("preeti shinoy");
//
//	productList.add(product3);
//
// 	Apparal product4 = new Apparal();
//	product4.setId(4);
//	product4.setProductName("Mens casuals");
//	product4.setPrice(760);
//	product4.setCatagory("Apparal");
//	product4.setDesign("Skin fit");
//	product4.setType("Jeans");
//	product4.setBrand("LP");
//
//	productList.add(product4);
//
//	Apparal product5 = new Apparal();
//	product5.setId(5);
//	product5.setProductName("Womans casuals");
//	product5.setPrice(460);
//	product5.setCatagory("Apparal");
//	product5.setDesign("Pleated");
//	product5.setType("Skirt");
//	product5.setBrand("Lara");
//
//	productList.add(product5);
//	
//	Apparal product6 = new Apparal();
//	product6.setId(6);
//	product6.setProductName("Womans party wear");
//	product6.setPrice(860);
//	product6.setCatagory("Apparal");
//	product6.setDesign("Flared");
//	product6.setType("Gown");
//	product6.setBrand("vera moda");
//
//	productList.add(product6);
//
//	return productList;
//    }
//    
//    public List<Cart> getCart(){
//    	List<Cart> cartItem = new ArrayList<>();
//	    Cart cart = new Cart();
//	    cart.setId(1);
//	    cart.setQuantity(4);
//	    cart.setUser(new User(111, "ram@gmail.com", "Ram"));
//	 	Apparal product4 = new Apparal();
//		product4.setId(4);
//		product4.setProductName("Mens casuals");
//		product4.setPrice(760);
//		product4.setCatagory("Apparal");
//		product4.setDesign("Skin fit");
//		product4.setType("Jeans");
//		product4.setBrand("LP");
//	    cart.setProduct(product4);
//	    cartItem.add(cart);
//	    return cartItem;
//	}
//
//    public Product getProduct(){
//	Book product2 = new Book();
//	product2.setId(3);
//	product2.setProductName("Meluha");
//	product2.setPrice(450);
//	product2.setCatagory("Book");
//	product2.setPublications("SKM publications");
//	product2.setGenre("Historical Fiction");
//	product2.setAuthor("Amit");
//	return product2;
//    }
//    }