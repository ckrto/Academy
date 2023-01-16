package com.example.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

public class EchoHandler extends TextWebSocketHandler{
	
	List<WebSocketSession> sessions = new ArrayList<>();
	//웹소켓 연결이 끊어졌을 때
	@Override
	public void afterConnectionClosed(WebSocketSession session, CloseStatus status) throws Exception {
		super.afterConnectionClosed(session, status);
		sessions.remove(session);
		System.out.println("연결 끊김 : " + session.getId());
	}

	//연결이 완료되었을 때
	@Override
	public void afterConnectionEstablished(WebSocketSession session) throws Exception {
		super.afterConnectionEstablished(session);
		sessions.add(session);
		System.out.println("연결됨 : " + session.getId());
	}

	//클라이언트에서 메세지가 전송되었을 때
	@Override
	protected void handleTextMessage(WebSocketSession session, TextMessage message) throws Exception {
		super.handleTextMessage(session, message);
	}
	
}
