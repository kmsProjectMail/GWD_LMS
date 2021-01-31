package com.min.edu.dao;

import java.util.List;
import java.util.Map;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.min.edu.dto.CalendarDto;
import com.min.edu.dto.StudentDto;

@Repository
public class DaoCalendarImpl implements IDaoCalendar {

	private final String NS="com.min.edu.dao.IDaoCalnedar.";
	
	@Autowired
	private SqlSessionTemplate sqlSession;
	
	@Override
	public boolean insertMeet(CalendarDto dto) {
		int cnt = sqlSession.insert(NS+"insertMeet", dto);
		return cnt>0?true:false;
	}

	@Override
	public List<CalendarDto> selectMeet() {
		return sqlSession.selectList(NS+"selectMeet");
	}

	@Override
	public boolean deleteSchedule(String id) {
		int cnt = sqlSession.delete(NS+"deleteSchedule", id);
		return cnt>0?true:false;
	}
	
	@Override
	public boolean updateMeet(CalendarDto dto) {
		int cnt = sqlSession.update(NS+"updateMeet", dto);
		return cnt>0?true:false;
	}

	@Override
	public boolean insertSchedule(CalendarDto dto) {
		int cnt = sqlSession.insert(NS+"insertSchedule", dto);
		return cnt>0?true:false;
	}

	@Override
	public List<CalendarDto> selectSchedule() {
		return sqlSession.selectList(NS+"selectSchedule");
	}

	@Override
	public boolean updateSchedule(CalendarDto dto) {
		int cnt = sqlSession.update(NS+"updateSchedule", dto);
		return cnt>0?true:false;
	}

	@Override
	public boolean updateAlarm(Map<String, Object> map) {
		int cnt = sqlSession.update(NS+"updateAlarm", map);
		return cnt>0?true:false;
	}
	
	@Override
	public StudentDto selectOneSchedule(String id) {
		return sqlSession.selectOne(NS+"selectOneSchedule",id);
	}

	@Override
	public List<StudentDto> selectMeetList() {
		return sqlSession.selectList(NS+"selectMeetList");
	}

	@Override
	public List<StudentDto> searchMeetList(String keyword) {
		return sqlSession.selectList(NS+"searchMeetList", keyword);
	}
	
	@Override
	public List<CalendarDto> searchShceduleList(String keyword) {
		return sqlSession.selectList(NS+"searchShceduleList", keyword);
	}
	
	@Override
	public boolean countMeet(Map<String, Object> map) {
		int cnt = sqlSession.selectOne(NS+"countMeet", map);
		return cnt>0?true:false;
	}

	@Override
	public boolean countSchedule(String start) {
		int cnt = sqlSession.selectOne(NS+"countSchedule", start);
		return cnt>0?true:false;
	}

	@Override
	public boolean countMyMeet(Map<String, Object> map) {
		int cnt = sqlSession.selectOne(NS+"countMyMeet", map);
		return cnt>0?true:false;
	}

	@Override
	public List<CalendarDto> getOneList(String id) {
		// TODO Auto-generated method stub
		return sqlSession.selectList(NS+"getOneList", id);
	}


}
