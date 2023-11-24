<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>유효성 검사</title>
<style type="text/css">
	ul li {list-style: none; margin: 10px;}
</style>
<script type="text/javascript">
	function checkForm(){
		// 자바스크립트 함수 - focus(), select(), submit()
		// 글자의 길이를 정하는 속성 - length
		// 폼이름 선택 - name 속성 선택
		let form = document.loginForm;
		let name = form.uname.value;
		let regex = /^[a-zA-z가-힣]/;  // 문자 1개  
		if(!regex.test(name)){
			alert("이름은 숫자로 시작할 수 없습니다");
			form.name.select();
			return false;
		}else {
			form.submit();   // 자바스크립트에서 폼을 전송하는 메서드
		}
	}
</script>
</head>
<body>
	<form action="test.jsp" method="post" name="loginForm">
		<ul>
			<li>
				<label for="uname"> 이름 </label>
				<input type="text" id="uname" name="uname">
			</li>
			<li>
				<!-- <input type="submit" value="로그인"> -->
				<button type="button" onclick="checkForm()">로그인</button>
			</li>
		</ul>
	</form>
</body>
</body>
</html>