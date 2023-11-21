<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head> 
<meta charset="UTF-8">
<title>실습 예제</title>
</head>
<body>
	<h3>홀수/짝수를 판별하는 프로그램</h3>
	<!-- 표현문 태그로 출력
	     결과는 홀수입니다/짝수입니다 -->
	<% 
		int num = 1;
		String result = "";
		if(num % 2 == 0){
			result = "짝수";
		}else{
			result = "홀수";
		}
	%>
	<p>결과는 <%= result %>입니다</p>
</body>
</html>