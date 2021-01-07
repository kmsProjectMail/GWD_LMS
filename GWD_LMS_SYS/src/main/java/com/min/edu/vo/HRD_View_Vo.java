package com.min.edu.vo;

import java.io.Serializable;
import java.util.Date;

public class HRD_View_Vo implements Serializable{
	private static final long serialVersionUID = -4525257253013633385L;
	
	private String trpr_nm;
	private Date tra_start_date;
	private Date tra_end_date;
	private String trtm;
	private String ino_nm;
	private String trpr_degr;

//	private String trpr_id;
//	private String trainst_cst_id;
//	private String address;
//	private String ncs_nm;
//	private String ncs_cd;
//	private String trpr_overview;
//	private String trpr_book;
//	private String trpr_teacher;
//	private String trpr_chap_tel;
//	private String trpr_chap;
//	private String trpr_chap_email;
//	private String trpr_chap_id;
//	private String fclty_ar_cn;
//	private String hold_qy_1;
//	private String ocu_acptn_cn;
//	private String trafclty_nm;
//	private String eqpmn_nm;
//	private String hold_qy_2;
//	private String p_file_name;
//	private String file_path;
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
				+ tra_end_date + ", trtm=" + trtm + ", ino_nm=" + ino_nm + ", trpr_degr=" + trpr_degr + "]";
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

	
	
	
	

}
