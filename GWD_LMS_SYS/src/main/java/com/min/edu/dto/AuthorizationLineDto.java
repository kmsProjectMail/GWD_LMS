package com.min.edu.dto;

import java.io.Serializable;

public class AuthorizationLineDto implements Serializable{

	private static final long serialVersionUID = -691017380627212807L;
	private String authorization_line;
	private String authorization_group;
	
	public AuthorizationLineDto() {
	}

	@Override
	public String toString() {
		return "AuthorizationLineDto [authorization_line=" + authorization_line + ", authorization_group="
				+ authorization_group + "]";
	}

	public AuthorizationLineDto(String authorization_line, String authorization_group) {
		super();
		this.authorization_line = authorization_line;
		this.authorization_group = authorization_group;
	}

	public String getAuthorization_line() {
		return authorization_line;
	}

	public void setAuthorization_line(String authorization_line) {
		this.authorization_line = authorization_line;
	}

	public String getAuthorization_group() {
		return authorization_group;
	}

	public void setAuthorization_group(String authorization_group) {
		this.authorization_group = authorization_group;
	}
	
}
