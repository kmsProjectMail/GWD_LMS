package com.min.edu.dao;

import java.io.IOException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Arrays;
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

	
	@Override
	public String selectTrainst_for_SignUp(String trainst_cst_id){
		log.info("welcome DaoHrdImpl 😍 기관 중복 검색 selectTrainst_for_SignUp {}", trainst_cst_id);
		String ino_nm= sqlSession.selectOne(NS+"selectTrainst_for_SignUp", trainst_cst_id);
		System.out.println(ino_nm);
		return ino_nm;
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

	
	//hrd 키워드검색
	@Override
	public List<HRD_View_Vo> hrdListView(Map<String, Object> map) {
		log.info("welcome DaoHrdImpl 😍  search DB 키워드 목록검색 hrdListView {}", map);
		List<HRD_View_Vo> lists = sqlSession.selectList(NS+"hrdListView", map);
		return lists;
	}
	
	//hrd 기관검색
	@Override
	public List<HRD_View_Vo> hrdListViewTrainst(Map<String, Object> map) {
		log.info("welcome DaoHrdImpl 😍  search DB 키워드 기관검색 hrdListViewTrainst {}", map);
		List<HRD_View_Vo> lists = sqlSession.selectList(NS+"hrdListViewTrainst", map);
		return lists;
	}
	
	//hrd 과정검색
	@Override
	public List<HRD_View_Vo> hrdListViewTrpr(Map<String, Object> map) {
		log.info("welcome DaoHrdImpl 😍  search DB 키워드 과정검색 hrdListViewTrpr {}", map);
		List<HRD_View_Vo> lists = sqlSession.selectList(NS+"hrdListViewTrpr", map);
		return lists;
	}
	
	@Override
	public HRD_View_Vo hrdDetailTrpr(Map<String, Object> map) {
		log.info("welcome DaoHrdImpl 😍   과정세부검색 hrdDetailTrpr {}", map);
		HRD_View_Vo vo = sqlSession.selectOne(NS+"hrdDetailTrpr", map);
		System.out.println("검색결과: "+vo);
		return vo;
	}
	
	public HRD_Trainst_Info_Vo hrdDetailTrainst(Map<String, Object> map) {
		log.info("welcome DaoHrdImpl 😍   기관세부검색 hrdDetailTrainst {}", map);
		return sqlSession.selectOne(NS+"hrdDetailTrainst", map);
	};

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

	
	//스케줄러 DB입력
	public void insertJeesoo() {
		log.info("welcome DaoHrdImpl 😍  DB입력 시작 insertJeesoo");
		List<String> list = new ArrayList<String>(Arrays.asList("11","41"));
		for(String area: list) {

			Map<String, Object> map = new HashMap<String, Object>();
			map.put("srchTraArea1", area); //11: 서울, 41: 경기
			map.put("pageSize", "30");
			map.put("pageNum", "1");
	
			int n = 1;
			boolean isc = true;
			
				while(isc) {
					try {
						isc = saveDB(map);
					} catch (Exception e) {
						System.err.println("입력 오류");
					}
					n++;
					map.put("pageNum", String.valueOf(n));
					System.out.println("입력 성공: "+ isc);
				}
			
		}
	}


	@Override
	public boolean trainstAddTrpr(HRD_Trpr_Info_Vo vo) {
		log.info("welcome DaoHrdImpl 😍  과정정보 입력, 수정  trainstAddTrpr {}", vo);
		int n = sqlSession.update(NS+"trainstAddTrpr", vo);
		return n>0?true:false;
	}


	@Override
	public boolean trainstAddTrainst(HRD_Trainst_Info_Vo vo) {
		log.info("welcome DaoHrdImpl 😍  기관정보 입력, 수정  trainstAddTrainst {}", vo);
		int n = sqlSession.update(NS+"trainstAddTrainst", vo);
		return n>0?true:false;
	}


	@Override
	public String trprBmkList(String user_id) {
		log.info("welcome DaoHrdImpl 😍  해당 유저의 과정즐겨찾기 존재 여부 및 정보조회  trprBmkList {}", user_id);
		String trpr_id = sqlSession.selectOne(NS+"trprBmkList", user_id);
//		System.out.println("과정즐겨찾기 검색 결과값은?"+trpr_id);
		return trpr_id;
	}


	@Override
	public String trainstBmkList(String user_id) {
		log.info("welcome DaoHrdImpl 😍  해당 유저의 기관즐겨찾기 존재 여부 및 정보조회  trainstBmkList {}", user_id);
		String trainst_id = sqlSession.selectOne(NS+"trainstBmkList", user_id);
		System.out.println("기관즐겨찾기 검색 결과값은?"+trainst_id);
		return trainst_id;
	}


	@Override
	public boolean trprBmkInsert(Map<String, Object> map) {
		log.info("welcome DaoHrdImpl 😍  즐겨찾기 리스트 최초 1회 추가  trprBmkInsert {}", map);
		int n = sqlSession.insert(NS+"trprBmkInsert", map);
		return n>0?true:false;
	}


	@Override
	public boolean trprBmkUpdate(Map<String, Object> map) {
		log.info("welcome DaoHrdImpl 😍  즐겨찾기 리스트 업데이트  trprBmkUpdate {}", map);
		int n = sqlSession.insert(NS+"trprBmkUpdate", map);
		return n>0?true:false;
	}


	@Override
	public HRD_View_Vo hrdBmkList(Map<String, Object> map) {
		log.info("welcome DaoHrdImpl 😍  북마크 목록 조회  hrdBmkList {}", map);
		HRD_View_Vo vo = sqlSession.selectOne(NS+"hrdBmkList", map);
		return vo;
	}
	

}
