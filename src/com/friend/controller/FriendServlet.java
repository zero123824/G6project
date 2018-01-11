package com.friend.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONArray;

import com.friend.model.FriendService;
import com.friend.model.FriendVO;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

public class FriendServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
   
    public FriendServlet() {
        super();
    }

	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		res.setContentType("text/html; charset=UTF-8");
	    req.setCharacterEncoding("UTF-8");
	    String action = req.getParameter("action");
	    PrintWriter out = res.getWriter();
	    
	    if("getfriendsStatus".equals(action)){
	    	Integer member_id1 = Integer.valueOf(req.getParameter("member_id"));
	    	Integer member_id2 = Integer.valueOf(req.getParameter("friendID"));	    	
	    	FriendService friendSvc = new FriendService();
	    	List<FriendVO> relations = friendSvc.getFriendRelation(member_id1);
			Integer unread = 0;
	    	Integer memberPosition = 1;
	    	JSONArray jsonArray = new JSONArray();
	    	for(FriendVO relationInTwo : relations){
		    	if(relationInTwo.getRelation_status() == null){
		    		relationInTwo = friendSvc.getRelationInTwo(member_id2,member_id1);
		    		unread = relationInTwo.getMember_id2_unread();
		    		memberPosition = 2;
		    	}
	    		unread = relationInTwo.getMember_id1_unread();
	    		JsonObject jsonObject = new JsonObject();
		    	jsonObject.addProperty("unread", unread);
		    	jsonObject.addProperty("position", memberPosition);
		    	jsonArray.put(jsonObject);
	    	}
	    	
	    	out.print(jsonArray.toString());
	    	return;
	    }
	    
	    
/**查詢與好友的訊息**/
	    if("getmsgs".equals(action)){
	    	Integer unreadmsgs = 0;
	    	try{
		    	Integer member_id1 = Integer.valueOf(req.getParameter("member_id"));
		    	Integer member_id2 = Integer.valueOf(req.getParameter("myfriendID"));
		    	
		    	FriendService friendSvc = new FriendService();
		    	FriendVO relationInTwo = friendSvc.getRelationInTwo(member_id1, member_id2);
		    	Integer memberPosition = 1;
		    	if(relationInTwo.getRelation_status() == null){
		    		relationInTwo = friendSvc.getRelationInTwo(member_id2,member_id1);
		    		memberPosition = 2;
		    	}
		    	String msgs = relationInTwo.getMember_msg();
		    	Integer relation_status = relationInTwo.getRelation_status();
		    	Integer msg_status = relationInTwo.getMsg_status();		    	
		    	switch(msg_status){
			    	case 0:
			    		if(memberPosition==2){System.out.println(member_id2+"向你發送了訊息");unreadmsgs++;}
			    		else if(memberPosition==1){System.out.println(member_id2+"你發送了訊息");}
			    		break;
			    	case 1:break;
			    	case 2:
			    		if(memberPosition==1){System.out.println(member_id1+"向你發送了訊息");unreadmsgs++;}
			    		else if(memberPosition==2){System.out.println(member_id1+"你發送了訊息");}
			    		break;
			    	case 3:break;
		    	
		    	}
		    	JsonObject jsonObject = new JsonObject();
		    	jsonObject.addProperty("relation_status", relation_status);
		    	jsonObject.addProperty("message", msgs);
		    	jsonObject.addProperty("position", memberPosition);
		    	out.print(jsonObject.toString());
		    	return;

	    	}catch(Exception e){
	    		e.printStackTrace();
	    	}
	    }
//請求加為好友
	    if("pending".equals(action)){
	    	Integer member_id1 = Integer.valueOf(req.getParameter("member_id"));
	    	Integer member_id2 = Integer.valueOf(req.getParameter("friendID"));
	    	Long nowtime = new java.util.Date().getTime();
	    	FriendService friendSvc = new FriendService();
	    	friendSvc.add(member_id1, member_id2, "",nowtime);	    	
	    }
	    
	    if("befriend".equals(action)){
	    	Integer member_id1 = Integer.valueOf(req.getParameter("member_id"));
	    	Integer member_id2 = Integer.valueOf(req.getParameter("friendID"));
	    	Long nowtime = new java.util.Date().getTime();
	    	FriendService friendSvc = new FriendService();
	    	friendSvc.update(member_id2, member_id1, 2, "", 0,nowtime);
	    	out.print("beingFriend");
	    	return;
	    }
	}
}
