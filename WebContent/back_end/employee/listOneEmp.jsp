<%@	page import="com.employee.model.*"%>
<%@ page contentType="text/html; charset=Big5"%>
<%-- 此頁暫練習採用 Script 的寫法取值 --%>

<%
	EmployeeVO empVO = (EmployeeVO) request.getAttribute("empVO"); //EmpServlet.java(Concroller), 存入req的empVO物件
%>

<html>
<head>
<title>員工資料 - listOneEmp.jsp</title>

<style>
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
	width: 600px;
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

</head>
<body bgcolor='white'>

<h4>此頁暫練習採用 Script 的寫法取值:</h4>
<table id="table-1">
	<tr><td>
		 <h3>員工資料 - ListOneEmp.jsp</h3>
		 <h4><a href="select_page.jsp"><img src="images/back1.gif" width="100" height="32" border="0">回首頁</a></h4>
	</td></tr>
</table>

<table>
	<tr>
		<th>員工編號</th>
		<th>員工姓名</th>
		<th>電子信箱</th>
		<th>雇用日期</th>
		<th>手機</th>
		<th>上次登入時間</th>
		<th>在職狀態</th>
	</tr>
	<tr>
		<td><%=empVO.getEmpno()%></td>
		<td><%=empVO.getEmp_name()%></td>
		<td><%=empVO.getEmp_email()%></td>
		<td><%=empVO.getEmp_hiredate()%></td>
		<td><%=empVO.getEmp_phone()%></td>
		<td><%=empVO.getLast_activity()%></td>
		<td><%=empVO.getInserviced()%></td>
	</tr>
</table>

</body>
</html>