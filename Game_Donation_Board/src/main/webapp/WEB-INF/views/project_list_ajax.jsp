<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<div class="row">
	<div class="col-lg-3 col-sm-6">
		<div class="item">
			<img src="resources/assets/images/popular-01.jpg" alt="">
			<h4>Fortnite   <span>Sandbox</span></h4>
			<p>안녕하세요 반갑습니다.</p>
			<p>&nbsp;dsadasd</p>
			<p>dsadasdassaddas</p>
		</div>
	</div>
</div>


<div class="row">
	<div class="col-lg-3 col-sm-6">
		<c:forEach var="dto" items="${list }">
		<div class="item">
			<img src="" alt="">
			<h4><span>${dto.project_title}</span></h4>
			<h6>${dto.project_subtitle }&nbsp;/&nbsp;${dto.author }</h6>
			<ul>
				<li>${달성퍼센트 계산해서 올리자} , ${dto.project_donation }</li>
				<li>${남은기간 계산해서 올리자 }</li>
			</ul>
		</div>
		</c:forEach>
	</div>
</div>