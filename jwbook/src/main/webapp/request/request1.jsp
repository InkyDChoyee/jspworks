<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>이름 등록 폼</title>
</head>
<body>
    <!-- action="이동파일경로" method="도메인의 공개방식" -->
	<form action="process.jsp" method="post">
		<label for="uname">이름 </label>
		<input type="text" id="uname" name="uname">
		<input type="submit" value="등록">
	</form>
</body>
</html>