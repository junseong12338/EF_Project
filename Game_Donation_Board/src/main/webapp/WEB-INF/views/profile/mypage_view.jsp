<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">

    <link href="https://fonts.googleapis.com/css2?family=Poppins:wght@100;200;300;400;500;600;700;800;900&display=swap" rel="stylesheet">

    <title>Cyborg - Awesome HTML5 Template</title>

    <!-- Bootstrap core CSS -->
    <link href="resources//css/bootstrap.min.css" rel="stylesheet">


    <!-- Additional CSS Files -->
    <link rel="stylesheet" href="resources/css/fontawesome.css">
    <link rel="stylesheet" href="resources/css/templatemo-cyborg-gaming.css">
    <link rel="stylesheet" href="resources/css/owl.css">
    <link rel="stylesheet" href="resources/css/animate.css">
    <link rel="stylesheet"href="https://unpkg.com/swiper@7/swiper-bundle.min.css"/>

    <style>
    

      .heading-section h4 {
        color: white;
        font-size: 34px;
        text-decoration: none;
        margin-bottom: 30px;
      }

    </style>

<style>
  .owl-features .item {
    width: 100%; /* 각 사진 요소의 너비를 25%로 설정하여 4개씩 나오도록 함 */
    float: left; /* 좌측 정렬되도록 함 */
  }

  .owl-features .thumb img {
    width: 100%; /* 각 사진 이미지가 요소 내에 꽉 차게 표시되도록 함 */
    border-radius: 20px;
  }
  .down-content .down-content span {
    padding: 10px 0; /* 위아래 padding을 조절하여 높이를 조절합니다 */
    display: flex;
    align-items: center;

}

</style>

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

          <!-- ***** Banner Start ***** -->
          <div class="row">
            <div class="col-lg-12">
              <div class="main-profile ">
                <div class="row">
                  <div class="col-lg-4">
                    <img src="resources/images/둘리의모험.jpg" alt="" style="border-radius: 23px;">
                  </div>
                  <div class="col-lg-4 align-self-center">
                    <div class="main-info header-text">
                      <span>현재 진행중인 프로젝트</span>
                      <h4>아기 공룡 둘리</h4>
                      <p>빙하 타고 내려온 둘리의 일상</p>
                    </div>
                  </div>
                  <div class="col-lg-4 align-self-center">
                   
                    <ul>
                      <li><a href="myinfo.html">내 정보</a></li>
                      <li>후원 누적 포인트 <span>1,100,000 Point</span></li>
                      <li>잔여 포인트 <span>7,000 Point</span></li>
                      <li>리뷰 <span>30</span></li>
                      <li>포인트 충전
                        <a href="charge.html">
                          <button id="recharge-button" style="margin-left: 135px; background-color: #bb4ab1; color: #fff; border: none; padding: 5px 10px; border-radius: 5px; cursor: pointer; transition: background-color 0.5s;" onmouseover="this.style.backgroundColor='#a400b3'" onmouseout="this.style.backgroundColor='#bb4ab1'">충전하기</button>
                        </a>
                      </li>
                      <li><a href="SponsorshipDetails.html">후원한 프로젝트 내역</a></li>
                    </ul>
                  </div>
                </div>
                <div class="row">
                  <div class="col-lg-12">
                    <div class="featured-games header-text">
                    <div class="clips">
                      <div class="row">
                        <div class="col-lg-12">
                          <div class="heading-section">
                            
                            <h4>나의 마감된 프로젝트</h4>
                          </div>
                        </div>
                        <div class="owl-features owl-carousel">
                          <div class="item">
                            <div class="thumb">
                              <img src="resources/images/귀멸의칼날01.jpg" alt="" style="border-radius: 23px;">
                              
                            </div>
                            <div class="down-content">
                              <h4>귀멸의 칼날</h4>
                              <span><br><br><i class="fa fa-eye"></i>달성 포인트 : 2,700,000 Point</span>
                            </div>
                          </div>
                        
                          <div class="item">
                            <div class="thumb">
                              <img src="resources/images/디아블로01.jpg" alt="" style="border-radius: 20px;">
                              
                            </div>
                            <div class="down-content">
                              <h4>디아블로3</h4>
                              <span><br><br><i class="fa fa-eye"></i>달성 포인트 : 4,000,000 Point</span>
                              
                            </div>
                          </div>
                        
                          <div class="item">
                            <div class="thumb">
                              <img src="resources/images/로스트아크01.jpg" alt="" style="border-radius: 23px;">
                              
                            </div>
                            <div class="down-content">
                              <h4>로스트아크</h4>
                              <span><br><br><i class="fa fa-eye"></i>달성 포인트 : 4,500,000 Point</span>
                            </div>
                          </div>
                        
                        
                          <div class="item">
                            <div class="thumb">
                              <img src="resources/images/무협01.jpg" alt="" style="border-radius: 23px;">
                            </div>
                            
                            <div class="down-content">
                              <h4>무협</h4>
                              <span><br><br><i class="fa fa-eye"></i>달성 포인트 : 1,500,000 Point</span>
                            </div>
                          </div>

                          <div class="item">
                            <div class="thumb">
                              <img src="resources/images/스타크래프트01.jpg" alt="" style="border-radius: 23px;">
                            </div>
                            
                            <div class="down-content">
                              <h4>스타크래프트</h4>
                              <span><br><br><i class="fa fa-eye"></i>달성 포인트 : 6,500,000 Point</span>
                            </div>
                          </div>

                          <div class="item">
                            <div class="thumb">
                              <img src="resources/images/검은사막01.jpg" alt="" style="border-radius: 23px;">
                            </div>
                            
                            <div class="down-content">
                              <h4>검은사막</h4>
                              <span><br><br><i class="fa fa-eye"></i>달성 포인트 : 2,300,000 Point</span>
                            </div>
                          </div>
                        </div>
                        </div>
                          <div class="main-button">
                            <a href="#">진행중인 프로젝트 보기</a>
                          </div>
                        </div>
                      </div>
                    </div>
                  </div> 
                  </div>
                </div>
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
  <script src="resources/bootstrap/js/bootstrap.min.js"></script>

  <script src="resources/js/isotope.min.js"></script>
  <script src="resources/js/owl-carousel.js"></script>
  <script src="resources/js/tabs.js"></script>
  <script src="resources/js/popup.js"></script>
  <script src="resources/js/custom.js"></script>

  <script>
    $(document).ready(function() {
      // 후원하기 버튼 클릭 시
      $('.main-border-button a').on('click', function(event) {
        event.preventDefault(); // 기본 이벤트 제거
  
        // 여기에 후원하기 버튼을 눌렀을 때의 동작을 정의합니다.
        alert("자신의 프로젝트에는 후원 할 수 없습니다."); // 예시로 경고창을 띄우는 코드입니다.
      });
    });

  </script>

</body>
</html>