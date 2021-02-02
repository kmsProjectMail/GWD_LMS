package com.min.edu.service;

import java.io.IOException;
import java.text.ParseException;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.min.edu.dao.IDaoHrd;
import com.min.edu.vo.HRD_Trainst_Info_Vo;
import com.min.edu.vo.HRD_Trpr_Info_Vo;
import com.min.edu.vo.HRD_View_Vo;

@Service
public class ServiceHrdImpl implements IServiceHrd{

	private Logger log = LoggerFactory.getLogger(this.getClass());
	
	@Autowired
	private IDaoHrd iDao;
	
	
	@Override
	public boolean selectTrainst(String trainst_cst_id){
		log.info("welcome ServiceHrdImpl ✔ 기관 중복 검색 selectTrainst {}", trainst_cst_id);
		return iDao.selectTrainst(trainst_cst_id);
	}
	@Override
	public String selectTrainst_for_SignUp(String trainst_cst_id){
		log.info("welcome ServiceHrdImpl ✔ 기관 중복 검색 selectTrainst_for_SignUp {}", trainst_cst_id);
		return iDao.selectTrainst_for_SignUp(trainst_cst_id);
	}
	
	@Override
	public boolean selectTrpr(Map<String, Object> map){
		log.info("welcome ServiceHrdImpl ✔과정 중복 검색 selectTrpr {}", map);
		return iDao.selectTrpr(map);
	}
	
	@Override
	public boolean insertTrainstInfo(List<HRD_Trainst_Info_Vo> vo) {
		log.info("welcome ServiceHrdImpl ✔ 기관정보 입력 insertTrainstInfo {}", vo);
		return iDao.insertTrainstInfo(vo);
	}

	@Override
	public boolean insertTrprInfo(List<HRD_Trpr_Info_Vo> vo) {
		log.info("welcome ServiceHrdImpl ✔ 과정정보 입력 insertTrprInfo {}", vo);
		return iDao.insertTrprInfo(vo);
	}

	@Override
	public List<HRD_View_Vo> hrdListView(Map<String, Object> map) {
		log.info("welcome ServiceHrdImpl ✔ search DB 키워드검색 hrdListView {}", map);
		return iDao.hrdListView(map);
	}
	
	@Override
	public List<HRD_View_Vo> hrdListViewTrainst(Map<String, Object> map) {
		log.info("welcome ServiceHrdImpl ✔ search DB 기관검색 hrdListViewTrainst {}", map);
		return iDao.hrdListViewTrainst(map);
	}
	
	@Override
	public List<HRD_View_Vo> hrdListViewTrpr(Map<String, Object> map) {
		log.info("welcome ServiceHrdImpl ✔ search DB 과정검색 hrdListViewTrpr {}", map);
		return iDao.hrdListViewTrpr(map);
	}

	@Override
	public HRD_View_Vo hrdDetailTrpr(Map<String, Object> map) {
		log.info("welcome ServiceHrdImpl ✔  과정세부조회 hrdDetailTrpr {}", map);
		return iDao.hrdDetailTrpr(map);
	}
	
	public HRD_Trainst_Info_Vo hrdDetailTrainst(Map<String, Object> map) {
		log.info("welcome ServiceHrdImpl ✔  기관세부조회 hrdDetailTrainst {}", map);
		return iDao.hrdDetailTrainst(map);
	};
	
	@Override
	public List<HRD_Trainst_Info_Vo> alltrainstinfo(Map<String, String> map) {
		log.info("welcome ServiceHrdImpl ✔ alltrainstinfo 실행 alltrainstinfo {}", map);
		return iDao.alltrainstinfo(map);
	}
    
	@Override
	public boolean saveDB(Map<String, Object> map) throws IOException, ParseException {
		log.info("welcome ServiceHrdImpl ✔ saveDB DB저장 실행", map);
		return iDao.saveDB(map);
	}

	@Override
	public boolean saveDBList(Map<String, Object> map) throws IOException, ParseException {
		log.info("welcome ServiceHrdImpl ✔ 과정, 시설정보 저장 saveDBList{}", map);
		return iDao.saveDBList(map);
	}

	@Override
	public String hrdListViewPaging(Map<String, Object> map) {
		log.info("welcome ServiceHrdImpl ✔ 목록 검색 결과 총 갯수 반환 saveDBList{}", map);
		return iDao.hrdListViewPaging(map);
	}

	/**
	 * 스케줄러에서 DB입력 실행 (서울, 경기)
	 */
	public void insertJeesoo() {
		log.info("welcome ServiceHrdImpl ✔ DB입력 시작 insertJeesoo");
		iDao.insertJeesoo();
	}
	public boolean trainstAddTrpr(HRD_Trpr_Info_Vo vo) {
		log.info("welcome ServiceHrdImpl ✔ 과정정보 입력, 수정  trainstAddTrpr");
		return iDao.trainstAddTrpr(vo);
	};
	
	@Override
	public boolean trainstAddTrainst(HRD_Trainst_Info_Vo vo) {
		log.info("welcome ServiceHrdImpl ✔ 기관정보 입력, 수정  trainstAddTrainst");
		return iDao.trainstAddTrainst(vo);
	}
	@Override
	public String trprBmkList(String user_id) {
		log.info("welcome ServiceHrdImpl ✔  해당 유저의 과정즐겨찾기 존재 여부 및 정보조회  trprBmkList {}", user_id);
		return iDao.trprBmkList(user_id);
	}
	@Override
	public String trainstBmkList(String user_id) {
		log.info("welcome ServiceHrdImpl ✔  해당 유저의 기관즐겨찾기 존재 여부 및 정보조회  trainstBmkList {}", user_id);
		return iDao.trainstBmkList(user_id);
	}
	@Override
	public boolean trprBmkInsert(Map<String, Object> map) {
		log.info("welcome ServiceHrdImpl ✔  즐겨찾기 리스트 최초 1회 추가  trprBmkInsert {}", map);
		return iDao.trprBmkInsert(map);
	}
	@Override
	public boolean trprBmkUpdate(Map<String, Object> map) {
		log.info("welcome ServiceHrdImpl ✔  즐겨찾기 리스트 업데이트  trprBmkUpdate {}", map);
		return iDao.trprBmkUpdate(map);
	}
	@Override
	public HRD_View_Vo hrdBmkList(Map<String, Object> map) {
		log.info("welcome ServiceHrdImpl ✔  북마크 목록 조회  hrdBmkList {}", map);
		return iDao.hrdBmkList(map);
	};
}
