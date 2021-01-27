package com.min.edu.vo;

import java.io.Serializable;
import java.util.Date;

public class HRD_View_Vo implements Serializable {
	private static final long serialVersionUID = -4525257253013633385L;

	private String trpr_nm;
	private Date tra_start_date;
	private Date tra_end_date;
	private String trtm;
	private String ino_nm;
	private String trpr_degr;
	private String trpr_id;
	private String trainst_cst_id;
	private String ncs_nm;
	private String trpr_overview;
	private String trpr_book;
	private String trpr_teacher;
	private String trpr_chap_tel;
	private String trpr_chap;
	private String trpr_chap_email;
	private String trpr_chap_id;
	private String p_file_name;
	private String file_path;
	private String facil_info_list;
	private String eqmn_info_list;
	private String ncs_cd;

//	private String address;
//	private String fclty_ar_cn;
//	private String hold_qy_1;
//	private String ocu_acptn_cn;
//	private String trafclty_nm;
//	private String eqpmn_nm;
//	private String hold_qy_2;
//	private String addr1;
//	private String addr2;
//	private String tel_no;
//	private String hp_addr;
//	private String trainst_cer;
//	private String torg_par_grad;
//	private String trainst_intro;
//	private String trainst_photo;
//	private String trainst_video;

	public HRD_View_Vo() {
	}


	@Override
	public String toString() {
		return "HRD_View_Vo [trpr_nm=" + trpr_nm + ", tra_start_date=" + tra_start_date + ", tra_end_date="
				+ tra_end_date + ", trtm=" + trtm + ", ino_nm=" + ino_nm + ", trpr_degr=" + trpr_degr + ", trpr_id="
				+ trpr_id + ", trainst_cst_id=" + trainst_cst_id + ", ncs_nm=" + ncs_nm + ", trpr_overview="
				+ trpr_overview + ", trpr_book=" + trpr_book + ", trpr_teacher=" + trpr_teacher + ", trpr_chap_tel="
				+ trpr_chap_tel + ", trpr_chap=" + trpr_chap + ", trpr_chap_email=" + trpr_chap_email
				+ ", trpr_chap_id=" + trpr_chap_id + ", p_file_name=" + p_file_name + ", file_path=" + file_path
				+ ", facil_info_list=" + facil_info_list + ", eqmn_info_list=" + eqmn_info_list + ", ncs_cd=" + ncs_cd
				+ "]";
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


	public String getIno_nm() {
		return ino_nm;
	}


	public void setIno_nm(String ino_nm) {
		this.ino_nm = ino_nm;
	}


	public String getTrpr_degr() {
		return trpr_degr;
	}


	public void setTrpr_degr(String trpr_degr) {
		this.trpr_degr = trpr_degr;
	}


	public String getTrpr_id() {
		return trpr_id;
	}


	public void setTrpr_id(String trpr_id) {
		this.trpr_id = trpr_id;
	}


	public String getTrainst_cst_id() {
		return trainst_cst_id;
	}


	public void setTrainst_cst_id(String trainst_cst_id) {
		this.trainst_cst_id = trainst_cst_id;
	}


	public String getNcs_nm() {
		return ncs_nm;
	}


	public void setNcs_nm(String ncs_nm) {
		this.ncs_nm = ncs_nm;
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


	public String getP_file_name() {
		return p_file_name;
	}


	public void setP_file_name(String p_file_name) {
		this.p_file_name = p_file_name;
	}


	public String getFile_path() {
		return file_path;
	}


	public void setFile_path(String file_path) {
		this.file_path = file_path;
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


	public String getNcs_cd() {
		return ncs_cd;
	}


	public void setNcs_cd(String ncs_cd) {
		this.ncs_cd = ncs_cd;
	}


	/**
	 * 목록조회
	 */
	public HRD_View_Vo(String trpr_nm, Date tra_start_date, Date tra_end_date, String trtm, String ino_nm,
			String trpr_degr) {
		super();
		this.trpr_nm = trpr_nm;
		this.tra_start_date = tra_start_date;
		this.tra_end_date = tra_end_date;
		this.trtm = trtm;
		this.ino_nm = ino_nm;
		this.trpr_degr = trpr_degr;
	}
	
	/**
	 * 과정세부정보 조회
	 */
	public HRD_View_Vo(String trpr_nm, Date tra_start_date, Date tra_end_date, String trtm, String ino_nm,
			String trpr_degr, String ncs_nm, String ncs_cd, String trpr_overview, String trpr_book, String trpr_teacher,
			String trpr_chap_tel, String trpr_chap, String trpr_chap_email, String trpr_chap_id, String p_file_name,
			String file_path, String facil_info_list, String eqmn_info_list) {
		super();
		this.trpr_nm = trpr_nm;
		this.tra_start_date = tra_start_date;
		this.tra_end_date = tra_end_date;
		this.trtm = trtm;
		this.ino_nm = ino_nm;
		this.trpr_degr = trpr_degr;
		this.ncs_nm = ncs_nm;
		this.ncs_cd = ncs_cd;
		this.trpr_overview = trpr_overview;
		this.trpr_book = trpr_book;
		this.trpr_teacher = trpr_teacher;
		this.trpr_chap_tel = trpr_chap_tel;
		this.trpr_chap = trpr_chap;
		this.trpr_chap_email = trpr_chap_email;
		this.trpr_chap_id = trpr_chap_id;
		this.p_file_name = p_file_name;
		this.file_path = file_path;
		this.facil_info_list = facil_info_list;
		this.eqmn_info_list = eqmn_info_list;
	}
	
	
	
	

}
