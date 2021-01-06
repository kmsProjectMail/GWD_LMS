package com.min.edu.dto;

import java.io.Serializable;
import java.util.Date;

public class AuthorizationFileDto implements Serializable{

	private static final long serialVersionUID = 597283605710701262L;
	private String file_seq;
	private String authorization_seq;
	private String file_origin_fname;
	private String file_stored_fname;
	private String file_size;
	private Date file_regdate;
	public AuthorizationFileDto() {
	}
	public AuthorizationFileDto(String authorization_seq, String file_origin_fname, String file_stored_fname,
			String file_size) {
		super();
		this.authorization_seq = authorization_seq;
		this.file_origin_fname = file_origin_fname;
		this.file_stored_fname = file_stored_fname;
		this.file_size = file_size;
	}
	@Override
	public String toString() {
		return "AuthorizationFileDto [file_seq=" + file_seq + ", authorization_seq=" + authorization_seq
				+ ", file_origin_fname=" + file_origin_fname + ", file_stored_fname=" + file_stored_fname
				+ ", file_size=" + file_size + ", file_regdate=" + file_regdate + "]";
	}
	public String getFile_seq() {
		return file_seq;
	}
	public void setFile_seq(String file_seq) {
		this.file_seq = file_seq;
	}
	public String getAuthorization_seq() {
		return authorization_seq;
	}
	public void setAuthorization_seq(String authorization_seq) {
		this.authorization_seq = authorization_seq;
	}
	public String getFile_origin_fname() {
		return file_origin_fname;
	}
	public void setFile_origin_fname(String file_origin_fname) {
		this.file_origin_fname = file_origin_fname;
	}
	public String getFile_stored_fname() {
		return file_stored_fname;
	}
	public void setFile_stored_fname(String file_stored_fname) {
		this.file_stored_fname = file_stored_fname;
	}
	public String getFile_size() {
		return file_size;
	}
	public void setFile_size(String file_size) {
		this.file_size = file_size;
	}
	public Date getFile_regdate() {
		return file_regdate;
	}
	public void setFile_regdate(Date file_regdate) {
		this.file_regdate = file_regdate;
	}
	
	
}
