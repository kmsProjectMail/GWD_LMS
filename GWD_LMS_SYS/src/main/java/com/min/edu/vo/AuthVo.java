package com.min.edu.vo;

import org.springframework.security.core.GrantedAuthority;


public class AuthVo  implements GrantedAuthority{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String auth;
	private String info;
	
	public AuthVo() {
		// TODO Auto-generated constructor stub
	}
	
	
	public AuthVo(String auth, String info) {
		super();
		this.auth = auth;
		this.info = info;
	}


	
	public String getAuth() {
		return auth;
	}


	public void setAuth(String auth) {
		this.auth = auth;
	}


	public String getInfo() {
		return info;
	}


	public void setInfo(String info) {
		this.info = info;
	}


	@Override
	public String toString() {
		return "AuthVo [auth=" + auth + ", info=" + info + "]";
	}


	@Override
	public String getAuthority() {
		// TODO Auto-generated method stub
		return this.auth;
	}
	
	
}
