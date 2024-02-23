<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<style type="text/css"></style>
<link rel="stylesheet" href="resources/css/login.css">
<script src="resources/js/httpRequest.js"></script>
<script type="text/javascript">
		
	function send(f){
		let userEmail = f.userEmail.value.trim();
		
		if(userEmail == ''){
			alert('이메일를 입력해주세요');
			return;
		}
		
		f.action = "user_insert";
		f.method = "POST";
		f.submit();
	}
	
	

	function login(f){
		let userEmail = f.userEmail.value.trim();
		let userPw = f.userPw.value.trim();
		
		alert(userEmail);
		
		//유효성체크
		if(userEmail==''){
			alert("아이디를 입력해주세요");
			return;
		}
		
		if(userPw==''){
			alert("비밀번호를 입력하세요");
			return;
		}
		
		let url = "login";
		let param ="userEmail="+encodeURIComponent(userEmail)+"&userPw="+encodeURIComponent(userPw);

		sendRequest(url,param,myCheck,"POST");
	}
	
	//콜백메서드
	function myCheck(){
	if(xhr.readyState == 4 && xhr.status == 200){
		let data = xhr.responseText;
		let json = eval(data);

		if(json[0].param == 'no_id'){
			alert("아이디가 존재하지 않습니다.");
		}else if(json[0].param == 'no_pwd'){
			alert("비밀번호가 맞지 않습니다.");
		}else {
			alert("로그인 성공");
			location.href="board_list";

		}
	}
}
</script>

</head>

<body>
<div class="container" id="container">
  <div class="form-container sign-up-container">
    <form action="#">
      <h1>Create Account</h1>
      <div class="social-container">
     
      </div>
      <span>or use your email for registration</span>
      <input type="text" name="userName" id ="userName" placeholder="Name" />
      <input type="email" name="userEmail" id="userEmail" oninput="check_id()"placeholder="Email" ><span id="check"></span>
      <input type="password" name="userPw" id ="userPw" placeholder="Password" />
      
      <button onclick="send(this.form)">Sign Up</button>
    </form>
  </div>
  
  
  <div class="form-container sign-in-container">
    <form action="#">
      <h1>Sign in</h1>
      <div class="social-container">
        <a href="#" class="social"><i class="fab fa-facebook-f"></i></a>
        <a href="#" class="social"><i class="fab fa-google-plus-g"></i></a>
        <a href="#" class="social"><i class="fab fa-linkedin-in"></i></a>
      </div>
      <span>or use your account</span>
      <input type="email" name="userEmail" id="userEmail" placeholder="Email" />
      <input type="password"name="userPw" id="userPw"  placeholder="Password" />
      <a href="#">Forgot your password?</a>
      <button onclick="login(this.form)">Sign In</button>
    </form>
  </div>
  
  
  <div class="overlay-container">
    <div class="overlay">
      <div class="overlay-panel overlay-left">
        <h1>Welcome Back!</h1>
        <p>세상에 하나뿐인 특별한 프로젝트를
        발견해보세요!</p>
        <button class="ghost" id="signIn">Sign In</button>
      </div>
      <div class="overlay-panel overlay-right">
        <h1>이메일로 가입하기</h1>
      	<p>후원을 기다리는 창작자를 만나보세요!</p>
        <button class="ghost" id="signUp">Sign Up</button>
      </div>
    </div>
  </div>
</div>


</body>
<script src="resources/js/login.js"></script>
</html>