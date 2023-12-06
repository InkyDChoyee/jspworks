<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>회원 가입 폼</title>
<link rel="stylesheet" href="resources/css/style.css">
<script src="../resources/js/jquery-3.7.1.js"></script>
<script src="../resources/js/validation.js"></script>
</head>
<body>
	<jsp:include page="../header.jsp"/>
	<div id="container">
		<section id="join">
			<h2>회원 가입</h2>
			<form action="insertmember.do" method="post" name="member">
				<fieldset>
					<ul>
						<li>
							<label for="t_id">아이디</label>
							<input type="text" id="t_id" name="id" placeholder="4~15자 이내">
							<button type="button" id="btnChk" value="N" class="btn_check" onclick="checkId()">ID 중복</button>
							<p id="message"></p>
						</li>
						<li>
							<label for="passwd">비밀번호</label>
							<input type="password" id="passwd" name="passwd" placeholder="영문자, 숫자, 특수문자 포함 8자 이상">
						</li>		
						<li>
							<label for="passwd2">비밀번호 확인</label>
							<input type="password" id="passwd2" name="passwd2" placeholder="비밀번호를 동일하게 입력하세요">
						</li>	
						<li>
							<label for="name">이름</label>
							<input type="text" id="name" name="name" placeholder="한글로 입력해주세요">
						</li>
						<li>
							<label for="email">이메일</label>
							<input type="email" id="email" name="email">
						</li>		
						<li>
							<label for="gender">성별</label>
							<input type="radio" id="gender" name="gender" value="남" checked>남
							<input type="radio" id="gender" name="gender" value="여">여
						</li>			
					</ul>
				</fieldset>
				<div>
					<button type="button" onclick="checkmember()">가입</button>
					<button type="reset">취소</button>
				</div>

			</form>
		</section>
	</div>
	<jsp:include page="../footer.jsp"/>
</body>
</html>