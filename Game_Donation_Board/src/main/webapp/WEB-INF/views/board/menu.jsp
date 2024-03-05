<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<link rel="stylesheet" href="resources/css/menu.css">
<script src="resources/js/menu.js"></script>

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
                        <li><a href="browse.html">Browse</a></li>
                        <li><a href="details.html">Details</a></li>
                        <li><a href="streams.html">Streams</a></li>
		                <c:choose>
							<c:when test="${empty user_email }">
								<li><a href="login_form">로그인/회원가입 <img src="resources/assets/images/user.png"></a></li>
 							</c:when>
							<c:when test="${not empty user_email }">
							<li>
							    <a href="#" onclick="toggleDropdown()" >${user_email.user_name}<img src="resources/assets/images/user.png"></a>
							    <ul id="dropdown-container" class="dropdown-list">
							        <li onclick="location.href='mypage_view'">프로필</li>
							        <li>후원한 프로젝트</li>
							        <li onclick="location.href='project_editor'">작품 등록하기</li>
							        <hr>
							        <li onclick="location.href='logout'">로그아웃</li>
							    </ul>
							</li>
							</c:when>
						</c:choose>
                    </ul>   
                    <a class='menu-trigger'>
                        <span>Menu</span>
                    </a>
                    <!-- ***** Menu End ***** -->
                </nav>
            </div>
        </div>
    </div>
  </header>