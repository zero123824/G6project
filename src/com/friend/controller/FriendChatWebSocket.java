package com.friend.controller;

import java.util.Collections;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

import javax.websocket.CloseReason;
import javax.websocket.OnClose;
import javax.websocket.OnError;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.PathParam;
import javax.websocket.server.ServerEndpoint;

@ServerEndpoint("/FriendChat/{friendID}/{myID}")
public class FriendChatWebSocket {

private static final Map<String,Set<Session>> allRelationAndSessions = new ConcurrentHashMap<>();
	
	@OnOpen
	public void onOpen(@PathParam("friendID") String friendID, @PathParam("myID") String myID, Session userSession) {
		String relation = relationChecked(friendID,myID);
		Set<Session> sessionInRelation = allRelationAndSessions.get(relation);
		if(sessionInRelation == null){
			sessionInRelation = new HashSet<Session>();
		}
		//將user的session放入關係集合裡
		sessionInRelation.add(userSession);
		//將進入的user放入map中
		allRelationAndSessions.put(relation,sessionInRelation);
		System.out.println(userSession.getId()+":已連線");
	}

	@OnMessage
	public void OnMessage(@PathParam("friendID") String friendID, @PathParam("myID") String myID ,Session userSession, String message){
		String relation = relationChecked(friendID,myID);
		Set<Session> sessionInRelation = allRelationAndSessions.get(relation);
		for (Session session : sessionInRelation) {
			if (session.isOpen())
				session.getAsyncRemote().sendText(message);
		}
		System.out.println("Message received: " + message);
	}
	
	@OnError
	public void onError(Session userSession, Throwable e){
		
	}
	
	@OnClose
	public void onClose(@PathParam("friendID") String friendID, @PathParam("myID") String myID ,Session userSession, CloseReason reason){
		String relation = relationChecked(friendID,myID);
		Set<Session> sessionInRelation = allRelationAndSessions.get(relation);
		sessionInRelation.remove(userSession);
		System.out.println(userSession.getId() + ": Disconnected: " + Integer.toString(reason.getCloseCode().getCode()));
	}
	
	//將兩個userID轉換為唯一關係
	private String relationChecked(String friendID, String myID) {
		Double friendIDPI = (Double.valueOf(friendID)-1000000000)*Math.PI;
		Double myIDPI = (Double.valueOf(myID)-1000000000)*Math.PI;
		Double key = (friendIDPI + myIDPI)/(friendIDPI*myIDPI);
		String relation = String.valueOf(key);
		return relation;	
	}
}
