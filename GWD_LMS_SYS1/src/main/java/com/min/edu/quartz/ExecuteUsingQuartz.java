package com.min.edu.quartz;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;

import com.min.edu.dto.Calender_Dto;
import com.min.edu.service.ServiceAlarm;

import net.nurigo.java_sdk.api.Message;
import net.nurigo.java_sdk.exceptions.CoolsmsException;

public class ExecuteUsingQuartz {

	@Autowired
	private ServiceAlarm dao;

	public void sendMessage() throws Exception {
		List<Calender_Dto> dto = dao.selectAlarm();
			for (Calender_Dto alarmDto : dto) {
				System.out.println("--->>>"+alarmDto.getAlarm_date());
					System.out.println(alarmDto);
				String d = alarmDto.getAlarm_date();
				SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
				Date dd = sdf.parse(d);
				Date time = new Date();
				System.out.println("0"+alarmDto.getStudent().getPhone());
				switch (time.getMinutes() - dd.getMinutes()) {
				case 0:
					System.out.println("T");
//					certifiedPhoneNumber("0"+alarmDto.getStudent().getPhone(), alarmDto.getStudent()+"님 "+alarmDto.getAlarm_date()+"에 예약 잡혀있습니다");
//					System.out.println("0"+alarmDto.getStudent().getPhone());
					continue;
				default:
					System.out.println("F");
					continue;
				}
			}
		}
	private void certifiedPhoneNumber(String phoneNumber, String message) {
		String api_key = ""; // coolSMS 사이트에서 받은 인증키NCSAFHLG7EUUYPTW
		String api_secret = "JDNOAGVDKENJNNKGL1T3BMAPHTTYOQSQ"; // coolSMS 자체에 발급된 비밀키
		Message coolsms = new Message(api_key, api_secret);

		// 4개의 입력값 필요
		HashMap<String, String> params = new HashMap<String, String>();
		params.put("to", phoneNumber); // 수신전화번호
		params.put("from", "010-4690-3675"); // 발신전화번호. 테스트시에는 발신,수신 둘다 본인 번호로 하면 됨 -> 발신전화 번호는 coolSMS에 등록해줘야됨
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
}
