function checkmember(){
	/* alert("test..."); */
	/* input의 name속성을 변수에 할당 */
	let form = document.member; // 폼 이름 
	let id = form.id.value;
	let pw1 = form.passwd.value;
	let pw2 = form.passwd2.value;
	let name = form.name.value;
	let btnChk = form.btnChk.value;
	
	// 정규 표현식
	// 비밀번호
 	let regexPw1 = /[0-9]+/;     // 숫자
	let regexPw2 = /[a-zA-Z]+/;  // 영문자
	let regexPw3 = /[~!@#$%^&*()]+/;  // 영문자
	 
	// 이름
	let regexName = /^[가-힣]+$/;
	
	
	if(id.length < 4 || id.length > 15){
		alert("아이디는 4 ~ 15자 이내로 작성해주세요");
		id.select();
		return false;
	}else if(pw1.length < 8 || !regexPw1.test(pw1)
			                || !regexPw2.test(pw1)
			                || !regexPw3.test(pw1)){
		alert("비밀번호는 영문자, 숫자, 특수문자(~!@#$%^&*()만 가능)를 포함하여 8자 이상으로 작성해주세요");
		pw1.select();
		return false;
	}else if(pw1 != pw2){  // pw1 과 pw2 문자열이 일치하지 않을 때
		alert("비밀번호를 동일하게 입력해주세요");
		pw2.select();
		return false;
	}else if(!regexName.test(name)){
		alert("이름은 한글로 입력해주세요")
		name.select();
		return false;
	}else if (btnChk == 'N'){
		alert("아이디 중복체크를 눌러주세요");	
		return false;
	}else {
		form.submit();  // 오류가 없으면 폼을 메인 컨트롤러로 전송
	}
}

function checkId(){
	/* alert("test"); */
	// input의 선택자(id)의 값을 사용
	if(t_id.value==""){
		alert("아이디를 입력해주세요");
		t_id.focus();
		return false;
	}
	$.ajax({
		type: "get",
		dataType: "text",
		url: "/member/checkid",
		data: {id: $("#t_id").val()},  // id 속성이 서버로 전달됨
 			success: function(data){
			if(data == "usable"){
				$("#btnChk").attr("value", "Y");
				$("#message").text("사용할 수 있는 ID입니다");
			}else {   // "not_usable"
				$("#btnChk").attr("value", "N");
				$("#message").text("중복된 ID입니다");
			}
		},
		error: function(){
			alert("에러 발생!!");
		}
	});
}
