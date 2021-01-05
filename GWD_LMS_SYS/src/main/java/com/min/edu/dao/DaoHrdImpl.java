package com.min.edu.dao;

import java.util.List;
import java.util.Map;

import org.mybatis.spring.SqlSessionTemplate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.min.edu.vo.HRD_Trainst_Info_Vo;
import com.min.edu.vo.HRD_Trpr_Info_Vo;

@Repository
public class DaoHrdImpl implements IDaoHrd{
	
	private Logger log = LoggerFactory.getLogger(this.getClass());
	private final String NS = "com.min.edu.dao.IDaoHrd.";
	
	@Autowired
	private SqlSessionTemplate sqlSession;
	
	@Override
	public boolean selectTrainst(String trainst_cst_id){
		log.info("welcome DaoHrdImpl 😍 기관 중복 검색 selectTrainst {}", trainst_cst_id);
		List<HRD_Trainst_Info_Vo> vo = sqlSession.selectList(NS+"selectTrainst", trainst_cst_id);
		System.out.println("기관정보가 존재하지 않음: "+vo.isEmpty());
		return vo.isEmpty();
	}
	
	@Override
	public boolean selectTrpr(Map<String, Object> map){
		log.info("welcome DaoHrdImpl 😍과정 중복 검색 selectTrpr {}", map);
		List<HRD_Trainst_Info_Vo> vo = sqlSession.selectList(NS+"selectTrpr", map);
		System.out.println("과정정보가 존재하지 않음: "+vo.isEmpty());
		return vo.isEmpty();
	}

	@Override
	public boolean insertTrainstInfo(HRD_Trainst_Info_Vo vo) {
		log.info("welcome DaoHrdImpl 😍 기관정보 입력 insertTrainstInfo {}", vo);
		int cnt = sqlSession.insert(NS+"insertTrainstInfo", vo);
		return cnt>0?true:false;
	}

	@Override
	public boolean insertTrprInfo(HRD_Trpr_Info_Vo vo) {
		log.info("welcome DaoHrdImpl 😍 과정정보 입력 insertTrprInfo {}", vo);
		int cnt = sqlSession.insert(NS+"insertTrprInfo", vo);
		return cnt>0?true:false;
	}

}
