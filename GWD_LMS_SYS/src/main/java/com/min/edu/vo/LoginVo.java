package com.min.edu.vo;


public class LoginVo {
	private String id;
	private String password;
	private String enabled;
	public LoginVo() {
		// TODO Auto-generated constructor stub
	}
	
	
	public LoginVo(String id, String password, String enabled) {
		super();
		this.id = id;
		this.password = password;
		this.enabled = enabled;
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
	public String getEnabled() {
		return enabled;
	}
	public void setEnabled(String enabled) {
		this.enabled = enabled;
	}
	@Override
	public String toString() {
		return "LoginVo [id=" + id + ", password=" + password + ", enabled=" + enabled + "]";
	}
	
	
}
