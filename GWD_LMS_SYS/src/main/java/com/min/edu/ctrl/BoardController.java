package com.min.edu.ctrl;

import java.io.File;
import java.security.Principal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.io.FileUtils;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.min.edu.dao.IBoardPage;
import com.min.edu.dto.Board_Dto;
import com.min.edu.dto.FileBoardDto;
import com.min.edu.dto.MemberAuthDto;
import com.min.edu.dto.Paging;
import com.min.edu.dto.Reply_Dto;
import com.min.edu.service.IServiceAuth;
import com.min.edu.service.IServiceBoard;
import com.min.edu.service.IServiceFile;

@Controller
public class BoardController {
	private Logger logger = LoggerFactory.getLogger(this.getClass());

	@Autowired
	private IServiceAuth authService;

	@Autowired
	private IServiceBoard dao;

	@Autowired
	private IBoardPage pageDao;

	@Autowired
	private IServiceFile file;

	@RequestMapping(value = "/board/board.do", method = RequestMethod.GET)
	public String board(HttpServletRequest req, Model model, Principal principal) {
		logger.info("BoardController board 입장");
		String page = req.getParameter("page");
		if (page == null) {
			page = "1";
		}
		String auth = authService.selectUserAuth(principal.getName()).getAuth();
		model.addAttribute("auth", auth);
		
		
		int selectPage = Integer.parseInt(page);
		System.out.println("현재 페이지 :" + selectPage);

		Paging p = new Paging();

		// 총 게시물의 개수
		p.setTotalCount(pageDao.countMyBoard());

		// 출력될 게시물의 개수
		p.setCountList(10);

		// 화면에 몇 개의 페이지를 보여줄지.(그룹)
		p.setCountPage(10);

		// 총 페이지 개수
		p.setTotalPage(p.getTotalCount()); // set이 있어야 들어감

		// 현재 페이지의 번호
		p.setPage(selectPage);

		// 시작 번호
		p.setStartPage(selectPage);

		// 끝 번호
		p.setEndPage(p.getCountPage());

		// 게시글을 가져옴
		Map<String, Object> map = new HashMap<String, Object>();

		// (p.getPage()-1)*getCountList+1
		map.put("first", p.getPage() * p.getCountList() - (p.getCountList() - 1));
		map.put("last", p.getPage() * p.getCountList());

		List<Board_Dto> list = pageDao.pageList(map);

		// 게시글, 페이지
		model.addAttribute("lists", list);
		model.addAttribute("page", p);
		return "board/board";
	}

//	새글입력 폼
	@RequestMapping(value = "/board/inputBForm.do", method = RequestMethod.GET)
	public String inputB(Principal principal , Model model) {
		logger.info("BoardController inputB 입장");
		model.addAttribute("id", principal.getName());
		return "board/inputBForm";
	}

//	새글입력 	
	@RequestMapping(value = "/board/inputBoard.do", method = RequestMethod.POST)
	public String inputBoard(Board_Dto dto, MultipartHttpServletRequest mpRequest) throws Exception {
		logger.info("BoardController inputBoard 입장 \t{}", dto);

		List<MultipartFile> list = mpRequest.getFiles("file");
		logger.info("BoardController inputBoard" + list);
		boolean isc = dao.inputBoard(dto, mpRequest);
		return isc ? "redirect:./board.do" : "error";
	}

//	BD 진짜 삭제
	@RequestMapping(value = "/board/deleteBoard.do", method = RequestMethod.POST)
	public String deleteBoard(@RequestParam("chk") String[] boardseqs) {
		Map<String, String[]> map = new HashMap<String, String[]>();
		System.out.println("boardseqsboardseqs" + boardseqs);
		map.put("boardseqs", boardseqs);
		boolean isc = dao.multiDel(map);
		return isc ? "redirect:./board.do" : "board/error";
	}

	// 게시글 상세보기에서 수정버튼 클릭
	@RequestMapping(value = "/board/modifyMove.do", method = RequestMethod.GET)
	public String modifyMove(String boardseq, Model model) throws Exception {
		System.out.println("-----------modifyMove입장------------" + boardseq);

		// 첨부파일 조회(첨부파일 목록을 수정에 띄워야 되므로)
		List<FileBoardDto> fileList = file.getFileBoardList(boardseq);
		model.addAttribute("file", fileList);

		// 게시글 내용 조회(게시글 내용을 수정에 띄워야 되므로)
		model.addAttribute("dto", dao.getOneBoard(boardseq));
		return "board/modifyForm";
	}

//			 게시글 내용 수정
	@RequestMapping(value = "/board/modify.do", method = RequestMethod.POST)
	public String modify(Board_Dto dto, @RequestParam("fileNoDel") String[] files,
			@RequestParam("fileNameDel") String[] fileNames, MultipartHttpServletRequest mpRequest, String boardseq)
			throws Exception {
		logger.info("modify {}", dto);
		System.out.println("boardseqboardseq" + boardseq);
		dao.modBoard(dto, files, fileNames, mpRequest);
		return "redirect:./oneBoard.do?boardseq=" + boardseq;
	}

//		게시판 상세보기
	@RequestMapping(value = "/board/oneBoard.do", method = RequestMethod.GET)
	public String board(String boardseq,String pages, Model model, Principal pro, HttpServletRequest req) throws Exception {
//		logger.info("principal --> ", principal.toString());
		// 상세글
		Board_Dto dto = dao.getOneBoard(boardseq);
		// 파일
		List<FileBoardDto> fileList = file.getFileBoardList(boardseq);
		System.out.println(fileList);
		System.out.println(dto);
		model.addAttribute("files", fileList);
		model.addAttribute("dto", dto);
		model.addAttribute("logid", pro.getName());
		String auth = authService.selectUserAuth(pro.getName()).getAuth();
		model.addAttribute("auth", auth);
		
		logger.info("BoardController oneboard 입장");
		String page = pages;
		if (page == null ) {
			page = "1";
		}
		
		int selectPage = Integer.parseInt(page);
		System.out.println("현재 페이지>? :" + selectPage);

		Paging p = new Paging();
		
		// 총 게시물의 개수
		p.setTotalCount(pageDao.replyCount(boardseq));
		
		// 출력될 게시물의 개수
		p.setCountList(3);
	
		p.setCountPage(3);
		
		// 총 페이지 개수
		p.setTotalPage(p.getTotalCount()); // set이 있어야 들어감

		// 현재 페이지의 번호
		p.setPage(selectPage);

		// 시작 번호
		p.setStartPage(selectPage);

		// 끝 번호
		p.setEndPage(p.getCountPage());

		// 게시글을 가져옴
		Map<String, Object> map = new HashMap<String, Object>();

		// (p.getPage()-1)*getCountList+1
		map.put("first", p.getPage() * p.getCountList() - (p.getCountList() - 1));
		map.put("last", p.getPage() * p.getCountList());
		map.put("boardseq", boardseq);
		List<Reply_Dto> list = pageDao.replyList(map);
		// 게시글, 페이지
		model.addAttribute("lists", list);
		model.addAttribute("pages", p);

		return "board/oneBoard";
	}
//	댓글입력아작스 
	@RequestMapping(value = "/board/inputRAjax.do", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, String> inputRAjax(String boardseq, String content, String title, String userid, Model model) {
		Map<String, String> map = new HashMap<String, String>();
		Reply_Dto dto = new Reply_Dto(boardseq, content, title, userid);
		boolean isc = dao.inputReply(dto);
		System.out.println("dto+iscdto+isc" + dto + isc);

		if (isc) {
			map.put("isc", "true");
		} else {
			map.put("isc", "false");
		}
		return map;
	}
//	댓글입력
	@RequestMapping(value = "/board/inputReply.do", method = RequestMethod.POST)
	public String inputReply(@RequestParam("bseq") String boardseq) {
		logger.info("inputReply \t {}", boardseq);
		System.out.println(boardseq);
		Board_Dto dto = dao.getOneBoard(boardseq);
		return "redirect:./oneBoard.do?boardseq=" + boardseq;
	}

//	수정 아작스 
	@RequestMapping(value = "/board/modiAjax.do", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, String> modiAjax(@RequestParam("contents") String content, String replyseq, String boardseq) {
		logger.info("modiAjax \t {}", content, replyseq, boardseq);
		Map<String, String> map = new HashMap<String, String>();
		Map<String, Object> map2 = new HashMap<String, Object>();
		map2.put("replyseq", replyseq);
		map2.put("content", content);
		boolean isc = dao.modiReply(map2);
		if (isc == true) {
			map.put("isc", "true");
		} else {
			map.put("isc", "false");
		}

		return map;
	}

//	댓글수정
	@RequestMapping(value = "/board/modiReply.do", method = RequestMethod.POST)
	public String modiReply(@RequestParam("rseq") String replyseq, @RequestParam("contents") String content,
			@RequestParam("bseq") String boardseq) {
		logger.info("modiReplymodiReplymodiReplymodiReply \n {} ", content);
		Board_Dto dto = dao.getOneBoard(boardseq);
		return "redirect:./oneBoard.do?boardseq=" + boardseq;
	}

//	게시글 삭제 --> 그에 따른 reply도 삭제
	@RequestMapping(value = "/board/delBoard.do", method = RequestMethod.GET)
	public String delBoard(String boardseq) throws Exception {
		boolean isc = dao.del(boardseq);
		return isc ? "redirect:./board.do" : "error";
	}

	// 댓글 삭제
	@RequestMapping(value = "/board/delReply.do", method = RequestMethod.GET)
	public String delReply(String replyseq, String boardseq) {
		logger.info("delReply ------------------", boardseq);
		System.out.println(replyseq + boardseq + "boardseqboardseqboardseqboardseq");
		boolean isc = dao.delReply(replyseq);
		return isc ? "redirect:./oneBoard.do?boardseq=" + boardseq : "redirect:./board.do";
	}

	// 단어 search
	@RequestMapping(value = "/board/wordAjax.do", method = RequestMethod.GET, produces = "application/text; charset=UTF-8")
	@ResponseBody
	public String wordAjax(String keyword, Model model) {
		System.out.println("keyword" + keyword);
		List<Board_Dto> dto = dao.searchBoard(keyword);
		
		System.out.println("dto" + dto);
		JSONArray jlist = new JSONArray();
		Paging paging = new Paging();
//		paging.calculation(dto.size(), 8, 5, 1);

		if (dto.size() == 0) {
			System.out.println("0");
		} else {
			System.out.println("1이상");
			for (int i = 0; i < dto.size(); i++) {
				JSONObject jobj = new JSONObject();
				jobj.put("boardseq", dto.get(i).getBoardseq());
				jobj.put("userid", dto.get(i).getUserid());
				jobj.put("title", dto.get(i).getTitle());
				jobj.put("content", dto.get(i).getContent());
				jlist.add(jobj);
			}
		}
		return jlist.toString();
	}

	// 파일 다운로드
	@RequestMapping(value = "/board/fileDown.do", method = RequestMethod.POST)
	public void fileDown(@RequestParam("fileNo") String f_seq, HttpServletResponse response) throws Exception {
		FileBoardDto filedown = file.getFileBoardDownload(f_seq); // 파일 다운로드
		String storedFileName = filedown.getStored_fname(); // 파일 다운로드 uuid 이름
		String originalFileName = filedown.getOrigin_fname(); // 파일 다운로드 원본이름

		// 파일을 저장했던 위치에서 첨부파일을 읽어 byte[]형식으로 변환한다.
		byte fileByte[] = FileUtils.readFileToByteArray(new File("C:\\test_file\\" + storedFileName));

		response.reset();
		response.setContentType("application/octet-stream");

		response.setContentLength(fileByte.length);
		// 한글 인코딩
		String encoding = new String(originalFileName.getBytes("UTF-8"), "8859_1"); // 8859_1이 MS949에 해당되는 인코딩 방식
		// 리터럴 값을 대입

		// 파일 다운로드 버튼을 클릭하면 다운로드 저장화면이 나오도록 처리
		response.setHeader("Content-Disposition", "attachment; filename=\"" + encoding); // 브라우저에 어떻게 표시할지에 대한
		// 파일네임을 헤더정보 어태치에 붙여서 보내줌

		response.getOutputStream().write(fileByte);
		response.getOutputStream().flush();
		response.getOutputStream().close();
	}
	
	/**
	 * 영서 임시*/
	
	@RequestMapping(value = "/board/boardgong.do", method = RequestMethod.GET)
	public String boardgong(HttpServletRequest req, Model model, Principal principal) {
		logger.info("BoardController board 입장");
		String page = req.getParameter("page");
		if (page == null) {
			page = "1";
		}
		String auth = authService.selectUserAuth(principal.getName()).getAuth();
		model.addAttribute("auth", auth);
		
		
		int selectPage = Integer.parseInt(page);
		System.out.println("현재 페이지 :" + selectPage);

		Paging p = new Paging();

		// 총 게시물의 개수
		p.setTotalCount(pageDao.countMyBoard());

		// 출력될 게시물의 개수
		p.setCountList(10);

		// 화면에 몇 개의 페이지를 보여줄지.(그룹)
		p.setCountPage(10);

		// 총 페이지 개수
		p.setTotalPage(p.getTotalCount()); // set이 있어야 들어감

		// 현재 페이지의 번호
		p.setPage(selectPage);

		// 시작 번호
		p.setStartPage(selectPage);

		// 끝 번호
		p.setEndPage(p.getCountPage());

		// 게시글을 가져옴
		Map<String, Object> map = new HashMap<String, Object>();

		// (p.getPage()-1)*getCountList+1
		map.put("first", p.getPage() * p.getCountList() - (p.getCountList() - 1));
		map.put("last", p.getPage() * p.getCountList());

		List<Board_Dto> list = pageDao.pageList(map);

		// 게시글, 페이지
		model.addAttribute("lists", list);
		model.addAttribute("page", p);
		return "board/boardgong";
	}
	
	@RequestMapping(value = "/board/boardJaju.do", method = RequestMethod.GET)
	public String boardJaju(HttpServletRequest req, Model model, Principal principal) {
		logger.info("BoardController board 입장");
		String page = req.getParameter("page");
		if (page == null) {
			page = "1";
		}
		String auth = authService.selectUserAuth(principal.getName()).getAuth();
		model.addAttribute("auth", auth);
		
		
		int selectPage = Integer.parseInt(page);
		System.out.println("현재 페이지 :" + selectPage);

		Paging p = new Paging();

		// 총 게시물의 개수
		p.setTotalCount(pageDao.countMyBoard());

		// 출력될 게시물의 개수
		p.setCountList(10);

		// 화면에 몇 개의 페이지를 보여줄지.(그룹)
		p.setCountPage(10);

		// 총 페이지 개수
		p.setTotalPage(p.getTotalCount()); // set이 있어야 들어감

		// 현재 페이지의 번호
		p.setPage(selectPage);

		// 시작 번호
		p.setStartPage(selectPage);

		// 끝 번호
		p.setEndPage(p.getCountPage());

		// 게시글을 가져옴
		Map<String, Object> map = new HashMap<String, Object>();

		// (p.getPage()-1)*getCountList+1
		map.put("first", p.getPage() * p.getCountList() - (p.getCountList() - 1));
		map.put("last", p.getPage() * p.getCountList());

		List<Board_Dto> list = pageDao.pageList(map);

		// 게시글, 페이지
		model.addAttribute("lists", list);
		model.addAttribute("page", p);
		return "board/boardJaju";
	}
	@RequestMapping(value = "/board/boardMon.do", method = RequestMethod.GET)
	public String boardMon(HttpServletRequest req, Model model, Principal principal) {
		logger.info("BoardController board 입장");
		String page = req.getParameter("page");
		if (page == null) {
			page = "1";
		}
		String auth = authService.selectUserAuth(principal.getName()).getAuth();
		model.addAttribute("auth", auth);
		
		
		int selectPage = Integer.parseInt(page);
		System.out.println("현재 페이지 :" + selectPage);

		Paging p = new Paging();

		// 총 게시물의 개수
		p.setTotalCount(pageDao.countMyBoard());

		// 출력될 게시물의 개수
		p.setCountList(10);

		// 화면에 몇 개의 페이지를 보여줄지.(그룹)
		p.setCountPage(10);

		// 총 페이지 개수
		p.setTotalPage(p.getTotalCount()); // set이 있어야 들어감

		// 현재 페이지의 번호
		p.setPage(selectPage);

		// 시작 번호
		p.setStartPage(selectPage);

		// 끝 번호
		p.setEndPage(p.getCountPage());

		// 게시글을 가져옴
		Map<String, Object> map = new HashMap<String, Object>();

		// (p.getPage()-1)*getCountList+1
		map.put("first", p.getPage() * p.getCountList() - (p.getCountList() - 1));
		map.put("last", p.getPage() * p.getCountList());

		List<Board_Dto> list = pageDao.pageList(map);

		// 게시글, 페이지
		model.addAttribute("lists", list);
		model.addAttribute("page", p);
		return "board/boardMon";
	}
}
