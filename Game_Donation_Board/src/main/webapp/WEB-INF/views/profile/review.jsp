<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>    
<!DOCTYPE html>
<html>
<head>
 <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">

    <title>Cyborg - Awesome HTML5 Template</title>

    <style>    

    .heading-section h4 {
        color: white;
        font-size: 34px;
        text-decoration: none;
        margin-bottom: 30px;
      }
    </style>
    
    <link rel="stylesheet" href="resources/css/menu.css"> 
	<link href="https://fonts.googleapis.com/css2?family=Poppins:wght@100;200;300;400;500;600;700;800;900&display=swap" rel="stylesheet">
    <link href="resources/css/bootstrap.min.css" rel="stylesheet">
    <link rel="stylesheet" href="resources/css/fontawesome.css">
    <link rel="stylesheet" href="resources/css/templatemo-cyborg-gaming.css">
    <link rel="stylesheet" href="resources/css/owl.css">
    <link rel="stylesheet" href="resources/css/animate.css">
    <link rel="stylesheet"href="https://unpkg.com/swiper@7/swiper-bundle.min.css"/>

<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body style="font-family: 'Montserrat', 'sans-serif';">
<%@ include file= "/WEB-INF/views/board/menu.jsp" %>
<div class="container">
    <div class="row">
        <div class="col-lg-12">
            <div class="page-content" id='status'>
                <div class="gaming-library" id="admin-page-content">
                    <div class="row">
                        <div class="col-lg-4" >
                            <div class="heading-section">
                                <h4>
                                  	<em>내가 작성한</em> 리뷰
                                </h4>
                            </div>
                        </div>
                    </div>
                    <div class="item">
                        <ul style="text-align: center;">
                            <li></li>
                            <li></li>
                            <li></li>
                            <li></li>
                            <li></li>
                            <li></li>
                        </ul>
                    </div>
                    <c:forEach items="${reviewList}" var="dto"  varStatus="count">
                    	<div class="item" >
                            <ul style="text-align: center;">
                          		 <li></li>
                                <li><h4>${count.index + 1}</h4></li>
                                <li><img class="templatemo-item" src="${user_email.user_img}" ></li>
                                <li><h4>제목</h4><span><a href="project_detail?project_idx=${ dto.project_idx}">${dto.project_name}</a></span></li>
                                <li><h4>내용</h4><span style="color: white;">${dto.review_content}</span></li>
                                <li></li>
                                
                            </ul> 
                   	 </div>
                  </c:forEach>     
                </div>
            </div>
        </div>
    </div>
</div>
<!-- Bootstrap core JavaScript -->
<script src="resources/vendor/jquery/jquery.min.js"></script>
<script src="resources/vendor/bootstrap/js/bootstrap.min.js"></script>
<script src="resources/js/httpRequest.js"></script>
</body>
</html>