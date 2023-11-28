<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>점수 입력 폼</title>
<script>
	function check(){
		/* alert("테스트"); */
		let form = document.form1;
		let score = form.score.value;
		
		// 값이 비었을 때(입력되지 않았을 때), 숫자가 아닌 문자가 입력되었을 때
		if(score == "" || isNaN(score)){
			alert("숫자를 입력해주세요");
			score.focus();
			return false;
		}
		form.submit();
	}
</script>
</head>
<body>	
	<h3>점수를 입력해주세요</h3>
	<form action="core02.jsp" method="post" name="form1">
		<p>점수 <input type="text" name="score">
		       <input type="button" value="전송" onclick="check()">
		</p>
		
	</form>
</body>
</html>