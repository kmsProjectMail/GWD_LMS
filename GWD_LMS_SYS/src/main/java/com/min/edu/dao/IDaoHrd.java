package com.min.edu.dao;

import java.io.IOException;
import java.text.ParseException;
import java.util.List;
import java.util.Map;

import com.min.edu.vo.HRD_Trainst_Info_Vo;
import com.min.edu.vo.HRD_Trpr_Info_Vo;
import com.min.edu.vo.HRD_View_Vo;

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
	 * 리스트로 입력 다이나믹쿼리
	 */
//	public boolean insertTrainstInfo1(List<String> vo);
	
	/**
	 * 기관 DB 입력
	 */
	public boolean insertTrainstInfo(List<HRD_Trainst_Info_Vo> vo);
	
	/**
	 * 과정 DB 입력
	 */
	public boolean insertTrprInfo(List<HRD_Trpr_Info_Vo> vo);
	
	
	/**
	 * 기관/과정 목록조회
	 */
	public List<HRD_View_Vo> hrdListView(Map<String, Object> map);

	public List<HRD_Trainst_Info_Vo> alltrainstinfo(String addr1);
	
	/**
	 * 기관DB/과정DB입력 트랜잭션
	 * @throws ParseException 
	 * @throws IOException 
	 */
	public boolean saveDB(String srchTraArea1) throws IOException, ParseException;
	
	


}
