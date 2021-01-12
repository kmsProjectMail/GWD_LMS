package com.min.edu.service;

import java.util.List;
import java.util.Map;

import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.min.edu.dto.ChatAlarmDto;
import com.min.edu.dto.FileBoardDto;
import com.min.edu.dto.FriendDto;
import com.min.edu.dto.MessengerDto;
import com.min.edu.dto.StudentDto;

public interface IServiceChat {

	// 사용자 전체 조회
	public List<StudentDto> selectAllUser();
	
	// 사용자 아이디에 해당하는 이름 조회
	public String selectUserName(String id);
	
	// 채팅방 여부 조회
	public MessengerDto selectBoard(String chatmember);
	
	// 채팅방 대화내용 업데이트
	public boolean updateBoard(MessengerDto dto);
	
	// 나의 채팅방 목록 조회
	public List<StudentDto> selectMyChatList(String id);
	
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
	
	// 채팅 파일 업로드
	public boolean chatFileUpload(Map<String, Object> map, MultipartHttpServletRequest mpRequest) throws Exception;
	
	// 채팅 파일 조회
	public List<FileBoardDto> chatFileList(String b_seq);
	
	// 채팅 파일 다운
	public FileBoardDto chatFileDownload(String f_seq);
	
	// 채팅 파일 삭제
	public boolean chatFileDelete(String f_seq);
	
	// 채팅방 생성시 수신함테이블에 생성한 채팅방에 해당하는 데이터 추가
	public boolean chatAlarmInsert(ChatAlarmDto dto);
	
	// 메세지를 주고받을 때 접속자 세션 목록에 상대방이 없을 시 CNT 1로 수정
	public boolean chatAlarmUpdateRe(ChatAlarmDto dto);
	
	// 메세지를 읽었을 때 CNT 0로 수정
	public boolean chatAlarmUpdateSe(ChatAlarmDto dto);	
	
	// 채팅방 삭제 시 수신함 데이터 삭제
	public boolean chatAlarmDelete(String chatroom);
	
	// 수신함 전체 조회
	public int chatAlarmAll(String id);
}
