package com.min.edu.dao;

import java.util.List;
import java.util.Map;

import com.min.edu.dto.Board_Dto;


public interface IBoardPage {

	public int countMyBoard();
	public List<Board_Dto> pageList(Map<String, Object> map);
}
