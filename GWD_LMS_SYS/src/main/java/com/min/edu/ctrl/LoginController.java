package com.min.edu.ctrl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class LoginController {
	private static final Logger logger = LoggerFactory.getLogger(LoginController.class);

	@RequestMapping(value="/login/loginForm.do", method =RequestMethod.GET)
	public String LoginForm() {
		logger.info("welcome LoginForm ! " );
		return "login/loginForm";
	}
	
	@RequestMapping(value="/login/signUpForm.do", method =RequestMethod.GET)
	public String signupForm() {
		logger.info("welcome signupForm ! " );
		return "login/signUpForm";
	}
}
