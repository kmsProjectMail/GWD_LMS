package com.min.edu;

import java.security.Principal;
import java.text.DateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.min.edu.commons.utils.AlarmPopUpMaker;
import com.min.edu.dto.CalendarDto;
import com.min.edu.service.IServiceCalendar;

/**
 * Handles requests for the application home page.
 */
@Controller
public class HomeController {
	
	private static final Logger logger = LoggerFactory.getLogger(HomeController.class);
	
	@Autowired
	private IServiceCalendar calendarService;
	
	/**
	 * Simply selects the home view to render by returning its name.
	 */
	@RequestMapping(value = "/home.do", method = RequestMethod.GET)
	public String home(Locale locale, Model model) {
		logger.info("Welcome home! The client locale is {}.", locale);
		
		Date date = new Date();
		DateFormat dateFormat = DateFormat.getDateTimeInstance(DateFormat.LONG, DateFormat.LONG, locale);
		
		String formattedDate = dateFormat.format(date);
		
		model.addAttribute("serverTime", formattedDate );
		
		return "home";
	}
	
	@RequestMapping(value="/login/accessDenied.do" ,method = {RequestMethod.GET, RequestMethod.POST})
	public String denied() {
		return "/login/accessDenied";
	}
	
	
	@RequestMapping(value="/all/alarmboard.do" ,method = {RequestMethod.GET, RequestMethod.POST})
	public String alarmboard() {
		return "/all/alarmboard";
	}
	
	
	
	@RequestMapping(value="/getMyList.do" ,method = RequestMethod.GET, produces = "application/text; charset=UTF-8")
	@ResponseBody
	public String getMyList(Principal principal, String num) {
		logger.info("Welcome getMyList principal.getName() {}.", principal.getName());

		List<CalendarDto> lists =  calendarService.getOneList(principal.getName());
		

		return 	AlarmPopUpMaker.alarmMaker(lists, Integer.parseInt(num), principal.getName());
	}
	
	
	
	
	
}
