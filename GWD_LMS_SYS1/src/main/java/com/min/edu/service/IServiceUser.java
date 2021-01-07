package com.min.edu.service;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

public interface IServiceUser extends UserDetailsService {

	public abstract UserDetails loadUserByUsername(String username) throws UsernameNotFoundException;
}
