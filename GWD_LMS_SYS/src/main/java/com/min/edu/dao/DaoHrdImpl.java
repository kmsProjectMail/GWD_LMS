package com.min.edu.dao;

import java.io.IOException;
import java.text.ParseException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.jsoup.select.Elements;
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
	public boolean saveDB(Map<String, Object> map) throws IOException, ParseException {
		log.info("welcome DaoHrdImpl 😍 기관,과정정보 DB에 저장하기 saveDB: {}", map);
		List<HRD_Trainst_Info_Vo> trainstLists = utils.trainstInfo(map);
		List<HRD_Trpr_Info_Vo> trprLists = utils.trprInfo(map);
		//기관, 과정의 반환 정보가 있을 경우 true 반환, 반환정보 없을경우 false 반환
		boolean isc = (!trainstLists.isEmpty() || !trprLists.isEmpty());	//둘 중 하나라도 반환정보가 있으면 true
		System.out.println("반환 정보 있음: "+isc);
		if(isc) {
			insertTrprInfo(trprLists);
			insertTrainstInfo(trainstLists);
		}
		return isc;
	}

	
	//hrd 목록검색
	@Override
	public List<HRD_View_Vo> hrdListView(Map<String, Object> map) {
		log.info("welcome DaoHrdImpl 😍  search DB 목록검색 hrdListView {}", map);
		List<HRD_View_Vo> lists = sqlSession.selectList(NS+"hrdListView", map);
		return lists;
	}
	
	@Override
	public HRD_View_Vo hrdDetailTrpr(Map<String, Object> map) {
		log.info("welcome DaoHrdImpl 😍   과정세부검색 hrdDetailTrpr {}", map);
		HRD_View_Vo vo = sqlSession.selectOne(NS+"hrdDetailTrpr", map);
		System.out.println("검색결과: "+vo);
		return vo;
	}

	@Override
	public List<HRD_Trainst_Info_Vo> alltrainstinfo(Map<String, String> map) {
		log.info("welcome DaoHrdImpl 😍  주소조회용 alltrainstinfo {}", map);
		List<HRD_Trainst_Info_Vo> lists = sqlSession.selectList(NS+"alltrainstinfo", map);
		return lists;
	}

	@Override
	public boolean saveDBList(Map<String, Object> map) throws IOException, ParseException {
		log.info("welcome DaoHrdImpl 😍  장비, 시설정보 저장 saveDBList {}", map);
		String trpr_id = (String)map.get("trpr_id");
		String trpr_degr = (String)map.get("trpr_degr");
		
		//시설정보 리스트
		Elements detailFacil = utils.getdetailurlFacil(trpr_id, trpr_degr);
		String facil_info_list = utils.facilInfoList(detailFacil);
		
		// 장비정보 리스트
		Elements detailEqnm = utils.getdetailurlEqnm(trpr_id, trpr_degr);
		String eqmn_info_list = utils.eqnmInfoList(detailEqnm);
		
		int cnt = 0;
		if(facil_info_list != null || eqmn_info_list != null) {
			System.out.println("시설,장비정보 입력");
			HRD_Trpr_Info_Vo vo = new HRD_Trpr_Info_Vo(trpr_id, trpr_degr, facil_info_list, eqmn_info_list);
//			System.out.println("vo?????????"+vo);
			cnt = sqlSession.update(NS+"updateInfoList", vo);
		}
		return cnt>0?true:false;
	}

	@Override
	public String hrdListViewPaging(Map<String, Object> map) {
		log.info("welcome DaoHrdImpl 😍  목록검색결과의 총 갯수 반환 hrdListViewPaging {}", map);
		return sqlSession.selectOne(NS+"hrdListViewPaging", map);
	}


}
