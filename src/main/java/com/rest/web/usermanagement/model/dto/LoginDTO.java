package com.rest.web.usermanagement.model.dto;

/**
 * DTO com e-mail e senha do usuário para login
 * 
 * @author emersonmuraro
 *
 */
public class LoginDTO {
	
	private String email;
	private String password;
	
	/**
	 * @return the email
	 */
	public String getEmail() {
		return email;
	}
	/**
	 * @param email the email to set
	 */
	public void setEmail(String email) {
		this.email = email;
	}
	/**
	 * @return the password
	 */
	public String getPassword() {
		return password;
	}
	/**
	 * @param password the password to set
	 */
	public void setPassword(String password) {
		this.password = password;
	}

}
