package com.member.controller;


import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Enumeration;
import java.util.LinkedList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

import com.member.model.*;
import com.member_favor.controller.GetRecommendMovie;
import com.member_favor.model.MemberFavorDAO;
import com.member_favor.model.MemberFavorVO;

@WebServlet("/Login")
public class Login extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private Connection con = null;
	private PreparedStatement pstmt = null;

    public Login() {
        super();
    }
    
	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		doPost(req,res);
	}

	
	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		HttpSession session = req.getSession();
		res.setContentType("text/html; charset=UTF-8");
	    req.setCharacterEncoding("UTF-8");
		PrintWriter out = res.getWriter();
		List<String> errorMsgs = new LinkedList<String>();
		req.setAttribute("errorMsgs", errorMsgs);


		String action = req.getParameter("action");
		if("login".equals(action)){
			String member_account = req.getParameter("member_account");
			String member_psw = req.getParameter("member_psw");	
			MemberVO member = new MemberVO();
			MemberDAO memberDAO = new MemberDAO();
			member = memberDAO.findByAccount(member_account);
			if(member!=null && member.getMember_psw().equals(member_psw)){
				session.setAttribute("member", member);					
//				session.setAttribute("orderrecord", getOrderlist(member.getMember_id()));
				getMemberFavor(member.getMember_id());
				GetRecommendMovie grm = new GetRecommendMovie();
				grm.getRecommendMovie_ID(member.getMember_id());
				String url="membercenter.jsp";
				res.sendRedirect(url);		
			}else{
				errorMsgs.add("帳密錯誤");
				RequestDispatcher failureView = req.getRequestDispatcher("index.jsp");
				failureView.forward(req, res);
			}
		}	
		if("logout".equals(action)){
			String url = String.valueOf(session.getAttribute("from"));
			Enumeration<String> en = session.getAttributeNames();
			while(en.hasMoreElements()){
				String name = String.valueOf(en.nextElement());
				session.removeAttribute(name);
			}
			res.sendRedirect(url);
		}
	}
////取得訂票紀錄
//	private JsonArray getOrderlist(Integer member_id){
//		try {
//			pstmt = con.prepareStatement("SELECT SCHEDULE_DATE,TICKET_LIST.SCHEDULE_ID,MOVIE_NAME_CH FROM MOVIE "
//					+ "JOIN MOVIE_SCHEDULE ON MOVIE.MOVIE_ID = MOVIE_SCHEDULE.MOVIE_ID "
//					+ "JOIN TICKET_LIST ON TICKET_LIST.SCHEDULE_ID = MOVIE_SCHEDULE.SCHEDULE_ID "
//					+ "JOIN ORDER_LIST ON ORDER_LIST.ORDER_ID = TICKET_LIST.ORDER_ID "
//					+ "WHERE MEMBER_ID = ? "
//					+ "GROUP BY SCHEDULE_DATE,TICKET_LIST.SCHEDULE_ID,MOVIE_NAME_CH");
//			pstmt.setInt(1, member_id);
//			ResultSet rs = pstmt.executeQuery();
////將電影名稱、場次紀錄放入jsonarray			
//			JsonArray ja = new JsonArray();
//			
//			while(rs.next()){				
//				
//				JsonObject jo = new JsonObject();
//				jo.addProperty("date", String.valueOf(rs.getDate("SCHEDULE_DATE")));
//				jo.addProperty("moviename", rs.getString("MOVIE_NAME_CH"));
//				ja.add(jo);
//			}
//			return ja;
//		} catch (SQLException e) {
//			e.printStackTrace();
//			return null;
//		}
//	}
	
//取得會員喜好類型
	private List<MemberFavorVO> getMemberFavor(Integer member_id){
		MemberFavorDAO mfd = new MemberFavorDAO();
		List<MemberFavorVO> memfavorList;
		memfavorList = mfd.getOneMemFavor(member_id);
		return memfavorList;
	}
}













//try {
//pstmt = con.prepareStatement("SELECT MEMBER_ACCOUNT,MEMBER_PSW,MEMBER_ID FROM MEMBER_INFO WHERE MEMBER_ACCOUNT= ?");
//pstmt.setString(1, member_account);			
//ResultSet rs = pstmt.executeQuery();
//while(rs.next()){
//	if(rs.getString("MEMBER_PSW").equals(member_psw)){
////需修改,改為放入memberVO物件
//		session.setAttribute("member", member_account);					
//		session.setAttribute("orderrecord", getOrderlist(rs.getInt("MEMBER_ID")));
//		getRecommendMovie_ID(rs.getInt("MEMBER_ID"));
//		String url="membercenter.jsp";
//		res.sendRedirect(url);					
//	}
//	else{
//	
//		out.println("<HTML>");
//        out.println("<HEAD><TITLE>error</TITLE></HEAD>");
//        out.println("<BODY>");
//        out.println("帳密錯誤!");
//        out.println("</BODY></HTML>");
//	}				
//}
//} catch (SQLException e1) {
//e1.printStackTrace();
//}
