package com.min.edu.dto;

public class MemberAuthDto {
	private String auth;
	private String id;
	
	public MemberAuthDto() {
		// TODO Auto-generated constructor stub
	}
	
	public MemberAuthDto(String auth, String id) {
		super();
		this.auth = auth;
		this.id = id;
	}


	public String getAuth() {
		return auth;
	}


	public void setAuth(String auth) {
		this.auth = auth;
	}


	public String getId() {
		return id;
	}


	public void setId(String id) {
		this.id = id;
	}


	@Override
	public String toString() {
		return "MemberAuthDto [auth=" + auth + ", id=" + id + "]";
	}
	
	
}
