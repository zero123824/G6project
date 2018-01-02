package com.employee.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.json.JSONException;
import org.json.JSONObject;

import com.employee.model.EmployeeService;
import com.employee.model.EmployeeVO;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.member.model.MemberService;
import com.member.model.MemberVO;
import com.permision.model.PermisionService;
import com.tools.PassWordEncode;
import com.tools.SendToNewEmployee;
//為了ajax異步互動，使用multifile讀檔
@MultipartConfig(fileSizeThreshold = 1024 * 1024, maxFileSize = 1024 * 1024, maxRequestSize = 10 * 1024 * 1024)
public class EmployeeServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		doPost(req,res);
	}	
	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		req.setCharacterEncoding("UTF-8");
		res.setContentType("text/html;charset=UTF-8");
		String action = req.getParameter("action");
		PrintWriter out = res.getWriter();
		HttpSession session = req.getSession();

//開啟新增員工跳窗		
		if("getAdd".equals(action)){
			boolean getAdd = true;
			req.setAttribute("getAdd", getAdd);
			RequestDispatcher successView = req.getRequestDispatcher("/back_end/employee/employee_management.jsp");
			successView.forward(req, res);
			return;
		}
//新增員工action		
		if("addnewemp".equals(action)){
			Map<String,String> errorMsgs = new LinkedHashMap<String,String>();		
			req.setAttribute("errorMsgs", errorMsgs);
			EmployeeService empSvc = new EmployeeService();

			try {
				/***************************1.接收請求參數 - 輸入格式的錯誤處理**********************/
				Gson gs = new GsonBuilder().setDateFormat("yyyy-MM-dd HH:mm:ss").create();
				String emp_name = req.getParameter("emp_name");
				String enameReg = "^[(\u4e00-\u9fa5)(a-zA-Z0-9_)]{2,7}$";
				String emailReg = "^[a-z0-9._%+-]+@[a-z0-9.-]+\\.[a-z]{2,3}$";

				if (emp_name == null || emp_name.trim().length() == 0) {
					errorMsgs.put("emp_name","員工姓名: 請勿空白");
				} else if(!emp_name.trim().matches(enameReg)) { //以下練習正則(規)表示式(regular-expression)
					errorMsgs.put("emp_name","員工姓名: 只能是中、英文字母、數字和_ , 且長度必需在2到7之間");
	            }
				
				String emp_email = req.getParameter("emp_email").trim();
				if (emp_email == null || emp_email.trim().length() == 0) {
					errorMsgs.put("emp_email","電子信箱請勿空白");
				}else if(!emp_email.trim().matches(emailReg)){
					errorMsgs.put("emp_email","電子信箱格式錯誤");
				}
								
				java.sql.Date emp_hiredate = null;
				try {
					emp_hiredate = java.sql.Date.valueOf(req.getParameter("emp_hiredate").trim());
				} catch (IllegalArgumentException e) {
					emp_hiredate=new java.sql.Date(System.currentTimeMillis());
					errorMsgs.put("emp_hiredate","請輸入日期!");
				}
				
				String emp_address = req.getParameter("emp_address").trim();
				if (emp_address == null || emp_address.trim().length() == 0) {
					errorMsgs.put("emp_address","地址請勿空白");
				}

				String emp_phone = req.getParameter("emp_phone").trim();
				if (emp_phone == null || emp_phone.trim().length() == 0) {
					errorMsgs.put("emp_phone","請輸入手機號碼");
				}else if(!emp_phone.trim().matches("^09[(0-9)]{8}$")){
					errorMsgs.put("emp_phone","號碼不正確");
				}
				
				java.sql.Date emp_birthday = null;
				try {
					emp_birthday = java.sql.Date.valueOf(req.getParameter("emp_birthday").trim());
				} catch (IllegalArgumentException e) {
					emp_birthday=new java.sql.Date(System.currentTimeMillis());
					errorMsgs.put("emp_birthday","請輸入日期!");
				}
				
				Integer emp_sex = -1;
				try{
					emp_sex = Integer.valueOf(req.getParameter("emp_sex"));		
				} catch(NumberFormatException ne){
					errorMsgs.put("emp_sex","性別錯誤");
				}
				
				String[] operation_ids = req.getParameterValues("operation_id");
				if(operation_ids == null || operation_ids.length == 0) {
					errorMsgs.put("operation_id","請至少選擇一項權限功能");
				}

				//取出員工物件，進行包裝
				EmployeeVO empVO = new EmployeeVO();
				empVO.setEmp_name(emp_name)
					.setEmp_email(emp_email)
					.setEmp_hiredate(emp_hiredate)
					.setEmp_address(emp_address)
					.setEmp_phone(emp_phone)
					.setEmp_birthday(emp_birthday)
					.setEmp_sex(emp_sex);

				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					out.print(gs.toJson(errorMsgs));
					return; //程式中斷
//					req.setAttribute("empVO", empVO); // 含有輸入格式錯誤的empVO物件,也存入req
//					RequestDispatcher failureView = req
//							.getRequestDispatcher("/emp/update_emp_input.jsp");
//					failureView.forward(req, res);
				}
				
				/*產生明碼for user and hashcode for資料庫*/
				String password = PassWordEncode.getAuthCode();
				PassWordEncode.getHashCode(password);
				String emp_psw = PassWordEncode.getHashCode(password);
				System.out.println(password);

				Integer inserviced = 1;
				
				/***************************2.開始新增資料*****************************************/
				try{empVO = empSvc.add(emp_psw, empVO.getEmp_name(), empVO.getEmp_email(), empVO.getEmp_hiredate(),
						empVO.getEmp_birthday(), empVO.getEmp_address(), empVO.getEmp_phone(),
						empVO.getEmp_sex(),operation_ids, inserviced);					

				/***************************3.新增完成,寄送email和密碼*****************************/

				SendToNewEmployee.SendMail(empVO, password);			
				
				/***************************4.新增完成,準備轉交(Send the Success view)*************/
//					gs.toJson(empVO);
//					String jsondata = gs.toJson(empVO);
//					out.print(jsondata);
//					return; //程式中斷
				}catch(Exception e){
					e.printStackTrace();
				}
//				req.setAttribute("empVO", empVO); 	 // 資料庫update成功後,正確的的empVO物件,存入req
				String url = req.getParameter("hrefFrom");
				res.sendRedirect(url);				 // 修改成功後,ajax重導
				return;

				/***************************其他可能的錯誤處理*************************************/
			} catch (Exception e) {
				e.printStackTrace();
				RequestDispatcher failureView = req
						.getRequestDispatcher("/emp/update_emp_input.jsp?whichPage=1500");
				failureView.forward(req, res);
			}
		}
		
//員工資料修改
		if("empupdate".equals(action)){
			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);
		
			EmployeeService empSvc = new EmployeeService();

			try {
				/***************************1.接收請求參數 - 輸入格式的錯誤處理**********************/
				Integer empno = new Integer(req.getParameter("empno").trim());
				Gson gs = new GsonBuilder().setDateFormat("yyyy-MM-dd HH:mm:ss").create();
				String emp_name = req.getParameter("emp_name");
				String enameReg = "^[(\u4e00-\u9fa5)(a-zA-Z0-9_)]{2,7}$";
				if (emp_name == null || emp_name.trim().length() == 0) {
					errorMsgs.add("員工姓名: 請勿空白");
				} else if(!emp_name.trim().matches(enameReg)) { //以下練習正則(規)表示式(regular-expression)
					errorMsgs.add("員工姓名: 只能是中、英文字母、數字和_ , 且長度必需在2到7之間");
	            }
				
				String emp_email = req.getParameter("emp_email").trim();
				if (emp_email == null || emp_email.trim().length() == 0) {
					errorMsgs.add("電子信箱請勿空白");
				}	
				
				java.sql.Date emp_hiredate = null;
				try {
					emp_hiredate = java.sql.Date.valueOf(req.getParameter("emp_hiredate").trim());
				} catch (IllegalArgumentException e) {
					emp_hiredate=new java.sql.Date(System.currentTimeMillis());
					errorMsgs.add("請輸入日期!");
				}

				String emp_phone = req.getParameter("emp_phone").trim();
				if (emp_phone == null || emp_phone.trim().length() == 0) {
					errorMsgs.add("請輸入手機號碼");
				}else if(!emp_phone.trim().matches("^09[(0-9)]{8}$")){
					errorMsgs.add("號碼不正確");
				}
				
				String emp_address = req.getParameter("emp_address").trim();
				if (emp_address == null || emp_address.trim().length() == 0) {
					errorMsgs.add("地址請勿空白");
				}

				//取出員工物件，進行包裝
				EmployeeVO empVO = empSvc.findByPK(empno);
				empVO.setEmpno(empno);
				empVO.setEmp_name(emp_name);
				empVO.setEmp_email(emp_email);
				empVO.setEmp_hiredate(emp_hiredate);
				empVO.setEmp_phone(emp_phone);
				empVO.setEmp_address(emp_address);

				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					out.print(gs.toJson(errorMsgs));
					return; //程式中斷
//					req.setAttribute("empVO", empVO); // 含有輸入格式錯誤的empVO物件,也存入req
//					RequestDispatcher failureView = req
//							.getRequestDispatcher("/emp/update_emp_input.jsp");
//					failureView.forward(req, res);
				}
				
				/***************************2.開始修改資料*****************************************/
				try{empVO = empSvc.update(empVO.getEmpno(),empVO.getEmp_psw(), empVO.getEmp_name(), empVO.getEmp_email(), 
						empVO.getEmp_hiredate(), empVO.getEmp_birthday(),empVO.getEmp_address(), empVO.getEmp_phone(),
						empVO.getEmp_sex(),empVO.getLast_activity(),empVO.getInserviced());
				
				/***************************3.修改完成,準備轉交(Send the Success view)*************/
					gs.toJson(empVO);
					String jsondata = gs.toJson(empVO);
					out.print(jsondata);
					return; //程式中斷
				}catch(Exception e){
					e.printStackTrace();
				}
//				req.setAttribute("empVO", empVO); // 資料庫update成功後,正確的的empVO物件,存入req
//				String url = "/emp/listOneEmp.jsp";
//				RequestDispatcher successView = req.getRequestDispatcher(url); // 修改成功後,轉交listOneEmp.jsp
//				successView.forward(req, res);

				/***************************其他可能的錯誤處理*************************************/
			} catch (Exception e) {
				e.printStackTrace();
				errorMsgs.add("修改資料失敗:"+e.getMessage());			
				RequestDispatcher failureView = req
						.getRequestDispatcher("/emp/update_emp_input.jsp");
				failureView.forward(req, res);
			}
		}
		
//員工離職停權	
		if("suspend".equals(action)){

			/*************1.接收停權會員ID***************/
			Integer empno = new Integer(req.getParameter("empno").trim());
			/*************2.修改資料庫狀態停權************/
			EmployeeService empSvc = new EmployeeService();
			EmployeeVO empVO = empSvc.findByPK(empno);
			if(empVO == null){
				out.print("noThis");
				return; //程式中斷
			}
			if(empVO.getInserviced() == 2){
				out.print("false");
				return; //程式中斷
			}else{
				empSvc.suspend(empVO, 2);
			}
			/*************3.停權完成******************/
			out.print("true");
			return;
//			String url = "/membergetall.jsp";
//			RequestDispatcher successView = req.getRequestDispatcher(url);// 刪除成功後,轉交回送出刪除的來源網頁
//			successView.forward(req, res);			
		}
		
//取得一位員工資料	
		if ("getOne_For_Update".equals(action)) { // 來自listAllEmp.jsp的請求
			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);
			
			try {
				/***************************1.接收請求參數****************************************/
				Integer empno = new Integer(req.getParameter("empno"));
				
				/***************************2.開始查詢資料****************************************/
				EmployeeService empSvc = new EmployeeService();
				EmployeeVO empVO = empSvc.findByPK(empno);
								
				/***************************3.查詢完成,準備轉交(Send the Success view)************/
				Gson gs = new GsonBuilder().setDateFormat("yyyy-MM-dd").create();				
				String jsondata = gs.toJson(empVO);
				out.print(jsondata);
				return;
//				req.setAttribute("empVO", empVO);         // 資料庫取出的empVO物件,存入req				
//				String url = "/emp/update_emp_input.jsp";
//				RequestDispatcher successView = req.getRequestDispatcher(url);// 成功轉交 update_emp_input.jsp
//				successView.forward(req, res);

				/***************************其他可能的錯誤處理**********************************/
			} catch (Exception e) {
				errorMsgs.add("無法取得要修改的資料:" + e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/emp/listAllEmp.jsp");
				failureView.forward(req, res);
			}
		}
		
//員工登入action
		if(("login").equals(action)){
			EmployeeService empSvc = new EmployeeService();
			PermisionService permisionSvc = new PermisionService();
			String emp_email = req.getParameter("emp_email");
			String emp_psw = req.getParameter("emp_psw");
			List<String> loginerror = new LinkedList<String>();
			req.setAttribute("errorMsgs", loginerror);
			if(emp_email.trim().length() == 0 || emp_psw.trim().length() == 0){	
				loginerror.add("請輸入帳號密碼");
				RequestDispatcher failureView = req.getRequestDispatcher("/back_end/login.jsp");
				failureView.forward(req, res);
				return;
			}
			EmployeeVO employeeVO = empSvc.findByEmail(emp_email);
			if(employeeVO == null) {
				out.print("無此員工");
				return;
			}
			if(PassWordEncode.verifyPsw(emp_psw, employeeVO.getEmp_psw())) {
				session.setAttribute("keymap",permisionSvc.getOneEmpPermision(employeeVO.getEmpno()));
				out.println("登入成功");	
			}else {
				out.println("登入失敗");
			}
		}
	}
}
