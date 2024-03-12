<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>    
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <title>Cyborg - Awesome HTML5 Template</title>
    
    <link rel="stylesheet" href="resources/assets/css/mypage.css">
    <link href="resources/assets/css/bootstrap.min.css" rel="stylesheet">
    <link rel="stylesheet" href="resources/assets/css/fontawesome.css">
    <link rel="stylesheet" href="resources/assets/css/templatemo-cyborg-gaming.css">
    <link rel="stylesheet" href="resources/assets/css/owl.css">
    <link rel="stylesheet" href="resources/assets/css/animate.css">
    <link rel="stylesheet"href="https://unpkg.com/swiper@7/swiper-bundle.min.css"/>
    <link href="https://fonts.googleapis.com/css2?family=Poppins:wght@100;200;300;400;500;600;700;800;900&display=swap" rel="stylesheet">

<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
  <div id="js-preloader" class="js-preloader">
    <div class="preloader-inner">
      <span class="dot"></span>
      <div class="dots">
        <span></span>
        <span></span>
        <span></span>
      </div>
    </div>
  </div>
  <%@ include file= "/WEB-INF/views/board/menu.jsp" %>
  <div class="container">
    <div class="row">
      <div class="col-lg-12">
        <div class="page-content">
          <div class="row">
            <div class="col-lg-12">
              <div class="main-profile ">
                <div class="row">
                                     
                    <ul>
                      <li><a href="myinfo_view">내 정보</a></li>
                      <li>
                        잔여 포인트
                        <span id="remaining-points"
                          >${user_email.user_point}</span
                        >
                      </li>
                      <li>
                        포인트 충전
                        <a href="charge_view">
                          <button
                            id="recharge-button"
                            style="
                              margin-left: 135px;
                              background-color: #bb4ab1;
                              color: #fff;
                              border: none;
                              padding: 5px 10px;
                              border-radius: 5px;
                              cursor: pointer;
                              transition: background-color 0.5s;
                            "
                            onmouseover="this.style.backgroundColor='#a400b3'"
                            onmouseout="this.style.backgroundColor='#bb4ab1'"
                          >
                            충전하기
                          </button>
                        </a>
                      </li>
                      <li><a href="sponsorshipdetails_view">후원한 프로젝트 내역</a> | <a href="registered_Project"> 등록한 프로젝트 </a> | <a href="review_list"> 리뷰 </a></li>
                    </ul>
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
         /* function fetchRemainingPoints() {
	    fetch("/getRemainingPoints")
	        .then(response => response.text())
	        .then(data => {
	            const remainingPoints = parseInt(data); // 서버로부터 받은 포인트 값을 정수로 변환하여 변수에 할당
	            document.getElementById('remaining-points').textContent = remainingPoints + ' Point';
	        })
	        .catch(error => console.error('Error fetching remaining points:', error));
	  }  */
      //페이지 로드 시 잔여 포인트를 가져오기
      document.addEventListener("DOMContentLoaded", fetchRemainingPoints);
  </script>

  </body>
</html>
