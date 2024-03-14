<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="en">

  <head>

    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">

    <link href="https://fonts.googleapis.com/css2?family=Poppins:wght@100;200;300;400;500;600;700;800;900&display=swap" rel="stylesheet">

    <title>Cyborg - Awesome HTML5 Template</title>

    <!-- Bootstrap core CSS -->
    <link href="resources/vendor/bootstrap/css/bootstrap.min.css" rel="stylesheet">


    <!-- Additional CSS Files -->
    <link rel="stylesheet" href="resources/assets/css/fontawesome.css">
    <link rel="stylesheet" href="resources/assets/css/templatemo-cyborg-gaming.css">
    <link rel="stylesheet" href="resources/assets/css/owl.css">
    <link rel="stylesheet" href="resources/assets/css/animate.css">
    <link rel="stylesheet"href="https://unpkg.com/swiper@7/swiper-bundle.min.css"/>

<style>
.image-container {
  width: 100%;
  height: 376px;
  overflow: hidden;
}

.image-container img {
  width: 100%;
  height: 100%;
}
</style>

<script>
	/* wanted : (프로젝트 내용, 공지, 리뷰) = (0,1,2) */
	let wanted = 0;
	
	const GetFunction = function(wanted){
		console.log("원하는 content : " + wanted)
		
		$.ajax({
			url : "ajax_detail",
			method : "GET",
			data : {
				wanted : wanted
			},
			success:function(data){
				console.log("ajax_detail data : " + data);
				
				$(".detail_content").append(data);
				console.log("ajax_detail 잘 넘어옴");
			}
		})
	};
</script>


</head>
<body>

  <%@ include file= "/WEB-INF/views/board/menu.jsp" %>
  <!-- ***** Header Area End ***** -->

  <div class="container">
    <div class="row">
      <div class="col-lg-12">
        <div class="page-content">

          <!-- ***** Detail Start ***** -->
          <div class="row">
            <div class="col-lg-12">
              <div class="main-profile ">
                <div class="row">
                  <div class="col-lg-4">
                  	<div class="image-container">
                    	<img src="${dto.img }" alt="" style="border-radius: 23px;">
                    </div>
                  </div>
                  <div class="col-lg-4">
                    <div class="main-info header-text">
                      <h4>${dto.title }</h4>
                      <h6>&nbsp;${dto.author }</h6>
                      <br>
                      <p>카테고리 1, 카테고리 2, 카테고리 3, 카테고리 4, 카테고리 5, ...</p>
                      <div class="main-border-button">
                        <a href="#">프로젝트 후원하기</a>
                      </div>
                    </div>
                  </div>
                  <div class="col-lg-4">
                    <ul>
                      <li>모인금액 <span>${dto.donation } 원 ( ${dto.persent} )</span></li>
                      <li>남은기간 <span>${dto.diff_date }</span></li>
                      <li>목표금액 <span>${dto.target }</span></li>
                      
                      <li>펀딩 기간 <span>${dto.start } ~ ${dto.end }</span></li>
                      <li>
                      	<div class="main-border-button">
	                        <a>좋아요<span>&nbsp; ${dto.like_cnt }</span> </a>
	                        <a>알람</a>
	                        <a>신고</a>
                        </div>
                      </li>
                    </ul>
                  </div>
                </div>
                <div class="row">
                  <div class="col-lg-12">
                    <div class="clips">
                      <div class="row">
                        <div class="col-lg-12">
                          <div class="heading-section">
                            <h4><em>EZ-FUNDING</em> 프로젝트</h4>
                          </div>
                        </div>
                        <div class="col-lg-12">
                          <div class="main-border-button" id="detail-box">
                            <a href="#detail-box" onclick="show_content(0)">프로젝트 내용</a>
                            <a href="#detail-box" onclick="show_notice(1)">공지</a>
                            <a href="#detail-box" onclick="show_review(2)">리뷰</a>
                          </div>
                        </div>
                        <hr><hr><hr><hr>
                        <div class="col-lg-12">
                          <!-- 프로젝트 내용 Start -->
                          <div class="row">
                            <div class="detail-content" align="center"></div>
                          </div>
                          <!-- 프로젝트 내용 End -->
                        </div>
                      </div>
                    </div>
                  </div>
                </div>
              </div>
            </div>
          </div>
          <!-- ***** Detail End ***** -->
          
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
  <script src="resources/vendor/jquery/jquery.min.js"></script>
  <script src="resources/vendor/bootstrap/js/bootstrap.min.js"></script>

  <script src="resources/assets/js/isotope.min.js"></script>
  <script src="resources/assets/js/owl-carousel.js"></script>
  <script src="resources/assets/js/tabs.js"></script>
  <script src="resources/assets/js/popup.js"></script>
  <script src="resources/assets/js/custom.js"></script>


  </body>

</html>
