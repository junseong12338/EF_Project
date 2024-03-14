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
	
	const GetDetail = function(wanted){
		console.log("원하는 content : " + wanted)
		
		$.ajax({
			url : "detail_ajax",
			method : "GET",
			data : {
				wanted : wanted,
				project_idx : ${dto.project_idx}
			},
			success:function(data){
				console.log("ajax_detail data : " + data);
				
				$(".detail_content").empty();
				$(".detail_content").append(data);
				console.log("ajax_detail 잘 넘어옴");
			}
		})		
	};
	
	
	function show_content(n){
		wanted = n;
		GetDetail(wanted);
	}

	function show_review(n){
		wanted = n;
		GetDetail(wanted);
	}
	
	$(document).ready(function(){
		GetDetail(0);
	})
	
	// 로그인 했을 때의 a태그 클래스 : heart-click
	$(".heart-click").unbind("click");
	$(".heart-click").click(function() {
		console.log("heart-click");
		
		// 빈 하트 클릭
		if($(this).children('img').attr('id') == "heart-not-fill"){
			console.log("빈 하트 클릭");
			
			$.ajax({
				url : "heart_plus_ajax",
				method : "GET",
				data : {
					project_idx : ${dto.project_idx}
				},
				success : function(data){
					
					let heart = data[0].is_heart
					
					// 하트 수 갱신
					$("#heart").text(heart);
					
					console.log("하트추가 성공 : " + heart);
				}
			})
			
			// 하트를 바꾸자
			$(this).html("<img id='heart-fill' alt='꽉 찬 하트' src='resources/img/icons8-heart-fill.png' style='wigth: 16px; height: 16px'>좋아요");
			
		// 꽉찬 하트 클릭
		}else if($(this).children('img').attr('id') == "heart-fill"){
			console.log("꽉찬 하트 클릭");
			
			$.ajax({
				url : "heart_minus_ajax",
				method : "GET",
				data : {
					project_idx : ${dto.project_idx},
					like_cnt : ${dto.like_cnt}
				},
				success : function(data){
					
					let heart = data[0].is_heart
					
					// 하트 수 갱신
					$("#heart").text(heart);
					
					console.log("하트빼기 성공 : " + heart);
				}
			})
			
			// 하트를 바꾸자
			$(this).html("<img id='heart-not-fill' alt='빈 하트' src='resources/img/icons8-heart.png' style='wigth: 16px; height: 16px'>좋아요");
			
		}
	})
	
	
	
	
	
	
	
	
	
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
                      <c:forEach var="category_name" items="list">
                      	<p>${category_name }</p>
                      </c:forEach>
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
                      
                      	<!-- 하트 버튼, 좋아요 숫자 Start -->
                      	<div class="main-border-button heart">
	                  
	                        <!-- 로그인 체크 -->
                      		<c:choose>
                      			<!-- 로그인 시 -->
                      			<c:when test="${user_email.user_idx ne null }">
                      			
                      				<!-- 내가 좋아요를 누른 프로젝트인가 ? -->
                      				<c:choose>
                      					<!-- yes : 1 -->
                      					<c:when test="${dto.is_heart eq 1}">
                      						<a href="javascript:void(0)" class="heart-click" >
                      							<img id="heart-fill" alt="꽉 찬 하트" src="resources/img/icons8-heart-fill.png" style="wigth: 16px; height: 16px">좋아요
		                      				</a>
                      					</c:when>
                      					<!-- no : 0 -->
                      					<c:otherwise>
                      						<a href="javascript:void(0)" class="heart-click" >
                      							<img id="heart-not-fill" alt="빈 하트" src="resources/img/icons8-heart.png" style="wigth: 16px; height: 16px">좋아요
		                      				</a>		
                      					</c:otherwise>
                      				</c:choose>	
                      			</c:when>
                      			
                      			<!-- 비 로그인 시 -->
                      			<c:otherwise>
                      				<a href="javascript:void(0)" class="heart-not-click">
                      					<img alt="빈 하트" src="resources/img/icons8-heart.png" style="wigth: 16px; height: 16px">좋아요
                      				</a>
                      			</c:otherwise>
                      		</c:choose>
                      		<span id="heart">&nbsp; ${dto.like_cnt }</span>
                        </div>
                        <!-- 하트 버튼, 좋아요 숫자 End -->
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
                            <a href="#detail-box" onclick="show_review(1)">리뷰</a>
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
