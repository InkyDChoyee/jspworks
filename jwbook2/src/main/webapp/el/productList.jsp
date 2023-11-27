<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
	<style>
		#container {width: 80%; margin: 30px auto; text-align: center;}
		
	</style>
</head>
<jsp:useBean id="product" class="el.Product" />
<body>
	<div id="container">
		<h2>상품 목록</h2>
		<hr>
		<form action="selProduct.jsp" method="get">
			<select name="select">
<!-- 				<option value="사과">사과</option>
				<option value="귤">귤</option>
				<option value="토마토">토마토</option> -->
				<%
					/* for(int i = 0; i<product.getProductList().length; i++){
						out.println("<option>" + product.getProductList()[i] + "</option>");
					} */
					
					for(String i : product.getProductList()){
						out.println("<option>" + i + "</option>");
					}
					
				%>
			</select>
			<input type="submit" value="선택">
		</form>
	</div>
</body>
</html>