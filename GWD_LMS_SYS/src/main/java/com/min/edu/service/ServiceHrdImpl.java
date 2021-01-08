package com.min.edu.service;

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
	public boolean selectTrpr(Map<String, Object> map){
		log.info("welcome ServiceHrdImpl ✔과정 중복 검색 selectTrpr {}", map);
		return iDao.selectTrpr(map);
	}
	
	@Override
	public boolean insertTrainstInfo(HRD_Trainst_Info_Vo vo) {
		log.info("welcome ServiceHrdImpl ✔ 기관정보 입력 insertTrainstInfo {}", vo);
		return iDao.insertTrainstInfo(vo);
	}

	@Override
	public boolean insertTrprInfo(HRD_Trpr_Info_Vo vo) {
		log.info("welcome ServiceHrdImpl ✔ 과정정보 입력 insertTrprInfo {}", vo);
		return iDao.insertTrprInfo(vo);
	}

	@Override
	public List<HRD_View_Vo> hrdListView(Map<String, Object> map) {
		log.info("welcome ServiceHrdImpl ✔ search DB검색 hrdListView {}", map);
		return iDao.hrdListView(map);
	}

	@Override
	public List<HRD_Trainst_Info_Vo> alltrainstinfo(String addr1) {
		// TODO Auto-generated method stub
		return iDao.alltrainstinfo(addr1);
	}

}
