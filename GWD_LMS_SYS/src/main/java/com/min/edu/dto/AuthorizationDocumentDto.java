package com.min.edu.dto;

import java.io.Serializable;
import java.util.Date;

public class AuthorizationDocumentDto implements Serializable{
	private static final long serialVersionUID = 1165646623163612248L;
	private String authorization_seq;
	private String template_type;
	private String id;
	private String authorization_line;
	private String fileflag;
	private String complain;
	private Date regdate;
	private String title;
	private String content;
	public AuthorizationDocumentDto() {
	}
	public AuthorizationDocumentDto(String template_type, String id, String fileflag, String title, String content) {
		super();
		this.template_type = template_type;
		this.id = id;
		this.fileflag = fileflag;
		this.title = title;
		this.content = content;
	}
	@Override
	public String toString() {
		return "AuthorizationDocumentDto [authorization_seq=" + authorization_seq + ", template_type=" + template_type
				+ ", id=" + id + ", authorization_line=" + authorization_line + ", fileflag=" + fileflag + ", complain="
				+ complain + ", regdate=" + regdate + ", title=" + title + ", content=" + content + "]";
	}
	public String getAuthorization_seq() {
		return authorization_seq;
	}
	public void setAuthorization_seq(String authorization_seq) {
		this.authorization_seq = authorization_seq;
	}
	public String getTemplate_type() {
		return template_type;
	}
	public void setTemplate_type(String template_type) {
		this.template_type = template_type;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getAuthorization_line() {
		return authorization_line;
	}
	public void setAuthorization_line(String authorization_line) {
		this.authorization_line = authorization_line;
	}
	public String getFileflag() {
		return fileflag;
	}
	public void setFileflag(String fileflag) {
		this.fileflag = fileflag;
	}
	public String getComplain() {
		return complain;
	}
	public void setComplain(String complain) {
		this.complain = complain;
	}
	public Date getRegdate() {
		return regdate;
	}
	public void setRegdate(Date regdate) {
		this.regdate = regdate;
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
	
	
}
