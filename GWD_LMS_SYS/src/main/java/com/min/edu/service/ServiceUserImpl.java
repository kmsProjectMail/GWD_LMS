package com.min.edu.service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.support.MessageSourceAccessor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.SpringSecurityMessageSource;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.min.edu.dao.IDaoUser;
import com.min.edu.dto.CenterDto;
import com.min.edu.dto.StudentDto;
import com.min.edu.dto.TrainstMemberDto;
import com.min.edu.info.UserInfo;
import com.min.edu.vo.HRD_View_Vo;
import com.min.edu.vo.LoginVo;

@Service
public class ServiceUserImpl implements IServiceUser {

	@Autowired
	private IServiceAuth authService;
	
	private MessageSourceAccessor messages = SpringSecurityMessageSource.getAccessor();
	
	
	private static final Logger logger = LoggerFactory.getLogger(ServiceUserImpl.class);

	
	@Autowired
	private IDaoUser dao;
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		// TODO Auto-generated method stub
		
		logger.info("loadUserByUsername 입력된 id : {}",username);
		
		LoginVo loginvo = dao.login(username);
		
		//아이디가 없으면 예외를 발생시킨다.
//		logger.info("loadUserByUsername 검색결과 : {}",loginvo.toString());

		if(loginvo == null) {
			logger.info("해당 아이디가 없습니다 . 입력된 아이디 : {}" ,username);
			UsernameNotFoundException ue = new UsernameNotFoundException(
					messages.getMessage("DaoUserImpl.notFound", new Object[] {username}, "User{0} not found"));
				throw ue;
		}
		
		Set<GrantedAuthority> userAuthSet = new HashSet<GrantedAuthority>();
		
		userAuthSet.addAll(authService.loadAuthorities(loginvo.getId()));
		
		logger.info("userAuthSet.toString () : {}",userAuthSet.toString());
		
		if(userAuthSet.size()==0) {
			logger.info("사용자 {} 어떠한 권한도 가지고 있지 않습니다. 확인이 필요합니다",loginvo.getId());
			UsernameNotFoundException ue = new UsernameNotFoundException(
					messages.getMessage("DaoAuthImpl.noAuthority", 
							new Object[] {loginvo.getId()},"user {0} has no GrantedAuthority"));
			throw ue;
		}
		
		List<GrantedAuthority> dbAuths = new ArrayList<GrantedAuthority>(userAuthSet);
		logger.info("dbAuths :{}",dbAuths.toArray());
		
		UserInfo result = new UserInfo(loginvo.getId(), loginvo.getPassword(), loginvo.getEnabled().equalsIgnoreCase("T"), dbAuths);
		
		logger.info("loadUserByUsername result (UserDetails) : {}", result.toString());
		
		
		return result;
	}

	@Transactional
	@Override
	public boolean signUpStudent(StudentDto dto) {
//		boolean isc1 = dao.insertUserID(dto.getId());
		boolean isc3 = dao.insertStudent_auth(dto.getId());
		boolean isc2 = dao.insertStudent(dto);
		if( isc2==true && isc3==true) {
			return true;
		}
		return false;
	}

	@Transactional	
	@Override
	public boolean signUpAcademy(TrainstMemberDto dto) {
//		boolean isc1 = dao.insertUserID(dto.getId());
		boolean isc3 = dao.insertAcaDemy_auth(dto.getId());
		boolean isc2 = dao.insertAcaDemy(dto);
		if( isc2==true && isc3==true) {
			return true;
		}return false;
	}

	@Transactional
	@Override
	public boolean signUpCenter(CenterDto dto) {
		logger.info("service.signUpCenter parameter : dto = {}" ,dto);
//		boolean isc1 = dao.insertUserID(dto.getId());
		boolean isc3 = dao.insertCenter_auth(dto.getId());
		boolean isc2 = dao.insertCenter(dto);
		if( isc2==true && isc3==true) { 
			return true;
		}return false;
	}

	@Override
	public String idDuplChk(String id) {
		// TODO Auto-generated method stub
		return dao.idDuplChk(id);
	}

	@Override
	public List<StudentDto> selectAllUser() {
		// TODO Auto-generated method stub
		return dao.selectAllUser();
	}

	@Override
	public StudentDto selectOneUser(String id) {
		// TODO Auto-generated method stub
		return dao.selectOneUser(id);
	}
	@Override
	public List<StudentDto> selectOneUser_dynamic(Map<String, String>map) {
		// TODO Auto-generated method stub
		return dao.selectOneUser_dynamic(map);
	}
	
	


}
