package com.rest.web.usermanagement.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.rest.web.usermanagement.dao.UserDao;
import com.rest.web.usermanagement.exception.BusinessException;
import com.rest.web.usermanagement.model.dto.LoginDTO;
import com.rest.web.usermanagement.model.dto.SimpleUserDTO;
import com.rest.web.usermanagement.model.dto.UserDTO;
import com.rest.web.usermanagement.model.entity.User;
import com.rest.web.usermanagement.utils.TokenUtils;

/**
 * Classe dos serviços reponsável pelas regras de negócio.
 * 
 * @author emersonmuraro
 *
 */
@Service("userManagementService")
@Transactional
public class UserManagementServiceImpl implements UserManagementService{
	
	@Value("${session.timeout:30}")
	private String timeout;
	
	@Autowired
	private UserDao userDao;

	@Override
	public UserDTO findByEmail(String email) {
		User user = (User)userDao.findByEmail(email);
		return user!=null? UserDTO.convertUserToUserDTO(user) : null;
	}
	
	@Override
	public UserDTO findById(String id) {
		User user = userDao.findById(id);
		return user!=null? UserDTO.convertUserToUserDTO(user) : null;
	}

	@Override
	public void saveUser(UserDTO userDTO) throws BusinessException{
		User user = userDao.findByEmail(userDTO.getSimpleUserDTO().getEmail());
		if (user != null && !user.getId().isEmpty()){
			throw new BusinessException("E-mail já existente");
		}
		Date creationDate = new Date();
		userDTO.setCreated(creationDate);
		userDTO.setLast_login(creationDate);
		userDTO.setToken(TokenUtils.generateJwt(userDTO.getSimpleUserDTO().getEmail(), creationDate, timeout));
		User tokeUser = userDTO.convertToUser();
		userDao.saveUser(tokeUser);
		userDTO.setId(tokeUser.getId());
	}

	@Override
	public void deleteUser(UserDTO userDTO) {
		userDao.deleteUser(userDTO.convertToUser());
	}

	@Override
	public List<SimpleUserDTO> listAllUsers() {
		List<User> users = userDao.listAllUsers();
		List<SimpleUserDTO> simpleUsersDTO = new ArrayList<SimpleUserDTO>();
		for (User user : users) {
			simpleUsersDTO.add(SimpleUserDTO.convertUserToSimpleUserDTO(user));
		}
		return simpleUsersDTO.isEmpty()? null : simpleUsersDTO;
	}
	
	@Override
	public UserDTO login(LoginDTO login)throws BusinessException{
		UserDTO userDTO = findByEmail(login.getEmail());
		if (userDTO == null || userDTO.getId().isEmpty()){
			throw new BusinessException("Usuário e/ou senha inválidos");
		}
		if(userDTO.getSimpleUserDTO() != null 
				&& userDTO.getSimpleUserDTO().getEmail().equals(login.getEmail())
				&& userDTO.getSimpleUserDTO().getPassword().equals(login.getPassword())){
			Date loginDate = new Date();
			String token = TokenUtils.generateJwt(login.getEmail(), loginDate, timeout);
			userDTO.setToken(token);
			userDTO.setLast_login(loginDate);
			userDao.updateUser(userDTO.convertToUser());
		 	return userDTO;
		}else{
			throw new BusinessException("Usuário e/ou senha inválidos");
		}
		
	}

}
