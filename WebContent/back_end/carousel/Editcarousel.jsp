<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.*"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width,initial-scale=1.0, shrink-to-fit=no">
<title>輪播牆編輯</title>
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/twitter-bootstrap/3.3.7/css/bootstrap.min.css">
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/font-awesome/4.4.0/css/font-awesome.min.css">
<!--[if lt IE 9]>
	<script src="https://cdnjs.cloudflare.com/ajax/libs/html5shiv/3.7.3/html5shiv.min.js"></script>
	<script src="https://cdnjs.cloudflare.com/ajax/libs/respond.js/1.4.2/respond.min.js"></script>
<![endif]-->
</head>
<style>
	.carouselnow img{
		width:50%;
	}
</style>
<body>
	<div class="page-header">
	  <h1>輪播牆編輯<small>真是好照片啊</small></h1>
	</div>
	<div class="container">
		<div class="row">
			<div class="col-xs-12 col-sm-12 carouselnow">
			carousel1目前圖片:<img src="<%=request.getContextPath()%>/img/carousel1.png">
			更換圖片<input type="file" id="carousel1" name="change">
			</div>
		</div>
	</div>
	<br>
	<div class="container">
		<div class="row">
			<div class="col-xs-12 col-sm-12 carouselnow">
			carousel2目前圖片:<img src="<%=request.getContextPath()%>/img/carousel2.jpg">
			更換圖片<input type="file" id="carousel2" name="change">			
			</div>
		</div>
	</div>
	<br>
	<div class="container">
		<div class="row">
			<div class="col-xs-12 col-sm-12 carouselnow">
			carousel3目前圖片:<img src="<%=request.getContextPath()%>/img/carousel3.png">
			更換圖片<input type="file" id="carousel3" name="change">
			</div>			
		</div>
	</div>
<!-- jQuery CDN -->
<script src="https://code.jquery.com/jquery-1.12.0.min.js"></script>
<!-- Bootstrap Js CDN -->
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
<!-- jQuery Custom Scroller CDN -->
<script src="https://cdnjs.cloudflare.com/ajax/libs/malihu-custom-scrollbar-plugin/3.1.5/jquery.mCustomScrollbar.concat.min.js"></script>
<script type="text/javascript">
//單張預覽
		function handleFileSelect(evt) {
			// FileList物件
			console.log(evt);
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
						var thisimg = $(evt).prev("img");
						console.log(thisimg);
						thisimg.attr({class:'thumb',src:e.target.result,title:escape(theFile.name)});			
					};
				})(f);				
			}
		}
		document.getElementById('carousel1').addEventListener('change',handleFileSelect, false);
		document.getElementById('carousel2').addEventListener('change',handleFileSelect, false);
		document.getElementById('carousel3').addEventListener('change',handleFileSelect, false);

</script>		
</body>
</html>