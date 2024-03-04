<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<c:if test="${page_num eq 1}">
	<c:choose>
		<c:when test="${not empty list }">
			<h6><em>${list_count}</em>건의 프로젝트가 있습니다.</h6>
		</c:when>
		<c:when test="${empty list }">
			<h6>등록된 프로젝트가 없습니다.</h6>
		</c:when>
	</c:choose>
</c:if>

<div class="row">
	<div class="col-lg-3 col-sm-6">
		<c:forEach var="dto" items="${list }">
		<div class="item">
			<img src="" alt="">
			<h4><span>${dto.project_title}</span></h4>
			<h6>${dto.project_subtitle }&nbsp;/&nbsp;${dto.author }</h6>
			<ul>
				<li>${persent} , ${dto.project_donation }</li>
				<li>${diff_date }</li>
			</ul>
		</div>
		</c:forEach>
	</div>
</div>