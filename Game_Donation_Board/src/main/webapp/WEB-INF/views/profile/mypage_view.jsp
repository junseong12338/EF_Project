<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
   

<title>EZ Funding</title>
    <!-- Bootstrap core CSS -->
    <link href="resources/vendor/bootstrap/css/bootstrap.min.css" rel="stylesheet">
    <!-- Additional CSS Files -->
    <link rel="stylesheet" href="resources/assets/css/fontawesome.css">
    <link rel="stylesheet" href="resources/assets/css/templatemo-cyborg-gaming.css">
    <link rel="stylesheet" href="resources/assets/css/owl.css">
    <link rel="stylesheet" href="resources/assets/css/animate.css">
    <link rel="stylesheet"href="https://unpkg.com/swiper@7/swiper-bundle.min.css"/>
    <link rel="stylesheet" href="resources/assets/css/side-bar.css">
    <link rel="stylesheet" href="https://fonts.googleapis.com/css?family=Montserrat:400,800">
<style type="text/css">
 a span:hover {
display: block;

  	border-bottom: 2px solid white;
}
</style>
<body>

  <%@ include file= "/WEB-INF/views/board/menu.jsp" %>
 <div class="container">
    <div class="row">
      <div class="col-lg-12">
        <div class="page-content">

          <!-- ***** Banner Start ***** -->
          <div class="row">
            <div class="col-lg-12">
              <div class="main-profile ">
                <div class="row">
                  <div class="col-lg-4">
                    <img src="assets/images/profile.jpg" alt="" style="border-radius: 23px;">
                  </div>
                  <div class="col-lg-4 align-self-center">
                    <div class="main-info header-text">
                      
                      <h4>${user_email.user_name}님 환영합니다.</h4>
                      <p>후원한 목록과 내 프로젝트를 관리하세요</p>
                      <div class="main-border-button">
                        <a href="#">대표 이미지 등록</a>
                      </div>
                    </div>
                  </div>
                  <div class="col-lg-4 align-self-center">
            		<ul>
                      <li>
                      	내 정보<a href="myinfo_view"><span>수정하기</span></a>
                      </li>
                      <li>
                        후원한 프로젝트 내역 <a href="sponsorshipdetails_view"><span>0개</span></a>
                      </li>
                       <li>
                        등록한 프로젝트<a href="registered_Project"><span>0개</span></a>
                      </li>
                      <li>
                        리뷰<a href="review"><span>0개</span></a>
                      </li>
                      <li>
                        잔여 포인트 <span id="remaining-points">${user_email.user_point}</span>
                      </li>
                      <li>
                        <a href="charge_view"><span>충전하기</span></a>
                        <br>
                      </li>
                    </ul>
                  </div>
                </div>
              </div>
            </div>
          </div>
        </div>
      </div>
    </div>
  </div>
  
  <script src="resources/assets/jquery/jquery.min.js"></script>
  <script src="resources/assets/bootstrap/js/bootstrap.min.js"></script>
  <script src="resources/assets/js/isotope.min.js"></script>
  <script src="resources/assets/js/owl-carousel.js"></script>
  <script src="resources/assets/js/tabs.js"></script>
  <script src="resources/assets/js/popup.js"></script>
  <script src="resources/assets/js/custom.js"></script>
  <script type="text/javascript">

      //페이지 로드 시 잔여 포인트를 가져오기
      document.addEventListener("DOMContentLoaded", fetchRemainingPoints);
  </script>

  </body>
</html>
