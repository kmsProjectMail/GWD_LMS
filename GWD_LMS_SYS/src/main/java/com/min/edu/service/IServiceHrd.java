package com.min.edu.service;

import java.util.List;
import java.util.Map;

import com.min.edu.vo.HRD_Trainst_Info_Vo;
import com.min.edu.vo.HRD_Trpr_Info_Vo;
import com.min.edu.vo.HRD_View_Vo;

public interface IServiceHrd {
	
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
	
	/**
	 * 기관/과정 목록조회
	 */
	public List<HRD_View_Vo> hrdListView(Map<String, Object> map);

}
