package com.tejapps.shopingcart.exception;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.NoHandlerFoundException;

@ControllerAdvice
public class GlobalDefaultExceptionHandler {

	@ExceptionHandler(NoHandlerFoundException.class)
	public ModelAndView handlerNoHandlerFoundException()
	{
		ModelAndView mv=new ModelAndView("error");
		mv.addObject("errorTitle","The page is not available");
		mv.addObject("errorDescription","The page you are looking for is not available");
		mv.addObject("title","404 Error Page");
		
		return mv;
	}
	
	@ExceptionHandler(ProductNotFoundException.class)
	public ModelAndView ProductNotFoundException()
	{
		ModelAndView mv=new ModelAndView("error");
		mv.addObject("errorTitle","The product is not available");
		mv.addObject("errorDescription","The product you are looking for is not available");
		mv.addObject("title","No Product Found");
		
		return mv;
	}
	
	@ExceptionHandler(Exception.class)
	public ModelAndView handlerException(Exception ex)
	{
		ModelAndView mv=new ModelAndView("error");
		mv.addObject("errorTitle","Contact your admin");
		mv.addObject("errorDescription",ex.toString());
		mv.addObject("title","Error, Something went wrong!");
		
		return mv;
	}
	
}
