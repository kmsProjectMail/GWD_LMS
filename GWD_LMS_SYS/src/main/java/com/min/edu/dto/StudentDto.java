package com.min.edu.dto;

import com.min.edu.dto.CalendarDto;

public class StudentDto {
	private String id;
	private String password;
	private String name;
	private String addr1;
	private String addr2;
	private String email;
	private String phone;
	private boolean enabled;
	private String mgr;
	private int cnt;
	
	private CalendarDto cDto;
	
	public StudentDto() {
		// TODO Auto-generated constructor stub
	}
	
	//SignUp Constructor
	public StudentDto(String id, String pw, String name, String addr1, String addr2, String email, String phone) {
		super();
		this.id = id;
		this.password = pw;
		this.name = name;
		this.addr1 = addr1;
		this.addr2 = addr2;
		this.email = email;
		this.phone = phone;
	}
	
	private void setEnabled(char enabled) {
		// TODO Auto-generated method stub
		this.enabled = (enabled=='T')? true : false; 
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

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
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

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public boolean isEnabled() {
		return enabled;
	}

	public void setEnabled(boolean enabled) {
		this.enabled = enabled;
	}

	public String getMgr() {
		return mgr;
	}

	public void setMgr(String mgr) {
		this.mgr = mgr;
	}

	public CalendarDto getcDto() {
		return cDto;
	}

	public void setcDto(CalendarDto cDto) {
		this.cDto = cDto;
	}
	
	public int getCnt() {
		return cnt;
	}

	public void setCnt(int cnt) {
		this.cnt = cnt;
	}

	@Override
	public String toString() {
		return "StudentDto [id=" + id + ", password=" + password + ", name=" + name + ", addr1=" + addr1 + ", addr2="
				+ addr2 + ", email=" + email + ", phone=" + phone + ", enabled=" + enabled + ", mgr=" + mgr + ", cnt="
				+ cnt + ", cDto=" + cDto + "]";
	}
	
	
}
