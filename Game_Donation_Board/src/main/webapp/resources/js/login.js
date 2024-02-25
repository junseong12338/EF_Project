

const signUpButton = document.getElementById('signUp');
const signInButton = document.getElementById('signIn');
const container = document.getElementById('container');

signUpButton.addEventListener('click', () => {
  container.classList.add("right-panel-active");
});

signInButton.addEventListener('click', () => {
  container.classList.remove("right-panel-active");
});

// 아이디 중복 여부 변수
let Email_Check = false;
// 이름 유효성 검사 함수
function check_Name(f) {
    let userName = f.USER_NAME.value.trim();
    // 이름이 공백인 경우
    if (userName === '') {
        let span = document.getElementById("check_Name");
        span.innerHTML = "이름을 입력해주세요.";
        Email_Check = false; // 이메일 유효성 검사 결과를 false로 설정
        return; // 함수 종료
    } else {
        let span = document.getElementById("check_Name");
        span.innerHTML = "";
        Email_Check = true;
    }
}

// 비밀번호 유효성 검사 함수
function check_Pw(f) {
    let userPw = f.USER_PW.value.trim();
    // 비밀번호가 공백인 경우
    if (userPw === '') {
        let span = document.getElementById("check_Pw");
        span.innerHTML = "비밀번호를 입력해주세요.<br><br>";
        Email_Check = false; // 이메일 유효성 검사 결과를 false로 설정
        return; // 함수 종료
    } else {
        let span = document.getElementById("check_Pw");
        span.innerHTML = "";
        Email_Check = true;
    }
}

// 이메일 유효성 검사 함수
function check_Email(f) {
    let userEmail = f.USER_EMAIL.value.trim();
    // 이메일이 공백인 경우
    if (userEmail.trim() === '') {
        let span = document.getElementById("check_Email");
        span.innerHTML = "이메일을 입력해주세요.";
        Email_Check = false; // 이메일 유효성 검사 결과를 false로 설정
        return; // 함수 종료
    }
    // 이메일 유효성 검사
    let emailPattern = /^[^\s@]+@[^\s@]+\.[^\s@]+$/;
    let isValidEmail = emailPattern.test(userEmail);
    // 이메일 형식이 올바르지 않은 경우
    if (!isValidEmail) {
        let span = document.getElementById("check_Email");
        span.innerHTML = "이메일 형식이 올바르지 않습니다.";
        Email_Check = false; // 아이디 중복 여부 초기화
        return; // 함수 종료
    }
    // 이메일 유효성 검사 통과한 경우 아이디 중복 검사 진행
    let url = "check_Email"; // 이메일 중복 확인을 위한 서버 경로
    let param = "userEmail=" + encodeURIComponent(userEmail);
    sendRequest(url, param, resultFn, "POST");
}

// 이메일 중복 확인 결과 처리 콜백 함수
function resultFn(f) {
    if (xhr.readyState == 4 && xhr.status == 200) {
        let span = document.getElementById("check_Email");
        let data = xhr.responseText;
        let json = JSON.parse(data);
        if (json[0].res == 'no') {
            span.innerHTML = "이미 있는 아이디 입니다.";
            Email_Check = false;
        } else if (json[0].res == 'yes') {
            span.innerHTML = "<span style='color:green;'>사용가능한 아이디 입니다.</span>";
            Email_Check = true;
        }
    }
}

// 회원가입 데이터 전송 함수
function send(f) {
    let userName = f.USER_NAME.value.trim();
    let userEmail = f.USER_EMAIL.value.trim();
    let userPw = f.USER_PW.value.trim();

    // 이메일 유효성 및 아이디 중복 검사 수행
    check_Email(f);
    check_Name(f);
    check_Pw(f);

    // 아이디 중복 여부에 따라 폼 제출 여부 결정
    if (Email_Check) {
        // 폼 데이터 설정
        f.USER_NAME.value = userName;
        f.USER_EMAIL.value = userEmail;
        f.USER_PW.value = userPw;

        // 폼 제출
        f.action = "user_insert";
        f.method = "POST";
        f.submit();
    }
}

// 로그인 함수
function login(f) {
    let userEmail = f.userEmail.value.trim();
    let userPw = f.userPw.value.trim();

    // 유효성 체크
    if (userEmail == '') {
        alert("아이디를 입력해주세요");
        return;
    }
    if (userPw == '') {
        alert("비밀번호를 입력하세요");
        return;
    }

    let url = "login";
    let param = "userEmail=" + encodeURIComponent(userEmail) + "&userPw=" + encodeURIComponent(userPw);
    sendRequest(url, param, myCheck, "POST");
}

// 로그인 결과 확인 함수
function myCheck() {
    if (xhr.readyState == 4 && xhr.status == 200) {
        let data = xhr.responseText;
        let json = JSON.parse(data);
        if (json[0].param == 'no_email') alert("아이디가 존재하지 않습니다.");
        else if (json[0].param == 'no_pw') alert("비밀번호가 맞지 않습니다.");
        else location.href = "board_list"; // 화면 전환
    }
}