package com.min.edu.vo;

import java.io.Serializable;

public class HRD_Bookmark_VO implements Serializable {
	
	private static final long serialVersionUID = 870160102353043555L;
	
	
	private String user_id;
	private String trpr_id;
	private String trpr_degr;
	private String trainst_cst_id;
	
	
	public HRD_Bookmark_VO() {
	}


	@Override
	public String toString() {
		return "HRD_Bookmark_VO [user_id=" + user_id + ", trpr_id=" + trpr_id + ", trpr_degr=" + trpr_degr
				+ ", trainst_cst_id=" + trainst_cst_id + "]";
	}


	public String getUser_id() {
		return user_id;
	}


	public void setUser_id(String user_id) {
		this.user_id = user_id;
	}


	public String getTrpr_id() {
		return trpr_id;
	}


	public void setTrpr_id(String trpr_id) {
		this.trpr_id = trpr_id;
	}


	public String getTrpr_degr() {
		return trpr_degr;
	}


	public void setTrpr_degr(String trpr_degr) {
		this.trpr_degr = trpr_degr;
	}


	public String getTrainst_cst_id() {
		return trainst_cst_id;
	}


	public void setTrainst_cst_id(String trainst_cst_id) {
		this.trainst_cst_id = trainst_cst_id;
	}


	/**
	 * 과정정보 즐겨찾기 VO
	 * @param user_id
	 * @param trpr_id
	 * @param trpr_degr
	 */
	public HRD_Bookmark_VO(String user_id, String trpr_id, String trpr_degr) {
		this.user_id = user_id;
		this.trpr_id = trpr_id;
		this.trpr_degr = trpr_degr;
	}


	/**
	 * 기관정보 즐겨찾기 VO
	 * @param user_id
	 * @param trainst_cst_id
	 */
	public HRD_Bookmark_VO(String user_id, String trainst_cst_id) {
		this.user_id = user_id;
		this.trainst_cst_id = trainst_cst_id;
	}
	
	
	
	
	

	
	
	

}
