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
				System.out.println("현재 날짜와 시간이 같습니다");
			}else if(!days.equalsIgnoreCase(now) || dto == null) {
				System.out.println("날짜 안맞거나 null값");
				continue;
			}
		}
	
		return "board/c";
	}

}
