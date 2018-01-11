<%@	page import="com.announcement.model.AnnouncementVO"%>
<%@	page import="com.announcement.model.AnnouncementService"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<% request.setAttribute("hereis", "announce");%>
<%	AnnouncementService anncmSvc = new AnnouncementService();
	if(request.getParameter("announce_id") != null){
		Integer announce_id = Integer.valueOf(request.getParameter("announce_id"));
		AnnouncementVO thisanna = anncmSvc.findByPK((announce_id));
		String[] contents = thisanna.getAnnounce_content().split("\n");
		pageContext.setAttribute("content",contents);
		pageContext.setAttribute("thisanna", thisanna);
	}else{
		response.sendRedirect(request.getContextPath()+"/front_end/announcement/announcement.jsp");
	}
	%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<link rel="icon" type="image/gif" href="<%=request.getContextPath()%>/img/logo_192.png" />
<title>Sneaker影城_影城公告</title>
<!-- Bootstrap CSS CDN -->
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
<!-- Scrollbar Custom CSS -->
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/malihu-custom-scrollbar-plugin/3.1.5/jquery.mCustomScrollbar.min.css">
<!-- ICON CSS -->
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/font-awesome/4.4.0/css/font-awesome.min.css">
<!-- Our Custom CSS -->
<link rel="stylesheet" href="<%=request.getContextPath()%>/front_end/css/frontend.css">
</head>
<style>
.content{
	font-family:Cambria;
	font-size:26px;
	color:blue;
	margin-bottom:40px;
}
</style>
<body id="myPage">
	<div class="wrapper">
		<!-- Include sidebar -->        
		<jsp:include page="/front_end/template/sidebar.jsp"/>
            
		<!-- Page Content -->
		<div id="content">
              
			<!-- Include sidebar --> 
			<jsp:include page="/front_end/template/header.jsp"/>               

			<!-- 從這裡開始修改 -->
            <ol class="breadcrumb">
            	<li><a href="<%=request.getContextPath()%>/front_end/index.jsp">首頁</a></li>            	
				<li><a href="<%=request.getContextPath()%>/front_end/announcement/announcement.jsp">影城公告</a></li>
				<li class="active">公告內容</li>				
            </ol>
			<div class="container">
				<div class="row">
					<div class="col-xs-12 col-sm-12">
						<h2><time class="date"><fmt:formatDate value="${thisanna.announce_time}" pattern="yyyy-MM-dd"/></time></h2>
						<h2>${thisanna.announce_title}</h2>
						<div class="content"><c:forEach var="ln" items="${content}">
							${ln}<br>
						</c:forEach>
						</div>
					</div>
				</div>
			</div>
			<!-- 到這裡結束 -->			
			<!-- include footer -->
			<jsp:include page="/front_end/template/footer.jsp"/>
		</div>            
	</div>
	<div class="overlay"></div>
	<!-- jQuery CDN -->
	<script src="https://code.jquery.com/jquery-1.12.0.min.js"></script>
	<!-- Bootstrap Js CDN -->
	<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
	<!-- jQuery Custom Scroller CDN -->
	<script src="https://cdnjs.cloudflare.com/ajax/libs/malihu-custom-scrollbar-plugin/3.1.5/jquery.mCustomScrollbar.concat.min.js"></script>
	<script src="<%=request.getContextPath()%>/front_end/js/frontend.js"></script>
</body>
</html>