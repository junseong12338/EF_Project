<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
<title>Project-List</title>

    <!-- Bootstrap core CSS -->
    <link href="vendor/bootstrap/css/bootstrap.min.css" rel="stylesheet">


    <!-- Additional CSS Files -->
    <link rel="stylesheet" href="assets/css/fontawesome.css">
    <link rel="stylesheet" href="assets/css/templatemo-cyborg-gaming.css">
    <link rel="stylesheet" href="assets/css/owl.css">
    <link rel="stylesheet" href="assets/css/animate.css">
    <link rel="stylesheet"href="https://unpkg.com/swiper@7/swiper-bundle.min.css"/>


    <link rel="stylesheet" href="assets/css/side-bar.css">

<script>
// 스크롤 이벤트 처리
let currentPage = 1;

let isLoading = false;

$(window).on("scroll",function(){
    let scrollTop = $(window).scrollTop();// 위로 스크롤된 길이
    let windowHeight = $(window).height();// 웹브라우저 창의 높이
    let documentHeight = $(document).height();// 문서 전체의 높이
    let isBottom = scrollTop + windowHeight >= documentHeight;// 스크롤이 바닥에 닿을 때

    if(isBottom){
        if(currentPage == ${totalPage} || isLoading){
            return;
        }

        isLoading = true;
        $(".back-drop").show();
        currentPage++;
        
        console.log("스크롤 - 현재페이지 : " + currentPage);
        // 추가로 받아올 페이지를 서버에 Ajax 요청
        GetList(currentPage);
    };
});

// list 가져오기
const GetList = function(currentPage){
    console.log("리스트 - 현재페이지 : " + currentPage);

    // 무한 스크롤
    $.ajax({
        url : "ajax_list",
        method : "GET",
        data : "pageNum"+currentPage,
        success:function(data){
            console.log("ajax 데이터 : " + data);

            $(".project-list-container").append(data);
            $(".back-drop").hide();
            isLoading = false;
            console.log("ajax 잘넘어옴");
        };
    });
};
</script>
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
                        <img src="assets/images/logo.png" alt="">
                    </a>
                    <!-- ***** Logo End ***** -->
                    <!-- ***** Search End ***** -->
                    <div class="search-input">
                      <form id="search" action="#">
                        <input type="text" placeholder="Type Something" id='searchText' name="searchKeyword" onkeypress="handle" />
                        <i class="fa fa-search"></i>
                      </form>
                    </div>
                    <!-- ***** Search End ***** -->
                    <!-- ***** Menu Start ***** -->
                    <ul class="nav">
                        <li><a href="index.html" class="active">Home</a></li>
                        <li><a href="browse.html">Browse</a></li>
                        <li><a href="details.html">Details</a></li>
                        <li><a href="streams.html">Streams</a></li>
                        <li><a href="profile.html">Profile <img src="assets/images/profile-header.jpg" alt=""></a></li>
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
  
  <!-- side bar Start -->
  <aside class="side-bar">
    <div class="side-bar__icon-box">
      <div class="side-bar__icon-1">
        <div></div>
        <div></div>
        <div></div>
      </div>
    </div>
    <ul>
      <li>
        <a href="#">menu1</a>
        <ul>
          <li><a href="#">text1</a></li>
          <li><a href="#">text2</a></li>
          <li><a href="#">text3</a></li>
          <li><a href="#">text4</a></li>
        </ul>
      </li>
      <li>
        <a href="#">menu2</a>
        <ul>
          <li><a href="#">text1</a></li>
          <li><a href="#">text2</a></li>
          <li><a href="#">text3</a></li>
          <li><a href="#">text4</a></li>
        </ul>
      </li>
      <li>
        <a href="#">menu3</a>
        <ul>
          <li><a href="#">text1</a></li>
          <li><a href="#">text2</a></li>
          <li><a href="#">text3</a></li>
          <li><a href="#">text4</a></li>
        </ul>
      </li>
      <li>
        <a href="#">menu4</a>
        <ul>
          <li><a href="#">text1</a></li>
          <li><a href="#">text2</a></li>
          <li><a href="#">text3</a></li>
          <li><a href="#">text4</a></li>
        </ul>
      </li>
    </ul>
  </aside>
  <!-- side bar End -->



  <div class="container">
    <div class="row">
      <div class="col-lg-12">
        <div class="page-content">
          
          <!-- ***** 진행중인 프로젝트 Start ***** -->
            <div class="row">
              <div class="col-lg-12">
                <div class="project-list header-text">
                  <div class="heading-section">
                    <h4><em>진행중인</em> 프로젝트</h4>
                    <hr><h6>인기순</h6><hr>
                  </div>
                  <div class="row">
                    <div class="col-lg-3 col-sm-6">
                      <div class="item">
                        <img src="assets/images/popular-01.jpg" alt="">
                        <h4>Fortnite   <span>Sandbox</span></h4>
                        <p>안녕하세요 반갑습니다.</p>
                        <p>&nbsp;dsadasd</p>
                        <p>dsadasdassaddas</p>
                      </div>
                    </div>
                    <div class="col-lg-3 col-sm-6">
                      <div class="item">
                        <img src="assets/images/popular-01.jpg" alt="">
                        <h4>Fortnite   <span>Sandbox</span></h4>
                        <p>안녕하세요 반갑습니다.</p>
                        <p>&nbsp;dsadasd</p>
                        <p>dsadasdassaddas</p>
                      </div>
                    </div>
                    <div class="col-lg-3 col-sm-6">
                      <div class="item">
                        <img src="assets/images/popular-01.jpg" alt="">
                        <h4>Fortnite   <span>Sandbox</span></h4>
                        <p>안녕하세요 반갑습니다.</p>
                        <p>&nbsp;dsadasd</p>
                        <p>dsadasdassaddas</p>
                      </div>
                    </div>
                    <div class="col-lg-3 col-sm-6">
                      <div class="item">
                        <img src="assets/images/popular-01.jpg" alt="">
                        <h4>Fortnite   <span>Sandbox</span></h4>
                        <p>안녕하세요 반갑습니다.</p>
                        <p>&nbsp;dsadasd</p>
                        <p>dsadasdassaddas</p>
                      </div>
                    </div>


                    <div class="col-lg-3 col-sm-6 project-list-container"></div>
                    <div class="back-drop">##</div>

                  </div>
                </div>
              </div>
            </div>
          <!-- ***** 진행중인 프로젝트 End ***** -->
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



  <!-- Bootstrap core JavaScript -->
  <script src="vendor/jquery/jquery.min.js"></script>
  <script src="vendor/bootstrap/js/bootstrap.min.js"></script>

  <script src="assets/js/isotope.min.js"></script>
  <script src="assets/js/owl-carousel.js"></script>
  <script src="assets/js/tabs.js"></script>
  <script src="assets/js/popup.js"></script>
  <script src="assets/js/custom.js"></script>


</body>
</html>