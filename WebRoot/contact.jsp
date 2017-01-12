<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>
<head>
<title>My JSP 'contact.jsp' starting page</title>

<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
<meta http-equiv="description" content="This is my page">
<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->

</head>

<body>
<div style="padding:20px">
	<h3>Here is ${user.name}'s Contact info table</h3>
	<form method="post">
		<table style="border: 1px,black;padding: 5px">
			<tr>
				<th>Name</th>
				<th>Sex</th>
				<th>Tel</th>
				<th>Address</th>
				<th colspan="2">Action</th>
			</tr>
			<c:forEach items="${list}" var="contact">
				<tr>
					<td>${contact.name}</td>
					<td>${contact.sex==0?"Male":"Female"}</td>
					<td>${contact.tel}</td>
					<td>${contact.addr}</td>
					<c:url value="/ContactServlet?cmd=delete&UID=${contact.id}" var="delete"></c:url>
					<td><a href=${delete }>Delete</a></td>
					<c:url value="/ContactServlet?cmd=update&UID=${contact.id}" var="update"></c:url>
					<td><a href=${update }>Update</a></td>
				</tr>
			</c:forEach>
			<tr>
			<td></td>
			</tr>
		</table>
	</form>
	<hr>
	<c:url value="/ShowSession" var="login"></c:url>
	Back to <a href=${login}>LOGIN</a>
	<br>
	</div>
</body>
</html>
