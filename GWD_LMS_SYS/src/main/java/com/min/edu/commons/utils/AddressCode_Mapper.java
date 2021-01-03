package com.min.edu.commons.utils;

import java.util.HashMap;
import java.util.Map;

public class AddressCode_Mapper {
	public static int AddressCodeMapper(String str) {
		Map<String, Integer> map = new HashMap<String, Integer>();
		map.put ("강남구",	11680);
		map.put ("강동구",	11740);
		map.put ("강북구",	11305);
		map.put ("강서구",	11500);
		map.put ("관악구",	11620);
		map.put ("광진구",	11215);
		map.put ("구로구",	11530);
		map.put ("금천구",	11545);
		map.put ("노원구",	11350);
		map.put ("도봉구",	11320);
		map.put ("동대문구",	11230);
		map.put ("동작구",	11590);
		map.put ("마포구",	11440);
		map.put ("서대문구",	11410);
		map.put ("서초구",	11650);
		map.put ("성동구",	11200);
		map.put ("성북구",	11290);
		map.put ("송파구",	11710);
		map.put ("양천구",	11470);
		map.put ("영등포구",	11560);
		map.put ("용산구",	11170);
		map.put ("은평구",	11380);
		map.put ("종로구",	11110);
		map.put ("중구",		11140);
		map.put ("중랑구",	11260);
		map.put ("가평군",	41820);
		map.put ("고양시",	41280);
		map.put ("고양시 덕양구",41281);
		map.put ("고양시 일산구",41283);
		map.put ("과천시",	41290);
		map.put ("광명시",	41210);
		map.put ("광주시",	41610);
		map.put ("구리시",	41310);
		map.put ("군포시",	41410);
		map.put ("김포시",	41570);
		map.put ("남양주시",	41360);
		map.put ("동두천시",	41250);
		map.put ("부천시",	41190);
		map.put ("성남시",	41130);
		map.put ("성남시 분당구",41135);
		map.put ("성남시 수정구",41131);
		map.put ("성남시 중원구",41133);
		map.put ("수원시",41110);
		map.put ("수원시 권선구",41113);
		map.put ("수원시 영통구",41117);
		map.put ("수원시 장안구",41111);
		map.put ("수원시 팔달구",41115);
		map.put ("시흥시",41390);
		map.put ("안산시",41270);
		map.put ("안산시 단원구",41273);
		map.put ("안산시 상록구",41271);
		map.put ("안성시",41550);
		map.put ("안양시",41170);
		map.put ("안양시 동안구",41173);
		map.put ("안양시 만안구",41171);
		map.put ("양주시",41630);
		map.put ("양평군",41830);
		map.put ("여주시",41670);
		map.put ("연천군",41800);
		map.put ("오산시",41370);
		map.put ("용인시",41460);
		map.put ("용인시 기흥구",41463);
		map.put ("용인시 수지구",41465);
		map.put ("용인시 처인구",41461);
		map.put ("의왕시",	41430);
		map.put ("의정부시",	41150);
		map.put ("이천시",	41220);
		map.put ("파주시",	41480);
		map.put ("포천군",	41810);
		map.put ("포천시",	41650);
		map.put ("하남시",	41450);
		map.put ("화성시",	41590);
		
		int temp = 0;
		String[] s = str.split(" ");
		for(int i =0 ; i <s.length; i++) {
			
			if(map.containsKey(s[i])) {
				temp = map.get(s[i]);
			}
			
		}
		
		return  map.get(temp);

	}
}
