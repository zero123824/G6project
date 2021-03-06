<%@page import="com.member.model.MemberVO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!-- basic註冊版本 -->
<%	
	MemberVO member = (MemberVO)session.getAttribute("member");
	MemberVO memberVO = (MemberVO)request.getAttribute("memberVO");

%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<link rel="icon" type="image/gif" href="<%=request.getContextPath()%>/img/logo_192.png" />
<title>Sneaker影城_註冊會員</title>
<!-- Bootstrap CSS CDN -->
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
<!-- Scrollbar Custom CSS -->
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/malihu-custom-scrollbar-plugin/3.1.5/jquery.mCustomScrollbar.min.css">
<!-- ICON CSS -->
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/font-awesome/4.4.0/css/font-awesome.min.css">
<!-- datepicker CSS from 老師 -->
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/front_end/datetimepicker/jquery.datetimepicker.css" />
<!-- Our Custom CSS -->
<link rel="stylesheet" href="<%=request.getContextPath()%>/front_end/css/frontend.css">
</head>
<style type="text/css">
	strong::before {
	content: '*';
	color: red;
    }
    .title_active{
        padding: 5px;       
        text-align: center;
        color: white;
        background:url('<%=request.getContextPath()%>/img/step-next.png') no-repeat;
        background-size: 100% 100%;
    }
    #titleforward li{
    	font-size: 18px;
    	padding-left: 20px;
        padding-right: 15px;
        letter-spacing: 2px;
    	font-family:"Microsoft JhengHei";
    }    
    .selectable{
        border-radius: 4px;
    }
    .custombtn{
        width: 140px;
        height: 55px;
        border: none;
        border-radius: 4px;
        font-size: 16px;
        letter-spacing: 6px;    
    }
    .cancelbtn{
        background-color: #C6C6C6;
        color:black;
    }
    .submitbtn{
        background-color: #b71c1c;
        color:#d9d9d9;             
    }
</style>
<body id="myPage">	
	<div class="wrapper">
		<!-- Include sidebar -->
		<jsp:include page="/front_end/template/sidebar.jsp" />

		<!-- Page Content -->
		<div id="content">

			<!-- Include sidebar -->
			<jsp:include page="/front_end/template/header.jsp" />

			<!-- 從這裡開始修改 -->
			<ol class="breadcrumb">
                <li><a href="#">會員中心</a></li>
                <li class="active"><a href="#">註冊會員</a></li>
                </ol>                
                <div class="container">
                    <div class="row">                        
                        <div class="col-xs-12 col-sm-6 col-sm-offset-3"><h1>註冊會員</h1>
                            <ul class="list-inline" id="titleforward">
                                <li class="title_active">基本資料</li>
                                <li>進階資料</li>
                                <li>完成討論區權限</li>
                            </ul>
                            <c:if test="${not empty errorMsgs}">
								<font style="color:red">請修正以下錯誤:</font>
								<ul>
									<c:forEach var="message" items="${errorMsgs}">
										<li style="color:red">${message}</li>
									</c:forEach>
								</ul>
							</c:if>
                            <form method="post" action="<%=request.getContextPath()%>/front_end/member/member.do" id="sentform" class="form-group mygroup" enctype="multipart/form-data">
                                <div class="basic">
                                <strong style="font-size:22px;color:#beb8b8;">為必填項目</strong>
                                <div class="form-group mygroup">
                                    <strong for="member_email">電子信箱</strong><b id="emailcheck" style="color:red"></b>
                                    <input type="email" id="member_email" name="member_email" maxlength="30" onchange="ajaxverify(this)" 
                                    class="form-control" value="<%= (memberVO == null) ? "": memberVO.getMember_email()%>" placeholder="請輸入電子信箱"><br>
                                </div>
                                <div class="form-group mygroup">
                                	<strong for="member_account">帳號</strong>
                                	<input type="text" id="member_account" name="member_account" maxlength="12" onchange="ajaxverify(this)"
                                	class="form-control" value="<%= (memberVO == null) ? "": memberVO.getMember_account()%>" placeholder="請輸入帳號4-12碼英文+數字"><br>
                                </div>
                                <div class="form-group mygroup">
                                    <strong for="member_psw">密碼</strong>
                                    <input type="password" id="member_psw" name="member_psw" maxlength="12" minlength="6"
                                    class="form-control" placeholder="請輸入密碼6-12碼"><br>
                                </div>
                                <div class="form-group mygroup">
                                    <strong for="member_psw_forcheck">確認密碼</strong>
                                    <input type="password" id="member_psw_forcheck" name="member_psw_forcheck" maxlength="12" minlength="6"
                                    class="form-control" placeholder="請再次輸入密碼"><br>
                                </div>
                                <div class="form-group mygroup">
                                    <strong for="member_lastname">姓</strong>
                                    <input type="text" id="member_lastname" name="member_lastname" maxlength="10" 
                                    class="form-control" value="<%= (memberVO == null) ? "": memberVO.getMember_lastname()%>" placeholder="請輸入姓氏"><br>
                                </div>
                                <div class="form-group mygroup">
                                    <strong for="member_firstname">名</strong>
                                    <input type="text" id="member_firstname" name="member_firstname" maxlength="10" 
                                    class="form-control" value="<%= (memberVO == null) ? "": memberVO.getMember_firstname()%>" placeholder="請輸入名字"><br>
                                </div>
                                <br><br><br>
                                </div>
                                
                                <div class="advanced" style="display:none" >
                                <div class="form-group mygroup">
                                    <strong for="member_address">通訊地址</strong>
                                    <select class="selectable" name="county" onchange="choosecounty(this)" >
                                        <option selected="selected" value="-1">請選擇</option>
                                        <option value="city2">基隆市</option>
                                        <option value="city1">台北市</option>
                                        <option value="city3">新北市</option>
                                        <option value="city9">桃園市</option>
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
                                    <select class="selectable" name="area" id="area">
                                    </select>
                                    <input type="text" id="member_address" name="member_address" maxlength="40" class="form-control"
                                   	value="<%= (memberVO == null) ? "": memberVO.getMember_address()%>" placeholder="請輸入地址"><br>
                                </div>
                                <div class="form-group mygroup">
                                    <strong for="mobilenum">手機號碼</strong>
                                    <input type="text" id="mobilenum" name="mobilenum" maxlength="10" 
                                    class="form-control" value="<%= (memberVO == null) ? "": memberVO.getMobilenum()%>" placeholder="請輸入手機號碼"><br>
                                </div>
                                <div class="form-group mygroup">
                                    <strong for="member_birthday">生日</strong>
                                    <input type="text" id="f_date1" name="member_birthday" 
                                    class="form-control" value="<%= (memberVO == null) ? "": memberVO.getMember_birthday()%>"><br>
                                </div>
                                <div class="form-group mygroup">
                                    <strong for="member_idcode">身分證字號</strong>
                                    <input type="text" id="member_idcode" name="member_idcode" maxlength="10" 
                                    class="form-control" value="<%= (memberVO == null) ? "": memberVO.getMember_idcode()%>" placeholder="請輸入身份證字號"><br>
                                </div>
                                <div class="form-group mygroup">
                                    <strong for="creaditcard">信用卡號</strong>
                                    <input type="text" id="creaditcard" name="creaditcard" 
                                    class="form-control"><br>
                                </div>
                                <br><br><br><br><br><br><br><br><br>
                                </div>

                                <div class="notneed" style="display:none">
                                <div class="form-group mygroup">
								<strong>性別</strong>
                                	<input type="radio" class="magic-radio" name="member_sex" id="men" 
                                	value="1" class="form-control" <c:if test="${memberVO.getMember_sex() == 1 }">checked</c:if>><label for="men">男</label>
                                    <input type="radio" class="magic-radio" name="member_sex" id="women" 
                                    value="2" class="form-control" <c:if test="${memberVO.getMember_sex() == 2 }">checked</c:if>><label for="women">女</label><br>
                                </div>
								是否訂閱電子報
                                <input type="radio" class="magic-radio" name="subsenews" id="subsenews_true" 
                                value="1" <c:if test="${memberVO.getSubsenews() == 1 }">checked</c:if>><label for="subsenews_true">是</label>
                                <input type="radio" class="magic-radio" name="subsenews" id="subsenews_false" 
                                value="0" <c:if test="${memberVO.getSubsenews() == 0 }">checked</c:if>><label for="subsenews_false">否</label><br>
								喜好電影類型<br>																
                                <input class="magic-checkbox" type="checkbox" name="favortype" value="90001" id="type1"><label for="type1">動作</label> 
                                <input class="magic-checkbox" type="checkbox" name="favortype" value="90002" id="type2"><label for="type2">冒險</label> 
                                <input class="magic-checkbox" type="checkbox" name="favortype" value="90003" id="type3"><label for="type3">科幻</label> 
                                <input class="magic-checkbox" type="checkbox" name="favortype" value="90004" id="type4"><label for="type4">奇幻</label> 
                                <input class="magic-checkbox" type="checkbox" name="favortype" value="90005" id="type5"><label for="type5">劇情</label> 
                                <input class="magic-checkbox" type="checkbox" name="favortype" value="90006" id="type6"><label for="type6">犯罪</label> 
                                <input class="magic-checkbox" type="checkbox" name="favortype" value="90007" id="type7"><label for="type7">恐怖</label> 
                                <input class="magic-checkbox" type="checkbox" name="favortype" value="90008" id="type8"><label for="type8">懸疑/驚悚</label> 
                                <input class="magic-checkbox" type="checkbox" name="favortype" value="90009" id="type9"><label for="type9">喜劇</label> 
                                <input class="magic-checkbox" type="checkbox" name="favortype" value="90010" id="type10"><label for="type10">愛情</label> 
                                <input class="magic-checkbox" type="checkbox" name="favortype" value="90011" id="type11"><label for="type11">家庭/溫馨</label> 
                                <input class="magic-checkbox" type="checkbox" name="favortype" value="90012" id="type12"><label for="type12">動畫</label> 
                                <input class="magic-checkbox" type="checkbox" name="favortype" value="90013" id="type13"><label for="type13">戰爭</label> 
                                <input class="magic-checkbox" type="checkbox" name="favortype" value="90014" id="type14"><label for="type14">音樂/歌舞</label> 
                                <input class="magic-checkbox" type="checkbox" name="favortype" value="90015" id="type15"><label for="type15">歷史/傳記</label> 
                                <input class="magic-checkbox" type="checkbox" name="favortype" value="90016" id="type16"><label for="type16">紀錄片</label> 
                                <input class="magic-checkbox" type="checkbox" name="favortype" value="90017" id="type17"><label for="type17">勵志</label> 
                                <input class="magic-checkbox" type="checkbox" name="favortype" value="90018" id="type18"><label for="type18">武俠</label> 
                                <input class="magic-checkbox" type="checkbox" name="favortype" value="90019" id="type19"><label for="type19">影展</label> 
                                <br><br>
                                <div class="form-group mygroup">
                                    <label for="member_nickname">暱稱</label>
                                    <input type="text" id="member_nickname" name="member_nickname" maxlength="10" 
                                    class="form-control" value="<%= (memberVO == null) ? "": memberVO.getMember_nickname()%>" placeholder="取個好聽的暱稱吧^^"><br>
                                </div><br><br>
								<h5>上傳一張大頭貼吧</h5>
                                <input type="file" id="member_pic" name="member_pic">
                                <img id="member_pic_preview" style="width:100%"></img>
                                <br><br><br><br><br><br><br><br><br><br><br><br><br>
                                </div><br>
                                <div class="text-center">             
                                <input type="button" class="cancelbtn custombtn" value="取消註冊" id="backstep">
                                <input type="submit" class="submitbtn custombtn" value="確認" id="submit" style="display: none;">
                                <input type="button" class="submitbtn custombtn" value="下一步" id="nextstep">
                                <input type="hidden" name="action" value="register">
                                </div>
                             </form>
                             <br><br>
                           </div>
                         </div>
                      </div>
                        <!-- 到這裡結束 -->
				<!-- include footer -->
<%--                 <jsp:include page="/front_end/template/footer.jsp"/> --%>
            </div>            
        </div>
        <div class="overlay"></div>
		</body>
		<!-- jQuery CDN -->
        <script src="https://code.jquery.com/jquery-1.12.0.min.js"></script>
        <!-- Bootstrap Js CDN -->
        <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
        <!-- jQuery Custom Scroller CDN -->
        <script src="https://cdnjs.cloudflare.com/ajax/libs/malihu-custom-scrollbar-plugin/3.1.5/jquery.mCustomScrollbar.concat.min.js"></script>
		<!-- datepickerJS from老師 -->		
		<script src="<%=request.getContextPath()%>/front_end/datetimepicker/jquery.datetimepicker.full.js"></script>
		<!-- customjs -->
		<script src="<%=request.getContextPath()%>/front_end/js/frontend.js"></script>
<% 
		java.sql.Date member_birthday = null;
		try {
			member_birthday = memberVO.getMember_birthday();
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
	           maxDate:               '+1970-01-01'  // 去除今日(不含)之後
	        });
			
			//控制資料顯示以及標題
            var stepcount = 1;
            $("#nextstep").click(function(){
            	$("#backstep").val("上一步")
                $(".title_active").removeClass(function(){
                	$(this).removeClass("title_active");
                    return $(this).next("li").addClass("title_active")
                });
                if(stepcount === 2){
                    $(".advanced").hide(function(){
                        $(".notneed").show();
                    });
                    $("#submit").show();
                    $("#nextstep").hide();
                }
                $(".basic").hide(function(){
                    $(".advanced").show();
                });
                stepcount++;
//                 history.pushState(stepcount, "test", "step1/1");
            });
            
            $("#backstep").click(function(){
            	$(".title_active").removeClass(function(){
                	$(this).removeClass("title_active");
                    return $(this).prev("li").addClass("title_active")
                });
                if(stepcount === 1){
                    window.history.back();
                }else if(stepcount === 2){
	                $("#backstep").val("取消註冊");
	                $(".advanced").hide(function(){
	                    $(".basic").show();
	                });    
	                stepcount--;
                }else{
	                $(".notneed").hide(function(){
	                    $(".advanced").show();
	                    $("#submit").hide();
	                    $("#nextstep").show();
	                });
	                stepcount--;
                }
            });
            
            var chooseval;
			function choosecounty(thisselect) {
				chooseval = thisselect.value;
				var xhr = new XMLHttpRequest();
				xhr.onreadystatechange = function() {
					if (this.readyState == 4 && this.status == 200) {
						getAreafromjson(this);
					}
				};
				xhr.open("GET", "<%=request.getContextPath()%>/front_end/taiwandistrict.json", true);
				xhr.send();
			}
			function getAreafromjson(jsonobject) {
				var json = JSON.parse(jsonobject.responseText);
				var city = json[chooseval];
				var options = "";
				var areas = city.area;
				for (var i = 0; i < city.area.length; i++) {
					options += "<option>" + city.area[i] + "</option>";
				}
				document.getElementById("area").innerHTML = options;
			}
			$("#member_email").bind("blur", validate);
			function validateEmail(email) {
				  var regex = /^(([^<>()[\]\\.,;:\s@\"]+(\.[^<>()[\]\\.,;:\s@\"]+)*)|(\".+\"))@((\[[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\])|(([a-zA-Z\-0-9]+\.)+[a-zA-Z]{2,}))$/;
				  return regex.test(email);
			}			
			function validate() {
				 $("#emailcheck").text("");
				 var email = $("#member_email").val();
				 if (validateEmail(email)) {
				    $("#member_email").css("background-color", "#DFF0D8");
				 } else {
					$("#emailcheck").append("<br>非有效電子信箱");
				    $("#member_email").css("background-color", "#F2DEDE");
				 }
				 return false;
			}
		</script>
	<script>
	
		//單張大頭貼預覽
		function handleFileSelect(evt) {
			// FileList物件
			var files = evt.target.files; 		
			// Loop through the FileList and render image files as thumbnails.
			for (var i = 0, f; f = files[i]; i++) {
	
				// 只處理image類型的檔案上傳
				if (!f.type.match('image.*')) {
					continue;
				}
				var reader = new FileReader();
				// Read in the image file as a data URL.
				reader.readAsDataURL(f);
				// Closure to capture the file information.
				reader.onload = (function(theFile) {
					return function(e) {
						// Render thumbnail.			
						$("#member_pic_preview").attr({class:'thumb',src:e.target.result,title:escape(theFile.name)});			
					};
				})(f);				
			}
		}
		document.getElementById('member_pic').addEventListener('change',handleFileSelect, false);
		
		//ajax驗證帳號與電子信箱是否OK
// 		function ajaxverify(this){
<%-- 			fetch('<%=request.getContextPath()%>/back_end/employee/emp.do',{method: 'post',body:newempform}) --%>
// 			.then(function(response){	
// 				if (response.redirected) {
// 					window.location = response.url ;
// 				}
// 				return response.json();
// 			})
// 		}
	</script>
</html>