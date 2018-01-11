<%@page import="com.employee.model.*"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
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
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/font-awesome/4.4.0/css/font-awesome.min.css">

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
	.employeetable tbody tr.suspend {
		color:burlywood;
	}
	.employeetable input{
		border-radius:4px;
	}
	.swal-button--catch{
		background-color:red;		
	}
	.swal-button--catch:active{
		background-color:#b71c1c;
		color:#C6C6C6;
	}
	.swal-button--confirm{
		background-color:#337ab7;
	}
	.swal-button--confirm:active{
		background-color:#286090;
	}	
</style>
<body>
	<div class="modal fade" id="modal_addemp">
		<div class="modal-dialog">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
					<h1 class="modal-title">新增員工</h1>
				</div>
				<div class="modal-body">
					<jsp:include page="/back_end/employee/addnewemployee.jsp" />
				</div>
				<div class="modal-footer">
					<button type="button" class="btn btn-default" data-dismiss="modal">關閉</button>
					<button type="button" onclick="newempsubmit()" class="btn btn-primary">送出新增</button>
				</div>
			</div>
		</div>
	</div>

	<div class="modal fade" id="modal-spinner">
		<div class="modal-dialog modal-sm">
			<div class="modal-content">
				<div class="modal-body">
					<i class="fa fa-spinner fa-pulse fa-3x fa-fw"></i>
					<span class="sr-only">新增中...</span>
					<span>新增中...</span>					
				</div>				
			</div>
		</div>
	</div>
		
	<div class="modal fade" id="modal_editemp">
		<div class="modal-dialog">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
					<h4 class="modal-title">員工資料</h4>
				</div>
				<div class="modal-body">
				<div class="errorMsgs" style="color:red"></div>
				<form id="editeform">
					<h4>員工編號</h4><input id="empno_edit" type="text" name="empno" disabled>
					<h4>員工姓名</h4><input id="emp_name" type="text" name="emp_name">
					<h4>電子信箱</h4><input id="emp_email" type="text" name="emp_email">
					<h4>手機</h4><input id="emp_phone" type="text" name="emp_phone" maxlength="10">				
					<h4>員工地址</h4><input id="emp_address" type="text" name="emp_address">		
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
			<button type="submit" class="btn btn-default">搜尋</button>
		</form>
		<button onclick="getAdd()">新增員工</button>
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
			<tr class="${(empVO.inserviced == 1) ? 'inserviced':'suspend'}">
				<td class="empno">${empVO.empno}</td>
				<td class="emp_name">${empVO.emp_name}</td>
				<td class="emp_email">${empVO.emp_email}</td>
				<td class="emp_hiredate">${empVO.emp_hiredate}</td>
				<td class="emp_phone">${empVO.emp_phone}</td>				
				<td class="last_activity"><fmt:formatDate value="${empVO.last_activity}" pattern="yyyy-MM-dd HH:mm:ss"/></td> 
				<td class="inserviced">${(empVO.inserviced == 1) ? "在職":"離職"}</td>
				<td>
				     <input type="submit" value="修改" class="editable" <c:if test="${(empVO.inserviced) == 2}">disabled</c:if>>
				     <input type="hidden" id="empno" name="empno"  value="${empVO.empno}">
				     <input type="hidden" name="action"	value="getOne_For_Update">
				</td>
				<td>
					<input type="button" onclick="suspend(event)" value="離職停權" <c:if test="${(empVO.inserviced) == 2}">disabled</c:if>>
				  	<FORM METHOD="post" ACTION="<%=request.getContextPath()%>/back_end/employee/emp.do">
				    <input type="hidden" name="empno"  value="${empVO.empno}">
				    <input type="hidden" name="action" value="suspend"></FORM>
				</td>
			</tr>
		</c:forEach>			
		</tbody>
	</table>
<%@ include file="page2.file" %>
	<!-- jquery $ jquery 特效 & 日期format套件 & sweetalert -->
	<script src="https://code.jquery.com/jquery.js"></script>
	<script src="https://code.jquery.com/ui/1.12.1/jquery-ui.min.js" integrity="sha256-VazP97ZCwtekAsvgPBSUwPFKdrwD3unUfSGVYrahUqU="  crossorigin="anonymous"></script>
	<script src="<%=request.getContextPath()%>/back_end/employee/js/jquery-dateFormat.min.js"></script>
	<script src="https://unpkg.com/sweetalert/dist/sweetalert.min.js"></script>
	<script src="https://cdnjs.cloudflare.com/ajax/libs/twitter-bootstrap/3.3.7/js/bootstrap.min.js"></script>
	<script src="<%=request.getContextPath()%>/back_end/employee/js/bootstrap-modal-popover.js"></script>	
    <script src="http://malsup.github.com/jquery.form.js"></script>
	<script type="text/javascript">
		var form;
		var logWhichRow;
		$(".editable").click(function(e){
			$(".errorMsgs").text("");
			$.ajax({url:"<%=request.getContextPath()%>/back_end/employee/emp.do",
				type:"post",
				data:{ action:"getOne_For_Update",empno:$(this).next("#empno").val()},
				async :false,
				dataType:"json"				
				})
				.done(function(msgs){
					$("#modal_editemp").modal({show:true});
					$("#empno_edit").val(msgs.empno);
					$("#emp_name").val(msgs.emp_name);
					$("#emp_email").val(msgs.emp_email);
					$("#emp_hiredate").val(msgs.emp_hiredate);
					$("#emp_phone").val(msgs.emp_phone);
					$("#emp_birthday").val(msgs.emp_birthday);
					$("#emp_address").val(msgs.emp_address);
				});
			logWhichRow = $(this).parents("tr");
		});
		
//         $('#editeform').ajaxForm(function() {
//         	console.log("teee");
//         }); 

		$("#submitedit").click(function(){
			editeform = new FormData(document.getElementById("editeform"));
			editeform.append("action","empupdate");
			editeform.append("empno",$("#empno_edit").val());
			editeform.append("emp_hiredate",$("#emp_hiredate").val());
			editeform.append("emp_birthday",$("#emp_birthday").val());
			
			$.ajax({url:"<%=request.getContextPath()%>/back_end/employee/emp.do",
					type:"POST",
					data:editeform,
					processData:false,	// 設置jQuery不去處理發送的數據
	 				contentType:false,	// 設置jQuery不去設置Content-Type header})
					dataType:"json"})
				.done(function(msgs){
	//				如回傳是錯誤訊息，型態為array 否則為完整的jsonObject;
					if(Array.isArray(msgs)){$(".errorMsgs").text(msgs)}
					else{
						$("#modal_editemp").modal("toggle");
						$.each(msgs,function(name,value){
							if(name == 'inserviced'){
								$(logWhichRow).children('td.'+name).text(value == 1 ? '在職':'離職');
							}else if(name == 'emp_hiredate'){
								$(logWhichRow).children('td.'+name).text($.format.date(value, 'yyyy-MM-dd'));
							}
							else{
								$(logWhichRow).children('td.'+name).text(value);}
						})
					}
					var origincolor = $(logWhichRow).css("background-color")
					$(logWhichRow).css("background-color","#FB6523");
					$(logWhichRow).animate({backgroundColor: origincolor},2000);					
				});
		});
/*停職員工 jsfunction 彈窗*/	
		function suspend(event){
/*先抓到哪一個按鈕，再找他的下一個form[0]*/
			var formdata = new FormData($(event.target).next("form")[0]);	
			swal({	icon: "warning",
					title: '真的要將此位員工停職?',
					dangerMode: true,
					buttons: {
				    cancel: "沒事!按錯了",
				    catch: {
				      text: "確定,跟他說byebye~",
				      value: "catch",
				    }
				  },
				})
/*判斷選擇的按鈕*/
			.then((value) => {  
				switch (value) {
				 
				case "catch":
					fetch('<%=request.getContextPath()%>/back_end/employee/emp.do',{method: 'post',body:formdata})
					.then(function(response){
						return response.text();						
					})
					.then(function(text){
						if(text == 'false'){
							swal("操作失敗!", "無法開除此員工!", "error");
						}else if(text == 'true'){
							swal("完成操作!", "已開除此員工!", "success");
							$(event.target).parents("tr").removeClass("inserviced").addClass("suspend");
						}else{
							swal("沒有此員工!","請重新操作", "error");							
						}
					})
				break;
				default:
				swal("他是位好員工!!");
				}
			});
		}
		function getAdd(){
			fetch('<%=request.getContextPath()%>/back_end/employee/emp.do?action=getAdd',{method: 'post'})
			.then(function(response){
				if(response.status == 200 && response.ok == true){
					$("#modal_addemp").modal('show');
				}
			});
		}
		function newempsubmit(){
			$(".errormsgs").text("");
			$("#modal-spinner").modal("toggle");
			var newempform = new FormData($("#newempdata")[0]);
			fetch('<%=request.getContextPath()%>/back_end/employee/emp.do',{method: 'post',body:newempform})
			.then(function(response){	
				if (response.redirected) {
					window.location = response.url ;
				}
				return response.json();
			})
			.then(function(error){
				$("#modal-spinner").modal("toggle");
				$.each(error,function(name,value){
					if(name == "emp_sex"){
						$("input[name="+name+"]").parent("td").after(function(){
							return "<b class='errormsgs' style='color:red'>"+ value + "</b>"
						});
					}else if(name == "operation_id"){
						$("#operation").after(function(){
							return "<b class='errormsgs' style='color:red'>"+ value + "</b>"
						});				
					}else{
						$("input[name="+name+"]").css("background-color","#F2DEDE");
						$("input[name="+name+"]").parent("td").after(function(){
							return "<b class='errormsgs' style='color:red'>"+ value + "</b>"
						});
					}
				})
			});
		}
		var newEmpmail = '<%=request.getParameter("newEmpmail")%>';
		if(newEmpmail != null){
			$("td[class='emp_email']:contains("+newEmpmail+")").parent("tr").css("background-color","yellow");			
		}
	</script>
</body>
</html>