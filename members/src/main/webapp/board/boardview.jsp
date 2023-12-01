<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>글 상세보기</title>
<link rel="stylesheet" href="resources/css/style.css">
</head>
<body>
	<jsp:include page="../header.jsp"/>
	<div id="container">
		<section id="writeform">
			<h2>글 상세보기</h2>
			<table>
				<tbody>
					<tr>
						<td>
							<input type="text" name="title" value="">
						</td>
					</tr>
					<tr>
						<td>
							<textarea rows="7" cols="100" name="content" 
							placeholder="글내용"></textarea>
						</td>
					</tr>	
					<tr>
						<td>
							<a href="/boardlist.do">
								<button type="button">목록</button>
							</a>
						</td>				
					</tr>
				</tbody>
			</table>
		</section>
	</div>
	<jsp:include page="../footer.jsp"/>
</body>
</html>