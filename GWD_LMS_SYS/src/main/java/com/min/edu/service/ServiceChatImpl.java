package com.min.edu.service;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.min.edu.commons.utils.ChatFileUtils;
import com.min.edu.dao.IDaoChat;
import com.min.edu.dto.ChatAlarmDto;
import com.min.edu.dto.FileBoardDto;
import com.min.edu.dto.FriendDto;
import com.min.edu.dto.MessengerDto;
import com.min.edu.dto.StudentDto;

@Service
public class ServiceChatImpl implements IServiceChat{

	@Autowired
	private IDaoChat chatDao;
	
	@Resource(name="chatFileUtils")
	private ChatFileUtils chatFile;
	
	private Logger logger = LoggerFactory.getLogger(this.getClass());
	
	@Override
	public List<StudentDto> selectAllUser() {
		logger.info("selectBoard");
		return chatDao.selectAllUser();
	}

	@Override
	public String selectUserName(String id) {
		logger.info("selectUserName parameter : id = {}", id);
		return chatDao.selectUserName(id);
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
	public List<StudentDto> selectMyChatList(String id) {
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

	@Override
	public boolean chatFileUpload(Map<String, Object> map, MultipartHttpServletRequest mpRequest) throws Exception {
		logger.info("chatFileUpload parameter :  map = {}",  map);
		boolean isc = false;
		MessengerDto mDto = (MessengerDto)map.get("mDto");
		String owner = (String)map.get("owner");
		List<FileBoardDto> list = chatFile.parseInsertFileInfo(mDto,owner, mpRequest);
		int size = list.size(); 
		for(int i = 0; i<size; i++) {
			isc = chatDao.chatFileUpload(list.get(i));
		}
		return isc;
	}

	@Override
	public List<FileBoardDto> chatFileList(String b_seq) {
		logger.info("chatFileList parameter :  b_seq = {}",  b_seq);
		return chatDao.chatFileList(b_seq);
	}

	@Override
	public FileBoardDto chatFileDownload(String f_seq) {
		logger.info("chatFileDownload parameter :  f_seq = {}",  f_seq);
		return chatDao.chatFileDownload(f_seq);
	}

	@Override
	public boolean chatFileDelete(String f_seq) {
		logger.info("chatFileDelete parameter :  f_seq = {}",  f_seq);
		return chatDao.chatFileDelete(f_seq);
	}

	@Override
	public boolean chatAlarmUpdateRe(ChatAlarmDto dto) {
		logger.info("chatAlarmUpdateRe parameter :  dto = {}",  dto);
		return chatDao.chatAlarmUpdateRe(dto);
	}

	@Override
	public boolean chatAlarmUpdateSe(ChatAlarmDto dto) {
		logger.info("chatAlarmUpdateSe parameter :  dto = {}",  dto);
		return chatDao.chatAlarmUpdateSe(dto);
	}

	@Override
	public boolean chatAlarmInsert(ChatAlarmDto dto) {
		logger.info("chatAlarmInsert parameter :  dto = {}",  dto);
		return chatDao.chatAlarmInsert(dto);
	}

	@Override
	public boolean chatAlarmDelete(String chatroom) {
		logger.info("chatAlarmDelete parameter :  chatroom = {}",  chatroom);
		return chatDao.chatAlarmDelete(chatroom);
	}

	@Override
	public int chatAlarmAll() {
		logger.info("chatAlarmAll");
		return chatDao.chatAlarmAll();
	}

}
