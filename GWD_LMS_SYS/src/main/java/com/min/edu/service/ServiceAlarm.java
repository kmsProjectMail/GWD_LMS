package com.min.edu.service;

import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.min.edu.dto.Calender_Dto;

@Service
public class ServiceAlarm {

	@Autowired
	private SqlSessionTemplate session;
	
	private final String NS="com.min.edu.model.IDao_Alarm.";
	
	public List<Calender_Dto> selectAlarm(){
		return session.selectList(NS+"selectAlarm");
	}

	public List<Calender_Dto> getAlarm(String id){
		System.out.println("ServiceAlarm getAlarm입장"+ id);
		if(id == null) {
			System.out.println("로그인안하");
		}
		return session.selectList(NS+"getAlarm", id);
	}
		
}
