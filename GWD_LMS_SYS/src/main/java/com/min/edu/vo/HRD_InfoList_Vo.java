package com.min.edu.vo;

//장비,시설정보 vo
public class HRD_InfoList_Vo {
	
	private String cstmr_nm; // 등록훈련기관
	private String fclty_ar_cn; // 시설 면적(㎡)
	private String hold_qy; // 시설 수 (보유 수량)
	private String ocu_acptn_cn; // 인원 수(명)
	private String trafclty_nm; // 시설명
	private String eqpmn_nm; // 장비명
	
	public String getCstmr_nm() {
		return cstmr_nm;
	}
	public void setCstmr_nm(String cstmr_nm) {
		this.cstmr_nm = cstmr_nm;
	}
	public String getFclty_ar_cn() {
		return fclty_ar_cn;
	}
	public void setFclty_ar_cn(String fclty_ar_cn) {
		this.fclty_ar_cn = fclty_ar_cn;
	}
	public String getHold_qy() {
		return hold_qy;
	}
	public void setHold_qy(String hold_qy) {
		this.hold_qy = hold_qy;
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
	@Override
	public String toString() {
		return "HRD_InfoList_Vo [cstmr_nm=" + cstmr_nm + ", fclty_ar_cn=" + fclty_ar_cn + ", hold_qy=" + hold_qy
				+ ", ocu_acptn_cn=" + ocu_acptn_cn + ", trafclty_nm=" + trafclty_nm + ", eqpmn_nm=" + eqpmn_nm + "]";
	}
	public HRD_InfoList_Vo() {
	}
	
	/**
	 * 시설정보리스트
	 */
	public HRD_InfoList_Vo(String fclty_ar_cn, String hold_qy, String ocu_acptn_cn, String trafclty_nm) {
		super();
		this.fclty_ar_cn = fclty_ar_cn;
		this.hold_qy = hold_qy;
		this.ocu_acptn_cn = ocu_acptn_cn;
		this.trafclty_nm = trafclty_nm;
	}
	
	/**
	 * 장비정보리스트
	 */
	public HRD_InfoList_Vo(String eqpmn_nm, String hold_qy) {
		super();
		this.eqpmn_nm = eqpmn_nm;
		this.hold_qy = hold_qy;
	}
	
	
	
}
