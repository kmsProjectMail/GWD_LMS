package com.min.edu.dto;

import com.min.edu.commons.utils.AddressCode_Mapper;

import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class CenterDto {
	private String	id;
	private String	password;
	private String	addr1;
	private String 	addr2;
	private String	cen_name;
	private String 	cen_phone;
	private boolean 	enabled;
	private String customer;
	
	public CenterDto() {
		// TODO Auto-generated constructor stub
	}
	

	
	//SignUp Constructor
	public CenterDto(String id, String pw, String addr1, String addr2, String cen_name, String cen_phone) {
		super();
		this.id = id;
		this.password = pw;
		this.addr1 = addr1;
		this.addr2 = addr2;
		this.cen_name = cen_name;
		this.cen_phone = cen_phone;
	}
	
	
	
	private void setEnabled(char enabled) {
		// TODO Auto-generated method stub
		this.enabled = (enabled=='T')? true : false; 
	}
	
}
