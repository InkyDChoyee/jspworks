<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<script src="https://kit.fontawesome.com/bf7b37fa88.js" crossorigin="anonymous"></script>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>글 상세보기</title>
<link rel="stylesheet" href="resources/css/style.css">
</head>
<body>
	<%-- <c:if test="${empty sessionId }"> --%>
<!-- 		<script type="text/javascript">
			alert("로그인이 필요합니다");
			location.href="/loginform.do";
		</script> -->
	<%-- </c:if> --%>
	<jsp:include page="../header.jsp"/>
	<div id="container">
		<section id="board_view">
			<h2>글 상세보기</h2>
			<table>
				<tbody>
					<tr>
						<td>
							<input type="text" name="title" value="${board.title}" readonly>
						</td>
					</tr>
					<tr class="uploadfile">
						<td>
						<c:choose>
							<c:when test="${not empty board.filename}">
								${board.filename}<a href="filedown.do?filename=${board.filename}">&nbsp; [다운로드] </a>
							</c:when>
							<c:otherwise>
								<c:out value="첨부파일이 없습니다 "/>							
							</c:otherwise>
						</c:choose>
						</td>
					</tr>
					<tr>
						<td>
							<c:if test="${not empty board.filename}">
								<div>
									<img src="../upload/${board.filename}" alt="">
								</div>
							</c:if>
							<br>
							${board.content}
						</td>
					</tr>	
					<tr class="like">
						<td>
						<c:choose>
							<c:when test="${empty sessionId}">
								<p> 좋아요 <i class="fa-solid fa-heart" style="color: #000"></i>
									<span> x ${voteCount}</span> <br>
									<a href="#" onclick="location.href='/loginform.do'">(좋아요는 로그인이 필요합니다)</a>
								</p><br>
							</c:when>
							<c:otherwise>
								<c:choose>
									<c:when test="${sw eq false}">
										<p> 좋아요 
											<a href="/like.do?bno=${board.bno}&id=${sessionId}">
												<i class="fa-solid fa-heart" style="color: #f99"></i>
											</a>
											<span> x ${voteCount}</span><br>
										</p>
									</c:when>
									<c:otherwise>
										<p> 좋아요 
											<a href="/like.do?bno=${board.bno}&id=${sessionId}">
												<i class="fa-regular fa-heart" style="color: #f99"></i>
											</a>
											<span> x ${voteCount}</span><br>
										</p>
									</c:otherwise>
								</c:choose>
							</c:otherwise>
						</c:choose>
						</td>
					</tr>
					<tr class="viewhit">
						<td>
							<p> 작성자: ${board.id} 
						</td>
						<td>
							<p> 작성일: ${board.createDate}
						</td>
						<td>
							<p> 조회수: ${board.hit}
						</td>
					</tr>


		
					
					<tr>
						<td>
							<c:if test="${sessionId eq board.id}">
								<a href="/updateboardform.do?bno=${board.bno}">
									<button type="button" class="ud_btn">수정</button>
								</a>
								<a href="/deleteboard.do?bno=${board.bno}" onclick="return confirm('정말로 삭제하시겠습니까?')">
									<button type="button" class="ud_btn">삭제</button>
								</a>
							</c:if>
							<a href="/boardlist.do">
								<button type="button" class="list_btn">목록</button>
							</a>
						</td>				
					</tr>
				</tbody>
			</table>
			
			<!-- 댓글 영역 -->
			<h3 class="replytitle"> 댓글 <i class="fa-regular fa-comment-dots"></i></h3><br>
			<c:forEach items="${replyList}" var="reply">
			<div class="reply">
				<p>작성자: ${reply.replyer}</p>
				<p>${reply.rcontent}</p>
				<p>
					<span>(작성일: <fmt:formatDate value ="${reply.rdate}" pattern="yyyy-MM-dd HH:mm:ss a"/>)</span>
					<c:if test="${sessionId eq reply.replyer}">
						<a href="/updatereplyform.do?bno=${board.bno}&rno=${reply.rno}">수정</a>
						 | <a href="/deletereply.do?bno=${board.bno}&rno=${reply.rno}" onclick="return confirm('정말로 삭제하시겠습니까?')">삭제</a>
					</c:if>
				</p>
			</div>
			</c:forEach>
			<!-- 댓글 등록 -->
			<form action="/insertreply.do" id="replyform">
				<input type="hidden" name="bno" value="${board.bno}">
				<input type="hidden" name="replyer" value="${sessionId}">
				
				<p>
					<textarea rows="4" cols="50" name="rcontent"
							  placeholder="댓글을 남겨주세요"></textarea>
				</p>
				<c:if test="${not empty sessionId}">
					<button type="submit">등록</button>
				</c:if>
			</form>
			<!-- 로그인한 사용자만 댓글 등록 가능 -->
			<c:if test="${empty sessionId}">
			<div class="replylogin">
				<a href="/loginform.do">
					<i class="fa-solid fa-user"></i>로그인한 사용자만 댓글 등록이 가능합니다
				</a>
			</div>
			</c:if>
		</section>
	</div>
	<jsp:include page="../footer.jsp"/>
</body>
</html>