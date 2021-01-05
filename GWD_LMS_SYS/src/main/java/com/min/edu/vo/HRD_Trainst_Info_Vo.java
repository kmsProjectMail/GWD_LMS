package com.min.edu.vo;

import java.io.Serializable;
import java.util.Date;

public class HRD_Trainst_Info_Vo implements Serializable {
	private static final long serialVersionUID = 3646539766263511941L;

	private String trainst_cst_id;
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

	public HRD_Trainst_Info_Vo() {
	}

	public String getTrainst_cst_id() {
		return trainst_cst_id;
	}

	public void setTrainst_cst_id(String trainst_cst_id) {
		this.trainst_cst_id = trainst_cst_id;
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

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	@Override
	public String toString() {
		return "HRD_Trainst_Info_Vo [trainst_cst_id=" + trainst_cst_id + ", p_file_name=" + p_file_name + ", file_path="
				+ file_path + ", ino_nm=" + ino_nm + ", addr1=" + addr1 + ", addr2=" + addr2 + ", tel_no=" + tel_no
				+ ", hp_addr=" + hp_addr + ", trainst_cer=" + trainst_cer + ", torg_par_grad=" + torg_par_grad
				+ ", trainst_intro=" + trainst_intro + ", trainst_photo=" + trainst_photo + ", trainst_video="
				+ trainst_video + "]";
	}

	/**
	 * 기관 DB 입력
	 */
	public HRD_Trainst_Info_Vo(String trainst_cst_id, String p_file_name, String file_path, String ino_nm, String addr1,
			String addr2, String tel_no, String hp_addr, String torg_par_grad) {
		super();
		this.trainst_cst_id = trainst_cst_id;
		this.p_file_name = p_file_name;
		this.file_path = file_path;
		this.ino_nm = ino_nm;
		this.addr1 = addr1;
		this.addr2 = addr2;
		this.tel_no = tel_no;
		this.hp_addr = hp_addr;
		this.torg_par_grad = torg_par_grad;
	}

	/**
	 * 교육기관 기능 기관정보 입력수정
	 */
	public HRD_Trainst_Info_Vo(String trainst_cst_id, String trainst_intro, String trainst_photo,
			String trainst_video) {
		super();
		this.trainst_cst_id = trainst_cst_id;
		this.trainst_intro = trainst_intro;
		this.trainst_photo = trainst_photo;
		this.trainst_video = trainst_video;
	}

}
