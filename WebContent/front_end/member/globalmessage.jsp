<%@page import="com.member.model.*"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%	MemberVO memberVO = (MemberVO)session.getAttribute("member");%>

<!DOCTYPE html>
<html>
<body onload="getGlobalMessage()" onunload="disconnectGlobalMessage()">

</body>
<script type="text/javascript">
	var globalWebSocket;
	function getGlobalMessage(){
		var member_id = <%= memberVO.getMember_id()%>;
		var EndPoint = "/GlobalMessage";
		var host = window.location.host;
		var path = window.location.pathname;
		var webCtx = path.substring(0,path.indexOf('/',1));
		var endPointURL = "ws://"+host+webCtx+EndPoint;
		globalWebSocket = new WebSocket(endPointURL+"/"+member_id+"/online"); 
	}
	
	function disconnectGlobalMessage(){
		if(globalWebSocket){
			globalWebSocket.close();
		}		
	}
</script>
</html>