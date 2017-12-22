<%@page import="com.member.model.MemberVO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<% session.setAttribute("from_forward", request.getServletPath());%>
<% session.setAttribute("from_redirect", request.getRequestURI());%>
<!-- basic註冊版本 -->
<%	
	MemberVO member = (MemberVO)session.getAttribute("member");
	MemberVO memberVO = (MemberVO)request.getAttribute("memberVO");

%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="icon" type="image/gif" href="<%=request.getContextPath()%>/img/logo_192.png" />
<title>Sneaker影城_註冊會員</title>
<!-- Bootstrap CSS CDN -->
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
<!-- Scrollbar Custom CSS -->
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/malihu-custom-scrollbar-plugin/3.1.5/jquery.mCustomScrollbar.min.css">
<!-- ICON CSS -->
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/font-awesome/4.4.0/css/font-awesome.min.css">
<!-- Our Custom CSS -->
<link rel="stylesheet" href="<%=request.getContextPath()%>/css/frontend.css">
</head>
<style type="text/css">
	label::before {
	content: '*';
	color: red;
}
</style>
<body id="myPage">	
	<div class="wrapper">
		<!-- Include sidebar -->
		<jsp:include page="/frontend/sidebar.jsp" />

		<!-- Page Content -->
		<div id="content">

			<!-- Include sidebar -->
			<jsp:include page="/frontend/header.jsp" />

			<!-- 從這裡開始修改 -->
			<ol class="breadcrumb">
                <li><a href="#">會員中心</a></li>
                <li class="active"><a href="#">註冊會員</a></li>
                </ol>                
                <div class="container">
                    <div class="row">                        
                        <div class="col-xs-12 col-sm-6 col-sm-offset-3"><h1>註冊會員</h1>
                            <ul class="list-inline" style="font-size: 20px;font-family:Microsoft JhengHei;">
                                <li>1.基本資料</li>
                                <li class="active" style="color: white;background-image:url('<%=request.getContextPath()%>/img/step-next.png');background-size: 100% 100%;">2.　進階資料</li>
                                <li>3.　完成討論區權限</li>
                            </ul>
                            <c:if test="${not empty errorMsgs}">
								<font style="color:red">請修正以下錯誤:</font>
								<ul>
									<c:forEach var="message" items="${errorMsgs}">
										<li style="color:red">${message}</li>
									</c:forEach>
								</ul>
							</c:if>
                            <form method="post" action="<%=request.getContextPath()%>/member/member.do" id="sentform" class="form-group mygroup" enctype="multipart/form-data">
                                <div class="form-group mygroup">
                                    <label for="member_emailaddress">電子信箱</label>
                                    <input type="email" id="member_emailaddress" name="member_emailaddress" maxlength="30" 
                                    class="form-control" value="<%= (memberVO == null) ? "": memberVO.getMember_emailaddress()%>" placeholder="請輸入電子信箱"><br>
                                </div>
                                <div class="form-group mygroup">
                                	<label for="member_account">帳號</label>
                                	<input type="text" id="member_account" name="member_account" maxlength="12" 
                                	class="form-control" value="<%= (memberVO == null) ? "": memberVO.getMember_account()%>" placeholder="請輸入帳號4-12碼英文+數字"><br>
                                </div>
                                <div class="form-group mygroup">
                                    <label for="member_psw">密碼</label>
                                    <input type="password" id="member_psw" name="member_psw" maxlength="12" minlength="6"
                                    class="form-control" placeholder="請輸入密碼6-12碼"><br>
                                </div>
                                <div class="form-group mygroup">
                                    <label for="member_psw_forcheck">確認密碼</label>
                                    <input type="password" id="member_psw_forcheck" name="member_psw_forcheck" maxlength="12" minlength="6"
                                    class="form-control" placeholder="請再次輸入密碼"><br>
                                </div>
                                <div class="form-group mygroup">
                                    <label for="member_lastname">姓</label>
                                    <input type="text" id="member_lastname" name="member_lastname" maxlength="10" 
                                    class="form-control" value="<%= (memberVO == null) ? "": memberVO.getMember_lastname()%>" placeholder="請輸入姓氏"><br>
                                </div>
                                <div class="form-group mygroup">
                                    <label for="member_firstname">名</label>
                                    <input type="text" id="member_firstname" name="member_firstname" maxlength="10" 
                                    class="form-control" value="<%= (memberVO == null) ? "": memberVO.getMember_firstname()%>" placeholder="請輸入名字"><br>
                                </div>
                                <div class="form-group mygroup">
                                    <label for="member_address">通訊地址</label>
                                    <select name="county" onchange="choosecounty(this)">
                                        <option selected="selected" value="-1">請選擇</option>
                                        <option value="city2">基隆市</option>
                                        <option value="city1">台北市</option>
                                        <option value="city3">新北市</option>
                                        <option value="city8">桃園縣</option>
                                        <option value="city6">新竹市</option>
                                        <option value="city8">新竹縣</option>
                                        <option value="city10">苗栗縣</option>
                                        <option value="city11">台中市</option>
                                        <option value="city13">彰化縣</option>
                                        <option value="city14">南投縣</option>
                                        <option value="city17">雲林縣</option>
                                        <option value="city15">嘉義市</option>
                                        <option value="city16">嘉義縣</option>
                                        <option value="city18">台南市</option>
                                        <option value="city20">高雄市</option>
                                        <option value="city25">屏東縣</option>
                                        <option value="city26">台東縣</option>
                                        <option value="city27">花蓮縣</option>
                                        <option value="city5">宜蘭縣</option>
                                        <option value="city23">澎湖縣</option>
                                        <option value="city24">金門縣</option>
                                        <option value="city4">連江縣</option>
                                    </select>
                                    <select name="area" id="area">
                                    </select>
                                    <input type="text" id="member_address" name="member_address" maxlength="40" class="form-control"><br>
                                </div>
                                <div class="form-group mygroup">
                                    <label for="mobilenum">手機號碼</label>
                                    <input type="text" id="mobilenum" name="mobilenum" maxlength="10" 
                                    class="form-control" value="<%= (memberVO == null) ? "": memberVO.getMobilenum()%>" placeholder="請輸入手機號碼"><br>
                                </div>
                                <div class="form-group mygroup">
                                    <label for="member_birthday">生日</label>
                                    <input type="text" id="f_date1" name="member_birthday" 
                                    class="form-control" value="<%= (memberVO == null) ? "": memberVO.getMember_birthday()%>"><br>
                                </div>
                                <div class="form-group mygroup">
                                    <label for="member_idcode">身分證字號</label>
                                    <input type="text" id="member_idcode" name="member_idcode" maxlength="10" 
                                    class="form-control" value="<%= (memberVO == null) ? "": memberVO.getMember_idcode()%>" placeholder="請輸入身份證字號"><br>
                                </div>
                                <div class="form-group mygroup">
                                    <label for="creaditcard">信用卡號</label>
                                    <input type="text" id="creaditcard" name="creaditcard" 
                                    class="form-control"><br>
                                </div>
                                <div class="form-group mygroup">
									性別
                                	<input type="radio" class="magic-radio" name="member_sex" id="men" value="1" class="form-control"><label for="men" class="radio-inline">男</label>
                                    <input type="radio" class="magic-radio" name="member_sex" id="women" value="2" class="form-control"><label for="women" class="radio-inline">女</label><br>
                                </div>
								是否訂閱電子報
                                <input type="radio" class="magic-radio" name="subsenews" id="subsenews_true" value="1"><label for="subsenews_true">是</label>
                                <input type="radio" class="magic-radio" name="subsenews" id="subsenews_false" value="2"><label for="subsenews_false">否</label><br>
								喜好電影類型<br>
																
                                <input class="magic-checkbox" type="checkbox" name="favortype" value="0" id="type0"><label for="type0">動作</label> 
                                <input class="magic-checkbox" type="checkbox" name="favortype" value="1" id="type1"><label for="type1">冒險</label> 
                                <input class="magic-checkbox" type="checkbox" name="favortype" value="2" id="type2"><label for="type2">科幻</label> 
                                <input class="magic-checkbox" type="checkbox" name="favortype" value="3" id="type3"><label for="type3">奇幻</label> 
                                <input class="magic-checkbox" type="checkbox" name="favortype" value="4" id="type4"><label for="type4">劇情</label> 
                                <input class="magic-checkbox" type="checkbox" name="favortype" value="5" id="type5"><label for="type5">犯罪</label> 
                                <input class="magic-checkbox" type="checkbox" name="favortype" value="6" id="type6"><label for="type6">恐怖</label> 
                                <input class="magic-checkbox" type="checkbox" name="favortype" value="7" id="type7"><label for="type7">懸疑/驚悚</label> 
                                <input class="magic-checkbox" type="checkbox" name="favortype" value="8" id="type8"><label for="type8">喜劇</label> 
                                <input class="magic-checkbox" type="checkbox" name="favortype" value="9" id="type9"><label for="type9">愛情</label> 
                                <input class="magic-checkbox" type="checkbox" name="favortype" value="10" id="type10"><label for="type10">家庭/溫馨</label> 
                                <input class="magic-checkbox" type="checkbox" name="favortype" value="11" id="type11"><label for="type11">動畫</label> 
                                <input class="magic-checkbox" type="checkbox" name="favortype" value="12" id="type12"><label for="type12">戰爭</label> 
                                <input class="magic-checkbox" type="checkbox" name="favortype" value="13" id="type13"><label for="type13">音樂/歌舞</label> 
                                <input class="magic-checkbox" type="checkbox" name="favortype" value="14" id="type14"><label for="type14">歷史/傳記</label> 
                                <input class="magic-checkbox" type="checkbox" name="favortype" value="15" id="type15"><label for="type15">紀錄片</label> 
                                <input class="magic-checkbox"  type="checkbox" name="favortype" value="16" id="type16"><label for="type16">勵志</label> 
                                <input class="magic-checkbox" type="checkbox" name="favortype" value="17" id="type17"><label for="type17">武俠</label> 
                                <input class="magic-checkbox" type="checkbox" name="favortype" value="18" id="type18"><label for="type18">影展</label> 
                                <br>
                                <input type="file" name="member_pic">
                                <input type="button" value="取消" onclick="goback()">
                                <input type="submit" value="確認">
                                <input type="hidden" name="action" value="register">
                             </form>
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
	</body>
        <link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/datetimepicker/jquery.datetimepicker.css" />
		<script src="<%=request.getContextPath()%>/datetimepicker/jquery.datetimepicker.full.js"></script>
		<!-- customjs -->
		<script src="<%=request.getContextPath()%>/js/frontend.js"></script>
<% 
		java.sql.Date member_birthday = null;
		try {
			member_birthday = member.getMember_birthday();
		} catch (Exception e) {
			member_birthday = new java.sql.Date(System.currentTimeMillis());
		}
%>
		<style>
		  .xdsoft_datetimepicker .xdsoft_datepicker {
		           width:  300px;   /* width:  300px; */
		  }
		  .xdsoft_datetimepicker .xdsoft_timepicker .xdsoft_time_box {
		           height: 151px;   /* height:  151px; */
		  }
		</style>
        <script>
	        $.datetimepicker.setLocale('zh');
	        $('#f_date1').datetimepicker({
		       theme: '',              //theme: 'dark',
		       timepicker:false,       //timepicker:true,
		       step: 1,                //step: 60 (這是timepicker的預設間隔60分鐘)
		       format:'Y-m-d',         //format:'Y-m-d H:i:s',
			   value: '<%=member_birthday%>', // value:   new Date(),
	           //disabledDates:        ['2017/06/08','2017/06/09','2017/06/10'], // 去除特定不含
	           //startDate:	            '2017/07/10',  // 起始日
	           //minDate:               '-1970-01-01', // 去除今日(不含)之前
	           //maxDate:               '+1970-01-01'  // 去除今日(不含)之後
	        });
			var chooseval;
			function choosecounty(thisselect) {
				chooseval = thisselect.value;
				var xhr = new XMLHttpRequest();
				xhr.onreadystatechange = function() {
					if (this.readyState == 4 && this.status == 200) {
						myFunction(this);
					}
				};
				xhr.open("GET", "<%=request.getContextPath()%>/taiwandistrict.json", true);
				xhr.send();
			}
			function myFunction(jsonobject) {
				var json = JSON.parse(jsonobject.responseText);
				var city = json[chooseval];
				var options = "";
				var areas = city.area;
				for (var i = 0; i < city.area.length; i++) {
					options += "<option>" + city.area[i] + "</option>";
				}
				document.getElementById("area").innerHTML = options;
			}
		</script>    
</html>