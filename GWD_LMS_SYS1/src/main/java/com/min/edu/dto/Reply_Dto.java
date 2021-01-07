package com.min.edu.dto;

public class Reply_Dto {
	
	private String boardseq;
	private String content ;
	private String regdate ;
	private String replyseq;
	private String title   ;
	private String userid  ;
	private Board_Dto bdto;
	
	public Board_Dto getBdto() {
		return bdto;
	}
	public void setBdto(Board_Dto bdto) {
		this.bdto = bdto;
	}
	public String getBoardseq() {
		return boardseq;
	}
	public void setBoardseq(String boardseq) {
		this.boardseq = boardseq;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public String getRegdate() {
		return regdate;
	}
	public void setRegdate(String regdate) {
		this.regdate = regdate;
	}
	public String getReplyseq() {
		return replyseq;
	}
	public void setReplyseq(String replyseq) {
		this.replyseq = replyseq;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getUserid() {
		return userid;
	}
	public void setUserid(String userid) {
		this.userid = userid;
	}
	@Override
	public String toString() {
		return "Reply_Dto [boardseq=" + boardseq + ", content=" + content + ", regdate=" + regdate + ", replyseq="
				+ replyseq + ", title=" + title + ", userid=" + userid + "]";
	}
	public Reply_Dto(String boardseq, String content, String regdate, String replyseq, String title, String userid) {
		super();
		this.boardseq = boardseq;
		this.content = content;
		this.regdate = regdate;
		this.replyseq = replyseq;
		this.title = title;
		this.userid = userid;
	}
	public Reply_Dto(String boardseq, String content, String title, String userid) {
		super();
		this.boardseq = boardseq;
		this.content = content;
		this.title = title;
		this.userid = userid;
	}
	public Reply_Dto() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	
	
}
