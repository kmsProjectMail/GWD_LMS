package com.min.edu.dao;

import java.util.List;
import java.util.Map;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.min.edu.dto.AuthorizationDocumentDto;
import com.min.edu.dto.AuthorizationFileDto;
import com.min.edu.dto.AuthorizationGroupDto;
import com.min.edu.dto.AuthorizationStampDto;
import com.min.edu.dto.AuthorizationTemplateDto;

@Repository
public class DaoAuthorizationImpl implements IDaoAuthorization {

	@Autowired
	private SqlSessionTemplate session;
	
	private final String NS = "authorization.";
	
	@Override
	public AuthorizationDocumentDto getDocumentDetail(Map<String, Object> map) {
		return session.selectOne(NS + "getDocumentDetail",map);
	}

	@Override
	public List<String> getGroupSelectAll(String authorization_seq) {
		return session.selectList(NS+"getGroupSelectAll",authorization_seq);
	}

	@Override
	public List<AuthorizationFileDto> getDocumentFileSelect(String authorization_seq) {
		return session.selectList(NS+"getDocumentFileSelect",authorization_seq);
	}

	@Override
	public List<AuthorizationDocumentDto> getDocumentBranch(Map<String, Object> map) {
		return session.selectList(NS+"getDocumentBranch",map);
	}

	@Override
	public boolean setDocumentFileUpload(AuthorizationFileDto dto) {
		int cnt = session.insert(NS+"setDocumentFileUpload",dto);
		return cnt > 0 ? true : false;
	}

	@Override
	public AuthorizationFileDto getDocumentFileDownload(String file_seq) {
		return session.selectOne(NS+"getDocumentFileDownload",file_seq);
	}

	@Override
	public boolean setDocumentWrite(AuthorizationDocumentDto dto) {
		int cnt = session.insert(NS+"setDocumentWrite",dto);
		return cnt > 0 ? true : false;
	}

	@Override
	public boolean setLineInsert() {
		int cnt = session.insert(NS+"setDocumentWrite");
		return cnt > 0? true : false;
	}

	@Override
	public boolean setGroupInsert(AuthorizationGroupDto dto) {
		int cnt = session.insert(NS+"setGroupInsert",dto);
		return cnt > 0 ? true : false;
	}

	@Override
	public boolean setDocumentModify(Map<String, Object> map) {
		int cnt = session.update(NS+"setDocumentModify",map);
		return cnt > 0 ? true : false;
	}

	@Override
	public boolean setDocumentGroupReadModify(Map<String, Object> map) {
		int cnt = session.update(NS+"setDocumentGroupReadModify",map);
		return cnt > 0 ? true : false;
	}
	
	@Override
	public boolean setStampInsert(Map<String, Object> map) {
		int cnt = session.insert(NS+"setStampInsert",map);
		return cnt > 0 ? true : false;
	}

	@Override
	public boolean setStampModify(Map<String, Object> map) {
		int cnt = session.update(NS+"setStampModify",map);
		return cnt > 0 ? true : false;
	}

	@Override
	public boolean setGroupStatusModify(Map<String, Object> map) {
		int cnt = session.update(NS+"setGroupStatusModify",map);
		return cnt > 0 ? true : false;
	}

	@Override
	public AuthorizationStampDto getStampSelect(String id) {
		return session.selectOne(NS+"getStampSelect",id);
	}

	@Override
	public boolean setGroupReadModify(Map<String, Object> map) {
		int cnt = session.update(NS+"setGroupReadModify",map);
		return cnt > 0 ? true : false;
	}

	@Override
	public boolean setTemplateInsert(Map<String, Object> map) {
		int cnt = session.insert(NS+"setTemplateInsert",map);
		return cnt > 0 ? true : false;
	}

	@Override
	public boolean setTemplateModify(Map<String, Object> map) {
		int cnt = session.update(NS+"setTemplateModify",map);
		return cnt > 0 ? true : false;
	}

	@Override
	public boolean setTemplateRemove(String template_type) {
		int cnt = session.delete(NS+"setTemplateRemove",template_type);
		return cnt > 0 ? true : false;
	}

	@Override
	public AuthorizationTemplateDto getTemplateSelect(String template_type) {
		return session.selectOne(NS+"getTemplateSelect",template_type);
	}

	@Override
	public List<AuthorizationTemplateDto> getTemplateSelectAll(Map<String, Object> map) {
		return session.selectList(NS+"getTemplateSelectAll",map);
	}

}
