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
							<c:choose>
								<c:when test="${empty b.modifyDate}">
									<td>${b.bno}</td>
									<!-- title를 클릭하면 상세보기로 이동함 -->
									<td><a href="/boardview.do?bno=${b.bno}">${b.title}</a></td>
									<!-- 수정일이 있을 경우 수정된 날짜로 값이 표시되고, 없을경우 작성이로 표시 -->
									<td><fmt:formatDate value ="${b.createDate}" pattern="yyyy-MM-dd HH:mm:ss a"/></td>
									<td>${b.hit}</td>
									<td>${b.id}</td>
								</c:when>
								<c:otherwise>
									<td>${b.bno}</td>
									<td><a href="/boardview.do?bno=${b.bno}">${b.title}</a></td>
									<td><small>(수정일)</small> <fmt:formatDate value ="${b.modifyDate}" pattern="yyyy-MM-dd HH:mm:ss a"/></td>
									<td>${b.hit}</td>
									<td>${b.id}</td>
								</c:otherwise>
							</c:choose>
						</tr>
					</c:forEach>
				</tbody>
			</table>
			
			<!-- 페이지 처리 영역 -->
			<div class="pagination">
				<!-- 페이지 리스트 -->
				<c:forEach var= "i" begin="1" end="${endPage}">
				<c:if test="${page == i}">
					<a href="/boardlist.do?pageNum=${i}"><b>${i}</b></a>
				</c:if>
				<c:if test="${page != i}">
					<a href="/boardlist.do?pageNum=${i}">${i}</a>
				</c:if>
				</c:forEach>
			</div>
			
			<!-- 글쓰기 버튼 -->
			<div>
				<a href="/writeform.do"><button type="button">글쓰기</button></a>
			</div>
		</section>
	</div>
	<jsp:include page="../footer.jsp"/>
</body>
</html>