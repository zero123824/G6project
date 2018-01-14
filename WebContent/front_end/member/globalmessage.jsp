<%@page import="com.member.model.*"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%	MemberVO memberVO = (MemberVO)session.getAttribute("member");%>

<!DOCTYPE html>
<html>
<style type="text/css">
#theWindow{
  width:150px;
  height:26px;
  border:1px solid #dcc8c8;
  position:fixed;
  bottom:1px;
  right:2px;
  display:none;
  background-color:#f2dede;
  font-size: 18px;
  font-weight: 600;
}
.onlineStatus{
	background: rgb(66, 183, 42);
    border-radius: 50%;
    display: inline-block;
    height: 8px;
    width: 8px;    
    margin-left: 4px;
    float: right;
}
</style>
<body onload="getGlobalMessage()" onunload="disconnectGlobalMessage()">
<div id="theWindow"> 
  <span></span>
</div>
</body>
<script type="text/javascript">
	var globalWebSocket;
	function getGlobalMessage(){
		var location = window.location.href;
		var thispage = location.split("/")
		thispage = thispage[(thispage.length)-1];
		console.log(thispage);
		var member_id = <%= memberVO.getMember_id()%>;
		var EndPoint = "/GlobalMessage";
		var host = window.location.host;
		var path = window.location.pathname;
		var webCtx = path.substring(0,path.indexOf('/',1));
		var endPointURL = "ws://"+host+webCtx+EndPoint;		
		globalWebSocket = new WebSocket(endPointURL+"/"+thispage);
		globalWebSocket.onopen = function(event){
			globalWebSocket.send("iAmOnline");
		};
		globalWebSocket.onmessage = function(event){
			var jsonObject = JSON.parse(event.data);
			if(jsonObject.property == 'isOnline'){
				$("#theWindow span").text(jsonObject.message);
				softwhy();
			}else if(jsonObject.property == 'getFreind'){
				var online = jsonObject.online.substring(1,jsonObject.online.length-1);
				var array = online.split(", ");
				array.forEach(function(member_id){
					$("a."+member_id).children(".badge").after("<span class='onlineStatus'></span>");
				});
			}
		};
		globalWebSocket.onerror = function(event){
			console.log(event);
		}
		
	}
	
	function disconnectGlobalMessage(){
		if(globalWebSocket){
			globalWebSocket.close();
		}
	};
	
	//右下彈窗http://www.softwhy.com/article-7944-1.html
	function softwhy(){		
		$("#theWindow").slideDown("slow");
		setTimeout(function(){ 
			$("#theWindow").slideUp("slow"); 
		}, 2500);
	}; 
</script>
</html>