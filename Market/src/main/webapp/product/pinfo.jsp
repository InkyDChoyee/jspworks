<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>상품정보</title>
</head>
<body>
	<jsp:include page="../header.jsp" />
	<div class="container my-3">
		<h2>상품정보</h2>
		<div class="row">
			<div class="col-5">
				<img src="../upload/${product.pimage}" alt="" style="width:100%">
			</div>
			<div class="col-7">
				<h3>상품명 : ${product.pname}</h3>
				<p>상품설명 : ${product.description}</p>
				<p><b>상품코드</b> : <span class="badge bg-dark">${product.pid}</span></p>
				<p><b>분류</b> : ${product.category}</p>
				<p><b>재고수</b> : ${product.pstock}</p>
				<p><b>상태</b> : ${product.condition}</p>
				<p><b>가격</b> : <fmt:formatNumber value="${product.price}" pattern="#,##0"/></p>
				<form action="/addcart.do?pid=${product.pid}" name="addform" method="post">
					<!-- 상품 주문 버튼을 클릭하면 폼이 전송되어야 함 -->
					<a href="#" onclick="addToCart()" class="btn btn-success">상품 주문</a>
					<a href="/cart.do" class="btn btn-warning">장바구니</a>
					<a href="/productlist.do" class="btn btn-secondary">상품 목록 &raquo;</a>
				</form>
			</div>
		</div>
	</div>
	<jsp:include page="../footer.jsp" />
	<script>
		let addToCart = function(){
			if(confirm("상품을 주문하시겠습니까?")){ // 확인, 취소
				document.addform.submit();
			}else {
				document.addform.reset();
			}
		}
	
	</script>
</body>
</html>