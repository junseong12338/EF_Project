<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
    
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <title>EZ Funding</title>
    
    <link href="resources/css/bootstrap.min.css" rel="stylesheet">
    <link rel="stylesheet" href="resources/css/fontawesome.css">
    <link rel="stylesheet" href="resources/css/templatemo-cyborg-gaming.css">
    <link rel="stylesheet" href="resources/css/owl.css">
    <link rel="stylesheet" href="resources/css/animate.css">
    <link rel="stylesheet"href="https://unpkg.com/swiper@7/swiper-bundle.min.css"/>
    <link href="https://fonts.googleapis.com/css2?family=Poppins:wght@100;200;300;400;500;600;700;800;900&display=swap" rel="stylesheet">
	<link rel="stylesheet" href="resources/css/myinfo.css">
</head>
<body>
    <%@ include file= "/WEB-INF/views/board/menu.jsp" %>

  <div class="container">
    <div class="row">
      <div class="col-lg-12">
        <div class="page-content">
  			<form>
            <div id="profile-settings">
                <h2>프로필 설정</h2><br>
              
             <div class="form-group"> 
                    <label for="username">사용자 닉네임 </label>
                    <input type="text" id="username" name="user_name" class="nickname"><br><br>
                 </div>
               <div class="form-group">
                    <label for="email">이메일 주소 </label>
                    <input type="email" id="email" name="user_email" oninput="validateEmail(this)">
					<div id="email-error" style="color: red;"></div>
                  </div>   
            </div>
          		<!-- 배송지 설정 입력란 추가 -->
             <h2>주소 입력</h2><br><br>
	            <input type="text" name ="postal_code" id="sample4_postcode" placeholder="우편번호">
	            <input type="button" onclick="sample4_execDaumPostcode()" value="우편번호 찾기"><br>
	            <input type="text" name = "road_name" id="sample4_roadAddress" placeholder="도로명주소">
	            <input type="text" name = "detailed_address"id="sample4_detailAddress" placeholder="상세주소"><br>
	            <input type ="hidden" name = "user_addr"/>
	            <button onclick="addRess(this.form)">저장</button>
            </form>
                <!-- 배송지 설정 입력란 추가 -->
                
                      <!-- 회원 탈퇴 -->
                 <form name="deleteForm">
            <div id="profile-delete" class="form-group" style="text-align: right;">
              <input type ="hidden" name = "user_idx" value="${user_email.user_idx }"/> 
              <button onclick="deleteAccount(this.form)">회원탈퇴</button>

          </div>
        </form>
        		<!-- 회원 탈퇴 -->
            </div>
        </div>
    </div>
  </div>
  <footer>
    <div class="container">
      <div class="row">
        <div class="col-lg-12">
          <p>Copyright © 2036 <a href="#">Cyborg Gaming</a> Company. All rights reserved. 
          
          <br>Design: <a href="https://templatemo.com" target="_blank" title="free CSS templates">TemplateMo</a>  Distributed By <a href="https://themewagon.com" target="_blank" >ThemeWagon</a></p>
        </div>
      </div>
    </div>
  </footer>

  <script src="resources/jquery/jquery.min.js"></script>
  <script src="resources/js/bootstrap.min.js"></script>
  <script src="resources/js/isotope.min.js"></script>
  <script src="resources/js/owl-carousel.js"></script>
  <script src="resources/js/tabs.js"></script>
  <script src="resources/js/popup.js"></script>
  <script src="resources/js/custom.js"></script>
  <script src="//t1.daumcdn.net/mapjsapi/bundle/postcode/prod/postcode.v2.js"></script>
  <script src="resources/js/myinfo.js"></script>
</body>
</html>