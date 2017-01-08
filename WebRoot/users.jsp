<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <title>My JSP 'users.jsp' starting page</title>

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
     <c:forEach items="${list}" var="user" varStatus="index">
     <div>${index.count}, ${user.id}, ${user.name}, </div>
     </c:forEach>
     <hr color="red">
     <c:forEach begin="${start}" end="${end}" var="in">
     <c:url value="/ShowUser?current=${in}" var="url1"></c:url>
     	[<a href="${url1}">${in}</a>]-
     </c:forEach>
  </body>
</html>
