package com.min.edu.vo;

import java.io.Serializable;
import java.util.Date;

public class HRD_Trpr_Info_Vo implements Serializable {
	private static final long serialVersionUID = 3646539766263511941L;

	private String trpr_id;
	private String trpr_nm;
	private Date tra_start_date;
	private Date tra_end_date;
	private String trtm;
	private String trainst_cst_id;
	private String address;
	private String ncs_nm;
	private String ncs_cd;
	private String trpr_overview;
	private String trpr_book;
	private String trpr_teacher;
	private String trpr_chap_tel;
	private String trpr_chap;
	private String trpr_chap_email;
	private String trpr_chap_id;
	private String trpr_degr;
	private String facil_info_list;
	private String eqmn_info_list;

	public HRD_Trpr_Info_Vo() {
	}

	@Override
	public String toString() {
		return "HRD_Trpr_Info_Vo [trpr_id=" + trpr_id + ", trpr_nm=" + trpr_nm + ", tra_start_date=" + tra_start_date
				+ ", tra_end_date=" + tra_end_date + ", trtm=" + trtm + ", trainst_cst_id=" + trainst_cst_id
				+ ", address=" + address + ", ncs_nm=" + ncs_nm + ", ncs_cd=" + ncs_cd + ", trpr_overview="
				+ trpr_overview + ", trpr_book=" + trpr_book + ", trpr_teacher=" + trpr_teacher + ", trpr_chap_tel="
				+ trpr_chap_tel + ", trpr_chap=" + trpr_chap + ", trpr_chap_email=" + trpr_chap_email
				+ ", trpr_chap_id=" + trpr_chap_id + ", trpr_degr=" + trpr_degr + ", facil_info_list=" + facil_info_list
				+ ", eqmn_info_list=" + eqmn_info_list + "]";
	}

	public String getTrpr_id() {
		return trpr_id;
	}

	public void setTrpr_id(String trpr_id) {
		this.trpr_id = trpr_id;
	}

	public String getTrpr_nm() {
		return trpr_nm;
	}

	public void setTrpr_nm(String trpr_nm) {
		this.trpr_nm = trpr_nm;
	}

	public Date getTra_start_date() {
		return tra_start_date;
	}

	public void setTra_start_date(Date tra_start_date) {
		this.tra_start_date = tra_start_date;
	}

	public Date getTra_end_date() {
		return tra_end_date;
	}

	public void setTra_end_date(Date tra_end_date) {
		this.tra_end_date = tra_end_date;
	}

	public String getTrtm() {
		return trtm;
	}

	public void setTrtm(String trtm) {
		this.trtm = trtm;
	}

	public String getTrainst_cst_id() {
		return trainst_cst_id;
	}

	public void setTrainst_cst_id(String trainst_cst_id) {
		this.trainst_cst_id = trainst_cst_id;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getNcs_nm() {
		return ncs_nm;
	}

	public void setNcs_nm(String ncs_nm) {
		this.ncs_nm = ncs_nm;
	}

	public String getNcs_cd() {
		return ncs_cd;
	}

	public void setNcs_cd(String ncs_cd) {
		this.ncs_cd = ncs_cd;
	}

	public String getTrpr_overview() {
		return trpr_overview;
	}

	public void setTrpr_overview(String trpr_overview) {
		this.trpr_overview = trpr_overview;
	}

	public String getTrpr_book() {
		return trpr_book;
	}

	public void setTrpr_book(String trpr_book) {
		this.trpr_book = trpr_book;
	}

	public String getTrpr_teacher() {
		return trpr_teacher;
	}

	public void setTrpr_teacher(String trpr_teacher) {
		this.trpr_teacher = trpr_teacher;
	}

	public String getTrpr_chap_tel() {
		return trpr_chap_tel;
	}

	public void setTrpr_chap_tel(String trpr_chap_tel) {
		this.trpr_chap_tel = trpr_chap_tel;
	}

	public String getTrpr_chap() {
		return trpr_chap;
	}

	public void setTrpr_chap(String trpr_chap) {
		this.trpr_chap = trpr_chap;
	}

	public String getTrpr_chap_email() {
		return trpr_chap_email;
	}

	public void setTrpr_chap_email(String trpr_chap_email) {
		this.trpr_chap_email = trpr_chap_email;
	}

	public String getTrpr_chap_id() {
		return trpr_chap_id;
	}

	public void setTrpr_chap_id(String trpr_chap_id) {
		this.trpr_chap_id = trpr_chap_id;
	}

	public String getTrpr_degr() {
		return trpr_degr;
	}

	public void setTrpr_degr(String trpr_degr) {
		this.trpr_degr = trpr_degr;
	}

	public String getFacil_info_list() {
		return facil_info_list;
	}

	public void setFacil_info_list(String facil_info_list) {
		this.facil_info_list = facil_info_list;
	}

	public String getEqmn_info_list() {
		return eqmn_info_list;
	}

	public void setEqmn_info_list(String eqmn_info_list) {
		this.eqmn_info_list = eqmn_info_list;
	}

	/**
	 * 과정 DB 입력
	 */
	public HRD_Trpr_Info_Vo(String trpr_id, String trpr_nm, Date tra_start_date, Date tra_end_date, String trtm,
			String trainst_cst_id, String address, String ncs_nm, String ncs_cd, String trpr_chap_tel, String trpr_chap,
			String trpr_chap_email, String trpr_degr, String facil_info_list, String eqmn_info_list) {
		super();
		this.trpr_id = trpr_id;
		this.trpr_nm = trpr_nm;
		this.tra_start_date = tra_start_date;
		this.tra_end_date = tra_end_date;
		this.trtm = trtm;
		this.trainst_cst_id = trainst_cst_id;
		this.address = address;
		this.ncs_nm = ncs_nm;
		this.ncs_cd = ncs_cd;
		this.trpr_chap_tel = trpr_chap_tel;
		this.trpr_chap = trpr_chap;
		this.trpr_chap_email = trpr_chap_email;
		this.trpr_degr = trpr_degr;
		this.facil_info_list = facil_info_list;
		this.eqmn_info_list = eqmn_info_list;
	}

	/**
	 * 교육기관 기능 과정정보 입력수정
	 */
	public HRD_Trpr_Info_Vo(String trpr_id, String trpr_overview, String trpr_book, String trpr_teacher) {
		this.trpr_id = trpr_id;
		this.trpr_overview = trpr_overview;
		this.trpr_book = trpr_book;
		this.trpr_teacher = trpr_teacher;
	}

}
