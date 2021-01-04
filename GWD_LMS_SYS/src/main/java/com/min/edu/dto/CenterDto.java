package com.min.edu.dto;

import com.min.edu.commons.utils.AddressCode_Mapper;

import lombok.Data;
import lombok.ToString;

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
	
	
	
	public String getId() {
		return id;
	}



	public void setId(String id) {
		this.id = id;
	}



	public String getPassword() {
		return password;
	}



	public void setPassword(String password) {
		this.password = password;
	}



	public String getAddr1() {
		return addr1;
	}



	public void setAddr1(String addr1) {
		this.addr1 = addr1;
	}



	public String getAddr2() {
		return addr2;
	}



	public void setAddr2(String addr2) {
		this.addr2 = addr2;
	}



	public String getCen_name() {
		return cen_name;
	}



	public void setCen_name(String cen_name) {
		this.cen_name = cen_name;
	}



	public String getCen_phone() {
		return cen_phone;
	}



	public void setCen_phone(String cen_phone) {
		this.cen_phone = cen_phone;
	}



	public boolean isEnabled() {
		return enabled;
	}



	public void setEnabled(boolean enabled) {
		this.enabled = enabled;
	}



	public String getCustomer() {
		return customer;
	}



	public void setCustomer(String customer) {
		this.customer = customer;
	}



	private void setEnabled(char enabled) {
		// TODO Auto-generated method stub
		this.enabled = (enabled=='T')? true : false; 
	}



	@Override
	public String toString() {
		return "CenterDto [id=" + id + ", password=" + password + ", addr1=" + addr1 + ", addr2=" + addr2
				+ ", cen_name=" + cen_name + ", cen_phone=" + cen_phone + ", enabled=" + enabled + ", customer="
				+ customer + "]";
	}
	
	
	
}
