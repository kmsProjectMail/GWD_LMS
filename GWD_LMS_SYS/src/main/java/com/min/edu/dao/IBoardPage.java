package com.min.edu.dao;

import java.util.List;
import java.util.Map;

import com.min.edu.dto.Board_Dto;
import com.min.edu.dto.Reply_Dto;


public interface IBoardPage {

	public int countMyBoard();
	public List<Board_Dto> pageList(Map<String, Object> map);
	public int replyCount(String boardseq);
	public List<Reply_Dto> replyList(Map<String, Object> map);
	
}
