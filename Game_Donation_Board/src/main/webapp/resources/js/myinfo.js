//실시간으로 이메일 형식 유효성 검사
function validateEmail(input) {
    const email = input.value;
    const emailError = document.getElementById('email-error');
    const re = /\S+@\S+\.\S+/;
    if (!re.test(email)) {
        emailError.textContent = '올바른 이메일 주소를 입력하세요.';
    } else {
        emailError.textContent = '';
    }
}

// 전체 폼 데이터 검증, 이메일 주소 형식이 올바르지 않으면 DB로 전송 막음.
function validateForm() {
    const emailInput = document.getElementById('email');
    const emailError = document.getElementById('email-error');

    // 이메일 주소 유효성 검사
    const re = /\S+@\S+\.\S+/;
    if (!re.test(emailInput.value)) {
        emailError.textContent = '올바른 이메일 주소를 입력하세요.';
        return false; // 폼 제출 방지
    } else {
        emailError.textContent = ''; // 에러 메시지 초기화
        return true; // 폼 제출 허용
    }
}

function sample4_execDaumPostcode() {
        new daum.Postcode({
            oncomplete: function(data) {
             
                var roadAddr = data.roadAddress; // 도로명 주소 변수
              /*   var extraRoadAddr = ''; */ // 참고 항목 변수

       
                if(data.bname !== '' && /[동|로|가]$/g.test(data.bname)){
                  /*   extraRoadAddr += data.bname; */
                }
             
                if(data.buildingName !== '' && data.apartment === 'Y'){
                  /*  extraRoadAddr += (extraRoadAddr !== '' ? ', ' + data.buildingName : data.buildingName); */
                }
             
                /* if(extraRoadAddr !== ''){
                    extraRoadAddr = ' (' + extraRoadAddr + ')';
                } */

                document.getElementById('sample4_postcode').value = data.zonecode;
                document.getElementById("sample4_roadAddress").value = roadAddr;
              /*   document.getElementById("sample4_jibunAddress").value = data.jibunAddress; */
                
                if(roadAddr !== ''){
              /*       document.getElementById("sample4_extraAddress").value = extraRoadAddr; */
                } else {
                    document.getElementById("sample4_extraAddress").value = '';
                }

                var guideTextBox = document.getElementById("guide");

                if(data.autoRoadAddress) {
                /*     var expRoadAddr = data.autoRoadAddress + extraRoadAddr; */
                  /*   guideTextBox.innerHTML = '(예상 도로명 주소 : ' + expRoadAddr + ')'; */
                  /*   guideTextBox.style.display = 'block'; */

                } else {
                   /*  guideTextBox.innerHTML = ''; */
                   /*  guideTextBox.style.display = 'none'; */
                }
            }
        }).open();
    }




	function addRess(f) {

           
		let user_addr = f.postal_code.value.trim() +  f.road_name.value.trim() +f.detailed_address.value.trim();

		
	    f.user_addr.value = user_addr;
	 // 폼 제출
        f.action = "address_update";
        f.method = "POST";
        f.submit();
	}
	
	function deleteAccount(form) {
	    // 회원 탈퇴를 확인하고 진행할 수 있는 다이얼로그나 로직을 추가할 수 있습니다.
	    if (confirm("정말로 회원을 탈퇴하시겠습니까?")) {
	        form.action = "delete_account"; // 회원탈퇴를 처리할 컨트롤러나 서블릿의 엔드포인트를 지정합니다.
	        form.method = "POST";
	        form.submit();
	    }
	}