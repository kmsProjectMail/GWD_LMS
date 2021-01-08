package com.min.edu.ctrl;


import java.security.Principal;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.min.edu.dto.Calender_Dto;
import com.min.edu.service.ServiceAlarm;

@Controller
public class JobLauncherBatch {

	@Autowired
	private ServiceAlarm dao;
	
//	@RequestMapping(value="/aaa.do",method=RequestMethod.GET)
//	public String launchJob(Model model) throws Exception {
//		AlarmDto dto = dao.getAlarm("USER6");
//		String day = dto.getAlarmdate();
//		String days = day.substring(0, 10);
//		
//		Date date = new Date();
//		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
//		String now = sdf.format(date);
//		
//		if(days.equalsIgnoreCase(now)) {
//			model.addAttribute("gg", dto);
//		}else {
//			System.out.println("nono");
//		}
//		return "a";
//	}
	
	@RequestMapping(value="/board/bbb.do",method=RequestMethod.GET)
	public String launchJob(Model model, Principal pro) throws Exception {
		JSONArray jlist = new JSONArray();
		
		List<Calender_Dto> dto = dao.getAlarm(pro.getName());
		for (int i = 0; i < dto.size(); i++) {
			JSONObject jobj = new JSONObject();
			String day =dto.get(i).getAlarm_date();
			String days = day.substring(0, 10);
			Date date = new Date();
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			String now = sdf.format(date);
			
			String ia = day.substring(11,13);
			int ii =date.getHours();
			jobj.put("alarm_date", dto.get(i).getAlarm_date());
			jobj.put("id", dto.get(i).getStudent().getId());
			jobj.put("phone",dto.get(i).getStudent().getPhone());
			if(days.equalsIgnoreCase(now)&&ii==Integer.parseUnsignedInt(ia)) {
				jlist.add(jobj);
				model.addAttribute("gg", jlist);
				continue;
			}else if(!days.equalsIgnoreCase(now) || dto == null) {
				System.out.println("날짜 안맞거나 null값");
				continue;
			}
		}
	
		return "board/c";
	}
//	
	
	
	
//	@RequestMapping(value="/board/bbb.do",method=RequestMethod.GET)
//	public String alarm(Model model) throws Exception {
//		List<Calender_Dto> lists =  dao.selectAlarm();
//		System.out.println("lists.size()"+lists.size());
//		JSONArray jlist = new JSONArray();
//		for (int i = 0; i < lists.size(); i++) {
//			JSONObject jobj = new JSONObject();
//			jobj.put("alarm_check",lists.get(i).getAlarm_check());
//			jobj.put("alarm_date",lists.get(i).getAlarm_date());
//			jobj.put("id",lists.get(i).getStudent().getId());
//			jobj.put("phone",lists.get(i).getStudent().getPhone());
//			jlist.add(jobj);
//		}
//	System.out.println(jlist);
//		model.addAttribute("j", jlist);
//		
//		return "board/c";
//	}
	
}
