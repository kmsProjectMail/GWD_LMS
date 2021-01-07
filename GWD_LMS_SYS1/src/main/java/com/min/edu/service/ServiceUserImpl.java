package com.min.edu.service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.support.MessageSourceAccessor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.SpringSecurityMessageSource;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.min.edu.dao.IDaoUser;
import com.min.edu.info.UserInfo;
import com.min.edu.vo.LoginVo;

@Service
public class ServiceUserImpl implements IServiceUser {

	@Autowired
	private IServiceAuth authService;
	
	private MessageSourceAccessor messages = SpringSecurityMessageSource.getAccessor();
	private static final Logger logger = LoggerFactory.getLogger(ServiceUserImpl.class);

	
	@Autowired
	private IDaoUser dao;
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		// TODO Auto-generated method stub
		
		logger.info("loadUserByUsername 입력된 id : {}",username);
		
		LoginVo loginvo = dao.login(username);
		
		//아이디가 없으면 예외를 발생시킨다.
//		logger.info("loadUserByUsername 검색결과 : {}",loginvo.toString());

		if(loginvo == null) {
			logger.info("해당 아이디가 없습니다 . 입력된 아이디 : {}" ,username);
			UsernameNotFoundException ue = new UsernameNotFoundException(
					messages.getMessage("DaoUserImpl.notFound", new Object[] {username}, "User{0} not found"));
				throw ue;
		}
		
		Set<GrantedAuthority> userAuthSet = new HashSet<GrantedAuthority>();
		
		userAuthSet.addAll(authService.loadAuthorities(loginvo.getId()));
		
		logger.info("userAuthSet.toString () : {}",userAuthSet.toString());
		
		if(userAuthSet.size()==0) {
			logger.info("사용자 {} 어떠한 권한도 가지고 있지 않습니다. 확인이 필요합니다",loginvo.getId());
			UsernameNotFoundException ue = new UsernameNotFoundException(
					messages.getMessage("DaoAuthImpl.noAuthority", 
							new Object[] {loginvo.getId()},"user {0} has no GrantedAuthority"));
			throw ue;
		}
		
		List<GrantedAuthority> dbAuths = new ArrayList<GrantedAuthority>(userAuthSet);
		logger.info("dbAuths :{}",dbAuths.toArray());
		
		UserInfo result = new UserInfo(loginvo.getId(), loginvo.getPassword(), loginvo.getEnabled().equalsIgnoreCase("T"), dbAuths);
		
		logger.info("loadUserByUsername result (UserDetails) : {}", result.toString());
		
		
		return result;
	}

}
