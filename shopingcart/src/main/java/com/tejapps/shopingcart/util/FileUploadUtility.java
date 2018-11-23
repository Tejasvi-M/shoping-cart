package com.tejapps.shopingcart.util;

import java.io.File;
import java.io.IOException;

import javax.servlet.http.HttpServletRequest;

import org.springframework.web.multipart.MultipartFile;

public class FileUploadUtility {
	
	private static final String ABS_PATH="/home/tejasvi/git/shoping-cart/shopingcart/src/main/webapp/assets/images/";
	private static String REAL_PATH="";
	public static void uploadFile(HttpServletRequest request, MultipartFile file, String code) {
		// TODO Auto-generated method stub
		REAL_PATH=request.getSession().getServletContext().getRealPath("/assets/images/");
		//to create directory if not exist to upload image file
		if(!new File(ABS_PATH).exists()) {
			new File(ABS_PATH).mkdirs();
		}
		
		if(!new File(REAL_PATH).exists()) {
			new File(REAL_PATH).mkdirs();
		}
		
		try {
			file.transferTo(new File(ABS_PATH+code+".jpg"));
			file.transferTo(new File(ABS_PATH+code+".jpg"));
		}catch(IOException e) {
			
		}
	}
	

}
