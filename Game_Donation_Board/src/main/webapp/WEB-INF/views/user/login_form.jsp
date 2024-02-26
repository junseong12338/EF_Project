<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<style type="text/css"></style>
<link rel="stylesheet" href="resources/css/login.css">

</head>
<body>
<div class="container" id="container">
  <!-- 회원가입 -->
  <div class="form-container sign-up-container">
    <form>
      <h1>Create Account</h1>
      <div class="social-container">
       	<a href="#" class="social"><i class="fab fa-facebook-f"></i></a>
        <a href="#" class="social"><i class="fab fa-google-plus-g"></i></a>
        <a href="#" class="social"><i class="fab fa-linkedin-in"></i></a>
      </div>
      <span>or use your email for registration</span>
	    <input type="text" name="user_name" id="user_name"  oninput="checkName(this.form)" placeholder="Name" /><span style='color:red;'id="check_name"></span>
		<input type="email" name="user_email" id="user_email" oninput="checkEmail(this.form)" placeholder="Email" /><span style='color:green;'id="check_email"></span>
		<input type="password" name="user_pw" id="user_pw"  oninput="checkPw(this.form)" placeholder="Password" /><span style='color:red;' id="check_pw"></span>
      <button type = "button" onclick="send(this.form)">Sign Up</button>
    </form>
  </div>
  
  <!-- 로그인 -->
  <div class="form-container sign-in-container">
    <form>
      <h1>Sign in</h1>
      <div class="social-container">
        <a href="#" class="social"><i class="fab fa-facebook-f"></i></a>
        <a href="#" class="social"><i class="fab fa-google-plus-g"></i></a>
        <a href="#" class="social"><i class="fab fa-linkedin-in"></i></a>
      </div>
      <span>or use your account</span>
      <input type="email" name="user_email" id="login_email" oninput="LoginCheckEmail(this.form)" placeholder="Email" /><span style='color:red;'id="checklog_email"></span>
      <input type="password"name="user_pw" id="login_pw"  oninput="LoginCheckPw(this.form)" placeholder="Password" /><span style='color:red;' id="checklog_pw"></span>
      <a href="#">Forgot your password?</a>
   		<button type = "button"onclick="login(this.form)">Sign In</button>
    </form>
  </div>
  
  <!-- 회원가입 / 로그인 사이드바  -->
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
<script src="resources/js/httpRequest.js"></script>
<script src="resources/js/login.js"></script>
</html>