package com.min.edu.commons.utils;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
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
import org.springframework.stereotype.Component;

import com.min.edu.service.IServiceHrd;
import com.min.edu.vo.HRD_Trainst_Info_Vo;
import com.min.edu.vo.HRD_Trpr_Info_Vo;

@Component
public class HRDUtils {
	
	public HRDUtils() {
	}
	
	@Autowired
	private IServiceHrd iService;
	
	private Logger logger = LoggerFactory.getLogger(getClass());
	
	private SimpleDateFormat fm = new SimpleDateFormat("yyyyMMdd");
	private SimpleDateFormat fm2 = new SimpleDateFormat("yyyy-MM-dd");

	/**
	 * 목록 Elements를 반환하는 메서드
	 * @param srchTraArea1 훈련지역 대분류 (11:서울 41:경기)
	 * @return 목록 Element
	 */
	public Elements hrdList(String srchTraArea1) throws IOException, ParseException {
//		srchTraArea1 - 훈련지역 대분류 (11:서울, 41:경기) 
		//DB 저장 날짜: 현재일 +90일
		
		Date time = new Date();	//현재시간 받아오기
		Calendar cal = Calendar.getInstance();
        cal.setTime(time);
        cal.add(Calendar.DATE, 90);		//날짜 더하기
        
		//인증키
        String authKey = "lRXjWY7EwfYBVA7OImsU5myks52C9yRQ";
        //출력할 페이지 번호
        String pageNum = "1";
        //한 페이지에 출력할 검색결과 갯수 (최소 10개~ 100개)
        String pageSize = "25";
        
		//훈련 시작일 (현재일자)
		String srchTraStDt = fm.format(time);	//현재시간 yyyyMMdd
		logger.info("srchTraStDt 훈련시작일-------"+srchTraStDt);
		//훈련 종료일 (현재일자 + 90일)
		String srchTraEndDt = fm.format(cal.getTime());	//현재시간+90일 yyyyMMdd
		logger.info("srchTraEndDt 훈련종료일-------"+srchTraEndDt);
		
		
		//선택조건 추가하여 검색
		String url = "http://www.hrd.go.kr/jsp/HRDP/HRDPO00/HRDPOA60/HRDPOA60_1.jsp?"
				+ "returnType=XML&"
				+ "authKey="+authKey+"&"
				+ "pageNum="+pageNum+"&"
				+ "pageSize="+pageSize+"&"
				+ "srchTraStDt="+srchTraStDt+"&"
				+ "srchTraEndDt="+srchTraEndDt+"&"
				+ "outType=1&"
				+ "sort=ASC&"
				+ "sortCol=TR_STT_DT&"
				+ "srchTraArea1="+srchTraArea1;
		
//		logger.info("목록 url 출력 : " + url);
		Document doc = Jsoup.connect(url).get();		//문서 가져옴, 출력시 페이지 list를 태그와 함께 가져온다.
		Elements els = doc.select("scn_list");
		return els;
	}
	
	
	/**
	 *  과정/기관 기본정보 Elements를 반환하는 메서드
	 * @param trpr_id
	 * @param trpr_degr
	 * @return 과정/기관 기본정보 Elements
	 * @throws IOException
	 * @throws ParseException
	 */
	public Elements getdetailurl(String trpr_id, String trpr_degr) throws IOException, ParseException {
		// 과정/기관정보 기본정보
		String getdetailurl ="http://www.hrd.go.kr/jsp/HRDP/HRDPO00/HRDPOA60/HRDPOA60_2.jsp?"
				+ "authKey=lRXjWY7EwfYBVA7OImsU5myks52C9yRQ&"	//인증키
				+ "returnType=XML&"								//리턴타입, 반드시 XML
				+ "outType=2&"									//출력형태 (1:리스트 2:상세)
				+ "srchTrprId="+trpr_id+"&"						//훈련과정 ID
				+ "srchTrprDegr="+trpr_degr+"&"					//훈련과정 회차
				+ "srchTorgId=default";							//훈련기관 ID (default:기본정보, facility_detail:시설정보, eqnm_detail:장비정보)
		
//		logger.info("과정/기관 기본정보 url : " + getdetailurl);
		Document doc = Jsoup.connect(getdetailurl).get();		//문서 가져옴, 출력시 페이지 list를 태그와 함께 가져온다.
		Elements els = doc.select("inst_base_info");
		return els;
	}
	
	/**
	 * 과정/기관정보의 시설정보 Elements를 반환하는 메서드
	 * @param srchTraArea1
	 * @return
	 * @throws IOException
	 * @throws ParseException
	 */
	public Elements getdetailurlFacil(String trpr_id, String trpr_degr) throws IOException, ParseException {
		// 과정/기관정보 & 시설정보
		String detailurlFacil ="http://www.hrd.go.kr/jsp/HRDP/HRDPO00/HRDPOA60/HRDPOA60_2.jsp?"
		+ "authKey=lRXjWY7EwfYBVA7OImsU5myks52C9yRQ&"	//인증키
		+ "returnType=XML&"								//리턴타입, 반드시 XML
		+ "outType=2&"									//출력형태 (1:리스트 2:상세)
		+ "srchTrprId="+trpr_id+"&"						//훈련과정 ID
		+ "srchTrprDegr="+trpr_degr+"&"					//훈련과정 회차
		+ "srchTorgId=facility_detail";					//훈련기관 ID (default:기본정보, facility_detail:시설정보, eqnm_detail:장비정보)

		
//		logger.info("과정/기관정보 + 시설정보 url : " + detailurlFacil);
		
		Document doc = Jsoup.connect(detailurlFacil).get();		//문서 가져옴, 출력시 페이지 list를 태그와 함께 가져온다.
		Elements els = doc.select("inst_facility_info_list");
		return els;
	}
	
	public Elements getdetailurlEqnm(String trpr_id, String trpr_degr) throws IOException, ParseException {
		// 과정/기관 기본정보 & 장비정보
		String detailurlEqmn ="http://www.hrd.go.kr/jsp/HRDP/HRDPO00/HRDPOA60/HRDPOA60_2.jsp?"
				+ "authKey=lRXjWY7EwfYBVA7OImsU5myks52C9yRQ&"	//인증키
				+ "returnType=XML&"								//리턴타입, 반드시 XML
				+ "outType=2&"									//출력형태 (1:리스트 2:상세)
				+ "srchTrprId="+trpr_id+"&"						//훈련과정 ID
				+ "srchTrprDegr="+trpr_degr+"&"					//훈련과정 회차
				+ "srchTorgId=eqnm_detail";						//훈련기관 ID (default:기본정보, facility_detail:시설정보, eqnm_detail:장비정보)
		
		
//		logger.info("과정/기관정보 + 장비정보 url : " + detailurlEqmn);
		Document doc = Jsoup.connect(detailurlEqmn).get();		//문서 가져옴, 출력시 페이지 list를 태그와 함께 가져온다.
		Elements els = doc.select("inst_eqnm_info_list");
		
		return els;
	}
	
	/**
	 * 시설정보 리스트를 반환하는 메서드
	 * @param detailFacil 시설정보 Elements
	 * @return json.toString() 시설정보리스트
	 */
	@SuppressWarnings("unchecked")
	public String facilInfoList(Elements detailFacil) {

		JSONObject facilJobj = new JSONObject();
		JSONArray facilJarray = new JSONArray();
		
		for (Element fEl : detailFacil) {
			JSONObject facilJobj2 = new JSONObject();
			//기관명
//			String cstmr_nm = fEl.select("cstmrNm").toString().replace("<cstmrNm>", "").replace("</cstmrNm>", "").trim();
			String trafclty_nm = fEl.select("trafcltyNm").toString().replace("<trafcltyNm>", "").replace("</trafcltyNm>", "").trim();
			String fclty_ar_cn = fEl.select("fcltyArCn").toString().replace("<fcltyArCn>", "").replace("</fcltyArCn>", "").trim();
			String hold_qy1 = fEl.select("holdQy").toString().replace("<holdQy>", "").replace("</holdQy>", "").trim();
			String ocu_acptn_cn = fEl.select("ocuAcptnNmprCn").toString().replace("<ocuAcptnNmprCn>", "").replace("</ocuAcptnNmprCn>", "").trim();
			
//			if(cstmr_nm!=null) {
//			facilJobj2.put("CSTMR_NM", cstmr_nm);
//			}
			if(!trafclty_nm.isEmpty()) {
				facilJobj2.put("TRAFCLTY_NM", trafclty_nm);
			}
			if(!fclty_ar_cn.isEmpty()) {
				facilJobj2.put("FCLTY_AR_CN", fclty_ar_cn);
			}
			if(!hold_qy1.isEmpty()) {
				facilJobj2.put("HOLD_QY", hold_qy1);
			}
			if(!ocu_acptn_cn.isEmpty()) {
				facilJobj2.put("OCU_ACPTN_CN", ocu_acptn_cn);
			}
			if(!facilJobj2.isEmpty()) {
				facilJarray.add(facilJobj2);
			}
		}
		if(!facilJarray.isEmpty()) {
			facilJobj.put("DATA", facilJarray);
		}

		return facilJobj.toJSONString().trim();

	}
	
	/**
	 * 장비정보 리스트를 반환하는 메서드
	 * @param detailEqnm 장비정보 Elements
	 * @return json.toString() 장비정보리스트
	 */
	@SuppressWarnings("unchecked")
	public String eqnmInfoList(Elements detailEqnm) {

		JSONObject eqpmJobj = new JSONObject();
		JSONArray eqpmJarray = new JSONArray();

		for (Element eEl : detailEqnm) {
			JSONObject eqpmJobj2 = new JSONObject();
			//기관명
//			String cstmr_nm = eEl.select("cstmrNm").toString().replace("<cstmrNm>", "").replace("</cstmrNm>", "").trim();
			String eqpmn_nm = eEl.select("eqpmnNm").toString().replace("<eqpmnNm>", "").replace("</eqpmnNm>", "").trim();
			String hold_qy2 = eEl.select("holdQy").toString().replace("<holdQy>", "").replace("</holdQy>", "").trim();
			
//			if(cstmr_nm!=null) {
//				eqpmJobj2.put("CSTMR_NM", cstmr_nm);
//			}
			if(!eqpmn_nm.isEmpty()) {
				eqpmJobj2.put("EQPMN_NM", eqpmn_nm);
			}
			if(!hold_qy2.isEmpty()) {
				eqpmJobj2.put("HOLD_QY", hold_qy2);
			}
			if(!eqpmJobj2.isEmpty()) {
				eqpmJarray.add(eqpmJobj2);
			}
		}
		if(!eqpmJarray.isEmpty()) {
			eqpmJobj.put("DATA", eqpmJarray);
		}
		return eqpmJobj.toJSONString().trim();
		
	}
	
	/**
	 * 기관정보를 담은 vo를 반환하는 메서드
	 * @param srchTraArea1 지역코드
	 * @return 기관정보 List<HRD_Trainst_Info_VO>
	 * @throws IOException
	 * @throws ParseException
	 */
	public List<HRD_Trainst_Info_Vo> trainstInfo(String srchTraArea1) throws IOException, ParseException {
		
		//목록정보 호출
		Elements els = hrdList(srchTraArea1);
		
		List<HRD_Trainst_Info_Vo> lists = new ArrayList<HRD_Trainst_Info_Vo>();
		
		for(Element el: els) {
			
			//기관 id
			String trainst_cst_id = el.select("trainstCstId").toString().replace("<trainstCstId>", "").replace("</trainstCstId>", "").trim();
			//기관 전화번호
			String tel_no = el.select("telNo").toString().replace("<telNo>", "").replace("</telNo>", "").trim();
			//과정 id
			String trpr_id = el.select("trprId").toString().replace("<trprId>", "").replace("</trprId>", "").trim();
			//과정회차
			String trpr_degr = el.select("trprDegr").toString().replace("<trprDegr>", "").replace("</trprDegr>", "").trim();
			
			//기관정보 조회
			Elements detail = getdetailurl(trpr_id, trpr_degr);
			
			String addr1 = detail.select("addr1").toString().replace("<addr1>", "").replace("</addr1>", "").trim();
			String addr2 = detail.select("addr2").toString().replace("<addr2>", "").replace("</addr2>", "").trim();
			String file_path = detail.select("filePath").toString().replace("<filePath>", "").replace("</filePath>", "").trim();
			String hp_addr = detail.select("hpAddr").toString().replace("<hpAddr>", "").replace("</hpAddr>", "").trim();
			String ino_nm = detail.select("inoNm").toString().replace("<inoNm>", "").replace("</inoNm>", "").trim();
			String p_file_name = detail.select("pFileName").toString().replace("<pFileName>", "").replace("</pFileName>", "").trim();
			String torg_par_grad = detail.select("torgParGrad").toString().replace("<torgParGrad>", "").replace("</torgParGrad>", "").trim();
			
			HRD_Trainst_Info_Vo vo = new HRD_Trainst_Info_Vo(trainst_cst_id, p_file_name, file_path, ino_nm, addr1, addr2, tel_no, hp_addr, torg_par_grad);
			
			logger.info("기관정보 vo 반환: {}",vo);
			
			lists.add(vo);
		}
		return lists;
	}
	
	/**
	 * 과정정보를 담은 vo를 반환하는 메소드
	 * @param srchTraArea1
	 * @return
	 * @throws ParseException 
	 * @throws IOException 
	 */
	@SuppressWarnings({ "unchecked", "unused" })
	public List<HRD_Trpr_Info_Vo> trprInfo(String srchTraArea1) throws ParseException, IOException{
		
		//목록정보 호출
		Elements els = hrdList(srchTraArea1);
		
		List<HRD_Trpr_Info_Vo> lists = new ArrayList<HRD_Trpr_Info_Vo>();
		
		for(Element el: els) {
			
			//기관 id
			String trainst_cst_id = el.select("trainstCstId").toString().replace("<trainstCstId>", "").replace("</trainstCstId>", "").trim();
			//과정회차
			String trpr_degr = el.select("trprDegr").toString().replace("<trprDegr>", "").replace("</trprDegr>", "").trim();
			
			String trpr_id = el.select("trprId").toString().replace("<trprId>", "").replace("</trprId>", "").trim();
			String address = el.select("address").toString().replace("<address>", "").replace("</address>", "").trim();
			String ncs_cd = el.select("ncsCd").toString().replace("<ncsCd>", "").replace("</ncsCd>", "").trim();
			
			String tra_end_date1 = el.select("traEndDate").toString().replace("<traEndDate>", "").replace("</traEndDate>", "").trim();
			Date tra_end_date = fm2.parse(tra_end_date1);
			String tra_start_date1 = el.select("traStartDate").toString().replace("<traStartDate>", "").replace("</traStartDate>", "").trim();
			Date tra_start_date = fm2.parse(tra_start_date1);
			
			//과정정보 조회
			Elements detail = getdetailurl(trpr_id, trpr_degr);
//			System.out.println("왜 안되지???????????"+detail);
			String trpr_chap = detail.select("trprChap").toString().replace("<trprChap>", "").replace("</trprChap>", "").trim();
			String trpr_chap_email = detail.select("trprChapEmail").toString().replace("<trprChapEmail>", "").replace("</trprChapEmail>", "").trim();
			String trpr_chap_tel = detail.select("trprChapTel").toString().replace("<trprChapTel>", "").replace("</trprChapTel>", "").trim();
			String trpr_nm = detail.select("trprNm").toString().replace("<trprNm>", "").replace("</trprNm>", "").trim();
			String ncs_nm = detail.select("ncsNm").toString().replace("<ncsNm>", "").replace("</ncsNm>", "").trim();
			String trtm = detail.select("trtm").toString().replace("<trtm>", "").replace("</trtm>", "").trim();
			
			
			//시설정보 리스트
			Elements detailFacil = getdetailurlFacil(trpr_id, trpr_degr);
			String facil_info_list = facilInfoList(detailFacil);
			
			// 장비정보 리스트
			
			Elements detailEqnm = getdetailurlEqnm(trpr_id, trpr_degr);
			String eqmn_info_list = eqnmInfoList(detailEqnm);
			
			HRD_Trpr_Info_Vo vo = new HRD_Trpr_Info_Vo(trpr_id, trpr_nm, tra_start_date, tra_end_date, trtm, trainst_cst_id, address, ncs_nm, ncs_cd, trpr_chap, trpr_chap_tel, trpr_chap_email, trpr_degr, facil_info_list, eqmn_info_list);
			
			logger.info("과정정보 vo 반환: {}",vo);
			
			lists.add(vo);
		}
		return lists;
	}
	
}
