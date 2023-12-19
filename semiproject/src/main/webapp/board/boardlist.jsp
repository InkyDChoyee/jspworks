<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>게시판 목록</title>
<link rel="stylesheet" href="../resources/css/boardlist.css">
</head>
<body>
	<jsp:include page="../header.jsp" />
	<div id="container">
		<div id="mid">
			<div class="Board">
				<table id="board_tb">
					<tbody>
						<div class="boardkind">
							<div class="notice-board">
								<h3 class="title-notice">
									<a href="/noticeboardlist.do">Notice / Event</a>
								</h3>
								<ul class="notice-list">
									<li><a href="#">Notice 1</a></li>
									<li><a href="#">Notice 2</a></li>
									<li><a href="#">Event 2</a></li>
									<li><a href="#">Event 1</a></li>
								</ul>
							</div>

							<div class="review-board">
								<h3 class="title-review">
									<a href="/reviewboardlist.do">Review Board</a>
								</h3>
								<ul class="review-list">
									<li><a href="#">Review 4</a></li>
									<li><a href="#">Review 3</a></li>
									<li><a href="#">Review 2</a></li>
									<li><a href="#">Review 1</a></li>
								</ul>
							</div>

							<div class="QnA-board">
								<h3 class="title-QnA">
									<a href="/qaboardlist.do">Q/A Board</a>
								</h3>
								<ul class="QnA-list">
									<li><a href="#">Q/A 4</a></li>
									<li><a href="#">Q/A 3</a></li>
									<li><a href="#">Q/A 2</a></li>
									<li><a href="#">Q/A 1</a></li>
								</ul>
							</div>

						</div>
					</tbody>
				</table>
			</div>
		</div>
	</div>
	<jsp:include page="../footer.jsp" />
</body>
</html>