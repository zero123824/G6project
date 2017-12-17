package com.member.controller;


import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.naming.Context;
import javax.naming.NamingException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.sql.DataSource;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

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
		Context ctx;
		HttpSession session = req.getSession();
		res.setContentType("text/html; charset=UTF-8");
	    req.setCharacterEncoding("UTF-8");
		PrintWriter out = res.getWriter();
		try {
			ctx = new javax.naming.InitialContext();
			DataSource ds = (DataSource) ctx.lookup("java:comp/env/jdbc/movie");
			con = ds.getConnection();
		} catch (NamingException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		String member_account = req.getParameter("member_account");
		String member_psw = req.getParameter("member_psw");		
		try {
			pstmt = con.prepareStatement("SELECT MEMBER_ACCOUNT,MEMBER_PSW,MEMBER_ID FROM MEMBER_INFO WHERE MEMBER_ACCOUNT= ?");
			pstmt.setString(1, member_account);			
			ResultSet rs = pstmt.executeQuery();
			while(rs.next()){
				if(rs.getString("MEMBER_PSW").equals(member_psw)){
//需修改,改為放入memberVO物件
					session.setAttribute("member", member_account);					
					session.setAttribute("orderrecord", getOrderlist(rs.getInt("MEMBER_ID")));
					getRecommendMovie_ID(rs.getInt("MEMBER_ID"));
					String url="membercenter.jsp";
					res.sendRedirect(url);					
				}
				else{
				
					out.println("<HTML>");
			        out.println("<HEAD><TITLE>error</TITLE></HEAD>");
			        out.println("<BODY>");
			        out.println("帳密錯誤!");
			        out.println("</BODY></HTML>");
				}				
			}
		} catch (SQLException e1) {
			e1.printStackTrace();
		}
	}

//取得訂票紀錄
	private JsonArray getOrderlist(Integer member_id){
		try {
			pstmt = con.prepareStatement("SELECT SCHEDULE_DATE,TICKET_LIST.SCHEDULE_ID,MOVIE_NAME_CH FROM MOVIE "
					+ "JOIN MOVIE_SCHEDULE ON MOVIE.MOVIE_ID = MOVIE_SCHEDULE.MOVIE_ID "
					+ "JOIN TICKET_LIST ON TICKET_LIST.SCHEDULE_ID = MOVIE_SCHEDULE.SCHEDULE_ID "
					+ "JOIN ORDER_LIST ON ORDER_LIST.ORDER_ID = TICKET_LIST.ORDER_ID "
					+ "WHERE MEMBER_ID = ? "
					+ "GROUP BY SCHEDULE_DATE,TICKET_LIST.SCHEDULE_ID,MOVIE_NAME_CH");
			pstmt.setInt(1, member_id);
			ResultSet rs = pstmt.executeQuery();
//將電影名稱、場次紀錄放入jsonarray			
			JsonArray ja = new JsonArray();
			
			while(rs.next()){				
				
				JsonObject jo = new JsonObject();
				jo.addProperty("date", String.valueOf(rs.getDate("SCHEDULE_DATE")));
				jo.addProperty("moviename", rs.getString("MOVIE_NAME_CH"));
				ja.add(jo);
			}
			return ja;
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
	}
	
//取得推薦電影的ID並呼叫電影資訊方法
	private void getRecommendMovie_ID(Integer member_id){
		try {
			pstmt = con.prepareStatement("SELECT MEMBER_ID,GENRE_NAME,MOVIE_GENRE.MOVIE_ID,MOVIE_NAME_CH FROM MEMBER_FAVOR "
					+ "JOIN GENRE ON MEMBER_FAVOR.GENRE_ID = GENRE.GENRE_ID "
					+ "JOIN MOVIE_GENRE ON MOVIE_GENRE.GENRE_ID = GENRE.GENRE_ID "
					+ "JOIN MOVIE ON MOVIE_GENRE.MOVIE_ID = MOVIE.MOVIE_ID "
					+ "WHERE MEMBER_ID = ?");
			
			pstmt.setInt(1, member_id);
			ResultSet rs = pstmt.executeQuery();
			while(rs.next()){
				rs.getString("GENRE_NAME");
				rs.getString("MOVIE_NAME_CH");
				System.out.println(rs.getString("GENRE_NAME"));
				System.out.println(rs.getString("MOVIE_NAME_CH"));
				getMovieDate(rs.getString("MOVIE_NAME_CH"));
				

			}
		
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

////取得推薦電影的資訊
	private void getMovieDate(String moviename){
		try {
			pstmt = con.prepareStatement("SELECT MOVIE_NAME_CH,MOVIE_DATE,MOVIE_INTRODUCE "
					+ "FROM MOVIE "
					+ "WHERE MOVIE_NAME_CH = ?");
			pstmt.setString(1, moviename);
			ResultSet rs = pstmt.executeQuery();
			while(rs.next()){
				String name = rs.getString("MOVIE_NAME_CH");
				rs.getDate("MOVIE_DATE");
				rs.getString("MOVIE_INTRODUCE");
				System.out.println(String.valueOf(rs.getDate("MOVIE_DATE")));
				System.out.println(rs.getClob("MOVIE_INTRODUCE"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
