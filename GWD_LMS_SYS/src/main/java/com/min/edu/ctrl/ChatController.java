package com.min.edu.ctrl;

import java.security.Principal;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.context.ServletConfigAware;

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
		System.out.println("content(DB에 저장된 내용): " + content);
		boolean isc = chatService.updateBoard(dto);
		System.out.println(isc);
		return isc == true ? "성공" : "실패";
	}

	// WebSocket 채팅 접속
	@RequestMapping(value = "/socketOpen.do", method = { RequestMethod.GET, RequestMethod.POST })
	public String socketOpen2(HttpSession session, String user, String other, Model model) {
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
			boolean isc = chatService.insertChatRoom(boardDto);
			boardDto = chatService.selectBoard(finalChat);
		} else { // DB에 방이 있을 시 content를 가져옴
			content = boardDto.getContent();
		}

		Authentication authentication = SecurityContextHolder.getContext().getAuthentication(); 
		UserInfo users = (UserInfo) authentication.getPrincipal();
		
		String mem_id = user;
		String gr_id = finalChat;
		HashMap<String, String> chatList = (HashMap<String, String>) servletContext.getAttribute("chatList");
		if (chatList == null) {
			chatList = new HashMap<String, String>();
			chatList.put(mem_id, gr_id);
			servletContext.setAttribute("chatList", chatList);
		} else {
			chatList.put(mem_id, gr_id);
			servletContext.setAttribute("chatList", chatList);
		}

		System.out.println("contents:" + content);
		logger.info("socketOpen 소켓 화면 이동 2)리스트 값 전달");

		model.addAttribute("content", content);
		model.addAttribute("gr_id", gr_id);

		session.setAttribute("mem_id", mem_id);
		String loginUserName = chatService.selectUserName(mem_id);
		System.out.println("------ 세션에 담을 로그인 유저의 이름" + loginUserName);
		session.setAttribute("mem_name", loginUserName);
		session.setAttribute("gr_id", finalChat);
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
		List<MessengerDto> lists = chatService.selectMyChatList(id);
		System.out.println(lists);
		model.addAttribute("lists", lists);
		return "/messenger/myChatList";
	}
}
