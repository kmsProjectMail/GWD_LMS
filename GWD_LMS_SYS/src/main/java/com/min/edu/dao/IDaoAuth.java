package com.min.edu.dao;

import java.util.List;

import org.springframework.security.core.GrantedAuthority;

import com.min.edu.dto.MemberAuthDto;

public interface IDaoAuth {

	public List<GrantedAuthority> loadAuthorities(String id);

	public MemberAuthDto selectUserAuth(String id);

}
