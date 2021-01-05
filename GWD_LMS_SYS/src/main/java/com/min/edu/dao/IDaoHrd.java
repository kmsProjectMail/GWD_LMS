package com.min.edu.dao;

import java.util.Map;

import com.min.edu.vo.HRD_Trainst_Info_Vo;
import com.min.edu.vo.HRD_Trpr_Info_Vo;

public interface IDaoHrd {
	
	/**
	 * DB에 과정이 존재하는지 검색
	 */
	public boolean selectTrpr(Map<String, Object> map);
	
	/**
	 * DB에 기관이 존재하는지 검색
	 */
	public boolean selectTrainst(String trainst_cst_id);
	
	/**
	 * 기관 DB 입력
	 */
	public boolean insertTrainstInfo(HRD_Trainst_Info_Vo vo);
	
	/**
	 * 과정 DB 입력
	 */
	public boolean insertTrprInfo(HRD_Trpr_Info_Vo vo);


}
