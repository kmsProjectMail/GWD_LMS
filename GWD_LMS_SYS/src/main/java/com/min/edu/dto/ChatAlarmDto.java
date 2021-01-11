package com.min.edu.dto;

public class ChatAlarmDto {

	private String id;
	private String other;
	private String chatroom;
	private int cnt;

	public ChatAlarmDto() {
		// TODO Auto-generated constructor stub
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getOther() {
		return other;
	}

	public void setOther(String other) {
		this.other = other;
	}

	public String getChatroom() {
		return chatroom;
	}

	public void setChatroom(String chatroom) {
		this.chatroom = chatroom;
	}

	public int getCnt() {
		return cnt;
	}

	public void setCnt(int cnt) {
		this.cnt = cnt;
	}

	public ChatAlarmDto(String id, String other, String chatroom, int cnt) {
		super();
		this.id = id;
		this.other = other;
		this.chatroom = chatroom;
		this.cnt = cnt;
	}

	@Override
	public String toString() {
		return "ChatAlarmDto [id=" + id + ", other=" + other + ", chatroom=" + chatroom + ", cnt=" + cnt + "]";
	}

	
	
}