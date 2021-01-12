package com.min.edu.ctrl;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.PDPageContentStream;
import org.apache.pdfbox.pdmodel.graphics.image.PDImageXObject;
import org.apache.tomcat.util.codec.binary.Base64;
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
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.min.edu.dto.AuthorizationDocumentDto;
import com.min.edu.dto.AuthorizationFileDto;
import com.min.edu.dto.AuthorizationGroupDto;
import com.min.edu.dto.AuthorizationStampDto;
import com.min.edu.dto.Paging;
import com.min.edu.dto.StudentDto;
import com.min.edu.info.UserInfo;
import com.min.edu.service.IServiceAuthorization;
import com.min.edu.service.IServiceUser;


@Controller
public class AuthorizationController {
	private static final Logger logger = LoggerFactory.getLogger(AuthorizationController.class);
	
	@Autowired
	private IServiceAuthorization authorization;
	
	@Autowired
	private IServiceUser userSearch;
	
	// 문서 메인
	@RequestMapping(value = "/authorizationMain.do",method = RequestMethod.GET)
	public String authorizationMainMove(Model model) {
		logger.info("authorizationMainMove : {}",new Date());
		Map<String,Object> map = new HashMap<String, Object>();
		
		// USER 정보 담기
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication(); 
		UserInfo user = (UserInfo) authentication.getPrincipal();
		
		// paging 처리 및 처리완료 게시글 최상단 3개 불러오기
		map.put("id",user.getUsername());
		map.put("branch", "inComplete");
		Paging p = new Paging();
		p.calculation(Integer.parseInt(authorization.getDocumentBranchCount(map)), 3, 1, 1);
		map.put("start", p.getFirst());
		map.put("end", p.getLast());
		model.addAttribute("incomplete",authorization.getDocumentBranch(map));

		// paging 처리 및 미처리완료 게시글 최하단 3개 불러오기
		map.put("branch", "complete");
		p.calculation(Integer.parseInt(authorization.getDocumentBranchCount(map)), 3, 1, 1);
		map.put("start", p.getFirst());
		map.put("end", p.getLast());
		model.addAttribute("complete",authorization.getDocumentBranch(map));
		return "authorization/authorizationMain";
	}
	
	// 문서 상세 보기
	@RequestMapping(value = "/documentDetail.do",method = RequestMethod.GET)
	public String documentDetail(@RequestParam("seq") String authorization_seq,  @RequestParam Map<String,Object> map,Model model) {
		logger.info("documentDetail : {}",map);
		// USER 정보 담기
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication(); 
		UserInfo user = (UserInfo) authentication.getPrincipal();
		map.remove("seq");
		map.put("id", user.getUsername());
		map.put("authorization_seq", authorization_seq);
		authorization.setGroupReadModify(map);
		AuthorizationDocumentDto dto = authorization.getDocumentDetail(map);
		System.out.println(dto);
		List<String> groupList = authorization.getGroupSelectAll(dto.getAuthorization_seq());
		System.out.println(groupList);
		if(dto.getFileflag().equals("Y")) {
			List<AuthorizationFileDto> fileList = authorization.getDocumentFileSelect(dto.getAuthorization_seq());
			System.out.println(fileList);
			model.addAttribute("fileList",fileList);
		}
		logger.info("documentDetail : {}",dto);
		model.addAttribute("authorization",dto);
		model.addAttribute("groupList",groupList);
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
	
	// 아이디 검색
	@RequestMapping(value = "/searchIdName.do",method = RequestMethod.GET)
	@ResponseBody
	public Map<String, Object> searchIdName(@RequestParam("search") String id) {
		System.out.println(id);
		Map<String, Object> map = new HashMap<String, Object>();
		Map<String, String> mapI = new HashMap<String, String>();
		mapI.put("id",id);
		System.out.println(map);
		List<StudentDto> lists = userSearch.selectOneUser_dynamic(mapI);
		for(StudentDto list : lists) {
			map.put(list.getId(),list.getName());
		}
		
		System.out.println(lists);
		
		return map;
	}
	
	// 문서 작성
	@RequestMapping(value = "/documentWrite.do",method = RequestMethod.POST)
	public String documentWrite(AuthorizationDocumentDto dto,@RequestParam("gPersen") String[] group,@RequestParam("gStatus") String[] status, MultipartHttpServletRequest mpRequest) throws Exception {
		logger.info("documentWrite : {}",dto);
		logger.info("documentWrite : {}",Arrays.toString(group));
		logger.info("documentWrite : {}",(mpRequest.getFiles("file")).get(0).isEmpty());
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication(); 
		UserInfo user = (UserInfo) authentication.getPrincipal();
		
		dto.setId(user.getUsername());
		dto.setTemplate_type("휴가");
		if((mpRequest.getFiles("file")).get(0).isEmpty()) {
			dto.setFileflag("N");
		} else {
			dto.setFileflag("Y");
		}
		
		List<AuthorizationGroupDto> chage = new ArrayList<AuthorizationGroupDto>();
		AuthorizationGroupDto g = new AuthorizationGroupDto(dto.getId(), "승인");
		chage.add(g);
		for (int i = 0; i < group.length; i++) {
			chage.add(new AuthorizationGroupDto(group[i], status[i]));
		}
		
		
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("aDocumentDto",dto); 
		map.put("aGroupDto",chage); 
		
		authorization.setDocumentInsert(map, mpRequest);
		return "redirect:/authorizationBranch.do?branch=complete";
	}
	
	// 문서반려 페이지로 이동
//	@RequestMapping(value = "/documentModifyMove.do",method = RequestMethod.POST)
//	public String documentModifyMove(@RequestParam Map<String,Object> map, Model model) throws Exception {
//		logger.info("documentWrite : {}",map);
//		AuthorizationDocumentDto dto = authorization.getDocumentDetail(map);
//		List<String> groupList = authorization.getGroupSelectAll(dto.getAuthorization_seq());
//		List<AuthorizationFileDto> fileList = authorization.getDocumentFileSelect(dto.getAuthorization_seq());
//		model.addAttribute("authorization",dto);
//		model.addAttribute("groupList",groupList);
//		model.addAttribute("fileList",fileList);
//		return "authorization/authorizationModify";
//	}
	
	// 문서 반려
	@RequestMapping(value = "/documentModify.do",method = RequestMethod.POST)
	public String documentModify(@RequestParam("seq") String authorization_seq, @RequestParam Map<String,Object> map) {
		map.remove("seq");
		logger.info("documentModify : {}",map);
		logger.info("documentModify : {}",authorization_seq);
		map.put("authorization_seq", authorization_seq);
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication(); 
		UserInfo user = (UserInfo) authentication.getPrincipal();
		
		map.put("id",user.getUsername());
		authorization.setDocumentModify(map);
		
		map.put("authorized_person",user.getUsername());
		map.put("authorized_status","반려");
		authorization.setGroupStatusModify(map);
		
		
		return "redirect:/documentDetail.do?seq="+authorization_seq;
	}
	
	// 문서 승인
	@RequestMapping(value = "/documentApproved.do",method = RequestMethod.POST)
	public String documentApproved(@RequestParam("seq") String authorization_seq, @RequestParam Map<String,Object> map) throws IOException {
		logger.info("documentApproved : {}",map);
		logger.info("documentApproved : {}",authorization_seq);
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication(); 
		UserInfo user = (UserInfo) authentication.getPrincipal();
		
		map.put("authorization_seq",authorization_seq);
		map.put("authorized_person",user.getUsername());
		map.put("authorized_status","승인");
		authorization.setGroupStatusModify(map);
		if(authorization.getDocumentToPdf(authorization_seq) == 0) {
			String binaryData = (String)map.get("last");
			FileOutputStream stream = null;
			String fileName=  UUID.randomUUID().toString();
			try{
				System.out.println("binary file   "  + binaryData);
				if(binaryData == null || binaryData.trim().equals("")) {
				    throw new Exception();
				}
				binaryData = binaryData.replaceAll("data:image/png;base64,", "");
				byte[] file = Base64.decodeBase64(binaryData);
				
				stream = new FileOutputStream("C:/test_file/"+"1111"+fileName+".png");
				stream.write(file);
				stream.close();
				System.out.println("캡처 저장");
			    
			}catch(Exception e){
				e.printStackTrace();
				System.out.println("에러 발생");
			}finally{
				if(stream != null) {
					stream.close();
				}
			}
			
			
			String imagePath = "C:/test_file/"+"1111"+fileName+".png";
	        String pdfPath = "C:/test_file/"+"1111"+fileName+".pdf";
//	         
	        if (!pdfPath.endsWith(".pdf"))
	        {
	            System.err.println("Last argument must be the destination .pdf file");
	            System.exit(1);
	        }
	 
	        PDDocument doc = new PDDocument();
	        try
	        {
	 
	         File imgDir = new File(imagePath);
//	         File[] imgFiles = imgDir.listFiles();
//	         for(int i=1; i<=imgFiles.length; i++) {
	            PDPage page = new PDPage();
	            doc.addPage(page);
	             
	            // capture한 이미지 이름이 1.jpg, 2.jpg 이런식으로 되어있음.
	            PDImageXObject pdImage = PDImageXObject.createFromFile(imagePath, doc);
	             
	            PDPageContentStream contents = new PDPageContentStream(doc, page);
	             
	            contents.drawImage(pdImage, 0, 0, 590, 590);
	             
	            contents.close();
	            doc.save(pdfPath);
//	            System.out.print(i+" ");
//	            if(i%50 == 0) System.out.println("");
//	         }
	        }
	        finally
	        {
	            doc.close();
	            System.out.println("");
	            System.out.println("fin");
	        }
		}
		
		return "redirect:/documentDetail.do?seq="+authorization_seq;
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
	
	// 결재 수단 등록 페이지 open으로 열기
	@RequestMapping(value = "/stamp.do",method = RequestMethod.GET)
	public String stamp(Model model) {
		logger.info("stamp : {}",new Date());
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication(); 
		UserInfo user = (UserInfo) authentication.getPrincipal();
		
		AuthorizationStampDto stamp = authorization.getStampSelect(user.getUsername());
		if(stamp != null) {
			model.addAttribute("imageFile",stamp.getStamp_image_link());
		}
		return "authorization/stamp";
	}
	
	// 각아이디의 도장 이미지 불러오기
	@RequestMapping(value = "/stampImage.do",method = RequestMethod.GET)
	public String stampInfo(HttpServletResponse response) throws IOException {
		logger.info("stampInfo : {}",new Date());
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication(); 
		UserInfo user = (UserInfo) authentication.getPrincipal();
		
		AuthorizationStampDto stamp = authorization.getStampSelect(user.getUsername());
		if(stamp != null) {
			response.setContentType("image/jpg");
		    ServletOutputStream bout = response.getOutputStream();
			FileInputStream f = new FileInputStream(stamp.getStamp_image_link());
		    int length;
		    byte[] buffer = new byte[10];
		    while((length=f.read(buffer)) != -1){
		    	bout.write(buffer,0,length);
		    }
		}
		
		return null;
	}
	
	// 도장 등록
	@RequestMapping(value = "/stampTest.do",method = RequestMethod.POST)
	public String stampTest(String img, MultipartHttpServletRequest mpRequest) throws IOException {
		logger.info("stampTest : {}",new Date());
		logger.info("stampTest : {}",mpRequest);
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication(); 
		UserInfo user = (UserInfo) authentication.getPrincipal();
		
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("id",user.getUsername());
		if(img == null) {
			authorization.setStampInsert(map,mpRequest);
		} else {
			authorization.setStampModify(map, mpRequest);
		}
		return "redirect:/authorizationMain.do";
	}
	
}
