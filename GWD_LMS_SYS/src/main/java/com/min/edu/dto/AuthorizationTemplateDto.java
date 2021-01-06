package com.min.edu.dto;

import java.io.Serializable;

public class AuthorizationTemplateDto implements Serializable{

	private static final long serialVersionUID = -6793030319577566712L;
	private String template_seq;
	private String template_type;
	private String template_link;
	public AuthorizationTemplateDto() {
	}
	public AuthorizationTemplateDto(String template_seq, String template_type, String template_link) {
		super();
		this.template_seq = template_seq;
		this.template_type = template_type;
		this.template_link = template_link;
	}
	@Override
	public String toString() {
		return "AuthorizationTemplateDto [template_seq=" + template_seq + ", template_type=" + template_type
				+ ", template_link=" + template_link + "]";
	}
	public String getTemplate_seq() {
		return template_seq;
	}
	public void setTemplate_seq(String template_seq) {
		this.template_seq = template_seq;
	}
	public String getTemplate_type() {
		return template_type;
	}
	public void setTemplate_type(String template_type) {
		this.template_type = template_type;
	}
	public String getTemplate_link() {
		return template_link;
	}
	public void setTemplate_link(String template_link) {
		this.template_link = template_link;
	}
	
	
	
}
