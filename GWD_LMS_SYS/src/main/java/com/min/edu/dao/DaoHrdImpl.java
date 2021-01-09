package com.min.edu.dao;

import java.io.IOException;
import java.text.ParseException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.mybatis.spring.SqlSessionTemplate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.min.edu.commons.utils.HRDUtils;
import com.min.edu.vo.HRD_Trainst_Info_Vo;
import com.min.edu.vo.HRD_Trpr_Info_Vo;
import com.min.edu.vo.HRD_View_Vo;

@Repository
public class DaoHrdImpl implements IDaoHrd{
	
	private Logger log = LoggerFactory.getLogger(this.getClass());
	private final String NS = "com.min.edu.dao.IDaoHrd.";
	
	@Autowired
	private SqlSessionTemplate sqlSession;
	
	@Autowired
	private HRDUtils utils;
	
	
	/**
	 * true: 기관정보 중복없음
	 * false: 기관정보 중복있음
	 */
	@Override
	public boolean selectTrainst(String trainst_cst_id){
		log.info("welcome DaoHrdImpl 😍 기관 중복 검색 selectTrainst {}", trainst_cst_id);
		List<HRD_Trainst_Info_Vo> vo = sqlSession.selectList(NS+"selectTrainst", trainst_cst_id);
		return vo.isEmpty();
	}
	
	/**
	 * true: 과정정보 중복없음
	 * false: 과정정보 중복있음
	 */
	@Override
	public boolean selectTrpr(Map<String, Object> map){
		log.info("welcome DaoHrdImpl 😍과정 중복 검색 selectTrpr {}", map);
		List<HRD_Trainst_Info_Vo> vo = sqlSession.selectList(NS+"selectTrpr", map);
		return vo.isEmpty();
	}

	@Override
	public boolean insertTrainstInfo(List<HRD_Trainst_Info_Vo> trainstLists) {
		log.info("welcome DaoHrdImpl 😍 기관정보 입력 insertTrainstInfo {}", trainstLists);
		int cnt = 0;
		for(HRD_Trainst_Info_Vo v: trainstLists) {
			if(selectTrainst(v.getTrainst_cst_id())){
				cnt += sqlSession.insert(NS+"insertTrainstInfo", v);
			}
		}
		System.out.println("입력된 기관의 수: "+cnt);
		return cnt>0?true:false;
	}

	@Override
	public boolean insertTrprInfo(List<HRD_Trpr_Info_Vo> trprLists) {
		log.info("welcome DaoHrdImpl 😍 과정정보 입력 insertTrprInfo {}", trprLists);
		int cnt = 0;
		Map<String, Object> map = new HashMap<String, Object>();
		for(HRD_Trpr_Info_Vo v: trprLists) {
			map.put("trpr_id", v.getTrpr_id());
			map.put("trpr_degr", v.getTrpr_degr());
			if(selectTrpr(map)) {
				cnt += sqlSession.insert(NS+"insertTrprInfo", v);
			}
		}
		System.out.println("입력된 과정의 수: "+cnt);
		return cnt>0?true:false;
	}
	
	@Override
	@Transactional
	public boolean saveDB(String srchTraArea1) throws IOException, ParseException {
		log.info("welcome DaoHrdImpl 😍 기관,과정정보 DB에 저장하기 saveDB 저장할 지역: {}", srchTraArea1);
		List<HRD_Trainst_Info_Vo> trainstLists = utils.trainstInfo(srchTraArea1);
		List<HRD_Trpr_Info_Vo> trprLists = utils.trprInfo(srchTraArea1);
		boolean isc1 = insertTrainstInfo(trainstLists);
		boolean isc2 = insertTrprInfo(trprLists);
		return (isc1||isc2)?true:false;
	}

	
	//hrd 목록검색
	@Override
	public List<HRD_View_Vo> hrdListView(Map<String, Object> map) {
		log.info("welcome DaoHrdImpl 😍  search DB 목록검색 hrdListView {}", map);
		List<HRD_View_Vo> lists = sqlSession.selectList(NS+"hrdListView", map);
		return lists;
	}
	
	

	@Override
	public List<HRD_Trainst_Info_Vo> alltrainstinfo(Map<String, String> map) {
		log.info("welcome DaoHrdImpl 😍  주소조회용 alltrainstinfo {}", map);
		List<HRD_Trainst_Info_Vo> lists = sqlSession.selectList(NS+"alltrainstinfo", map);
		return lists;
	}

}
