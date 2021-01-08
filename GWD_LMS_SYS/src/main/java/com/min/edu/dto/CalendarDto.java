package com.min.edu.dto;

import java.io.Serializable;

public class CalendarDto implements Serializable{

	private static final long serialVersionUID = 8468745829019997414L;
	
	private String id;
	private String calendar_id;
	private String title;
	private String content;
	private String category;
	private String start;
	private String end;
	private String alarm_date;
	private String student_id;
	private String meet_id;
	private String alarm_check;

	public CalendarDto() {
		super();
	}

	public CalendarDto(String id, String calendar_id, String title, String content, String category, String start,
			String end, String alarm_date, String student_id, String meet_id, String alarm_check) {
		super();
		this.id = id;
		this.calendar_id = calendar_id;
		this.title = title;
		this.content = content;
		this.category = category;
		this.start = start;
		this.end = end;
		this.alarm_date = alarm_date;
		this.student_id = student_id;
		this.meet_id = meet_id;
		this.alarm_check = alarm_check;
	}

	@Override
	public String toString() {
		return "CalendarDto [id=" + id + ", calendar_id=" + calendar_id + ", title=" + title + ", content=" + content
				+ ", category=" + category + ", start=" + start + ", end=" + end + ", alarm_date=" + alarm_date
				+ ", student_id=" + student_id + ", meet_id=" + meet_id + ", alarm_check=" + alarm_check + "]";
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getCalendar_id() {
		return calendar_id;
	}

	public void setCalendar_id(String calendar_id) {
		this.calendar_id = calendar_id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public String getStart() {
		return start;
	}

	public void setStart(String start) {
		this.start = start;
	}

	public String getEnd() {
		return end;
	}

	public void setEnd(String end) {
		this.end = end;
	}

	public String getAlarm_date() {
		return alarm_date;
	}

	public void setAlarm_date(String alarm_date) {
		this.alarm_date = alarm_date;
	}

	public String getStudent_id() {
		return student_id;
	}

	public void setStudent_id(String student_id) {
		this.student_id = student_id;
	}

	public String getMeet_id() {
		return meet_id;
	}

	public void setMeet_id(String meet_id) {
		this.meet_id = meet_id;
	}

	public String getAlarm_check() {
		return alarm_check;
	}

	public void setAlarm_check(String alarm_check) {
		this.alarm_check = alarm_check;
	}

}
