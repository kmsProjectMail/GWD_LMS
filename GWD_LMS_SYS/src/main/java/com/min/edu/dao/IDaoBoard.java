package com.min.edu.dao;

import java.util.List;
import java.util.Map;

import com.min.edu.dto.Board_Dto;
import com.min.edu.dto.Reply_Dto;



public interface IDaoBoard {

	/**
	 * 게시글 전체 보기
	 */
	public List<Board_Dto> selectAll();
	
	/**
	 * 게시글 상세보기
	 */
	public Board_Dto getOneBoard(String boardseq);
	
	/**
	 * 게시글 수정하기
	 */
	public boolean modBoard(Board_Dto dto);
	
	/**
	 * 게시글 화면에서 삭제
	 */
	public boolean delBoard(String boardseq);
	public boolean dReply(String boardseq);
	
	/**
	 * 댓글 입력
	 */
	public boolean inputReply(Reply_Dto dto);
	
	/**
	 * 게시글입력
	 */
	public boolean inputBoard(Board_Dto dto);
	
	/**
	 * db 삭제
	 */
	public boolean deleteBoard(String boardseq);
	
	/**
	 * 다중 삭제
	 */
	public boolean multiDel(Map<String, String[]> map);
	
	
//	REPLY_BOARD
	/**
	 * 댓글 보기
	 */
	public List<Reply_Dto> oneBoardReply(String boardseq);
	/**
	 * 댓글 삭제
	 */
	public boolean delReply(String replyseq);
	/**
	 * 댓글 수정
	 */
	public boolean modiReply(Map<String, Object> map);
	/**
	 * 댓글seq
	 */
	public Reply_Dto oneRe(String boardseq);
	/**
	 * 조회기능
	 */
//	searchBoard
	public List<Board_Dto> searchBoard(String word);
	
	
}
