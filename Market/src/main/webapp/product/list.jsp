<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>'
<meta charset="UTF-8">
<title>상품 목록</title>
</head>
<body>
	<h1>상품 목록</h1>
	<table>
		<tr>
			<td>상품코드</td>
			<td>상품명</td>
			<td>가격</td>
			<td>등록일</td>
		</tr>
		<c:forEach items="${products}" var="product">
			<tr>
				<td>${product.pid}</td>
				<td>${product.pname}</td>
				<td>${product.price}</td>
				<td>${product.regDate}</td>
			</tr>
		
		</c:forEach>
	</table>
</body>
</html>