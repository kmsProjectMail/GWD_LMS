package com.min.edu.ctrl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.min.edu.commons.utils.AddressCode_Mapper;
import com.min.edu.dto.CenterDto;
import com.min.edu.dto.StudentDto;
import com.min.edu.dto.TrainstMemberDto;
import com.min.edu.service.IServiceUser;

@Controller
public class LoginController {
	private static final Logger logger = LoggerFactory.getLogger(LoginController.class);

	@Autowired
	private IServiceUser userService;
	
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
	@RequestMapping(value="/login/signUpCenter.do", method = RequestMethod.POST)
	public String signUpCenter(CenterDto dto) {
		logger.info("welcome signUpCenter ! {}", dto );
		dto.setCen_name("우리집");
		dto.setCen_phone("01089102198");
		userService.signUpCenter(dto);
//		System.out.println(AddressCode_Mapper.AddressCodeMapper( dto.getAddr1()));
		return "login/loginForm";
	}
}
