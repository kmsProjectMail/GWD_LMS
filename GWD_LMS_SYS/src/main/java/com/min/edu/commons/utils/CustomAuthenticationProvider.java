package com.min.edu.commons.utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import com.min.edu.info.UserInfo;
import com.min.edu.service.IServiceUser;

public class CustomAuthenticationProvider implements AuthenticationProvider {

	private Logger logger = LoggerFactory.getLogger(this.getClass());

	@Autowired 
	private IServiceUser userService;
	
	@Autowired
	private BCryptPasswordEncoder passwordEncoder;

	@Override
	public Authentication authenticate(Authentication authentication) throws AuthenticationException {
		// TODO Auto-generated method stub
		logger.info("this is CustomAuthenticationProvider.authenticate getPrincipal {}, getCredentials{}", authentication.getPrincipal(), authentication.getCredentials());
		String username = (String)authentication.getPrincipal();
		String password = (String)authentication.getCredentials();
		UserInfo user = (UserInfo) userService.loadUserByUsername(username);
		if(!matchPassword(password, user.getPassword())) {
			throw new BadCredentialsException("비밀번호 불일치");
		}else if(!user.isEnabled()) {
			throw new BadCredentialsException(username);
			
		}
		return new UsernamePasswordAuthenticationToken(username, user.getPassword(), user.getAuthorities());
		
	}

	@Override
	public boolean supports(Class<?> authentication) {
		// TODO Auto-generated method stub
		return true;
	}
	
	private boolean matchPassword(String loginPwd, String inputPwd){
		
//		String enPassword = passwordEncoder.encode(loginPwd);
		logger.info("matchPassword inputPwd {}",inputPwd);
		logger.info("matchPassword loginPwd {}",loginPwd);
		logger.info("matchPassword matches {}",passwordEncoder.matches(loginPwd,inputPwd));
		return passwordEncoder.matches(loginPwd,inputPwd);
		
	}
	
	
}
