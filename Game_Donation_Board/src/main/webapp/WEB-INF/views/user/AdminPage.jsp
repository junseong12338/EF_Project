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
            <div class="page-content" >
                <!-- AdminPage 내용 -->
                <div class="gaming-library">
                    <div class="row">
                        <div class="col-lg-4">
                            <div class="heading-section">
                                <h4><a href = "#" ><em>승인 대기</em> 프로젝트</a></h4>
                            </div>
                        </div>
                        <div class="col-lg-4">
                            <div class="heading-section">
                                <h4><a href = "#" >승인 완료 프로젝트</a></h4>
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
                                <div class="main-border-button">
                                    <a href="#" onclick="toggleAllApprovalStatus()">전체 승인</a>
                                </div>
                            </li>
                        </ul>
                    </div>
                    <div class="item" id="admin-page-content">
                        <c:forEach items="${AdminInfo}" var="AdminInfo">
                            <ul style="text-align: center;">
                                <li><img class="templatemo-item" src="${AdminInfo.project_img}" ></li>
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
                                    <div class="main-border-button">
                                        <a href="javascript:void(0)" onclick="toggleApprovalStatus(this)" data-project-idx="${AdminInfo.project_idx}" data-project-status="0">승인 대기</a>
                                    </div>
                                </li>
                            </ul>
                        </c:forEach>
                    </div>
                    <div class="pagination-container" id="pagination-container">
                        <ul>
                            <c:choose>
                                <c:when test="${page.prevPage <= 0}">
                                    <li><a href="javascript:void(0)" onclick="fetchPageData('AdminPage?page=${page.prevPage}')">이전</a></li>
                                </c:when>
                                <c:otherwise>
                                    <li><a href="javascript:void(0)" onclick="fetchPageData('AdminPage?page=${page.prevPage}')">이전</a></li>
                                </c:otherwise>
                            </c:choose>
                            <c:forEach var='idx' begin="${page.min}" end="${page.max}">
                                <li>
                                    <c:choose>
                                        <c:when test="${idx == page.currentPage}">
                                            <a href="javascript:void(0)" style="color: #e75e8d; pointer-events: none;" onclick="fetchPageData('AdminPage?page=${idx}')">${idx}</a>
                                        </c:when>
                                        <c:otherwise>
                                            <a href="javascript:void(0)" onclick="fetchPageData('AdminPage?page=${idx}')">${idx}</a>
                                        </c:otherwise>
                                    </c:choose>
                                </li>
                            </c:forEach>
                            <c:choose>
                                <c:when test="${page.max >= page.pageCnt}">
                                    <li><a href="javascript:void(0)" onclick="fetchPageData('AdminPage?page=${page.nextPage}')">다음</a></li>
                                </c:when>
                                <c:otherwise>
                                    <li><a href="javascript:void(0)" onclick="fetchPageData('AdminPage?page=${page.nextPage}')">다음</a></li>
                                </c:otherwise>
                            </c:choose>
                        </ul>
                    </div>
                    <div class="main-button">
                        <a href="javascript:void(0)" onclick="applyApproval()">승인 적용</a>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>
<!-- Bootstrap core JavaScript -->
<script src="resources/vendor/jquery/jquery.min.js"></script>
<script src="resources/vendor/bootstrap/js/bootstrap.min.js"></script>
<script src="resources/js/adminList.js"></script>
<script src="resources/js/httpRequest.js"></script>
<script type="text/javascript">
$(document).ready(function() {
    // 페이지 버튼 클릭 이벤트에 대한 처리
    $('.pagination-container a').on('click', function(event) {
        event.preventDefault(); // 기본 동작 중단
        var pageUrl = $(this).attr('href'); // 클릭된 페이지의 URL
        fetchPageData(pageUrl);
    });
});

function fetchPageData(pageUrl) {
    // AJAX 호출을 통해 해당 페이지의 HTML 정보 요청
    $.ajax({
        type: 'GET',
        url: pageUrl,
        success: function(data) {
        	event.preventDefault();
            var itemHtml = $(data).find('#admin-page-content').html(); // .item 클래스를 가진 요소의 HTML 가져오기
            var paginationHtml = $(data).find('.pagination-container').html(); // .pagination-container 클래스를 가진 요소의 HTML 가져오기
            $('#admin-page-content').html(itemHtml); // .item 클래스를 가진 요소의 HTML을 페이지에 적용
            $('#pagination-container').html(paginationHtml); // .pagination-container 클래스를 가진 요소의 HTML을 페이지에 적용
        },
        error: function(xhr, status, error) {
            console.error('AJAX 호출이 실패하였습니다.');
            // 실패 시 처리할 내용 추가
        }
    });
}

function applyApproval() {
    var approvedProjects = document.querySelectorAll('.main-border-button a[data-project-status="1"]');
    var approvedProjectData = [];

    approvedProjects.forEach(function(project) {
        var projectIdx = project.dataset.projectIdx;
        var projectStatus = project.dataset.projectStatus;
        approvedProjectData.push({ project_idx: projectIdx, project_status: projectStatus });
    });

    // JSON 형식으로 데이터 변환
    var requestData = JSON.stringify(approvedProjectData);

    // 서버로 POST 요청 전송
    $.ajax({
        type: "POST",
        url: "Status",
        contentType: "application/json", // 요청의 데이터 형식을 JSON으로 설정
        data: requestData, // JSON 형식으로 변환된 데이터 전송
        success: function(response) {
            console.log("서버 요청이 성공적으로 전송되었습니다.");
            alert("승인완료");
            location.reload();            
        },
        error: function(xhr, status, error) {
            console.error("서버 요청이 실패하였습니다.");
            // 서버 요청이 실패한 경우에 대한 처리를 여기에 추가
        }
    });
}
</script>
</body>
</html>
