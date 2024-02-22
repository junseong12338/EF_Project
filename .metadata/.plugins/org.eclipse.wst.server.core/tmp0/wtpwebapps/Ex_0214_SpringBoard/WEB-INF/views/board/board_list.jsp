<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<style>
	a{text-decoration: none;}
	table{
		border-collapse: collapse;
		margin:0 auto;
	}
	
	img{
		cursor: pointer;
	}
</style>
</head>
<body>
	<table border="1">
		<tr>
			<td colspan="5"><img src="resources/img/title_04.gif"></td>
		</tr>
		<tr>
			<td colspan = "5" align="right">
			<c:choose>
				<c:when test="${empty id }">
					<input type ="button" value="로그인" onclick="location.href='login_form'">
					<input type ="button" value="회원가입" onclick="location.href='member_insert_form'">
				</c:when>
				<c:when test="${not empty id }">
					<input type ="button" value="로그아웃" onclick="location.href='logout'">
				</c:when>
			</c:choose>
			</td>
		</tr>
		<tr>
			<th>번호</th>
			<th>제목</th>
			<th>작성자</th>
			<th>작성일</th>
			<th>조회수</th>
		</tr>
		<c:forEach var="dto" items="${list}">
		<tr>
			<td>${dto.idx}</td>
			<td>
				<!-- 댓글일 경우 들여쓰기 -->
				<c:forEach begin="1" end="${dto.depth}">&nbsp;</c:forEach>
				<!-- 댓글기호 -->
				<c:if test="${dto.depth ne 0 }">ㄴ</c:if>
				
				
				<!-- 삭제되지 않은 글일 경우 클릭이 가능 -->
				<c:if test="${dto.del_info ne -1}">
				<a href="view?idx=${dto.idx}&page=${param.page}">
					<font color="black">${dto.subject}</font>
				</a>
				</c:if>
				
				<!-- 삭제된 게시글을 클릭할 수 없도록 처리 -->
				<c:if test="${dto.del_info eq -1}">
					<font color="gray">${dto.subject}</font>
				</c:if>
					
			</td>
			<td>${dto.name}</td>
			
			
			<!-- 삭제가 되지 않은 게시물은 정상적으로 표시 -->
			<c:if test="${dto.del_info ne -1}">
			<td>${fn:split(dto.regdate,' ')[0]}</td>
			</c:if>
			
			<!-- 삭제가 된 게시물은 unkown으로 표시 -->
			<c:if test="${dto.del_info eq -1}">
			<td>unkown</td>
			</c:if>
			
			<td>${dto.readhit}</td>
		</tr>
		</c:forEach>
		<tr>
			<td colspan="5" align="center">
					${pageMenu} <!-- 나중에 페이징 처리를 하기 위해 자리를 잡아주자  -->
			</td>
		</tr>
		<tr>
			<td colspan="5" align="right">
				<img src="resources/img/btn_reg.gif" onclick="location.href='insert_form'">
			</td>
		</tr>
	</table>
</body>
</html>









