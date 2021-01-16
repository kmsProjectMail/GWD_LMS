package com.min.edu.commons.utils;

import java.util.Properties;

import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import org.springframework.stereotype.Component;


@Component
public class MailSender {
	
	public static void gmailSender(String _targetName, String _targetEmail,String answer) {
		final String user ="kmsProjectMail";//발신자의 이메일 아이디
		final String password = "msProject(1)"; //발신자의 이메일 비밀번호 
		
		//property에 SMTP 서버 정보를 설정한다
		Properties prop = new Properties();
		prop.put("mail.smtp.host", "smtp.gmail.com");
		prop.put("mail.smtp.port", 465);
		prop.put("mail.smtp.auth", "true");
		prop.put("mail.smtp.ssl.enable", "true");
		prop.put("mail.smtp.ssl.trust", "smtp.gmail.com");

		
		//SMTP 서버 정보와 사용자 정보를 기반으로 Session 클래스를 인스턴스화 한다
		Session mailSession = Session.getDefaultInstance(prop, new Authenticator() {
			protected PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication(user, password);
			}
		});
		
		try {
			//Message 클래스의 객체를 사용해 수신자와 내용, 제목을 입력한다.
			InternetAddress receiver = new InternetAddress(_targetEmail);
			MimeMessage msg = new MimeMessage(mailSession);
			msg.setFrom(new InternetAddress(user));
			//수신자 메일 주소
			msg.addRecipient(Message.RecipientType.TO, receiver);
			
			//메일 제목을 입력
			msg.setSubject(_targetName+"님 GWD_LMS 시스템의 인증번호입니다");
			
			//메일 내용을 입력
			msg.setText("인증번호는"+answer+"입니다");
			
			
			//전송
			Transport.send(msg);
		} catch (AddressException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (MessagingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	}
	
}
