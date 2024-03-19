<%@ page language="java" contentType="text/html; charset=UTF-8"
pageEncoding="UTF-8"%> <%@ taglib prefix="c"
uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html>
  <head>
    <meta charset="utf-8" />
    <meta
      name="viewport"
      content="width=device-width, initial-scale=1, shrink-to-fit=no"
    />
    <title>EZ Funding</title>

    <link href="resources/css/bootstrap.min.css" rel="stylesheet" />
    <link rel="stylesheet" href="resources/css/fontawesome.css" />
    <link rel="stylesheet" href="resources/css/templatemo-cyborg-gaming.css" />
    <link rel="stylesheet" href="resources/css/owl.css" />
    <link rel="stylesheet" href="resources/css/animate.css" />
    <link
      rel="stylesheet"
      href="https://unpkg.com/swiper@7/swiper-bundle.min.css"
    />
    <link
      href="https://fonts.googleapis.com/css2?family=Poppins:wght@100;200;300;400;500;600;700;800;900&display=swap"
      rel="stylesheet"
    />
    <link rel="stylesheet" href="resources/css/myinfo.css" />

    <meta charset="UTF-8" />
    <title>Insert title here</title>
  </head>
  <body>
    <%@ include file= "/WEB-INF/views/board/menu.jsp" %>

    <div class="container">
      <div class="row">
        <div class="col-lg-12">
          <div class="page-content">
          <form>
              <div id="profile-settings">
                  <h2>프로필 설정</h2><br>
                
               <div class="form-group"> 
                      <label for="username"><span style="color:red">* </span> 사용자 닉네임 </label>
                      <br/>
                      <input type="text" id="user_name" name="user_name" value="${name}" oninput="checkName(this.form)">
                      <br/>
                      <span id="check_name" style="color:red"></span>
                     
                   </div>
                      <br/>
                 <div class="form-group">
                      <label for="email">이메일 : ${email}</label> 
            
                    </div>   
              </div>
                <!-- 배송지 설정 입력란 추가 -->
               <span style="color:red">* </span><h2> 주소 입력</h2><br>
                <input type="text" name ="postal_code" id="sample4_postcode" placeholder="우편번호"  value="${user.user_addr.split('/')[0]}">
                <input type="button" onclick="sample4_execDaumPostcode()" value="우편번호 찾기"><br>
                <input type="text" name = "road_name" id="sample4_roadAddress" placeholder="도로명주소"  value="${user.user_addr.split('/')[1]}">
                <input type="text" name = "detailed_address"id="sample4_detailAddress" placeholder="상세주소"  value="${user.user_addr.split('/')[2]}"><br>
                <input type ="hidden" name = "user_addr"/>
                 <input type ="hidden" name = "user_email" value="${email}"/>
                <button onclick="updatemyinfo(event,this.form)">저장</button>
              </form>
                  <!-- 배송지 설정 입력란 추가 --> 
              </div>
          </div>
      </div>
    </div>   
    <script src="resources/jquery/jquery.min.js"></script>
    <script src="resources/js/bootstrap.min.js"></script>
    <script src="resources/js/isotope.min.js"></script>
    <script src="resources/js/owl-carousel.js"></script>
    <script src="resources/js/tabs.js"></script>
    <script src="resources/js/popup.js"></script>
    <script src="resources/js/custom.js"></script>
    <script src="//t1.daumcdn.net/mapjsapi/bundle/postcode/prod/postcode.v2.js"></script>
    <script src="resources/js/myinfo.js"></script>

	

  </body>
</html>
