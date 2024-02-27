<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">

    <link href="https://fonts.googleapis.com/css2?family=Poppins:wght@100;200;300;400;500;600;700;800;900&display=swap" rel="stylesheet">

    <title>Cyborg - Awesome HTML5 Template</title>

    <style>
    
      </style>

    <!-- Bootstrap core CSS -->
    <link href="resources/css/bootstrap.min.css" rel="stylesheet">


    <!-- Additional CSS Files -->
    <link rel="stylesheet" href="resources/css/fontawesome.css">
    <link rel="stylesheet" href="resources/css/templatemo-cyborg-gaming.css">
    <link rel="stylesheet" href="resources/css/owl.css">
    <link rel="stylesheet" href="resources/css/animate.css">
    <link rel="stylesheet"href="https://unpkg.com/swiper@7/swiper-bundle.min.css"/>

    <style>
      /* 프로필 설정과 사용자 닉네임 간격 조정 */
      /* #profile-settings {
        margin-bottom: 40px; /* 프로필 설정과 다음 섹션 사이의 간격 설정 */
      /* } */ 
    
    /* 결제 수단 방법과 간격 조정 */
    /* #payment-settings {
      margin-bottom: 50px; /* 원하는 만큼의 간격으로 설정 */
      /* } */ 

          /* CSS 스타일링 */
          .category {
          display: none; /* 초기에는 숨겨진 상태 */
        }

    .heading-section th {
      color: white;
      font-size: 34px;
      text-decoration: none;
      margin-bottom: 30px;
    }

    th {
    color: white;
    font-weight: bold;
      padding: 10px;
    }

    /* 테이블 셀 경계 선 스타일링 */
    td, th {
    border: 1px solid #ddd;
    padding: 8px;
    text-align: left;
    }

    table{border:1px solid white; 
		      border-collapse:collapse;
		      text-align:center;}
		      
		th{width:80px;}

  .button-container {
       margin-right: 20%; /* 버튼을 오른쪽에 정렬 */
      margin-bottom: 20px; /* 버튼과 다음 섹션 사이의 간격 설정 */
   }

    /* 버튼에 마우스를 올렸을 때의 스타일 */
  /* button:hover {
  background-color: #b502c5;
} */

.payment-button {
  margin-left: 0px;
  background-color: #FFEB3B; /* 배경색을 변경합니다. */
  color: #000; /* 텍스트 색상을 변경합니다. */
  border: none;
  padding: 5px 15px; /* 내부 여백을 조정합니다. */
  border-radius: 5px;
  cursor: pointer;
  transition: background-color 0.5s; /* 마우스 오버 시 부드럽게 변화하도록 설정합니다. */
}

.payment-button img {
  width: 100px; /* 이미지의 너비를 조정합니다. */
  height: auto; /* 이미지의 높이를 자동으로 조정합니다. */
}


#recharge {
  margin-left: 0px;
  background-color: #bb4ab1; /* 배경색을 변경합니다. */
  color: #fff; /* 텍스트 색상을 변경합니다. */
  border: none;
  padding: 5px 23px; /* 내부 여백을 조정합니다. */
  border-radius: 5px;
  cursor: pointer;
  transition: background-color 0.5s; /* 마우스 오버 시 부드럽게 변화하도록 설정합니다. */
}

#recharge:hover {
  background-color: #a400b3; /* 마우스 오버 시 배경색을 변경합니다. */
}

</style>

<!--

TemplateMo 579 Cyborg Gaming

https://templatemo.com/tm-579-cyborg-gaming

-->

<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
 <!-- ***** Preloader Start ***** -->
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
  <!-- ***** Preloader End ***** -->

  <!-- ***** Header Area Start ***** -->
  <header class="header-area header-sticky">
    <div class="container">
        <div class="row">
            <div class="col-12">
                <nav class="main-nav">
                    <!-- ***** Logo Start ***** -->
                    <a href="index.html" class="logo">
                        <img src="resources/images/logo.png" alt="">
                    </a>
                    <!-- ***** Logo End ***** -->
                    <!-- ***** Search End ***** -->
                    <div class="search-input">
                      <form id="search" action="#">
                        <input type="text" placeholder="검색" id='searchText' name="searchKeyword" onkeypress="handle" />
                        <i class="fa fa-search"></i>
                      </form>
                    </div>
                    <!-- ***** Search End ***** -->
                    <!-- ***** Menu Start ***** -->
                    <ul class="nav">
                        <li><a href="index.html">Home</a></li>
                        <li><a href="browse.html">Browse</a></li>
                        <li><a href="details.html">Details</a></li>
                        <li><a href="streams.html">Streams</a></li>
                        <li><a href="profile.html" class="active">마이페이지 <img src="resources/images/profile-header.jpg" alt=""></a></li>
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
  <!-- ***** Header Area End ***** -->

  <div class="container">
    <div class="row">
      <div class="col-lg-12">
        <div class="page-content">

            <div id="profile-settings">
                <h2>결제 수단 방법</h2><br>
                <div class="button-container">
                    <p>결제 금액</p>
                    <input type="number"placeholder="숫자만 입력가능." id="payment">
                </div>
            </div>
            <div>
                <button id="kakao-pay" class="payment-button">
                  <img src="./resources/images/카카오페이.jpg" alt="카카오페이">
                </button>
              </div><br>
              <button id="recharge" class="payment-button">충전하기</button>
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
  <!-- Scripts -->
  <!-- Bootstrap core JavaScript -->
  <script src="resources/jquery/jquery.min.js"></script>
  <script src="resources/js/bootstrap.min.js"></script>

  <script src="resources/js/isotope.min.js"></script>
  <script src="resources/js/owl-carousel.js"></script>
  <script src="resources/js/tabs.js"></script>
  <script src="resources/js/popup.js"></script>
  <script src="resources/js/custom.js"></script>

</body>
</html>