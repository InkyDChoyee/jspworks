<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>상품등록</title>
</head>
<body>
	<h1>상품 등록</h1>
	<form action="/insertproduct.do" method="post">
		<p>상품코드 <input type="text" name="pid"></p>
		<p>상품명 <input type="text" name="pname"></p>
		<p>가격 <input type="text" name="price"></p>
		<p>상품설명 <textarea rows="3" cols="40" name="description"></textarea></p>
		<p><input type="submit" value="등록"></p>
	</form>
</body>
</html>