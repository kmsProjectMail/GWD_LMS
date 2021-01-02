package com.min.edu.service;

import java.util.List;

import org.springframework.security.core.GrantedAuthority;

public interface IServiceAuth {
	
	public List<GrantedAuthority> loadAuthorities(String id);
}
