<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>    
<%@ page import="com.google.gson.*"%>
<% session.setAttribute("from_forward", request.getServletPath());%>
<% session.setAttribute("from_redirect", request.getRequestURI());%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
    <head>
        <meta charset="utf-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
		<link rel="icon" type="image/gif" href="<%=request.getContextPath()%>/img/logo_192.png" />
		<title>Sneaker影城</title>

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
                
                <!-- 輪播牆 -->
            
                <div class="carousel_custom">
                    <div class="row center-block">
                        <div class="col-xs-12 col-sm-12">
                            <div class="row">
                                <div id="myCarousel" class="carousel slide container" data-ride="carousel">
                                    <!-- Indicators -->
                                    <ol class="carousel-indicators">
                                        <li data-target="#myCarousel" data-slide-to="0" class="active"></li>
                                        <li data-target="#myCarousel" data-slide-to="1"></li>
                                        <li data-target="#myCarousel" data-slide-to="2"></li>
                                    </ol>

                                    <!-- Wrapper for slides -->
                                    <div class="carousel-inner img-responsive img-custom">
                                        <div class="item active">
                                            <img src="banner2.jpg" alt="leaves">
                                        </div>

                                        <div class="item">
                                            <img src="carousel2.jpg" alt="leaves-edit">
                                        </div>

                                        <div class="item">
                                            <img src="banner3.jpg" alt="car">
                                        </div>
                                    </div>

                                    <!-- Left and right controls -->
                                    <a class="left carousel-control" href="#myCarousel" data-slide="prev">
                                        <span class="glyphicon glyphicon-chevron-left"></span>
                                        <span class="sr-only">Previous</span>
                                    </a>
                                    <a class="right carousel-control" href="#myCarousel" data-slide="next">
                                        <span class="glyphicon glyphicon-chevron-right"></span>
                                        <span class="sr-only">Next</span>
                                    </a>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>

                <div class="container">
                    <!-- 按鈕 -->
                    <div class="list-group list-group-horizontal row">
                        <div class="container">
                                <div class="col-xs-4 col-sm-4"><button class="btn btn-danger mainbtn">現正上映</button></div>
                                <div class="col-xs-4 col-sm-4"><button class="btn btn-default mainbtn">即將上映</button></div>
                                <div class="col-xs-4 col-sm-4"><button class="btn btn-default mainbtn">票房排行</button></div>
                        </div>
                    </div>
                    <!-- 海報牆 -->
                    <div class="container dynamicwall">
                        <div class="row">
                        <div class="col-xs-12 col-sm-3">
                            <div class="thumbnail">
                                <a href="moviesinfo.html">
                                <img src="poster1.jpg" alt="">
                                    <div class="caption">
                                        <h2>中文電影名稱</h2>
                                        <p>上映時間:2017-12-01</p>         
                                    </div>
                                </a>
                            </div>
                        </div>
                        <div class="col-xs-12 col-sm-3">
                            <div class="thumbnail">
                                <a href="moviesinfo.html">
                                    <img src="poster2.jpg" alt="">
                                        <div class="caption">
                                            <h2>中文電影名稱</h2>
                                            <p>上映時間:2017-12-01</p>         
                                        </div>
                                </a>
                            </div>
                        </div>
                        <div class="col-xs-12 col-sm-3">
                            <div class="thumbnail">
                                <a href="moviesinfo.html">
                                    <img src="poster3.jpg" alt="">
                                        <div class="caption">
                                            <h2>中文電影名稱</h2>
                                                <p>上映時間:2017-12-01</p>         
                                        </div>
                                </a>
                            </div>
                        </div>
                        <div class="col-xs-12 col-sm-3">
                            <div class="thumbnail">
                                <img src="poster4.jpg" alt="">
                                    <div class="caption">
                                        <h2>中文電影名稱</h2>
                                        <p>上映時間:2017-12-01</p>         
                                    </div>
                            </div>
                        </div>
                        </div>
                    </div>
                    <div class="container discuss">
                        <div class="row">
                            <div class="line"></div>
                            <div class="col-xs-12 col-sm-12">
                                <table class="table table-hover">
                                    <caption class="text-center">討論區熱門文章</caption>
                                    <thead>
                                        <tr>
                                            <th>類型</th>
                                            <th>主題</th>
                                            <th>熱門度</th>
                                            <th>發布時間</th>
                                        </tr>
                                    </thead>
                                    <tbody>
                                        <tr>
                                            <td>情報</td>
                                            <td>雷神索爾好看</td>
                                            <td>75</td>
                                            <td>2017-12-05</td>
                                        </tr>
                                        <tr>
                                            <td>情報</td>
                                            <td>雷神索爾好看</td>
                                            <td>75</td>
                                            <td>2017-12-05</td>
                                        </tr>
                                        <tr>
                                            <td>情報</td>
                                            <td>雷神索爾好看</td>
                                            <td>75</td>
                                            <td>2017-12-05</td>
                                        </tr>
                                        <tr>
                                            <td>情報</td>
                                            <td>雷神索爾好看</td>
                                            <td>75</td>
                                            <td>2017-12-05</td>
                                        </tr>
                                        <tr>
                                            <td>情報</td>
                                            <td>雷神索爾好看</td>
                                            <td>75</td>
                                            <td>2017-12-05</td>
                                        </tr>
                                        <tr>
                                            <td>情報</td>
                                            <td>雷神索爾好看</td>
                                            <td>75</td>
                                            <td>2017-12-05</td>
                                        </tr>
                                        <tr>
                                            <td>情報</td>
                                            <td>雷神索爾好看</td>
                                            <td>75</td>
                                            <td>2017-12-05</td>
                                        </tr>
                                        <tr>
                                            <td>情報</td>
                                            <td>雷神索爾好看</td>
                                            <td>75</td>
                                            <td>2017-12-05</td>
                                        </tr>
                                        <tr>
                                            <td>情報</td>
                                            <td>雷神索爾好看</td>
                                            <td>75</td>
                                            <td>2017-12-05</td>
                                        </tr>
                                        <tr>
                                            <td>情報</td>
                                            <td>雷神索爾好看</td>
                                            <td>75</td>
                                            <td>2017-12-05</td>
                                        </tr>
                                    </tbody>
                                </table>
                                <br>
                                <div class="text-center">
                                    <ul class="pagination pagination-sm">
                                     <li><a href="#">&laquo;</a></li>
                                     <li class="active"><a href="#">1</a></li>
                                     <li><a href="#">2</a></li>
                                     <li><a href="#">3</a></li>
                                     <li><a href="#">4</a></li>
                                     <li><a href="#">5</a></li>
                                     <li><a href="#">&raquo;</a></li>
                                 </ul>
                             </div>
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
		<!-- customjs -->
        <script src="<%=request.getContextPath()%>/js/frontend.js"></script>   
    </body>
</html>