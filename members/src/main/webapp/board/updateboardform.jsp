<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>게시글 수정</title>
<link rel="stylesheet" href="resources/css/style.css">
</head>
<body>
	<jsp:include page="../header.jsp"/>
	<div id="container">
		<section id="writeform">
			<h2>게시글 수정</h2>
			<form action="/updateboard.do" method="post" enctype="multipart/form-data">
				<!-- type:hidden = ui를 만들지 않고 데이터만 숨겨서 보낼 때 사용 -->
				<input type="hidden" name="bno" value="${board.bno}" >
				<table>
					<tbody>
						<tr>
							<td>
								<input type="text" name="title" value="${board.title}" >
							</td>
						</tr>
						<tr>
							<td>
								<textarea rows="7" cols="100" name="content">${board.content}</textarea>
							</td>
						</tr>	
						<tr>
							<td><input type="file" name="filename" value="${board.filename}"></td>
						</tr>
						
						<tr>
							<td>
								<button type="submit" class="ud_btn">저장</button>
								<a href="/boardlist.do">
									<button type="button" class="list_btn">취소</button>
								</a>
							</td>				
						</tr>
					</tbody>
				</table>
			</form>
		</section>
	</div>
	<jsp:include page="../footer.jsp"/>
</body>
</html>