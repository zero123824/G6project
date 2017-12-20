<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>    
<%@ page import="com.google.gson.*"%>
<% session.setAttribute("from", request.getRequestURI());%>
<% JsonArray ja = (JsonArray)session.getAttribute("orderrecord");%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
    <head>
        <meta charset="utf-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">

        <title>Sneaker影城</title>

        <!-- Bootstrap CSS CDN -->
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">  
        <!-- Scrollbar Custom CSS -->
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/malihu-custom-scrollbar-plugin/3.1.5/jquery.mCustomScrollbar.min.css">
        <!-- ICON CSS -->
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/font-awesome/4.4.0/css/font-awesome.min.css">
         <!-- Our Custom CSS -->
        <link rel="stylesheet" href="frontend.css">
    </head>
    <body id="myPage">

        <div class="wrapper">
            <!-- Sidebar Holder -->
            <nav id="sidebar">
                <div id="dismiss">
                    <i class="glyphicon glyphicon-arrow-left"></i>
                </div>
                <div class="sidebar-header">
                    <img src="logo4.png">
                </div>
                <ul class="list-unstyled components">                    
                    <li class="active">
                        <a href="index.jsp"><i class="fa fa-home fa-lg"></i>　首頁</a>
                    </li>
                    <li class="visible-xs">
                        <a href="membercenter.jsp"><i class="fa fa-user fa-lg"></i>　會員中心</a>
                    </li>
                    <li>
                        <a href="#"><i class="fa fa-film fa-lg"></i>　上映電影資訊</a>
                    </li>
                    <li>
                        <a href="forum.html"><i class="fa fa-commenting-o fa-lg"></i>　討論區</a>
                    </li>
                    <li>                
                        <a href="#"><i class="fa fa-newspaper-o fa-lg" aria-hidden="true"></i>　電影活動</a>
                    </li>
                    <li>
                        <a href="#"><i class="fa fa-bullhorn fa-lg" aria-hidden="true"></i>　影城公告</a>            
                    </li>
                    <li>
                        <a href="#"><i class="fa fa-cutlery fa-lg" aria-hidden="true"></i>　餐飲介紹</a>
                    </li>
                    <li>
                        <a href="#"><i class="fa fa-info-circle fa-lg" aria-hidden="true"></i>　影城介紹</a>
                    </li>
                    <li>
                        <a href="#"><i class="fa fa-question fa-lg" aria-hidden="true"></i>　聯繫客服</a>
                    </li>
                </ul>
            </nav>
            <ul class="right-fastticket list-group hidden-xs">
                <a href="booking.html"><li class="list-group-item" id="right-fastticket"><i class="fa fa-ticket" aria-hidden="true"></i>快速訂票</li></a>
            </ul>

            <div class="modal fade" id="login-modal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true" style="display:none;">
                <div class="modal-dialog">
                    <div class="loginmodal-container">
                        <h1>登入您的帳號</h1><br>
                        <form method="post" action="Login">
                            <input type="hidden" name="action" value="login">
                            <input type="text" name="member_account" placeholder="帳號">
                            <input type="password" name="member_psw" placeholder="密碼">
                            <input type="submit" name="login" class="login loginmodal-submit" value="確認輸入">
                        </form>

                        <div class="login-help">
                            <a href="memberregister.html">註冊會員</a> - <a href="#">忘記密碼</a>
                        </div>
                    </div>
                </div>
            </div>
            <!-- Page Content Holder -->
            <div id="content">         
                <nav class="navbar navbar-default">
                    <div class="container-fluid">
                        <div class="navbar-header"> 
                            <button type="button" id="sidebarCollapse" class="glyphicon glyphicon-align-justify btn btn-info navbar-btn">
                            </button>
                            <a href="index.jsp"><img src="logo4.png"></a>
                        </div>
                        <ul class="nav navbar-nav navbar-right hidden-xs">
                        	<c:if test="${empty member}">
                            	<li><a data-toggle="modal" data-target="#login-modal" style="color:white;margin-top: 5px"><i class="fa fa-user"></i> 會員中心</a></li>
                            </c:if>
                            <c:if test="${not empty member}">
                            	<li><a href="membercenter.jsp" style="color:white;margin-top: 5px"><i class="fa fa-user"></i> ${member.member_account} 您好!</a></li>
                            	<li><a onclick="document.getElementById('logoutform').submit();" style="color:white;margin-top: 5px">登出</a></li> 
                            </c:if>
                        </ul>                 
                    </div>
                </nav>
                <form id="logoutform" method="post" action="Login">
                	<input type="hidden" name="action" value="logout">
                </form>
                

                <!-- 從這裡開始修改 -->
                <ol class="breadcrumb">
                    <li><a href="#">會員中心</a></li>
                    <li class="active"><a href="#">個人頁面</a></li>
                </ol>
                <div class="container">
                    <div class="row">
                    <div class="col-xs-12 col-sm-6 col-sm-offset-3">
                        <ul class="list-inline center-block text-center" style="margin-bottom: 25px">
                            <li><a href="#" class="list-group-item list-group-item-action list-group-item-danger">編輯會員資料</a></li>
                            <li><a href="#" class="list-group-item list-group-item-action list-group-item-danger">管理文章</a></li>
                            <li><a href="#" class="list-group-item list-group-item-action list-group-item-danger">好友管理</a></li>
                        </ul>
                    </div>
                    </div>
                </div>
                <div class="container">
                    <div class="col-xs-12 col-sm-4">
                        <div class="panel panel-info">
                            <div class="panel-heading">
                                <h3 class="panel-title">最近五筆消費紀錄</h3>
                            </div>
                            <!-- 動態產生訂票紀錄 -->
                            <div class="panel-body">
                            <% for(JsonElement jsonElement : ja){
								JsonObject jo = jsonElement.getAsJsonObject();
     							%><p>電影:<%=jo.get("moviename")%><br>
    							日期:<%=jo.get("date")%>								
								<%}%>                               
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
                    <div class="panel panel-danger">
                      <div class="panel-heading">
                        <h3 class="panel-title">標題</h3>
                    </div>
                    <div class="panel-body">
                        內容文字
                    </div>
                </div>
            </div>                                
        </div>
                <!-- 到這裡結束 -->
                <footer class="text-center">
                    <a class="up-arrow" href="#myPage" data-toggle="tooltip" title="TO TOP"><span class="glyphicon glyphicon-chevron-up"></span></a><br><br>
                    <p>&copy; 2017 BA105 SNEAKER CINEMAS<p>
                </footer>
            </div>            
        </div>

        <div class="overlay"></div>


        <!-- jQuery CDN -->
        <script src="https://code.jquery.com/jquery-1.12.0.min.js"></script>
        <!-- Bootstrap Js CDN -->
        <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
        <!-- jQuery Custom Scroller CDN -->
        <script src="https://cdnjs.cloudflare.com/ajax/libs/malihu-custom-scrollbar-plugin/3.1.5/jquery.mCustomScrollbar.concat.min.js"></script>

        <script type="text/javascript">
            $(document).ready(function () {
                $("#sidebar").mCustomScrollbar({
                    theme: "minimal"
                });

                $('#dismiss, .overlay').on('click', function () {
                    $('#sidebar').removeClass('active');
                    $('.overlay').fadeOut();
                });

                $('#sidebarCollapse').on('click', function () {
                    $('#sidebar').addClass('active');
                    $('.overlay').fadeIn();
                    $('.collapse.in').toggleClass('in');
                    $('a[aria-expanded=true]').attr('aria-expanded', 'false');
                });

                // Initialize Tooltip
                $('[data-toggle="tooltip"]').tooltip(); 
                $(".navbar a, footer a[href='#myPage']").on('click', function(event) {

                // Make sure this.hash has a value before overriding default behavior
                if (this.hash !== "") {

                // Prevent default anchor click behavior
                event.preventDefault();

                // Store hash
                var hash = this.hash;

                // Using jQuery's animate() method to add smooth page scroll
                // The optional number (900) specifies the number of milliseconds it takes to scroll to the specified area
                $('html, body').animate({
                    scrollTop: $(hash).offset().top
                }, 900, function(){
                // Add hash (#) to URL when done scrolling (default click behavior)
                window.location.hash = hash;
            });
                } // End if 
            });
            });
        </script>
    </body>
</html>
