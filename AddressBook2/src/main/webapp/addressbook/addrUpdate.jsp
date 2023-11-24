<%@page import="addressbook.AddrBook"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>주소 수정</title>
<link rel="stylesheet" href="../resources/css/style.css">

</head>
<body>
	<jsp:useBean id="abDAO" class="addressbook.AddrBookDAO" scope="application"/>
	<% 
		// bnum으로 찾은 객체를 생성
		int bnum = Integer.parseInt(request.getParameter("bnum"));
		AddrBook addrBook = abDAO.getAddrBook(bnum);
	%>
	<div id="container">
		<h2>주소 수정</h2>
		<hr>
		<form action="addrUpdateProcess.jsp" method="post">
			<table id="tbl_view">
				<tr>
					<td class="sub">번호</td>
					<td><input type="text" name="bnum" value=<%= addrBook.getBnum() %> readonly></td>
				</tr>
				<tr>
					<td class="sub">이름</td>
					<td><input type="text" name="username" value=<%= addrBook.getUsername() %>></td>
				</tr>
				<tr>
					<td class="sub">전화번호</td>
					<td><input type="text" name="tel" value=<%= addrBook.getTel() %>></td>
				</tr>
				<tr>
					<td class="sub">이메일</td>
					<td><input type="text" name="email" value=<%= addrBook.getEmail() %>></td>
				</tr>
				<tr>
					<td class="sub">성별</td>
					<td>
					<% if(addrBook.getGender().equals("남")){ %>
						<input type="radio" name="gender" value="남" checked>남
						<input type="radio" name="gender" value="여">여
					<% } else { %>
						<input type="radio" name="gender" value="남">남
						<input type="radio" name="gender" value="여" checked>여	
					<% } %>	
					</td>
				</tr>
				<tr>
					<td colspan="2">
						<input type="submit" value="저장">
						<input type="reset" value="리셋">
						<a href="addrList.jsp">
							<button type="button">목록</button>
						</a>
					</td>
				</tr>
			</table>
		</form>
	</div>
</body>
</html>