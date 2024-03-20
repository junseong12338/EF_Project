<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<c:choose>
	<c:when test="${wanted eq 0 }">
		<p>${content }</p>
	</c:when>
	<c:when test="${wanted eq 1 }">
		<div class="review">
			<div class="review-register">
				<div class="review-content-input">
					<input id="review-content" placeholder="EZ-FUNDING REVIEW REGISTER" style="width: 730px;">
				</div>
				<div class="review-register-btn">
					<a href="javascript:void(0)" onclick="register_review()"><img alt="등록" src="resources/img/icons8-register.png"></a>
				</div>
			</div>
			<div class="review-list" style="color: white;">
				<c:forEach var="dto" items="${list }">
				<div class="item">
					<div class="user-row">
						<img class="img" src="${dto.user_img }" alt="사용자 이미지">
						<span>${dto.user_name }</span>
					</div>
					<div class="content-row">${dto.review_content }</div>
					<div class="regdate-row">${dto.diff_date }</div>
				</div>
				</c:forEach>
			</div>
		</div>
	</c:when>
</c:choose>

