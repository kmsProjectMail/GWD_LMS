package com.min.edu.vo;

import org.springframework.security.core.GrantedAuthority;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.ToString;

@Data
@AllArgsConstructor
@ToString
public class AuthVo  implements GrantedAuthority{
	private String auth;
	private String info;
	@Override
	public String getAuthority() {
		// TODO Auto-generated method stub
		return this.auth;
	}
}
