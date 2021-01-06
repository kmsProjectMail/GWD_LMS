package com.min.edu.ctrl;

import java.io.File;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.min.edu.dto.AuthorizationDocumentDto;
import com.min.edu.dto.AuthorizationFileDto;
import com.min.edu.service.IServiceAuthorization;

@Controller
public class AuthorizationController {
	private static final Logger logger = LoggerFactory.getLogger(AuthorizationController.class);
	
	@Autowired
	private IServiceAuthorization authorization;
	
	// 문서 메인
	@RequestMapping(value = "/authorizationMain.do",method = RequestMethod.GET)
	public String authorizationMainMove(Model model) {
		logger.info("authorizationMainMove : {}",new Date());
		Map<String,Object> map = new HashMap<String, Object>();
		map.put("id","STUDENT01");
		map.put("branch", "inComplete");
		map.put("start", "1");
		map.put("end", "3");
		model.addAttribute("complete",authorization.getDocumentBranch(map));
		map.put("branch", "complete");
		model.addAttribute("incomplete",authorization.getDocumentBranch(map));
		return "authorization/authorizationMain";
	}
	
	// 문서 상세 보기
	@RequestMapping(value = "/documentDetail.do",method = RequestMethod.GET)
	public String documentDetail(@RequestParam Map<String,Object> map,Model model) {
		logger.info("documentDetail : {}",map);
		AuthorizationDocumentDto dto = authorization.getDocumentDetail(map);
		List<String> groupList = authorization.getGroupSelectAll(dto.getAuthorization_seq());
		List<AuthorizationFileDto> fileList = authorization.getDocumentFileSelect(dto.getAuthorization_seq());
		model.addAttribute("authorization",dto);
		model.addAttribute("groupList",groupList);
		model.addAttribute("fileList",fileList);
		return "authorization/authorizationDetail";
	}
	
	// 처리 보관, 미처리 보관, 검색 조회
	@RequestMapping(value = "/authorizationBranch.do",method = RequestMethod.GET)
	public String authorizationBranch(Model model,String branch) {
		logger.info("authorizationBranch : {}",branch);
		Map<String,Object> map = new HashMap<String, Object>();
		map.put("id","STUDENT01");
		if(branch.equals("inComplete")) {
			map.put("branch", "inComplete");
			map.put("start", "1");
			map.put("end", "3");
			model.addAttribute("complete",authorization.getDocumentBranch(map));
		} else if(branch.equals("complete")) {
			map.put("branch", "complete");
			map.put("start", "1");
			map.put("end", "3");
			model.addAttribute("incomplete",authorization.getDocumentBranch(map));
		} else if(branch.equals("search")) {
			map.put("searchId", "STUDENT01");
			map.put("start", "1");
			map.put("end", "3");
			model.addAttribute("incomplete",authorization.getDocumentBranch(map));
		}
		return "authorization/authorizationBranch";
	}
	
	// 문서 작성 페이지로 이동
	@RequestMapping(value = "/documentWriteMove.do",method = RequestMethod.GET)
	public String documentWrite() {
		logger.info("documentWriteMove : {}",new Date());
		return "authorization/authorizationWrite";
	}
	
	// 문서 작성
	@RequestMapping(value = "/documentWrite.do",method = RequestMethod.POST)
	public String documentWrite(@RequestParam Map<String,Object> map, MultipartHttpServletRequest mpRequest, Model model) throws Exception {
		logger.info("documentWrite : {}",map);
		authorization.setDocumentInsert(map, mpRequest);
		return "redirect:/authorizationBranch.do?branch=complete";
	}
	
	// 문서반려 페이지로 이동
	@RequestMapping(value = "/documentModifyMove.do",method = RequestMethod.POST)
	public String documentModifyMove(@RequestParam Map<String,Object> map, Model model) throws Exception {
		logger.info("documentWrite : {}",map);
		AuthorizationDocumentDto dto = authorization.getDocumentDetail(map);
		List<String> groupList = authorization.getGroupSelectAll(dto.getAuthorization_seq());
		List<AuthorizationFileDto> fileList = authorization.getDocumentFileSelect(dto.getAuthorization_seq());
		model.addAttribute("authorization",dto);
		model.addAttribute("groupList",groupList);
		model.addAttribute("fileList",fileList);
		return "authorization/authorizationModify";
	}
	
	// 문서 반려
	@RequestMapping(value = "/documentModify.do",method = RequestMethod.POST)
	public String documentModify(@RequestParam Map<String,Object> map, Model model) throws Exception {
		logger.info("documentWrite : {}",map);
		authorization.setDocumentModify(map);
		return "redirect:/authorizationBranch.do?branch=complete";
	}
	
	// 파일 다운로드
	@RequestMapping(value = "/documentFileDownload.do",method = RequestMethod.POST)
	public void fileDown(@RequestParam("fileNo") String file_seq, HttpServletResponse response) throws Exception{
		AuthorizationFileDto filedown = authorization.getDocumentFileDownload(file_seq); // 파일 다운로드
		String storedFileName = filedown.getFile_stored_fname(); // 파일 다운로드 uuid 이름
		String originalFileName = filedown.getFile_origin_fname(); // 파일 다운로드 원본이름
		
		// 파일을 저장했던 위치에서 첨부파일을 읽어 byte[]형식으로 변환한다.
		byte fileByte[] = org.apache.commons.io.FileUtils.readFileToByteArray(new File("C:\\test_file\\"+storedFileName));
	
		response.reset(); // 브라우저로 응답할때 값을 초기화 함 / 그전에 response 했던것들 전부 지워줌
		// 전달하는 파일의 특성에 맞춰 헤더정보를 변경
		// application/octet-stream 파일이 어떤 특징인지 모를때 / 워드 application / msword
		response.setContentType("application/octet-stream");
		
		response.setContentLength(fileByte.length);
		// 한글 인코딩
		String encoding = new String(originalFileName.getBytes("UTF-8"),"8859_1"); // 8859_1이 MS949에 해당되는 인코딩 방식
		// 리터럴 값을 대입
		
		
		// 파일 다운로드 버튼을 클릭하면 다운로드 저장화면이 나오도록 처리
		response.setHeader("Content-Disposition", "attachment; filename=\""+encoding); // 브라우저에 어떻게 표시할지에 대한 
		// 파일네임을 헤더정보 어태치에 붙여서 보내줌
		
//			response.setHeader("Content-Disposition",  "attachment; fileName=\""+URLEncoder.encode(originalFileName, "UTF-8")+"\";");
		response.getOutputStream().write(fileByte);
		response.getOutputStream().flush();
		response.getOutputStream().close();
		
	}
}
