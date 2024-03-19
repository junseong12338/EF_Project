<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<script>
function modify(f){

	f.action = "modify_form";
	f.method = "post";
	f.submit();

}
</script>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<div id="main_box">
		<h1>공지 사항</h1>	
		<input type="button" value="글쓰기" onclick="location.href='notice_insert_form'">
	</div>
	
	<c:forEach var="dto" items="${list}">
		<div class="visit_box" border="1" align="center">
		<div class="type_content"><pre>${dto.notice_content}</pre></div>
		<div class="type_name">작성자 : ${dto.user_idx}</div>
<%-- 		<div class="type_regdate">작성일 ${vo.regdate }</div> --%>
		<div>
		<form>
			<input type="hidden" name="idx" value="${dto.user_idx}">
			<input type="button" value="수정" onclick="modify(this.form)">
			<input type="button" value="삭제" onclick="del(this.form)">
			<!-- 자바스크립트 구현해야됌. 로그인 되있는 상태에서 공지 추가 삭제 만들기 -->
		</form>
		</div>
		</div>

	</c:forEach>
	
</body>
</html>