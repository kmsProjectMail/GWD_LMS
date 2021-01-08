package com.min.edu.ctrl;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.min.edu.commons.utils.HRDUtils;
import com.min.edu.service.IServiceHrd;
import com.min.edu.vo.HRD_Trainst_Info_Vo;
import com.min.edu.vo.HRD_Trpr_Info_Vo;
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
	
	/////////////////test
	@Autowired
	private HRDUtils utils;
	
	
	@RequestMapping(value = "/testtest.do", method = RequestMethod.GET)
	public String testtest() throws IOException, ParseException {
		List<HRD_Trainst_Info_Vo> trainstLists = utils.trainstInfo("11");
		List<HRD_Trpr_Info_Vo> trprLists = utils.trprInfo("11");
		System.out.println("기관정보 vo>>>>>>>>>>>>>>>>>>>>"+trainstLists);
		System.out.println("과정정보 vo>>>>>>>>>>>>>>>>>>>>"+trprLists);
		return "hrd/hrdView";
	}
	//////////////////////
	
	
	
	
	@SuppressWarnings("unchecked")
	@RequestMapping(value = "/search.do", method = RequestMethod.GET)
	@ResponseBody
	public JSONObject connect(@RequestParam Map<String, Object> map) {
		logger.info("welcome HrdController! search DB검색 이동");
		List<HRD_View_Vo> lists =  iService.hrdListView(map);
		
		JSONArray jArray = new JSONArray();
		JSONObject jObj2 = new JSONObject();
		System.out.println("검색결과-------------"+lists);
		System.out.println("lists size---------"+lists.size());
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
	
	
		//DB에 기관, 과정정보를 저장
		@RequestMapping(value = "/saveDB.do", method = RequestMethod.GET, produces = "application/text; charset=utf8")
		public String SaveDB(String url) throws IOException, ParseException {

			
			return "hrd/hrdView";
		}
}
