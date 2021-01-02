package com.min.edu.dao;

import org.mybatis.spring.SqlSessionTemplate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.min.edu.vo.LoginVo;
@Repository
public class DaoUserImpl implements IDaoUser {
	
	@Autowired
	private SqlSessionTemplate sqlSession;
	
	private final String NS ="usermapper.";
	private final Logger logger = LoggerFactory.getLogger(this.getClass());
	
	@Override
	public LoginVo login(String id) {
		// TODO Auto-generated method stub
		logger.info("DaoUserImpl parameter : id  = {}", id);
		
		
		return sqlSession.selectOne(NS+"login", id);
	}
	
	
}
