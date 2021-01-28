package com.min.edu.socket;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import javax.websocket.OnClose;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.ServerEndpoint;

import org.json.simple.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.google.inject.Singleton;
import com.min.edu.service.IServiceCalendar;


/**
 * 웹소캣을 사용한 Server를 구현하여 해당 서버 내에서 로그인한 사용자를 확인하고 처리한다
 * @author YS_Kim
 * @since 2021.01.15
 * @version 0.01
 * */
@Component
@ServerEndpoint("/echoServer")
@Singleton
public class SocketAllLoginUser {
	static Set<Session> sessionUser = Collections.synchronizedSet(new HashSet<Session>());
	Logger logger = LoggerFactory.getLogger(this.getClass());
	
	
	
	@OnOpen
	public void handleOpen(Session userSession) {
		sessionUser.add(userSession);
	}
	
	@OnMessage
	public void handleMessage(Session userSession, String msg) throws IOException {
		Iterator<Session> iter = sessionUser.iterator();
		logger.info("handleMessage recevied msg : {}",msg);
		String dowhat = msg.substring(msg.indexOf("*")+1, msg.lastIndexOf("*"));
		
		String target = msg.substring(0,msg.indexOf("*"));
		logger.info("dowhat : {} \t target : {} ",dowhat,target);
		if(dowhat.equals("calendar")) {
			while(iter.hasNext()) {
				Session tempsession = iter.next();
				if(tempsession.getUserPrincipal().getName().equals(target)) {
					logger.info("iter.next() : {} \t  ",tempsession.getUserPrincipal().getName());
					logger.info("목적 아이디 < {} > 가 세션에 소켓에 등록되어 있습니다.",target);
					tempsession.getBasicRemote().sendText("calendar");
					break;
				}
			}
		}else {
			while(iter.hasNext()) {
				Session tempsession = iter.next();
				if(tempsession.getUserPrincipal().getName().equals(target)) {
					logger.info("iter.next() : {} \t  ",tempsession.getUserPrincipal().getName());
					logger.info("목적 아이디 < {} > 가 세션에 소켓에 등록되어 있습니다.",target);
					tempsession.getBasicRemote().sendText("chat");
					break;
				}
			}
		}
			
		
		logger.info("WebSocket echoHandler connected User : {} ", sessionUser.toString());
	}
	@OnClose
	public void handleClose(Session session) {
		logger.info("Websocket session remove comple [ID :{}]",session.getId() );
		sessionUser.remove(session);
	}
	
}
