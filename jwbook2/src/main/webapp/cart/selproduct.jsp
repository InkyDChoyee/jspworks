<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>상품 선택</title>
<style>
	#content{width: 800px; margin:0 auto; text-align: center;}
</style>
</head>
<body>
	<%
		request.setCharacterEncoding("utf-8");
		// 로그인 후 세션 발급
		String username = request.getParameter("username");
		session.setAttribute("sessionName", username);
	%>
	<div id="content">
		<h2>상품 선택</h2>
		<hr>
		<p><%=session.getAttribute("sessionName") %> 님이 로그인한 상태입니다
		<form action="addproduct.jsp"method="post" name="form1">
			<select name="productName" id="product" onchange="selproduct()">
				<option>---상품 선택---</option>
				<option value="사과">사과</option>
				<option value="바나나">바나나</option>
				<option value="토마토">토마토</option>
			</select>
		<!-- 	<input type="submit" value="추가"> -->
		</form>		
		<p><button onclick="location.href='cart.jsp'">장바구니</button></p>
		
	</div>
	
	<script>
/* 		let selproduct = function(){
			let product = document.getElementById("product").value;
			alert(product + "가(이) 선택도었습니다"); 
			document.form1.submit();
			
		} */
		
		// 화살표함수 (실행함수)
		let selproduct = () => {  // 익명함수
			let product = document.getElementById("product").value;
			/* alert(product + "가(이) 선택도었습니다"); */
			document.form1.submit();
			
		}
	</script>
</body>
</html>