<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<hr>
<c:if test="${page_num eq 1}">
	<c:choose>
		<c:when test="${not empty list }">
			<h6 align="center"><em>${list_count}</em> 건의 프로젝트가 있습니다.</h6>
		</c:when>
		<c:when test="${empty list }">
			<h6>등록된 프로젝트가 없습니다.</h6>
		</c:when>
	</c:choose>
</c:if>
<hr><hr><hr><hr>

<c:forEach var="dto" items="${list }">
	<div class="col-lg-3 col-sm-6">
		<div class="item">
			<a href="#"><!-- 상세페이지 경로 작성 -->
				<img src="${dto.project_img }" alt="프로젝트 이미지">
			</a>
			<h4>
				${dto.project_title}
				<br>
				<span>${dto.project_author}</span>
				<br>
				<span>${dto.project_subtitle}</span>
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
					${dto.project_donation}
				</li>
			</ul>
		</div>
	</div>
</c:forEach>
