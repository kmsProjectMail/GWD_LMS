package com.min.edu.dto;

import java.util.Date;

public class FileBoardDto {

	private String f_seq;
	private String b_seq;
	private String origin_fname;
	private String stored_fname;
	private String file_size;
	private Date f_regdate;
	private String f_delflag;
	public FileBoardDto() {
	}
	public FileBoardDto(String b_seq, String origin_fname, String stored_fname, String file_size
			) {
		super();
		this.b_seq = b_seq;
		this.origin_fname = origin_fname;
		this.stored_fname = stored_fname;
		this.file_size = file_size;
	}
	@Override
	public String toString() {
		return "FileBoardDto [f_seq=" + f_seq + ", b_seq=" + b_seq + ", origin_fname=" + origin_fname
				+ ", stored_fname=" + stored_fname + ", file_size=" + file_size + ", f_regdate=" + f_regdate
				+ ", f_delflag=" + f_delflag + "]";
	}
	public String getF_seq() {
		return f_seq;
	}
	public void setF_seq(String f_seq) {
		this.f_seq = f_seq;
	}
	public String getB_seq() {
		return b_seq;
	}
	public void setB_seq(String b_seq) {
		this.b_seq = b_seq;
	}
	public String getOrigin_fname() {
		return origin_fname;
	}
	public void setOrigin_fname(String origin_fname) {
		this.origin_fname = origin_fname;
	}
	public String getStored_fname() {
		return stored_fname;
	}
	public void setStored_fname(String stored_fname) {
		this.stored_fname = stored_fname;
	}
	public String getFile_size() {
		return file_size;
	}
	public void setFile_size(String file_size) {
		this.file_size = file_size;
	}
	public Date getF_regdate() {
		return f_regdate;
	}
	public void setF_regdate(Date f_regdate) {
		this.f_regdate = f_regdate;
	}
	public String getF_delflag() {
		return f_delflag;
	}
	public void setF_delflag(String f_delflag) {
		this.f_delflag = f_delflag;
	}

	
}
