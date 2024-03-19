

function sample4_execDaumPostcode() {
        new daum.Postcode({
            oncomplete: function(data) {
                var roadAddr = data.roadAddress; // 도로명 주소 변수
                document.getElementById('sample4_postcode').value = data.zonecode;
                document.getElementById("sample4_roadAddress").value = roadAddr;
                var guideTextBox = document.getElementById("guide");
            }
        }).open();
    }

	let name_check = false;
	
	function checkName(f) {
    let user_name = f.user_name.value.trim();
    // 이름이 공백인 경우
    if (user_name === '') {
        let span = document.getElementById("check_name");
        span.innerHTML = "이름을 입력해주세요.";
        name_check = false; // 이메일 유효성 검사 결과를 false로 설정
        return; // 함수 종료
    } else {
        let span = document.getElementById("check_name");
        span.innerHTML = "";
        name_check = true;
    }
}
	

function updatemyinfo(event,f) {

    console.log('실행됨');
    let user_addr = f.postal_code.value.trim()+'/' + f.road_name.value.trim()+'/' + f.detailed_address.value.trim();
    let user_name = f.user_name.value.trim();
	
	checkName(f);
	
    if (name_check) {
    
        f.user_name.value = user_name;
        console.log(user_name);
        f.user_addr.value = user_addr;
        f.action = "address_update";
        f.method = "POST";
        return true; // 기본 동작 중단하지 않음
    }else{
        event.preventDefault(); // 폼 제출 기본 동작 막기
    	return false; 
    }
    
}		
	
	function deleteAccount(form) {
	    // 회원 탈퇴를 확인하고 진행할 수 있는 다이얼로그나 로직을 추가할 수 있습니다.
	    if (confirm("정말로 회원을 탈퇴하시겠습니까?")) {
	        form.action = "delete_account"; // 회원탈퇴를 처리할 컨트롤러나 서블릿의 엔드포인트를 지정합니다.
	        form.method = "POST";
	        form.submit();
	    }
	}
	