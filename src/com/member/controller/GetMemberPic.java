package com.member.controller;

import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.naming.Context;
import javax.naming.NamingException;
import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.UnavailableException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;

public class GetMemberPic extends HttpServlet {
	private static final long serialVersionUID = 1L;
    private Connection con;
    public GetMemberPic() {
        super();
    }
	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		res.setContentType("image/jpeg");
		ServletOutputStream out = res.getOutputStream();
		req.setCharacterEncoding("UTF-8");
		
		try {
			Statement stmt = con.createStatement();
			Integer member_id = Integer.valueOf(req.getParameter("member_id"));
			ResultSet rs = stmt.executeQuery(
					"SELECT MEMBER_PIC FROM MEMBER_INFO WHERE MEMBER_ID ="+member_id);
			if (rs.next()) {
				BufferedInputStream in = new BufferedInputStream(rs.getBinaryStream("MEMBER_PIC"));
				byte[] buf = new byte[8 * 1024]; // 4K buffer
				int len;
				while ((len = in.read(buf)) != -1) {
					out.write(buf, 0, len);
				}
				in.close();
			} else {
				res.sendError(HttpServletResponse.SC_NOT_FOUND);
			}
			rs.close();
			stmt.close();
			
		} catch (Exception e) {
			String pic = req.getScheme()+"://"+req.getServerName()+":"+req.getServerPort();
			InputStream is = new URL(pic+"/BA105G6/img/member_default.png").openStream();
			BufferedInputStream in = new BufferedInputStream(is);
			byte[] buf = new byte[8 * 1024]; // 4K buffer
			int len;
			while ((len = in.read(buf)) != -1) {
				out.write(buf, 0, len);
			}
			in.close();		
		  }
	}

	public void init() throws ServletException{
		try {
			Context ctx = new javax.naming.InitialContext();
			DataSource ds = (DataSource) ctx.lookup("java:comp/env/jdbc/BA105G6DB");
			con = ds.getConnection();
		} catch (Exception e) {
			throw new UnavailableException("Couldn't get db connection");
		}
	}
	public void destroy(){
		try {
			if (con != null) con.close();
		} catch (SQLException e) {
			System.out.println(e);
		}
	}
}
