package com.min.edu.dao;

import java.util.List;

import org.springframework.security.core.GrantedAuthority;

public interface IDaoAuth {

	public List<GrantedAuthority> loadAuthorities(String id);

}
