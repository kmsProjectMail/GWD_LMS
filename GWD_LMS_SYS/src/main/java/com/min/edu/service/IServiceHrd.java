package com.min.edu.service;

import java.io.IOException;
import java.text.ParseException;
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
	public boolean insertTrainstInfo(List<HRD_Trainst_Info_Vo> vo);
	
	/**
	 * 과정 DB 입력
	 */
	public boolean insertTrprInfo(List<HRD_Trpr_Info_Vo> vo);
	
	/**
	 * 기관/과정 목록조회
	 */
	public List<HRD_View_Vo> hrdListView(Map<String, Object> map);
	
	/**
	 * 과정 상세조회
	 */
	public HRD_View_Vo hrdDetailTrpr(Map<String, Object> map);
	
	
	/**
	 * 목록 검색 결과의 총 갯수 반환
	 */
	public String hrdListViewPaging(Map<String, Object> map);
	
	
	/**
	 * 기관정보 주소조회
	 */
	public List<HRD_Trainst_Info_Vo> alltrainstinfo(Map<String, String> map);

	/**
	 * 기관DB/과정DB입력 트랜잭션
	 * @param Map srchTraArea1 지역코드, pageNum 페이지번호, pageSize 리스트갯수
	 * @throws IOException
	 * @throws ParseException
	 */
	public boolean saveDB(Map<String, Object> map) throws IOException, ParseException;
	
	/**
	 * 과정,시설정보 DB입력 트랜잭션
	 * @param map trpr_id, trpr_degr
	 * @throws ParseException 
	 * @throws IOException 
	 */
	public boolean saveDBList(Map<String, Object> map) throws IOException, ParseException;
	
	/**
	 * 스케줄러에서 DB입력 실행 (서울, 경기)
	 */
	public void insertJeesoo();
}
