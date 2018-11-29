package com.tejapps.shopingcart.service;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tejapps.shopingcart.model.UserModel;
import com.tejapps.shoppingcartbackend.dao.CartLineDAO;
import com.tejapps.shoppingcartbackend.dao.ProductDAO;
import com.tejapps.shoppingcartbackend.dto.Cart;
import com.tejapps.shoppingcartbackend.dto.CartLine;
import com.tejapps.shoppingcartbackend.dto.Product;

@Service("cartService")
public class CartService {
	
	@Autowired
    private	ProductDAO productDAO;
	@Autowired
	private CartLineDAO cartLineDAO;
	@Autowired
	private HttpSession session;
	
	private Cart getCart() {
		return ((UserModel)session.getAttribute("userModel")).getCart();
	}
	public List<CartLine>getCartLines(){
		return cartLineDAO.list(this.getCart().getId());
	}
	public String manageCartLine(int cartLineId, int count) {
		//fectch cartline
		CartLine cartLine=cartLineDAO.get(cartLineId);
		
		if(cartLine==null) {
			return "result=error";
		}
		else {
			
			Product product =cartLine.getProduct();
			double oldTotal=cartLine.getTotal();
			
			
			
			if(product.getQuantity()<count) {
				return "result=unavailable";
			}
			
			cartLine.setProductCount(count);
			cartLine.setBuyingPrice(product.getUnitPrice());
			
			cartLine.setTotal(product.getUnitPrice()*count);
			cartLineDAO.update(cartLine);
			
			Cart cart=this.getCart();
			cart.setGrandTotal(cart.getGrandTotal()-oldTotal+cartLine.getTotal());
			cartLineDAO.updateCart(cart);
			return "result=updated";
		}
	}
	public String deleteCartLine(int cartLineId) {
		CartLine cartLine	= cartLineDAO.get(cartLineId);
		if(cartLine== null) {
			return "result=error";
		}
		else {
			Cart cart=this.getCart();
			cart.setGrandTotal(cart.getGrandTotal()-cartLine.getTotal());
			cart.setCartLines(cart.getCartLines());
			cartLineDAO.updateCart(cart);
			
			cartLineDAO.delete(cartLine);
			return "result=deleted";
		}
	}
	public String addCartLine(int productId) {
		String response=null;
		Cart cart=this.getCart();
		
		CartLine cartLine=cartLineDAO.getByCartAndProduct(cart.getId(), productId);
		
		if(cartLine==null) {
			cartLine  = new CartLine();
			Product product = productDAO.get(productId);
			cartLine.setCartId(cart.getId());
			cartLine.setProduct(product);
			cartLine.setBuyingPrice(product.getUnitPrice());
			cartLine.setProductCount(1);
			cartLine.setTotal(product.getUnitPrice());
			cartLine.setAvailable(true);
			
			cartLineDAO.add(cartLine);
			
			cart.setCartLines(cart.getCartLines());
			cart.setGrandTotal(cart.getGrandTotal()+cartLine.getTotal());
			cartLineDAO.updateCart(cart);
			response="result=added";
		}
		
		else {
			if(cartLine.getProductCount()<3) {
				response=this.manageCartLine(cartLine.getId(),cartLine.getProductCount()+1);
			}
			else {
				response="result=maximum";
			}
		}
		
		
		
		return response;
	}
}
