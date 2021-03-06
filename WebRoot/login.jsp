<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<title>My JSP 'login.jsp' starting page</title>

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

	<c:url value="/UserServlet" var="user"></c:url>
	<c:choose>
		<c:when test="${empty sessionScope.user}">
			<form action='${user}?cmd=login' method="post">
				<table>
					<tr>
						<th colspan="2">Please Login</th>
					</tr>
					<tr>
						<td>NAME</td>
						<td><input type="text" name="name" /></td>
					</tr>
					<tr>
						<td>PassWord</td>
						<td><input type="text" name="pwd"></td>
					</tr>
					<tr>
						<td><input type="submit"></td>
					</tr>
				</table>
			</form>
		</c:when>
		<c:otherwise>
			<table>
				<tr>
					<th>SessionId</th>
					<th>UserId</th>
					<th>UserName</th>
					<th>UserPwd</th>
					<th>Time</th>
					<th>Delete</th>
				</tr>
				<c:forEach items="${UserList}" var="list">
					<tr>
						<th> ${list.SessionId} </th>
						<th> ${list.UserId} </th>
						<th> ${list.UserName} </th>
						<th> ${list.UserPwd} </th>
						<th> ${list.Time} </th>
						<c:url value="/ShowSession?sessionid=${list.SessionId}" var="delete"></c:url>
						<th><a href='${delete}'> Delete </a></th>
					</tr>
				</c:forEach>
			</table>
		</c:otherwise>
	</c:choose>
</body>
</html>
