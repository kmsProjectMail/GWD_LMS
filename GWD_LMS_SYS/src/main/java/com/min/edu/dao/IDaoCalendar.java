package com.min.edu.dao;

import java.util.List;
import java.util.Map;

import com.min.edu.dto.CalendarDto;
import com.min.edu.dto.StudentDto;

public interface IDaoCalendar {
	
	public boolean insertMeet(CalendarDto dto);
	
	public List<CalendarDto> selectMeet();
	
	public boolean deleteSchedule(String id);
	
	public boolean updateMeet(CalendarDto dto);
	
	public boolean insertSchedule(CalendarDto dto);
	
	public List<CalendarDto> selectSchedule();
	
	public boolean updateSchedule(CalendarDto dto);
	
	public boolean updateAlarm(Map<String, Object> map);
	
	public StudentDto selectOneSchedule(String id);
	
	public List<StudentDto> selectMeetList();
	
	public List<StudentDto> searchMeetList(String keyword);
	
	public List<CalendarDto> searchShceduleList(String keyword);
}
