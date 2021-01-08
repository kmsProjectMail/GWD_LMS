package com.min.edu.dto;

public class Calender_Dto {

	private String alarm_date  ;
	private String alarm_check ;
	public Calender_Dto() {
		// TODO Auto-generated constructor stub
	}
	public String getAlarm_date() {
		return alarm_date;
	}
	public void setAlarm_date(String alarm_date) {
		this.alarm_date = alarm_date;
	}
	public String getAlarm_check() {
		return alarm_check;
	}
	public void setAlarm_check(String alarm_check) {
		this.alarm_check = alarm_check;
	}
	@Override
	public String toString() {
		return "Calender [alarm_date=" + alarm_date + ", alarm_check=" + alarm_check + ", student=" + student + "]";
	}
	private Student student;
	public Student getStudent() {
		return student;
	}
	public void setStudent(Student student) {
		this.student = student;
	}
	
}
