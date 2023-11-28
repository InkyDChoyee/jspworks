<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>구구단</title>
</head>
<body>
	<c:set var="dan" value="6" />
		<c:forEach var="i" begin="1" end="9">
			${dan} × ${i} = ${dan*i} <br>
		</c:forEach>
</body>
</html>