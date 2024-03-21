<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
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
    <link rel="stylesheet" href="https://unpkg.com/swiper@7/swiper-bundle.min.css"/>
    <link rel="stylesheet" href="resources/assets/css/side-bar.css">
    <link rel="stylesheet" href="https://fonts.googleapis.com/css?family=Montserrat:400,800">
    <link rel="stylesheet" href="resources/css/adminList.css">
</head>
<body style="font-family: 'Montserrat', 'sans-serif';">
<%@ include file= "/WEB-INF/views/board/menu.jsp" %>
<div class="container">
    <div class="row">
        <div class="col-lg-12">
            <div class="page-content" id='status'>
                <div class="gaming-library" id="admin-page-content">
                    <div class="row">
                        <div class="col-lg-4">
                            <div class="heading-section">
                                <h4>
                                  	<em>내가 등록한</em> 프로젝트
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
                 <c:forEach items="${projectList}" var="dto" varStatus="count">
                   	 <div class="item" > 
                            <ul style="text-align: center;">
                             <li><h4>${count.index + 1}</h4></li>
                                <li><img class="templatemo-item" src="${dto.project_img}" ></li>
                                <li><h4>프로젝트 제목</h4><span><a href="project_detail?project_idx=${dto.project_idx}">${dto.project_title}</a></span></li>
                                <li><h4>후원 시작일</h4><span>${dto.project_start.substring(0, 10)}</span></li>
                                <li><h4>후원 종료일</h4><span>${dto.project_end.substring(0, 10)}</span></li>
                                <li><div class="main-border-button">
                        			<a href="project_modify?idx=${dto.project_idx}" id="openModalBtn">수정하기</a></div></li>
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
