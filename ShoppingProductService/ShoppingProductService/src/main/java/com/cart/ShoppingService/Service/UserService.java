package com.cart.ShoppingService.Service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cart.ShoppingService.Model.User;
import com.cart.ShoppingService.Repository.UserRepository;

@Service
public class UserService {
	
	@Autowired
	public UserRepository userRepository;

	public UserService(UserRepository userRepository) {
		this.userRepository = userRepository;
	}
	
	public User getUser(Integer userId) {
		// TODO Auto-generated method stub
		Optional<User> user = userRepository.findById(userId);
		return user.isPresent() ? user.get() : null;
	}
	
	
}