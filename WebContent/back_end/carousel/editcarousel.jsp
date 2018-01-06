<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.*"%>
<% 	response.setHeader("Cache-Control", "no-store");
	response.setHeader("Pragma", "no-cache");
	response.setDateHeader("Expires", 0);%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width,initial-scale=1.0, shrink-to-fit=no">
<title>輪播牆編輯</title>
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/twitter-bootstrap/3.3.7/css/bootstrap.min.css">
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/font-awesome/4.4.0/css/font-awesome.min.css">
<!-- 剪裁圖片CSS -->
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/cropper/3.1.3/cropper.min.css">
<!--[if lt IE 9]>
	<script src="https://cdnjs.cloudflare.com/ajax/libs/html5shiv/3.7.3/html5shiv.min.js"></script>
	<script src="https://cdnjs.cloudflare.com/ajax/libs/respond.js/1.4.2/respond.min.js"></script>
<![endif]-->
</head>
<style>
	.carouselnow, .carouselpreview {
		width:50%;
	}
	
	.carouselnow img, .carouselpreview canvas{
		width:100%;
	}
	
	#submit{
		width: 10%;
		height: 60px;
		background-color:#00e4ff;
		border: none;
	}
</style>
<body>
	<div class="page-header">
	  <h1>輪播牆編輯<small>真是好照片啊</small></h1>
	</div>
	<form method="post" enctype="multipart/form-data" id="form" action="<%=request.getContextPath()%>/img/changecarousel.do?action=change"> 
	<div class="container">
		<input type="submit" id="submit" value="送出更新">	
		<div class="row">
			<div class="col-xs-12 col-sm-6 carouselnow">
				carousel1目前圖片:<img src="/img/carousel1.png?random=<%=Math.random()%>">
				<input type="file" class="carouselbtn" id="carousel1" name="carousel1.png">
				<input type="button" name="" value="預覽" class="imgSubmit">
			</div>			
			<div class="col-xs-12 col-sm-6 carouselpreview">
				預覽圖片
				<div id="crop"></div>
			</div>
		</div>
	</div>
	<br>
	<div class="container">
		<div class="row">
			<div class="col-xs-12 col-sm-6 carouselnow">
			carousel2目前圖片:<img src="/img/carousel2.jpg?random=<%=Math.random()%>">
			<input type="file" class="carouselbtn" id="carousel2" name="carousel2.jpg">
			<input type="button" name="" value="預覽" class="imgSubmit">	
			</div>
			<div class="col-xs-12 col-sm-6 carouselpreview">			
				預覽圖片				
				<div id="crop"></div>
			</div>
		</div>
	</div>
	<br>
	<div class="container">
		<div class="row">
			<div class="col-xs-12 col-sm-6 carouselnow">
			carousel3目前圖片:<img src="/img/carousel3.png?random=<%=Math.random()%>">
			<input type="file" class="carouselbtn" id="carousel3" name="carousel3.png">
			<input type="button" name="" value="預覽" class="imgSubmit">	
			</div>
			<div class="col-xs-12 col-sm-6 carouselpreview">
				預覽圖片	
				<div id="crop"></div>
			</div>
			<br>
		</div>
	</div>
	<input type="hidden" name="pageFrom" value="<%=request.getRequestURL()%>">
	</form>
<!-- jQuery CDN -->
<script src="https://code.jquery.com/jquery-1.12.0.min.js"></script>
<!-- 剪裁圖片js -->
<script src="https://cdnjs.cloudflare.com/ajax/libs/cropper/3.1.3/cropper.min.js"></script>
<!-- Bootstrap Js CDN -->
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
<!-- jQuery Custom Scroller CDN -->
<script src="https://cdnjs.cloudflare.com/ajax/libs/malihu-custom-scrollbar-plugin/3.1.5/jquery.mCustomScrollbar.concat.min.js"></script>
<script type="text/javascript">
//單張預覽
// 		function handleFileSelect(evt) {
// 			// FileList物件
// 			var files = evt.target.files; 		
// 			// Loop through the FileList and render image files as thumbnails.
// 			for (var i = 0, f; f = files[i]; i++) {
	
// 				// 只處理image類型的檔案上傳
// 				if (!f.type.match('image.*')) {
// 					continue;
// 				}
// 				var reader = new FileReader();
// 				// Read in the image file as a data URL.
// 				reader.readAsDataURL(f);
// 				// Closure to capture the file information.
// 				reader.onload = (function(theFile) {
// 					return function(e) {
// 						// Render thumbnail.	
// 						var thisinput = evt.target;
// 						var thisimg = $(thisinput).prev("img");
// 						thisimg.attr({class:'thumb',src:e.target.result,title:escape(theFile.name)});			
// 					};
// 				})(f);				
// 			}
// 		}
// 		document.getElementById('carousel1').addEventListener('change',handleFileSelect, false);
// 		document.getElementById('carousel2').addEventListener('change',handleFileSelect, false);
// 		document.getElementById('carousel3').addEventListener('change',handleFileSelect, false);

		var formdata = new FormData();
		  //生成裁剪區域
	    $(".carouselbtn").change(function(e){
	    	var input = $(this);
			var name = ($(this).attr("name"));
	    	$(this).siblings("img").cropper('destroy');
// 	    	$(".box > img").cropper('destroy');
	        var file = $(input).get(0).files[0];
	        var reader = new FileReader();
	        reader.readAsDataURL(file);
	        reader.onload=function(e){
	        	$(input).prev("img").attr("src",e.target.result);
// 	           $("#preview").attr("src",e.target.result);
// 	            $(".box > img")
	          	$(input).prev("img").cropper({
	                aspectRatio: 3 / 1,
	                autoCropArea: 1,
	                movable:false,
	                resizable:false,
	                zoomable:false,
	                crop: function(e) {
	                	formdata.set("which",name);
	                	var param = new Array(e.x,e.y,e.width,e.height); 
	                	formdata.set("param",param);
	                	formdata.set("x",e.x);
	                	formdata.set("y",e.y);
	                	formdata.set("width",e.width);
	                	formdata.set("height",e.height);
	                }
	            }); 
	        }	        
	    })
	    //預覽按鈕
	    $(".imgSubmit").click(function(target){
	    	var pf = $(target.target).parents(".row").children(".carouselpreview").children("#crop");
	    	var result = $(target.target).siblings("img").cropper("getCroppedCanvas");
	    	$(pf).html(result);
			fetch('<%=request.getContextPath()%>/img/changecarousel.do?action=setSize',{method: 'post',body:formdata})
			.then(function(response){
				console.log(response);
			});
	    })
// 		function imgSubmit(){
//         	var result= $('.box > img').cropper("getCroppedCanvas");
//         	$(".avatar-preview").target = result;
//         	document.getElementById("carousel1").after(result);
// 	    	$("#crop").html(result);
// 		}    
</script>		
</body>
</html>