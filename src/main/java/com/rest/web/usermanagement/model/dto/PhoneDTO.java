package com.rest.web.usermanagement.model.dto;

import java.util.ArrayList;
import java.util.List;

import com.rest.web.usermanagement.model.entity.Phone;

/**
 * DTO com os dados de telefone para contato
 * 
 * @author emersonmuraro
 *
 */
public class PhoneDTO{
	
	private String number;
	
	private String ddd;
	
	public PhoneDTO(String number, String ddd){
		this.number=number;
		this.ddd=ddd;
	}
	
	public PhoneDTO(){};
	
	public String getNumber() {
		return number;
	}
	public void setNumber(String number) {
		this.number = number;
	}
	public String getDdd() {
		return ddd;
	}
	public void setDdd(String ddd) {
		this.ddd = ddd;
	}
	
	public Phone convertToPhone(){
		Phone phone = new Phone();
		phone.setDdd(this.ddd);
		phone.setNumber(this.number);
		return phone;
	}
	
	public static PhoneDTO convertPhoneToPhoneDTO(Phone phone){
		return new PhoneDTO(phone.getNumber(), phone.getDdd());
	}
	
	public static List<Phone> convertToPhones(List<PhoneDTO> phoneDTOList){
		ArrayList<Phone> entityPhones = new ArrayList<Phone>();
		if (phoneDTOList == null || phoneDTOList.isEmpty()){
			return entityPhones;
		}
		for (PhoneDTO phoneDTO : phoneDTOList) {
			entityPhones.add(phoneDTO.convertToPhone());
		}
		return entityPhones;
	}
	
	public static List<PhoneDTO> convertPhonesToPhonesDTO(List<Phone> phonesList){
		ArrayList<PhoneDTO> phonesDTO = new ArrayList<PhoneDTO>();
		if (phonesList == null || phonesList.isEmpty()){
			return phonesDTO;
		}
		for (Phone phone : phonesList) {
			phonesDTO.add(PhoneDTO.convertPhoneToPhoneDTO(phone));
		}
		return phonesDTO;
	}

}
