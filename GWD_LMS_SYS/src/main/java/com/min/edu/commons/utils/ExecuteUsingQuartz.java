package com.min.edu.commons.utils;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;

import com.min.edu.dto.Calender_Dto;
import com.min.edu.service.IServiceHrd;
import com.min.edu.service.ServiceAlarm;

import net.nurigo.java_sdk.api.Message;
import net.nurigo.java_sdk.exceptions.CoolsmsException;

public class ExecuteUsingQuartz {

	@Autowired
	private ServiceAlarm dao;
	
	@Autowired
	private IServiceHrd service;

	public void sendMessage() throws Exception {
		Date dfouram = new Date();
		System.out.println(dfouram);
//		if(dfouram.getHours() == 04) {
//			service.insertJeesoo();
//		}
		List<Calender_Dto> dto = dao.selectAlarm();
			for (Calender_Dto alarmDto : dto) {
				System.out.println("--->>>"+alarmDto.getAlarm_date());
//					System.out.println(alarmDto);
				String aldate = alarmDto.getAlarm_date();
				SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
				Date alarmdate = sdf.parse(aldate);
				Date time = new Date();
				String timecut = time.toLocaleString().substring(0, 21);
				String alarmdatecut = alarmdate.toLocaleString().substring(0, 21);
				System.out.println(timecut.equalsIgnoreCase(alarmdatecut)?"true":"false");
				
//				System.out.println("0"+alarmDto.getStudent().getPhone());
//				String times = time.getYear()+"."+time.getMonth()+1+"."+time.getDate()+"-"+time.getHours();
//				String dds = dd.getYear()+"."+dd.getMonth()+1+"."+(dd.getDate()-1)+"-"+dd.getHours();
//				System.out.println(times == dds ?"true":"false");
//				System.out.println(times+"."+dds);
				
				
				if (time.getMinutes() - alarmdate.getMinutes() == 0&& timecut.equalsIgnoreCase(alarmdatecut)== true) {
					System.out.println("T");
//					certifiedPhoneNumber("0"+alarmDto.getStudent().getPhone(), alarmDto.getStudent()+"님 "+alarmDto.getAlarm_date()+"에 예약 잡혀있습니다");
//					System.out.println("0"+alarmDto.getStudent().getPhone());
					continue;
				}else {
					
					System.out.println("F");
					continue;
				}
			}
		}
	private void certifiedPhoneNumber(String phoneNumber, String message) {
		String api_key = "NCSKN9HYZ6GGTZ4A"; // coolSMS 사이트에서 받은 인증키NCSAFHLG7EUUYPTW
		String api_secret = "ALWDWNBOPSD7MVGMVSZLXA16JGJ7DXOY"; // coolSMS 자체에 발급된 비밀키
		Message coolsms = new Message(api_key, api_secret);

		// 4개의 입력값 필요
		HashMap<String, String> params = new HashMap<String, String>();
		params.put("to", phoneNumber); // 수신전화번호
		params.put("from", "010-8910-2198"); // 발신전화번호. 테스트시에는 발신,수신 둘다 본인 번호로 하면 됨 -> 발신전화 번호는 coolSMS에 등록해줘야됨
		params.put("type", "SMS"); // type 방식
		params.put("text", message);
		params.put("app_version", "test app 1.2"); // application name and version
		try {
			JSONObject obj = (JSONObject) coolsms.send(params);
			System.out.println(obj.toString());
		} catch (CoolsmsException e) {
			System.out.println(e.getMessage());
			System.out.println(e.getCode());
		}
	}
	
	public void sendMsg(String phoneNumber, String message) {
		String api_key = "NCSKN9HYZ6GGTZ4A"; // coolSMS 사이트에서 받은 인증키NCSAFHLG7EUUYPTW
		String api_secret = "ALWDWNBOPSD7MVGMVSZLXA16JGJ7DXOY"; // coolSMS 자체에 발급된 비밀키
		Message coolsms = new Message(api_key, api_secret);

		// 4개의 입력값 필요
		HashMap<String, String> params = new HashMap<String, String>();
		params.put("to", phoneNumber); // 수신전화번호
		params.put("from", "010-8910-2198"); // 발신전화번호. 테스트시에는 발신,수신 둘다 본인 번호로 하면 됨 -> 발신전화 번호는 coolSMS에 등록해줘야됨
		params.put("type", "SMS"); // type 방식
		params.put("text", message);
		params.put("app_version", "test app 1.2"); // application name and version
		try {
			JSONObject obj = (JSONObject) coolsms.send(params);
			System.out.println(obj.toString());
		} catch (CoolsmsException e) {
			System.out.println(e.getMessage());
			System.out.println(e.getCode());
		}
	}
	private void insertJeesoo() {
		System.out.println("insertTEST");
	}
}
