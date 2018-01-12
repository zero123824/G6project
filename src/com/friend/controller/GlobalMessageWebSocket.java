package com.friend.controller;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
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

import com.friend.model.FriendService;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.member.model.MemberVO;

//設定檔案WSbindServletConfiguration 綁定請求用戶的httpsession
//將用戶session存入set中，避免多次開啟websocket通道
@ServerEndpoint(value="/GlobalMessage/{Pagefrom}",configurator = WSbindServletConfiguration.class)
public class GlobalMessageWebSocket {
    private EndpointConfig config;
	private static final Map<MemberVO,Session> onlineUser = Collections.synchronizedMap(new HashMap<MemberVO,Session>());
	private static final Map<MemberVO,Set<MemberVO>> userFriendmap = Collections.synchronizedMap(new HashMap<MemberVO,Set<MemberVO>>());
	private static final FriendService friendSvc = new FriendService();
    
	@OnOpen
	public void onOpen(Session userSession,EndpointConfig config){
		this.config = config;
		HttpSession session = (HttpSession)config.getUserProperties().get("httpSession");
		MemberVO memberVO = (MemberVO)session.getAttribute("member");
		onlineUser.put(memberVO,userSession);
		Set<MemberVO> friends = friendSvc.getOneMemFriends(memberVO.getMember_id());
		userFriendmap.put(memberVO, friends);
	} 
    
	@OnMessage
	public void onMessage(@PathParam("Pagefrom") String pageFrom,Session userSession, String message){
		HttpSession session = (HttpSession)config.getUserProperties().get("httpSession");
		MemberVO memberVO = (MemberVO)session.getAttribute("member");
		String name = memberVO.getMember_lastname()+memberVO.getMember_firstname();
		List<Integer> list = new ArrayList<Integer>();
		for(MemberVO friend : userFriendmap.get(memberVO)){
			Session friendSession = onlineUser.get(friend);
			if(friendSession != null){
				JsonObject jsonObject = new JsonObject();
				jsonObject.addProperty("property", "isOnline");
				jsonObject.addProperty("message", name+"上線了!!");
				friendSession.getAsyncRemote().sendText(jsonObject.toString());
			}
			if("memberlist.jsp".equals(pageFrom) && onlineUser.containsKey(friend)){
				list.add(friend.getMember_id());
			}			
		}
		JsonObject jsonObject = new JsonObject();
		jsonObject.addProperty("property", "getFreind");
		jsonObject.addProperty("online",list.toString());
		userSession.getAsyncRemote().sendText(jsonObject.toString());
	}

	@OnError
	public void onError(Session userSession, Throwable e){
		e.printStackTrace();
	}

	@OnClose
	public void onClose(Session userSession, CloseReason reason){
		HttpSession session = (HttpSession)config.getUserProperties().get("httpSession");
		MemberVO memberVO = (MemberVO)session.getAttribute("member");
		onlineUser.remove(memberVO);
	}
}
