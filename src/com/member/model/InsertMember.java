package com.member.model;

import java.io.IOException;
import java.io.InputStream;
import java.util.Collection;
import java.util.Date;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.http.Part;


@WebServlet("/insertmember.do")

public class InsertMember extends HttpServlet {
	private static final long serialVersionUID = 1L;
    
    public InsertMember() {
        
    }
    
	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		doPost(req,res);
	}



	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		MemberDAO dao = new MemberDAO();
		MemberVO newmember = new MemberVO();
		Date today = new Date();
		//輸出圖片
		InputStream in;
		byte[] buf = new byte[0];
		
		req.setCharacterEncoding("UTF-8");
		res.setContentType("text/html;charset=UTF-8");
		//圖片input擷取
		Collection<Part> parts = req.getParts();
		if(parts.isEmpty()){}
		else{
			for(Part part:parts){
				if(part.getName().equals("member_pic")){
					in = part.getInputStream();
					buf = new byte[in.available()];
					in.read(buf);
				}
			}
		}
		/*----------------------------錯誤處理--------------------------------*/
		
		
		/*-------------------------2.包裹會員bean--------------------------------*/
		
		newmember.setMember_account(req.getParameter("member_account"))
		  .setMember_psw(req.getParameter("member_psw"))
		  .setMember_lastname(req.getParameter("member_lastname"))
		  .setMember_firstname(req.getParameter("member_firstname"))
		  .setMember_address(req.getParameter("member_address"))
		  .setMobilenum(req.getParameter("mobilenum"))
		  .setMember_email(req.getParameter("member_email"))
		  .setMember_birthday(new java.sql.Date(today.getTime()))
		  .setMember_idcode(req.getParameter("member_idcode"))
		  .setCreaditcard(req.getParameter("creaditcard"))
		  .setSubsenews(1)
		  .setMember_sex(1)
		  .setMember_lock_status(1)
		  .setMember_pic(buf)
		  .setMember_nickname("yoyo");
		
		/*-------------------------3.轉交會員bean--------------------------------*/
		HttpSession session = req.getSession();
		session.setAttribute("newmember", newmember);
		String url="register_advanced.jsp";
//		RequestDispatcher rd = req.getRequestDispatcher(url);
//		rd.forward(req, res);
		res.sendRedirect(url);
//		dao.add(newmember);
	}

}
