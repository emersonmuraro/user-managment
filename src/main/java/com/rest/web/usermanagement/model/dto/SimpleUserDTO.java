package com.rest.web.usermanagement.model.dto;

import java.io.Serializable;
import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import com.rest.web.usermanagement.model.entity.Phone;
import com.rest.web.usermanagement.model.entity.User;

/**
 * DTO como os dados cadastrais do usuário.
 * 
 * @author emersonmuraro
 *
 */
@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public class SimpleUserDTO implements Serializable{
	
	private static final long serialVersionUID = 3864727777606894864L;
	
	@XmlElement(name = "name", required=true)
	private String name;
	
	@XmlElement(name = "email", required=true)
	private String email;
	
	@XmlElement(name = "password", required=true)
	private String password;
	
	@XmlElement(name = "phones", required=false)
	private List<PhoneDTO> phones;
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public List<PhoneDTO> getPhones() {
		return phones;
	}
	public void setPhones(List<PhoneDTO> phones) {
		this.phones = phones;
	}
	
	public User convertToUser(){
		User user = new User();
		user.setName(this.name);
		user.setEmail(this.email);
		user.setPassword(this.password);
		List<Phone> phones = PhoneDTO.convertToPhones(this.phones); 
		if (phones != null && !phones.isEmpty()){
			user.setPhones(phones);
		}
		return user;
	}
	
	public static SimpleUserDTO convertUserToSimpleUserDTO(User user){
		SimpleUserDTO simpleUserDTO = new SimpleUserDTO();
		if (user == null){
			return simpleUserDTO;
		}
		simpleUserDTO.setName(user.getName());
		simpleUserDTO.setEmail(user.getEmail());
		simpleUserDTO.setPassword(user.getPassword());
		simpleUserDTO.setPhones(PhoneDTO.convertPhonesToPhonesDTO(user.getPhones())); 
		return simpleUserDTO;
	}
	
	

}
