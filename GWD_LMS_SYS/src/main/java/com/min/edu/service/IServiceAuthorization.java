package com.min.edu.service;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.min.edu.dto.AuthorizationDocumentDto;
import com.min.edu.dto.AuthorizationFileDto;
import com.min.edu.dto.AuthorizationStampDto;
import com.min.edu.dto.AuthorizationTemplateDto;

public interface IServiceAuthorization {
	
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
	public boolean setDocumentInsert(Map<String, Object> map, MultipartHttpServletRequest mpRequest) throws IOException;

	/**
	 * 결재 문서 수정
	 * @param map
	 * @return
	 */
	public boolean setDocumentModify(Map<String, Object> map);

	/**
	 * 결재 도장 등록
	 * @param map
	 * @return
	 */
	public boolean setStampInsert(Map<String, Object> map, MultipartHttpServletRequest mpRequest) throws IOException;

	/**
	 * 결재 도장 수정
	 * @param map
	 * @return
	 */
	public boolean setStampModify(Map<String, Object> map, MultipartHttpServletRequest mpRequest) throws IOException;

	/**
	 * 결재원 결재여부 수정
	 * @param map
	 * @return
	 */
	public boolean setGroupStatusModify(Map<String, Object> map);
	
	/**
	 * 결재 승인 마지막일 경우 pdf로 변환
	 * @param authorization_seq
	 * @return
	 */
	public int getDocumentToPdf(String authorization_seq);

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
