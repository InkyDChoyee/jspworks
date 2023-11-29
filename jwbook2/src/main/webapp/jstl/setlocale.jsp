<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<p> === 기본 로케일 === </p>
		<fmt:setLocale value="ko"/>
		<fmt:setBundle basename="com.bundle.message_ko"/>
	<p>제목: <fmt:message key="title"/>
	<p>이름: <fmt:message key="username"/>
	
		<p> === 영문 로케일 === </p>
		<fmt:setLocale value="en"/>
		<fmt:setBundle basename="com.bundle.message_en"/>
	<p>제목: <fmt:message key="title"/>
	<p>이름: <fmt:message key="username"/>
	
</body>
</html>