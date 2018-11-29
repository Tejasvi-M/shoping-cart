package com.tejapps.shoppingcartbackend.daoimpl;

import java.util.List;


import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.tejapps.shoppingcartbackend.dao.CartLineDAO;
import com.tejapps.shoppingcartbackend.dto.Cart;
import com.tejapps.shoppingcartbackend.dto.CartLine;
import com.tejapps.shoppingcartbackend.dto.OrderDetail;

@Repository("cartLineDAO")
@Transactional
public class CartLineDAOImpl implements CartLineDAO {

	@Autowired
	SessionFactory sessionFactory;
	
	@Override
	public List<CartLine> list(int cartId) {
		
		String selectQuery = "from CartLine where cartId=:cartId";
		return sessionFactory.getCurrentSession()
				.createQuery(selectQuery,CartLine.class)
				.setParameter("cartId",cartId)
				.getResultList();
		
	}

	@Override
	public CartLine get(int id) {
		// TODO Auto-generated method stub
		return sessionFactory.getCurrentSession().get(CartLine.class,Integer.valueOf(id));
	}

	@Override
	public boolean add(CartLine cartLine) {
		try {
			sessionFactory.getCurrentSession().persist(cartLine);
			return true;
		}catch(Exception e) {return false;}
		
	}

	@Override
	public boolean update(CartLine cartLine) {
		try {
			sessionFactory.getCurrentSession().update(cartLine);
			return true;
		}catch(Exception e) {
			return false;
		}
	}

	@Override
	public boolean remove(CartLine cartLine) {
		try {
			sessionFactory.getCurrentSession().delete(cartLine);
			return true;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}
	}

	@Override
	public CartLine getByCartAndProduct(int cartId, int productId) {
		String query="from CartLine where cartId=:cartId and product.id=:productId";
		try {
			return sessionFactory.getCurrentSession().createQuery(query,CartLine.class)
					.setParameter("cartId",cartId)
					.setParameter("productId",productId).getSingleResult();
		}catch(Exception e) {return null;}
	}
	
	@Override
	public boolean delete(CartLine cartLine) {
		
		try {
				sessionFactory.getCurrentSession().delete(cartLine);
				return true;
		}catch(Exception e) {return false;}
	}
	
	@Override
	public boolean updateCart(Cart cart) {
			try {
				sessionFactory.getCurrentSession().update(cart);
				return true;
			}
			catch(Exception e) {
				return false;
			}
	}

	@Override
	public List<CartLine> listAvailable(int cartId) {
			
		String query="from CartLine where cartId=:cartId and available=:available";
		return sessionFactory.getCurrentSession().createQuery(query,CartLine.class)
				.setParameter("cartId", cartId)
				.setParameter("available",true)
				.getResultList();
	}

	@Override
	public boolean addOrderDetail(OrderDetail orderDetail) {
		try {
			sessionFactory.getCurrentSession().persist(orderDetail);
			return true;
		}
		catch(Exception e){return false;}
	}

}
