package com.friend.controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.friend.model.FriendService;
import com.friend.model.FriendVO;

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
		    	System.out.println(unreadmsgs);
		    	System.out.println(msgs);		    	
		    	System.out.println(msg_status);
		    	out.print(msgs);
		    	return;

	    	}catch(Exception e){
	    		e.printStackTrace();
	    	}
	    }
	}

}
