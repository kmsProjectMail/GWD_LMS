package com.min.edu.dao;

import java.util.List;
import java.util.Map;

import org.mybatis.spring.SqlSessionTemplate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.min.edu.dto.Board_Dto;
import com.min.edu.dto.Reply_Dto;

@Repository
public class DaoBoardImpl implements IDaoBoard{
	@Autowired
	private SqlSessionTemplate session;
	private Logger logger = LoggerFactory.getLogger(this.getClass());
	
	private final String NS = "com.min.edu.dtos.board.";
	private final String NS1 = "com.min.edu.dtos.reply.";
	
	@Override
	public List<Board_Dto> selectAll() {
		logger.info("\t BoardDaoImpl selectAll 입장");
		return session.selectList(NS+"selectAll");
	}

	@Override
	public Board_Dto getOneBoard(String boardseq) {
		logger.info("\t BoardDaoImpl getOneBoard 입장");
		return session.selectOne(NS+"getOneBoard",boardseq);
	}

	@Override
	public boolean modBoard(Board_Dto dto) {
		logger.info("\t BoardDaoImpl modBoard 입장");
		int n =session.update(NS+"modBoard", dto);
		return n>0?true:false;
	}

	@Override
	public boolean delBoard(String boardseq) {
		logger.info("\t BoardDaoImpl delBoard 입장");
		int n =session.update(NS+"delBoard",boardseq);
		return n>0?true:false;
	}

	@Override
	public boolean dReply(String boardseq) {
		logger.info("\t dReply delBoard 입장");
		int n =session.update(NS+"dReply",boardseq);
		return n>0?true:false;
	}
	
	@Override
	public boolean inputBoard(Board_Dto dto) {
		logger.info("\t BoardDaoImpl inputBoard 입장");
		int n =session.insert(NS+"inputBoard", dto);
		return n>0?true:false;
	}

	@Override
	public boolean deleteBoard(String boardseq) {
		logger.info("\t BoardDaoImpl deleteBoard 입장");
		int n =session.delete(NS+"deleteBoard",boardseq);
		return n>0?true:false;
	}

	@Override
	public boolean multiDel(Map<String, String[]> map) {
		logger.info("\t BoardDaoImpl multiDel 입장");
		int n =session.update(NS+"multiDel",map);
		return n>0?true:false;
	}

	@Override
	public boolean inputReply(Reply_Dto dto) {
		logger.info("\t BoardDaoImpl inputReply 입장");
		int n =session.insert(NS1+"inputReply", dto);
		return n>0?true:false;
	}

	@Override
	public List<Reply_Dto> oneBoardReply(String boardseq) {
		logger.info("\t BoardDaoImpl oneBoardReply 입장");
		return session.selectList(NS+"oneBoardReply", boardseq);
	}

	@Override
	public boolean delReply(String replyseq) {
		logger.info("\t BoardDaoImpl delReply 입장");
		int n =session.update(NS1+"delReply", replyseq);
		return n>0?true:false;
	}

	@Override
	public boolean modiReply(Map<String, Object> map) {
		logger.info("\t BoardDaoImpl modiReply 입장");
		int n =session.update(NS1+"modiReply", map);
		return n>0?true:false;
	}

	@Override
	public Reply_Dto oneRe(String boardseq) {	
		
		return session.selectOne(NS1+"oneRe", boardseq);
	}

	@Override
	public List<Board_Dto> searchBoard(String keyword) {
		return session.selectList(NS+"searchBoard", keyword);
	}

	

}
