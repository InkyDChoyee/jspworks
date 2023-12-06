function checkForm() {
	let form = document.member;
	let uid = form.uid.value;
	let passwd = form.passwd.value;
	let passwd2 = form.passwd2.value;
	let name = form.name.value;
	
	let regexPasswd = /^[a-zA-Z0-9~!@#$%^&*()]+$/;
	/*let regexName = /^[0-9]/;*/
	let regexName = /^[a-zA-Z가-힣]+$/;
	
	if(uid.length < 5 || uid.length > 12) {
		alert("아이디는 5~12자까지 입력해주세요");
		form.uid.select();
		return false;
	}else if (!regexPasswd.test(passwd)) {
		alert("비밀번호는 영문자, 숫자, 특수문자 포함 7자 이상 입력해주세요");
		form.passwd.select();
		return false;
	}else if (passwd != passwd2) {
		alert("비밀번호를 동일하게 입력해주세요");
		form.passwd2.select();
		return false;
	}else if (!regexName.test(name)) {
		alert("이름은 숫자로 시작할 수 없습니다");
		form.name.select();
		return false;
	}else {
		form.submit();
	}
}