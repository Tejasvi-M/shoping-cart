package com.tejapps.shoppingcartbackend;

import static org.junit.Assert.assertEquals;

import org.junit.BeforeClass;
import org.junit.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import com.tejapps.shoppingcartbackend.dao.CategoryDAO;
import com.tejapps.shoppingcartbackend.dto.Category;

public class CategoryTestCase {
	private static AnnotationConfigApplicationContext context;
	private static CategoryDAO categoryDAO;
	private Category category;

	@BeforeClass
	public static void init() {
		context = new AnnotationConfigApplicationContext();
		context.scan("com.tejapps.shoppingcartbackend");
		context.refresh();
		categoryDAO = (CategoryDAO) context.getBean("categoryDAO");
	}

	/*
	 * @Test public void testAddCategory() { category = new Category();
	 * category.setName("Mobile"); category.setDescription("Mobile description");
	 * category.setImageURL("CAT_3.png");
	 * assertEquals("Something wrong in adding category",true,categoryDAO.add(category)); }
	 */
	/*
	 * @Test public void testGetCategory() { category=categoryDAO.get(1);
	 * assertEquals("Something wrong in fetching category","Television",category.getName())
	 * ; }
	 */

	/*
	 * @Test public void testUpdateCategory(){ category=categoryDAO.get(1);
	 * category.setName("TV");
	 * assertEquals("Something wrong in updating category",true,categoryDAO.update(category
	 * ));
	 * 
	 * }
	 */

	/*
	 * @Test public void testdeleteCategory(){ category=categoryDAO.get(1);
	 * 
	 * assertEquals("Something wrong in deleting category",true,categoryDAO.delete(category
	 * ));
	 * 
	 * }
	 */

	/*
	 * @Test public void testListCategory(){
	 * 
	 * assertEquals("Something wrong in fetching category",2,categoryDAO.list().size());
	 * 
	 * }
	 */

	@Test
	public void testCrudCategory() {
		category = new Category();
		// test case, adding category
		category.setName("Mobile");
		category.setDescription("Mobile description");
		category.setImageURL("CAT_1.png");
		assertEquals("Something wrong in adding category", true, categoryDAO.add(category));
		
		category = new Category();
		category.setName("Television");
		category.setDescription("Television description");
		category.setImageURL("CAT_2.png");
		assertEquals("Something wrong in adding category", true, categoryDAO.add(category));

		// test case, fetching and updating
		category = categoryDAO.get(2);
		category.setName("TV");
		assertEquals("Something wrong in updating category", true, categoryDAO.update(category));

		// test case, deleting
		assertEquals("Something wrong in deleting category", true, categoryDAO.delete(category));
		
		//test case, fetching list
		assertEquals("Something wrong in fetching category",1,categoryDAO.list().size());
	}
}
