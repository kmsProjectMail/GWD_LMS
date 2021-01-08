package com.min.edu.dto;

public class FriendDto {

	private int seq;
	private String id;
	private String friend_id;
	private String name;
	
	public FriendDto() {
		// TODO Auto-generated constructor stub
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

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public FriendDto(int seq, String id, String friend_id, String name) {
		super();
		this.seq = seq;
		this.id = id;
		this.friend_id = friend_id;
		this.name = name;
	}

	@Override
	public String toString() {
		return "FriendDto [seq=" + seq + ", id=" + id + ", friend_id=" + friend_id + ", name=" + name + "]";
	}
	
}