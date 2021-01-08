package com.min.edu.ctrl;

import java.util.HashMap;
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
import org.springframework.web.bind.annotation.ResponseBody;

import com.min.edu.commons.utils.AddressCode_Mapper;
import com.min.edu.service.IServiceHrd;
import com.min.edu.vo.HRD_Trainst_Info_Vo;

@Controller
public class GoogleMapController {
	private static final Logger logger = LoggerFactory.getLogger(LoginController.class);

	@Autowired
	private IServiceHrd hrdService;
	
	@SuppressWarnings("unchecked")
	@RequestMapping(value = "/searchAddress.do" , method = RequestMethod.GET ,produces = "application/text; charset=UTF-8;")
	@ResponseBody
	public String searchAddress(String val , String source) {
		logger.info("welcome searchAddress ! {} , {}",val, source );
		
		JSONObject jobj = new JSONObject();
		JSONArray jarr = new JSONArray();
		Map<String, String> map = new HashMap<String, String>();
		if(Integer.parseInt(val) == 1) {
			map.put("addr1", source);
		}else {
			map.put("ino_nm", source);
		}
		List<HRD_Trainst_Info_Vo> lists = hrdService.alltrainstinfo(map);
		System.out.println("결과값 : "+lists.toString());
		for(HRD_Trainst_Info_Vo v : lists) {
			JSONObject jo = new JSONObject();
			jo.put("name", v.getIno_nm());
			jo.put("addr1",v.getAddr1());
			jo.put("addr2",v.getAddr2());
			jo.put("phone", v.getTel_no());
			jarr.add(jo);
		}
		jobj.put("lists", jarr );
		System.out.println(jobj.toJSONString());
		return jobj.toJSONString();
	}
	
	@SuppressWarnings("unchecked")
	@RequestMapping(value = "/returnaddress.do" , method = RequestMethod.GET ,produces = "application/text; charset=UTF-8;")
	@ResponseBody
	public String returnaddress(String val1 , String val2) {
		String result = "";
		result = result.concat(AddressCode_Mapper.AddressCodeMapper(val1)).concat(AddressCode_Mapper.AddressCodeMapper(val2));
		return result;
	}
}
