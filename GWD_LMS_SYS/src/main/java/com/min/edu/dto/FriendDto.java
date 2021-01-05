package com.min.edu.dto;

public class FriendDto {

	private int seq;
	private String id;
	private String friend_id;
	
	public FriendDto() {
		// TODO Auto-generated constructor stub
	}

	public FriendDto(int seq, String id, String friend_id) {
		super();
		this.seq = seq;
		this.id = id;
		this.friend_id = friend_id;
	}

	public int getSeq() {
		return seq;
	}

	public void setSeq(int seq) {
		this.seq = seq;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getFriend_id() {
		return friend_id;
	}

	public void setFriend_id(String friend_id) {
		this.friend_id = friend_id;
	}

	@Override
	public String toString() {
		return "FriendDto [seq=" + seq + ", id=" + id + ", friend_id=" + friend_id + "]";
	}
	
	
	
}
