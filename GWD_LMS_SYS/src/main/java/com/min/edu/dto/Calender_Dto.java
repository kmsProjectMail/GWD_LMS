package com.min.edu.dto;

public class Calender_Dto {

	private String alarm_date  ;
	private String alarm_check ;
	private String content ;
	private String title ;
	private String start ;
	
	public Calender_Dto(String alarm_date, String alarm_check, String content, String title, String start,
			Student student) {
		super();
		this.alarm_date = alarm_date;
		this.alarm_check = alarm_check;
		this.content = content;
		this.title = title;
		this.start = start;
		this.student = student;
	}
	public String getStart() {
		return start;
	}
	public void setStart(String start) {
		this.start = start;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public Calender_Dto() {
		// TODO Auto-generated constructor stub
	}
	public String getAlarm_date() {
		return alarm_date;
	}
	public void setAlarm_date(String alarm_date) {
		this.alarm_date = alarm_date;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public String getAlarm_check() {
		return alarm_check;
	}
	public void setAlarm_check(String alarm_check) {
		this.alarm_check = alarm_check;
	}
	@Override
	public String toString() {
		return "Calender_Dto [alarm_date=" + alarm_date + ", alarm_check=" + alarm_check + ", content=" + content
				+ ", title=" + title + ", start=" + start + ", student=" + student + "]";
	}
	private Student student;
	public Student getStudent() {
		return student;
	}
	public void setStudent(Student student) {
		this.student = student;
	}
	
}
