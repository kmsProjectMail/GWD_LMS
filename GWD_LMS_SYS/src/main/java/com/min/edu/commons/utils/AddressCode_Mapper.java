package com.min.edu.commons.utils;

import java.util.HashMap;
import java.util.Map;

public class AddressCode_Mapper {
	
	static Map<String, String> map = new HashMap<String, String>();
	
	static {
		
		map.put("0", "0");
		map.put("11", "서울특별시 ");
		map.put("41", "경기도 ");
		map.put("11680", "강남구");
		map.put("11740", "강동구");
		map.put("11305", "강북구");
		map.put("11500", "강서구");
		map.put("11620", "관악구");
		map.put("11215", "광진구");
		map.put("11530", "구로구");
		map.put("11545", "금천구");
		map.put("11350", "노원구");
		map.put("11320", "도봉구");
		map.put("11230", "동대문구");
		map.put("11590", "동작구");
		map.put("11440", "마포구");
		map.put("11410", "서대문");
		map.put("11650", "서초구");
		map.put("11200", "성동구");
		map.put("11290", "성북구");
		map.put("11710", "송파구");
		map.put("11470", "양천구");
		map.put("11560", "영등포구");
		map.put("11170", "용산구");
		map.put("11380", "은평구");
		map.put("11110", "종로구");
		map.put("11140", "중구");
		map.put("11260", "중랑구");
		map.put("41820", "가평군");
		map.put("41280", "고양시");
		map.put("41281", "고양시 덕양구");
		map.put("41283", "고양시 일산구");
		map.put("41290", "과천시");
		map.put("41210", "광명시");
		map.put("41610", "광주시");
		map.put("41310", "구리시");
		map.put("41410", "군포시");
		map.put("41570", "김포시");
		map.put("41360", "남양주시");
		map.put("41250", "동두천시");
		map.put("41190", "부천시");
		map.put("41130", "성남시");
		map.put("41135", "성남시 분당구");
		map.put("41131", "성남시 수정구");
		map.put("41133", "성남시 중원구");
		map.put("41110", "수원시");
		map.put("41113", "수원시 권선구");
		map.put("41117", "수원시 영통구");
		map.put("41111", "수원시 장안구");
		map.put("41115", "수원시 팔달구");
		map.put("41390", "시흥시");
		map.put("41270", "안산시");
		map.put("41273", "안산시 단원구");
		map.put("41271", "안산시 상록구");
		map.put("41550", "안성시");
		map.put("41170", "안양시");
		map.put("41173", "안양시 동안구");
		map.put("41171", "안양시 만안구");
		map.put("41630", "양주시");
		map.put("41830", "양평군");
		map.put("41670", "여주시");
		map.put("41800", "연천군");
		map.put("41370", "오산시");
		map.put("41460", "용인시");
		map.put("41463", "용인시 기흥구");
		map.put("41465", "용인시 수지구");
		map.put("41461", "용인시 처인구");
		map.put("41430", "의왕시");
		map.put("41150", "의정부시");
		map.put("41220", "이천시");
		map.put("41480", "파주시");
		map.put("41810", "포천군");
		map.put("41650", "포천시");
		map.put("41450", "하남시");
		map.put("41590", "화성시");
	}
	
	public static String AddressCodeMapper(String str) {
		return map.get(str);

	}
}
