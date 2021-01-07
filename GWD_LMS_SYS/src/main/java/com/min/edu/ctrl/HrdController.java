package com.min.edu.ctrl;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
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
	
	
	@SuppressWarnings("unchecked")
	@RequestMapping(value = "/search.do", method = RequestMethod.GET)
	@ResponseBody
	public JSONObject connect(@RequestParam Map<String, Object> map) {
		logger.info("welcome HrdController! search DB검색 이동");
		List<HRD_View_Vo> lists =  iService.hrdListView(map);
		
		JSONArray jArray1 = new JSONArray();
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
//			jArray1.clear();
//			jArray1.add("ino_nm:"+lists.get(i).getIno_nm());
//			jArray1.add("trpr_nm:"+lists.get(i).getTrpr_nm());
//			jArray1.add("tra_start_date:"+lists.get(i).getTra_start_date());
//			jArray1.add("tra_end_date:"+lists.get(i).getTra_end_date());
//			jArray1.add("trtm:"+lists.get(i).getTrtm());
//			jArray1.add("trpr_degr:"+lists.get(i).getTrpr_degr());
//			jArray.add(jArray1);
			System.out.println("jArray!!!!!!!!!!!"+jArray);
		}
		jObj2.put("info", jArray);
		System.out.println("jObj2222222222toString"+jObj2.toString());
		System.out.println("jArray-------------------"+jArray);
		System.out.println("jObj2-------------------"+jObj2);
		
		return jObj2;
	}
	
	
	
	//url을 통해  웹페이지에 접속 후 Jsoup을 활용하여 웹페이지 정보를 Document 타입으로 내려받아 DB에 저장하기
		@SuppressWarnings("unchecked")
		@RequestMapping(value = "/saveDB.do", method = RequestMethod.GET, produces = "application/text; charset=utf8")
		public String SaveDB(String url) throws IOException, ParseException {
			SimpleDateFormat fm = new SimpleDateFormat("yyyyMMdd");
//			Date time = new Date();
//			System.out.println("현재시간 ~~~~~~~~~~~~"+time);
//			System.out.println("현재시간 ~~~~~~~~~~~~포맷팅"+fm.format(time));
			
			//인증키
			String authKey = "lRXjWY7EwfYBVA7OImsU5myks52C9yRQ";
			//훈련지역 대분류 (11: 서울)
			String srchTraArea1 = "11";
			//훈련 시작일 (현재일자)
			String srchTraStDt = "20210105";
			//훈련 종료일 (현재일자 + 90일)
			String srchTraEndDt = "20210405";
			
			
			//기본 검색조건
//			http://www.hrd.go.kr/jsp/HRDP/HRDPO00/HRDPOA60/HRDPOA60_1.jsp?
//			authKey=[인증키]&
//			returnType=XML&
//			outType=1&
//			pageNum=1&
//			pageSize=20&
//			srchTraStDt=20141001&
//			srchTraEndDt=20141231&
//			sort=ASC&
//			sortCol=TOT_FXNUM
			
			//선택조건 추가하여 검색
			
			String url1 = "http://www.hrd.go.kr/jsp/HRDP/HRDPO00/HRDPOA60/HRDPOA60_1.jsp?"
					+ "returnType=XML&"
					+ "authKey="+authKey+"&"
					+ "pageNum=1&"
					+ "pageSize=10&"
					+ "srchTraStDt="+srchTraStDt+"&"
					+ "srchTraEndDt="+srchTraEndDt+"&"
					+ "outType=1&"
					+ "sort=ASC&"
					+ "sortCol=TR_STT_DT&"
					+ "srchTraArea1="+srchTraArea1;
			
			System.out.println("url : " + url1);
			Document doc = Jsoup.connect(url1).get();		//문서 가져옴, 출력시 페이지 list를 태그와 함께 가져온다.
			Elements els = doc.select("scn_list");
			
			for(Element el : els) {
				
				String trainst_cst_id = el.select("trainstCstId").toString().replace("<trainstCstId>", "").replace("</trainstCstId>", "").trim();
				
				if(iService.selectTrainst(trainst_cst_id)) {
					System.out.println("기관정보 중복없음 정상");
				
					String trpr_id = el.select("trprId").toString().replace("<trprId>", "").replace("</trprId>", "").trim();
					String trpr_degr = el.select("trprDegr").toString().replace("<trprDegr>", "").replace("</trprDegr>", "").trim();
					String address = el.select("address").toString().replace("<address>", "").replace("</address>", "").trim();
					String ncs_cd = el.select("ncsCd").toString().replace("<ncsCd>", "").replace("</ncsCd>", "").trim();
					String tel_no = el.select("telNo").toString().replace("<telNo>", "").replace("</telNo>", "").trim();
					String tra_end_date1 = el.select("traEndDate").toString().replace("<traEndDate>", "").replace("</traEndDate>", "").trim();
					Date tra_end_date = fm.parse(tra_end_date1);
					String tra_start_date1 = el.select("traStartDate").toString().replace("<traStartDate>", "").replace("</traStartDate>", "").trim();
					Date tra_start_date = fm.parse(tra_start_date1);
					
					// 과정/기관정보 & 시설정보
					String detailurlFacil ="http://www.hrd.go.kr/jsp/HRDP/HRDPO00/HRDPOA60/HRDPOA60_2.jsp?"
							+ "authKey=lRXjWY7EwfYBVA7OImsU5myks52C9yRQ&"	//인증키
							+ "returnType=XML&"								//리턴타입, 반드시 XML
							+ "outType=2&"									//출력형태 (1:리스트 2:상세)
							+ "srchTrprId="+trpr_id+"&"						//훈련과정 ID
							+ "srchTrprDegr="+trpr_degr+"&"					//훈련과정 회차
							+ "srchTorgId=facility_detail";					//훈련기관 ID (default:기본정보, facility_detail:시설정보, eqnm_detail:장비정보)
					
					// 과정/기관 기본정보 & 장비정보
					String detailurlEqmn ="http://www.hrd.go.kr/jsp/HRDP/HRDPO00/HRDPOA60/HRDPOA60_2.jsp?"
							+ "authKey=lRXjWY7EwfYBVA7OImsU5myks52C9yRQ&"	//인증키
							+ "returnType=XML&"								//리턴타입, 반드시 XML
							+ "outType=2&"									//출력형태 (1:리스트 2:상세)
							+ "srchTrprId="+trpr_id+"&"						//훈련과정 ID
							+ "srchTrprDegr="+trpr_degr+"&"					//훈련과정 회차
							+ "srchTorgId=eqnm_detail";						//훈련기관 ID (default:기본정보, facility_detail:시설정보, eqnm_detail:장비정보)
					
	
					Document detailsDocFacil = Jsoup.connect(detailurlFacil).get();
					Elements detailEls = detailsDocFacil.select("inst_base_info");											//과정,기관 기본정보
					Elements facilEls = detailsDocFacil.select("inst_facility_info").select("inst_facility_info_list");		//시설정보 세부
					Document detailsDocEqmn = Jsoup.connect(detailurlEqmn).get();
					Elements eqmnEls = detailsDocEqmn.select("inst_eqnm_info").select("inst_eqnm_info_list");				//장비정보 세부
					
					String addr1 = detailEls.select("addr1").toString().replace("<addr1>", "").replace("</addr1>", "").trim();
					String addr2 = detailEls.select("addr2").toString().replace("<addr2>", "").replace("</addr2>", "").trim();
					String file_path = detailEls.select("filePath").toString().replace("<filePath>", "").replace("</filePath>", "").trim();
					String hp_addr = detailEls.select("hpAddr").toString().replace("<hpAddr>", "").replace("</hpAddr>", "").trim();
					String ino_nm = detailEls.select("inoNm").toString().replace("<inoNm>", "").replace("</inoNm>", "").trim();
					String ncs_nm = detailEls.select("ncsNm").toString().replace("<ncsNm>", "").replace("</ncsNm>", "").trim();
					String p_file_name = detailEls.select("pFileName").toString().replace("<pFileName>", "").replace("</pFileName>", "").trim();
					String torg_par_grad = detailEls.select("torgParGrad").toString().replace("<torgParGrad>", "").replace("</torgParGrad>", "").trim();
					String trpr_chap = detailEls.select("trprChap").toString().replace("<trprChap>", "").replace("</trprChap>", "").trim();
					String trpr_chap_email = detailEls.select("trprChapEmail").toString().replace("<trprChapEmail>", "").replace("</trprChapEmail>", "").trim();
					String trpr_chap_tel = detailEls.select("trprChapTel").toString().replace("<trprChapTel>", "").replace("</trprChapTel>", "").trim();
					String trpr_nm = detailEls.select("trprNm").toString().replace("<trprNm>", "").replace("</trprNm>", "").trim();
					String trtm = detailEls.select("trtm").toString().replace("<trtm>", "").replace("</trtm>", "").trim();
					

					
					//시설정보 세부
	//				["CSTRMR_NM":"["TRAFCLTY_NM":"", "FCLTY_AR_CN":"", "HOLD_QY":"", "OCU_ACPTN_CN":""]"]
					
					JSONObject facilJobj = new JSONObject();
					JSONArray facilJarray1 = new JSONArray();
					JSONArray facilJarray2 = new JSONArray();
					
					for (Element fEl: facilEls) { 
						String cstmr_nm = fEl.select("cstmrNm").toString().replace("<cstmrNm>", "").replace("</cstmrNm>", "").trim();
						String trafclty_nm = fEl.select("trafcltyNm").toString().replace("<trafcltyNm>", "").replace("</trafcltyNm>", "").trim();
						String fclty_ar_cn = fEl.select("fcltyArCn").toString().replace("<fcltyArCn>", "").replace("</fcltyArCn>", "").trim();
						String hold_qy1 = fEl.select("holdQy").toString().replace("<holdQy>", "").replace("</holdQy>", "").trim();
						String ocu_acptn_cn = fEl.select("ocuAcptnNmprCn").toString().replace("<ocuAcptnNmprCn>", "").replace("</ocuAcptnNmprCn>", "").trim();
						
						facilJarray1.clear();
						
						facilJarray1.add("TRAFCLTY_NM:"+trafclty_nm);
						facilJarray1.add("FCLTY_AR_CN:"+fclty_ar_cn);
						facilJarray1.add("HOLD_QY:"+hold_qy1);
						facilJarray1.add("OCU_ACPTN_CN:"+ocu_acptn_cn);
						facilJarray2.add(cstmr_nm+":"+facilJarray1);
						
						facilJobj.put("DATA", facilJarray2);
					}
					
					//시설정보 리스트
					String facil_info_list = facilJobj.toJSONString();
	
					
					// 장비정보 세부
	//				["CSTRMR_NM":"["EQPMN_NM":"", "HOLD_QY":""]"]
					
					JSONObject eqpmJobj = new JSONObject();
					JSONArray eqpmJarray1 = new JSONArray();
					JSONArray eqpmJarray2 = new JSONArray();
					
					for (Element eEl: eqmnEls) {
						String cstmr_nm = eEl.select("cstmrNm").toString().replace("<cstmrNm>", "").replace("</cstmrNm>", "").trim();
						String eqpmn_nm = eEl.select("eqpmnNm").toString().replace("<eqpmnNm>", "").replace("</eqpmnNm>", "").trim();
						String hold_qy2 = eEl.select("holdQy").toString().replace("<holdQy>", "").replace("</holdQy>", "").trim();
						
						eqpmJarray1.clear();
						
						eqpmJarray1.add("EQPMN_NM:"+eqpmn_nm);
						eqpmJarray1.add("HOLD_QY:"+hold_qy2);
						eqpmJarray2.add(cstmr_nm+":"+eqpmJarray1);
						eqpmJobj.put("DATA", eqpmJarray2);
					}
					
					//장비정보 리스트
					String eqmn_info_list = eqpmJobj.toJSONString();
					
					//기관정보 vo
					HRD_Trainst_Info_Vo vo1 = new HRD_Trainst_Info_Vo(trainst_cst_id, p_file_name, file_path, ino_nm, addr1, addr2, tel_no, hp_addr, torg_par_grad);
					
					//과정정보 vo
					HRD_Trpr_Info_Vo vo2 = new HRD_Trpr_Info_Vo(trpr_id, trpr_nm, tra_start_date, tra_end_date, trtm, trainst_cst_id, address, ncs_nm, ncs_cd, trpr_chap_tel, trpr_chap, trpr_chap_email, trpr_degr, facil_info_list, eqmn_info_list);
					
					//기관정보 입력
					iService.insertTrainstInfo(vo1);
					System.out.println("~~~~~~~~~~~~기관정보 입력 성공.....");
					
					//과정정보 입력
					iService.insertTrprInfo(vo2);
					System.out.println("~~~~~~~~~~~~과정정보 입력 성공.....");

				}else {
					System.out.println("기관정보 중복 발생");
					
					String trpr_id = el.select("trprId").toString().replace("<trprId>", "").replace("</trprId>", "").trim();
					String trpr_degr = el.select("trprDegr").toString().replace("<trprDegr>", "").replace("</trprDegr>", "").trim();
					
					Map<String, Object> map = new HashMap<String, Object>();
					map.put("trpr_id", trpr_id);
					map.put("trpr_degr", trpr_degr);
					
					if(iService.selectTrpr(map)) {
						System.out.println("과정정보 중복없음 정상");
						
						String address = el.select("address").toString().replace("<address>", "").replace("</address>", "").trim();
						String ncs_cd = el.select("ncsCd").toString().replace("<ncsCd>", "").replace("</ncsCd>", "").trim();
						String tra_end_date1 = el.select("traEndDate").toString().replace("<traEndDate>", "").replace("</traEndDate>", "").trim();
						Date tra_end_date = fm.parse(tra_end_date1);
						String tra_start_date1 = el.select("traStartDate").toString().replace("<traStartDate>", "").replace("</traStartDate>", "").trim();
						Date tra_start_date = fm.parse(tra_start_date1);
						
						// 과정/기관정보 & 시설정보
						String detailurlFacil ="http://www.hrd.go.kr/jsp/HRDP/HRDPO00/HRDPOA60/HRDPOA60_2.jsp?"
								+ "authKey=lRXjWY7EwfYBVA7OImsU5myks52C9yRQ&"	//인증키
								+ "returnType=XML&"								//리턴타입, 반드시 XML
								+ "outType=2&"									//출력형태 (1:리스트 2:상세)
								+ "srchTrprId="+trpr_id+"&"						//훈련과정 ID
								+ "srchTrprDegr="+trpr_degr+"&"					//훈련과정 회차
								+ "srchTorgId=facility_detail";					//훈련기관 ID (default:기본정보, facility_detail:시설정보, eqnm_detail:장비정보)
						
						// 과정/기관 기본정보 & 장비정보
						String detailurlEqmn ="http://www.hrd.go.kr/jsp/HRDP/HRDPO00/HRDPOA60/HRDPOA60_2.jsp?"
								+ "authKey=lRXjWY7EwfYBVA7OImsU5myks52C9yRQ&"	//인증키
								+ "returnType=XML&"								//리턴타입, 반드시 XML
								+ "outType=2&"									//출력형태 (1:리스트 2:상세)
								+ "srchTrprId="+trpr_id+"&"						//훈련과정 ID
								+ "srchTrprDegr="+trpr_degr+"&"					//훈련과정 회차
								+ "srchTorgId=eqnm_detail";						//훈련기관 ID (default:기본정보, facility_detail:시설정보, eqnm_detail:장비정보)
						
		
						Document detailsDocFacil = Jsoup.connect(detailurlFacil).get();
						Elements detailEls = detailsDocFacil.select("inst_base_info");											//과정,기관 기본정보
						Elements facilEls = detailsDocFacil.select("inst_facility_info").select("inst_facility_info_list");		//시설정보 세부
						Document detailsDocEqmn = Jsoup.connect(detailurlEqmn).get();
						Elements eqmnEls = detailsDocEqmn.select("inst_eqnm_info").select("inst_eqnm_info_list");				//장비정보 세부

						String ncs_nm = detailEls.select("ncsNm").toString().replace("<ncsNm>", "").replace("</ncsNm>", "").trim();
						String trpr_chap = detailEls.select("trprChap").toString().replace("<trprChap>", "").replace("</trprChap>", "").trim();
						String trpr_chap_email = detailEls.select("trprChapEmail").toString().replace("<trprChapEmail>", "").replace("</trprChapEmail>", "").trim();
						String trpr_chap_tel = detailEls.select("trprChapTel").toString().replace("<trprChapTel>", "").replace("</trprChapTel>", "").trim();
						String trpr_nm = detailEls.select("trprNm").toString().replace("<trprNm>", "").replace("</trprNm>", "").trim();
						String trtm = detailEls.select("trtm").toString().replace("<trtm>", "").replace("</trtm>", "").trim();
						
						
						//시설정보 세부
		//				["CSTRMR_NM":"["TRAFCLTY_NM":"", "FCLTY_AR_CN":"", "HOLD_QY":"", "OCU_ACPTN_CN":""]"]
						
						JSONObject facilJobj = new JSONObject();
						JSONArray facilJarray1 = new JSONArray();
						JSONArray facilJarray2 = new JSONArray();
						
						for (Element fEl: facilEls) { 
							String cstmr_nm = fEl.select("cstmrNm").toString().replace("<cstmrNm>", "").replace("</cstmrNm>", "").trim();
							String trafclty_nm = fEl.select("trafcltyNm").toString().replace("<trafcltyNm>", "").replace("</trafcltyNm>", "").trim();
							String fclty_ar_cn = fEl.select("fcltyArCn").toString().replace("<fcltyArCn>", "").replace("</fcltyArCn>", "").trim();
							String hold_qy1 = fEl.select("holdQy").toString().replace("<holdQy>", "").replace("</holdQy>", "").trim();
							String ocu_acptn_cn = fEl.select("ocuAcptnNmprCn").toString().replace("<ocuAcptnNmprCn>", "").replace("</ocuAcptnNmprCn>", "").trim();
							
							facilJarray1.clear();
							
							if(trafclty_nm!=null) {
								facilJarray1.add("TRAFCLTY_NM:"+trafclty_nm);
							}
							if(fclty_ar_cn!=null) {
								facilJarray1.add("FCLTY_AR_CN:"+fclty_ar_cn);
							}
							if(hold_qy1!=null) {
								facilJarray1.add("HOLD_QY:"+hold_qy1);
							}
							if(ocu_acptn_cn!=null) {
								facilJarray1.add("OCU_ACPTN_CN:"+ocu_acptn_cn);
							}
							if(facilJarray1!=null) {
								facilJarray2.add(cstmr_nm+":"+facilJarray1);
							}
							if(facilJarray2!=null) {
								facilJobj.put("DATA", facilJarray2);
							}
						}
						
						//시설정보 리스트
						String facil_info_list = facilJobj.toJSONString().trim();
		
						
						// 장비정보 세부
		//				["CSTRMR_NM":"["EQPMN_NM":"", "HOLD_QY":""]"]
						
						JSONObject eqpmJobj = new JSONObject();
						JSONArray eqpmJarray1 = new JSONArray();
						JSONArray eqpmJarray2 = new JSONArray();
						
						for (Element eEl: eqmnEls) {
							String cstmr_nm = eEl.select("cstmrNm").toString().replace("<cstmrNm>", "").replace("</cstmrNm>", "").trim();
							String eqpmn_nm = eEl.select("eqpmnNm").toString().replace("<eqpmnNm>", "").replace("</eqpmnNm>", "").trim();
							String hold_qy2 = eEl.select("holdQy").toString().replace("<holdQy>", "").replace("</holdQy>", "").trim();
							
							eqpmJarray1.clear();
							
							if(eqpmn_nm!=null) {
								eqpmJarray1.add("EQPMN_NM:"+eqpmn_nm);
							}
							if(hold_qy2!=null) {
								eqpmJarray1.add("HOLD_QY:"+hold_qy2);
							}
							if(eqpmJarray1!=null) {
								eqpmJarray2.add(cstmr_nm+":"+eqpmJarray1);
							}
							if(eqpmJarray2!=null) {
								eqpmJobj.put("DATA", eqpmJarray2);
							}
							
						}
						
						//장비정보 리스트
						String eqmn_info_list = eqpmJobj.toJSONString().trim();
						
						//과정정보 vo
						HRD_Trpr_Info_Vo vo2 = new HRD_Trpr_Info_Vo(trpr_id, trpr_nm, tra_start_date, tra_end_date, trtm, trainst_cst_id, address, ncs_nm, ncs_cd, trpr_chap_tel, trpr_chap, trpr_chap_email, trpr_degr, facil_info_list, eqmn_info_list);
						
						//과정정보 입력
						iService.insertTrprInfo(vo2);
						System.out.println("~~~~~~~~~~~~과정정보 입력 성공.....");
						
					}else {
						System.out.println("과정정보 중복 발생");
					}
				}
			}
			
			return "home"; 
			
		}
		
	

}
