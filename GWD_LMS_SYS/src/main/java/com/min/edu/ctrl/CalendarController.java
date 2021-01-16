package com.min.edu.ctrl;

import java.security.Principal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.ParseException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.min.edu.dto.CalendarDto;
import com.min.edu.dto.MemberAuthDto;
import com.min.edu.dto.StudentDto;
import com.min.edu.service.IServiceAuth;
import com.min.edu.service.IServiceCalendar;
import com.min.edu.service.IServiceUser;

@Controller
public class CalendarController {
	
	private static final Logger logger = LoggerFactory.getLogger(CalendarController.class);
	
	@Autowired
	private IServiceCalendar iService;
	
	@Autowired
	private IServiceAuth aService;
	
	@RequestMapping(value = "/calendar/calendar.do",method = RequestMethod.GET)
	public String calendar(Principal principal, Model model) {
		logger.info("면담 캘린더로");
		
		MemberAuthDto mDto = aService.selectUserAuth(principal.getName());
		logger.info("mDto?????????????:{} \t",mDto);
		model.addAttribute("mDto", mDto);
		return "calendar/calendar";
	}
	
	//면담 일정 로드
	@SuppressWarnings("unchecked")
	@RequestMapping(value="/calendar/load.do",method = RequestMethod.POST, produces = "application/text; charset=UTF-8")
	@ResponseBody
	public String load() throws ParseException{
		
		JSONArray jlist = new JSONArray();
		CalendarDto dto = null;
		
		List<CalendarDto> lists = iService.selectMeet();
		
		if (lists == null || lists.size() == 0) {
			logger.info("nothing found to load");
		}else {
			logger.info("lists 값:\t {}",lists);
			
			for (int i = 0; i < lists.size(); i++) {
				dto = lists.get(i);
				JSONObject jdto = new JSONObject();
				jdto.put("id", dto.getId());
				jdto.put("calendarId", dto.getCalendar_id());
				jdto.put("content", dto.getContent());      
				jdto.put("title", dto.getTitle());      
				jdto.put("category", dto.getCategory());
				jdto.put("center", dto.getCenter());
				jdto.put("start", dto.getStart());      
				jdto.put("end", dto.getEnd()); 
				jdto.put("alarmDate", dto.getAlarm_date()); 
				jlist.add(jdto);		
		}
			logger.info("jlist??????????????????????: \t"+jlist.toString());
		}
		return jlist.toString();
	}
	
	//면담 일정 수정 // 내가 쓴 것만 //면담 리스트때 권한 바꾸기
	@SuppressWarnings("unchecked")
	@RequestMapping(value = "/calendar/update.do",method = RequestMethod.POST)
	@ResponseBody
	public String update(String id, String calendarId, String title, String content,String center,String cen, String category, String start, Principal principal) {
		JSONObject jObj = new JSONObject();
		if(title.equalsIgnoreCase(principal.getName())) {
			String s = start;
			CalendarDto dto = new CalendarDto();
			StudentDto selDto = iService.selectOneSchedule(id);
			String selS = selDto.getcDto().getStart();
			selS = selS.substring(11, 13);
			
			dto.setId(id);
			dto.setCalendar_id(calendarId);
			dto.setTitle(title);
			if (content==null) {
				dto.setContent(selDto.getcDto().getContent());
				if (selDto.getcDto().getContent() == null) {
					dto.setContent("");
				}
			}else {
				dto.setContent(content);
			}
//			dto.setCategory(category);
//			System.out.println(""+(s+selS));
			if (s.length()==8) {
				dto.setStart(s.concat(selS));
			}else {
				dto.setStart(s);
			}
			dto.setAlarm_date(s);
			if (cen=="0") {
				dto.setMeet_id("CENTER001");
			}else {
				dto.setMeet_id("GOODEE1234");
			}
			dto.setCenter(center);
			logger.info("welcome update : {} \t",dto);
			boolean isc = iService.updateMeet(dto);
			logger.info("update result : {} \t",isc);
			
			jObj.put("id", id);
			jObj.put("calendarId", calendarId);
			jObj.put("title", title);
			jObj.put("content", content);
			jObj.put("cen", cen);
			jObj.put("center", center);
			jObj.put("category", category);
			jObj.put("start", s);
			jObj.put("iMsg", "true");
			
		}else if(title!=principal.getName()) {
			jObj.put("iMsg", "false");
		}
		logger.info(jObj.toString());
		return jObj.toString();
	}
	
	//면담 일정 등록
	@SuppressWarnings("unchecked")
	@RequestMapping(value = "/calendar/save.do",method = RequestMethod.POST, produces = "applicaton/text; charset=UTF-8;")
	@ResponseBody
	public String save(CalendarDto dto,String id, String title, String content,String category, String start, String meet_id, String center) throws ParseException {
		JSONObject json = new JSONObject();
		boolean one = iService.countMeet(start);
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("student_id", title);
		map.put("start", start);
		boolean myOne = iService.countMyMeet(map);
		if (one==false && myOne==false) {
				logger.info("welcome save : {} \t",dto);
				
				String s = start.substring(0, start.length()-2) + "T" +  start.substring(start.length()-2);
			
				dto.setId(id);
				dto.setTitle(title);
				dto.setStart(s);
				dto.setEnd(s);
				dto.setContent(content);
				dto.setAlarm_date(s);
				dto.setStudent_id(title);
				dto.setCategory(category);
				dto.setCenter(center);
				
	//			StudentDto sDto = Service.select(title); //student_id로 select MGR 추가시 바꾸기... etc) STUDENT02-MGR:CENTER001/GOODEE1234} / CENTER001-CUSTOMER:STUDENT02,STUDENT01...
				if (meet_id.equals("0")) {
					dto.setMeet_id("CENTER001");
				}else {
					dto.setMeet_id("GOODEE1234");
				}
				
				logger.info("dto 값은?????????????????????????????: \t"+dto);
				boolean isc = iService.insertMeet(dto);
				
				json.put("id", id);
				json.put("title", title);
				json.put("content", content);
				json.put("category", category);
				json.put("center", center);
				json.put("start", s);
				json.put("end", s);
				json.put("iMsg", "true");
		}else {
			if (myOne==true) {
				json.put("iMsg", "false,myOne");
			}else {
				json.put("iMsg", "false");
			}
		}
		logger.info(json.toString());
		
		return json.toString();
	}
	
	//면담 일정 삭제 // 내가 쓴것만
	@SuppressWarnings("unchecked")
	@RequestMapping(value = "/calendar/delete.do",method = RequestMethod.POST)
	@ResponseBody
	public String delete(CalendarDto dto, String id, String title, Principal principal) {
		JSONObject jObj = new JSONObject();
		
		if (title.equalsIgnoreCase(principal.getName())) {
			dto.setId(id);
			logger.info("삭제될 dto id값은?????????????????????????????: \t"+dto);
			boolean isc = iService.deleteSchedule(id);
			logger.info("isc: {} \t",isc);
			
			jObj.put("id", id);
			jObj.put("iMsg", "true");
			
			logger.info(jObj.toString());
		}else if (title!=principal.getName()) {
			jObj.put("iMsg", "false");
		}
		System.out.println(jObj.toString());
		return jObj.toString();
	}

	//면담 선택
	@SuppressWarnings("unchecked")
	@RequestMapping(value = "/calendar/selectOne.do",method = RequestMethod.POST, produces = "application/text; charset=UTF-8")
	@ResponseBody
	public String selectOne(String id,Principal principal) {
		logger.info("받은 id 값 : {} \t",id);
		StudentDto dto = iService.selectOneSchedule(id);
		
		String s = dto.getcDto().getStart();
		String e = dto.getcDto().getEnd();

		logger.info("selectOne 결과값 : {} \t",dto);
		JSONObject jObj = new JSONObject();
		
		String auth = aService.selectUserAuth(dto.getcDto().getMeet_id()).getAuth();
//		STUDENT001 MGR : CENTER001/GOODEE1234 일때...바꾸기
		if (auth.equals("ROLE_ACADEMY")) {
			jObj.put("cen", 1);
		}else {
			jObj.put("cen", 0);
		}
		jObj.put("id", dto.getId());
		jObj.put("calendarId", dto.getcDto().getCalendar_id());
		jObj.put("content", dto.getcDto().getContent());      
		jObj.put("center", dto.getcDto().getCenter());      
		jObj.put("title", dto.getcDto().getTitle());
		jObj.put("calendarId", dto.getcDto().getCalendar_id());
//		jObj.put("category", dto.getcDto().getCategory());
		jObj.put("alarmDate", dto.getcDto().getAlarm_date());
		
		jObj.put("name", dto.getName());
		jObj.put("phone", dto.getPhone());
		jObj.put("nowId", principal.getName());
		
		int intS = Integer.parseInt(s.substring(11, 13));
		String strS="";
		if (intS < 10) {
			 strS = "0"+intS+":00 AM";
		}else if (intS >= 10 && intS <12) {
			 strS = intS+":00 AM";
		}else if (intS == 12) {
			 strS = intS+ ":00 PM";
		}else if (intS > 12 && intS < 22){
			intS -= 12;
			 strS = "0"+intS+":00 PM";
		}else if (intS > 21 && intS <24) {
			intS -= 12;
			 strS = intS+":00 PM";
		}else if (intS == 24) {
			intS -= 24;
			 strS = "0"+intS+":00 AM";
		}
		
		int intE = Integer.parseInt(e.substring(11, 13));
		String strE="";
		if (intE < 10) {
			 strE = "0"+intE+":00 AM";
		}else if (intE >= 10 && intE <12) {
			 strE = intE+":00 AM";
		}else if (intE == 12) {
			 strE = intE+ ":00 PM";
		}else if (intE > 12 && intE < 22){
			intE -= 12;
			 strE = "0"+intE+":00 PM";
		}else if (intE > 21 && intE <24) {
			intE -= 12;
			 strE = intE+":00 PM";
		}else if (intE == 24) {
			intE -= 24;
			 strE = "0"+intE+":00 AM";
		}
		jObj.put("start", s.substring(0, 11)+strS);
		jObj.put("end", e.substring(0, 11)+strE);
		return jObj.toJSONString();
	}
	//-------------------------------------------------면담 리스트-----------------------------------------------
	
	//면담 리스트 조회 //meet_id가 교육/고용 자신의 아이디일때만
	@SuppressWarnings("unchecked")
	@RequestMapping(value = "/calendar/calendarList.do",method = RequestMethod.GET)
	public String boardList(Model model,Principal principal) {
		List<CalendarDto> lists = iService.selectMeet();
		List<StudentDto> mLists = iService.selectMeetList();
		JSONArray jlist = new JSONArray();
		for (int i = 0; i < lists.size(); i++) {
			JSONObject jobj  = new JSONObject();
			String start = lists.get(i).getStart();
			String s = start.substring(0, 13).concat("시");
			String meet_id = lists.get(i).getMeet_id();
			if (meet_id.equals(principal.getName())) {
				jobj.put("title", lists.get(i).getTitle());
				jobj.put("content", lists.get(i).getContent());
				jobj.put("start", s);
				jobj.put("seq", lists.get(i).getId());
				jobj.put("name", mLists.get(i).getName());
				jobj.put("phone", mLists.get(i).getPhone());
				jlist.add(jobj);
			}
		}
		logger.info("jlist: {} \t",jlist); 
		model.addAttribute("jlist", jlist);
		model.addAttribute("lists", lists);
		return "calendar/calendarList";
	}
	
	//면담 리스트 조회 //meet_id가 교육/고용 자신의 아이디일때만
	@SuppressWarnings("unchecked")
	@RequestMapping(value = "/calendar/loadList.do",method = RequestMethod.POST, produces = "application/text; charset=UTF-8")
	@ResponseBody
	public String loadList(Model model,Principal principal) {
		List<CalendarDto> lists = iService.selectMeet();
		List<StudentDto> mLists = iService.selectMeetList();
		JSONArray jlist = new JSONArray();
		System.out.println(lists);
		System.out.println(mLists);
		for (int i = 0; i < lists.size(); i++) {
			JSONObject jobj  = new JSONObject();
			String start = lists.get(i).getStart();
			String s = start.substring(0, 13).concat("시");
			String meet_id = lists.get(i).getMeet_id();
			if (meet_id.equals(principal.getName())) {
				jobj.put("title", lists.get(i).getTitle());
				jobj.put("content", lists.get(i).getContent());
				jobj.put("start", s);
				jobj.put("seq", lists.get(i).getId());
				jobj.put("name", mLists.get(i).getName());
				jobj.put("phone", mLists.get(i).getPhone());
				jlist.add(jobj);
			}
		}
		logger.info("jlist: {} \t",jlist); 
		return jlist.toString();
	}
	
	@SuppressWarnings("unchecked")
	@RequestMapping(value = "/calendar/searchMeetList.do", method = RequestMethod.POST, produces = "application/text; charset=UTF-8")
	@ResponseBody
	public String searchMeet(StudentDto dto, String keyword) {
		logger.info("검색 문구!!!!!!!!!!!!!!!!!!!!: {} \t",keyword);
		List<StudentDto> sLists =  iService.searchMeetList(keyword);
//				System.out.println("sLists..........."+sLists);
		//[stDto [,cDto=ScheDto[]], stDto [,cDto=ScheDto[]] ]
		JSONArray jlist = new JSONArray();
		for (int i = 0; i < sLists.size(); i++) {
			JSONObject jobj = new JSONObject();
			jobj.put("title", sLists.get(i).getcDto().getTitle());
			jobj.put("id", sLists.get(i).getcDto().getStudent_id());
			jobj.put("name", sLists.get(i).getName());
			jobj.put("phone", sLists.get(i).getPhone());
			jobj.put("seq", sLists.get(i).getcDto().getId());
			jobj.put("content", sLists.get(i).getcDto().getContent());
			jobj.put("start", sLists.get(i).getcDto().getStart().substring(0, 13).concat("시"));
			jlist.add(jobj);
		}
		logger.info("jlist: {} \t",jlist.toString());
		return jlist.toString();
	}
			
	
}
