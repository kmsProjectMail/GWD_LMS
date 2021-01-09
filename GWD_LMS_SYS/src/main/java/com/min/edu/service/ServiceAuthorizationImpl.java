package com.min.edu.service;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.min.edu.commons.utils.DocumentFileUtils;
import com.min.edu.dao.IDaoAuthorization;
import com.min.edu.dto.AuthorizationDocumentDto;
import com.min.edu.dto.AuthorizationFileDto;
import com.min.edu.dto.AuthorizationGroupDto;
import com.min.edu.dto.AuthorizationStampDto;
import com.min.edu.dto.AuthorizationTemplateDto;

@Service
public class ServiceAuthorizationImpl implements IServiceAuthorization {

	private Logger logger = LoggerFactory.getLogger(this.getClass());
	
	@Autowired
	private IDaoAuthorization dao;
	
	@Resource(name="documentFileUtils")
	private DocumentFileUtils documentFile;
	
	@Override
	public AuthorizationDocumentDto getDocumentDetail(Map<String, Object> map) {
		logger.info("getDocumentDetail");
		return dao.getDocumentDetail(map);
	}

	@Override
	public List<String> getGroupSelectAll(String authorization_seq) {
		logger.info("getGroupSelectAll");
		return dao.getGroupSelectAll(authorization_seq);
	}

	@Override
	public List<AuthorizationFileDto> getDocumentFileSelect(String authorization_seq) {
		logger.info("getDocumentFileSelect");
		return dao.getDocumentFileSelect(authorization_seq);
	}

	@Override
	public List<AuthorizationDocumentDto> getDocumentBranch(Map<String, Object> map) {
		logger.info("getDocumentBranch");
		return dao.getDocumentBranch(map);
	}

	@Override
	public String getDocumentBranchCount(Map<String, Object> map) {
		logger.info("getDocumentBranchCount");
		return dao.getDocumentBranchCount(map);
	}
	
	@Override
	public AuthorizationFileDto getDocumentFileDownload(String file_seq) {
		logger.info("getDocumentFileDownload");
		return dao.getDocumentFileDownload(file_seq);
	}

	@SuppressWarnings("unchecked")
	@Transactional
	@Override
	public boolean setDocumentInsert(Map<String, Object> map,MultipartHttpServletRequest mpRequest) throws Exception{
		logger.info("setDocumentInsert");
		boolean isc = dao.setLineInsert();
		System.out.println("isc"+isc);
		AuthorizationDocumentDto aDocumentDto = (AuthorizationDocumentDto) map.get("aDocumentDto");
		boolean isc1 = dao.setDocumentWrite(aDocumentDto);
		System.out.println("isc1"+isc1);
		List<AuthorizationGroupDto> aGroupDto = (List<AuthorizationGroupDto>) map.get("aGroupDto");
		for (int i = 0; i < aGroupDto.size(); i++) {
			System.out.println(aGroupDto.get(i));
			dao.setGroupInsert(aGroupDto.get(i));
		}
		List<AuthorizationFileDto> list = documentFile.parseInsertFileInfo(aDocumentDto, mpRequest);
		for(int i=0; i<list.size(); i++) {
			dao.setDocumentFileUpload(list.get(i));
		}
		return (isc || isc1) ? true : false;
	}

	@Transactional
	@Override
	public boolean setDocumentModify(Map<String, Object> map) {
		logger.info("setDocumentModify");
		boolean isc1 = dao.setDocumentModify(map);
		boolean isc2 = dao.setDocumentGroupReadModify(map);
		return (isc1 || isc2) ? true : false;
	}

	@Override
	public boolean setStampInsert(Map<String, Object> map) {
		logger.info("setStampInsert");
		return dao.setStampInsert(map);
	}

	@Override
	public boolean setStampModify(Map<String, Object> map) {
		logger.info("setStampModify");
		return dao.setStampModify(map);
	}

	@Override
	public boolean setGroupStatusModify(Map<String, Object> map) {
		logger.info("setGroupStatusModify");
		return dao.setGroupStatusModify(map);
	}

	@Override
	public AuthorizationStampDto getStampSelect(String id) {
		logger.info("getStampSelect");
		return dao.getStampSelect(id);
	}

	@Override
	public boolean setGroupReadModify(Map<String, Object> map) {
		logger.info("setGroupReadModify");
		return dao.setGroupReadModify(map);
	}

	@Override
	public boolean setTemplateInsert(Map<String, Object> map) {
		logger.info("setTemplateInsert");
		return dao.setTemplateInsert(map);
	}

	@Override
	public boolean setTemplateModify(Map<String, Object> map) {
		logger.info("setTemplateModify");
		return dao.setTemplateModify(map);
	}

	@Override
	public boolean setTemplateRemove(String template_type) {
		logger.info("setTemplateRemove");
		return dao.setTemplateRemove(template_type);
	}

	@Override
	public AuthorizationTemplateDto getTemplateSelect(String template_type) {
		logger.info("getTemplateSelect");
		return dao.getTemplateSelect(template_type);
	}

	@Override
	public List<AuthorizationTemplateDto> getTemplateSelectAll(Map<String, Object> map) {
		logger.info("getTemplateSelectAll");
		return dao.getTemplateSelectAll(map);
	}

}
