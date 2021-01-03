package com.min.edu.dto;

import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class TrainstMemberDto {
	private String id;
	private String password;
	private String trprinst_cst_id;
	private String trprchap;
	private String trprchapemail;
	private String trprchaptel;
	private boolean enabled;

	public TrainstMemberDto() {
		// TODO Auto-generated constructor stub
	}
	
	public TrainstMemberDto(String id, String pw, String trprinst_cst_id, String trprchap, String trprchapemail,
			String trprchaptel) {
		super();
		this.id = id;
		this.password = pw;
		this.trprinst_cst_id = trprinst_cst_id;
		this.trprchap = trprchap;
		this.trprchapemail = trprchapemail;
		this.trprchaptel = trprchaptel;
	}
	
	private void setEnabled(String enabled) {
		// TODO Auto-generated method stub
		this.enabled = (enabled.equals("T"))? true : false; 
	}
	

}
