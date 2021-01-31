package com.min.edu.service;

import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.min.edu.dao.IDaoCalendar;
import com.min.edu.dto.CalendarDto;
import com.min.edu.dto.StudentDto;

@Service
public class ServiceCalendarImpl implements IServiceCalendar {
	
	private Logger logger = LoggerFactory.getLogger(this.getClass());
	
	@Autowired
	private IDaoCalendar iDao;
	
	@Override
	public boolean insertMeet(CalendarDto dto) {
		logger.info("insertMeet{} \t",dto);
		return iDao.insertMeet(dto);
	}

	@Override
	public List<CalendarDto> selectMeet() {
		logger.info("selectMeet");
		return iDao.selectMeet();
	}

	@Override
	public boolean deleteSchedule(String id) {
		logger.info("deleteSchedule{} \t",id);
		return iDao.deleteSchedule(id);
	}
	
	@Override
	public boolean updateMeet(CalendarDto dto) {
		logger.info("updateMeet{} \t",dto);
		return iDao.updateMeet(dto);
	}

	@Override
	public boolean insertSchedule(CalendarDto dto) {
		logger.info("insertSchedule{} \t",dto);
		return iDao.insertSchedule(dto);
	}

	@Override
	public List<CalendarDto> selectSchedule() {
		logger.info("selectSchedule");
		return iDao.selectSchedule();
	}

	@Override
	public boolean updateSchedule(CalendarDto dto) {
		logger.info("updateSchedule{} \t",dto);
		return iDao.updateSchedule(dto);
	}

	@Override
	public boolean updateAlarm(Map<String, Object> map) {
		logger.info("updateAlarm{} \t",map);
		return iDao.updateAlarm(map);
	}
	
	@Override
	public StudentDto selectOneSchedule(String id) {
		logger.info("selectOneSchedule{} \t",id);
		return iDao.selectOneSchedule(id);
	}

	@Override
	public List<StudentDto> selectMeetList() {
		logger.info("selectMeetList{} \t");
		return iDao.selectMeetList();
	}

	@Override
	public List<StudentDto> searchMeetList(String keyword) {
		logger.info("searchMeetList{} \t",keyword);
		return iDao.searchMeetList(keyword);
	}
	
	@Override
	public List<CalendarDto> searchShceduleList(String keyword) {
		logger.info("searchShceduleList{} \t",keyword);
		return iDao.searchShceduleList(keyword);
	}
	
	@Override
	public boolean countMeet(Map<String, Object> map) {
		logger.info("countMeet{} \t",map);
		return iDao.countMeet(map);
	}

	@Override
	public boolean countSchedule(String start) {
		logger.info("countSchedule{} \t",start);
		return iDao.countSchedule(start);
	}

	@Override
	public boolean countMyMeet(Map<String, Object> map) {
		logger.info("countMyMeet{} \t",map);
		return iDao.countMyMeet(map);
	}

	@Override
	public List<CalendarDto> getOneList(String id) {
		// TODO Auto-generated method stub
		return iDao.getOneList(id);
	}


}
