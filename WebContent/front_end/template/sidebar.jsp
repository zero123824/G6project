<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>sidebar</title>
</head>
<style type="text/css">
	.modal{
		text-align: center;
		padding: 0!important;
	}
	.modal:before{
		content: '';
		display: inline-block;
		height: 100%;
		vertical-align: middle;
		margin-right: -4px;
	}
	.modal .modal-dialog{
		display: inline-block;
		text-align: left;
		vertical-align: middle;
	}
</style>
<body>
<!-- Sidebar Holder -->
	<nav id="sidebar">
	<div id="dismiss">
		<i class="glyphicon glyphicon-arrow-left"></i>
	</div>
	<div class="sidebar-header">
		<img src="<%=request.getContextPath()%>/img/logo.png">
	</div>
	<ul class="list-unstyled components">
		<li class="active"><a
			href="<%=request.getContextPath()%>/front_end/index.jsp"><i
				class="fa fa-home fa-lg"></i> 首頁</a></li>
		<li class="visible-xs"><a href="<%=request.getContextPath()%>/front_end/member/membercenter.jsp"><i
				class="fa fa-user fa-lg"></i> 會員中心</a></li>
		<li><a href="#"><i class="fa fa-film fa-lg"></i> 上映電影資訊</a></li>
		<li><a href="forum.html"><i class="fa fa-commenting-o fa-lg"></i>
				討論區</a></li>
		<li><a href="#"><i class="fa fa-newspaper-o fa-lg"
				aria-hidden="true"></i> 電影活動</a></li>
		<li><a href="<%=request.getContextPath()%>/front_end/announcement/announcement.jsp"><i class="fa fa-bullhorn fa-lg"
				aria-hidden="true"></i> 影城公告</a></li>
		<li><a href="#"><i class="fa fa-cutlery fa-lg"
				aria-hidden="true"></i> 餐飲介紹</a></li>
		<li><a href="#"><i class="fa fa-info-circle fa-lg"
				aria-hidden="true"></i> 影城介紹</a></li>
		<li><a href="#"><i class="fa fa-question fa-lg"
				aria-hidden="true"></i> 聯繫客服</a></li>
	</ul>
	</nav>

	<ul class="right-fastticket list-group hidden-xs">
		<a href="booking.html">
		<li class="list-group-item" id="right-fastticket"><i class="fa fa-ticket" aria-hidden="true"></i>快速訂票</li></a>
	</ul>

	<div class="modal fade" id="login-modal" tabindex="-1" role="dialog"
		aria-labelledby="myModalLabel" aria-hidden="true"
		style="display: none;">
		<div class="modal-dialog">
			<div class="loginmodal-container">
				<h1>登入您的帳號</h1>
				<br>
				<c:if test="${not empty errorMsgs}">
					<ul>
						<c:forEach var="message" items="${errorMsgs}">
							<li style="color: red">${message}</li>
						</c:forEach>
					</ul>
				</c:if>
				<h5 style="color: red" id="msgs" style="visibility:hidden"></h5>
				<form method="post" action="<%=request.getContextPath()%>/front_end/member/member.do">
					<input type="hidden" name="action" value="login"> 
					<input type="text" class="inputforlogin" name="member_account" id="member_account_login" placeholder="帳號"> 
					<input type="password" class="inputforlogin" name="member_psw" id="member_psw_login" placeholder="密碼">
					<label><input type="checkbox" name="cosistlogin" value="y" style="vertical-align:sub"> 保持登入狀態(公用電腦不建議使用)</label>
					<input type="submit" name="login" id="verifyaccount" class="login loginmodal-submit" value="確認輸入">
				</form>
				<div class="login-help">
					<a href="<%=request.getContextPath()%>/front_end/member/memberregister.jsp">註冊</a> - <a href="#">忘記密碼</a>
				</div>
			</div>
		</div>
	</div>
</body>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
	<script type="text/javascript">
		$(".inputforlogin").click(function(){
			$(this).css("background-color", "white");
		})
	
		$("#login-modal form").submit(function(){
			if($("#member_account_login").val() == ""){
			    $("#member_account_login").css("background-color", "#F2DEDE");
			}
			if($("#member_psw_login").val() == ""){
			    $("#member_psw_login").css("background-color", "#F2DEDE");
			}
			$.ajax({url:"<%=request.getContextPath()%>/front_end/member/member.do",
				method:"post",
				data:{ action: "login",
					   member_account:$("#member_account_login").val(),
					   member_psw:$("#member_psw_login").val()},
				dataType:"json"
				})
				.done(function(msgs){
					if(msgs.success == 'success'){
						window.location = "<%=request.getContextPath()%>/front_end/member/membercenter.jsp";
					}else{
						getMessage(msgs);
					}
				});
			return false;
		});
		function getMessage(msgs){
			$("#msgs").css("visibility","visible");
			$("#msgs").text(msgs.錯誤);
		}	
	
	
	//	$("#login-modal input").blur(function(){
	//		$("#msgs").css("visibility","hidden");
	//		$.ajax({url:"<%=request.getContextPath()%>/front_end/member/member.do",
	//				method:"post",
	//				data:{ action: "verify",
	//					   member_account:$("#member_account_login").val(),
	//					   member_psw:$("#member_psw_login").val()},
	//				dataType:"json"
	//				})
	//				.done(function(errorMsgs){
	//					getMessage(errorMsgs);
	//				});
	//		});	
	</script>
</html>