package com.tejapps.shopingcart.handler;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.binding.message.MessageBuilder;
import org.springframework.binding.message.MessageContext;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

import com.tejapps.shopingcart.model.RegisterModel;
import com.tejapps.shoppingcartbackend.dao.UserDAO;
import com.tejapps.shoppingcartbackend.dto.Address;
import com.tejapps.shoppingcartbackend.dto.Cart;
import com.tejapps.shoppingcartbackend.dto.User;

@Component
public class RegisterHandler {

	@Autowired
	private BCryptPasswordEncoder passwordEncoder;
	
	@Autowired
	 private UserDAO userDAO;
	
	public RegisterModel init() {
		return new RegisterModel();
	}
	
	public void addUser(RegisterModel registerModel,User user) {
		registerModel.setUser(user);
	}
	
	public void addBilling(RegisterModel registerModel,Address billing) {
		registerModel.setBilling(billing);
	}
	
	
	public String validateUser(User user,MessageContext error) {
		String transitionValue= "success";
		
		if(!(user.getPassword().equals(user.getConfirmPassword()))) {
			error.addMessage(new MessageBuilder().error().source("confirmPassword")
					.defaultText("Re-type correct password").build());
			
			transitionValue="failure";
		}
		
		if(userDAO.getByEmail(user.getEmail())!=null) {
			
			error.addMessage(new MessageBuilder().error().source("confirmPassword")
					.defaultText("Email already exists!").build());
			transitionValue="failure";
		}
		
		return transitionValue;
	}
	
	public String saveAll(RegisterModel model) {
		String transitionValue="success";
		
		//fetch user
		User user = model.getUser();
		if(user.getRole().equals("USER")) {
			Cart cart = new Cart();
			cart.setUser(user);
			user.setCart(cart);
		}
	
		//encrypting password
		user.setPassword(passwordEncoder.encode(user.getPassword()));
		//saving user;
		userDAO.add(user);
		//get address
		Address billing = model.getBilling();
		billing.setUserId(user.getId());
		billing.setBilling(true);
		
		//saving address
		userDAO.addAddress(billing);
		
		return transitionValue;
	}
	
}
