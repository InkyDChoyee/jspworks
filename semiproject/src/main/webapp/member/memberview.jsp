<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>회원 목록</title>
<link rel="stylesheet" href="../resources/css/mypage.css">
</head>
<body>
 <c:set var="member" value="${request.member}" />
	<jsp:include page="../header.jsp" />
	<jsp:include page="../navbar.jsp"/>
	<div id="mid">
            <h3>ORDER</h3>
            <div class="mid-top">
                <div class="member_info">
                    <img src="#" alt="">
                    <p>${member.name}님! 저희 쇼핑몰을 이용해 주셔서 감사합니다. </p>
                </div>
            </div>
            <div class="mid-mid">
                <div class="basic_info">
                    <div class="basic_info_span">
                        <span class="span_title">기본 정보</span>
                        <span><span class="required">*</span> 필수입력사항</span>
                    </div>
                    <form action="/updatemember.do">
                        <table>
                            <tbody>
                                <tr>
                                    <th>아이디 <span class="required">*</span></th>
                                    <td><input type="text" size="10" readonly placeholder="${member.id }"></td>
                                </tr>
                                <tr>
                                    <th>비밀번호 <span class="required">*</span></th>
                                    <td><input type="password" size="10"></td>
                                </tr>
                                <tr>
                                    <th>비밀번호 확인 <span class="required">*</span></th>
                                    <td><input type="password" size="10"></td>
                                </tr>
                                <tr>
                                    <th>이름 <span class="required">*</span></th>
                                    <td><input type="text" size="10" readonly placeholder="${member.name }"></td>
                                </tr>
                                <tr>
                                    <th>주소 <span class="required">*</span></th>
                                    <td>
                                        <div class="order_address">
                                            <input type="text" size="10"><button>우편번호 ></button> <br>
                                            <input type="text" size="30" placeholder="${member.address }"> 기본주소 <br>
                                            <input type="text" size="30" placeholder="${member.detailaddress}"> 나머지주소
                                        </div>
                                    </td>
                                </tr>
                                <tr>
                                    <th>휴대전화 <span class="required">*</span></th>
                                    <td>
                                    	<input type="text" readonly placeholder="${member.phone}" size="10">
                                        <select name="mobilephone1" id="mobilephone1">
                                            <option value="02">010</option>
                                            <option value="031">011</option>
                                        <option value="032">019</option>
                                        </select> -
                                        <input type="text" size="5" maxlength=4> -
                                        <input type="text" size="5" maxlength=4>
                                    </td>
                                </tr>
                                <tr>
                                    <th>SMS 수신여부 <span class="required">*</span></th>
                                    <td>
                                        <input type="radio" name="receive" value="agree"> 수신함
                                        <input type="radio" name="receive" value="disagree"> 수신안함 <br>
                                        <span class="sms_info">쇼핑몰에서 제공하는 유익한 이벤트 소식을 SMS로 받으실 수 있습니다</span>
                                    </td>
                                </tr>
                                <tr>
                                    <th>이메일 <span class="required">*</span></th>
                                    <td>
                                        <input type="text" placeholder="${member.email}">
                                    </td>
                                </tr>
                            </tbody>
                        </table>
                        <div class="basic_info_btn">
                            <div class="info_edit">
                            	<a href="/updatemember.do?id=${member.id}">
                                	<button type="button">회원정보 수정</button>
                                </a> 
                                <button type="reset">취소</button>
                                
                            </div>
                            <div class="member_quit">
	                            <a href="/deletemember.do?id=${member.id}">
									<button type="button" onclick="return confirm('삭제하시겠습니까?')">회원탈퇴</button>
								</a>
                            </div>
                        </div>
                    </form>
                </div>
            </div>
        </div>
	<jsp:include page="../footer.jsp" />
</body>
</html>