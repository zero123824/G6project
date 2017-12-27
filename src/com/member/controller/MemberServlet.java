package com.member.controller;


import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.http.Part;

import org.json.JSONException;
import org.json.JSONObject;

import com.member.model.*;

@MultipartConfig(fileSizeThreshold = 1024 * 1024, maxFileSize = 10 * 1024 * 1024, maxRequestSize = 20 * 10 * 1024 * 1024)
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
		PrintWriter out = res.getWriter();
		String action = req.getParameter("action");

			
		/**********************會員登入****************************/		
		if("login".equals(action)){
			String member_account = req.getParameter("member_account");
			String member_psw = req.getParameter("member_psw");
			String cosistlogin = req.getParameter("cosistlogin");
			MemberService memberSvc = new MemberService();
			MemberVO member = new MemberVO();
			member = memberSvc.findByAccount(member_account);
			
			if(member_account.trim().length() == 0 || member_psw.trim().length() == 0){
				List<String> loginerror = new LinkedList<String>();
				loginerror.add("請輸入帳號密碼");	
				JSONObject error = new JSONObject();
				try {
					error.put("錯誤", loginerror.get(0));
					out.print(error);
					return;
				} catch (JSONException e) {
					e.printStackTrace();
				}
				return;
			}
			
			if(member_account.trim().length() != 0 && member!=null){
				if(member.getMember_psw().equals(member_psw)) {
			/******************登入成功 準備AJAX回應給瀏覽器重導************************/	
					String url = (String)session.getAttribute("from_redirect");
					session.setAttribute("member", member);
					if("y".equals(cosistlogin)) {
						session.setMaxInactiveInterval(2592000);
					}
					JSONObject success = new JSONObject();
					try {
						success.put("url", url);
						success.put("success", "success");
						out.print(success);
						return;
					} catch (JSONException | NullPointerException ne) {
						ne.printStackTrace();
					}
			/******************登入成功 轉交FORWARD方法************************/			
//					String url=req.getContextPath()+"/member/membercenter.jsp";
//					res.sendRedirect(url);
//					return
				}else {
			/******************登入失敗 準備轉交AJAX方法************************/
					errorMsgs.add("密碼不符合");	
					JSONObject error = new JSONObject();
					try {
						error.put("錯誤", errorMsgs.get(0));
						out.print(error);
						return;
					} catch (JSONException e) {
						e.printStackTrace();
					}
			/******************登入失敗 準備轉交FORWARD方法************************/					
//					errorMsgs.add("密碼不符合");
//					String url = String.valueOf(session.getAttribute("from_forward"));
//					RequestDispatcher failureView = req.getRequestDispatcher(url);
//					failureView.forward(req, res);
				}
			}else{
			/******************登入失敗 準備轉交AJAX方法************************/
				errorMsgs.add("帳號不存在");	
				JSONObject error = new JSONObject();
				try {
					error.put("錯誤", errorMsgs.get(0));
					out.print(error);
					return;
				} catch (JSONException e) {
					e.printStackTrace();
				}
			/******************登入失敗 準備轉交FORWARD方法************************/	
//				errorMsgs.add("帳號錯誤");
//				String url = String.valueOf(session.getAttribute("from_forward"));
//				RequestDispatcher failureView = req.getRequestDispatcher(url);
//				failureView.forward(req, res);
			}
		}
		
		/**********************會員登出****************************/
		if("logout".equals(action)){
			String url = String.valueOf(session.getAttribute("from_redirect"));
			Enumeration<String> en = session.getAttributeNames();
			while(en.hasMoreElements()){
				String name = String.valueOf(en.nextElement());
				session.removeAttribute(name);
			}			
			res.sendRedirect(url);
			return;
		}
		
		if("register".equals(action)){
			String enameReg = "^[(a-zA-Z0-9_)]{4,12}$";
			String nameReg = "^[\u4e00-\u9fa5_a-zA-Z]$";
			MemberService memberSvc = new MemberService();
			
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
				String member_nickname = req.getParameter("member_nickname");;				
				String county = req.getParameter("county");

				county = citymap.get(county)+"-";
				String area = req.getParameter("area")+"-";				

				if(memberSvc.findByAccount(member_account) !=null){
					errorMsgs.add("此帳號已被使用");
				}
				
				if(member_account.trim().length() == 0 || member_psw.trim().length() == 0
				   || member_lastname.trim().length() == 0 || member_firstname.trim().length() == 0
				   || member_psw_forcheck.trim().length() == 0 || member_address.trim().length() == 0
				   || mobilenum.trim().length() == 0 || creaditcard.trim().length() == 0){
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
				
				InputStream in = null;
				byte[] member_pic = null;
				in = req.getPart("member_pic").getInputStream();
				member_pic = new byte[in.available()];
				in.read(member_pic);
				
				List<String> favorlist = new ArrayList<String>();
				try {
					if(req.getParameterValues("favortype").length>5) {
						errorMsgs.add("超過5個喜好類型，請重選");
					}else {
						for(String favor : req.getParameterValues("favortype")) {
							favorlist.add(favor); 
						}
					}
				}catch(NullPointerException ne){
					System.out.println(ne);
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
				.setMember_pic(member_pic)
				.setMember_sex(member_sex)
				.setSubsenews(subsenews)
				.setMember_nickname(member_nickname);
				
				if (!errorMsgs.isEmpty()) {
					req.setAttribute("memberVO", memberVO);
					RequestDispatcher failureView = req.getRequestDispatcher("/front_end/member/memberregister.jsp");
					failureView.forward(req, res);
					return;
				}
				/******************2.新增資料*******************/
				Integer member_lock_status = 0;
				memberVO = memberSvc.add(member_account, member_psw_forcheck, member_lastname, member_firstname, 
						county+area+member_address, mobilenum, member_emailaddress, member_birthday, member_idcode, 
						creaditcard, subsenews, member_sex, member_lock_status, member_pic, member_nickname,favorlist);
				/*****************3.新增完成,準備轉交(Send the Success view)*********/
				session.setAttribute("member", memberVO);
				String url=req.getContextPath()+"/front_end/member/membercenter.jsp";
				res.sendRedirect(url);
				return;
		
			} catch(Exception e) {
				e.printStackTrace();
			}
		}
		if ("update".equals(action)) { 
			MemberService memberSvc = new MemberService();
			/***************************1.接收請求參數 - 輸入格式的錯誤處理**********************/
			Integer member_id = new Integer(req.getParameter("member_id").trim());
			String member_name = req.getParameter("member_name");			
			String mobilenum = req.getParameter("mobilenum");
			String member_emailaddress = req.getParameter("member_emailaddress");
			String creaditcard = req.getParameter("creaditcard");
			String member_nickname = req.getParameter("member_nickname");
			Integer subsenews = Integer.valueOf(req.getParameter("subsenews"));
			String member_lastname = null;
			String member_firstname = null;
			String member_address = req.getParameter("member_address");
			if(member_name != null){
				member_firstname = member_name.substring(1, 3);
				member_lastname = member_name.substring(0, 1);
			}
			
//			String county = req.getParameter("county");
//			county = citymap.get(county)+"-";
//			String area = req.getParameter("area")+"-";
			if(member_address.trim().length() == 0 || member_emailaddress.trim().length() == 0
			   || mobilenum.trim().length() == 0){
				errorMsgs.add("*為必填欄位");
			}
			MemberVO memberVO = memberSvc.findByPK(member_id);
			memberVO.setMember_address(member_address)
					.setMobilenum(mobilenum)
					.setMember_emailaddress(member_emailaddress)
					.setSubsenews(subsenews)
					.setMember_nickname(member_nickname);
			if (!errorMsgs.isEmpty()) {
				req.setAttribute("memberVO", memberVO);
				RequestDispatcher failureView = req.getRequestDispatcher("/membergetall.jsp");
				failureView.forward(req, res);
				return;
			}					
			/***************************2.開始修改資料*****************************************/
			memberVO = memberSvc.update(member_id,memberVO.getMember_account(),memberVO.getMember_psw(),member_lastname, member_firstname, member_address,
						mobilenum, member_emailaddress, memberVO.getMember_birthday(), memberVO.getMember_idcode(),
						memberVO.getCreaditcard(), memberVO.getSubsenews(), memberVO.getMember_sex(), memberVO.getMember_lock_status(),
						memberVO.getMember_pic(), memberVO.getMember_nickname());
			/***************************3.修改完成,準備轉交(Send the Success view)*************/
			session.setAttribute("member", memberVO); // 資料庫update成功後,正確的的empVO物件,存入session
			req.setAttribute("member", memberVO); // 資料庫update成功後,正確的的empVO物件,存入req
			String url = String.valueOf(session.getAttribute("from_forward"));
			RequestDispatcher successView = req.getRequestDispatcher(url); // 修改成功後,轉交listOneEmp.jsp
			successView.forward(req, res);
		}
		
		if("suspend".equals(action)){
			/*************1.接收停權會員ID***************/
			Integer member_id = Integer.valueOf(req.getParameter("member_id").trim());
			/*************2.修改資料庫狀態停權************/
			MemberService memberSvc = new MemberService();
			MemberVO thismem = memberSvc.findByPK(member_id);
			if(thismem.getMember_lock_status() == 4){
				errorMsgs.add("此會員已遭停權");
			}else{
				memberSvc.suspend(thismem, 4);
			}
			/*************3.停權完成******************/
			String url = "/membergetall.jsp";
			RequestDispatcher successView = req.getRequestDispatcher(url);// 刪除成功後,轉交回送出刪除的來源網頁
			successView.forward(req, res);
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