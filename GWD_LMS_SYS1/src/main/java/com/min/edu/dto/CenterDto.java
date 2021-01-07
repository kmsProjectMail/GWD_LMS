package com.min.edu.dto;

import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class CenterDto {
	private String	id;
	private String	pw;
	private int	addr_code;
	private String 	addr2;
	private String	cen_name;
	private String 	cen_phone;
	private boolean 	enabled;
	private String customer;
	
	
	//SignUp Constructor
	public CenterDto(String id, String pw, int addr_code, String addr2, String cen_name, String cen_phone) {
		super();
		this.id = id;
		this.pw = pw;
		this.addr_code = addr_code;
		this.addr2 = addr2;
		this.cen_name = cen_name;
		this.cen_phone = cen_phone;
	}
	private void setEnabled(char enabled) {
		// TODO Auto-generated method stub
		this.enabled = (enabled=='T')? true : false; 
	}
	
}
