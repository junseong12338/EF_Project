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
    <link href="vendor/bootstrap/css/bootstrap.min.css" rel="stylesheet">


    <!-- Additional CSS Files -->
    <link rel="stylesheet" href="resources/assets/css/fontawesome.css">
    <link rel="stylesheet" href="resources/assets/css/templatemo-cyborg-gaming.css">
    <link rel="stylesheet" href="resources/assets/css/owl.css">
    <link rel="stylesheet" href="resources/assets/css/animate.css">
    <link rel="stylesheet"href="https://unpkg.com/swiper@7/swiper-bundle.min.css"/>
<!--

TemplateMo 579 Cyborg Gaming

https://templatemo.com/tm-579-cyborg-gaming

-->
<script type="text/javascript">
	let content = false
	let notice = false
	let review = false
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
                    <img src="${dto.img }" alt="" style="border-radius: 23px;">
                  </div>
                  <div class="col-lg-4 align-self-center">
                    <div class="main-info header-text">
                      <h4>${dto.title }</h4>
                      <h6>&nbsp;${dto.author }</h6>
                      <br>
                      <p>카테고리 1, 카테고리 2, 카테고리 3, 카테고리 4, 카테고리 5, ...</p>
                      <div class="main-border-button">
                        <a href="#" >프로젝트 후원하기</a>
                      </div>
                    </div>
                  </div>
                  <div class="col-lg-4 align-self-center">
                    <ul>
                      <li>모인금액 <span>${dto.donation }</span></li>
                      <li>목표금액 <span>${dto.target }</span></li>
                      <li>기간 <span>${dto.start } ~ ${dto.end } ( ${dto.diff_date } )</span></li>
                      <li>
                        <button>좋아요&nbsp; <span>${like_count }</span> </button>
                        <button>알람</button>
                        <button>신고</button>
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
                            <h4><em>Your Most Popular</em> Clips</h4>
                          </div>
                        </div>
                        <div class="col-lg-12">
                          <div class="main-border-button">
                            <a href="#">프로젝트 내용</a>
                            <a href="#">공지</a>
                            <a href="#">리뷰</a>
                          </div>
                        </div>
                        <hr>
                        <div class="col-lg-12">
                          <div>
                          	<c:choose>
                          		<c:when test="content">
                          		</c:when>
                          		<c:when test="notice">
                          			
                          		</c:when>
                          		<c:when test="review">
                          			
                          		</c:when>
                          	</c:choose>
                          	<c:if test="content">
                          	<!-- 프로젝트 내용 Start -->
                            <div class="main-contentbox">
                              <p>${dto.content }</p>
                            </div>
                            <!-- 프로젝트 내용 End -->
                            </c:if>
                            
                            
                            
                            
                            
                            
                            
                            
                            
                            
                            
                            
                            
                            
                            
                          </div>
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
  <script src="vendor/jquery/jquery.min.js"></script>
  <script src="vendor/bootstrap/js/bootstrap.min.js"></script>

  <script src="resources/assets/js/isotope.min.js"></script>
  <script src="resources/assets/js/owl-carousel.js"></script>
  <script src="resources/assets/js/tabs.js"></script>
  <script src="resources/assets/js/popup.js"></script>
  <script src="resources/assets/js/custom.js"></script>


  </body>

</html>
