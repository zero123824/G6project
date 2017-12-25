<%@page import="java.util.ArrayList"%>
<%@page import="java.util.List"%>
<%@page import="com.movie.model.MovieVO"%>
<%@page import="java.util.Set"%>
<%@page import="com.member_favor.model.MemberFavorService"%>
<%@page import="com.member.model.MemberService"%>
<%@page import="com.member.model.MemberVO"%>
<%@page import="java.util.HashMap"%>
<%@page import="java.util.Map"%>
<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>    
<%@ page import="com.google.gson.*"%>
<!-- 沒登入重導至首頁 -->
<% 	MemberVO memberVO = (MemberVO)session.getAttribute("member"); 
	if( memberVO == null ){
		response.sendRedirect(request.getContextPath()+"/front_end/index.jsp");
	} %>
<!-- 取得當前頁面,重導和轉送時使用 -->
<% session.setAttribute("from_forward", request.getServletPath());%>
<% session.setAttribute("from_redirect", request.getRequestURI());%>
<% Map<String, String> status = new HashMap<String, String>();
	if(session.getAttribute("member")!=null){
		if(((MemberVO)session.getAttribute("member")).getSubsenews() == 0){
			status.put("subscribe", "是");
		}else{
			status.put("subscribe", "否");
		}
		pageContext.setAttribute("status", status);
	}
%>
<!-- 取得會員喜好類型名稱,換成中文。 根據喜好類型,找推薦電影。接放入pagecontext裡。-->
<%  int count = 0;
	MemberService memberSvc = new MemberService();
	MemberFavorService memberfavorSvc = new MemberFavorService();
	Map<String,String> favormap = new HashMap<String,String>();
	List<MovieVO> recommendmovie = new ArrayList<MovieVO>();
	if(memberVO != null){
 		Set<MovieVO> mvset = memberfavorSvc.getRecommendMovieByMemFavor(memberVO.getMember_id());
 		for(MovieVO mv : mvset){
 			recommendmovie.add(mv);
 		}
		pageContext.setAttribute("recommendmovie", recommendmovie);
		for(String favorname : memberSvc.getfavorTypeName(memberVO.getMember_id())) {
			count++;
			favormap.put("類型"+count, favorname);
			pageContext.setAttribute("favorname", favormap);
		}
	}%>

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
        <link rel="stylesheet" href="<%=request.getContextPath()%>/front_end/css/frontend.css">
    </head>
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
                                <img src="<%=request.getContextPath()%>/front_end/member/getmemberpic?member_id=${member.member_id}" style="width: 50%;border-radius: 50%;">
                               	 哈摟!<c:out value="${member.member_firstname}" default="親愛的會員"/>                                
                            </div>
                            <ul class="list-inline center-block text-center" style="margin-bottom: 25px">
	                            <li><a href="#" class="list-group-item list-group-item-action list-group-item-danger">編輯會員資料</a></li>
	                            <li><a href="#" class="list-group-item list-group-item-action list-group-item-danger">管理文章</a></li>
	                            <li><a href="#" class="list-group-item list-group-item-action list-group-item-danger">好友管理</a></li>
                        	</ul>
                        	<div class="panel-body showdata" id="editable">
                        		<h4>暱稱:<c:out value="${member.member_nickname}" default=""/></h4><br>
                        		<h4>姓名:<c:out value="${member.member_lastname}" default=""/> <c:out value="${member.member_firstname}" default=""/></h4><br>
                        		<h4>地址:<c:out value="${member.member_address}" default=""/></h4><br>
                        		<h4>手機號碼:<c:out value="${member.mobilenum}" default=""/></h4><br>
                        		<h4>電子信箱:<c:out value="${member.member_emailaddress}" default=""/></h4><br>
                        		<h4>訂閱電子報:<c:out value="${status.subscribe}" default=""/></h4><br>
                        	</div>                        	
                   		</div>
                   </div>
                   
					<div class="col-xs-12 col-sm-4 col-sm-push-4">
						<div class="panel panel-info">
		                	<div class="panel-heading">
		                        <h3 class="panel-title">最近五筆消費紀錄</h3>
							</div>
		                    <!-- 動態產生訂票紀錄 -->
		                    <div class="panel-body">
		                                                  
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
					
                   	<div class="col-xs-12 col-sm-4 col-sm-pull-4">
                        <div class="panel panel-success">
                            <div class="panel-heading">
                                <h2 class="panel-title">
                                	根據您喜好類型:
                                	<c:if test="${favorname != null}">
                                	${favorname.類型1}、
                                	${favorname.類型2}、
                                	${favorname.類型3}、
                                	${favorname.類型4}
                                	</c:if>
                                </h2>
                                <h3 class="panel-title">目前有電影上映:</h3>
                            </div>
                        </div>
                        <c:forEach var="recommend" items="${recommendmovie}">
                        <div class="thumbnail text-center">
                            <img src="<%=request.getContextPath()%>/front_end/member/getmc?member_id=${recommend.movie_id}" alt="${recommend.movie_name_en}">
                            <div class="caption">
                                <h4>${recommend.movie_name_ch}</h4>
                                <p class="introducecontent">${recommend.movie_introduce}</p>                               
                            </div>
                        </div>
                        </c:forEach>                    
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
	<script type="text/javascript">
	
		$(".caption p").each(function(){
			if($(this).text().length > 80) {
				$(this).html($(this).text().substring(0,100)+'<a style="font-size:14px;color:#0f52ba;font-weight:bold" href="#">......觀看詳細介紹</a>');
			};
		});
		
		
//		if($(".introducecontent").text().length > 100){
//			$(".introducecontent").text($(".introducecontent").text().substring(0,100)+'......觀看詳細介紹'); 
//		}
// 		$("#editable").children("h4").click(function(){
// 			$(".edit").hide();
// 			var content1 = $(this).text();
// 			$(this).append("<input type='text' id='edit2' value='"+content1+"'>");
// 			$(this).next("#editform").show().attr("class","editable");
// 			var content1 = $(this).parents("tr").children("#edit1").text();
// 			$(this).parents("tr").children("#edit1").html("<input type='text' id='edit1' value='"+content1+"'>");
// 			var content2 = $(this).parents("tr").children("#edit2").text();
// 			$(this).parents("tr").children("#edit2").html("<input type='text' id='edit2' value='"+content2+"'>");
// 			var content3 = $(this).parents("tr").children("#edit3").text();
// 			$(this).parents("tr").children("#edit3").html("<input type='text' id='edit3' value='"+content3+"'>");
// 			var content4 = $(this).parents("tr").children("#edit4").text();
// 			$(this).parents("tr").children("#edit4").html("<input type='text' id='edit4' value='"+content4+"'>");
// 		});
		
// 		$("form").submit(function(){
// 			var lastname = $(this).parents("tr").children("td").children("#edit1").val();
// 			var firstname = $(this).parents("tr").children("td").children("#edit2").val();
// 			var address = $(this).parents("tr").children("td").children("#edit3").val();
// 			var mobile = $(this).parents("tr").children("td").children("#edit4").val();	
			
// 			$(this).children("#lnchecked").attr("value",lastname);
// 			$(this).children("#fnchecked").attr("value",firstname);
// 			$(this).children("#addchecked").attr("value",address);
// 			$(this).children("#mobilechecked").attr("value",mobile);
// 			$(this).children("#emailchecked").attr("value","we3333");
// 			$(this).children("#cardchecked").attr("value","eresdf");
	
// 		})
	</script>    
</html>
