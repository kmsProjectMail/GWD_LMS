package com.min.edu.dao;

import java.util.List;
import java.util.Map;

import com.min.edu.dto.AuthorizationDocumentDto;
import com.min.edu.dto.AuthorizationFileDto;
import com.min.edu.dto.AuthorizationGroupDto;
import com.min.edu.dto.AuthorizationStampDto;
import com.min.edu.dto.AuthorizationTemplateDto;

public interface IDaoAuthorization {

	/**
	 * 결재 문서 상세조회
	 * @param map
	 * @return
	 */
	public AuthorizationDocumentDto getDocumentDetail(Map<String, Object> map);

	/**
	 * 결재원 조회
	 * @param authorization_seq
	 * @return
	 */
	public List<String> getGroupSelectAll(String authorization_seq);

	/**
	 * 파일 조회
	 * @param authorization_seq
	 * @return
	 */
	public List<AuthorizationFileDto> getDocumentFileSelect(String authorization_seq);

	/**
	 * 결재 문서 처리 완료, 미처리, 검색 조회
	 * @param map
	 * @return
	 */
	public List<AuthorizationDocumentDto> getDocumentBranch(Map<String, Object> map);

	/**
	 * 결재 문서 처리 완료, 미처리, 검색 조회 전체 개수
	 * @param map
	 * @return
	 */
	public String getDocumentBranchCount(Map<String, Object> map);
	
	/**
	 * 파일 등록
	 * @param dto
	 * @return
	 */
	public boolean setDocumentFileUpload(AuthorizationFileDto dto);

	/**
	 * 파일 다운
	 * @param file_seq
	 * @return
	 */
	public AuthorizationFileDto getDocumentFileDownload(String file_seq);

	/**
	 * 결재 문서 작성
	 * @param map
	 * @return
	 */
	public boolean setDocumentWrite(AuthorizationDocumentDto dto);

	/**
	 * 결재선 등록
	 * @return
	 */
	public boolean setLineInsert();

	/**
	 * 결재원 등록
	 * @param map
	 * @return
	 */
	public boolean setGroupInsert(AuthorizationGroupDto dto);

	/**
	 * 결재 문서 반려
	 * @param map
	 * @return
	 */
	public boolean setDocumentModify(Map<String, Object> map);

	/**
	 * 결재 문서 반려시 읽은 여부 초기화
	 * @param map
	 * @return
	 */
	public boolean setDocumentGroupReadModify(Map<String, Object> map);
	
	/**
	 * 결재 도장 등록
	 * @param map
	 * @return
	 */
	public boolean setStampInsert(Map<String, Object> map);

	/**
	 * 결재 도장 수정
	 * @param map
	 * @return
	 */
	public boolean setStampModify(Map<String, Object> map);

	/**
	 * 결재원 결재여부 수정
	 * @param map
	 * @return
	 */
	public boolean setGroupStatusModify(Map<String, Object> map);

	/**
	 * 결재 도장 등록 여부 조회
	 * @param id
	 * @return
	 */
	public AuthorizationStampDto getStampSelect(String id);

	/**
	 * 결재원 읽은여부 수정
	 * @param map
	 * @return
	 */
	public boolean setGroupReadModify(Map<String, Object> map);

	/**
	 * 결재 템플릿  등록
	 * @param map
	 * @return
	 */
	public boolean setTemplateInsert(Map<String, Object> map);

	/**
	 * 결재 템플릿 수정
	 * @param map
	 * @return
	 */
	public boolean setTemplateModify(Map<String, Object> map);

	/**
	 * 결재 템플릿 삭제
	 * @param template_type
	 * @return
	 */
	public boolean setTemplateRemove(String template_type);

	/**
	 * 결재 템플릿 선택
	 * @param template_type
	 * @return
	 */
	public AuthorizationTemplateDto getTemplateSelect(String template_type);

	/**
	 * 결재 템플릿 조회
	 * @param map
	 * @return
	 */
	public List<AuthorizationTemplateDto> getTemplateSelectAll(Map<String, Object> map);
}
