package com.min.edu.service;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Service;

import com.min.edu.dao.IDaoAuth;

@Service
public class ServiceAuthImpl implements IServiceAuth{
	@Autowired
	private IDaoAuth dao;
	
	private Logger logger = LoggerFactory.getLogger(this.getClass());

	@Override
	public List<GrantedAuthority> loadAuthorities(String id) {
		// TODO Auto-generated method stub
		logger.info("ServiceAuthImpe - loadAuthrities parameter : id ={}",id );
		return dao.loadAuthorities(id);
	}

}
