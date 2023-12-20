<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>KHMarket</title>
</head>
<body>
	<%-- <%@ include file="header.jsp" %> --%>
	<jsp:include page="header.jsp"/>
	<div class="container my-3">
		<h1 class="text-center">웹 마켓에 오신 것을 환영합니다</h1>
		<div class="text-center my-4">
			<img src="resources/images/main.jpg" alt="집이미지" style="width:500px" class="rounded-lg">
		</div>
	</div>
	<jsp:include page="footer.jsp"/>
	<%-- <%@ include file="footer.jsp" %> --%>
</body>
</html>