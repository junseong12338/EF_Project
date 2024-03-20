<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>


<c:if test="${page_num eq 1}">
	<c:choose>
		<c:when test="${not empty list }">
			<h6 align="center"><em style="font-color:red;">${list_count}</em> 개의 프로젝트가 있습니다.</h6>
		</c:when>
		<c:when test="${empty list }">
			<h6 align="center">등록된 프로젝트가 없습니다.</h6>
		</c:when>
	</c:choose>
	<input id="total_page" type="hidden" value="${total_page_count}">
</c:if>
<hr><hr><hr><hr>

<c:forEach var="dto" items="${list }">
	<div class="col-lg-3 col-sm-6">
		<div class="item" style="height: 440px">
			<div class="image-container">
				<a href="project_detail?project_idx=${dto.project_idx}">
					<img src="${dto.project_img }" alt="프로젝트 이미지">
				</a>
			</div>
			<h4 style="max-width:120px">
				${dto.project_title}
				<br>
				<span>${dto.project_author}</span>
			</h4>
			<ul>
				<li>
					<i class="fa fa-star"></i>
					${dto.diff_date}
				</li>
				<li>
					<i class="fa fa-star"></i>
					${dto.persent}
				</li>
				<li>
					<i class="fa fa-download"></i>
					${dto.project_target}&nbsp; &#8361;
				</li>
			</ul>
		</div>
	</div>
</c:forEach>
