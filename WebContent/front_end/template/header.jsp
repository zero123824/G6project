<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>	
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="icon" type="image/gif" href="<%=request.getContextPath()%>/img/logo_192.png" />
<title>header</title>
</head>
<style>
.navbar-right-custom li:hover{
	color:green;
}
</style>
<body>
	<!-- header -->
	<nav class="navbar navbar-default">
	<div class="container-fluid">
		<div class="navbar-header">
			<button type="button" id="sidebarCollapse"
				class="glyphicon glyphicon-align-justify btn btn-info navbar-btn">
			</button>
			<a href="<%=request.getContextPath()%>/front_end/index.jsp"><img
				src="<%=request.getContextPath()%>/img/logo.png"></a>
		</div>
		<ul class="nav navbar-nav navbar-right hidden-xs navbar-right-custom">
			<c:if test="${empty member}">
				<li><a data-toggle="modal" data-target="#login-modal"
					style="color: white; margin-top: 5px"><i class="fa fa-user"></i>
						會員登入</a></li>
			</c:if>
			<c:if test="${not empty member}">
				<li><a
					href="<%=request.getContextPath()%>/front_end/member/membercenter.jsp"
					style="color: white; margin-top: 5px"><img src="<%=request.getContextPath()%>/front_end/member/getmemberpic?member_id=${member.member_id}" style="width: 30px;border-radius: 50%;">${member.member_firstname} 您好!會員中心</a></li>
				<li><a
					onclick="document.getElementById('logoutform').submit();"
					style="color: white; margin-top: 5px">登出</a></li>
				<jsp:include page="/front_end/member/globalmessage.jsp"/>
			</c:if>
		</ul>
	</div>
	</nav>
	<form id="logoutform" method="post"
		action="<%=request.getContextPath()%>/front_end/member/member.do">
		<input type="hidden" name="action" value="logout">
	</form>
</body>
</html>