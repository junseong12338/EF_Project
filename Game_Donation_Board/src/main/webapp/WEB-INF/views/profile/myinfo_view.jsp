<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <title>Cyborg - Awesome HTML5 Template</title>
    
    <link href="resources/css/bootstrap.min.css" rel="stylesheet">
    <link rel="stylesheet" href="resources/css/fontawesome.css">
    <link rel="stylesheet" href="resources/css/templatemo-cyborg-gaming.css">
    <link rel="stylesheet" href="resources/css/owl.css">
    <link rel="stylesheet" href="resources/css/animate.css">
    <link rel="stylesheet"href="https://unpkg.com/swiper@7/swiper-bundle.min.css"/>
    <link href="https://fonts.googleapis.com/css2?family=Poppins:wght@100;200;300;400;500;600;700;800;900&display=swap" rel="stylesheet">

    <style> 
 input[type="text"] {
            background-color: white; /* 투명 배경색 */
            border: 1px solid black; /* 테두리 설정 */
            border-radius: 5px; /* 테두리 둥글게 만듦 */
            padding: 5px; /* 입력 칸 내부 여백 */
            margin-bottom: 10px; /* 입력 칸 간격 조정 */
            color: black; /* 텍스트 색상을 흰색으로 설정 */
        }

        #nickname{
          width: 25%; /* 사용자 이름 입력 칸 너비를 70%로 설정 */
        }

        input[type="email"] {
            background-color: white; /* 투명 배경색 */
            border: 1px solid black; /* 테두리 설정 */
            border-radius: 5px; /* 테두리 둥글게 만듦 */
            padding: 5px; /* 입력 칸 내부 여백 */
            margin-bottom: 10px; /* 입력 칸 간격 조정 */
            width: 30%; /* 이메일 주소 입력 칸 너비를 설정 */
            color: black; /* 텍스트 색상을 흰색으로 설정 */
            display: inline-block; /* 인라인 블록 요소로 변경 */
        }
        .shipping-address-1 {
            width:50%; 
        }

        .shipping-address-2 {
            width: 20%; 
        }
        
    label{
        color: white;
    }

    button[type="submit"] {
            padding: 10px 20px;
            border: none;
            border-radius: 5px;
            background-color: #bb4ab1; padding: 5px 10px;
            color: #fff;
            font-size: 16px;
            cursor: pointer;
            margin-left: 5px;
            margin-top: 20px;
        }

        button[type="submit"]:hover {
            background-color: #a400b3;
            transition: background-color 0.5s;
        }

        h2 {
            margin-bottom: 20px;
            color: white;
            display: inline-block;
        }

        .section {
            margin-bottom: 30px;
        }
        
           /* 프로필 설정과 사용자 닉네임 간격 조정 */
      #profile-settings {
        margin-bottom: 40px; /* 프로필 설정과 다음 섹션 사이의 간격 설정 */
      }
      
        /* 결제 수단 방법과 간격 조정 */
 	 #payment-settings {
  	  margin-bottom: 50px;
 	 }
        
    </style>

    
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
  
  <header class="header-area header-sticky">
    <div class="container">
        <div class="row">
            <div class="col-12">
                <nav class="main-nav">
                    <a href="index.html" class="logo">
                        <img src="resources/images/logo.png" alt="">
                    </a>
                    <div class="search-input">
                      <form id="search" action="#">
                        <input type="text" placeholder="검색" id='searchText' name="searchKeyword" onkeypress="handle" />
                        <i class="fa fa-search"></i>
                      </form>
                    </div>
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
                </nav>
            </div>
        </div>
    </div>
  </header>
  <div class="container">
    <div class="row">
      <div class="col-lg-12">
        <div class="page-content">
  			<form>
            <div id="profile-settings">
                <h2>프로필 설정</h2><br>
              
             <div class="form-group"> 
                    <label for="username">사용자 닉네임 </label>
                    <input type="text" id="username" name="user_name" class="nickname"><br><br>
                 </div>
               <div class="form-group">
                    <label for="email">이메일 주소 </label>
                    <input type="email" id="email" name="user_email" onblur="validateEmail(this)">
					<div id="email-error" style="color: red;"></div>
                  </div>   
            </div>
          		<!-- 배송지 설정 입력란 추가 -->
             <h2>주소 입력</h2><br><br>
	            <input type="text" name ="postal_code" id="sample4_postcode" placeholder="우편번호">
	            <input type="button" onclick="sample4_execDaumPostcode()" value="우편번호 찾기"><br>
	            <input type="text" name = "road_name" id="sample4_roadAddress" placeholder="도로명주소">
	            <input type="text" name = "detailed_address"id="sample4_detailAddress" placeholder="상세주소"><br>
	            <input type ="hidden" name = "user_addr"/>
	            <button onclick="addRess(this.form)">저장</button>
            </form>
                <!-- 배송지 설정 입력란 추가 -->
                
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

  <script src="resources/jquery/jquery.min.js"></script>
  <script src="resources/js/bootstrap.min.js"></script>
  <script src="resources/js/isotope.min.js"></script>
  <script src="resources/js/owl-carousel.js"></script>
  <script src="resources/js/tabs.js"></script>
  <script src="resources/js/popup.js"></script>
  <script src="resources/js/custom.js"></script>
  <script src="//t1.daumcdn.net/mapjsapi/bundle/postcode/prod/postcode.v2.js"></script>

<script>

//실시간으로 이메일 형식 유효성 검사
function validateEmail(input) {
    const email = input.value;
    const emailError = document.getElementById('email-error');
    const re = /\S+@\S+\.\S+/;
    if (!re.test(email)) {
        emailError.textContent = '올바른 이메일 주소를 입력하세요.';
    } else {
        emailError.textContent = '';
    }
}

// 전체 폼 데이터 검증, 이메일 주소 형식이 올바르지 않으면 DB로 전송 막음.
function validateForm() {
    const emailInput = document.getElementById('email');
    const emailError = document.getElementById('email-error');

    // 이메일 주소 유효성 검사
    const re = /\S+@\S+\.\S+/;
    if (!re.test(emailInput.value)) {
        emailError.textContent = '올바른 이메일 주소를 입력하세요.';
        return false; // 폼 제출 방지
    } else {
        emailError.textContent = ''; // 에러 메시지 초기화
        return true; // 폼 제출 허용
    }
}


function sample4_execDaumPostcode() {
        new daum.Postcode({
            oncomplete: function(data) {
             
                var roadAddr = data.roadAddress; // 도로명 주소 변수
              /*   var extraRoadAddr = ''; */ // 참고 항목 변수

       
                if(data.bname !== '' && /[동|로|가]$/g.test(data.bname)){
                  /*   extraRoadAddr += data.bname; */
                }
             
                if(data.buildingName !== '' && data.apartment === 'Y'){
                  /*  extraRoadAddr += (extraRoadAddr !== '' ? ', ' + data.buildingName : data.buildingName); */
                }
             
                /* if(extraRoadAddr !== ''){
                    extraRoadAddr = ' (' + extraRoadAddr + ')';
                } */

                document.getElementById('sample4_postcode').value = data.zonecode;
                document.getElementById("sample4_roadAddress").value = roadAddr;
              /*   document.getElementById("sample4_jibunAddress").value = data.jibunAddress; */
                
                if(roadAddr !== ''){
              /*       document.getElementById("sample4_extraAddress").value = extraRoadAddr; */
                } else {
                    document.getElementById("sample4_extraAddress").value = '';
                }

                var guideTextBox = document.getElementById("guide");

                if(data.autoRoadAddress) {
                /*     var expRoadAddr = data.autoRoadAddress + extraRoadAddr; */
                  /*   guideTextBox.innerHTML = '(예상 도로명 주소 : ' + expRoadAddr + ')'; */
                  /*   guideTextBox.style.display = 'block'; */

                } else {
                   /*  guideTextBox.innerHTML = ''; */
                   /*  guideTextBox.style.display = 'none'; */
                }
            }
        }).open();
    }




	function addRess(f) {

           
		let user_addr = f.postal_code.value.trim() +  f.road_name.value.trim() +f.detailed_address.value.trim();

		
	    f.user_addr.value = user_addr;
	 // 폼 제출
        f.action = "address_update";
        f.method = "POST";
        f.submit();
	}

  </script>

</body>
</html>