package com.min.edu.ctrl;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.min.edu.service.IServiceHrd;
import com.min.edu.vo.HRD_InfoList_Vo;
import com.min.edu.vo.HRD_View_Vo;


@Controller
public class HrdController {
	
	private Logger logger = LoggerFactory.getLogger(this.getClass());
	SimpleDateFormat fm = new SimpleDateFormat("yyyy-MM-dd");
	
	@Autowired
	private IServiceHrd iService;
	
	
	@RequestMapping(value = "/hrdMain.do", method = RequestMethod.GET)
	public String hrdMain() {
		logger.info("welcome HrdController! hrd 조회시스템 이동");
		return "hrd/hrdView";
	}
	
	
	//DB에 기관, 과정정보 저장
	@RequestMapping(value = "/saveDB.do", method = RequestMethod.GET)
	public String testtest(Map<String, Object> map) throws IOException, ParseException {
		map.put("srchTraArea1", "41"); //11: 서울, 41: 경기
		map.put("pageSize", "30");
		map.put("pageNum", "1");

		int n = 1;
		boolean isc = true;
		
			while(isc) {
				try {
					isc = iService.saveDB(map);
				} catch (Exception e) {
					System.err.println("입력 오류");
				}
				n++;
				map.put("pageNum", String.valueOf(n));
				System.out.println("입력 성공: "+ isc);
			}
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
//			System.out.println(lists.get(i).getIno_nm());
			jObj1.put("ino_nm", lists.get(i).getIno_nm());
			jObj1.put("trpr_nm", lists.get(i).getTrpr_nm());
			jObj1.put("tra_start_date", fm.format(lists.get(i).getTra_start_date()));
			jObj1.put("tra_end_date", fm.format(lists.get(i).getTra_end_date()));
			jObj1.put("trtm", lists.get(i).getTrtm());
			jObj1.put("trpr_degr", lists.get(i).getTrpr_degr());
			jObj1.put("trpr_id", lists.get(i).getTrpr_id());
			jObj1.put("trainst_cst_id", lists.get(i).getTrainst_cst_id());
			jArray.add(jObj1);
//			System.out.println("jArray!!!!!!!!!!!"+jArray);
		}
		jObj2.put("info", jArray);
		
		return jObj2;
	}
	
	@RequestMapping(value = "hrdDetailTrpr", method = RequestMethod.GET)
	public String hrdDetailTrpr(String trpr_id, String trpr_degr, Model model) {
		logger.info("welcome HrdController! 과정상세조회 이동");
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("trpr_id", trpr_id);
		map.put("trpr_degr", trpr_degr);
		HRD_View_Vo vo = iService.hrdDetailTrpr(map);
		System.out.println(vo);
		
//		JsonParser parser = new JsonParser();
//		JsonElement facilElement = parser.parse(vo.getFacil_info_list());
//		JsonElement eqmnElement = parser.parse(vo.getFacil_info_list());
		
//		System.out.println("type?"+element.getClass().getName());
//		System.out.println("element-----------"+element);
//		System.out.println(element.getAsJsonObject().get("DATA"));
		
//		List<HRD_InfoList_Vo> fvos = new ArrayList<HRD_InfoList_Vo>();
//		List<HRD_InfoList_Vo> evos = new ArrayList<HRD_InfoList_Vo>();
//		
//		
//		for (int i = 0; i < facilElement.getAsJsonObject().get("DATA").getAsJsonArray().size(); i++) {
////			System.out.println(element.getAsJsonObject().get("DATA").getAsJsonArray().get(i).getAsJsonObject().get("TRAFCLTY_NM"));
//			
//			JsonObject facilInfo = facilElement.getAsJsonObject().get("DATA").getAsJsonArray().get(i).getAsJsonObject();
//			
//			String fclty_ar_cn = facilInfo.get("FCLTY_AR_CN").toString();
//			String hold_qy = facilInfo.get("HOLD_QY").toString();
//			String ocu_acptn_cn = facilInfo.get("OCU_ACPTN_CN").toString();
//			String trafclty_nm = facilInfo.get("TRAFCLTY_NM").toString();
//			
//			HRD_InfoList_Vo fvo = new HRD_InfoList_Vo(fclty_ar_cn, hold_qy, ocu_acptn_cn, trafclty_nm);
//			fvos.add(fvo);
//		}
//		
//		for (int i = 0; i < eqmnElement.getAsJsonObject().get("DATA").getAsJsonArray().size(); i++) {
//			
//			JsonObject eqmnInfo = eqmnElement.getAsJsonObject().get("DATA").getAsJsonArray().get(i).getAsJsonObject();
//			
//			String eqpmn_nm = eqmnInfo.get("EQPMN_NM").toString();
//			String hold_qy = eqmnInfo.get("HOLD_QY").toString();
//			
//			HRD_InfoList_Vo evo = new HRD_InfoList_Vo(eqpmn_nm, hold_qy);
//			evos.add(evo);
//		}
//		
//		
//		model.addAttribute("facilVo",fvos);
//		model.addAttribute("TrprVo",evos);
		
		model.addAttribute("TrprVo",vo);
		return "hrd/hrdTrprDetailView";
	}
}
