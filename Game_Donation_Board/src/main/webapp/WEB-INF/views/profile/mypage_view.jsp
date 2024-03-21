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
    <link rel="stylesheet" href="resources/assets/css/side-bar.css">
    <link rel="stylesheet"href="https://unpkg.com/swiper@7/swiper-bundle.min.css"/>
    <link rel="stylesheet" href="https://fonts.googleapis.com/css?family=Montserrat:400,800">
<style type="text/css">
 a span:hover {
display: block;

  	border-bottom: 2px solid white;
}

/*정진수*/
/* 모달 스타일 */
        .modal {
            display: none; /* 초기에는 숨김 */
            position: fixed;
            z-index: 1;
            left: 0;
            top: 0;
            width: 100%;
            height: 100%;
            overflow: auto;
            background-color: rgba(0,0,0,0.4); /* 배경에 어둡게 */
        }
        .modal-content {
            background-color: #1f2122;
            margin: 15% auto; /* 모달을 수직 가운데 정렬 */
            padding: 20px;
            border: 1px solid #888;
            width: 80%;
            max-width: 400px;
        }
        .close {
            color: #aaa;
            float: right;
            font-size: 28px;
            font-weight: bold;
        }
        .close:hover,
        .close:focus {
            color: black;
            text-decoration: none;
            cursor: pointer;
        }

        .image-container {
       width: 150px;
        height: 150px;
        overflow: hidden;
        border-radius: 100px;
      }

      .image-container img {
        width: 100%;
        height: 100%;
      }
      
      #user_img_view{
        border-radius: 100px;
      }
	
	 #user_img_setting_container{
	 	display: flex;
   		justify-content: space-between;
	 }
	 
	 #button-container{
	    display: flex;
	    flex-direction: column;
	    justify-content: space-around;
	 }
</style>
<body>
	
	<!-- 모달 -->
	<div id="myModal" class="modal">
    
	  <div class="modal-content">
	    <span class="close">&times;</span>
        <div id="user_img_setting_container">
          <div class="image-container">
            <img
              src="${dto.user_img}"
              class="img-rounded"
              id="user_img_show"
            />
          </div>
          <div id="button-container">
	                   		
	          <div class="main-border-button">
	          	<a href="javascript: js(); event.preventDefault();"><label for="user_img">이미지 선택</label></a>
	          	<input
                      type="file"
                      name="user_img"
                      id="user_img"
                      onchange="user_image_send()"
                      style="display: none;"
                    />
                    
	          </div>
	          <form>
			      <input
			                type="hidden"
			                name="user_main_img"
			                id="user_main_img"
			      />
		          <div class="main-border-button">
		          
		            <a href="javascript: js(); event.preventDefault();"><label for="user_update" id="file_label">선택완료</label></a>
		            <input
                      type="button"
                      onclick="user_img_update(this.form)"
                      id="user_update"
                      style="display: none;"
                    />
		          </div>	
			  </form>
          </div>
        </div>
	  </div>
	  
	</div>
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
                    <img src="${dto.user_img}" style="border-radius: 150px; width: 300px; height: 300px;" id="user_img_view">
                  </div>
                  <div class="col-lg-4 align-self-center">
                    <div class="main-info header-text">
                      
                      <h4>${dto.user_name}님 환영합니다.</h4>
                      <p>후원한 목록과 내 프로젝트를 관리하세요</p>
                      <div class="main-border-button">
                        <a href="#" id="openModalBtn">대표 이미지 수정하기</a>
                        <a href="javascript: js(); event.preventDefault();" onclick="user_img_delete()">이미지 삭제하기</a>
                      </div>
                    </div>
                  </div>
                  <div class="col-lg-4 align-self-center">
            		<ul>
                      <li>
                      	내 정보<a href="myinfo_view"><span>수정하기</span></a>
                      </li>
                      <li>
                        후원한 프로젝트 내역 <a href="sponsorshipdetails_view?user_idx=${user_email.user_idx}"><span>${sponsored_Project_Details}개</span></a>
                      </li>
                       <li>
                        등록한 프로젝트<a href="registered_Project?user_idx=${user_email.user_idx}"><span>${registered_project}개</span></a>
                      </li>
                      <li>
                       리뷰<a href="review?user_idx=${user_email.user_idx}"><span>${reviewCount}개</span></a>
                      </li>
                      <li>
                        잔여 포인트 <span id="remaining-points">${user_email.user_point}</span>
                      </li>
                      <li>
                        <a href="javascript: js(); event.preventDefault();" onclick="charge_popup()"><span>충전하기</span></a>
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
  
    <!-- jQuery -->
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
  <!--<script src="resources/assets/bootstrap/js/bootstrap.min.js"></script> -->
  <script src="resources/assets/js/isotope.min.js"></script>
  <script src="resources/assets/js/owl-carousel.js"></script>
  <script src="resources/assets/js/tabs.js"></script>
  <script src="resources/assets/js/popup.js"></script>
  <script src="resources/assets/js/custom.js"></script>
  <script type="text/javascript">

      //페이지 로드 시 잔여 포인트를 가져오기
      //document.addEventListener("DOMContentLoaded", fetchRemainingPoints);
      
    
      
      window.onload = function(){
	 	  // 모달 열기 버튼 클릭 시 이벤트 핸들링
	      document.getElementById("openModalBtn").onclick = function() {
	          document.getElementById("myModal").style.display = "block";
	      }
	
	      // 모달 닫기 버튼 클릭 시 이벤트 핸들링
	      document.getElementsByClassName("close")[0].onclick = function() {
	          document.getElementById("myModal").style.display = "none";
	      }
	
	      // 모달 외부 영역 클릭 시 모달 닫기
	      window.onclick = function(event) {
	          var modal = document.getElementById("myModal");
	          if (event.target == modal) {
	              modal.style.display = "none";
	          }
	      }
	      
	      
	      
	      
	      
      }//  window.onload = function()닫는부분
      
    //메인이미지(썸네일) 등록 ajax함수
      function user_image_send() {
        var preview = new FileReader();
        preview.onload = function (e) {
          // img id 값
          document.getElementById("user_img_show").src = e.target.result;
        };
        // input id 값
        preview.readAsDataURL(document.getElementById("user_img").files[0]);

        data = new FormData();

        data.append("file", document.getElementById("user_img").files[0]);

        $.ajax({
          data: data,
          type: "POST",
          url: "user_img_upload",
          contentType: false,
          enctype: "multipart/form-data",
          processData: false,
          success: function (data) {
            console.log(data.url);
            document.getElementById("user_main_img").value = data.url;
          },
        });
      }
      
      function user_img_update(f){
    	  console.log(f);
    	  const user_img = f.user_main_img.value;
    	  
    	  if(user_img==""){
    		  alert("이미지를 등록해주세요");
    		  return;
    	  }
    	  
    	  
    	  f.action = "user_img_update";
    	  f.method = "POST";
    	  f.submit();
      }
      
      function user_img_delete(){
    	  var result = confirm("대표이미지를 삭제하시겠습니까?");
    	  
    	  if(result){
    		location.href="user_img_delete";  
    	  }
      }
      
      function charge_popup(){
    	  var url = "charge_view";
          var name = "charge_view";
          var option = "width=500,height=500,location=no";

	       // 팝업창을 가운데에 위치시키기 위해 창의 좌표를 계산합니다.
	       var left =  Math.ceil((window.screen.width) / 2)-250;
	       var top =  Math.ceil((window.screen.height) / 2)-250;
	
	       // 좌표를 옵션에 추가합니다.
	       option += ",top=" + top + ",left=" + left;
	       
          window.open(url, name, option);
      }
   
  </script>

  </body>
</html>
