package com.tejapps.shopingcart.model;

import java.io.Serializable;

import com.tejapps.shoppingcartbackend.dto.Address;
import com.tejapps.shoppingcartbackend.dto.User;

public class RegisterModel implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private User user;
	private Address billing;
	public User getUser() {
		return user;
	}
	public Address getBilling() {
		return billing;
	}
	public void setUser(User user) {
		this.user = user;
	}
	public void setBilling(Address billing) {
		this.billing = billing;
	}
	
	
	
}
