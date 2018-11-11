package com.tejapps.shoppingcartbackend.dao;

import java.util.List;

import com.tejapps.shoppingcartbackend.dto.Category;

public interface CategoryDAO {
	
	boolean add(Category category);
	
	boolean update(Category category);
	
	boolean delete(Category category);
	
	
	List<Category> list();
	
	Category get(int id);

}
