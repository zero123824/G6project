<%@	page import="com.employee.model.*"%>
<%@ page contentType="text/html; charset=Big5"%>
<%-- �����Ƚm�߱ĥ� Script ���g�k���� --%>

<%
	EmployeeVO empVO = (EmployeeVO) request.getAttribute("empVO"); //EmpServlet.java(Concroller), �s�Jreq��empVO����
%>

<html>
<head>
<title>���u��� - listOneEmp.jsp</title>

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

<h4>�����Ƚm�߱ĥ� Script ���g�k����:</h4>
<table id="table-1">
	<tr><td>
		 <h3>���u��� - ListOneEmp.jsp</h3>
		 <h4><a href="select_page.jsp"><img src="images/back1.gif" width="100" height="32" border="0">�^����</a></h4>
	</td></tr>
</table>

<table>
	<tr>
		<th>���u�s��</th>
		<th>���u�m�W</th>
		<th>�q�l�H�c</th>
		<th>���Τ��</th>
		<th>���</th>
		<th>�W���n�J�ɶ�</th>
		<th>�b¾���A</th>
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