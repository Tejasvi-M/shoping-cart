package com.tejapps.shopingcart.validator;

import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import com.tejapps.shoppingcartbackend.dto.Product;

public class ProductValidator implements Validator {

	@Override
	public boolean supports(Class<?> clazz) {
		// TODO Auto-generated method stub
		return Product.class.equals(clazz);
	}

	@Override
	public void validate(Object target, Errors errors) {
		// TODO Auto-generated method stub
		Product product=(Product)target;
		//validation to check file has been selected
		if(product.getFile()==null || product.getFile().getOriginalFilename().equals("")) {
			errors.rejectValue("file",null,"Please select an image");
			return ;
		}
		
		//validation to check the format is image 
		if(! product.getFile().getContentType().equals("image/jpeg") || ! product.getFile().getContentType().equals("image/jpg")
				|| ! product.getFile().getContentType().equals("image/png")
				) {
			errors.rejectValue("file",null, "Please select an image format only");
			return ;
		}
		
	}

}
