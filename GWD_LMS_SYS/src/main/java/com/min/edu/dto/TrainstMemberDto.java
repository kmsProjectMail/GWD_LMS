package com.min.edu.dto;

import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class TrainstMemberDto {
	private String id;
	private String pw;
	private String trprinst_cst_id;
	private String trprchap;
	private String trprchapemail;
	private String trprchaptel;
	private boolean enabled;

	public TrainstMemberDto(String id, String pw, String trprinst_cst_id, String trprchap, String trprchapemail,
			String trprchaptel) {
		super();
		this.id = id;
		this.pw = pw;
		this.trprinst_cst_id = trprinst_cst_id;
		this.trprchap = trprchap;
		this.trprchapemail = trprchapemail;
		this.trprchaptel = trprchaptel;
	}
	
	private void setEnabled(char enabled) {
		// TODO Auto-generated method stub
		this.enabled = (enabled=='T')? true : false; 
	}
	

}
