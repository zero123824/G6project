<%@page import="com.member.model.*"%>
<%@page import="com.friend.model.*"%>
<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!-- 取得目前麵包屑位置 -->
<% 	request.setAttribute("hereis", "center");%>
<%	MemberVO memberVO = (MemberVO)session.getAttribute("member");
	if(memberVO == null){memberVO = new MemberVO();}
	FriendService friendSvc = new FriendService();
	pageContext.setAttribute("friendSvc",friendSvc);%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<link rel="icon" type="image/gif" href="<%=request.getContextPath()%>/img/logo_192.png" />
<title>Sneaker影城_會員列表</title>.<!-- Bootstrap CSS CDN -->
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">  
<!-- Scrollbar Custom CSS -->
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/malihu-custom-scrollbar-plugin/3.1.5/jquery.mCustomScrollbar.min.css">
<!-- ICON CSS -->
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/font-awesome/4.4.0/css/font-awesome.min.css">
<!-- Our Custom CSS -->
<link rel="stylesheet" href="<%=request.getContextPath()%>/front_end/css/frontend.css">   
</head>
<style>
	.plus{
    	float: right;
    	color:#468f06;
	}
	.checked{
	    float: right;
		color:red;
	}
	.pending{
	    float: right;
	    color:blue;	    
	}
	.positionright{
		float: right;
    	color:#468f06;
	}	
	.sticky{
		margin: 3px;
		width:50px;
		height:50px;
		display:inline-block;
		border-radius: 50%;
	}
	.messagediv{
		margin: 5px;
		background-color:white;
		display: inline-table;
		width: 60%;
		border-radius: 17px;
		padding:5px;
	}
	#textbody{
		height: 420px;
		overflow: auto;
		background-color:#e0e0de;		
	}
	#accept,#reject{
	    margin: 2px;
		float: right;
		border-radius: 10px;
		border: 0;
    	box-shadow: 1px 2px #ecafaf;		
	}
	#accept{
		background-color:#c9302c;
		color:#fff;				
	}
</style>
<body id="myPage" onload="arrange()" onunload="disconnect();">
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
					<li><a href="<%=request.getContextPath()%>/front_end/member/membercenter.jsp">會員中心</a></li>
                    <li class="active"><a href="#">會員列表</a></li>
                </ol>
				<div class="container">
					<div class="row">
						<div class="col-xs-12 col-sm-4">
							<div class="panel panel-warning">
								<div class="panel-heading">
									<h3 class="panel-title">搜尋會員</h3>
								</div>
								<div class="panel-body">
									<div class="form-group">
										<label for="usr">輸入姓名或暱稱</label>
										<input type="text" class="form-control" oninput="search(this)" placeholder="ex:小火龍or阿龍..." id="usr">
									</div>
									<ul class="list-group" id="membetcontainer">
										
									</ul>
							  </div>
							</div>
						</div>
						<div class="col-xs-12 col-sm-8">
							<div class="panel panel-success">
								<div class="panel-heading">
									<h3 class="panel-title">訊息</h3>
								</div>
								<div class="panel-body" id="textbody">
									<div id="msgtextarea">
									</div>								
								</div>
								<div class="panel-footer">
							  		<div class="input-area">
										<input id="message" class="text-field" type="text" placeholder="輸入訊息......." onkeydown="if (event.keyCode == 13)sendMessage();" disabled="disabled"/>
										<input type="submit" id="sendMessage" class="button" value="Send" onclick="sendMessage();" disabled="disabled"/>
									</div>
								</div>
							</div>
						</div>
					</div>
				</div>	
				<div class="container">
					<div class="row">
						<div class="col-xs-12 col-sm-12">
							<div class="panel panel-info">
								<div class="panel-heading">
									<h3 class="panel-title">好友們</h3>
								</div>
								<div class="panel-body">
								<c:forEach var="friend" items="${friendSvc.getOneMemFriends(member.member_id)}">
                                    <div class="col-xs-12 col-sm-2 friendArray" style="height:80px" >
	                                    <a class="thumbnail friend ${friend.member_id}">
	                                    	<span style="display:none">${friend.member_id}</span>
	                                    	<span class="badge" style="color:white;background-color:red;"></span>
	    									<img src="<%=request.getContextPath()%>/front_end/member/getmemberpic?member_id=${friend.member_id}" style="width: 40px;height:40px;border-radius: 30%;">
	    									<span>${friend.member_lastname}${friend.member_firstname}</span>
	                                    </a>
                                    </div>							
								</c:forEach>								
								</div>
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
<c:forEach var="relation" items="${friendSvc.getFriendRelation(member.member_id)}">
	<span style="display:none" class="arraytime">${relation.last_msg_time}</span>
	<span style="display:none" class="arrayStatus">${relation.relation_status}</span>	
</c:forEach>
    
<script type="text/javascript">
	
	//排序好友與顯示未讀訊息
	function arrange(){
		var friensArray=$(".friendArray").toArray();
		var friendTimeArray =$(".arraytime").toArray();
		var friendStatusArray =$(".arrayStatus").toArray();
		var friendBindArray = new Array();
		var friendID;
		friensArray.forEach(function(that){
			var jsonObject = new Object();
			jsonObject.name = that;
			jsonObject.time = (friendTimeArray[friensArray.indexOf(that)]).innerHTML;
			jsonObject.status = (friendStatusArray[friensArray.indexOf(that)]).innerHTML;
			friendBindArray.push(jsonObject);
			var friend = ($(jsonObject.name).find("a.friend span")[0]);
			friendID = ($(friend).text());			
		});
		console.log(friendBindArray);
		fetch('<%=request.getContextPath()%>/front_end/friend/friend.do?action=getfriendsStatus&friendID='+friendID+'&member_id='+<%=memberVO.getMember_id()%>,{method: 'post'})
		.then(function(response){
			if(response.status == 200 && response.statusText == "OK"){
				return (response.json())
			}
		})
		.then(function(msg){
			$(msg).each(function(index){
				var friendBadge = friendBindArray[index].name;
				var unread = JSON.parse(msg[index]).unread;
				if(unread != "0"){
					$(friendBadge).find(".badge").text(unread);
				}
			});
		})		
	}
	
	//搜尋會員
	function search(tar){		
		$("#membetcontainer").empty();
		var keyword = $(tar).val();
		var fd = new FormData();
		fd.set("keyword",keyword);
		fd.set("who",me);
		//ajax回傳搜尋會員 回傳json陣列
		fetch('<%=request.getContextPath()%>/front_end/member/member.do?action=search',{method: 'post',body:fd})
		.then(function(response){
			return(response.json())
		})
		.then(function(msg){
			$(msg).each(function(index){
				var membername = $(this)[0].member_lastname+$(this)[0].member_firstname;
				var memberid = $(this)[0].member_id;
				var membernick = $(this)[0].member_nickname;
				var picurl = "<%=request.getContextPath()%>/front_end/member/getmemberpic?member_id=";
				var havebeenfriend = $(this)[0].havebeenfriend;
				var pending = $(this)[0].pending;
				if(havebeenfriend == "true" && pending != "true" && memberid != me.toString()){
					$("#membetcontainer").append("<li class='list-group-item'><img src='"+picurl+memberid+"' style='width:80px;height:80px;display:inline-block'><p style='display:inline-block;color:black;'>"+membername+"("+membernick+")"+"</p><input type='hidden' value='"+memberid+"'><i class='fa fa-check-circle checked' aria-hidden='true'>已是好友</i><br>");	
				}else if(memberid == me.toString()){
					$("#membetcontainer").append("<li class='list-group-item'><img src='"+picurl+memberid+"' style='width:80px;height:80px;display:inline-block'><p style='display:inline-block;color:black;'>"+membername+"("+membernick+")"+"</p><br>");
				}else if(pending == "true"){
					$("#membetcontainer").append("<li class='list-group-item'><img src='"+picurl+memberid+"' style='width:80px;height:80px;display:inline-block'><p style='display:inline-block;color:black;'>"+membername+"("+membernick+")"+"</p><input type='hidden' value='"+memberid+"'><i class='fa fa-clock-o pending' aria-hidden='true'>請求中</i><br>");
				}else{
					$("#membetcontainer").append("<li class='list-group-item'><img src='"+picurl+memberid+"' style='width:80px;height:80px;display:inline-block'><p style='display:inline-block;color:black;'>"+membername+"("+membernick+")"+"</p><input type='hidden' value='"+memberid+"'><i class='fa fa-plus-circle plus' onclick='befriend(this)' data-toggle='tooltip' title='加為好友' aria-hidden='true'></i><br>");
				} 
			})
			trigger();
		});
	}
	function trigger(){
		$(".plus").hover(function(that){
		    $('[data-toggle="tooltip"]').tooltip(); 
		})
	}
	function befriend(that){
		var friendID = $(that).prev("input").val();		
		fetch('<%=request.getContextPath()%>/front_end/friend/friend.do?action=pending&friendID='+friendID+'&member_id='+<%=memberVO.getMember_id()%>,{method: 'post'})
		.then(function(response){
			if(response.status == 200 && response.statusText == "OK"){
				$(that).removeClass("fa fa-plus-circle plus").addClass("fa fa-clock-o pending");
				$(that).append("請求中");
				$(that).removeAttr("onclick data-original-title");
			}
		});
	}
	var me = (!<%=memberVO.getMember_id()%>)?  0 : <%=memberVO.getMember_id()%>;
	var EndPoint = "/FriendChat";
	var host = window.location.host;
	var path = window.location.pathname;
	var webCtx = path.substring(0,path.indexOf('/',1));
	var endPointURL = "ws://"+host+webCtx+EndPoint;
	var connectArray = new Array();
	var WSSessionArray = new Array();
	var nowWebSocket;
	var friendId;
	var friendFocus;
	
	//查看好友訊息
	$(".friend").click(function(that){
		friendFocus = that.target;
		var picurl = "<%=request.getContextPath()%>/front_end/member/getmemberpic?member_id=";
		$("#sendMessage").attr("disabled","true");
		$("#message").attr("disabled","true");
		friendID = ($(this).children("span")[0]);
		$(".friend").each(function(index){			
			$(this).css("background-color","white");
		})
		$(this).css("background-color","#F2DEDE");
		$("#msgtextarea").text("");
		fetch('<%=request.getContextPath()%>/front_end/friend/friend.do?action=getmsgs&myfriendID='+$(friendID).text()+'&member_id='+<%=memberVO.getMember_id()%>,{method: 'post'})
		.then(function(response){
			return response.text();})
		.then(function(msgs){
			var BIGjson = JSON.parse(msgs);	
	    	//查詢位置在2的不是好友關係，->好友請求
			if(BIGjson.relation_status == 2){
				$("#sendMessage").removeAttr("disabled");
				$("#message").removeAttr("disabled");
				if(!BIGjson.message){
					$("#msgtextarea").append("<div class='messagediv'>"
							 +"<p style='color:#383838'>你們已經成為好友!趕快留下第一則訊息吧</p><br>"
							 +"</div>");					
				}else{
					var jsonarray = BIGjson.message.split(/\n/g);
					jsonarray.forEach(function(vjson){
		 				var jsonObj = JSON.parse(vjson);
						parseMessage(jsonObj,$(friendID).text(),me);
					});
				}
			}else if(BIGjson.relation_status == 0 && BIGjson.position == 2){
				$("#msgtextarea").append("<div class='messagediv'>"
										 +"<img src='"+picurl+$(friendID).text()+"' class='sticky'>"
				 		  				 +"<p style='color:#383838'>請求成為好友!!</p><br>"
										 +"<button class='btn' id='reject'>拒絕</button><button class='btn' id='accept' onclick='acceptinvite()'>接受</button></div>");
			}else{
				$("#msgtextarea").append("<h1>好友邀請已經送出!!!等待接受</h1>");
			}			
		    startWSSession($(friendID).text(),<%=memberVO.getMember_id()%>);
			$("#textbody").scrollTop($("#textbody")[0].scrollHeight);
// 			var jsonObj = JSON.parse(msgs);
// 			vMsg = msgs.replace(/\n/g,"<br>");				
// 			var array = vMsg.split("<br>");
// 			var stringbuilder = "";
// 			array.forEach(function(that){
// 				var who = that.split("　")[0];
// 				if(who == me){
// 					stringbuilder += '<b class="positiobright">'+that.split("　")[1]+'</b>';
// 				}else if (who != null){
// 					stringbuilder += '<div class="name">'+who+':</div><br>'+that.split("　")[1]+'<br>';
// 				}				
// 			})
// 			$("#msgtextarea").html(stringbuilder);
		});			
	});
	
	function acceptinvite(){
		fetch('<%=request.getContextPath()%>/front_end/friend/friend.do?action=befriend&friendID='+$(friendID).text()+'&member_id='+<%=memberVO.getMember_id()%>,{method: 'post'})
		.then(function(response){
			if(response.status == 200 && response.statusText == "OK"){
				$(friendFocus).click();
			}
		});
	}	
	//為點擊不同好友開啟一條新的ws,如有舊的則從array中取出沿用。再進行ws操作
	function startWSSession(friendID,myID){
		$("a."+friendID).children("span.badge").text("");
		var wsurl = endPointURL+"/"+friendID+"/"+myID;
		if(connectArray.indexOf(wsurl) == -1){
			connectArray.push(wsurl);
			nowWebSocket = new WebSocket(wsurl);
			WSSessionArray.push({wsurl:nowWebSocket});			
		}else{				
			WSSessionArray.forEach(function(ws){          //從已有的連線中撈取未讀取資料
				if(ws.wsurl.url == wsurl){
					nowWebSocket = ws.wsurl;
					if(ws.msg){
						ws.msg.forEach(function(jsonObj){
							parseMessage(jsonObj,friendID,myID);
						});
						ws.msg = "";
					}
				}
			});
		}
		webSocketOperation(nowWebSocket,friendID,myID);
	}

	//註冊不同session的ws事件與操作
	function webSocketOperation(webSocket,friendID,myID){
		webSocket.onopen = function(event) {			
			console.log("WebSocket 成功連線");
		};
		var unread = 0;
		var msgArray = new Array();
		webSocket.onmessage = function(event) {
			if(webSocket == nowWebSocket){
				var jsonObj = JSON.parse(event.data);
				console.log(jsonObj);
				parseMessage(jsonObj,friendID,myID)
			}else{
				unread++;
				WSSessionArray.forEach(function(ws){
					if(ws.wsurl == webSocket){
						var jsonObj = JSON.parse(event.data);
						msgArray.push(jsonObj);
						ws.msg = msgArray;
						$("a."+friendID).children("span.badge").text(unread);
					}
				});
			}
		};
	}
	
	function sendMessage(){
		var inputMessage = document.getElementById("message");
	    var message = inputMessage.value.trim();
	    var time = new Date();
	    if(message == ""){inputMessage.focus();return}
        var jsonObj = {"whoSend" : <%=memberVO.getMember_id()%>, "message" : message,"time" : formatAMPM(time)};
        nowWebSocket.send(JSON.stringify(jsonObj));
		inputMessage.value = "";
		inputMessage.focus();
	}
	
	function disconnect () {
		if(nowWebSocket){
			nowWebSocket.close();
		}
	}
	
	function formatAMPM(date) {
	    var hours = date.getHours();
	    var minutes = date.getMinutes();
	    var ampm = hours >= 12 ? 'PM' : 'AM';
	    hours = hours % 12;
	    hours = hours ? hours : 12; // the hour '0' should be '12'
	    minutes = minutes < 10 ? '0'+minutes : minutes;
	    var strTime = hours + ':' + minutes + ' ' + ampm;
	    return strTime;
	}   
	
	function parseMessage(jsonObj,friendID,myID){
		if(jsonObj == null){
			return;
		}
		var picurl = "<%=request.getContextPath()%>/front_end/member/getmemberpic?member_id=";
		var message = jsonObj.whoSend + ": " + jsonObj.message + "\r\n";
		var time = jsonObj.time;		
		if(jsonObj.whoSend == me){
			$("#msgtextarea").append("<div class='messagediv positionright'>"
		        					 +"<img src='"+picurl+myID+"' class='sticky positionright'>"
		        					 +"<p style='color:#383838'>"+jsonObj.message+"</p>"
		        					 +"<p><small>"+time+"</small></p></div><br>");
		}else{
			$("#msgtextarea").append("<div class='messagediv'>"
		        					 +"<img src='"+picurl+friendID+"' class='sticky'>"
		        					 +"<p style='color:#383838;display: inline-block;'>"+jsonObj.message+"</p>"
		        					 +"<p><small>"+time+"</small></p></div><br>");
		}
		$("#textbody").scrollTop($("#textbody")[0].scrollHeight);		
	}
</script>
</html>