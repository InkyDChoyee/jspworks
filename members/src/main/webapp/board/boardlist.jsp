<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>게시글 목록</title>
<link rel="stylesheet" href="resources/css/style.css">
</head>
<body>
	<jsp:include page="../header.jsp"/>
	<div id="container">
		<section id="boardlist">
			<h2>게시글 목록</h2>
			<table>
				<thead>
					<tr>
						<th>글번호</th>
						<th>글제목</th>
						<th>작성일</th>
						<th>조회수</th>
						<th>작성자</th>
					</tr>
				</thead>
				<tbody>
					<c:forEach items="${boardList}" var="b">
						<tr>
						<!-- b.bno - b.getBno()와 같음 -->
							<td>${b.bno}</td>
							<!-- title를 클릭하면 상세보기로 이동함 -->
							<td><a href="/boardview.do?id=${b.title}">${b.title}</a></td>
							<td><fmt:formatDate value ="${b.createDate}" pattern="yyyy-MM-dd HH:mm:ss a"/></td>
							<td>${b.hit}</td>
							<td>${b.id}</td>
						</tr>
					</c:forEach>
				</tbody>
			</table>
		</section>
	</div>
	<jsp:include page="../footer.jsp"/>
</body>
</html>