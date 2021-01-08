package com.min.edu.dao;

import java.util.List;

import com.min.edu.dto.FileBoardDto;


public interface IDaoFile {

	/**
	 * 첨부파일 입력
	 * @param dto
	 * @return
	 */
	public boolean setFileBoardUpload(FileBoardDto dto);

	/**
	 * 첨부파일 조회
	 * @param b_seq
	 * @return
	 */
	public List<FileBoardDto> getFileBoardList(String b_seq);

	/**
	 * 첨부파일 다운
	 * @param f_seq
	 * @return
	 */
	public FileBoardDto getFileBoardDownload(String f_seq) throws Exception;

	/**
	 * 첨부파일 삭제
	 * @param f_seq
	 * @return
	 */
	public boolean setFileBoardDelete (String f_seq) throws Exception;
}
