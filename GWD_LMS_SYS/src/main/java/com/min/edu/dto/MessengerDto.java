package com.min.edu.dto;

public class MessengerDto {

	private int seq;
	private String chatmember;
	private String content;
	private String recentdate;
	
	public MessengerDto() {
	}

	public MessengerDto(int seq, String chatmember, String content, String recentdate) {
		super();
		this.seq = seq;
		this.chatmember = chatmember;
		this.content = content;
		this.recentdate = recentdate;
	}

	public int getSeq() {
		return seq;
	}

	public void setSeq(int seq) {
		this.seq = seq;
	}

	public String getChatmember() {
		return chatmember;
	}

	public void setChatmember(String chatmember) {
		this.chatmember = chatmember;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getRecentdate() {
		return recentdate;
	}

	public void setRecentdate(String recentdate) {
		this.recentdate = recentdate;
	}

	@Override
	public String toString() {
		return "MessengerDto [seq=" + seq + ", chatmember=" + chatmember + ", content=" + content + ", recentdate="
				+ recentdate + "]";
	}
	
	
	
}
