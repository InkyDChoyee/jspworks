/* 상품 등록 유효성 검사 */
/* 
	상품코드 = p와 숫자 조합, 5~12자로 입력
	상품명 = 4~20자 입력
	가격 및 재고수 = 숫자만 입력

 */
const checkProduct = function(){
	/*alert("테스트...");	*/
	let pid = document.getElementById("pid");
	let pname = document.getElementById("pname");
	let price = document.getElementById("price");
	let pstock= document.getElementById("pstock");

	// 정규 표현식
	let regexPid = /^P[0-9]{4,11}/

	if(!regexPid.test(pid.value)){  // 상품코드와 정규표현식이 일치하지 않으면
		alert("상품 코드는 P를 포함하여 숫자만 사용하여 4~11자로 입력해주세요");
		pid.select();
		return false;
	}else if(pname.value.length < 4 || pname.value.length > 20) {
		alert("상품명은 4~20자로 입력해주세요");
		pname.select();
		return false;
	}else if(price.value.length == 0 || isNaN(price.value) || price.value < 0) {
		alert("가격은 숫자만 입력해주세요");
		price.select();
		return false;
	}else if(pstock.value.length == 0 || isNaN(pstock.value) || pstock.value < 0) {
		alert("재고는 숫자만 입력해주세요");
		pstock.select();
		return false;
	}else {
		document.newProduct.submit();
	}

}