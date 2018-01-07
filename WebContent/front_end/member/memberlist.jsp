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
	.positiobright{
		float: right;
    	color:#468f06;
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
								<div class="panel-body">
								<div id="msgtextarea">
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
                                    <div class="col-xs-12 col-sm-2">
	                                    <a class="thumbnail friend">
	                                    	<span style="display:none">${friend.member_id}</span>
	    									<img src="<%=request.getContextPath()%>/front_end/member/getmemberpic?member_id=${friend.member_id}" style="width: 30%;border-radius: 30%;">
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
<%--                 <jsp:include page="/front_end/template/footer.jsp"/> --%>
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
<script type="text/javascript">
	//搜尋會員
	function search(tar){		
		$("#membetcontainer").empty();
		var keyword = $(tar).val();
		var fd = new FormData();
		fd.set("keyword",keyword);
		//ajax回傳搜尋會員 回傳json陣列
		fetch('<%=request.getContextPath()%>/front_end/member/member.do?action=search',{method: 'post',body:fd})
		.then(function(response){
			return(response.json())
		})
		.then(function(msg){
			$(msg).each(function(index){
				console.log(index);
				var membername = $(this)[0].member_lastname+$(this)[0].member_firstname;
				var memberid = $(this)[0].member_id;
				var membernick = $(this)[0].member_nickname;
				var picurl = "<%=request.getContextPath()%>/front_end/member/getmemberpic?member_id=";
				$("#membetcontainer").append("<li class='list-group-item'><img src='"+picurl+memberid+"' style='width:80px;height:80px;display:inline-block'><p style='display:inline-block;color:black;'>"+membername+"("+membernick+")"+"</p><i class='fa fa-plus-circle plus' aria-hidden='true'></i><br>");				
			})
		});
	}

	//查看好友訊息
	$(".friend").click(function(){		
		var area = '<div class="panel input-area"><input id="message" class="text-field"'
		+'type="text" placeholder="Message" onkeydown="if (event.keyCode == 13)'
		+'sendMessage();" /> <input type="submit" id="sendMessage" class="button" value="Send"'
		+'onclick="sendMessage();" /></div>';
		var friendID = ($(this).children("span")[0]);
		$(friendID).text();
		fetch('<%=request.getContextPath()%>/front_end/friend/friend.do?action=getmsgs&myfriendID='+$(friendID).text()+'&member_id='+<%=memberVO.getMember_id()%>,{method: 'post'})
		.then(function(response){
			return response.text();})
		.then(function(msgs){
			vMsg = msgs.replace(/\n/g,"<br>");				
			var array = vMsg.split("<br>");
			var stringbuilder = "";
			array.forEach(function(that){
				var who = that.split("　")[0];
				if(who == 'Me'){
					stringbuilder += '<b class="positiobright">'+that.split("　")[1]+'</b>';
				}else if (who != null){
					stringbuilder += '<div class="name">'+who+':</div><br>'+that.split("　")[1]+'<br>';
				}				
			})
		    console.log(stringbuilder);
			$("#msgtextarea").html(stringbuilder+area);
		});			
	});
	
	function sendMessage(){
		console.log("sending");
	}
</script>
</html>