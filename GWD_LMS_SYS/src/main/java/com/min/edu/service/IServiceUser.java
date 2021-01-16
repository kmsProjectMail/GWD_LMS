package com.min.edu.service;

import java.util.List;
import java.util.Map;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import com.min.edu.dto.CenterDto;
import com.min.edu.dto.StudentDto;
import com.min.edu.dto.TrainstMemberDto;

public interface IServiceUser extends UserDetailsService {

	public abstract UserDetails loadUserByUsername(String username) throws UsernameNotFoundException;
	
	public boolean signUpStudent(StudentDto dto);
	public boolean signUpAcademy(TrainstMemberDto dto);
	public boolean signUpCenter(CenterDto dto);

	public String idDuplChk(String id);
	
	public List<StudentDto> selectAllUser();
	
	public StudentDto selectOneUser(String id);

	public List<StudentDto> selectOneUser_dynamic(Map<String, String> map);
	
	public List<String> findID(Map<String, String> map);
	public String findPW(Map<String, String> map);

	public boolean updatePassword(Map<String, String> map);
}
