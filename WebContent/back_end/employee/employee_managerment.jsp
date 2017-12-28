<%@page import="com.employee.model.*"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.*"%>  
<%
	EmployeeService empSvc = new EmployeeService();
    List<EmployeeVO> list = empSvc.getAll();
    pageContext.setAttribute("list",list);
%>

<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width,initial-scale=1.0, shrink-to-fit=no">
<title>SNEAKER影城員工管理</title>
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/twitter-bootstrap/3.3.7/css/bootstrap.min.css">
<!--[if lt IE 9]>
	<script src="https://cdnjs.cloudflare.com/ajax/libs/html5shiv/3.7.3/html5shiv.min.js"></script>
	<script src="https://cdnjs.cloudflare.com/ajax/libs/respond.js/1.4.2/respond.min.js"></script>
<![endif]-->
</head>
<style>
	.employeetable tbody tr:nth-child(even){
		background-color:#B7C2F3;
	}
	.employeetable tbody tr:hover{
		background-color:#77ABC0;
		opacity:0.7;
	}
	.employeetable tbody tr.suspended {
		color:burlywood;
	}
	.employeetable input{
		border-radius:4px;
	}
</style>
<body>
	<div class="modal fade" id="modal-id">
		<div class="modal-dialog">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
					<h4 class="modal-title">員工資料</h4>
				</div>
				<div class="modal-body">
				<form id="editeform">
					<h4>員工編號</h4><input id="empno_edit" type="text" name="empno" disabled>
					<h4>員工姓名</h4><input id="emp_name" type="text" name="emp_name">
					<h4>電子信箱</h4><input id="emp_email" type="text" name="emp_email">
					<h4>手機</h4><input id="emp_phone" type="text" name="emp_phone">				
					<h4>地址</h4><input id="emp_address" type="text" name="emp_address">		
					<h4>生日</h4><input id="emp_birthday" type="text" name="emp_birthday" disabled>			
					<h4>雇用日期</h4><input id="emp_hiredate" type="text" name="emp_hiredate" disabled>
				</form>	
				</div>
				<div class="modal-footer">
					<button type="button" class="btn btn-default" data-dismiss="modal">關閉</button>
					<button type="button" id="submitedit" class="btn btn-primary">確認修改</button>
				</div>
			</div>
		</div>
	</div>

	<h1>SNEAKER影城員工管理</h1>
	<div class="collapse navbar-collapse navbar-ex1-collapse">
	<!-- 搜尋表單 -->
		<form class="navbar-form navbar-right" role="search">
			<div class="form-group">
				<input type="text" class="form-control" placeholder="搜尋員工">
			</div>
			<button type="submit" class="btn btn-default">新增員工</button>
		</form>
	</div>
<%@ include file="page1.file" %>
	<table class="table table-hover employeetable">
		<caption>員工列表</caption>
		<thead>
			<tr>
				<th>員工編號</th>
				<th>員工姓名</th>
				<th>電子信箱</th>
				<th>雇用日期</th>
				<th>手機</th>
				<th>上次登入時間</th>
				<th>在職狀態</th>
				<th>修改</th>
				<th>刪除</th>
			</tr>
		</thead>
		<tbody>
		<c:forEach var="empVO" items="${list}" begin="<%=pageIndex%>" end="<%=pageIndex+rowsPerPage-1%>">
			<tr class="${(empVO.inserviced == 1) ? 'inserviced':'suspended'}">
				<td>${empVO.empno}</td>
				<td>${empVO.emp_name}</td>
				<td>${empVO.emp_email}</td>
				<td>${empVO.emp_hiredate}</td>
				<td>${empVO.emp_phone}</td>
				<td>${empVO.last_activity}</td> 
				<td>${(empVO.inserviced == 1) ? "在職":"離職"}</td>
				<td>
				     <input type="submit" value="修改" class="editable" <c:if test="${(empVO.inserviced) == 2}">disabled</c:if>>
				     <input type="hidden" id="empno" name="empno"  value="${empVO.empno}">
				     <input type="hidden" name="action"	value="getOne_For_Update">
				</td>
				<td>
				  <FORM METHOD="post" ACTION="<%=request.getContextPath()%>/back_end/employee/emp.do">
				     <input type="submit" value="離職停權" <c:if test="${(empVO.inserviced) == 2}">disabled</c:if>>
				     <input type="hidden" name="empno"  value="${empVO.empno}">
				     <input type="hidden" name="action" value="suspended"></FORM>
				</td>
			</tr>
		</c:forEach>			
		</tbody>
	</table>
<%@ include file="page2.file" %>
	<script src="https://code.jquery.com/jquery.js"></script>
	<script src="https://cdnjs.cloudflare.com/ajax/libs/twitter-bootstrap/3.3.7/js/bootstrap.min.js"></script>
    <script src="http://malsup.github.com/jquery.form.js"></script>
	<script type="text/javascript">
		var form;
		$(".editable").click(function(){
			$.ajax({url:"<%=request.getContextPath()%>/back_end/employee/emp.do",
				type:"post",
				data:{ action:"getOne_For_Update",empno:$(this).next("#empno").val()},
				dataType:"json"				
				})
				.done(function(msgs){
					$("#modal-id").modal();
					$("#empno_edit").val(msgs.empno);
					$("#emp_name").val(msgs.emp_name);
					$("#emp_email").val(msgs.emp_email);
					$("#emp_hiredate").val(msgs.emp_hiredate);
					$("#emp_phone").val(msgs.emp_phone);
					$("#emp_birthday").val(msgs.emp_birthday);
					$("#emp_address").val(msgs.emp_address);
					form = new FormData(document.getElementById("editeform"));
//					$('#editeform').ajaxForm();
				});
		});
		
//         $('#editeform').ajaxForm(function() {
//         	console.log("teee");
//         }); 

		$("#submitedit").click(function(){
			form.append("action","empupdate");
			$.ajax({url:"<%=request.getContextPath()%>/back_end/employee/emp.do",
				type:"POST",
				data:form,
				processData:false,	// 設置jQuery不去處理發送的數據
 				contentType:false,	// 設置jQuery不去設置Content-Type header})
				dataType:"json"})
				.done(function(msgs){
					console.log("123");
				});
		});

	</script>
</body>
</html>