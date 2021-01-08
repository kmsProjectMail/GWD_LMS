package com.min.edu.ctrl;

import java.security.Principal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
	public String LoginForm() {
		logger.info("welcome LoginForm ! " );
		return "login/loginForm";
	}
	
	@RequestMapping(value="/login/signUpForm.do", method =RequestMethod.GET)
	public String signUpForm() {
		logger.info("welcome signupForm ! " );
		return "login/signUpForm";
	}
	@RequestMapping(value="/login/testMap.do", method =RequestMethod.GET)
	public String testMap(Model model, String addr1) {
		logger.info("welcome testMap ! " );
		
		List<String> addrs = new ArrayList<String>();
		
		
		return "login/testMap";
	}
	
	
	@RequestMapping(value="/login/signUpStudent.do", method = RequestMethod.POST)
	public String signUpStudent(StudentDto dto) {
		logger.info("welcome signUpStudent ! {}", dto );
		
		userService.signUpStudent(dto);

		return "login/loginForm";
	}
	@RequestMapping(value="/login/signUpAcademy.do", method = RequestMethod.POST)
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
	
	@RequestMapping(value="/login/signUpCenter.do", method = RequestMethod.POST)
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
		String regex = "^[a-zA-Z0-9]*$";
		boolean isc = id.matches(regex);		
		String result =	userService.idDuplChk(id);
//		System.out.println(result == null ? "널" : "안널");
		return (isc ==true && result != null )? "사용 불가능한 아이디": "사용 가능한 아이디";
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
