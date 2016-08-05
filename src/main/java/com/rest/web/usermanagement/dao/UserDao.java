package com.rest.web.usermanagement.dao;

import java.util.List;

import com.rest.web.usermanagement.model.entity.User;

public interface UserDao {
	/**
	 * Recupera o usuário pelo e-mail.
	 * @param email
	 * @return
	 */
	User findByEmail(String email);
	void saveUser(User user);
	void deleteUser(User user);
	
	/**
	 * Lista todos os usuários cadastrado.
	 * @return
	 */
	List<User> listAllUsers();
	
	/**
	 * Recupera o usuário pelo id.
	 * @param id
	 * @return
	 */
	User findById(String id);
	void updateUser(User user);
}
