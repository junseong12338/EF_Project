<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
  		<!-- jQuery -->
        <script type="text/javascript" src="https://code.jquery.com/jquery-1.12.4.min.js" ></script>
        <!-- iamport.payment.js -->
        <script type="text/javascript" src="https://cdn.iamport.kr/js/iamport.payment-1.2.0.js"></script>
        <script>
          var IMP = window.IMP; 
          IMP.init("imp52373275"); 
        
          var today = new Date();   
          var hours = today.getHours(); // 시
          var minutes = today.getMinutes();  // 분
          var seconds = today.getSeconds();  // 초
          var milliseconds = today.getMilliseconds();
          var makeMerchantUid = hours +  minutes + seconds + milliseconds;
          
  
          function requestPay() {
        	  if(confirm('결제하시겠습니까?')){
        		  let payment = document.getElementById("payment").value;
                  IMP.request_pay({
                      pg : 'kakaopay',
                      merchant_uid: "IMP"+makeMerchantUid, 
                      name : '컴퓨터',
                      amount : Number(payment),
                      buyer_email : 'tyghqkr456@naver.com',
                      buyer_name : '석진',
                      buyer_tel : '010-1234-5678',
                      buyer_addr : '인천광역시 부평',
                      buyer_postcode : '123-456'
                  }, function (rsp) { // callback
                      if (rsp.success) {
                          console.log(rsp);
                          alert('결제완료');
                      } else {
                          console.log(rsp);
                          alert('결제실패');
                      }
                  });
        	  }
        	 
          }
      </script>

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

#KG이니시스 {
      width: 100px; /* 버튼의 너비를 조정합니다. */
      border-radius:15px;
    }

    .payment-button:not(:last-child) {
      margin-right: 20px;
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

<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
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
            <div id="profile-settings" style="margin:auto">
                <h2>결제 수단 방법</h2><br>
                <div class="button-container">
                    <p>결제 금액</p>
                    <input type="number"placeholder="숫자만 입력가능." id="payment">
                </div>
                 <button onclick="requestPay()" id="kakao-pay" class="payment-button">
                  <img src="./resources/images/카카오페이.jpg" alt="카카오페이">
                </button>
                <button id="KG이니시스" class="KG이니시스">
                <img src="./resources/images/KG이니시스.jpg" alt="KG이니시스">
              </button>
              </div><br>
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

</body>
</html>