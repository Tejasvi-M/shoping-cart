package com.tejapps.shoppingcartbackend;

import static org.junit.Assert.assertEquals;

import org.junit.BeforeClass;
import org.junit.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import com.tejapps.shoppingcartbackend.dao.ProductDAO;
import com.tejapps.shoppingcartbackend.dto.Product;

public class ProductTestCase {

		private static AnnotationConfigApplicationContext context;
		private static ProductDAO productDAO;
		private  Product product;
		
		@BeforeClass
		public static void init() {
			context=new AnnotationConfigApplicationContext();
			context.scan("com.tejapps.shoppingcartbackend");
			context.refresh();
			productDAO = (ProductDAO)context.getBean("productDAO");
		}
		
		@Test
		public void testCrud() {
			product =new Product();
			product.setName("Nokia 3");
			product.setBrand("Nokia");
			product.setDescription("Nokia latest mobile ");
			product.setUnitPrice(5000);
			product.setActive(true);
			product.setCategoryId(3);
			product.setSupplierId(3);
			
			assertEquals("Something wrong in entering new product",true,productDAO.add(product));
		}
}
