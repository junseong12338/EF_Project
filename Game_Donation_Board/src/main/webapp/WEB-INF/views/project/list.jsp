<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
<title>Project-List</title>

    <!-- Bootstrap core CSS -->
    <link href="resources/vendor/bootstrap/css/bootstrap.min.css" rel="stylesheet">


    <!-- Additional CSS Files -->
    <link rel="stylesheet" href="resources/assets/css/fontawesome.css">
    <link rel="stylesheet" href="resources/assets/css/templatemo-cyborg-gaming.css">
    <link rel="stylesheet" href="resources/assets/css/owl.css">
    <link rel="stylesheet" href="resources/assets/css/animate.css">
    <link rel="stylesheet"href="https://unpkg.com/swiper@7/swiper-bundle.min.css"/>

	<link rel="stylesheet" href="resources/css/project-list.css">
    <link rel="stylesheet" href="resources/assets/css/side-bar.css">

	<script src="https://code.jquery.com/jquery-3.4.1.js"></script>
	
<style>
	
.side-bar ul > li > a {
  font-size: 0.8rem;
  padding-left: 30px;
}

.image-container {
  width: 100%;
  height: 300px;
  overflow: hidden;
}

.image-container img {
  width: 100%;
  height: 100%;
}
</style>
	
<script>
//스크롤 이벤트 처리
let currentPage = 1;
let total_page_count = 1;
let category = [0,0,0,0,0,0,0,0,0,0];
let sort = 0;
let sort_date = 0;

$(window).on("scroll",function(){
    let scrollTop = $(window).scrollTop();// 위로 스크롤된 길이
    let windowHeight = $(window).height();// 웹브라우저 창의 높이
    let documentHeight = $(document).height();// 문서 전체의 높이
    let isBottom = scrollTop + windowHeight >= documentHeight;// 스크롤이 바닥에 닿을 때

    if(isBottom){
        if(currentPage == total_page_count){
            return;
        }

        currentPage++;
        
        console.log("스크롤 - 현재페이지 : " + currentPage);

        // 추가로 받아올 페이지를 서버에 Ajax 요청
        GetList(currentPage, sort, sort_date, category);
    };
});

// list 가져오기
const GetList = function(currentPage, sort, sort_date, category){
    console.log("리스트 - 현재페이지 : " + currentPage);
    console.log("스크롤 - 정렬1 : " + sort);
    console.log("스크롤 - 정렬2 : " + sort_date);

    // 무한 스크롤
    $.ajax({
        url : "ajax_list",
        method : "GET",
        data : {
        	pageNum : currentPage,
        	sort : sort,
        	sort_date : sort_date,
        	category_box : category
        },
        success:function(data){
            console.log("ajax 데이터 : " + data);

            $(".project-list-container").append(data);
            console.log("ajax 잘넘어옴");
            
            // hidden 으로 total_page_count 받아오기
            total_page_count = document.getElementById("total_page").value;
        }
    });
};

// 로딩시 1페이지 실행
$(document).ready(function(){
	GetList(1, 0, 0, [0,0,0,0,0,0,0,0,0,0]);
})



// select box Event

function send_sort(value){
	sort = value;
	
	$(".project-list-container").empty();
	GetList(1, sort, sort_date, category);
}

function send_sort_date(value){
	sort_date = value;
	
	$(".project-list-container").empty();
	GetList(1, sort, sort_date , category);
}


function send(n){
	
	if(category[n-1] == 0){
		category[n-1] = n;
		
		if(n == 1){
			document.getElementById("img1").style.display = "block";
		}else if(n == 2){
			document.getElementById("img2").style.display = "block";
		}else if(n == 3){
			document.getElementById("img3").style.display = "block";
		}else if(n == 4){
			document.getElementById("img4").style.display = "block";
		}else if(n == 5){
			document.getElementById("img5").style.display = "block";
		}else if(n == 6){
			document.getElementById("img6").style.display = "block";
		}else if(n == 7){
			document.getElementById("img7").style.display = "block";
		}else if(n == 8){
			document.getElementById("img8").style.display = "block";
		}else if(n == 9){
			document.getElementById("img9").style.display = "block";
		}else if(n == 10){
			document.getElementById("img10").style.display = "block";
		}else{
			return;
		}
		
	} else{
		category[n-1] = 0;
		
		if(n == 1){
			document.getElementById("img1").style.display = "none";
		}else if(n == 2){
			document.getElementById("img2").style.display = "none";
		}else if(n == 3){
			document.getElementById("img3").style.display = "none";
		}else if(n == 4){
			document.getElementById("img4").style.display = "none";
		}else if(n == 5){
			document.getElementById("img5").style.display = "none";
		}else if(n == 6){
			document.getElementById("img6").style.display = "none";
		}else if(n == 7){
			document.getElementById("img7").style.display = "none";
		}else if(n == 8){
			document.getElementById("img8").style.display = "none";
		}else if(n == 9){
			document.getElementById("img9").style.display = "none";
		}else if(n == 10){
			document.getElementById("img10").style.display = "none";
		}else{
			return;
		}
		
		
	};
	
	
	$(".project-list-container").empty();
	GetList(1, sort, sort_date, category);
}

</script>
</head>
<body>

  
  <!-- ***** Preloader End ***** -->

  <!-- ***** Header Area Start ***** -->
  <%@ include file= "/WEB-INF/views/board/menu.jsp" %>
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
      	<a href="project_list">Reset</a>
      </li>
      <li>
        <a href="#" onclick="send(1)" class="category" style="height: 50px; padding-top: 15px; padding-bottom: 15px; padding-right:50px;">1인칭
            <img id="img1" src="resources/assets/images/check.png" style="height: 30px; width: 30px; display:none;" align="right">
        </a>
      </li>
      <li>
        <a href="#" onclick="send(2)" class="category"  style="height: 50px; padding-top: 15px; padding-bottom: 15px; padding-right:50px;">레이싱
            <img id="img2" src="resources/assets/images/check.png" style="height: 30px; width: 30px; display:none;" align="right">
        </a>
      </li>
      <li>
        <a href="#" onclick="send(3)" class="category"  style="height: 50px; padding-top: 15px; padding-bottom: 15px; padding-right:50px;">생존
            <img id="img3" src="resources/assets/images/check.png" style="height: 30px; width: 30px; display:none;" align="right">
        </a>
      </li>
      <li>
        <a href="#" onclick="send(4)" class="category" style="height: 50px; padding-top: 15px; padding-bottom: 15px; padding-right:50px;">슈팅
            <img id="img4" src="resources/assets/images/check.png" style="height: 30px; width: 30px; display:none;" align="right">
        </a>
      </li>
      <li>
        <a href="#" onclick="send(5)" class="category" style="height: 50px; padding-top: 15px; padding-bottom: 15px; padding-right:50px;">스포츠
            <img id="img5" src="resources/assets/images/check.png" style="height: 30px; width: 30px; display:none;" align="right">
        </a>
      </li>
      <li>
        <a href="#" onclick="send(6)" class="category" style="height: 50px; padding-top: 15px; padding-bottom: 15px; padding-right:50px;">액션
            <img id="img6" src="resources/assets/images/check.png" style="height: 30px; width: 30px; display:none;" align="right">
        </a>
      </li>
      <li>
        <a href="#" onclick="send(7)" class="category" style="height: 50px; padding-top: 15px; padding-bottom: 15px; padding-right:50px;">오픈 월드
            <img id="img7" src="resources/assets/images/check.png" style="height: 30px; width: 30px; display:none;" align="right">
        </a>
      </li>
      <li>
        <a href="#" onclick="send(8)" class="category" style="height: 50px; padding-top: 15px; padding-bottom: 15px; padding-right:50px;">전략
            <img id="img8" src="resources/assets/images/check.png" style="height: 30px; width: 30px; display:none;" align="right">
        </a>
      </li>
      <li>
        <a href="#" onclick="send(9)" class="category" style="height: 50px; padding-top: 15px; padding-bottom: 15px; padding-right:50px;">전투
            <img id="img9" src="resources/assets/images/check.png" style="height: 30px; width: 30px; display:none;" align="right">
        </a>
      </li>
      <li>
        <a href="#" onclick="send(10)" class="category" style="height: 50px; padding-top: 15px; padding-bottom: 15px; padding-right:50px;">타워 디펜스
            <img id="img10" src="resources/assets/images/check.png" style="height: 30px; width: 30px; display:none;" align="right">
        </a>
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
                  <h4><em>EZ-FUNDING</em> 프로젝트</h4>
                  
                  <!-- select 박스 Start -->
                  <div>
                      <select id="select_sort" onchange="send_sort(this.value)">
                        <option value="0">인기순</option>
                        <option value="1">최신순</option>
                        <option value="2">최대 후원순</option>
                      </select>
                      <select id="select_sort_date" onchange="send_sort_date(this.value)">
                      	<option value="0">전체보기</option>
                        <option value="1">진행중</option>
                        <option value="2">진행예정</option>
                        <option value="3">마감</option>
                      </select>
                  </div>
                  

                  <!-- select 박스 End -->
                </div>  
                
              	<!-- project-list Start -->
				<div class="row project-list-container"></div>
                <!-- project-list End -->

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
  <script src="resources/vendor/jquery/jquery.min.js"></script>
  <script src="resources/vendor/bootstrap/js/bootstrap.min.js"></script>

  <script src="resources/assets/js/isotope.min.js"></script>
  <script src="resources/assets/js/owl-carousel.js"></script>
  <script src="resources/assets/js/tabs.js"></script>
  <script src="resources/assets/js/popup.js"></script>
  <script src="resources/assets/js/custom.js"></script>


</body>
</html>