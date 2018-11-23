package com.tejapps.shoppingcartbackend.dao;

import java.util.List;

import com.tejapps.shoppingcartbackend.dto.Cart;
import com.tejapps.shoppingcartbackend.dto.CartLine;
import com.tejapps.shoppingcartbackend.dto.OrderDetail;

public interface CartLineDAO {

	public List<CartLine> list(int cartId);
	public CartLine get(int id);
	public boolean add(CartLine cartLine);
	public boolean update(CartLine cartLine);
	public boolean remove(CartLine cartLine);
	
	public CartLine getByCartAndProduct(int cartId,int productId);
	
	boolean updateCart(Cart cart);
	
	public List<CartLine> listAvailable(int cartId);
	
	boolean addOrderDetail(OrderDetail orderDetail);
	
}
