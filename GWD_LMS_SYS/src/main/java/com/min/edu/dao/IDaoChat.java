package com.min.edu.dao;

import java.util.List;

import com.min.edu.dto.FriendDto;
import com.min.edu.dto.MessengerDto;
import com.min.edu.dto.StudentDto;

public interface IDaoChat {

		// 사용자 전체 조회
		public List<StudentDto> selectAllUser();
	
		// 사용자 아이디에 해당하는 이름 조회
		public String selectUserName(String id);
		
		// 채팅방 여부 조회
		public MessengerDto selectBoard(String chatmember);
		
		// 채팅방 대화내용 업데이트
		public boolean updateBoard(MessengerDto dto);
		
		// 나의 채팅방 목록 조회
		public List<MessengerDto> selectMyChatList(String id);
		
		// 채팅방 생성
		public boolean insertChatRoom(MessengerDto dto);
		
		// 채팅방 삭제
		public boolean deleteChatRoom(String chatmember);
		
		// 친구 목록 조회
		public List<FriendDto> selectFriend(String id);
			
		// 친구 추가
		public boolean insertFriend(FriendDto dto);
			
		// 친구 삭제
		public boolean deleteFriend(FriendDto dto);
	
}
