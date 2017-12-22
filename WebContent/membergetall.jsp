<%@page import="com.member.model.*"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.*"%>
<%
    MemberService memSvc = new MemberService();
    List<MemberVO> list = memSvc.getAll();
    pageContext.setAttribute("list",list);
%>    
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<!-- <style>
  table#table-1 {
	background-color: #CCCCFF;
    border: 2px solid black;
    text-align: center;
  }
  table#table-1 h4 {
    color: red;
    display: block;
    margin-bottom: 1px;
  }
  h4 {
    color: blue;
    display: inline;
  }
</style>

<style>
  table {
	width: 800px;
	background-color: white;
	margin-top: 5px;
	margin-bottom: 5px;
  }
  table, th, td {
    border: 1px solid #CCCCFF;
  }
  th, td {
    padding: 5px;
    text-align: center;
  }
</style>
 -->
<body>
<%-- 錯誤表列 --%>
<c:if test="${not empty errorMsgs}">
	<font style="color:red">請修正以下錯誤:</font>
	<ul>
		<c:forEach var="message" items="${errorMsgs}">
			<li style="color:red">${message}</li>
		</c:forEach>
	</ul>
</c:if>
<table>
	<tr>
		<th>會員帳號</th>
		<th>會員姓</th>
		<th>會員名</th>
		<th>地址</th>
		<th>手機號碼</th>
		<th>生日日期</th>
		<th>性別</th>
		<th>大頭貼</th>
		<th>狀態</th>
		<th>停權</th>
		<th>修改</th>
		
	</tr>
	
	<%@ include file="page1.file" %> 
	<c:forEach var="memberVO" items="${list}" begin="<%=pageIndex%>" end="<%=pageIndex+rowsPerPage-1%>">
		
		<tr>
			<td>${memberVO.member_account}</td>
			<td id="edit1">${memberVO.member_lastname}</td>
			<td id="edit2">${memberVO.member_firstname}</td>
			<td id="edit3">${memberVO.member_address}</td>
			<td id="edit4">${memberVO.mobilenum}</td>
			<td>${memberVO.member_birthday}</td> 
			<td>${memberVO.member_sex}</td>
			<td><img src="<%=request.getContextPath()%>/member/getmemberpic?member_id=${memberVO.member_id}" style="width: 50%;border-radius: 50%;">
			<td>${memberVO.member_lock_status}</td>
			<td>
			  <FORM METHOD="post" ACTION="<%=request.getContextPath()%>/member/member.do" style="margin-bottom: 0px;">
			     <input type="submit" value="停權">
			     <input type="hidden" name="member_id" value="${memberVO.member_id}">
			     <input type="hidden" name="action" value="suspend"></FORM>
			</td>
			<td>
				<input type="submit" id="edit" class="edit" value="修改">
				<FORM METHOD="post" id="editform" ACTION="<%=request.getContextPath()%>/member/member.do" style="margin-bottom: 0px;display: none;">
				     <input type="submit" value="完成">
				     <input type="hidden" name="member_id" value="${memberVO.member_id}">
				     <input type="hidden" name="member_lastname" id="lnchecked">
				     <input type="hidden" name="member_firstname" id="fnchecked">
				     <input type="hidden" name="member_address" id="addchecked">
				     <input type="hidden" name="mobilenum" id="mobilechecked">
				     <input type="hidden" name="member_emailaddress" id="emailchecked">
				     <input type="hidden" name="creaditcard" id="cardchecked">
				     <input type="hidden" name="action"	value="update">
			    </FORM>
			</td>
		</tr>
	</c:forEach>
</table>
<%@ include file="page2.file" %>
</body>
<script
  src="https://code.jquery.com/jquery-3.2.1.min.js"
  integrity="sha256-hwg4gsxgFZhOsEEamdOYGBf13FyQuiTwlAQgxVSNgt4="
  crossorigin="anonymous"></script>
<script type="text/javascript">
	$("td").children("#edit").click(function(){
		$(".edit").hide();
		$(this).next("#editform").show().attr("class","editable");
		var content1 = $(this).parents("tr").children("#edit1").text();
		$(this).parents("tr").children("#edit1").html("<input type='text' id='edit1' value='"+content1+"'>");
		var content2 = $(this).parents("tr").children("#edit2").text();
		$(this).parents("tr").children("#edit2").html("<input type='text' id='edit2' value='"+content2+"'>");
		var content3 = $(this).parents("tr").children("#edit3").text();
		$(this).parents("tr").children("#edit3").html("<input type='text' id='edit3' value='"+content3+"'>");
		var content4 = $(this).parents("tr").children("#edit4").text();
		$(this).parents("tr").children("#edit4").html("<input type='text' id='edit4' value='"+content4+"'>");
	});
	$("form").submit(function(){
		var lastname = $(this).parents("tr").children("td").children("#edit1").val();
		var firstname = $(this).parents("tr").children("td").children("#edit2").val();
		var address = $(this).parents("tr").children("td").children("#edit3").val();
		var mobile = $(this).parents("tr").children("td").children("#edit4").val();	
		
		$(this).children("#lnchecked").attr("value",lastname);
		$(this).children("#fnchecked").attr("value",firstname);
		$(this).children("#addchecked").attr("value",address);
		$(this).children("#mobilechecked").attr("value",mobile);
		$(this).children("#emailchecked").attr("value","we3333");
		$(this).children("#cardchecked").attr("value","eresdf");

	})
</script>
</html>