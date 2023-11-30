<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>회원 정보</title>
<link rel="stylesheet" href="resources/css/style.css">
</head>
<body>
	<jsp:include page="../header.jsp"/>
	<div id="container">
		<section id="memberview">
			<h2>회원 정보</h2>
			<table>
				<tbody>
					<tr>
						<td><label for="id">아이디</label></td>
						<!-- dispatcher가 보내준 모델을 받음 -->
						<td>${member.id}</td>
					</tr>
					<tr>
						<td><label for="passwd">비밀번호</label></td>
						<td>${member.passwd}</td>
					</tr>
					<tr>
						<td><label for="name">이름</label></td>
						<td>${member.name}</td>
					</tr>
					<tr>
						<td><label for="email">이메일</label></td>
						<td>${member.email}</td>
					</tr>
					<tr>
						<td><label for="gender">성별</label></td>
						<td>${member.gender}</td>
					</tr>
					<tr>
						<td colspan="2"><fmt:formatDate value ="${member.joinDate}" pattern="yyyy-MM-dd HH:mm:ss a"/></td>
					</tr>
					<tr>
						<td>
							<a href="/memberlist.do"><button>목록</button></a>
						</td>
					</tr>
				
				</tbody>
			</table>
		</section>
	</div>
	<jsp:include page="../footer.jsp"/>
</body>
</html>