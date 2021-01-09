package com.min.edu.ctrl;

import java.io.IOException;
import java.text.ParseException;
import java.util.List;
import java.util.Map;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.min.edu.service.IServiceHrd;
import com.min.edu.vo.HRD_View_Vo;


@Controller
public class HrdController {
	
	private Logger logger = LoggerFactory.getLogger(this.getClass());
	
	@Autowired
	private IServiceHrd iService;
	
	
	@RequestMapping(value = "/hrdMain.do", method = RequestMethod.GET)
	public String hrdMain() {
		logger.info("welcome HrdController! hrd 조회시스템 이동");
		return "hrd/hrdView";
	}
	
	
	//DB에 기관, 과정정보 저장
	@RequestMapping(value = "/saveDB.do", method = RequestMethod.GET)
	public String testtest(String srchTraArea1) throws IOException, ParseException {
		srchTraArea1 = "11";
		boolean isc = iService.saveDB(srchTraArea1);
		System.out.println("입력 성공: "+ isc);
		return "redirect:/hrdMain.do";
	}
	
	
	//ajax를 통해 검색결과 반환
	@SuppressWarnings("unchecked")
	@RequestMapping(value = "/search.do", method = RequestMethod.GET)
	@ResponseBody
	public JSONObject connect(@RequestParam Map<String, Object> map) {
		logger.info("welcome HrdController! search DB검색 이동");
		if(map.get("ncs_cd").equals("0")) { 	//ncd_cd 0: 전체검색
			map.replace("ncs_cd", null);
		}
		if(map.get("address").equals("all")) {	//address all: 전체검색
			map.replace("address", null);
		}
//		System.out.println("map???----"+map);
		
		List<HRD_View_Vo> lists =  iService.hrdListView(map);
		
//		System.out.println("검색결과-------------"+lists);
//		System.out.println("lists size---------"+lists.size());
		
		JSONArray jArray = new JSONArray();
		JSONObject jObj2 = new JSONObject();
		for(int i = 0; i < lists.size(); i++) {
			JSONObject jObj1 = new JSONObject();
			System.out.println(lists.get(i).getIno_nm());
			jObj1.put("ino_nm", lists.get(i).getIno_nm());
			jObj1.put("trpr_nm", lists.get(i).getTrpr_nm());
			jObj1.put("tra_start_date", lists.get(i).getTra_start_date());
			jObj1.put("tra_end_date", lists.get(i).getTra_end_date());
			jObj1.put("trtm", lists.get(i).getTrtm());
			jObj1.put("trpr_degr", lists.get(i).getTrpr_degr());
			jArray.add(jObj1);
//			System.out.println("jArray!!!!!!!!!!!"+jArray);
		}
		jObj2.put("info", jArray);
		
		return jObj2;
	}
}
