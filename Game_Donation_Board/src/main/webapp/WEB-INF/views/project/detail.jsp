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
    
    <script src="https://code.jquery.com/jquery-3.4.1.js"></script>

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

.heart img{
  width: 16px;
  height: 16px;
  overflow: hidden;
}

.modal{
	position:absolute;
	display:none;
         
	justify-content: center;
	top:0;
	left:0;
	width:100%;
	height:100%;

	background-color: rgba(0,0,0,0.4);
}

.modal-body{
    position:absolute;
    top:50%; 
            
    width:400px;
    height:700px;

    padding:40px;  

    text-align: center;

    background-color: #1F2122;
    border-radius:10px;
    box-shadow:0 2px 3px 0 rgba(34,36,38,0.15);

    transform:translateY(-50%);
}

.form-label{
	color:#ec6090;
}

.donation-btn{
    font-size: 14px;
    color: #ec6090;
    background-color: transparent;
    border: 1px solid #ec6090;
    padding: 12px 30px;
    display: inline-block;
    border-radius: 25px;
    font-weight: 400;
    text-transform: capitalize;
    letter-spacing: 0.5px;
    transition: all .3s;
    position: relative;
    overflow: hidden;
}


.review-register {
    display: flex;
    -webkit-box-align: center;
    align-items: center;
    -webkit-box-pack: justify;
    justify-content: space-between;
    width: 100%;
    height: 60px;
    margin: 14px 0px 0px;
    padding: 0px 20px;
    background: #1f2122;
    border: 1px solid rgb(240, 240, 240);
    border-radius: 4px;
    box-sizing: border-box;
    box-shadow: rgba(0, 0, 0, 0.04) 0px 4px 12px, rgba(0, 0, 0, 0.03) 0px 0.8px 0px;
    font: 400 14px / 24px "Noto Sans KR";
    color: rgb(158, 158, 158);
}

.review-register-btn{
	width: 32px;
    height: 32px;
}

.item {
    padding: 28px 16px 16px 16px;
}

.user-row {
    display: flex;
    justify-content: space-between;
    width: 100%;
    -webkit-box-align: center;
    align-items: center;
    gap: 10px;
    flex: 1 0 auto;
    max-width: 100%;
}

.img {
    display: inline-block;
    background-size: cover;
    background-position: 50% 38%;
    width: 36px;
    height: 36px;
    border-radius: 50%;
    box-shadow: rgb(208, 208, 208) 0px 0px 1px 0px inset, rgb(208, 208, 208) 0px 0px 1px 0px;
    margin-right: 0px;
    margin-top: 5px;
}

.regdate-row {
    display: flex;
    -webkit-box-pack: justify;
    justify-content: space-between;
    align-items: flex-end;
}


</style>

<script>
	/* wanted : (프로젝트 내용, 리뷰) = (0,1) */
	let wanted = 0;
	let heart_check = 0;
	
	const GetDetail = function(wanted){
		/* console.log("원하는 content : " + wanted) */
		
		$.ajax({
			url : "detail_ajax",
			method : "GET",
			data : {
				wanted : wanted,
				project_idx : ${dto.project_idx}
			},
			success:function(data){
				/* console.log("ajax_detail data : " + data); */
				
				$(".detail-content").empty();
				$(".detail-content").append(data);
				/* console.log("ajax_detail 잘 넘어옴"); */
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
	
	
	// 하트 이벤트
	function heart(n){
		heart_check = n;
		/* console.log("heart : " + heart_check); */
		
		$.ajax({
			url : "heart_ajax",
			method : "GET",
			data : {
				project_idx : ${dto.project_idx},
				heart_check : heart_check
			},
			success : function(data){
				/* console.log(data); */
				
				let new_heart = data.heart;
				
				// 꽉 찬 하트를 클릭시, 빈 하트로 바꿔주기
				if(n == -1){
					$('.heart > a').attr('onclick', "heart(1)"); // 메서드 변경
					$('.heart > a > img').attr('src', "resources/img/icons8.png"); // 빈 하트 이미지
					$('.like_cnt').html("&nbsp; "+new_heart); // 하트 수 갱신
				// 빈 하트 클릭시, 꽉 찬 하트로 바꿔주기
				}else if(n == 1){
					$('.heart > a').attr('onclick', "heart(-1)"); // 메서드 변경
					$('.heart > a > img').attr('src', "resources/img/icons8-heart-fill.png"); // 꽉찬 하트 이미지
					$('.like_cnt').html("&nbsp; "+new_heart); // 하트 수 갱신
				};
			}
		})
	};
	
	function donation(){
		
		let idx = document.getElementById('user_idx').value;
		let diff_date = document.getElementById('diff_date').value;
		
		if(idx == ''){
			alert("로그인이 필요한 서비스입니다.");
			return;
		}
		
		if(diff_date == '마감' || diff_date == '진행예정'){
			alert("프로젝트 후원기간에 해당하지 않습니다.");
			return;
		}
		
		document.querySelector('.modal').style.display="flex";
	};
	
	function donation_check(f){
		let idx = f.user_idx.value;
		let point = f.user_point.value;
		let money = f.point_donation.value;
		
		let regex = /^[0-9]*$/;
		
		if(money == ''){
			alert("후원할 포인트를 입력하세요.");
			return;
		};
		
		if(money <= 0){
			alert("0 point 이상 후원할 수 있습니다.");
			return;
		}
		
		if(!regex.test(money)){
			alert("숫자만 입력하세요.");
			return;
		}
		
		if(money > point){
			alert("보유한 포인트보다 많이 후원할 수 없습니다.");
			return;
		};
		
		f.action = "donation?user_idx="+idx+"&use_point="+money+"&project_idx="+${dto.project_idx}+"&user_point="+point;
		f.method = "POST";
		f.submit();
		
	};
	
	
	function cancle(){
		document.querySelector('.modal').style.display="none";
	};
	
	
	function register_review(){
		
		let input_content = document.getElementById("review-content").value;
		let user_idx = document.getElementById("user_idx").value;
		
		if(user_idx == ''){
			alert("로그인 후 이용해주세요");
			return;
		}
		
		if(input_content == ''){
			alert("리뷰 내용을 작성하여 등록해주세요.");
			return;
		}
		
		$.ajax({
			url : "review_register",
			method : "POST",
			data : {
				user_idx : user_idx,
				project_idx : ${dto.project_idx},
				input_content : input_content
			},
			success : function(){
				GetDetail(1);
			}
		})
	};
	
	
	
</script>


</head>
<body>

  <%@ include file= "/WEB-INF/views/board/menu.jsp" %>

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
                      	<p style="color:#999;">
                      <c:forEach var="category_name" items="${dto.category_name}" >
                      	#${category_name }&nbsp;
                      </c:forEach>
                      	</p>
                      <div class="main-border-button">
                        <a href="javascript:void(0)" onclick="donation()">프로젝트 후원하기</a>
                      </div>
                    </div>
                  </div>
                  <div class="col-lg-4">
                    <ul>
                      <li>모인금액 <span>${dto.donation_str } &#8361; ( ${dto.persent} )</span></li>
                      <li>남은기간 <span>${dto.diff_date }</span></li>
                      <li>목표금액 <span>${dto.target_str } &#8361;</span></li>
                      
                      <li>펀딩 기간 <span>${dto.start } ~ ${dto.end }</span></li>
                      <li>
                      
                      	<!-- 하트 버튼, 좋아요 숫자 Start -->
                      	<div class="main-border-button heart">
                      		<c:choose>
                      			<c:when test="${user_email.user_idx ne null }">
                      				<c:choose>
                      					<c:when test="${dto.is_heart eq 1}">
                      						<a href="javascript:void(0)" onclick="heart(-1)" >
                      							<img src="resources/img/icons8-heart-fill.png" style=>
                      							<span class="like_cnt">&nbsp; ${dto.like_cnt }</span>
		                      				</a>
                      					</c:when>
                      					<c:otherwise>
                      						<a href="javascript:void(0)" onclick="heart(1)" >
                      							<img src="resources/img/icons8.png">
                      							<span class="like_cnt">&nbsp; ${dto.like_cnt }</span>
		                      				</a>		
                      					</c:otherwise>
                      				</c:choose>	
                      			</c:when>
                      			<c:otherwise>
                      				<a href="javascript:void(0)">
                      					<img src="resources/img/icons8.png">
                      					<span class="like_cnt">&nbsp; ${dto.like_cnt }</span>
                      				</a>
                      			</c:otherwise>
                      		</c:choose>
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
  
  <div class="modal">
  	<div class="modal-body">
  		 <form>
         	 <div class="image-container">
               	<img src="${dto.img }" alt="" style="border-radius: 23px;">
             </div>
             <div class="mb-3">
    	         <label class="form-label">프로젝트 명</label>
	             <input class="form-control form-control-sm" id="project_title" value="${dto.title }" readonly>
             </div>
             <!-- 상품 이름 -->
             <div class="mb-3">
    	         <label class="form-label">프로젝트 팀</label>
	             <input class="form-control form-control-sm" id="project_author" value="${dto.author }" readonly>
             </div>
             <!-- 상품 가격 -->
             <div class="mb-3">
                 <label class="form-label">후원 포인트</label>
                 <input class="form-control form-control-sm" id="point_donation" placeholder="후원 가능한 포인트 : ${point_str } point">
             </div>
             <!-- 히든 인풋 -->
             <input type="hidden" id="user_point" value="${user_email.user_point }">
             <input type="hidden" id="user_idx" value="${user_email.user_idx }">
             <input type="hidden" id="diff_date" value="${dto.diff_date }">
             <!-- 전송 버튼 -->
             <button class="donation-btn" type="button" onclick="donation_check(this.form)">후원하기</button>
	         <button class="donation-btn" type="button" onclick="cancle()">취소</button>
         </form>
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
