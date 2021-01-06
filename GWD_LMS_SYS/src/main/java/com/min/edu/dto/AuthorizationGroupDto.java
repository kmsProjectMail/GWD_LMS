package com.min.edu.dto;

import java.io.Serializable;

public class AuthorizationGroupDto implements Serializable{

	private static final long serialVersionUID = -7973935648879135942L;
	private String authorization_group;
	private String authorized_person;
	private String authorized_status;
	private String authorized_stamp;
	private String aram;
	private String group_seq;
	public AuthorizationGroupDto() {
	}
	@Override
	public String toString() {
		return "AuthorizationGroupDto [authorization_group=" + authorization_group + ", authorized_person="
				+ authorized_person + ", authorized_status=" + authorized_status + ", authorized_stamp="
				+ authorized_stamp + ", aram=" + aram + ", group_seq=" + group_seq + "]";
	}
	public AuthorizationGroupDto(String authorized_person, String authorized_status) {
		super();
		this.authorized_person = authorized_person;
		this.authorized_status = authorized_status;
	}
	public String getAuthorization_group() {
		return authorization_group;
	}
	public void setAuthorization_group(String authorization_group) {
		this.authorization_group = authorization_group;
	}
	public String getAuthorized_person() {
		return authorized_person;
	}
	public void setAuthorized_person(String authorized_person) {
		this.authorized_person = authorized_person;
	}
	public String getAuthorized_status() {
		return authorized_status;
	}
	public void setAuthorized_status(String authorized_status) {
		this.authorized_status = authorized_status;
	}
	public String getAuthorized_stamp() {
		return authorized_stamp;
	}
	public void setAuthorized_stamp(String authorized_stamp) {
		this.authorized_stamp = authorized_stamp;
	}
	public String getAram() {
		return aram;
	}
	public void setAram(String aram) {
		this.aram = aram;
	}
	public String getGroup_seq() {
		return group_seq;
	}
	public void setGroup_seq(String group_seq) {
		this.group_seq = group_seq;
	}
	
	

}
