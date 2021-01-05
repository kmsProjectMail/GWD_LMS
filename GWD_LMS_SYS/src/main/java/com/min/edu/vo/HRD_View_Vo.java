package com.min.edu.vo;

import java.io.Serializable;
import java.util.Date;

public class HRD_View_Vo implements Serializable{
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
	private String fclty_ar_cn;
	private String hold_qy_1;
	private String ocu_acptn_cn;
	private String trafclty_nm;
	private String eqpmn_nm;
	private String hold_qy_2;
	private String trpr_degr;
	private String p_file_name;
	private String file_path;
	private String ino_nm;
	private String addr1;
	private String addr2;
	private String tel_no;
	private String hp_addr;
	private String trainst_cer;
	private String torg_par_grad;
	private String trainst_intro;
	private String trainst_photo;
	private String trainst_video;
	
	public HRD_View_Vo() {
	}

	@Override
	public String toString() {
		return "HRD_Info_Vo [trpr_id=" + trpr_id + ", trpr_nm=" + trpr_nm + ", tra_start_date=" + tra_start_date
				+ ", tra_end_date=" + tra_end_date + ", trtm=" + trtm + ", trainst_cst_id=" + trainst_cst_id
				+ ", address=" + address + ", ncs_nm=" + ncs_nm + ", ncs_cd=" + ncs_cd + ", trpr_overview="
				+ trpr_overview + ", trpr_book=" + trpr_book + ", trpr_teacher=" + trpr_teacher + ", trpr_chap_tel="
				+ trpr_chap_tel + ", trpr_chap=" + trpr_chap + ", trpr_chap_email=" + trpr_chap_email
				+ ", trpr_chap_id=" + trpr_chap_id + ", fclty_ar_cn=" + fclty_ar_cn + ", hold_qy_1=" + hold_qy_1
				+ ", ocu_acptn_cn=" + ocu_acptn_cn + ", trafclty_nm=" + trafclty_nm + ", eqpmn_nm=" + eqpmn_nm
				+ ", hold_qy_2=" + hold_qy_2 + ", trpr_degr=" + trpr_degr + ", p_file_name=" + p_file_name
				+ ", file_path=" + file_path + ", ino_nm=" + ino_nm + ", addr1=" + addr1 + ", addr2=" + addr2
				+ ", tel_no=" + tel_no + ", hp_addr=" + hp_addr + ", trainst_cer=" + trainst_cer + ", torg_par_grad="
				+ torg_par_grad + ", trainst_intro=" + trainst_intro + ", trainst_photo=" + trainst_photo
				+ ", trainst_video=" + trainst_video + "]";
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

	public String getFclty_ar_cn() {
		return fclty_ar_cn;
	}

	public void setFclty_ar_cn(String fclty_ar_cn) {
		this.fclty_ar_cn = fclty_ar_cn;
	}

	public String getHold_qy_1() {
		return hold_qy_1;
	}

	public void setHold_qy_1(String hold_qy_1) {
		this.hold_qy_1 = hold_qy_1;
	}

	public String getOcu_acptn_cn() {
		return ocu_acptn_cn;
	}

	public void setOcu_acptn_cn(String ocu_acptn_cn) {
		this.ocu_acptn_cn = ocu_acptn_cn;
	}

	public String getTrafclty_nm() {
		return trafclty_nm;
	}

	public void setTrafclty_nm(String trafclty_nm) {
		this.trafclty_nm = trafclty_nm;
	}

	public String getEqpmn_nm() {
		return eqpmn_nm;
	}

	public void setEqpmn_nm(String eqpmn_nm) {
		this.eqpmn_nm = eqpmn_nm;
	}

	public String getHold_qy_2() {
		return hold_qy_2;
	}

	public void setHold_qy_2(String hold_qy_2) {
		this.hold_qy_2 = hold_qy_2;
	}

	public String getTrpr_degr() {
		return trpr_degr;
	}

	public void setTrpr_degr(String trpr_degr) {
		this.trpr_degr = trpr_degr;
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

	public String getIno_nm() {
		return ino_nm;
	}

	public void setIno_nm(String ino_nm) {
		this.ino_nm = ino_nm;
	}

	public String getAddr1() {
		return addr1;
	}

	public void setAddr1(String addr1) {
		this.addr1 = addr1;
	}

	public String getAddr2() {
		return addr2;
	}

	public void setAddr2(String addr2) {
		this.addr2 = addr2;
	}

	public String getTel_no() {
		return tel_no;
	}

	public void setTel_no(String tel_no) {
		this.tel_no = tel_no;
	}

	public String getHp_addr() {
		return hp_addr;
	}

	public void setHp_addr(String hp_addr) {
		this.hp_addr = hp_addr;
	}

	public String getTrainst_cer() {
		return trainst_cer;
	}

	public void setTrainst_cer(String trainst_cer) {
		this.trainst_cer = trainst_cer;
	}

	public String getTorg_par_grad() {
		return torg_par_grad;
	}

	public void setTorg_par_grad(String torg_par_grad) {
		this.torg_par_grad = torg_par_grad;
	}

	public String getTrainst_intro() {
		return trainst_intro;
	}

	public void setTrainst_intro(String trainst_intro) {
		this.trainst_intro = trainst_intro;
	}

	public String getTrainst_photo() {
		return trainst_photo;
	}

	public void setTrainst_photo(String trainst_photo) {
		this.trainst_photo = trainst_photo;
	}

	public String getTrainst_video() {
		return trainst_video;
	}

	public void setTrainst_video(String trainst_video) {
		this.trainst_video = trainst_video;
	}

	/**
	 * 기관 DB 입력
	 */
	public HRD_View_Vo(String trainst_cst_id, String p_file_name, String file_path, String ino_nm, String addr1,
			String addr2, String tel_no, String hp_addr, String trainst_cer, String torg_par_grad, String trainst_intro,
			String trainst_photo, String trainst_video) {
		super();
		this.trainst_cst_id = trainst_cst_id;
		this.p_file_name = p_file_name;
		this.file_path = file_path;
		this.ino_nm = ino_nm;
		this.addr1 = addr1;
		this.addr2 = addr2;
		this.tel_no = tel_no;
		this.hp_addr = hp_addr;
		this.trainst_cer = trainst_cer;
		this.torg_par_grad = torg_par_grad;
		this.trainst_intro = trainst_intro;
		this.trainst_photo = trainst_photo;
		this.trainst_video = trainst_video;
	}
	/**
	 * 과정 DB 입력
	 */
	public HRD_View_Vo(String trpr_id, String trpr_nm, Date tra_start_date, Date tra_end_date, String trtm,
			String trainst_cst_id, String address, String ncs_nm, String ncs_cd, String trpr_overview, String trpr_book,
			String trpr_teacher, String trpr_chap_tel, String trpr_chap, String trpr_chap_email, String trpr_chap_id,
			String fclty_ar_cn, String hold_qy_1, String ocu_acptn_cn, String trafclty_nm, String eqpmn_nm,
			String hold_qy_2, String trpr_degr) {
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
		this.trpr_overview = trpr_overview;
		this.trpr_book = trpr_book;
		this.trpr_teacher = trpr_teacher;
		this.trpr_chap_tel = trpr_chap_tel;
		this.trpr_chap = trpr_chap;
		this.trpr_chap_email = trpr_chap_email;
		this.trpr_chap_id = trpr_chap_id;
		this.fclty_ar_cn = fclty_ar_cn;
		this.hold_qy_1 = hold_qy_1;
		this.ocu_acptn_cn = ocu_acptn_cn;
		this.trafclty_nm = trafclty_nm;
		this.eqpmn_nm = eqpmn_nm;
		this.hold_qy_2 = hold_qy_2;
		this.trpr_degr = trpr_degr;
	}

	
	
	

}
