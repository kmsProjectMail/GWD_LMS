package com.min.edu.dto;

import java.util.Date;

public class Board_Dto {
	private String boardseq;
	private String userid  ;
	private String title   ;
	private String content ;
	private Date regdate ;
	private String delflag ;
	private Reply_Dto rdto;
	
	public Reply_Dto getRdto() {
		return rdto;
	}
	public void setRdto(Reply_Dto rdto) {
		this.rdto = rdto;
	}
	public String getBoardseq() {
		return boardseq;
	}
	public void setBoardseq(String boardseq) {
		this.boardseq = boardseq;
	}
	public String getUserid() {
		return userid;
	}
	public void setUserid(String userid) {
		this.userid = userid;
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
	public Date getRegdate() {
		return regdate;
	}
	public void setRegdate(Date regdate) {
		this.regdate = regdate;
	}
	public String getDelflag() {
		return delflag;
	}
	public void setDelflag(String delflag) {
		this.delflag = delflag;
	}
	@Override
	public String toString() {
		return "Board_Dto [boardseq=" + boardseq + ", userid=" + userid + ", title=" + title + ", content=" + content
				+ ", regdate=" + regdate + ", delflag=" + delflag + "]";
	}
	public Board_Dto(String boardseq, String userid, String title, String content, Date regdate, String delflag) {
		super();
		this.boardseq = boardseq;
		this.userid = userid;
		this.title = title;
		this.content = content;
		this.regdate = regdate;
		this.delflag = delflag;
	}
	public Board_Dto(String userid, String title, String content) {
		super();
		this.userid = userid;
		this.title = title;
		this.content = content;
	}
	
	public Board_Dto() {
		super();
		// TODO Auto-generated constructor stub
	}
	
}
