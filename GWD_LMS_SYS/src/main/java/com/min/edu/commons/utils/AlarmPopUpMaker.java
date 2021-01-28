package com.min.edu.commons.utils;

import java.util.List;

import com.min.edu.dto.CalendarDto;

public class AlarmPopUpMaker {
    
    
   
    
	public static String alarmMaker(List<CalendarDto> lists, int num, String myId) {
		System.out.println("alramMaker 동작");
		String html = "";
		html +="<thead>"+
				"<tr style='background: #2C3E50; color: snow;  text-align: center;font-size: medium;'><th>"+
				"일정알람"+
				"</th></tr></thead><tbody>";
		int index = num;
		for(CalendarDto d : lists) {
			if(index-- >0) {
				html += "<tr class='alarmtable_tr' style ='background-color : aliceblue;'>";
			}else {
				html += "<tr class='alarmtable_tr' style ='background-color : lightgrey;'>";
				
			}
			
			
			
			html += "<th><label style=' text-align: center;  width : 100%; font-size: small;'>"+answer(d.getStart())+" - "+answer(d.getEnd())+
					"</label>";
			html += "<label style=' text-align: center; width: 100%'>";
			html +=	"<strong>내용</strong> ";
			if(d.getMeet_id() !=null && d.getMeet_id().equals(myId)) {
				html += d.getStudent_id()+"님과의 상담예약";
			}else {
				html += d.getMeet_id()+"님과의 상담예약";
				
			}
			html +=	"</label></th></tr>";
		}
		html +="</tbody>";
		
		return html;
	}
	public static String answer(String str) {
		String year = str.substring(0,4);
		String month = str.substring(5,7);
		String day = str.substring(8,10);
		String hour = str.substring(11,13);
		
		return year+"년"+month+"월"+day+"일"+hour+"시";
	}
}
