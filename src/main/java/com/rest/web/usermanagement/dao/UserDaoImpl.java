package com.rest.web.usermanagement.dao;

import java.util.List;

import org.hibernate.Query;
import org.springframework.stereotype.Repository;

import com.rest.web.usermanagement.model.entity.User;

@Repository("userDao")
public class UserDaoImpl extends AbstractDao implements UserDao{


	@Override
	public void saveUser(User user) {
		persist(user);
	}

	@Override
	public void deleteUser(User user) {
		delete(user);
	}
	
	@Override
	public void updateUser(User user){
		merge(user);
	}

	@Override
	public List<User> listAllUsers() {
		Query query = getSession().createQuery ("from User");
		@SuppressWarnings("unchecked")
		List<User> users = query.list();
		return users;
	}

	@Override
	public User findByEmail(String email) {
		Query query = getSession().createQuery ("from User where email = :email");
		query.setParameter("email", email);
		return (User)query.uniqueResult();
	}

	@Override
	public User findById(String id) {
		Query query = getSession().createQuery ("from User where id = :id");
		query.setParameter("id", id);
		return (User)query.uniqueResult();
	}
}
