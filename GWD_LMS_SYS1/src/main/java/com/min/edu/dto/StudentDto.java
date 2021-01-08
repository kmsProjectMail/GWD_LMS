package com.min.edu.dto;

import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class StudentDto {
	private String id;
	private String pw;
	private String name;
	private int addr_code;
	private String addr2;
	private String email;
	private String phone;
	private boolean enabled;
	private String mgr;
	
	//SignUp Constructor
	public StudentDto(String id, String pw, String name, int addr_code, String addr2, String email, String phone) {
		super();
		this.id = id;
		this.pw = pw;
		this.name = name;
		this.addr_code = addr_code;
		this.addr2 = addr2;
		this.email = email;
		this.phone = phone;
	}
	
	private void setEnabled(char enabled) {
		// TODO Auto-generated method stub
		this.enabled = (enabled=='T')? true : false; 
	}
	
}
