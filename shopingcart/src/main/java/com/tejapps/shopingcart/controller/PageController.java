package com.tejapps.shopingcart.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.tejapps.shopingcart.exception.ProductNotFoundException;
import com.tejapps.shoppingcartbackend.dao.CategoryDAO;
import com.tejapps.shoppingcartbackend.dao.ProductDAO;
import com.tejapps.shoppingcartbackend.dto.Category;
import com.tejapps.shoppingcartbackend.dto.Product;

@Controller
public class PageController {

	@Autowired
	private CategoryDAO categoryDAO;
	
	@Autowired 
	private ProductDAO productDAO;
	
	@RequestMapping(value = { "/", "/home", "/index" })
	public ModelAndView index() {
		ModelAndView mv = new ModelAndView("page");
		mv.addObject("title", "Home");
		mv.addObject("categories",categoryDAO.list());
		mv.addObject("clickHome",true);
		return mv;
	}

	@RequestMapping(value = { "/about" })
	public ModelAndView about() {
		ModelAndView mv = new ModelAndView("page");
		mv.addObject("title", "About");
		mv.addObject("clickAbout",true);
		return mv;
	}
	
	@RequestMapping(value = { "/contact" })
	public ModelAndView contact() {
		ModelAndView mv = new ModelAndView("page");
		mv.addObject("title", "Contact");
		mv.addObject("clickContact",true);
		return mv;
	}
	
	@RequestMapping(value = { "/show/all/products" })
	public ModelAndView showAllProducts() {
		ModelAndView mv = new ModelAndView("page");
		mv.addObject("title", "All Products");
		mv.addObject("categories",categoryDAO.list());
		mv.addObject("clickAllProducts",true);
		return mv;
	}
	
	@RequestMapping(value = { "/show/category/{id}/products" })
	public ModelAndView showCategoryProducts(@PathVariable("id") int id) {
		ModelAndView mv = new ModelAndView("page");
		//using categortDAO to get category 
		Category category =null;
		category= categoryDAO.get(id);
		mv.addObject("title", category.getName());
		//passing list of categories
		mv.addObject("categories",categoryDAO.list());
		//passing category object
		mv.addObject("category",category);
		
		mv.addObject("clickCategoryProducts",true);
		return mv;
	}

	@RequestMapping(value="/show/{id}/product")
	public ModelAndView showSingleProduct(@PathVariable int id) throws ProductNotFoundException
	{
		ModelAndView mv=new ModelAndView("page");
		Product product= productDAO.get(id);
		if(product==null) {
			throw new ProductNotFoundException();
		}
		product.setViews(product.getViews()+1);
		productDAO.update(product);
		mv.addObject("title",product.getName());
		mv.addObject("product",product);
		mv.addObject("userClickShowProduct",true);
		
		return mv;
	}
}
