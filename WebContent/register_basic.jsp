<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Sneaker影城_註冊會員</title>
<!-- Bootstrap CSS CDN -->
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
<!-- Scrollbar Custom CSS -->
<link rel="stylesheet"
	href="https://cdnjs.cloudflare.com/ajax/libs/malihu-custom-scrollbar-plugin/3.1.5/jquery.mCustomScrollbar.min.css">
<!-- ICON CSS -->
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/font-awesome/4.4.0/css/font-awesome.min.css">
<!-- Our Custom CSS -->
<link rel="stylesheet" href="frontend.css">
</head>
<style type="text/css">
	label::before{
	content: '*';
	color:red;
}
</style>
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
                        <a href="index.html"><i class="fa fa-home"></i>首頁</a>
                    </li>
                    <li class="visible-xs">
                        <a href="membercenter.html"><i class="fa fa-user"></i>會員中心</a>
                    </li>
                    <li>
                        <a href="#"><i class="fa fa-film"></i>上映電影資訊</a>
                    </li>
                    <li>
                        <a href="forum.html"><i class="fa fa-commenting-o"></i>討論區</a>
                    </li>
                    <li>                
                        <a href="#">電影活動</a>
                    </li>
                    <li>
                        <a href="#"><i class="fa fa-bullhorn" aria-hidden="true"></i>影城公告</a>            
                    </li>
                    <li>
                        <a href="#"><i class="fa fa-cutlery" aria-hidden="true"></i>餐飲介紹</a>
                    </li>
                    <li>
                        <a href="#">影城介紹</a>
                    </li>
                    <li>
                        <a href="#">聯繫客服</a>
                    </li>
                </ul>
            </nav>
            <ul class="right-fastticket list-group hidden-xs">
                <a href="booking.html"><li class="list-group-item" id="right-fastticket"><i class="fa fa-ticket" aria-hidden="true"></i>快速訂票</li></a>
            </ul>
            <!-- Page Content Holder -->
            <div id="content">         
                <nav class="navbar navbar-default">
                    <div class="container-fluid">
                        <div class="navbar-header"> 
                            <button type="button" id="sidebarCollapse" class="glyphicon glyphicon-align-justify btn btn-info navbar-btn">
                            </button>
                            <img src="logo4.png">
                        </div>
                        <ul class="nav navbar-nav navbar-right hidden-xs">
                            <li><a href="membercenter.html" style="color:white;margin-top: 5px"><i class="fa fa-user"></i>會員中心</a></li>
                        </ul>                
                    </div>
                </nav>
                

                <!-- 從這裡開始修改 -->
                <ol class="breadcrumb">
                <li><a href="#">會員中心</a></li>
                <li class="active"><a href="#">註冊會員</a></li>
                </ol>                
                <div class="container">
                    <div class="row">                        
                        <div class="col-xs-12 col-sm-6 col-sm-offset-3"><h1>註冊會員</h1>
                            <ul class="list-inline" style="font-size: 20px;font-family:Microsoft JhengHei;">
                                <li class="active" style="color: white;background-image:url('step-next.png');background-size: 100% 100%;">1.基本資料</li>
                                <li>2.　進階資料</li>
                                <li>3.　完成討論區權限</li>
                            </ul>
                            <form method="post" action="insertmember.do" id="sentform" class="form-group mygroup" enctype="multipart/form-data">
                                <div class="form-group mygroup">
                                    <label for="member_emailaddress">電子信箱</label><input type="email" id="member_emailaddress" name="member_emailaddress" maxlength="30" class="form-control"><br>
                                </div>
                                <div class="form-group mygroup">
                                <label for="member_account">帳號</label><input type="text" id="member_account" name="member_account" maxlength="12" class="form-control"><br>
                                </div>
                                <div class="form-group mygroup">
                                    <label for="member_psw">密碼</label><input type="password" id="member_psw" name="member_psw" maxlength="12" class="form-control"><br>
                                </div>
                                <div class="form-group mygroup">
                                    <label for="member_psw_forcheck">確認密碼</label><input type="password" id="member_psw_forcheck" name="member_psw_forcheck" maxlength="12" class="form-control"><br>
                                </div>
                                <div class="form-group mygroup">
                                    <label for="member_lastname">姓</label><input type="text" id="member_lastname" name="member_lastname" maxlength="10" class="form-control"><br>
                                </div>
                                <div class="form-group mygroup">
                                    <label for="member_firstname">名</label><input type="text" id="member_firstname" name="member_firstname" maxlength="10" class="form-control"><br>
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
                                    <label for="mobilenum">手機號碼</label><input type="text" id="mobilenum" name="mobilenum" maxlength="10" class="form-control"><br>
                                </div>
                                <div class="form-group mygroup">
                                    <label for="member_birthday">生日</label><input type="date" id="member_birthday" name="member_birthday" class="form-control"><br>
                                </div>
                                <div class="form-group mygroup">
                                    <label for="member_idcode">身分證字號</label><input type="text" id="member_idcode" name="member_idcode" maxlength="10" class="form-control"><br>
                                </div>
                                <div class="form-group mygroup">
                                    <label for="creaditcard">信用卡號</label><input type="text" id="creaditcard" name="creaditcard" class="form-control"><br>
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
                                    <input type="button" value="取消" onclick="goback()"><input type="submit" value="下一步">
                                </form></div>
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
        	function goback(){
        	    window.history.back();
        	};
        
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
		<script>
			var chooseval;
			function choosecounty(thisselect) {
				chooseval = thisselect.value;
				var xhr = new XMLHttpRequest();
				xhr.onreadystatechange = function() {
					if (this.readyState == 4 && this.status == 200) {
						myFunction(this);
					}
				};
				xhr.open("GET", "taiwandistrict.json", true);
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
</body>
</html>