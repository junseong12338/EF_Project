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
                                    <a href="javascript:void(0)" onclick="${status == 0 ? '' : 'fetchStatusData(\'AdminPage?page=1&status=0\')'}" style="${status == 0 ? 'color: #e75e8d; pointer-events: none;' : ''}">
                                        ${status == 0 ? '<em>승인 대기</em>' : '승인 대기'} 프로젝트
                                    </a>
                                </h4>
                            </div>
                        </div>
                        <div class="col-lg-4">
                            <div class="heading-section">
                                <h4>
                                    <a href="javascript:void(0)" onclick="${status == 1 ? '' : 'fetchStatusData(\'AdminPage?page=1&status=1\')'}" style="${status == 1 ? 'color: #e75e8d; pointer-events: none;' : ''}">
                                        ${status == 1 ? '<em>승인 완료</em>' : '승인 완료'} 프로젝트
                                    </a>
                                </h4>
                            </div>
                        </div>
                        <div class="col-lg-4">
                            <div class="heading-section">
                                <h4>
                                    <a href="javascript:void(0)" onclick="${status == 3 ? '' : 'fetchStatusData(\'AdminPage?page=1&status=3\')'}" style="${status == 3 ? 'color: #e75e8d; pointer-events: none;' : ''}">
                                        ${status == 3 ? '<em>공지</em>' : '공지'} 사항
                                    </a>
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
                            <li>
                                <c:choose>
                                    <c:when test="${status != 3}">
                                        <c:choose>
                                            <c:when test="${status == 0}">
                                                <div class="main-border-button">
                                                    <a href="javascript:void(0)" onclick="toggleAllApprovalStatus()">전체 선택</a>
                                                </div>
                                            </c:when>
                                            <c:otherwise>
                                                <div class="main-border-button">
                                                    <a href="javascript:void(0)" onclick="toggleAllRevertApprovalStatus()">전체 선택</a>
                                                </div>
                                            </c:otherwise>
                                        </c:choose>
                                    </c:when>
                                    <c:otherwise>
                                        <div class="main-border-button">
                                            <a href="admin_notice_editor">공지 등록</a>
                                        </div>
                                    </c:otherwise>
                                </c:choose>
                            </li>
                        </ul>
                    </div>
                    <div class="item">
                        <c:choose>
                            <c:when test="${status != 3}">
                                <c:forEach items="${AdminInfo}" var="AdminInfo">
                                    <ul style="text-align: center;">
                                        <li><img class="templatemo-item" src="${AdminInfo.project_img}"></li>
                                        <li><h4>${AdminInfo.project_title}</h4><span>${AdminInfo.user_name}</span></li>
                                        <li><h4>후원 시작일</h4><span>${AdminInfo.project_start.substring(0, 10)}</span></li>
                                        <li><h4>후원 종료일</h4><span>${AdminInfo.project_end.substring(0, 10)}</span></li>
                                        <li>
                                            <h4>Currently</h4>
                                            <span>
                                                <c:choose>
                                                    <c:when test="${AdminInfo.project_status == 0}">
                                                        대기중
                                                    </c:when>
                                                    <c:otherwise>
                                                        승인완료
                                                    </c:otherwise>
                                                </c:choose>
                                            </span>
                                        </li>
                                        <li>
                                            <c:choose>
                                                <c:when test="${status == 0}">
                                                    <div class="main-border-button">
                                                        <a href="javascript:void(0)" onclick="toggleApprovalStatus(this)" data-project-idx="${AdminInfo.project_idx}" data-project-status="0">승인 대기</a>
                                                    </div>
                                                </c:when>
                                                <c:otherwise>
                                                    <div class="main-border-button">
                                                        <a href="javascript:void(0)" onclick="toggleRevertApprovalStatus(this)" data-project-idx="${AdminInfo.project_idx}" data-project-status="1">해제 대기</a>
                                                    </div>
                                                </c:otherwise>
                                            </c:choose>
                                        </li>
                                    </ul>
                                </c:forEach>
                            </c:when>
                            <c:otherwise>
                                <c:forEach items="${NoticeList}" var="NoticeList" varStatus="count">
                                    <ul style="text-align: center;">
                                        <li><h4>${count.index + 1}</h4></li>
                                        <li><h4>${NoticeList.ad_notice_title}</h4></li>
                                        <li></li>
                                        <li></li>
                                        <li></li>
                                        <li> 
                                        	<div class="main-border-button">
                                        		<a href="admin_notice_modify?idx=${NoticeList.ad_notice_idx}" style="margin-left: 55px;">공지 수정</a>
                                        	</div>
                                        </li>
                                        <li><hr></li>
                                    </ul>
                                </c:forEach>
                            </c:otherwise>
                        </c:choose>
                    </div>
                    <div class="pagination-container" id="pagination-container">
                        <ul>
                            <c:choose>
                                <c:when test="${status != 3}">
                                    <c:choose>
                                        <c:when test="${page.currentPage == 1}">
                                            <li><a href="javascript:void(0)" style="pointer-events: none;"> &lt;&lt;</a></li>
                                        </c:when>
                                        <c:otherwise>
                                            <li><a href="javascript:void(0)" onclick="fetchPageData('AdminPage?page=${page.prevPage+1}&status=${status}')"> &lt;&lt;</a></li>
                                        </c:otherwise>
                                    </c:choose>
                                    <c:forEach var='idx' begin="${page.min}" end="${page.max}">
                                        <li>
                                            <c:choose>
                                                <c:when test="${idx == page.currentPage}">
                                                    <a href="javascript:void(0)" style="color: #e75e8d; pointer-events: none;">${page.currentPage}</a>
                                                </c:when>
                                                <c:otherwise>
                                                    <a href="javascript:void(0)" onclick="fetchPageData('AdminPage?page=${idx}&status=${status}')">${idx}</a>
                                                </c:otherwise>
                                            </c:choose>
                                        </li>
                                    </c:forEach>
                                    <c:choose>
                                        <c:when test="${page.max == page.currentPage}">
                                            <li><a href="javascript:void(0)" style="pointer-events: none;">&gt;&gt;</a></li>
                                        </c:when>
                                        <c:otherwise>
                                            <li><a href="javascript:void(0)" onclick="fetchPageData('AdminPage?page=${page.nextPage-1}&status=${status}')"> &gt;&gt;</a></li>
                                        </c:otherwise>
                                    </c:choose>
                                </c:when>
                                <c:otherwise>
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
                                </c:otherwise>
                            </c:choose>
                        </ul>
                    </div>
                    <c:choose>
                        <c:when test="${status != 3}">
                            <c:choose>
                                <c:when test="${status == 0}">
                                    <div class="main-button">
                                        <a href="javascript:void(0)" onclick="applyApproval(1, 'AdminPage?page=${page.currentPage}&status=${status}')">승인 적용</a>
                                    </div>
                                </c:when>
                                <c:otherwise>
                                    <div class="main-button">
                                        <a href="javascript:void(0)" onclick="applyApproval(0, 'AdminPage?page=${page.currentPage}&status=${status}')">해제 적용</a>
                                    </div>
                                </c:otherwise>
                            </c:choose>
                        </c:when>
                    </c:choose>
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
