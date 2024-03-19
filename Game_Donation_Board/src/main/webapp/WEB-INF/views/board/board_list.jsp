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
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">

    <style type="text/css">
        /* Custom CSS styles */
        img {
            image-rendering: -webkit-optimize-contrast;
            transform: translateZ(0);
            backface-visibility: hidden;
        }
    </style>
</head>

<body style="font-family: 'Montserrat', sans-serif;">

<%@ include file="menu.jsp" %>

<div class="container">
    <div class="row">
        <div class="col-lg-12">
            <div class="page-content">

                <!-- ***** Banner Start ***** -->
                <div class="main-banner">
                    <div class="row">
                        <div class="col-lg-7">
                            <div class="header-text">
                                <h6>Welcome To EZ_Funding</h6>
                                <h4><em>Join the Quest</em><br> Fund Your Next Adventure!</h4>
                                <div class="main-button">
                                    <a href="charge_view">후원하기</a>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
                <!-- ***** Banner End ***** -->

                <!-- ***** Featured Games Start ***** -->
                <div class="row" style="margin-top: 30px;">
                    <div class="col-lg-8">
                        <div class="featured-games header-text">
                            <div class="heading-section">
                                <h4><em>최다 후원</em> 프로젝트</h4>
                            </div>
                            <div class="owl-features owl-carousel">
                                <c:forEach items="${Main_donation_list}" var="donation">
                                    <div class="item">
                                        <div class="thumb">
                                            <a href="project_detail?project_idx=${donation.project_idx}">
                                                <img src="${donation.project_img}" style="height: 393px;" alt="">
                                            </a>
                                            <div class="hover-effect">
                                                <h6>달성 퍼샌트</h6>
                                            </div>
                                        </div>
                                        <h4>${donation.project_title}<br><span>${donation.user_name}</span></h4>
                                        <ul>
                                            <li><i class="fa fa-heart" style="color: #ec6090;"></i>${donation.project_like}</li>
                                            <li><i class="fa fa-download"></i>${donation.donation_money}</li>
                                        </ul>
                                    </div>
                                </c:forEach>
                            </div>
                        </div>
                    </div>
                    <!-- ***** Featured Games End ***** -->

                    <div class="col-lg-4">
                        <div class="top-streamers">
                            <div class="heading-section">
                                <h4><em>인기</em> 프로젝트</h4>
                            </div>
                            <ul>
                                <c:forEach items="${Main_Like_Project_list}" var="likeProject" varStatus="loop">
                                    <li style="display: flex; align-items: center;">
                                        <span>${loop.index + 1}</span>
                                        <img src="${likeProject.user_img}" alt="" style="max-width: 46px; border-radius: 50%; margin-right: 15px;">
                                        <h6 style="color: #fff;">${likeProject.user_name}</h6>
                                        <h6 style="color: #fff; margin-left: auto; margin-right: 20px;">
                                            <i class="fa fa-thumbs-o-up" style="background-color: #84a1db; color: white;"></i>
                                            ${likeProject.project_like}
                                        </h6>
                                        <div class="main-button">
                                            <a href="project_detail?project_idx=${likeProject.project_idx}">Show View</a>
                                        </div>
                                    </li>
                                </c:forEach>
                            </ul>
                        </div>
                    </div>
                </div>
                <!-- ***** Featured Games End ***** -->

                <!-- ***** Live Stream Start ***** -->
                <div class="live-stream most-popular">
                    <div class="col-lg-12">
                        <div class="heading-section">
                            <h4><em>신규 등록</em> 프로젝트</h4>
                        </div>
                    </div>

                    <div class="row">
                        <c:forEach items="${Main_New_registration_list}" var="newRegistration">
                            <div class="col-lg-3 col-sm-6">
                                <div class="item">
                                    <div class="thumb" >
                                        <a href="project_detail?project_idx=${newRegistration.project_idx}">
                                            <img src="${newRegistration.project_img}" style="width: 246px; height: 246px;">
                                        </a>
                                        <div class="hover-effect">
                                            <div class="content">
                                                <div class="live">
                                                    <a href="#">New</a>
                                                </div>
                                                <ul>
                                                    <li><a href="#"><i class="fa fa-thumbs-o-up" style="color: #fff"></i> ${newRegistration.project_like}</a></li>
                                                    <li><a href="#"><i class="fa fa-dollar" style="color: #fff"></i > 1000 % </a></li>
                                                </ul>
                                            </div>
                                        </div>
                                    </div>   

                                    <div class="down-content" style="display: flex; text-align: center;">
                                        <div class="avatar" style="margin-right: 10px;">
                                            <img src="${newRegistration.user_img}" alt="" style="max-width: 46px; border-radius: 50%;">
                                        </div>

                                        <h4 style="display: inline-block; margin-top: 10px; margin-left: -10px;">${newRegistration.project_title}</h4> 
                                    </div>
                                    <span style="display: inline-block; color:#e75e8d;">${newRegistration.user_name}</span>
                                    <span style="display: inline-block; vertical-align: top; margin-left: 10px; color:#999;"> ${newRegistration.donation_money} 원 10 일 남음</span>
                                </div>
                            </div>
                        </c:forEach>
                    </div>

                    <div class="col-lg-12">
                        <div class="main-button">
                            <a href="project_list">프로젝트 전체보기</a>
                        </div>
                    </div>
                </div>
                <!-- ***** Live Stream End ***** -->

                <!-- ***** Gaming Library Start ***** -->
                <div class="gaming-library">
                    <div class="col-lg-12">
                        <div class="heading-section">
                            <h4><em>공개 예정</em> 프로젝트</h4>
                        </div>
                        <c:forEach items="${Main_To_be_released_list}" var="TobeReleased">
                            <div class="item">
                                <ul>
                                    <li><img src="${TobeReleased.project_img}" class="templatemo-item"></li>
                                    <li><h4>${TobeReleased.project_title}</h4><span>${TobeReleased.user_name}</span></li>
                                    <li><h4>공개 날짜</h4><span>${TobeReleased.project_start.substring(0, 10)}</span></li>
                                    <li></li>
                                    <li></li>
                                    <li><div class="main-border-button"><a href="project_detail?project_idx=${TobeReleased.project_idx}">미리 보기</a></div></li>
                                </ul>
                            </div>
                        </c:forEach>
                    </div>
                    <div class="col-lg-12">
                        <div class="main-button">
                            <a href="profile.html">공개 예정 프로젝트 보기</a>
                        </div>
                    </div>
                </div>
                <!-- ***** Gaming Library End ***** -->
            </div>
        </div>
    </div>
</div>

<!-- Bootstrap core JavaScript -->
<script src="https://code.jquery.com/jquery-3.5.1.js"></script>
<script src="resources/vendor/jquery/jquery.min.js"></script>
<script src="resources/vendor/bootstrap/js/bootstrap.min.js"></script>
<script src="resources/assets/js/isotope.min.js"></script>
<script src="resources/assets/js/owl-carousel.js"></script>
<script src="resources/assets/js/tabs.js"></script>
<script src="resources/assets/js/popup.js"></script>
<script src="resources/assets/js/custom.js"></script>

</body>
</html>
