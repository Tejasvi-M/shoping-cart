package com.tejapps.shoppingcartbackend.dao;

import java.util.List;

import com.tejapps.shoppingcartbackend.dto.Address;
import com.tejapps.shoppingcartbackend.dto.User;

public interface UserDAO {
		
	User getByEmail(String email);
	User get(int id);
	boolean add(User user);
	
	Address getAddress(int addressId);
	boolean addAddress(Address address);
	boolean updateAdress(Address address);
	Address getBillingAddress(int userId);
	List<Address> listShippingAdresses(int userId);

}
