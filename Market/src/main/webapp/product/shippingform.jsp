<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>배송 정보</title>
<script src="../resources/js/validation.js"></script>
</head>
<body>
	<jsp:include page="../header.jsp" />
	<div class="container my-3">
		<h2>배송 정보</h2>
		<div class="row">
			<form action="/shippinginfo.do"  name="newProduct" method="post">
				<div class="form-group row my-3">
					<label class="col-2">성명</label>
					<div class="col-3">
					<p>성명 <input type="text" id="sname" name="sname" class="form-control"></p>
					</div>
				</div>
				<div class="form-group row my-3">
					<label class="col-2">배송일</label>
					<div class="col-3">
					<p>배송일 <input type="text" id="shippingdate" name="shippingdate" class="form-control"></p>
					</div>
				</div>
				<div class="form-group row my-3">
					<label class="col-2">우편번호</label>
					<div class="col-3">
					<p>우편번호 <input type="text" id="zipcode" name="zipcode" class="form-control"></p>
					</div>
				</div>
				<div class="form-group row my-3">
					<label class="col-2">주소</label>
					<div class="col-3">
					<p>주소 <input type="text" id="address" name="address" class="form-control"></p>
					</div>
				</div>
				
				<div class="form-group row my-3">
					<div class="col-3">
						<p><input onclick="submit" type="button" value="등록" class="btn btn-success"></p>
					</div>
				</div>
			</form>
		</div>
	</div>
	<jsp:include page="../footer.jsp" />
</body>
</html>