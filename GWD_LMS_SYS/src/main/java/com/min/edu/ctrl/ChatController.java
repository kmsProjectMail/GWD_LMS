package com.min.edu.ctrl;

import java.io.File;
import java.security.Principal;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.io.FileUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.context.ServletConfigAware;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.min.edu.dto.ChatAlarmDto;
import com.min.edu.dto.FileBoardDto;
import com.min.edu.dto.FriendDto;
import com.min.edu.dto.MessengerDto;
import com.min.edu.dto.StudentDto;
import com.min.edu.info.UserInfo;
import com.min.edu.service.IServiceChat;

@Controller
public class ChatController implements ServletConfigAware {

	private ServletContext servletContext;
	private static final Logger logger = LoggerFactory.getLogger(ChatController.class);

	@Autowired
	private IServiceChat chatService;

	@Override
	public void setServletConfig(ServletConfig servletConfig) {
		servletContext = servletConfig.getServletContext();
	}

	// 메신저 테스트 화면 페이지로 이동
	@RequestMapping(value = "/chatMain.do", method = RequestMethod.GET)
	public String chatMain(Principal principal) {
		logger.info("welcome Messenger ! ");
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		UserInfo user = (UserInfo) authentication.getPrincipal();
		return "/messenger/chatMain";
	}

	// messenger 창 화면 업로드
	@RequestMapping(value = "/goChat.do", method = RequestMethod.GET)
	public String goChat(Model model) {
		logger.info("UserController messenger");
		List<StudentDto> lists = chatService.selectAllUser();
		model.addAttribute("lists", lists);
		return "/messenger/messenger";
	}

	// 친구추가 페이지
	@RequestMapping(value = "/addUser.do", method = RequestMethod.GET)
	public String addUser(Model model) {
		List<StudentDto> lists = chatService.selectAllUser();
		model.addAttribute("lists", lists);
		return "/messenger/addUser";
	}

	// 친구목록 페이지
	@RequestMapping(value = "/friendList.do", method = { RequestMethod.POST, RequestMethod.GET })
	public String friendList(String id, Model model) {
		List<FriendDto> flists = chatService.selectFriend(id);
		model.addAttribute("flists", flists);
		return "/messenger/friendList";
	}

	// 친구 추가
	@RequestMapping(value = "/addFriend.do", method = { RequestMethod.POST, RequestMethod.GET })
	@ResponseBody
	public boolean addFriend(String id, String friend_id) {
		FriendDto dto = new FriendDto();
		dto.setId(id);
		dto.setFriend_id(friend_id);
		boolean isc = chatService.insertFriend(dto);
		return isc;
	}

	// 친구 삭제
	@RequestMapping(value = "/delFriend.do", method = { RequestMethod.POST, RequestMethod.GET })
	@ResponseBody
	public boolean delFriend(String id, String friend_id) {
		FriendDto dto = new FriendDto();
		dto.setId(id);
		dto.setFriend_id(friend_id);
		boolean isc = chatService.deleteFriend(dto);
		return isc;
	}
	
	// 사용자 아이디에 해당하는 이름 검색
	@RequestMapping(value="/selectName.do", method = RequestMethod.GET)
	@ResponseBody
	public String selectName(String id) {
		System.out.println(id);
		System.out.println("해당 아이디에 이름 검색결과 : "+chatService.selectUserName(id));
		return chatService.selectUserName(id);
	}

	// 채팅방 목록에서 채팅방 삭제
	@RequestMapping(value = "/delChat.do", method = { RequestMethod.POST, RequestMethod.GET })
	@ResponseBody
	public boolean delChat(String id, String otherId) {
		String chatmember = null;
		String sum = id + "," + otherId;
		String[] split = sum.split(",");
		Arrays.sort(split);
		chatmember = split[0] + "," + split[1];
		boolean isc = chatService.deleteChatRoom(chatmember);
		chatService.chatAlarmDelete(chatmember); // 채팅방 삭제시 해당 채팅방 수신함도 함께 삭제
		System.out.println("삭제하고자 하는 채팅방 이름은?" + chatmember);
		System.out.println(isc ? "성공" : "실패");
		return isc;
	}

	// 채팅방을 나가게 되면 대화내용을 DB에 저장 및 Chatlist (접속자 목록)에서 삭제
	@RequestMapping(value = "/updateChat.do", method = {RequestMethod.POST, RequestMethod.GET}, produces = "application/text; charset=UTF-8")
	@ResponseBody
	public String updateChat(String chatmember, String content, HttpSession session) {
		MessengerDto dto = new MessengerDto();

		System.out.println("chatmember(채팅방 이름) : " + chatmember); // chatmember -> 채팅방 이름

		String[] newChat = chatmember.split(","); // 가져온 chatmember 채팅방 이름을 , 으로 쪼갬
		Arrays.sort(newChat); // ,을 기준으로 쪼갠 채팅방이름을 담은 배열을 정렬
		String finalChat = newChat[0] + "," + newChat[1]; // 배열을 합쳐친다음 String의 형태로 받아옴 ex: user01,user02
		dto.setChatmember(finalChat);
		dto.setContent(content);
		boolean isc = chatService.updateBoard(dto);
		System.out.println(isc);
		return isc == true ? "성공" : "실패";
	}

	// WebSocket 채팅 접속
	@RequestMapping(value = "/socketOpen.do", method = { RequestMethod.GET, RequestMethod.POST })
	public String socketOpen(HttpSession session, String user, String other, Model model) {
		logger.info("socketOpen 소켓 화면 이동 1)리스트에 접속자 값 넣기");

		System.out.println("사용자는?" + user);
		System.out.println("대화할 대상은??" + other);

		String chatmember = user + "," + other;
		String[] newChat = chatmember.split(",");
		Arrays.sort(newChat);
		String finalChat = newChat[0] + "," + newChat[1];
		MessengerDto boardDto = chatService.selectBoard(finalChat);
		String content = "";
		
		// DB에 채팅방이 없을 시 DB에 방 생성
		if (boardDto == null) {
			boardDto = new MessengerDto();
			boardDto.setChatmember(finalChat);
			boardDto.setContent("");
			chatService.insertChatRoom(boardDto);
			
			// 채팅방 생성시 채팅방 알람 테이블 같이 생성
			ChatAlarmDto dto = new ChatAlarmDto();
			dto.setId(user);
			dto.setOther(other);
			dto.setChatroom(finalChat);
			chatService.chatAlarmInsert(dto);
			
			ChatAlarmDto dto2 = new ChatAlarmDto();
			dto2.setId(other);
			dto2.setOther(user);
			dto2.setChatroom(finalChat);
			chatService.chatAlarmInsert(dto2);
			
			boardDto = chatService.selectBoard(finalChat);
		} else {
			// DB에 방이 있을 시 content를 가져옴
			content = boardDto.getContent();
			
			// 방에 입장하면서 해당방 수신함에 cnt를 0으로 초기화
			ChatAlarmDto dto = new ChatAlarmDto();
			dto.setId(user);
			dto.setChatroom(finalChat);
			chatService.chatAlarmUpdateSe(dto);
		}

		Authentication authentication = SecurityContextHolder.getContext().getAuthentication(); 
		UserInfo users = (UserInfo) authentication.getPrincipal();
		
		String mem_id = user;
		String gr_id = finalChat;
		String mem_name = chatService.selectUserName(mem_id); // 로그인 유저 이름
		
		HashMap<String, String> chatList = (HashMap<String, String>) servletContext.getAttribute("chatList");
		if (chatList == null) {
			chatList = new HashMap<String, String>();
			chatList.put(mem_id, gr_id);
			servletContext.setAttribute("chatList", chatList);
		} else {
			chatList.put(mem_id, gr_id);
			servletContext.setAttribute("chatList", chatList);
		}

		logger.info("socketOpen 소켓 화면 이동 2)리스트 값 전달");

		model.addAttribute("content", content);
		model.addAttribute("gr_id", gr_id);
		model.addAttribute("chatroomDto", boardDto);
		
		// 채팅방 제목 설정을 위한 split을 사용해 상대방 아이디 및 이름 검색
		String otherName = "";
		String[] otherNameArr = gr_id.split(",");
		if(otherNameArr[0].equals(mem_id)) {
			otherName = otherNameArr[1];
		}else {
			otherName = otherNameArr[0];
		}

		// 해당 채팅방의 파일 리스트를 조회
		String chatSeq = String.valueOf(boardDto.getSeq()); // 해당 채팅방의 seq
		List<FileBoardDto> filelist = chatService.chatFileList(chatSeq);
		System.out.println("파일리스트 model 객체에 담기 진행");
		model.addAttribute("fileList", filelist);
		
		String finalOtherName = chatService.selectUserName(otherName);
		String loginUserName = chatService.selectUserName(mem_id);
		System.out.println("------ 세션에 담을 로그인 유저의 이름" + loginUserName);
		session.setAttribute("mem_name", loginUserName);
		session.setAttribute("mem_id", mem_id);
		session.setAttribute("gr_id", finalChat);
		session.setAttribute("otherName", finalOtherName);
		System.out.println("otherName 상대방 아이디로 검색한 상대방 이름 : " + finalOtherName);
		return "/messenger/groupChat";
	}

	// 채팅방에서 나갈경우 접속자 목록에서 채팅방에서 나간 사용자를 제거해줌
	@RequestMapping(value = "/socketOut.do", method = { RequestMethod.GET, RequestMethod.POST })
	public void socketOut(HttpSession session) {
		logger.info("socketOut 소켓에서 나오기");
		String mem_id = (String) session.getAttribute("mem_id");
		HashMap<String, String> chatList = (HashMap<String, String>) servletContext.getAttribute("chatList");
		System.out.println("기존 접속 회원 리스트:" + chatList);
		if (chatList != null) {
			chatList.remove(mem_id);
		}
		System.out.println("갱신 후 접속 회원 리스트:" + chatList);
		servletContext.setAttribute("chatList", chatList);
	}

	// 채팅 접속자 리스트 출력
	@RequestMapping(value = "/viewChatList.do", method = RequestMethod.GET)
	@ResponseBody
	public Map<String, Map<String, String>> viewChatList() {
		Map<String, Map<String, String>> map = new HashMap<String, Map<String, String>>();
		Map<String, String> chatList = (HashMap<String, String>) servletContext.getAttribute("chatList");
		map.put("list", chatList);
		System.out.println("접속자목록조회-------------------------------"+map);
		return map;
	}

	// 자신의 id를 포함한 이름의 채팅방을 가져옴
	@RequestMapping(value = "/myChatList.do", method = RequestMethod.GET)
	public String myChatList(Model model, String id) {
		List<StudentDto> lists = chatService.selectMyChatList(id);
		System.out.println(lists);
		model.addAttribute("lists", lists);
		return "/messenger/myChatList";
	}
	
	// 파일 업로드
	@RequestMapping(value = "/uploadChatFile.do", method = {RequestMethod.POST, RequestMethod.GET})
	@ResponseBody
	public String uploadChatFile(MessengerDto dto,String seq,String loginUser, MultipartHttpServletRequest mpRequest) throws Exception {
		System.out.println("파일 업로드 실행");
		dto.setSeq(Integer.valueOf(seq));
		List<MultipartFile> list = mpRequest.getFiles("file");
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("mDto", dto);
		map.put("owner", loginUser);
		boolean isc = chatService.chatFileUpload(map, mpRequest);
		System.out.println(isc?"파일 업로드 성공":"파일 업로드 실패");
		return null;
	}
	
	// 파일 다운로드
	@RequestMapping(value = "/downloadChatFile.do", method= {RequestMethod.POST, RequestMethod.GET})
	public void downloadChatFile(String f_seq, HttpServletResponse response) throws Exception {
		System.out.println("다운로드할 파일의 seq 값은? "+f_seq);
		FileBoardDto filedown = chatService.chatFileDownload(f_seq);
		System.out.println("파일다운로드 진행중");
		String storedFileName = filedown.getStored_fname();
		String originalFileName = filedown.getOrigin_fname();
		
		// 파일이 저장된 위치 찾기
		byte fileByte[] = FileUtils.readFileToByteArray(new File("C:\\chat_test_file\\"+storedFileName));
		
		response.reset();
		response.setContentType("application/octet-stream");
		response.setContentLength(fileByte.length);
		String encoding = new String(originalFileName.getBytes("UTF-8"), "8859_1");
		
		response.setHeader("Content-Disposition", "attachment; filename=\""+encoding);
		response.getOutputStream().write(fileByte);
		response.getOutputStream().flush();
		response.getOutputStream().close();
	}
	
	// 접속하지 않은 상태에서 메세지를 수신했을 경우 수신함에 표시
	@RequestMapping(value="/updateAlarm.do", method= {RequestMethod.POST, RequestMethod.GET})
	@ResponseBody
	public void updateAlarm(String id, String chatroom) {
		ChatAlarmDto dto = new ChatAlarmDto();
		System.out.println("알람 id는??" + id);
		System.out.println("알람 chatroom는??" + chatroom);
		dto.setId(id);
		dto.setChatroom(chatroom);
		chatService.chatAlarmUpdateRe(dto);
	}
	
	// 수신함 확인
	@RequestMapping(value="/chatAlarm.do", method= {RequestMethod.POST, RequestMethod.GET})
	@ResponseBody
	public int chatAlarm(Principal prin) {
		int n = 0;
		if(prin.getName() != null || prin.getName()!="" ) {
			n = chatService.chatAlarmAll(prin.getName());
		}
		return n;
	}
}
