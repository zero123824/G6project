<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>sidebar</title>
</head>
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
			href="<%=request.getContextPath()%>/frontend/index.jsp"><i
				class="fa fa-home fa-lg"></i> 首頁</a></li>
		<li class="visible-xs"><a href="<%=request.getContextPath()%>/member/membercenter.jsp"><i
				class="fa fa-user fa-lg"></i> 會員中心</a></li>
		<li><a href="#"><i class="fa fa-film fa-lg"></i> 上映電影資訊</a></li>
		<li><a href="forum.html"><i class="fa fa-commenting-o fa-lg"></i>
				討論區</a></li>
		<li><a href="#"><i class="fa fa-newspaper-o fa-lg"
				aria-hidden="true"></i> 電影活動</a></li>
		<li><a href="#"><i class="fa fa-bullhorn fa-lg"
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
				<form method="post" action="<%=request.getContextPath()%>/member/member.do">
					<input type="hidden" name="action" value="login"> 
					<input type="text" name="member_account" placeholder="帳號"> 
					<input type="password" name="member_psw" placeholder="密碼">
					<label><input type="checkbox" name="">保持登入狀態(公用電腦不建議使用)</label>
					<input type="submit" name="login" class="login loginmodal-submit" value="確認輸入">
				</form>				
				<div class="login-help">
					<a href="<%=request.getContextPath()%>/member/memberregister.jsp">註冊</a> - <a href="#">忘記密碼</a>
				</div>

			</div>
		</div>
	</div>


</body>
</html>