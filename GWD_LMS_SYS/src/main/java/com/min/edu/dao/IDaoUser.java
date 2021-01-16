package com.min.edu.dao;

import java.util.List;
import java.util.Map;

import com.min.edu.dto.CenterDto;
import com.min.edu.dto.StudentDto;
import com.min.edu.dto.TrainstMemberDto;
import com.min.edu.vo.LoginVo;

public interface IDaoUser {
	public LoginVo login(String id);
	public boolean insertUserID(String id);
	public boolean insertStudent(StudentDto dto);
	public boolean insertStudent_auth(String id);
	public boolean insertAcaDemy(TrainstMemberDto dto);
	public boolean insertAcaDemy_auth(String id);
	public boolean insertCenter(CenterDto dto);
	public boolean insertCenter_auth(String id);
	public String idDuplChk(String id);
	public List<StudentDto> selectAllUser();
	public StudentDto selectOneUser(String id);
	public List<StudentDto> selectOneUser_dynamic(Map<String, String> map);
	public List<String> findID(Map<String, String> map);
	public String findPW(Map<String, String> map);
	public boolean updatePassword(Map<String, String> map);
}
