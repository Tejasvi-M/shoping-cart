package com.tejapps.shopingcart.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.tejapps.shopingcart.service.CartService;

@Controller
@RequestMapping("/cart")
public class CartController {
	
	@Autowired 
	private CartService cartService;
	
	@RequestMapping("/show")
	public ModelAndView showCart(@RequestParam(name="result",required=false)String result) {
		ModelAndView mv =new ModelAndView("page");
		
		if(result!=null) {
		switch(result) {
		case "updated": mv.addObject("message","Cart updated successfully");
		break;
		case "error" : mv.addObject("message", "Cart is'nt updated!");
		break;
		case "deleted":mv.addObject("message","Cart deleted successfully");
		break;
		case "added": mv.addObject("message","Cartline added");
		break;
		case "maximum": mv.addObject("message","Cartline limit exceeded");
		break;
		case "unavailable": mv.addObject("message","Product is not available for now");
		break;
		}
		}
		mv.addObject("title","User Cart");
		mv.addObject("userClickShowCart",true);
		mv.addObject("cartLines",cartService.getCartLines());
		
		
		return mv;
	}
	
	@RequestMapping("/{cartLineId}/update")
	public String udateCart(@PathVariable int cartLineId, @RequestParam int count) {
			String response= cartService.manageCartLine(cartLineId,count);
			return "redirect:/cart/show?"+response;
		}
	
	@RequestMapping("/{cartLineId}/delete")
	public String updateCart(@PathVariable int cartLineId) {
		String response= cartService.deleteCartLine(cartLineId);
		return "redirect:/cart/show?"+response;
	}
	@RequestMapping("/add/{productId}/product")
	public String addCart(@PathVariable int productId) {
		String response= cartService.addCartLine(productId);
		return "redirect:/cart/show?"+response;
	}
		
}
	

