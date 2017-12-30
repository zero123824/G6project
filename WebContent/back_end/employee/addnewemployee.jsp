<%@page import="com.employee.model.EmployeeVO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%
	EmployeeVO empVO = (EmployeeVO) request.getAttribute("empVO");
%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1.0, shrink-to-fit=no">
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/twitter-bootstrap/3.3.7/css/bootstrap.min.css">
	<!--[if lt IE 9]>
	<script src="https://cdnjs.cloudflare.com/ajax/libs/html5shiv/3.7.3/html5shiv.min.js"></script>
	<script src="https://cdnjs.cloudflare.com/ajax/libs/respond.js/1.4.2/respond.min.js"></script>
	<![endif]-->
<title>SNEAKER影城新增員工</title>
</head>
<style type="text/css">
	#permission td {
		width: 80px;
		height: 80px;
	}
	.chosed{
		color:red;
	}
</style>
<body>
	<FORM METHOD="post" id="newempdata" ACTION="<%=request.getContextPath()%>/back_end/employee/emp.do" name="addform">
		<div id="container""></div>
		<table>
		<tr>
			<td>員工姓名:</td>
			<td><input type="text" name="emp_name" size="45" 
				 value="<%= (empVO==null)? "吳永志" : empVO.getEmp_name()%>" /></td>
		</tr>
		<tr>
			<td>電子信箱:</td>
			<td><input type="text" name="emp_email" size="45"
				 value="<%= (empVO==null)? "" : empVO.getEmp_email()%>" placeholder="ex:ba105g6@gmail.com"/></td>
		</tr>
		<tr>
			<td>雇用日期:</td>
			<td><input name="emp_hiredate" id="f_date1" type="text"></td>
		</tr>
		<tr>
			<td>員工地址:</td>
			<td><input type="text" name="emp_address" size="45"
				 value="<%= (empVO==null)? "" : empVO. getEmp_phone()%>" placeholder="ex:台北市..."/></td>
		</tr>
		<tr>
			<td>生日:</td>
			<td><input name="emp_birthday" id="f_date2" type="text"></td>
		</tr>
		<tr>
			<td>手機:</td>
			<td><input type="text" name="emp_phone" size="45" maxlength="10"
				 value="<%= (empVO==null)? "" : empVO. getEmp_phone()%>" placeholder="ex:0910..." /></td>
		</tr>
		<tr>
			<td>性別:</td>
			<td>
				<input type="radio" name="emp_sex" value="1" size="45"/>男
				<input type="radio" name="emp_sex" value="2" size="45"/>女
			</td>
		</tr>
		<jsp:useBean id="opSvc" scope="page" class="com.operation.model.OperationService" />
		<tr>
			<td>權限:<font color=red><b>*</b></font></td>
			<td><input type="button" data-toggle="popover" data-content="
					<table border='1' id='permission'>
						<tr>
							<td>票價管理</td>
							<td>電影排程</td>
							<td>廳院管理</td>
						</tr>
						<tr>
							<td>餐飲管理</td>
							<td>窗口售票</td>
							<td>公告管理</td>
						</tr>
						<tr>
							<td>檢舉系統</td>
							<td>售票統計</td>
							<td>員工管理</td>
						</tr>
					</table>" value="選擇權限" onclick="bind()"></td>
			<td><select size="1" name="operaion_id">
				<c:forEach var="operationVO" items="${opSvc.all}">
					<option value="${operationVO.operation_id}" ${(empVO.operation_id==deptVO.operation_id)? 'selected':'' } >${operationVO.operation_name}
				</c:forEach>
			</select></td>
		</tr>
		</table>
		<input type="hidden" name="action" value="addnewemp">
		<input type ="hidden" name="hrefFrom" value="<%=request.getServletPath()+"?"+request.getQueryString()%>">
	</FORM>
	<script src="https://code.jquery.com/jquery.js"></script>
	<script src="https://cdnjs.cloudflare.com/ajax/libs/twitter-bootstrap/3.3.7/js/bootstrap.min.js"></script>
</body>
<% 
	java.sql.Date hiredate = null;
	java.sql.Date birthday = null;
  try {
	    hiredate = empVO.getEmp_hiredate();
	    birthday = empVO.getEmp_birthday();
   } catch (Exception e) {
	    hiredate = new java.sql.Date(System.currentTimeMillis());
	    birthday = new java.sql.Date(System.currentTimeMillis());
   }
%>
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/datetimepicker/jquery.datetimepicker.css" />
<script src="<%=request.getContextPath()%>/datetimepicker/jquery.js"></script>
<script src="<%=request.getContextPath()%>/datetimepicker/jquery.datetimepicker.full.js"></script>
<script>
        $.datetimepicker.setLocale('zh');
        $('#f_date1').datetimepicker({
	       theme: '',              //theme: 'dark',
	       timepicker:false,       //timepicker:true,
	       step: 1,                //step: 60 (這是timepicker的預設間隔60分鐘)
	       format:'Y-m-d',         //format:'Y-m-d H:i:s',
		   value: '<%=hiredate%>', // value:   new Date(),
           //disabledDates:        ['2017/06/08','2017/06/09','2017/06/10'], // 去除特定不含
           //startDate:	            '2017/07/10',  // 起始日
           //minDate:               '-1970-01-01', // 去除今日(不含)之前
           //maxDate:               '+1970-01-01'  // 去除今日(不含)之後
        });
        $('#f_date2').datetimepicker({
 	       theme: '',              //theme: 'dark',
 	       timepicker:false,       //timepicker:true,
 	       step: 1,                //step: 60 (這是timepicker的預設間隔60分鐘)
 	       format:'Y-m-d',         //format:'Y-m-d H:i:s',
 		   value: '<%=birthday%>', // value:   new Date(),
            //disabledDates:        ['2017/06/08','2017/06/09','2017/06/10'], // 去除特定不含
            //startDate:	            '2017/07/10',  // 起始日
            //minDate:               '-1970-01-01', // 去除今日(不含)之前
            //maxDate:               '+1970-01-01'  // 去除今日(不含)之後
         });
        $(document).ready(function(){
			$('[data-toggle="popover"]').popover({
				html:true,
				trigger:"click",
				container:"#container",
				placement:"bottom"
			});
		});
        
        
//         container.onclick= function(){
//         	console.log("sdf");
//         	$(this).addClass("chosed");
//         }
        function bind(){
        	var container = document.getElementById("container").children;
        	console.log(container);
        	console.log($(container).find("*"));
        }
        
</script>
</html>