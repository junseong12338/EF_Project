<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>    
<!DOCTYPE html>
<html>
  		<!-- jQuery -->
        <script type="text/javascript" src="https://code.jquery.com/jquery-1.12.4.min.js" ></script>
        <!-- iamport.payment.js -->
        <script type="text/javascript" src="https://cdn.iamport.kr/js/iamport.payment-1.2.0.js"></script>
        <script>
          var IMP = window.IMP; 
        
          var today = new Date();   
          var hours = today.getHours(); // 시
          var minutes = today.getMinutes();  // 분
          var seconds = today.getSeconds();  // 초
          var milliseconds = today.getMilliseconds();
          var makeMerchantUid = hours +  minutes + seconds + milliseconds;
          


          function kakaoPay(f) {
              IMP.init("imp52373275");
    		  let email = document.getElementById("user_email").value;
    		  let name = document.getElementById("user_name").value;
    		  let addr = document.getElementById("user_addr").value;
    		  let point = document.getElementById("user_point").value;
    		  let idx = document.getElementById("user_idx").value;
    		  let payment = document.getElementById("payment").value;
    		  if(payment < 1){
    			  alert('0원 이하로 결제 할 수 없습니다.');
              	  history.go(0);
    		  }else{
        	  if(confirm('결제하시겠습니까?')){
            		  IMP.request_pay({
                          pg : 'kakaopay',
                          merchant_uid: "IMP"+makeMerchantUid, 
                          name : payment+' 포인트',
                          amount : Number(payment),
                          buyer_email : email,
                          buyer_name : name,
                          buyer_tel : '010-1234-5678',
                          buyer_addr : addr,
                          buyer_postcode : '123-456'
            	  }
            	  , function (rsp) { // callback
                      if (rsp.success) {
                    	  console.log('success');
                     	   $.ajax({
                     		    url: "user_point_update",
                     		    data: {"user_email" : email,"payment": payment},
                     		    type: "POST"
                     		  });
                    	  alert("결제성공");
                    	  window.opener.location.reload();
                    	  window.close();
                      } else {
                        	  console.log(rsp.error_msg);
                          	  alert('결제실패');
                        	  history.go(0);
                      }
                  });
        	  }
    			  
    			  
    		  }
          }
          
          function kgpay(f) {
              IMP.init("imp60461863");
    		  let email = document.getElementById("user_email").value;
    		  let name = document.getElementById("user_name").value;
    		  let addr = document.getElementById("user_addr").value;
    		  let point = document.getElementById("user_point").value;
    		  let idx = document.getElementById("user_idx").value;
    		  let payment = document.getElementById("payment").value;
        	  //class가 btn_payment인 태그를 선택했을 때 작동한다.
    		  if(payment < 1){
    			  alert('0원 이하로 결제 할 수 없습니다.');
              	  history.go(0);
    		  }else{
    			  if(confirm('결제하시겠습니까?')){
    	        		IMP.request_pay({
    					      pg : 'html5_inicis', 
    	  				      pay_method : 'card',
    	                      merchant_uid: "IMP"+makeMerchantUid, 
    	                      name : payment+' 포인트',
    	                      amount : Number(payment),
    	                      buyer_email : email,
    	                      buyer_name : name,
    	                      buyer_tel : '010-1234-5678',
    	                      buyer_addr : addr,
    	                      buyer_postcode : '123-456'
    	        			}, function (rsp) { // callback
    	                            if (rsp.success) {
    	                          	  console.log('success');
    	                           	   $.ajax({
    	                           		    url: "user_point_update",
    	                           		    data: {"user_email" : email,"payment": payment},
    	                           		    type: "POST"
    	                           		  });
    	                          	  alert("결제성공");
    	                        	  window.opener.location.reload();
    	                          	  window.close();
    	                            } else {
    	                          	  console.log(rsp.error_msg);
    	                          	  alert('결제실패');
    	                        	  history.go(0);
    	                            }
    	                        });
    	        			}
    			  
    		  }
        	  
        	 
          }
      </script>

<head>
<meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">

    <title>Cyborg - Awesome HTML5 Template</title>

    <link href="resources/css/bootstrap.min.css" rel="stylesheet">
    <link rel="stylesheet" href="resources/assets/css/fontawesome.css">
    <link rel="stylesheet" href="resources/assets/css/templatemo-cyborg-gaming.css">
    <link rel="stylesheet" href="resources/assets/css/owl.css">
    <link rel="stylesheet" href="resources/assets/css/animate.css">
    
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
  margin-left: 0px; /* 이미지의 너비를 조정합니다. */
  height: auto;
     width: 95px; /* 버튼의 너비를 조정합니다. */
      border-radius:5px;
      color: #000; /* 텍스트 색상을 변경합니다. */
  border: none;
  padding: 5px 15px; /* 내부 여백을 조정합니다. */
  border-radius: 5px;
  cursor: pointer;
  transition: background-color 0.5s; /* 마우스 오버 시 부드럽게 변화하도록 설정합니다. */
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

  
  <div class="container">
    <div class="row">
      <div class="col-lg-12">
        <div class="page-content">
            <div id="profile-settings" style="margin:auto">
                <h2>결제 수단 방법</h2><br>
                <div class="button-container">
                    <p>결제 금액</p>
                    <form name="f">
                    <input type="hidden" value="${user_email.user_idx}" id="user_idx">
                    <input type="hidden" value="${user_email.user_email}" id="user_email">
                    <input type="hidden" value="${user_email.user_name}" id="user_name">
                    <input type="hidden" value="${user_email.user_addr}" id="user_addr">
                    <input type="hidden" value="${user_email.user_point}" id="user_point">
                    <input type="number"placeholder="숫자만 입력가능." id="payment" required>
                    </form>
                </div>
                 <button onclick="kakaoPay(this.form)" id="kakao-pay" class="payment-button">
                  <img src="resources/img/kakaopay.jpg" alt="카카오페이" >
                </button>
                <button onclick="kgpay(this.form)" id="KG이니시스" class="KG이니시스">
                <img src="resources/img/KG.jpg" alt="KG이니시스" >
              </button>
              </div><br>
            </div>
            </div>
        </div>
    </div>
  

 <script src="resources/assets/jquery/jquery.min.js"></script>
  <script src="resources/assets/bootstrap/js/bootstrap.min.js"></script>
  <script src="resources/assets/js/isotope.min.js"></script>
  <script src="resources/assets/js/owl-carousel.js"></script>
  <script src="resources/assets/js/tabs.js"></script>
  <script src="resources/assets/js/popup.js"></script>
  <script src="resources/assets/js/custom.js"></script>
</body>
</html>