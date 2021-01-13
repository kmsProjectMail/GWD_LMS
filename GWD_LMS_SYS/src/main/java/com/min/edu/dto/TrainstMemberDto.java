package com.min.edu.dto;

import lombok.Data;
import lombok.ToString;

public class TrainstMemberDto {
	private String id;
	private String password;
	private String trainst_cst_id;
	private String trprchap;
	private String trprchapemail;
	private String trprchaptel;
	private boolean enabled;

	public TrainstMemberDto() {
		// TODO Auto-generated constructor stub
	}
	
	public TrainstMemberDto(String id, String pw, String trainst_cst_id, String trprchap, String trprchapemail,
			String trprchaptel) {
		super();
		this.id = id;
		this.password = pw;
		this.trainst_cst_id = trainst_cst_id;
		this.trprchap = trprchap;
		this.trprchapemail = trprchapemail;
		this.trprchaptel = trprchaptel;
	}
	
	public String getTrainst_cst_id() {
		return trainst_cst_id;
	}
	
	public void setTrainst_cst_id(String trainst_cst_id) {
		this.trainst_cst_id = trainst_cst_id;
	}
	
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	
	public String getTrprchap() {
		return trprchap;
	}

	public void setTrprchap(String trprchap) {
		this.trprchap = trprchap;
	}

	public String getTrprchapemail() {
		return trprchapemail;
	}

	public void setTrprchapemail(String trprchapemail) {
		this.trprchapemail = trprchapemail;
	}

	public String getTrprchaptel() {
		return trprchaptel;
	}

	public void setTrprchaptel(String trprchaptel) {
		this.trprchaptel = trprchaptel;
	}

	public boolean isEnabled() {
		return enabled;
	}

	public void setEnabled(boolean enabled) {
		this.enabled = enabled;
	}

	private void setEnabled(String enabled) {
		// TODO Auto-generated method stub
		this.enabled = (enabled.equals("T"))? true : false; 
	}

	@Override
	public String toString() {
		return "TrainstMemberDto [id=" + id + ", password=" + password + ", trainst_cst_id=" + trainst_cst_id
				+ ", trprchap=" + trprchap + ", trprchapemail=" + trprchapemail + ", trprchaptel=" + trprchaptel
				+ ", enabled=" + enabled + "]";
	}
	

	
}
