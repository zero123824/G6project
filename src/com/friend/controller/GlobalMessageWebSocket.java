package com.friend.controller;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

import javax.servlet.http.HttpSession;
import javax.websocket.CloseReason;
import javax.websocket.EndpointConfig;
import javax.websocket.OnClose;
import javax.websocket.OnError;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.PathParam;
import javax.websocket.server.ServerEndpoint;

import com.member.model.MemberVO;

//設定檔案WSbindServletConfiguration 綁定請求用戶的httpsession
//將用戶session存入set中，避免多次開啟websocket通道
@ServerEndpoint(value="/GlobalMessage/{member_id}/{action}",configurator = WSbindServletConfiguration.class)
public class GlobalMessageWebSocket {
    private EndpointConfig config;
	private static final Set<MemberVO> onlineMember = Collections.synchronizedSet(new HashSet<MemberVO>());
    
	@OnOpen
	public void onOpen(@PathParam("member_id") String member_id,Session userSession,EndpointConfig config){
		this.config = config;
		HttpSession session = (HttpSession)config.getUserProperties().get("httpSession");
		MemberVO memberVO = (MemberVO)session.getAttribute("member");
		onlineMember.add(memberVO);
	} 
    
	@OnMessage
	public void onMessage(@PathParam("action") String action ,Session userSession, String message){
		userSession.getAsyncRemote().sendText(message);

	}

	@OnError
	public void onError(Session userSession, Throwable e){
		
	}

	@OnClose
	public void onClose(Session userSession, CloseReason reason){
		HttpSession session = (HttpSession)config.getUserProperties().get("httpSession");
		MemberVO memberVO = (MemberVO)session.getAttribute("member");
		onlineMember.remove(memberVO);
	}
}
