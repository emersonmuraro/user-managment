package com.rest.web.usermanagement.service;

import java.util.List;

import com.rest.web.usermanagement.exception.BusinessException;
import com.rest.web.usermanagement.model.dto.LoginDTO;
import com.rest.web.usermanagement.model.dto.SimpleUserDTO;
import com.rest.web.usermanagement.model.dto.UserDTO;

public interface UserManagementService {
	UserDTO findByEmail(String email);
	void saveUser(UserDTO userDTO)throws BusinessException;
	void deleteUser(UserDTO userDTO);
	List<SimpleUserDTO> listAllUsers();
	UserDTO findById(String id);
	UserDTO login(LoginDTO login)throws BusinessException;

}
