<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>상품등록</title>
</head>
<body>
	<jsp:include page="../header.jsp" />
	<div class="container my-3">
		<h2>상품 등록</h2>
		<div class="row">
			<form action="/insertproduct.do" method="post" enctype="multipart/form-data">
				<div class="form-group row my-3">
					<label class="col-2">상품 코드</label>
					<div class="col-3">
					<p>상품코드 <input type="text" name="pid" class="form-control"></p>
					</div>
				</div>
				<div class="form-group row my-3">
					<label class="col-2">상품명</label>
					<div class="col-3">
					<p>상품명 <input type="text" name="pname" class="form-control"></p>
					</div>
				</div>
				<div class="form-group row my-3">
					<label class="col-2">가격</label>
					<div class="col-3">
					<p>가격 <input type="text" name="price" class="form-control"></p>
					</div>
				</div>
				<div class="form-group row my-3">
					<label class="col-2">상품 설명</label>
					<div class="col-4">
					<p>상품설명 <textarea rows="3" cols="40" name="description" class="form-control"></textarea></p>
					</div>
				</div>
				<div class="form-group row my-3">
					<label class="col-2">카테고리</label>
					<div class="col-3">
					<p>카테고리 <input type="text" name="category" class="form-control"></p>
					</div>
				</div>
				<div class="form-group row my-3">
					<label class="col-2">재고 수</label>
					<div class="col-3">
					<p>재고 수 <input type="text" name="pstock" class="form-control"></p>
					</div>
				</div>
				<div class="form-group row my-3">
					<label class="col-2">상태</label>
					<div class="col-3">
					<p>
						<label><input type="radio" name="condition" value="New" checked>신상품</label>
						<label><input type="radio" name="condition" value="Old">중고품</label>
					</p>
					</div>
				</div>
				<div class="form-group row my-3">
					<label class="col-2">상품 이미지</label>
					<div class="col-3">
					<p>상품 이미지 <input type="file" name="pimage" class="form-control"></p>
					</div>
				</div>
				<div class="form-group row my-3">
					<div class="col-3">
						<p><input type="submit" value="등록" class="btn btn-success"></p>
					</div>
				</div>
				
			
			
			
			
			</form>
		</div>
	</div>
	<jsp:include page="../footer.jsp" />
</body>
</html>