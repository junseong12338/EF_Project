<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<link rel="stylesheet" href="resources/css/menu.css">

<script src="resources/js/menu.js"></script>
  
  <!-- ***** Header Area Start ***** -->
  <header class="header-area header-sticky">
    <div class="container">
        <div class="row">
            <div class="col-12">
               <nav class="main-nav">
                    <a href="board_list" class="logo">
                        <img src="resources/assets/images/logo.png" alt="">
                    </a>
					<ul class="nav">
					    <li><a href="board_list" class="active">Home</a></li>
					    <li><a href="project_list">List</a></li>
				      	<li><a href="notice">Notice</a></li>
					    <c:choose>
					        <c:when test="${empty user_email }">
					            <li><a href="login_form">로그인/회원가입 <img src="resources/assets/images/user.png"></a></li>
					        </c:when>
					        <c:when test="${not empty user_email }">
					            <li class="dropdown-wrapper">
					                <a href="#" onclick="toggleDropdown(event)" class="dropdown-toggle">${user_email.user_name}<img src="${user_email.user_img}"><span class="caret"></span></a>
					                <ul id="dropdown-container" class="dropdown-list">
					                    <li class="no-background" onclick="location.href='mypage_view'">프로필</li>
					                    <li class="no-background" onclick="location.href='project_editor'">작품 등록하기</li>
					                    <c:if test="${user_email.user_grade eq 3}">
					                        <li onclick="location.href='AdminPage'">관리자페이지</li>
					                    </c:if>
					                    <!-- <li onclick="location.href='disconnectNaver?accessToken=' + encodeURIComponent('${accessToken}')">네이버 연동 해제</li>
					                    <li onclick="location.href='unlinkKakaoAccount?accessToken=' + encodeURIComponent('${access_Token}')">카카오 연동 해제</li> -->
					                    <hr>
					                    <li class="no-background" onclick="location.href='logout'">로그아웃</li>
					                </ul>
					            </li>
					        </c:when>
					    </c:choose>
					</ul>   
                    <a class='menu-trigger'>
                        <span>Menu</span>
                    </a>
                </nav>
            </div>
        </div>
    </div>
  </header>
  
  <!-- ***** Header Area End ***** -->