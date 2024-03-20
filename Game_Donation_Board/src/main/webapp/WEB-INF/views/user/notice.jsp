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
<body style="font-family: 'Montserrat', sans-serif';">
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
                                <em>공지</em>사항                                
                                </h4>
                            </div>
                        </div>  
                    </div>

                    <div class="item">
	                   <c:forEach items="${NoticeList}" var="NoticeList" varStatus="count">
	                       <ul style="text-align: center;">
	                           <li><h4>${count.index + 1}</h4></li>
	                           <li></li>
	                           <li><h4>${NoticeList.ad_notice_title}</h4></li>
	                           <li></li>
	                           <li></li>
	                           <li>
	                           		<div class="main-border-button">
                                    <a href="notice_detail?ad_notice_idx=${NoticeList.ad_notice_idx}&page=${Noticepage.currentPage}">공지 보기</a>
                                   </div>
	                           </li>
	                       </ul>
	                   </c:forEach>
                    </div>
                    <div class="pagination-container" id="pagination-container">
                        <ul>
	                        <c:choose>
	                            <c:when test="${Noticepage.currentPage == 1}">
	                                <li><a href="javascript:void(0)" style="pointer-events: none;"> &lt;&lt;</a></li>
	                            </c:when>
	                            <c:otherwise>
	                                <li><a href="javascript:void(0)" onclick="fetchPageData('AdminPage?page=${Noticepage.prevPage+1}&status=${status}')"> &lt;&lt;</a></li>
	                            </c:otherwise>
	                        </c:choose>
	                        <c:forEach var='idx' begin="${Noticepage.min}" end="${Noticepage.max}">
	                            <li>
	                                <c:choose>
	                                    <c:when test="${idx == Noticepage.currentPage}">
	                                        <a href="javascript:void(0)" style="color: #e75e8d; pointer-events: none;">${Noticepage.currentPage}</a>
	                                    </c:when>
	                                    <c:otherwise>
	                                        <a href="javascript:void(0)" onclick="fetchPageData('AdminPage?page=${idx}&status=${status}')">${idx}</a>
	                                    </c:otherwise>
	                                </c:choose>
	                            </li>
	                        </c:forEach>
	                        <c:choose>
	                            <c:when test="${Noticepage.max == Noticepage.currentPage}">
	                                <li><a href="javascript:void(0)" style="pointer-events: none;">&gt;&gt;</a></li>
	                            </c:when>
	                            <c:otherwise>
	                                <li><a href="javascript:void(0)" onclick="fetchPageData('AdminPage?page=${Noticepage.nextPage-1}&status=${status}')"> &gt;&gt;</a></li>
	                            </c:otherwise>
	                        </c:choose>
                        </ul>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>

<script src="resources/vendor/jquery/jquery.min.js"></script>
<script src="resources/vendor/bootstrap/js/bootstrap.min.js"></script>
<script src="resources/js/adminList.js"></script>
<script src="resources/js/httpRequest.js"></script>
</body>
</html>
