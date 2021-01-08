package com.min.edu.dao;

import java.util.List;
import java.util.Map;

import org.mybatis.spring.SqlSessionTemplate;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.min.edu.dto.Board_Dto;
import com.min.edu.dto.Reply_Dto;

@Service
public class DaoPageImpl implements IBoardPage {
	@Autowired
	private SqlSessionTemplate session;
	private final org.slf4j.Logger logger = LoggerFactory.getLogger(this.getClass());
	private final String NS = "com.min.edu.dao.IBoardPage.";
	
	@Override
	public int countMyBoard() {
		logger.info("전체 글의 개수");
		int n = session.selectOne(NS+"countMyBoard");
		return n;
	}

	@Override
	public List<Board_Dto> pageList(Map<String, Object> map) {
		logger.info("페이지별 게시글 리스트");
		List<Board_Dto> lists = session.selectList(NS+"pageList", map);
		return lists;
	}

	@Override
	public List<Reply_Dto> replyList(Map<String, Object> map) {
		logger.info("페이지별 댓글 리스트");
		System.out.println("map"+map);
		return session.selectList(NS+"replyList", map);
	}

	@Override
	public int replyCount(String boardseq) {
		logger.info("전체 댓글 개수");
		int n = session.selectOne(NS+"replyCount", boardseq);
		System.out.println(n);
		return n>0?11:00;
	}
}
