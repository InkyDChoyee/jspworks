<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<link rel="stylesheet" href="../resources/css/cart.css">
<body>
	<jsp:include page="../header.jsp" />
	<jsp:include page="../navbar.jsp" />
	<div id="mid">
		<h3>CART</h3>
		<div class="mid-top">
			<div class="member_order_info">
				<table>
					<tbody>
						<tr>
							<td rowspan="2" class="rowspan2">혜택 정보</td>
							<td class="first_td">${member.name}님,환영합니다</td>
						</tr>
						<tr class="second_tr">
							<td class="second_td"><a href="#">사용 가능 쿠폰 : 1개</a></td>
						</tr>
					</tbody>
				</table>
			</div>
			<div class="prod_order_confirm_table">
				<div class="order_change_decrip">! 상품의 옵션 및 수량 변경은 상품상세 또는
					장바구니에서 가능합니다.</div>
				<table>
					<tbody>
						<tr class="tr1">
							<td colspan="10">상품 주문내역</td>
						</tr>
						<tr class="table_td_head">
							<td class="td1"><input type="checkbox"></td>
							<td class="td2">이미지</td>
							<td class="td3">상품정보</td>
							<td class="td4">판매가</td>
							<td class="td5">수량</td>
							<td class="td6">쿠폰여부</td>
							<td class="td7">배송구분</td>
							<td class="td8">배송비</td>
							<td class="td9">합계</td>
							<td class="td0">선택</td>
						</tr>
						<c:choose>
							<c:when test="${empty cartlist}">
								<tr>
									<td colspan="10"><span class="order_config_small_text">
											담은 상품이 없습니다 </span></td>
								</tr>
							</c:when>
							<c:otherwise>
								<c:forEach items="${cartlist}" var="cart">
									<tr class="tr2">
										<td class="td1"><input type="checkbox"></td>
										<td><a href="#"><img
												src="../fileupload/${cart.pfilename}" alt=""></a></td>
										<td>${cart.name}
											<p class="new_icon">NEW</p>
										</td>
										<td><span class="product_price">${cart.price}</span>원</td>
										<td>
											<div class="num">
												<span class="count"> <a class="minus"
													onclick="decrease()"> - </a> <span class="count_num">1</span>
													<a class="plus" onclick="increase()"> + </a>
												</span>
											</div>
										</td>
										<td>No</td>
										<td>기본배송</td>
										<td class="td8">3,000원</td>
										<td class="mid_sub_price"></td>
										<td class="td0">
											<p>
												<button>주문하기 ></button>
											</p>
											<p>
												<button>상품삭제 X</button>
											</p>
										</td>
									</tr>
								</c:forEach>
							</c:otherwise>
						</c:choose>
						<c:if test="${!empty cartlist }">
							<tr class="tr3">
								<td colspan="10"><span class="order_config_small_text">상품구매금액
										<span class="mid_sub_price">${product.price}</span>원 + 배송비 3,000원 = 합계 : </span><span
									class="order_config_big_text"></span> <span
									class="order_config_small_text"><span class="mid_sub_price">원</span></span></td>
							</tr>
						</c:if>
					</tbody>
				</table>
				<div class="order_change_decrip">! 상품의 옵션 및 수량 변경은 상품상세 또는
					장바구니에서 가능합니다.</div>
				<div class="go_orders_btn">
					<div class="all_orders">
						<a href="/orders.do"><button type="button">모든상품 주문하기</button></a>
						<%-- <a href="/orders.do?pno=${product.pno}"><button type="button">모든상품 주문하기</button></a> --%>
					</div>
					<div class="select_order_cancel">
						<span>선택한 상품 : </span>
						<button class="select_order_delete">X 삭제하기</button>
						<a href="/orders.do"><button class="order_btn">주문하기 ></button></a>
						<button class="delete_all_cart">장바구니비우기</button>
					</div>
				</div>
			</div>
			<div class="mid-btm">
				<div class="expected_order">
					<div class="expected_order_price">
						<span>결제예정 금액</span>
					</div>
					<table>
						<thead>
							<tr>
								<th>총 주문 금액<span>(배송비포함)</span></th>
								<th>총 할인 금액</th>
								<th>총 결제예정 금액</th>
							</tr>
						</thead>
						<tbody>
							<tr>
								<td><span class="mid_sub_price"> </span> 원</td>
								<td>- <span>0 </span> 원
								</td>
								<td>= <span class="mid_sub_price"> </span> 원
								</td>
							</tr>
						</tbody>
					</table>
				</div>
				<div class="order_method">
					<div class="info_desk_box">
						<div class="using_info">
							<table>
								<tbody>
									<tr>
										<th>이용안내</th>
									</tr>
									<tr>
										<td>
											<div>장바구니 이용안내</div>
											<div>무이자할부 이용안내</div>
										</td>
									</tr>
								</tbody>
							</table>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
	<jsp:include page="../footer.jsp" />
	<script>
		function updateTotalPrice() {
		    var productPrice = parseInt(document.querySelector(".product_price").innerText);
		    var currentResult = parseInt(document.querySelector('.count_num').innerText);
		    var totalPrice = currentResult * productPrice + 3000;
	
	        var midSubPriceElements = document.querySelectorAll('.mid_sub_price');
	        midSubPriceElements.forEach(function(element) {
	            element.innerText = totalPrice;
	        });

	        document.querySelector('.order_config_big_text').innerText = totalPrice;
		}
	
		function increase() {
		    var currentResult = parseInt(document.querySelector('.count_num').innerText);
		    document.querySelector('.count_num').innerText = currentResult + 1;
			updateTotalPrice();
		}
	
		function decrease() {
		    var currentResult = parseInt(document.querySelector('.count_num').innerText);
		    if (currentResult > 1) {
		        document.querySelector('.count_num').innerText = currentResult - 1;
				updateTotalPrice();
		    }
		}
		updateTotalPrice();
	</script>
</body>
</html>