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
	#permission{
		text-align:center;
	}
	#permission td {
		width: 80px;
		height: 80px;
	}
	.chosen{
		background-color:#B7C2F3;
		border:2px solid blue;
		color:blue;
	}
</style>
<body>

	<div class="popover" id="modalpopover">
	    <div class="arrow"></div>
	   	<button type="button" class="close" id="popoverclose" aria-hidden="true">選擇完成</button>
	    <h1 class="popover-title">功能列表</h1>
	    <div class="popover-content">       	
			<table id='permission'>
				<tr>
					<td id="15001">票價管理</td>
					<td id="15002">電影排程</td>
					<td id="15003">廳院管理</td>
				</tr>
				<tr>
					<td id="15004">餐飲管理</td>
					<td id="15005">窗口售票</td>
					<td id="15006">公告管理</td>
				</tr>
				<tr>
					<td id="15007">檢舉系統</td>
					<td id="15008">售票統計</td>
					<td id="15009">員工管理</td>
				</tr>
			</table>			
	    </div>
	</div>


	<div class="modal fade">
		<div class="modal-dialog">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
					<h1 class="modal-title">新增員工</h1>
				</div>
				<div class="modal-body">
					this is a test;
				</div>
				<div class="modal-footer">
					<button type="button" class="btn btn-default" data-dismiss="modal">關閉</button>
					<button type="button" onclick="newempsubmit()" class="btn btn-primary">送出新增</button>
				</div>
			</div>
		</div>
	</div>
<!-- form在列表頁面中送出 抓取id -->
	<FORM METHOD="post" id="newempdata" ACTION="<%=request.getContextPath()%>/back_end/employee/emp.do" name="addform">
		<div style="display:none">
			<input type="checkbox" name="operation_id" value="15001"></input>
	       	<input type="checkbox" name="operation_id" value="15002"></input>
	       	<input type="checkbox" name="operation_id" value="15003"></input>
	       	<input type="checkbox" name="operation_id" value="15004"></input>
	       	<input type="checkbox" name="operation_id" value="15005"></input>
	       	<input type="checkbox" name="operation_id" value="15006"></input>
	       	<input type="checkbox" name="operation_id" value="15007"></input>
	       	<input type="checkbox" name="operation_id" value="15008"></input>
	       	<input type="checkbox" name="operation_id" value="15009"></input>
		</div>   		
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
				<input type="radio" name="emp_sex" value="1" />男
				<input type="radio" name="emp_sex" value="2" />女
			</td>
		</tr>
		<jsp:useBean id="opSvc" scope="page" class="com.operation.model.OperationService" />
		<tr>
			<td>權限:<font color=red><b>*</b></font></td>
			<td id="operation"><a href="#modalpopover" role="button" data-modal-position="relative" data-toggle="modal-popover" 
						data-placement="bottom">選擇權限</a></td>
		</tr>
		</table>
		<input type="hidden" name="action" value="addnewemp">
		<input type ="hidden" name="hrefFrom" value="<%=request.getRequestURI()+"?whichPage=200"%>">
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
        
        $("#newempdata input").click(function(){
        	$(this).css("background-color","white");
        })
        
        $("#permission td").click(function(){
 			var id = $(this).attr("id");
        	if($(this).attr("class")== 'chosen'){
     			$(this).removeClass("chosen");
     			$("input[value="+id+"]").removeAttr("checked");

     		}else{
     			$(this).addClass("chosen");
     			$("input[value="+id+"]").attr("checked","checked");
     		}	
        })
        
        $("#popoverclose").click(function(){
        	$("#modalpopover").modalPopover("toggle");
        })

</script>
</html>