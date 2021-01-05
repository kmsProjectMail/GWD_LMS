package com.min.edu.service;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.min.edu.dao.IDaoChat;
import com.min.edu.dto.FriendDto;
import com.min.edu.dto.MessengerDto;
import com.min.edu.dto.StudentDto;

@Service
public class ServiceChatImpl implements IServiceChat{

	@Autowired
	private IDaoChat chatDao;
	
	private Logger logger = LoggerFactory.getLogger(this.getClass());
	
	@Override
	public List<StudentDto> selectAllUser() {
		logger.info("selectBoard");
		return chatDao.selectAllUser();
	}
	
	@Override
	public MessengerDto selectBoard(String chatmember) {
		logger.info("selectBoard parameter : chatmember = {}", chatmember);
		return chatDao.selectBoard(chatmember);
	}

	@Override
	public boolean updateBoard(MessengerDto dto) {
		logger.info("updateBoard parameter : dto = {}", dto);
		return chatDao.updateBoard(dto);
	}

	@Override
	public List<MessengerDto> selectMyChatList(String id) {
		logger.info("updateBoard parameter : id = {}", id);
		return chatDao.selectMyChatList(id);
	}

	@Override
	public boolean insertChatRoom(MessengerDto dto) {
		logger.info("updateBoard parameter : dto = {}", dto);
		return chatDao.insertChatRoom(dto);
	}

	@Override
	public boolean deleteChatRoom(String chatmember) {
		logger.info("updateBoard parameter : chatmember = {}", chatmember);
		return chatDao.deleteChatRoom(chatmember);
	}

	@Override
	public List<FriendDto> selectFriend(String id) {
		logger.info("updateBoard parameter : id = {}", id);
		return chatDao.selectFriend(id);
	}

	@Override
	public boolean insertFriend(FriendDto dto) {
		logger.info("updateBoard parameter : dto = {}", dto);
		return chatDao.insertFriend(dto);
	}

	@Override
	public boolean deleteFriend(FriendDto dto) {
		logger.info("updateBoard parameter : dto = {}", dto);
		return chatDao.deleteFriend(dto);
	}

}
