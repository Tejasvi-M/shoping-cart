package com.tejapps.shoppingcartbackend.daoimpl;

import java.util.List;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.tejapps.shoppingcartbackend.dao.ProductDAO;
import com.tejapps.shoppingcartbackend.dto.Product;

@Repository("productDAO")
@Transactional
public class ProductDAOImpl implements ProductDAO {

	@Autowired
	private SessionFactory sessionFactory;

	@Override
	public Product get(int productId) {
		try {
			return sessionFactory.getCurrentSession().get(Product.class,Integer.valueOf(productId));
			
		}catch(Exception e) {e.printStackTrace();}
		return null;
	}

	@Override
	public List<Product> list() {
		return sessionFactory.getCurrentSession().createQuery("From Product",Product.class).getResultList();
				
	}

	@Override
	public boolean add(Product product) {
		try {
			sessionFactory.getCurrentSession().persist(product);
			return true;
		}catch(Exception e) {e.printStackTrace();}
		
		return false;
	}

	@Override
	public boolean update(Product product) {
		try {
			sessionFactory.getCurrentSession().update(product);
			return true;
		}catch(Exception e) {e.printStackTrace();}
		return false;
	}

	@Override
	public boolean delete(Product product) {
		try {
			product.setActive(false);
			return this.update(product);
		}catch(Exception e) {e.printStackTrace();}
		return false;
	}

	@Override
	public List<Product> getProductsByParam(String param, int count) {
		String selectProductsByParam= "FROM Product WHERE active = :active ORDER BY" + param + "DESC";
		return sessionFactory.getCurrentSession().createQuery(selectProductsByParam,Product.class).setParameter("active",true).setFirstResult(0).setMaxResults(count).getResultList();
	}

	@Override
	public List<Product> listActiveProducts() {
		String selectActiveProducts = "FROM Product WHERE active  = :active";
	    return sessionFactory.getCurrentSession().createQuery(selectActiveProducts,Product.class).setParameter("active", true).getResultList();
	
	}

	@Override
	public List<Product> listActiveProductsByCategory(int categoryId) {
		String selectActiveProductsByCategory = "FROM Product WHERE active= :active AND categoryId= :categoryId";
		return sessionFactory.getCurrentSession().createQuery(selectActiveProductsByCategory,Product.class).setParameter("active", true).setParameter("categoryId",categoryId).getResultList();
	}

	@Override
	public List<Product> getLatestActiveProducts(int count) {
		String getLatestActiveProducts= "FROM Product WHERE active=true ORDER BY id";
		return sessionFactory.getCurrentSession().createQuery(getLatestActiveProducts,Product.class).setFirstResult(0).setMaxResults(count).getResultList();
	}
	
}
