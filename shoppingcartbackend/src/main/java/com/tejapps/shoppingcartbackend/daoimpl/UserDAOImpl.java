package com.tejapps.shoppingcartbackend.daoimpl;

import java.util.List;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.tejapps.shoppingcartbackend.dao.UserDAO;
import com.tejapps.shoppingcartbackend.dto.Address;
import com.tejapps.shoppingcartbackend.dto.User;

@Repository("userDAO")
@Transactional
public class UserDAOImpl implements UserDAO {

	@Autowired
	private SessionFactory sessionFactory;
	
	@Override
	public User getByEmail(String email) {
		String selectQuery= "from User where email=:email";
		try {
				return sessionFactory.getCurrentSession().createQuery(selectQuery,User.class)
						.setParameter("email",email).getSingleResult();
			
		}catch(Exception e) {return null;}
	}

	@Override
	public User get(int id) {
		try {
			return sessionFactory.getCurrentSession().get(User.class,id);
		}catch(Exception e) {return null;}
	}

	@Override
	public boolean add(User user) {
		try {
			sessionFactory.getCurrentSession().persist(user);
			return true;
		}
		catch(Exception ex) {return false;}
	}

	@Override
	public Address getAddress(int addressId) {
			
		try {
			return sessionFactory.getCurrentSession().get(Address.class,addressId);
		}catch(Exception e) {
			return null;
		}
	}

	@Override
	public boolean addAddress(Address address) {
		try {
			sessionFactory.getCurrentSession().persist(address);
			return true;
		}
		catch(Exception e) {return false;}
	}

	@Override
	public boolean updateAdress(Address address) {
		try {
			sessionFactory.getCurrentSession().update(address);
			return true;
		}
		catch(Exception e) {return false;}
	}

	@Override
	public Address getBillingAddress(int userId) {
		String selectQuery="from Address where userId=:userId and billing=:isBilling";
		try {
			return sessionFactory.getCurrentSession().createQuery(selectQuery,Address.class)
					.setParameter("userId",userId)
					.setParameter("isBilling",true).getSingleResult();
		}catch(Exception e) {return null;}
	}

	@Override
	public List<Address> listShippingAdresses(int userId) {
		String selectQuery="from Address where userId=:userId and shipping=:isShipping order by id desc";
		return sessionFactory.getCurrentSession()
				.createQuery(selectQuery,Address.class)
				.setParameter("userId",userId)
				.setParameter("isShipping",true)
				.getResultList();
	}

}
