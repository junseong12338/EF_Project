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
        /* 입력 칸을 투명색으로 만드는 스타일 */
  
 input[type="text"] {
            background-color: transparent; /* 투명 배경색 */
            border: 1px solid black; /* 테두리 설정 */
            border-radius: 5px; /* 테두리 둥글게 만듦 */
            padding: 5px; /* 입력 칸 내부 여백 */
            margin-bottom: 10px; /* 입력 칸 간격 조정 */
         
            color: white; /* 텍스트 색상을 흰색으로 설정 */
        }

        #nickname{
          width: 20%; /* 사용자 이름 입력 칸 너비를 70%로 설정 */
        }

        input[type="email"] {
            background-color: transparent; /* 투명 배경색 */
            border: 1px solid black; /* 테두리 설정 */
            border-radius: 5px; /* 테두리 둥글게 만듦 */
            padding: 5px; /* 입력 칸 내부 여백 */
            margin-bottom: 10px; /* 입력 칸 간격 조정 */
            width: 35%; /* 이메일 주소 입력 칸 너비를 100%로 설정 */
            color: white; /* 텍스트 색상을 흰색으로 설정 */
            display: inline-block; /* 인라인 블록 요소로 변경 */
        }

        /* 첫 번째 배송지 입력란 스타일 */
        .shipping-address-1 {
            width:50%; /* 첫 번째 입력란의 너비를 60%로 설정 */
        }

        /* 두 번째 배송지 입력란 스타일 */
        .shipping-address-2 {
            width: 20%; /* 두 번째 입력란의 너비를 40%로 설정 */
        }

        
    label{
        color: white;
    }

    input[type="submit"] {
            padding: 10px 20px;
            border: none;
            border-radius: 5px;
            background-color: #bb4ab1; padding: 5px 10px;
            color: #fff;
            font-size: 16px;
            cursor: pointer;
            margin-left: 20px;
        }

        /* 저장 버튼에 마우스를 올렸을 때 스타일 */
        input[type="submit"]:hover {
            background-color: #a400b3;
            transition: background-color 0.5s;
        }

        h2 {
            margin-bottom: 20px;
            color: #333;
            display: inline-block;
        }

        .section {
            margin-bottom: 30px;
        }

        /* .form-group {
            display: flex;
            align-items: center;
        } */

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
      #profile-settings {
        margin-bottom: 40px; /* 프로필 설정과 다음 섹션 사이의 간격 설정 */
      }
    </style>

<style>
  /* 결제 수단 방법과 간격 조정 */
  #payment-settings {
    margin-bottom: 50px; /* 원하는 만큼의 간격으로 설정 */
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
                        <li><a href="mypage_view" class="active">마이페이지 <img src="resources/images/profile-header.jpg" alt=""></a></li>
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
                <h2>프로필 설정</h2><br>
                <form>
                  <div class="form-group">
                    <label for="username">사용자 닉네임 </label>
                    <input type="text" id="username" name="username" class="nickname">
                    <input type="submit" value="저장"><br><br>
                  </div>

                  <div class="form-group">
                    <label for="email">이메일 주소 </label>
                    <input type="email" id="email" name="email">
                    <input type="submit" value="저장">
                  </div>
                    <!-- 필요한 다른 프로필 설정 추가 -->
                    
                </form>
            </div>
        
                <div id="shipping-settings" class="section">
                     <h2>배송지 입력</h2>
                    <form>
                        <!-- 배송지 설정 입력란 추가 -->
                        <form action="(test)address.html" method="get">
                        <div class="form-group">
                          <label for="shipping-address-1">주소 입력 </label>
                          <input type="text" id="shipping-address-1" name="shipping-address-1" class="shipping-address-1">    
                          <input type="submit" value="추가">
                      </div>
                    </form>
                      <div class="form-group">
                          <label for="shipping-address-2">상세 주소 </label>
                          <input type="text" id="shipping-address-2" name="shipping-address-2" class="shipping-address-2">
                          <input type="submit" value="저장">
                      </div>
                      
                    </form>
                </div>
            </div>
        </div>
    </div>
  </div>
  <script>
    // 이메일 주소 유효성 검사 함수
    function validateEmail(email) {
        const re = /\S+@\S+\.\S+/;
        return re.test(email);
    }

    // 폼 제출 시 이메일 주소 유효성 검사
    document.querySelector('form').addEventListener('submit', function(event) {
        const emailInput = document.getElementById('email');
        const emailError = document.getElementById('email-error');
        if (!validateEmail(emailInput.value)) {
            emailError.textContent = '올바른 이메일 주소를 입력하세요.';
            event.preventDefault(); // 폼 제출 방지
        } else {
            emailError.textContent = ''; // 에러 메시지 초기화
        }
    });
</script>
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
  <script src="vendor/jquery/jquery.min.js"></script>
  <script src="vendor/bootstrap/js/bootstrap.min.js"></script>

  <script src="resources/js/isotope.min.js"></script>
  <script src="resources/js/owl-carousel.js"></script>
  <script src="resources/js/tabs.js"></script>
  <script src="resources/js/popup.js"></script>
  <script src="resources/js/custom.js"></script>

  <!-- 주소api -->
  <script src="//t1.daumcdn.net/mapjsapi/bundle/postcode/prod/postcode.v2.js"></script>


</body>
</html>