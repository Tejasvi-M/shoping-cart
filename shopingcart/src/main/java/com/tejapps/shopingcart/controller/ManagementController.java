package com.tejapps.shopingcart.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.tejapps.shopingcart.util.FileUploadUtility;
import com.tejapps.shopingcart.validator.ProductValidator;
import com.tejapps.shoppingcartbackend.dao.CategoryDAO;
import com.tejapps.shoppingcartbackend.dao.ProductDAO;
import com.tejapps.shoppingcartbackend.dto.Category;
import com.tejapps.shoppingcartbackend.dto.Product;

@Controller
@RequestMapping("/manage")
public class ManagementController {
	
	@Autowired
	CategoryDAO categoryDAO;
	
	@Autowired
	ProductDAO productDAO;

	@RequestMapping(value="/products",method=RequestMethod.GET)
	public ModelAndView showManageProducts(@RequestParam(name="operation",required=false)String operation)
	{
		ModelAndView mv =new ModelAndView("page");
		mv.addObject("userClickManageProducts",true);
		mv.addObject("title","Manage Products");
		
		Product nproduct = new Product();
		nproduct.setSupplierId(1);
		nproduct.setActive(true);
		mv.addObject("product",nproduct);
		
		if(operation!=null) {
			if(operation.equals("product")) {
				mv.addObject("message","Product submitted successfully");			
			}
		
		else if(operation.equals("category")) {
			mv.addObject("message","Category submitted successfully");
		}
			
		}	
		return mv;
	}
	
	@ModelAttribute("categories")
	public List<Category> getCategories()
	{
		return categoryDAO.list();
	}
	
	//Product submission handling
	
	@RequestMapping(value="/products",method=RequestMethod.POST)
	public String handleProductSubmission(@Valid @ModelAttribute("product") Product mproduct, BindingResult results, Model model,HttpServletRequest request) {
		//case: if there are no product before, checking for the product validations
		if(mproduct.getId()==0) {
		new ProductValidator().validate(mproduct,results);
		}
		//case: if the product exists i.e editing the existing product 
		else {
			//checking if the image is not empty i.e another image file is uploaded
			if(!mproduct.getFile().getOriginalFilename().equals(""))
				new ProductValidator().validate(mproduct,results);
		}
		
		if(results.hasErrors()) {
			model.addAttribute("title","Manage Products");
			model.addAttribute("userClickManageProducts",true);
			return "page";
		}
		
		if(mproduct.getId()== 0) {
		productDAO.add(mproduct);
		}
		else {
			productDAO.update(mproduct);
		}
		if(!mproduct.getFile().getOriginalFilename().equals("")) {
			FileUploadUtility.uploadFile(request,mproduct.getFile(),mproduct.getCode());
		}
		
		return "redirect:/manage/products?operation=product";
	}
	
	@RequestMapping(value = "/product/{id}/activation",method=RequestMethod.GET)
	@ResponseBody
	public String handleProductActivation(@PathVariable int id) {
		
		Product product=productDAO.get(id);
		boolean isActive=product.isActive();
		product.setActive(!isActive);
		productDAO.update(product);
		return (isActive)? "The product is deactivated with id"+product.getId() :
			"The product is activated with id"+product.getId();
		
	}

	@RequestMapping(value="/{id}/product",method=RequestMethod.GET)
	public ModelAndView showEditProduct(@PathVariable int id) {
		ModelAndView mv=new ModelAndView("page");
		mv.addObject("userClickManageProducts",true);
		mv.addObject("title","Manage Products");
		
		Product nproduct=productDAO.get(id);
		mv.addObject("product",nproduct);
		return mv;
	}
	
	@RequestMapping(value="/category",method=RequestMethod.POST)
	 public String handleCategorySubmission(@ModelAttribute Category category) {
		categoryDAO.add(category);
		return "redirect:/manage/products?operation=category";
	}

	@ModelAttribute("category")
	public Category getCategory() {
		return new Category();
	}
	
}


