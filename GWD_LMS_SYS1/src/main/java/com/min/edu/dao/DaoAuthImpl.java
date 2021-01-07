package com.min.edu.dao;

import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Repository;

@Repository
public class DaoAuthImpl implements IDaoAuth{
	
	@Autowired
	private SqlSessionTemplate sqlSession;
	
	private final String NS = "authmapper.";
	
	private Logger logger = LoggerFactory.getLogger(this.getClass());

	
	@Override
	public List<GrantedAuthority> loadAuthorities(String id) {
		logger.info("loadAuthorities parameter : id = {}",id);
		return sqlSession.selectList(NS+"loadAuthorities",id);
		
	}

}
