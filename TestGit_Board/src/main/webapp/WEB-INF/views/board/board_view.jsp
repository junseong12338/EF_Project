<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script src="resources/js/httpRequest.js"></script>
<script>
	function reply(){
	
		location.href="reply_form?idx=${dto.idx}&page=${param.page}";
 }
	
	function del(){
		if(!confirm("삭제하시겠습니까?")){
			return;
		}
		
		var pwd = ${dto.pwd};
		var c_pwd = document.getElementById("c_pwd").value;
		
		if(pwd != c_pwd){
			alert("비밀번호 불일치");
			return;
		}
			
		let f = document.f;		
		f.submit();		
	}

	
</script>
<style>
	a{text-decoration: none;}
	table{border-collapse:collapse;}
</style>
</head>
<body>

	<form action ="del" name = "f" method ="post">
		<input type="hidden" name = idx value="${dto.idx}"> 
	</form>
	
	<table border="1"  align="center">
		<caption>:::게시글 상세보기:::</caption>
		<tr>
			<th>제목</th>
			<td>${dto.subject}</td>
		</tr>
		<tr>
			<th>작성자</th>
			<td>${dto.name}</td>
		</tr>
		<tr>
			<th>작성일</th>
			<td>${dto.regdate}</td>
		</tr>
		<tr>
			<th>ip</th>
			<td>${dto.ip}</td>
		</tr>
		<tr>
			<th>내용</th>
			<td width="500px" height="200px"><pre>${dto.content}</pre></td>
		</tr>
		<tr>
			<th>비밀번호</th>
			<td><input type="password" id="c_pwd"></td>
		<tr>
			<td colspan="2">
			<!-- 목록보기 -->
				<img src="resources/img/btn_list.gif" onclick="location.href='board_list?page=${param.page}'">
				
				<c:if test="${dto.depth lt 1 }">
			<!-- 답변  -->
				<img src="resources/img/btn_reply.gif" onclick="reply()">
				</c:if>
			<!-- 삭제 -->
				<img src="resources/img/btn_delete.gif" onclick="del()">
			</td>
		</tr>
	
	</table>
</body>
</html>