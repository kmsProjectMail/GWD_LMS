package com.min.edu.dto;

import java.io.Serializable;

public class AuthorizationStampDto implements Serializable{

	private static final long serialVersionUID = 2779979336721799469L;
	private String id;
	private String stamp_image_link;
	public AuthorizationStampDto() {
	}
	public AuthorizationStampDto(String id, String stamp_image_link) {
		super();
		this.id = id;
		this.stamp_image_link = stamp_image_link;
	}
	@Override
	public String toString() {
		return "AuthorizationStampDto [id=" + id + ", stamp_image_link=" + stamp_image_link + "]";
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getStamp_image_link() {
		return stamp_image_link;
	}
	public void setStamp_image_link(String stamp_image_link) {
		this.stamp_image_link = stamp_image_link;
	}
	
}
