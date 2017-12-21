package com.member.controller;


import java.io.IOException;

import java.util.Enumeration;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.member.model.*;


public class MemberServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	Map<String, String> citymap ;
	public MemberServlet() {
        super();
    }
    
	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		doPost(req,res);
	}
	
	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		HttpSession session = req.getSession();
		res.setContentType("text/html; charset=UTF-8");
	    req.setCharacterEncoding("UTF-8");
		List<String> errorMsgs = new LinkedList<String>();
		req.setAttribute("errorMsgs", errorMsgs);
		
		String action = req.getParameter("action");
		if("login".equals(action)){
			String member_account = req.getParameter("member_account");
			String member_psw = req.getParameter("member_psw");	
			session.setMaxInactiveInterval(2592000);			
			MemberService memberSvc = new MemberService();
			MemberVO member = new MemberVO();
			member = memberSvc.findByAccount(member_account);
			if(member!=null && member.getMember_psw().equals(member_psw)){
				session.setAttribute("member", member);
				String url=req.getContextPath()+"/member/membercenter.jsp";
				res.sendRedirect(url);
			}else{
				errorMsgs.add("帳密錯誤");
				String url = String.valueOf(session.getAttribute("from_forward"));
				RequestDispatcher failureView = req.getRequestDispatcher(url);
				failureView.forward(req, res);
			}
		}	
		
		if("logout".equals(action)){
			String url = String.valueOf(session.getAttribute("from_redirect"));
			Enumeration<String> en = session.getAttributeNames();
			while(en.hasMoreElements()){
				String name = String.valueOf(en.nextElement());
				session.removeAttribute(name);
			}
			res.sendRedirect(url);
		}
		
		if("register".equals(action)){
			String enameReg = "^[(a-zA-Z0-9_)]{4,12}$";
			String nameReg = "^[\u4e00-\u9fa5_a-zA-Z]$";

			try {
				String member_account = req.getParameter("member_account");
				String member_psw = req.getParameter("member_psw");
				String member_lastname = req.getParameter("member_lastname");
				String member_firstname = req.getParameter("member_firstname");
				String member_psw_forcheck = req.getParameter("member_psw_forcheck");
				String member_address = req.getParameter("member_address");
				String mobilenum = req.getParameter("mobilenum");
				String member_emailaddress = req.getParameter("member_emailaddress");
				String member_idcode = req.getParameter("member_idcode");
				String creaditcard = req.getParameter("creaditcard");
				byte[] member_pic = null;
				String member_nickname = null;
				
				String county = req.getParameter("county");
				county = citymap.get(county)+"-";
				String area = req.getParameter("area")+"-";					
				
				if(member_account.trim().length() == 0 || member_psw.trim().length() == 0
				   || member_lastname.trim().length() == 0 || member_firstname.trim().length() == 0
				   || member_psw_forcheck.trim().length() == 0 || member_address.trim().length() == 0
				   || mobilenum.trim().length() == 0 ){
					errorMsgs.add("*為必填欄位");
				} else if(!member_account.trim().matches(enameReg)){
					errorMsgs.add("帳號必須為英文與數字 , 且長度必需在4到12之間");
				}
				if(!member_psw.equals(member_psw_forcheck)){
					errorMsgs.add("密碼不相符");
				}
				
				Integer member_sex = -1;
				try{
					member_sex = Integer.valueOf(req.getParameter("member_sex"));		
				} catch(NumberFormatException ne){
					errorMsgs.add("性別為必填欄位");
				}
				
				java.sql.Date member_birthday = null;
				try{
					member_birthday = java.sql.Date.valueOf(req.getParameter("member_birthday").trim());
				}catch(IllegalArgumentException e) {
					errorMsgs.add("請輸入日期!");
				}
				Integer subsenews = -1;
				try{
					subsenews = Integer.valueOf(req.getParameter("subsenews"));
				} catch(NumberFormatException ne){
					subsenews = 0;
				}		
				MemberVO memberVO = new MemberVO();
				memberVO.setMember_account(member_account)
				.setMember_psw(member_psw)
				.setMember_lastname(member_lastname)
				.setMember_firstname(member_firstname)
				.setMember_address(county+area+member_address)
				.setMobilenum(mobilenum)
				.setMember_emailaddress(member_emailaddress)
				.setMember_birthday(member_birthday)
				.setMember_idcode(member_idcode)
				.setCreaditcard(creaditcard)
				.setSubsenews(subsenews)
				.setMember_sex(member_sex);
				
				if (!errorMsgs.isEmpty()) {
					req.setAttribute("memberVO", memberVO);
					RequestDispatcher failureView = req.getRequestDispatcher("/member/memberregister.jsp");
					failureView.forward(req, res);
					return;
				}
				/******************新增資料*******************/
				Integer member_lock_status = 0;
				MemberService memberSvc = new MemberService();
				memberVO = memberSvc.add(member_account, member_psw_forcheck, member_lastname, member_firstname, county+area+member_address, mobilenum, member_emailaddress, member_birthday, member_idcode, creaditcard, subsenews, member_sex, member_lock_status, member_pic, member_nickname);
						
			} catch(Exception e) {
				e.printStackTrace();
			}
		}
	}
	public void init(){
		
		if(citymap == null){
			citymap = new HashMap<String, String>();
			citymap.put("city1", "台北市");
			citymap.put("city2", "基隆市");
			citymap.put("city3", "新北市");
			citymap.put("city4", "連江縣");
			citymap.put("city5", "宜蘭市");
			citymap.put("city7", "新竹市");
			citymap.put("city8", "新竹縣");
			citymap.put("city9", "桃園市");
			citymap.put("city10", "苗栗縣");
			citymap.put("city11", "台中市");
			citymap.put("city13", "彰化縣");
			citymap.put("city14", "南投縣");
			citymap.put("city15", "嘉義市");
			citymap.put("city16", "嘉義縣");
			citymap.put("city17", "雲林縣");
			citymap.put("city18", "臺南市");
			citymap.put("city20", "高雄市");
			citymap.put("city23", "澎湖縣");
			citymap.put("city24", "金門縣");
			citymap.put("city25", "屏東縣");
			citymap.put("city26", "台東縣");
			citymap.put("city27", "花蓮市");
			ServletContext context = getServletContext();
			context.setAttribute("citymap", citymap);
		}else{
			citymap = (Map<String, String>) getServletContext().getAttribute("citymap");
		}
	}
}