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
</style>
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
                  <div class="col-lg-12">
                    <div class="clips">
                      <div class="row">
                        <div class="col-lg-12">
                          <div class="heading-section">
                            <h4><em>EZ-FUNDING</em> 공지사항</h4>
                          </div>
                        </div>
      
                        <hr><hr><hr><hr>
                        <div class="col-lg-12">
                          <!-- 프로젝트 내용 Start -->
                            <h4>${dto.ad_notice_title}</h4>
                          <div class="row">
                        
                            <div class="detail-content" align="center">
                            	${dto.ad_notice_content}
                            </div>
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
