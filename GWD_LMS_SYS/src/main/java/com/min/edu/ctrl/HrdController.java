package com.min.edu.ctrl;

import java.io.IOException;
import java.io.PrintWriter;
import java.security.Principal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.min.edu.commons.utils.AddressCode_Mapper;
import com.min.edu.dto.Paging;
import com.min.edu.service.IServiceAuth;
import com.min.edu.service.IServiceHrd;
import com.min.edu.vo.HRD_InfoList_Vo;
import com.min.edu.vo.HRD_Trainst_Info_Vo;
import com.min.edu.vo.HRD_Trpr_Info_Vo;
import com.min.edu.vo.HRD_View_Vo;


@Controller
public class HrdController {
	
	private Logger logger = LoggerFactory.getLogger(this.getClass());
	SimpleDateFormat fm = new SimpleDateFormat("yyyy-MM-dd");
	
	@Autowired
	private IServiceHrd iService;
	
	@Autowired
	private IServiceAuth authService;
	
	@RequestMapping(value = "/hrdMain.do", method = RequestMethod.GET)
	public String hrdMain() {
		logger.info("welcome HrdController! hrd 조회시스템 이동");
		return "hrd/hrdView";
	}

	
	//ajax를 통해 검색결과 반환
	@SuppressWarnings("unchecked")
	@RequestMapping(value = "/search.do", method = RequestMethod.GET)
	@ResponseBody
	public JSONObject connect(@RequestParam Map<String, Object> map) {
		logger.info("welcome HrdController! search DB검색 이동 {}", map);
		map.put("addr1", (String)AddressCode_Mapper.AddressCodeMapper((String) map.get("addr1")));
//		if(map.get("trpr_nm").toString().isEmpty()) {
//			map.put("trpr_nm", null);
//		}
		if(map.get("ncs_cd").equals("0")) { 	//ncd_cd 0: 전체검색
			map.replace("ncs_cd", null);
		}
		
		if(map.get("addr1").equals("0")) {
			map.put("address", null);
		}else if(!map.get("addr1").equals("0")) {
			if(!map.get("addr2").equals("")) {
				if(map.get("addr1").equals("서울특별시 ")) {
					map.put("addr1", "서울 ");
					map.put("address", map.get("addr1")+""+map.get("addr2"));
				}else if(map.get("addr1").equals("경기도 ")){
					map.put("addr1", "경기 ");
					map.put("address", map.get("addr1")+""+map.get("addr2"));
				}else {
					map.put("address", map.get("addr1")+""+map.get("addr2"));
				}
			}else {
				if(map.get("addr1").equals("서울특별시 ")) {
					map.put("address", "서울");
				}else if(map.get("addr1").equals("경기도 ")){
					map.put("address", "경기");
				}else {
					map.put("address", ((String)map.get("addr1")));
				}
			}
		}
		
		
		Paging p = new Paging();
		p.calculation(Integer.parseInt(iService.hrdListViewPaging(map)), 10, 5, 1);
		map.put("start", p.getFirst());
		map.put("end", p.getLast());
		
//		System.out.println("map?????????"+map);
		List<HRD_View_Vo> lists = iService.hrdListView(map);
		
		System.out.println("검색결과-------------"+lists);
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
	
	//DB에 기관, 과정정보 저장
//	@RequestMapping(value = "/saveDB.do", method = RequestMethod.GET)
//	public String saveDB(Map<String, Object> map) throws IOException, ParseException {
//		List<String> list = new ArrayList<String>(Arrays.asList("50","36"));
//		for(String area: list) {
//			map.put("srchTraArea1", area); //11: 서울, 41: 경기
//			map.put("pageSize", "30");
//			map.put("pageNum", "1");
//	
//			int n = 1;
//			boolean isc = true;
//			
//				while(isc) {
//					try {
//						isc = iService.saveDB(map);
//					} catch (Exception e) {
//						System.err.println("입력 오류");
//					}
//					n++;
//					map.put("pageNum", String.valueOf(n));
//					System.out.println("입력 성공: "+ isc);
//				}
//			}
//		return "redirect:/hrdMain.do";
//	}
	

	//교육기관 기능: 과정정보 수정 페이지 이동
	@SuppressWarnings({ "unchecked", "unused" })
	@RequestMapping(value = "/trprUpdate.do", method = RequestMethod.GET)
	public String trprUpdate(Model model, HttpSession session, HttpServletResponse response) throws IOException {
		logger.info("welcome HrdController! trprUpdate 과정정보 수정 이동");
		
		Map<String, Object> userInfo = (Map<String, Object>)session.getAttribute("userInfo");
//		System.out.println("userInfo?"+userInfo);
//		System.out.println("userInfo---------?"+userInfo.get("auth"));
		
		if(userInfo == null || !(userInfo.get("auth").equals("ROLE_ACADEMY"))) {
			response.setContentType("text/html; charset=UTF-8");
			PrintWriter out = response.getWriter();
			out.println("<script>alert('잘못된 접근입니다. 기관정보로 로그인해주세요.'); history.back(-1);</script>");
			out.flush();
			return null;
		}
		
		return "hrd/hrdTrprUpdate";
	}
	
	
	
	//교육기관 기능: 기관정보 수정 페이지 이동
	@SuppressWarnings({ "unchecked", "unused" })
	@RequestMapping(value = "/trainstUpdate.do", method = RequestMethod.GET)
	public String trainstUpdate(Model model, HttpSession session, HttpServletResponse response) throws IOException {
		logger.info("welcome HrdController! trainstUpdate 기관정보 수정 이동");
		
		Map<String, Object> userInfo = (Map<String, Object>)session.getAttribute("userInfo");
//		System.out.println("userInfo?"+userInfo);
//		System.out.println("userInfo---------?"+userInfo.get("auth"));
		
		if(userInfo == null || !(userInfo.get("auth").equals("ROLE_ACADEMY"))) {
			response.setContentType("text/html; charset=UTF-8");
			PrintWriter out = response.getWriter();
			out.println("<script>alert('잘못된 접근입니다. 기관정보로 로그인해주세요.'); history.back(-1);</script>");
			out.flush();
			return null;
		}
		
		return "hrd/hrdTrainstUpdate";
	}
	
	//과정정보 수정
	@RequestMapping(value = "/trprModify.do", method = RequestMethod.POST)
	public String trprModify(HttpSession session, HRD_Trpr_Info_Vo vo) {
		
//		System.out.println("vo???????????????"+vo);
		
		HRD_View_Vo Vvo = (HRD_View_Vo)session.getAttribute("TrprVo");
		String trpr_id = Vvo.getTrpr_id();
		String trpr_degr = Vvo.getTrpr_degr();
		
		vo.setTrpr_id(trpr_id);
		vo.setTrpr_degr(trpr_degr);
		iService.trainstAddTrpr(vo);
		
		String trainst_cst_id = Vvo.getTrainst_cst_id();
		
		return "redirect:/hrdDetailTrpr.do?trpr_id="+trpr_id+"&trpr_degr="+trpr_degr+"&trainst_cst_id="+trainst_cst_id;
	}
	
	
	//과정 상세정보 조회
	@RequestMapping(value = "/hrdDetailTrpr.do", method = RequestMethod.GET)
	public String hrdDetailTrpr(String trpr_id, String trpr_degr, String trainst_cst_id, Principal principal, Model model, HttpSession session) throws IOException, ParseException {
		logger.info("welcome HrdController! 과정상세조회 이동");
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("trpr_id", trpr_id);
		map.put("trpr_degr", trpr_degr);
		HRD_View_Vo vo = iService.hrdDetailTrpr(map);
		System.out.println("과정정보 :"+vo);
		
		if(vo.getEqmn_info_list() == null || vo.getFacil_info_list() == null) {
			System.out.println("시설,장비정보가 없다.. 입력실행");
			iService.saveDBList(map);
			vo = iService.hrdDetailTrpr(map);
		}
		
		vo.setTrainst_cst_id(trainst_cst_id);
		vo.setTrpr_id(trpr_id);
		vo.setTrpr_degr(trpr_degr);
		model.addAttribute("TrprVo",vo);
		
		if(principal != null) {		//로그인 상태에서 userId와 auth 가져오기
			String auth = authService.selectUserAuth(principal.getName()).getAuth();
			String userId = principal.getName();
			Map<String, Object> userInfo = new HashMap<String, Object>();
			userInfo.put("auth", auth);
			userInfo.put("userId", userId);
			model.addAttribute("userInfo", userInfo);
			
			if(session.getAttribute("userInfo") != null) {
				System.out.println("session 유저정보가 존재합니다.");
				session.removeAttribute("userInfo");	//기존에 가지고 있는 유저정보 삭제
			}
			session.setAttribute("userInfo", userInfo);
		}
		
		if(session.getAttribute("TrprVo") != null) {
			System.out.println("session 과정정보가 존재합니다.");
			session.removeAttribute("TrprVo");		//기존에 가지고 있는 과정정보 삭제
		}
		session.setAttribute("TrprVo", vo);
		
		return "hrd/hrdTrprDetailView";
	}
	
	//기관정보 수정
		@RequestMapping(value = "/trainstModify.do", method = RequestMethod.POST)
		public String trainstModify(HttpSession session, HRD_Trainst_Info_Vo vo) {
			
//			System.out.println("vo???????????????"+vo);
			
			HRD_View_Vo Vvo = (HRD_View_Vo)session.getAttribute("TrprVo");
			
			String trainst_cst_id = Vvo.getTrainst_cst_id();
			vo.setTrainst_cst_id(trainst_cst_id);
			iService.trainstAddTrainst(vo);
			
			String trpr_id = Vvo.getTrpr_id();
			String trpr_degr = Vvo.getTrpr_degr();
			return "redirect:/hrdDetailTrainst.do?trpr_id="+trpr_id+"&trpr_degr="+trpr_degr+"&trainst_cst_id="+trainst_cst_id;
		}
		
	//기관 상세정보 조회
	@RequestMapping(value = "/hrdDetailTrainst.do", method = RequestMethod.GET)
	public String hrdDetailTrainst(String trpr_id, String trpr_degr, String trainst_cst_id, Principal principal, Model model, HttpSession session) throws IOException, ParseException {
		logger.info("welcome HrdController! 기관상세조회 이동");
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("trpr_id", trpr_id);
		map.put("trpr_degr", trpr_degr);
		map.put("trainst_cst_id", trainst_cst_id);
		
		HRD_Trainst_Info_Vo tvo = iService.hrdDetailTrainst(map);
		HRD_View_Vo vo = iService.hrdDetailTrpr(map);
		System.out.println("과정정보: "+vo);
		
		
		
		vo.setTrainst_cst_id(trainst_cst_id);
		vo.setTrpr_id(trpr_id);
		vo.setTrpr_degr(trpr_degr);
		model.addAttribute("TrprVo",vo);
		
		if(principal != null) {		//로그인 상태에서 userId와 auth 가져오기
			String auth = authService.selectUserAuth(principal.getName()).getAuth();
			String userId = principal.getName();
			Map<String, Object> userInfo = new HashMap<String, Object>();
			userInfo.put("auth", auth);
			userInfo.put("userId", userId);
			model.addAttribute("userInfo", userInfo);
			
			if(session.getAttribute("userInfo") != null) {
				System.out.println("session 유저정보가 존재합니다.");
				session.removeAttribute("userInfo");	//기존에 가지고 있는 유저정보 삭제
			}
			session.setAttribute("userInfo", userInfo);
		}
		
		if(session.getAttribute("TrprVo") != null) {
			System.out.println("session 과정정보가 존재합니다.");
			session.removeAttribute("TrprVo");		//기존에 가지고 있는 과정정보 삭제
		}
		session.setAttribute("TrprVo", vo);
		
		if(session.getAttribute("trainstVo") != null) {
			System.out.println("session 기관정보가 존재합니다.");
			session.removeAttribute("trainstVo");	//기존에 가지고 있는 기관정보 삭제
		}
		session.setAttribute("trainstVo", tvo);
		
		
		
		if(vo.getEqmn_info_list() == null || vo.getFacil_info_list() == null) {
			System.out.println("시설,장비정보가 없다.. 입력실행");
			iService.saveDBList(map);
			vo = iService.hrdDetailTrpr(map);
		}
		
		JsonParser parser = new JsonParser();
		JsonElement facilElement = parser.parse(vo.getFacil_info_list());
		JsonElement eqmnElement = parser.parse(vo.getEqmn_info_list());
		
//		System.out.println("type?"+element.getClass().getName());
//		System.out.println("element-----------"+element);
//		System.out.println(element.getAsJsonObject().get("DATA"));
		
		List<HRD_InfoList_Vo> fvos = new ArrayList<HRD_InfoList_Vo>();
		List<HRD_InfoList_Vo> evos = new ArrayList<HRD_InfoList_Vo>();
		
		
		for (int i = 0; i < facilElement.getAsJsonObject().get("DATA").getAsJsonArray().size(); i++) {
//			System.out.println(element.getAsJsonObject().get("DATA").getAsJsonArray().get(i).getAsJsonObject().get("TRAFCLTY_NM"));
			
			JsonObject facilInfo = facilElement.getAsJsonObject().get("DATA").getAsJsonArray().get(i).getAsJsonObject();
			
			String fclty_ar_cn = facilInfo.get("FCLTY_AR_CN").toString();
			String hold_qy = facilInfo.get("HOLD_QY").toString();
			String ocu_acptn_cn = facilInfo.get("OCU_ACPTN_CN").toString();
			String trafclty_nm = facilInfo.get("TRAFCLTY_NM").toString();
			
			HRD_InfoList_Vo fvo = new HRD_InfoList_Vo(fclty_ar_cn, hold_qy, ocu_acptn_cn, trafclty_nm);
			fvos.add(fvo);
		}
		
		for (int i = 0; i < eqmnElement.getAsJsonObject().get("DATA").getAsJsonArray().size(); i++) {
			
			JsonObject eqmnInfo = eqmnElement.getAsJsonObject().get("DATA").getAsJsonArray().get(i).getAsJsonObject();
			
//			JsonElement en = eqmnInfo.get("EQPMN_NM");
//			System.out.println("en?????????"+en);
//			String eqpmn_nm = en.toString();
			
			String eqpmn_nm = eqmnInfo.get("EQPMN_NM").toString();
			String hold_qy = eqmnInfo.get("HOLD_QY").toString();
			
			HRD_InfoList_Vo evo = new HRD_InfoList_Vo(eqpmn_nm, hold_qy);
			evos.add(evo);
		}
		
		
		model.addAttribute("facilVo",fvos);
		model.addAttribute("eqmnVo",evos);
		model.addAttribute("trainstVo",tvo);
		
		
		return "hrd/hrdTrainstDetailView";
	}
	
	@RequestMapping(value = "trprBmkList.do", method = RequestMethod.GET)
	public String trprBmkList(Principal principal, Model model, HttpSession session) {
		logger.info("즐겨찾기 과정조회");
		
		String userId = null;
		String auth = null;
		
		if(principal != null) {		//로그인 상태에서 userId와 auth 가져오기
			auth = authService.selectUserAuth(principal.getName()).getAuth();
			userId = principal.getName();
			System.out.println("---------"+auth);
			System.out.println("---------"+userId);
			Map<String, Object> userInfo = new HashMap<String, Object>();
			userInfo.put("auth", auth);
			userInfo.put("userId", userId);
			model.addAttribute("userInfo", userInfo);
			
			if(session.getAttribute("userInfo") != null) {
				System.out.println("session 유저정보가 존재합니다.");
				session.removeAttribute("userInfo");	//기존에 가지고 있는 유저정보 삭제
			}
			session.setAttribute("userInfo", userInfo);	//기존의 유저정보를 지우고 현재 유저정보를 담음
			if(userId != null && auth.equals("ROLE_STUDENT")) {
				String str = iService.trprBmkList(userId);
				System.out.println("쿼리결과값 "+str);
			}else {
				logger.info("즐겨찾기 조회 권한이 없습니다. 권한이 학생이 아닙니다.");
			}
		}else if(principal == null) {
			logger.info("즐겨찾기 조회 권한이 없습니다. 로그인해주세요.");
		}
		
		
		return "hrdView2";
	}
	
}
