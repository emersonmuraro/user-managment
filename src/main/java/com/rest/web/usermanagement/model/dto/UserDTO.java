package com.rest.web.usermanagement.model.dto;

import java.io.Serializable;
import java.util.Date;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.rest.web.usermanagement.model.entity.User;

/**
 * DTO completo do usuário, com os dados de autenticação e 
 * os dados cadastrais do usuário.
 * Este DTO está configurado para criar um JSON com apenas os dados de autenticação
 * 
 * @author emersonmuraro
 *
 */
@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public class UserDTO implements Serializable {

	private static final long serialVersionUID = 4005039767661256525L;

	@XmlElement(name = "id")
	private String id;
	
	@XmlElement(name = "created", type = Date.class)
	private Date created;
	
	@XmlElement(name = "modified", type = Date.class)
	private Date modified;
	
	@XmlElement(name = "last_login", type = Date.class)
	private Date last_login;
	
	@XmlElement(name = "token")
	private String token;
	
	@JsonIgnore
	private SimpleUserDTO simpleUserDTO;

	/**
	 * @return the id
	 */
	public String getId() {
		return id;
	}

	/**
	 * @param id the id to set
	 */
	public void setId(String id) {
		this.id = id;
	}

	/**
	 * @return the created
	 */
	public Date getCreated() {
		return created;
	}

	/**
	 * @param created the created to set
	 */
	public void setCreated(Date created) {
		this.created = created;
	}

	/**
	 * @return the modified
	 */
	public Date getModified() {
		return modified;
	}

	/**
	 * @param modified the modified to set
	 */
	public void setModified(Date modified) {
		this.modified = modified;
	}

	/**
	 * @return the last_login
	 */
	public Date getLast_login() {
		return last_login;
	}

	/**
	 * @param last_login the last_login to set
	 */
	public void setLast_login(Date last_login) {
		this.last_login = last_login;
	}

	/**
	 * @return the token
	 */
	public String getToken() {
		return token;
	}

	/**
	 * @param token the token to set
	 */
	public void setToken(String token) {
		this.token = token;
	}
	
	/**
	 * @return the simpleUserDTO
	 */
	public SimpleUserDTO getSimpleUserDTO() {
		return simpleUserDTO;
	}

	/**
	 * @param simpleUserDTO the simpleUserDTO to set
	 */
	public void setSimpleUserDTO(SimpleUserDTO simpleUserDTO) {
		this.simpleUserDTO = simpleUserDTO;
	}
	
	public User convertToUser(){
		User user = this.simpleUserDTO.convertToUser();
		user.setCreated(this.created);
		user.setModified(this.modified);
		user.setLast_login(this.last_login);
		user.setToken(this.token);
		if (this.id !=null && !this.id.isEmpty()){
			user.setId(this.id);
		}
		return user;
	}
	
	public static UserDTO convertUserToUserDTO(User user){
		if (user == null){
			return null;
		}
		UserDTO userDTO = new UserDTO();
		userDTO.setSimpleUserDTO(SimpleUserDTO.convertUserToSimpleUserDTO(user));
		userDTO.setCreated(user.getCreated());
		userDTO.setModified(user.getModified());
		userDTO.setLast_login(user.getLast_login());
		userDTO.setToken(user.getToken());
		userDTO.setId(user.getId());
		return userDTO;
	}
}
