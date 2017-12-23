<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>    
<%@ page import="com.google.gson.*"%>
<% session.setAttribute("from_forward", request.getServletPath());%>
<% session.setAttribute("from_redirect", request.getRequestURI());%>

<%-- <% JsonArray ja = (JsonArray)session.getAttribute("orderrecord");%> --%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
    <head>
        <meta charset="utf-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
		<link rel="icon" type="image/gif" href="<%=request.getContextPath()%>/img/logo_192.png" />
		<title>Sneaker影城_會員中心</title>

        <!-- Bootstrap CSS CDN -->
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">  
        <!-- Scrollbar Custom CSS -->
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/malihu-custom-scrollbar-plugin/3.1.5/jquery.mCustomScrollbar.min.css">
        <!-- ICON CSS -->
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/font-awesome/4.4.0/css/font-awesome.min.css">
         <!-- Our Custom CSS -->
        <link rel="stylesheet" href="<%=request.getContextPath()%>/css/frontend.css">
    </head>
    <body id="myPage">

        <div class="wrapper">
        <!-- Include sidebar -->        
        <jsp:include page="/frontend/sidebar.jsp"/>
            
            <!-- Page Content -->
            <div id="content">
              
               <!-- Include sidebar --> 
            	<jsp:include page="/frontend/header.jsp"/>               

               <!-- 從這裡開始修改 -->
                <ol class="breadcrumb">
                    <li><a href="#">會員中心</a></li>
                    <li class="active"><a href="#">個人頁面</a></li>
                </ol>
                <div class="container">
                    <div class="col-xs-12 col-sm-4">
                        <div class="panel panel-danger">
                            <div class="panel-heading">
                                <h3 class="panel-title">個人資料</h3>
                            </div>
                            <div class="panel-body">
                                <img src="<%=request.getContextPath()%>/member/getmemberpic?member_id=${member.member_id}" style="width: 50%;border-radius: 50%;">
                               	 哈摟!<c:out value="${member.member_firstname}" default="親愛的會員"/>                                
                            </div>
                            <ul class="list-inline center-block text-center" style="margin-bottom: 25px">
                            <li><a href="#" class="list-group-item list-group-item-action list-group-item-danger">編輯會員資料</a></li>
                            <li><a href="#" class="list-group-item list-group-item-action list-group-item-danger">管理文章</a></li>
                            <li><a href="#" class="list-group-item list-group-item-action list-group-item-danger">好友管理</a></li>
                        </ul>
                        </div>
                    </div> 
                    <div class="col-xs-12 col-sm-4 text-center" style="background-color: #fff">
                        <div class="panel panel-success">
                            <div class="panel-heading">
                                <h2 class="panel-title">根據您喜好類型:動作片</h2>
                                <h3 class="panel-title">目前有電影上映:</h3>
                            </div>
                        </div>                      
                        <div class="thumbnail">
                            <img src="poster1.jpg" alt="">
                            <div class="caption">
                                <h4>電影名稱</h4>
                                <p>Lorem ipsum dolor sit amet, consectetur adipisicing elit, sed do eiusmod
                                tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam,
                                quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo
                                consequat. Duis aute irure dolor in reprehenderit in voluptate velit esse
                                cillum dolore eu fugiat nulla pariatur. Excepteur sint occaecat cupidatat non
                                proident, sunt in culpa qui officia deserunt mollit anim id est laborum.</p>                               
                            </div>
                        </div>
                        <div class="thumbnail">
                            <img src="poster1.jpg" alt="">
                            <div class="caption">
                                <h4>電影名稱</h4>
                                <p>Lorem ipsum dolor sit amet, consectetur adipisicing elit, sed do eiusmod
                                tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam,
                                quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo
                                consequat. Duis aute irure dolor in reprehenderit in voluptate velit esse
                                cillum dolore eu fugiat nulla pariatur. Excepteur sint occaecat cupidatat non
                                proident, sunt in culpa qui officia deserunt mollit anim id est laborum.</p>                               
                            </div>
                        </div>
                        <div class="thumbnail">
                            <img src="poster1.jpg" alt="">
                            <div class="caption">
                                <h4>電影名稱</h4>
                                <p>Lorem ipsum dolor sit amet, consectetur adipisicing elit, sed do eiusmod
                                tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam,
                                quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo
                                consequat. Duis aute irure dolor in reprehenderit in voluptate velit esse
                                cillum dolore eu fugiat nulla pariatur. Excepteur sint occaecat cupidatat non
                                proident, sunt in culpa qui officia deserunt mollit anim id est laborum.</p>                               
                            </div>
                        </div>
                        <div class="thumbnail">
                            <img src="poster1.jpg" alt="">
                            <div class="caption">
                                <h4>電影名稱</h4>
                                <p>Lorem ipsum dolor sit amet, consectetur adipisicing elit, sed do eiusmod
                                tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam,
                                quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo
                                consequat. Duis aute irure dolor in reprehenderit in voluptate velit esse
                                cillum dolore eu fugiat nulla pariatur. Excepteur sint occaecat cupidatat non
                                proident, sunt in culpa qui officia deserunt mollit anim id est laborum.</p>                               
                            </div>
                        </div>
                        <div class="thumbnail">
                            <img src="poster1.jpg" alt="">
                            <div class="caption">
                                <h4>電影名稱</h4>
                                <p>Lorem ipsum dolor sit amet, consectetur adipisicing elit, sed do eiusmod
                                tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam,
                                quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo
                                consequat. Duis aute irure dolor in reprehenderit in voluptate velit esse
                                cillum dolore eu fugiat nulla pariatur. Excepteur sint occaecat cupidatat non
                                proident, sunt in culpa qui officia deserunt mollit anim id est laborum.</p>                               
                            </div>
                        </div>
                    </div>
            <div class="col-xs-12 col-sm-4">
                <div class="panel panel-info">
                    <div class="panel-heading">
                        <h3 class="panel-title">最近五筆消費紀錄</h3>
                    </div>
                    <!-- 動態產生訂票紀錄 -->
                    <div class="panel-body">
<%--                      <% for(JsonElement jsonElement : ja){ --%>
//                        JsonObject jo = jsonElement.getAsJsonObject();
<%--                       %><p>電影:<%=jo.get("moviename")%><br> --%>
<%--                       日期:<%=jo.get("date")%>                               --%>
<%--                       <%}%>                                --%>
                    </div>
                </div>
                <div class="panel panel-danger">
                    <div class="panel-heading">
                        <h3 class="panel-title">優惠券</h3>
                    </div>
                    <div class="panel-body">
                        <p>優惠券</p>
                    </div>
                </div>
            </div>                    
        </div>
                <!-- 到這裡結束 -->
				<!-- include footer -->
                <jsp:include page="/frontend/footer.jsp"/>
            </div>            
        </div>

        <div class="overlay"></div>


        <!-- jQuery CDN -->
        <script src="https://code.jquery.com/jquery-1.12.0.min.js"></script>
        <!-- Bootstrap Js CDN -->
        <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
        <!-- jQuery Custom Scroller CDN -->
        <script src="https://cdnjs.cloudflare.com/ajax/libs/malihu-custom-scrollbar-plugin/3.1.5/jquery.mCustomScrollbar.concat.min.js"></script>

        <script src="<%=request.getContextPath()%>/js/frontend.js"></script>   

    </body>
</html>
