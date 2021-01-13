package com.min.edu.ctrl;

import java.security.Principal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

import javax.servlet.http.HttpServletRequest;

import org.apache.coyote.Request;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.google.gson.JsonArray;
import com.min.edu.commons.utils.AddressCode_Mapper;
import com.min.edu.commons.utils.ExecuteUsingQuartz;
import com.min.edu.dto.CenterDto;
import com.min.edu.dto.StudentDto;
import com.min.edu.dto.TrainstMemberDto;
import com.min.edu.info.UserInfo;
import com.min.edu.service.IServiceAuth;
import com.min.edu.service.IServiceHrd;
import com.min.edu.service.IServiceUser;
import com.min.edu.vo.HRD_Trainst_Info_Vo;
import com.min.edu.vo.HRD_View_Vo;

@Controller
public class LoginController {
	private static final Logger logger = LoggerFactory.getLogger(LoginController.class);

	@Autowired
	private IServiceUser userService;
	
	@Autowired
	private IServiceAuth authService;
	
	@Autowired
	private IServiceHrd hrdService;
	
	
	@RequestMapping(value="/login/loginForm.do", method =RequestMethod.GET)
	public String loginForm() {
		logger.info("welcome LoginForm ! " );
		return "login/loginForm";
	}
	@RequestMapping(value="/login/searchTrainst.do", method =RequestMethod.GET)
	public String searchTrainst() {
		logger.info("welcome searchTrainst ! " );
		return "login/searchTrainst";
	}
	
	@RequestMapping(value="/login/phoneidentify.do", method =RequestMethod.GET)
	public String phoneidentify() {
		logger.info("welcome phoneidentify ! " );
		return "login/phoneidentify";
	}
	
	@RequestMapping(value="/login/signUpForm.do", method =RequestMethod.GET)
	public String signUpForm() {
		logger.info("welcome signupForm ! " );
		return "login/signUpForm";
	}
	@RequestMapping(value="/testMap.do", method =RequestMethod.GET)
	public String testMap(Model model, String addr1) {
		logger.info("welcome testMap ! " );
		
		List<String> addrs = new ArrayList<String>();
		
		
		return "login/testMap";
	}
	
	
	@RequestMapping(value="/login/signUpStudent.do", method = RequestMethod.GET)
	public String signUpStudent(StudentDto dto) {
		logger.info("welcome signUpStudent ! {}", dto );
		
		userService.signUpStudent(dto);

		return "login/loginForm";
	}
	@RequestMapping(value="/login/signUpAcademy.do", method = RequestMethod.GET)
	public String signUpAcademy(TrainstMemberDto dto) {
		logger.info("welcome signUpAcademy ! {}", dto );
		
		userService.signUpAcademy(dto);
		
		return "login/loginForm";
	}
	@RequestMapping(value="/login/accessterm.do", method = RequestMethod.GET)
	public String accessterm() {
		logger.info("welcome accessterm ! ");
		
		
		return "login/accessterm";
	}
	
	@RequestMapping(value="/login/signUpCenter.do", method = RequestMethod.GET)
	public String signUpCenter(CenterDto dto) {
		logger.info("welcome signUpCenter ! {}", dto );
		dto.setCen_name("우리집");
		dto.setCen_phone("01089102198");
		userService.signUpCenter(dto);
//		System.out.println(AddressCode_Mapper.AddressCodeMapper( dto.getAddr1()));
		return "login/loginForm";
	}
	
	@RequestMapping(value ="/login/idDuplChk.do", method = RequestMethod.GET , produces = "application/text; charset=UTF-8;")
	@ResponseBody
	public String idDuplChk(String id) {
		logger.info("welcome idDuplChk ! {}",id );
		String regex = "^[a-zA-Z]{1}[a-zA-Z0-9_]{8,15}$";
		boolean isc = id.matches(regex);		
		String result =	userService.idDuplChk(id);
//		System.out.println(result == null ? "널" : "안널");
		String f_prev ="<span style='color:red;'>";
		String s_prev ="<span style='color:blue ;'>";
		String back ="</span>";
		String r = (isc == true && result == null )? s_prev+"사용 가능한 아이디"+back :  f_prev+"사용 불가능한 아이디"+back;
		logger.info("idDuplChk isc : {} \t r : {} ",isc, r);
		return r;
	}
	
	
	@RequestMapping(value ="/login/passwordregex.do", method = RequestMethod.GET , produces = "application/text; charset=UTF-8;")
	@ResponseBody
	public String passwordregex(String password) {
		logger.info("welcome passwordregex ! {}",password );
		String regex = "^[A-Za-z0-9!@#$%^&*]{6,15}$";
		boolean isc = password.matches(regex);		
//		System.out.println(result == null ? "널" : "안널");
		String f_prev ="<span style='color:red;'>";
		String s_prev ="<span style='color:blue ;'>";
		String back ="</span>";
		String r = (isc !=true )? f_prev+"비밀번호는 6-15자 영문과 특수문자만 사용가능합니다."+back: s_prev+"사용 가능한 비밀번호"+back;
		return r;
	}
	
	
	@SuppressWarnings("unchecked")
	@RequestMapping(value ="/login/selectOneUser_dynamic.do", method = RequestMethod.GET , produces = "application/text; charset=UTF-8;")
	@ResponseBody
	public String selectOneUser_dynamic(String name) {
		logger.info("welcome selectOneUser_dynamic ! {}",name );
		Map<String, String> map = new HashMap<String, String>();
		map.put("name", name);
		List<StudentDto> lists = userService.selectOneUser_dynamic(map);
		JSONObject jobj = new JSONObject();
		JSONArray jarr = new JSONArray();
		for(StudentDto s : lists) {
			jarr.add(s.getId());
			jarr.add(s.getName());
		}
		jobj.put("result", jarr);
		System.out.println(jarr.toJSONString());
//		System.out.println(result == null ? "널" : "안널");
		return jobj.toJSONString();
	}
	@SuppressWarnings("unchecked")
	@RequestMapping(value ="/login/sendIdentifyMsg.do", method = RequestMethod.GET , produces = "application/text; charset=UTF-8;")
	@ResponseBody
	public String sendIdentifyMsg(String phone) {
		logger.info("welcome sendIdentifyMsg  ! phone {}\t status {}",phone );
		
		String number = "";
		
		for(int i =0; i<6; i++) {
			 number += String.valueOf(((int)(Math.random()*9)));
			 System.out.println(number);
		}
		
		logger.info("sendIdentifyMsg chk val {}", number);
		
		//여기서 메시지를 보내면 된다.
		ExecuteUsingQuartz msgSender = new ExecuteUsingQuartz();
		String massage = "인증번호는 "+number+"입니다";
		msgSender.sendMsg(phone, massage);
		
		return number;
		
	}
	@SuppressWarnings("unchecked")
	@RequestMapping(value ="/login/comPareIdentifyNumber.do", method = RequestMethod.GET , produces = "application/text; charset=UTF-8;")
	@ResponseBody
	public String comPareIdentifyNumber(String myNum, String receiveNum) {
		logger.info("welcome comPareIdentifyNumber  ! phone {}\t status {}",myNum,receiveNum );
			//여기서 메시지를 보내면 된다.
		return (myNum.equals(receiveNum))? "succ":"fail"; 
		
	}
	@SuppressWarnings("unchecked")
	@RequestMapping(value ="/login/doSearch.do", method = RequestMethod.GET , produces = "application/text; charset=UTF-8;")
	@ResponseBody
	public String doSearch(String trainst_cst_id) {
		logger.info("welcome doSearch  ! trainst_cst_id {}",trainst_cst_id );
		String ino_nm = hrdService.selectTrainst_for_SignUp(trainst_cst_id);
		return (ino_nm.length()>1)? ino_nm : "fail" ; 
		
	}
	
	
	@RequestMapping(value="/test.do" ,method = RequestMethod.GET)
	public String test(Principal principal) {
		logger.info("welcome test ! " );
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication(); 
		UserInfo user = (UserInfo) authentication.getPrincipal();
		
		System.out.println("name : "+user.getUsername() +", name : " + user.getUsername() +", auth :" + user.getAuthorities());
		
		System.out.println(	userService.selectOneUser(user.getUsername()).getName() );
		
		return "redirect:/home.do";
	}
}
