package com.min.edu.dao;

import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.min.edu.dto.FriendDto;
import com.min.edu.dto.MessengerDto;
import com.min.edu.dto.StudentDto;

@Repository
public class DaoChatImpl implements IDaoChat {

	@Autowired
	private SqlSessionTemplate sqlSession;
	
	private final String NS = "com.min.edu.dao.IDaoChat.";
	
	private Logger logger = LoggerFactory.getLogger(this.getClass());
	
	@Override
	public List<StudentDto> selectAllUser() {
		logger.info("selectBoard");
		List<StudentDto> lists = sqlSession.selectList(NS+"selectAllUser");
		return lists;
	}
	
	@Override
	public MessengerDto selectBoard(String chatmember) {
		logger.info("selectBoard parameter : chatmember = {}", chatmember);
		MessengerDto dto = sqlSession.selectOne(NS+"selectBoard", chatmember);
		return dto;
	}

	@Override
	public boolean updateBoard(MessengerDto dto) {
		logger.info("updateBoard parameter : dto = {}", dto);
		int n = sqlSession.update(NS+"updateBoard", dto);
		return n > 0 ? true : false;
	}

	@Override
	public List<MessengerDto> selectMyChatList(String id) {
		logger.info("selectMyChatList parameter : id = {}", id);
		List<MessengerDto> lists = sqlSession.selectList(NS+"selectMyChatList", id);
		return lists;
	}

	@Override
	public boolean insertChatRoom(MessengerDto dto) {
		logger.info("insertChatRoom parameter : dto = {}", dto);
		int n = sqlSession.insert(NS+"insertChatRoom", dto);
		return n > 0 ? true : false;
	}

	@Override
	public boolean deleteChatRoom(String chatmember) {
		logger.info("deleteChatRoom parameter : chatmember = {}", chatmember);
		int n = sqlSession.delete(NS+"deleteChatRoom", chatmember);
		return n > 0 ? true : false;
	}

	@Override
	public List<FriendDto> selectFriend(String id) {
		logger.info("selectFriend parameter :  id = {}",  id);
		List<FriendDto> lists = sqlSession.selectList(NS+"selectFriend", id);
		return lists;
	}

	@Override
	public boolean insertFriend(FriendDto dto) {
		logger.info("insertFriend parameter :  dto = {}",  dto);
		int n = sqlSession.insert(NS+"insertFriend", dto);
		return n>0?true:false;
	}

	@Override
	public boolean deleteFriend(FriendDto dto) {
		logger.info("deleteFriend parameter :  dto = {}",  dto);
		int n = sqlSession.delete(NS+"deleteFriend", dto);
		return n>0?true:false;
	}

}