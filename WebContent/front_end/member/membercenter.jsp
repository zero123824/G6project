<%@page import="com.friend.model.FriendService"%>
<%@page import="java.util.ArrayList"%>
<%@page import="java.util.List"%>
<%@page import="java.util.Collections"%>
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
<!-- 取得目前麵包屑位置 -->
<% 	request.setAttribute("hereis", "center");%>
<!-- 取得會員喜好類型名稱,換成中文。 根據喜好類型,找推薦電影。接放入pagecontext裡。-->
<%  MemberVO memberVO = (MemberVO)session.getAttribute("member");
	int count = 0;
	MemberService memberSvc = new MemberService();
	MemberFavorService memberfavorSvc = new MemberFavorService();
	pageContext.setAttribute("memberfavorSvc", memberfavorSvc);
 	Map<String,String> favormap = new HashMap<String,String>();
	List<MovieVO> recommendmovie = new ArrayList<MovieVO>();
	if(memberVO != null){
 		Set<MovieVO> mvset = memberfavorSvc.getRecommendMovieByMemFavor(memberVO.getMember_id());
 		for(MovieVO mv : mvset){
 			recommendmovie.add(mv);
 		}
// 		Collections.shuffle(recommendmovie);
		pageContext.setAttribute("recommendmovie", recommendmovie);
//計算有幾個喜歡的類型
		for(String favorname : memberSvc.getfavorTypeName(memberVO.getMember_id())) {
			count++;
			favormap.put("類型"+count, favorname);
			pageContext.setAttribute("favorname", favormap);
		}
	}%>
<%	FriendService friendSvc = new FriendService();
	pageContext.setAttribute("friendSvc",friendSvc);%>
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
    <style>
    	.message-area{
    		height:100px;
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
                    <li class="active"><a href="#">個人頁面</a></li>
                </ol>
                <div class="container">
                    <div class="col-xs-12 col-sm-4">
                        <div class="panel panel-danger person_data">
                            <div class="panel-heading">
                                <h3 class="panel-title">個人資料</h3>
                            </div>
                            <div class="panel-body">
                                <img src="<%=request.getContextPath()%>/front_end/member/getmemberpic?member_id=${member.member_id}" style="width: 50%;border-radius: 50%;">
                               	 哈摟!<c:out value="${member.member_firstname}" default="親愛的會員"/>                                
                            </div>
                            <ul class="list-inline center-block text-center" style="margin-bottom: 25px">
	                            <li><a id="ajaxgetmemberdata" data-toggle="modal" data-target="#modal-edittable" class="list-group-item list-group-item-action list-group-item-danger">編輯會員資料</a></li>
	                            <li><a href="#" class="list-group-item list-group-item-action list-group-item-danger">管理文章</a></li>
	                            <li><a id="friendlist" class="list-group-item list-group-item-action list-group-item-danger">好友管理</a></li>
                        	</ul>
                        	<div class="panel-body showdata" id="editable">
                        		<h4>暱稱:<c:out value="${member.member_nickname}" default=""/></h4><br>
                        		<h4>姓名:<c:out value="${member.member_lastname}" default=""/> <c:out value="${member.member_firstname}" default=""/></h4><br>
                        		<h4>地址:<c:out value="${member.member_address}" default=""/></h4><br>
                        		<h4>手機號碼:<c:out value="${member.mobilenum}" default=""/></h4><br>
                        		<h4>電子信箱:<c:out value="${member.member_email}" default=""/></h4><br>
                        		<h4>訂閱電子報:<c:out value="${(member.subsenews == 1)? '是':'否'}" default=""/></h4><br>
                        	</div>                        	
                   		</div>
						<div class="panel panel-info friend_list" class="thumbnail">
							<div class="panel-heading">
								<button type="button" class="close" style="color:red;font-size: 16px;opacity: .3;" onclick="backtoPD()">返回個人資料</button>
								<h3 class="panel-title">好友列表</h3>
							</div>
							<div class="panel-body">
								<c:forEach var="friend" items="${friendSvc.getOneMemFriends(member.member_id)}">
                                    <a class="thumbnail friend">
                                    	<span style="display:none">${friend.member_id}</span>
    									<img src="<%=request.getContextPath()%>/front_end/member/getmemberpic?member_id=${friend.member_id}" style="width: 30%;border-radius: 30%;">
    									<span>${friend.member_lastname}${friend.member_firstname}</span>
                                    </a>							
								</c:forEach>
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
                                	根據喜好類型:
                                	<c:forEach var="memberfavorVO" items="${memberfavorSvc.getOneMemFavor(member.member_id)}"></c:forEach>
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
                            <img src="<%=request.getContextPath()%>/front_end/member/movieposter?movie_id=${recommend.movie_id}" alt="${recommend.movie_name_en}">
                            <div class="caption">
                            	<h4>${recommend.movie_date}</h4>                            	
                                <h4>${recommend.movie_name_ch}</h4>
                                <p class="introducecontent">${recommend.movie_introduce}</p>                               
                            </div>
                        </div>
                        </c:forEach>                    
                   </div>        
        		</div>

                <div class="modal fade" id="modal-edittable" tabindex="-1" role="dialog"
                        aria-labelledby="myModalLabel" aria-hidden="true" style="display: none;">
                    <div class="modal-dialog">
                        <div class="modal-content">
                            <div class="modal-header">
                                <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
                                <h4 class="modal-title">修改資料</h4>
                            </div>
                            <div class="modal-body">
							<FORM METHOD="post" id="editform" ACTION="<%=request.getContextPath()%>/front_end/member/member.do" style="margin-bottom: 0px;">
                            	<h4>帳號:</h4><input type='text' name="member_account" value="${member.member_account}" disabled></h4>
                                <h4>暱稱:</h4><input type='text' name="member_nickname" value="${member.member_nickname}"><br>
                                <h4>姓名:</h4><input type='text' name="member_name" value="${member.member_lastname}${member.member_firstname}"><br>
                                <h4>地址:</h4><input type='text' name="member_address" value="${member.member_address}"><br>
                                <h4>手機號碼:</h4><input type='text' name="mobilenum" value="${member.mobilenum}" maxlength="10"><br>
                                <h4>電子信箱:</h4><input type='text' name="member_email" value="${member.getMember_email()}"><br>
                                <h4>生日:</h4><input type='text' value="${member.member_birthday}"><br>
                                <h4>身份證字號:</h4><input type='text' name="member_idcode" value="${member.member_idcode}"><br>
                                <h4>訂閱電子報:</h4>
                                <input type="radio" class="magic-radio" name="subsenews" id="subsenews_true" 
                                value="1" <c:if test="${member.getSubsenews() == 1 }">checked</c:if>><label for="subsenews_true">是</label>
                                <input type="radio" class="magic-radio" name="subsenews" id="subsenews_false" 
                                value="0" <c:if test="${member.getSubsenews() == 0 }">checked</c:if>><label for="subsenews_false">否</label><br>
								<h4>挑選喜好電影類型:</h4>
								<a href="#modalpopover" role="button" data-modal-position="relative" data-toggle="modal-popover" 
								data-placement="bottom"></a></td>
								<input type="hidden" name="action"	value="update">
								<input type="hidden" name="member_id" value="${member.member_id}">
								<input type="checkbox" name="operation_id" value="15001"></input>
						       	<input type="checkbox" name="operation_id" value="15002"></input>
						       	<input type="checkbox" name="operation_id" value="15003"></input>
						       	<input type="checkbox" name="operation_id" value="15004"></input>
						       	<input type="checkbox" name="operation_id" value="15005"></input>
						       	<input type="checkbox" name="operation_id" value="15006"></input>
						       	<input type="checkbox" name="operation_id" value="15007"></input>
						       	<input type="checkbox" name="operation_id" value="15008"></input>
						       	<input type="checkbox" name="operation_id" value="15009"></input>							                          	
                            </FORM>
                            </div>	    
                            <div class="modal-footer">
                                <button type="button" class="btn btn-default" data-dismiss="modal">關閉</button>
                                <button type="button" class="btn btn-primary" onClick="doupadte()">儲存變更</button>
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
    <!-- modal置中 -->
	<script type="text/javascript">
        function setBstModalMaxHeight(element) {
          this.$element          = $(element);
          this.$modalContent     = this.$element.find('.modal-content');
          var $window            = $(window);
          var $modalContentOH    = this.$modalContent.outerHeight();
          var $modalContentIH    = this.$modalContent.innerHeight();
          var _modalBorderWidth   = $modalContentOH - $modalContentIH;
          var _modalDialogMargin  = $window.width() < 768 ? 20 : 60;
          var _modalContentHeight = $window.height() - (_modalDialogMargin + _modalBorderWidth);
          var _modalHeaderHeight  = this.$element.find('.modal-header').outerHeight() || 0;
          var _modalFooterHeight  = this.$element.find('.modal-footer').outerHeight() || 0;
          var _modalMaxHeight     = _modalContentHeight - (_modalHeaderHeight + _modalFooterHeight);
        
          this.$modalContent.css({
              'overflow': 'hidden'
          });
          
          this.$element
            .find('.modal-body').css({
              'max-height': _modalMaxHeight,
              'overflow-y': 'auto'
          });
        }
        
        $('.modal').on('show.bs.modal', function() {
          $(this).show();
          setBstModalMaxHeight(this);
        });
        
        $(window).resize(function() {
          if ($('.modal.in').length != 0) {
            setBstModalMaxHeight($('.modal.in'));
          }
        });
	</script>
	<script type="text/javascript">   
		function doupadte(){
			$("#editform").submit();
		}
//只顯示電影內容前100個字
		$(".caption p").each(function(){
			if($(this).text().length > 80) {
				$(this).html($(this).text().substring(0,100)+'<a style="font-size:14px;color:#0f52ba;font-weight:bold" href="#">......觀看詳細介紹</a>');
			};
		});
//好友列表與個人資料切換		
		$("#friendlist").click(function(){
			$(".person_data").hide(500,function(){
				$(".friend_list").show(250);
			});
		})
		function backtoPD(){
			$(".friend_list").hide(500,function(){
				$(".person_data").show(250);
			});
		}
		
		$(".friend").click(function(){
			var friendID = ($(this).children("span")[0]);
			$(friendID).text();
			fetch('<%=request.getContextPath()%>/front_end/friend/friend.do?action=getmsgs&myfriendID='+$(friendID).text()+'&member_id='+<%=memberVO.getMember_id()%>,{method: 'post'})
			.then(function(response){
				console.log(response);
				return response.text();})
			.then(function(msgs){

			});			
		})
	</script>
</html>
