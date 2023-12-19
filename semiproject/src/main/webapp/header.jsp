<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<!DOCTYPE html>
<script src="https://kit.fontawesome.com/bf7b37fa88.js"
	crossorigin="anonymous"></script>
<link rel="stylesheet" href="../resources/css/header.css">
<div id=container>
	<div id="top">
		<div class="top-top">
			<div class="tt-box">
				<div class="mini-shortcut">
					<!-- 상단 좌측 링크 아이콘 -->
				</div>

				<div class="top-top-menu">
					<!-- 상단 미니메뉴 -->
					<c:choose>
						<c:when test="${empty sessionId }">
							<ul class="ttm-list">
								<li><a href="/joinform.do">JOIN</a></li>
								<li><a href="/loginform.do">LOG-IN</a></li>
								<li><a href="/memberview.do">MY-PAGE</a></li>
								<li><a href="/cart.do">BASKET</a></li>
								<li><a href="#">ORDER-INQUIRY</a></li>
								<li><a href="/boardlist.do">BOARD</a></li>
								<li><input type="text" id="search" size="5"> 🔍</li>
								<!-- 상단 검색창 -->
							</ul>
						</c:when>
						<c:otherwise>
							<ul class="ttm-list">
								<li><a href="#">${sessionName}</a></li>
								<li><a href="/logout.do">LOG-OUT</a></li>
								<li><a href="/memberview.do">MY-PAGE</a></li>
								<li><a href="#">BASKET</a></li>
								<li><a href="#">ORDER-INQUIRY</a></li>
								<li><a href="/boardlist.do">BOARD</a></li>
								<li><input type="text" id="search"> 🔍</li>
								<!-- 상단 검색창 -->
							</ul>
						</c:otherwise>
					</c:choose>
				</div>
			</div>
		</div>

		<div class="top-mid">
			<!-- 메인 로고, 사진 -->
			<a href="/main.jsp"> <img class="logo-pic"
				src="../resources/img/logo/01_logo2.2.jpg" alt="">
			</a>
		</div>

		<div id="top-btm">
			<!-- 사이트 내 각종 메뉴 모음 -->
			<nav class="top-btm-menu">
				<ul class="tbm-list">
					<li class="tbm-list-li"><a href="/productlist.do"><i
							class="fa-solid fa-bars"></i>전체 메뉴</a>
						<ul class="sub_all_menu">
							<ul>
								<a href="/categorylist.do?category=강아지 먹거리">강아지 먹거리</a>
								<a href="/categorylist.do?category=강아지 사료"><li>사료</li></a>
								<a href="/categorylist.do?category=강아지 간식"><li>간식</li></a>
								<a href="/categorylist.do?category=강아지 영양제"><li>영양제</li></a>
							</ul>
							<ul>
								<a href="/categorylist.do?category=강아지 용품">강아지 용품</a>
								<a href="/categorylist.do?category=강아지 집"><li>강아지 집</li></a>
								<a href="/categorylist.do?category=강아지 옷"><li>강아지 옷</li></a>
								<a href="/categorylist.do?category=강아지 장난감"><li>강아지 장난감</li></a>
								<a href="/categorylist.do?category=강아지 밥그릇"><li>강아지 밥그릇</li></a>
								<a href="/categorylist.do?category=강아지 산책용품"><li>강아지
										산책용품</li></a>
							</ul>
							<ul>
								<a href="/categorylist.do?category=고양이 먹거리">고양이 먹거리</a>
								<a href="/categorylist.do?category=고양이 사료"><li>사료</li></a>
								<a href="/categorylist.do?category=고양이 간식"><li>간식</li></a>
								<a href="/categorylist.do?category=고양이 영양제"><li>영양제</li></a>
							</ul>
							<ul>
								<a href="/categorylist.do?category=고양이 용품">고양이 용품</a>
								<a href="/categorylist.do?category=고양이 집"><li>고양이 집</li></a>
								<a href="/categorylist.do?category=고양이 옷"><li>고양이 옷</li></a>
								<a href="/categorylist.do?category=고양이 장난감"><li>고양이 장난감</li></a>
								<a href="/categorylist.do?category=고양이 밥그릇"><li>고양이 밥그릇</li></a>
								<a href="/categorylist.do?category=고양이 방콕용품"><li>고양이
										방콕용품</li></a>
							</ul>
							<ul>
								<a href="/categorylist.do?category=햄스터 먹거리">햄스터 먹거리</a>
								<a href="/categorylist.do?category=햄스터 사료"><li>사료</li></a>
								<a href="/categorylist.do?category=햄스터 간식"><li>간식</li></a>
								<a href="/categorylist.do?category=햄스터 영양제"><li>영양제</li></a>
							</ul>
							<ul>
								<a href="/categorylist.do?category=햄스터 용품">햄스터 용품</a>
								<a href="/categorylist.do?category=햄스터 집"><li>햄스터 집</li></a>
								<a href="/categorylist.do?category=햄스터 장난감"><li>햄스터 장난감</li></a>
								<a href="/categorylist.do?category=햄스터 밥그릇"><li>햄스터 밥그릇</li></a>
								<a href="/categorylist.do?category=햄스터 챗바퀴"><li>햄스터 챗바퀴</li></a>
								<a href="/categorylist.do?category=햄스터 이갈이"><li>햄스터 이갈이</li></a>
							</ul>
							<ul>
								<a href="/categorylist.do?category=이벤트 상품">이벤트 상품</a>
								<a href="/categorylist.do?category=기간 할인 상품"><li>기간 할인
										상품</li></a>
								<a href="/categorylist.do?category=신상품 할인 이벤트"><li>신상품
										할인 이벤트</li></a>
								<a href="/categorylist.do?category=시즌 한정 상품"><li>시즌 한정
										상품</li></a>
							</ul>
						</ul></li>
					<li class="tbm-list-li"><a
						href="/categorylist.do?category=강아지 먹거리">강아지 먹거리</a>
						<ul class="sub_menu">
							<a href="/categorylist.do?category=강아지 사료"><li>사료</li></a>
							<a href="/categorylist.do?category=강아지 간식"><li>간식</li></a>
							<a href="/categorylist.do?category=강아지 영양제"><li>영양제</li></a>
						</ul></li>
					<li class="tbm-list-li"><a
						href="/categorylist.do?category=강아지 용품">강아지 용품</a>
						<ul class="sub_menu">
							<a href="/categorylist.do?category=강아지 집"><li>강아지 집</li></a>
							<a href="/categorylist.do?category=강아지 옷"><li>강아지 옷</li></a>
							<a href="/categorylist.do?category=강아지 장난감"><li>강아지 장난감</li></a>
							<a href="/categorylist.do?category=강아지 밥그릇"><li>강아지 밥그릇</li></a>
							<a href="/categorylist.do?category=강아지 산책용품"><li>강아지
									산책용품</li></a>
						</ul></li>
					<li class="tbm-list-li"><a
						href="/categorylist.do?category=고양이 먹거리">고양이 먹거리</a>
						<ul class="sub_menu">
							<a href="/categorylist.do?category=고양이 사료"><li>사료</li></a>
							<a href="/categorylist.do?category=고양이 간식"><li>간식</li></a>
							<a href="/categorylist.do?category=고양이 영양제"><li>영양제</li></a>
						</ul></li>
					<li class="tbm-list-li"><a
						href="/categorylist.do?category=고양이 용품">고양이 용품</a>
						<ul class="sub_menu">
							<a href="/categorylist.do?category=고양이 집"><li>고양이 집</li></a>
							<a href="/categorylist.do?category=고양이 옷"><li>고양이 옷</li></a>
							<a href="/categorylist.do?category=고양이 장난감"><li>고양이 장난감</li></a>
							<a href="/categorylist.do?category=고양이 밥그릇"><li>고양이 밥그릇</li></a>
							<a href="/categorylist.do?category=고양이 방콕용품"><li>고양이
									방콕용품</li></a>
						</ul></li>
					<li class="tbm-list-li"><a
						href="/categorylist.do?category=햄스터 먹거리">햄스터 먹거리</a>
						<ul class="sub_menu">
							<a href="/categorylist.do?category=햄스터 사료"><li>사료</li></a>
							<a href="/categorylist.do?category=햄스터 간식"><li>간식</li></a>
							<a href="/categorylist.do?category=햄스터 영양제"><li>영양제</li></a>
						</ul></li>
					<li class="tbm-list-li"><a
						href="/categorylist.do?category=햄스터 용품">햄스터 용품</a>
						<ul class="sub_menu">
							<a href="/categorylist.do?category=햄스터 집"><li>햄스터 집</li></a>
							<a href="/categorylist.do?category=햄스터 장난감"><li>햄스터 장난감</li></a>
							<a href="/categorylist.do?category=햄스터 밥그릇"><li>햄스터 밥그릇</li></a>
							<a href="/categorylist.do?category=햄스터 챗바퀴"><li>햄스터 챗바퀴</li></a>
							<a href="/categorylist.do?category=햄스터 이갈이"><li>햄스터 이갈이</li></a>
						</ul></li>
					<li class="tbm-list-li"><a
						href="/categorylist.do?category=이벤트 상품">이벤트 상품</a>
						<ul class="sub_menu">
							<a href="/categorylist.do?category=기간 할인 상품"><li>기간 할인
									상품</li></a>
							<a href="/categorylist.do?category=신상품 할인 이벤트"><li>신상품
									할인 이벤트</li></a>
							<a href="/categorylist.do?category=시즌 한정 상품"><li>시즌 한정
									상품</li></a>
						</ul></li>
				</ul>
			</nav>
		</div>
	</div>