package com.min.edu.dao;

import java.util.List;
import java.util.Map;

import org.mybatis.spring.SqlSessionTemplate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.min.edu.dto.CenterDto;
import com.min.edu.dto.StudentDto;
import com.min.edu.dto.TrainstMemberDto;
import com.min.edu.vo.LoginVo;
@Repository
public class DaoUserImpl implements IDaoUser {
	
	@Autowired
	private SqlSessionTemplate sqlSession;
	
	private final String NS ="usermapper.";
	private final Logger logger = LoggerFactory.getLogger(this.getClass());
	
	@Override
	public LoginVo login(String id) {
		// TODO Auto-generated method stub
		logger.info("DaoUserImpl parameter : id  = {}", id);
		
		
		return sqlSession.selectOne(NS+"login", id);
	}

	@Override
	public boolean insertUserID(String id) {
		// TODO Auto-generated method stub
		return sqlSession.insert(NS+"insertUserID", id) >0 ? true :false ;
		
	}

	@Override
	public boolean insertStudent(StudentDto dto) {
		// TODO Auto-generated method stub
		return sqlSession.insert(NS+"insertStudent", dto)  >0 ? true :false;
	}

	@Override
	public boolean insertStudent_auth(String id) {
		// TODO Auto-generated method stub
		return sqlSession.insert(NS+"insertStudent_auth", id)  >0 ? true :false;
	}

	@Override
	public boolean insertAcaDemy(TrainstMemberDto dto) {
		// TODO Auto-generated method stub
		return sqlSession.insert(NS+"insertAcaDemy", dto)  >0 ? true :false;
	}

	@Override
	public boolean insertAcaDemy_auth(String id) {
		// TODO Auto-generated method stub
		return sqlSession.insert(NS+"insertAcaDemy_auth", id)  >0 ? true :false;
	}

	@Override
	public boolean insertCenter(CenterDto dto) {
		// TODO Auto-generated method stub
		return sqlSession.insert(NS+"insertCenter", dto)  >0 ? true :false;
	}

	@Override
	public boolean insertCenter_auth(String id) {
		// TODO Auto-generated method stub
		return sqlSession.insert(NS+"insertCenter_auth", id)  >0 ? true :false;
	}

	@Override
	public String idDuplChk(String id) {
		// TODO Auto-generated method stub
		return sqlSession.selectOne(NS+"idDuplChk", id);
		
	}

	@Override
	public List<StudentDto> selectAllUser() {
		// TODO Auto-generated method stub
		return sqlSession.selectList(NS+"selectAllUser");
	}

	@Override
	public StudentDto selectOneUser(String id) {
		// TODO Auto-generated method stub
		return sqlSession.selectOne(NS+"selectOneUser", id);
	}

	@Override
	public List<StudentDto> selectOneUser_dynamic(Map<String, String> map) {
		// TODO Auto-generated method stub
		return sqlSession.selectList(NS+"selectOneUser_dynamic" , map);
	}
	

	
	
}
