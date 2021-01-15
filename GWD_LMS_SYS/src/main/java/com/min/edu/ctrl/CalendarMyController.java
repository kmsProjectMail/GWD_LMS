package com.min.edu.ctrl;

import java.security.Principal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.ParseException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.min.edu.ctrl.CalendarMyController;
import com.min.edu.dto.CalendarDto;
import com.min.edu.dto.StudentDto;
import com.min.edu.service.IServiceCalendar;

@Controller
public class CalendarMyController {
	private static final Logger logger = LoggerFactory.getLogger(CalendarMyController.class);

	@Autowired
	private IServiceCalendar iService;
	
	@RequestMapping(value = "/calendar/calendarMy.do",method = RequestMethod.GET)
	public String calendarMy() {
		logger.info("일정 캘린더로");
		return "calendar/calendarMy";
	}
	
	//일정 로드 //내 것만
	@SuppressWarnings("unchecked")
	@RequestMapping(value="/calendar/loadMy.do",method = RequestMethod.POST, produces = "application/text; charset=UTF-8")
	@ResponseBody
	public String load(Principal principal) throws ParseException{
		JSONArray jlist = new JSONArray();
		
		List<CalendarDto> lists = iService.selectSchedule();
		
		if (lists == null || lists.size() == 0) {
			logger.info("nothing found to load");
		}else {
			logger.info("lists 값:\t {}",lists);
			CalendarDto dto = null;
			
			for (int i = 0; i < lists.size(); i++) {
				if (principal.getName().equalsIgnoreCase(lists.get(i).getStudent_id())) {
					JSONObject jdto = new JSONObject();
					dto = lists.get(i);
					jdto.put("id", dto.getId());
					jdto.put("calendarId", dto.getCalendar_id());
					jdto.put("content", dto.getContent());      
					jdto.put("title", dto.getTitle());      
					jdto.put("category", dto.getCategory());
					jdto.put("start", dto.getStart());      
					jdto.put("end", dto.getEnd()); 
					jdto.put("alarmDate", dto.getAlarm_date()); 
					jlist.add(jdto);		
				}
		}
			logger.info("jlist??????????????????????: \t"+jlist.toString());
		}
		return jlist.toString();
	}
	
	//일정 리스트 로드 //내 것만
	@SuppressWarnings("unchecked")
	@RequestMapping(value="/calendar/loadMyList.do",method = RequestMethod.POST, produces = "application/text; charset=UTF-8")
	@ResponseBody
	public String loadList(Principal principal) throws ParseException{
		JSONArray jlist = new JSONArray();
		
		List<CalendarDto> lists = iService.selectSchedule();
		
		if (lists == null || lists.size() == 0) {
			logger.info("nothing found to load");
		}else {
			logger.info("lists 값:\t {}",lists);
			CalendarDto dto = null;
			
			for (int i = 0; i < lists.size(); i++) {
				if (principal.getName().equalsIgnoreCase(lists.get(i).getStudent_id())) {
					JSONObject jdto = new JSONObject();
					dto = lists.get(i);
					jdto.put("id", dto.getId());
					jdto.put("calendarId", dto.getCalendar_id());
					jdto.put("content", dto.getContent());      
					jdto.put("title", dto.getTitle());      
					jdto.put("category", dto.getCategory());
					jdto.put("start", dto.getStart().substring(0,13).concat("시"));      
					jdto.put("end", dto.getEnd().substring(0,13).concat("시")); 
					jdto.put("alarmDate", dto.getAlarm_date()); 
					jlist.add(jdto);
				}
			}
			logger.info("jlist??????????????????????: \t"+jlist.toString());
		}
		return jlist.toString();
	}
	
	
	//일정 수정
	@SuppressWarnings("unchecked")
	@RequestMapping(value = "/calendar/updateMy.do",method = RequestMethod.POST)
	@ResponseBody
	public String update(String id, String calendar_id, String title, String content, String category, String start, String end) {
		String s = start;
		String e = end;
		CalendarDto dto = new CalendarDto();
		
		StudentDto selDto = iService.selectOneSchedule(id);
		String selS = selDto.getcDto().getStart();
		String selE = selDto.getcDto().getEnd();
		selS = selS.substring(11, 13);
		selE = selE.substring(11, 13);
		
		dto.setId(id);
		dto.setCalendar_id(calendar_id);
		dto.setTitle(title);
		if (content==null) {
			dto.setContent(selDto.getcDto().getContent());
			if (selDto.getcDto().getContent() == null) {
				dto.setContent("");
			}
		}else {
			dto.setContent(content);
		}
		dto.setCategory(category);
		System.out.println(""+(s+selS));
		if (s.length()==8) {
			dto.setStart(s.concat(selS));
		}else {
			dto.setStart(s);
		}
		if (e.length()==8) {
			dto.setEnd(e.concat(selE));
		}else {
			dto.setEnd(e);
		}
		dto.setAlarm_date(s);
		logger.info("welcome update : {} \t",dto);
		boolean isc = iService.updateSchedule(dto);
		logger.info("update result : {} \t",isc);
		
		JSONObject jObj = new JSONObject();
		
		jObj.put("id", id);
		jObj.put("calendarId", calendar_id);
		jObj.put("title", title);
		jObj.put("content", content);
		jObj.put("category", category);
		jObj.put("start", s);
		jObj.put("end", e);
		
		
		logger.info(jObj.toString());
		
		return jObj.toString();
	}
	
	//일정 등록
	@SuppressWarnings("unchecked")
	@RequestMapping(value = "/calendar/saveMy.do",method = RequestMethod.POST, produces = "applicaton/text; charset=UTF-8;")
	@ResponseBody
	public String save(CalendarDto dto,String id, String calendarId, String title, String content, String category, String start, String end) throws ParseException {
		logger.info("welcome save : {} \t",dto);
		
		String s = start.substring(0, start.length()-2) + "T" +  start.substring(start.length()-2);//.substring(10 , start.length()-2);
		String e = end.substring(0, end.length()-2) + "T" +  end.substring(end.length()-2);;//.substring(10 , end.length()-2 );
	
		dto.setId(id);
		dto.setCalendar_id(calendarId);
		dto.setTitle(title);
		dto.setCategory(category);
		dto.setStart(s);
		dto.setEnd(e);
		dto.setContent(content);
		dto.setAlarm_date(s);
		dto.setStudent_id("STUDENT01");
		logger.info("dto 값은?????????????????????????????: \t"+dto);
		boolean isc = iService.insertSchedule(dto);
		
		JSONObject json = new JSONObject();
		
		json.put("id", id);
		json.put("calendarId", calendarId);
		json.put("title", title);
		json.put("category", category);
		json.put("content", content);
		json.put("start", s);
		json.put("end", e);
		
		logger.info(json.toString());
		
		return json.toString();
	}
	
	//일정 삭제
	@SuppressWarnings("unchecked")
	@RequestMapping(value = "/calendar/deleteMy.do",method = RequestMethod.POST)
	@ResponseBody
	public String delete(CalendarDto dto,String id) {
		System.out.println(id);
		dto.setId(id);
		
		logger.info("삭제될 dto id값은?????????????????????????????: \t"+dto);
		boolean isc = iService.deleteSchedule(id);
		System.out.println(isc);
		
		JSONObject jObj = new JSONObject();
		jObj.put("id", id);
		
		logger.info(jObj.toString());
		
		return jObj.toString();
	}
	
	//면담/일정 선택
	@SuppressWarnings("unchecked")
	@RequestMapping(value = "/calendar/selectOne.do",method = RequestMethod.POST, produces = "application/text; charset=UTF-8")
	@ResponseBody
	public String selectOne(String id) {
		logger.info("받은 id 값 : {} \t",id);
		StudentDto dto = iService.selectOneSchedule(id);
		
		String s = dto.getcDto().getStart();
		String e = dto.getcDto().getEnd();

		logger.info("selectOne 결과값 : {} \t",dto);
		JSONObject jObj = new JSONObject();
		jObj.put("id", dto.getId());
		jObj.put("calendarId", dto.getcDto().getCalendar_id());
		jObj.put("content", dto.getcDto().getContent());      
		jObj.put("title", dto.getcDto().getTitle());
		jObj.put("calendarId", dto.getcDto().getCalendar_id());
		jObj.put("category", dto.getcDto().getCategory());
		jObj.put("alarmDate", dto.getcDto().getAlarm_date());
		
		jObj.put("name", dto.getName());
		jObj.put("phone", dto.getPhone());
		
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
	
	//-------------------------------------------------일정 리스트-----------------------------------------------
	
		//일정 리스트 조회 //student가 자신의 아이디일때만
		@SuppressWarnings("unchecked")
		@RequestMapping(value = "/calendar/calendarMyList.do",method = RequestMethod.GET)
		public String boardList(Model model) {
			List<CalendarDto> lists = iService.selectSchedule();
			JSONArray jlist = new JSONArray();
			for (int i = 0; i < lists.size(); i++) {
				JSONObject jobj  = new JSONObject();
				String start = lists.get(i).getStart();
				String s = start.substring(0, 13).concat("시");
				String end= lists.get(i).getEnd();
				String e = end.substring(0, 13).concat("시");
				String student_id = lists.get(i).getStudent_id();
				if (student_id.equals("STUDENT01")) {
					jobj.put("alarm_check", lists.get(i).getAlarm_check());
					jobj.put("calId", lists.get(i).getCalendar_id());
					jobj.put("title", lists.get(i).getTitle());
					jobj.put("content", lists.get(i).getContent());
					jobj.put("start", s);
					jobj.put("end", e);
					jobj.put("seq", lists.get(i).getId());
					jlist.add(jobj);
				}
			}
			System.out.println(jlist); 
			model.addAttribute("jlist", jlist);
			model.addAttribute("lists", lists);
			return "calendar/calendarMyList";
		}
		
		//일정 검색// 내 아이디인것만
		@SuppressWarnings("unchecked")
		@RequestMapping(value = "/calendar/searchShceduleList.do", method = RequestMethod.POST, produces = "application/text; charset=UTF-8")
		@ResponseBody
		public String searchShceduleList(StudentDto dto, String keyword, Principal principal) {
			logger.info("검색 문구!!!!!!!!!!!!!!!!!!!!: {} \t",keyword);
			List<CalendarDto> sLists =  iService.searchShceduleList(keyword);
			JSONArray jlist = new JSONArray();
			for (int i = 0; i < sLists.size(); i++) {
				JSONObject jobj = new JSONObject();
				if (sLists.get(i).getStudent_id().equalsIgnoreCase(principal.getName())) {
					jobj.put("title", sLists.get(i).getTitle());
					jobj.put("id", sLists.get(i).getStudent_id());
					jobj.put("seq", sLists.get(i).getId());
					jobj.put("calId", sLists.get(i).getCalendar_id());
					jobj.put("content", sLists.get(i).getContent());
					jobj.put("alarm_check", sLists.get(i).getAlarm_check());
					jobj.put("start", sLists.get(i).getStart().substring(0, 13).concat("시"));
					jobj.put("end", sLists.get(i).getEnd().substring(0, 13).concat("시"));
					jlist.add(jobj);
				}
			}
			logger.info("jlist: {} \t",jlist.toString());
			return jlist.toString();
		}
		
		@RequestMapping(value = "/calendar/updateCheck.do", method = RequestMethod.POST)
		@ResponseBody
		public Map<String, String> updateCheck(String id, boolean chkVal) {
			Map<String, String> map = new HashMap<String, String>();
			Map<String, Object> iMap = new HashMap<String, Object>();
			iMap.put("id", id);
			if (chkVal==true) {
				iMap.put("alarm_check", "Y");
			}else {
				iMap.put("alarm_check", "N");
			}
			boolean isc = iService.updateAlarm(iMap);
			if (isc == true) {
				if (chkVal==true) {
					map.put("isc", "성공,true");
				}else {
					map.put("isc", "성공,false");
				}
			}else {
				map.put("isc", "실패");
			}
			return map;
		}
}
