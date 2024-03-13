<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script src="${pageContext.request.contextPath}/resources/js/httpRequest.js"></script>
<script type="text/javascript">
function send(f){
	
	
	f.action="insert"
	f.submit();
}

function del(f){
	  let user_idx = document.getElementById("user_idx").value;
	  var r_user_idx = document.getElementById("review_user_idx").value;
	  if(user_idx == r_user_idx){
		alert("작성자가 일치");
	  }else{
		  console.log(user_idx);
		  console.log(r_user_idx);
		alert("작성자가 일치하지않습니다.");
		
	  }
	
}
</script>
</head>
<body>
	<div id="main_box">
		<h1>리뷰리스트</h1>	<!-- 스프링에서는 jsp에서 jsp로 이동할 수 없다. -->
		<form>
		<table border="1" align="center">
			<caption>::새글 작성하기::</caption>
			<tr>
				<th>내용</th>
				<td>		
					<textarea row="5" cols="50" name="review_content" style="resize:none;"wrap="on"></textarea>
				</td>
				<td>	
				<input type="button" value="등록하기" onclick="send(this.form);">
				</td>
			</tr>
		</table>
		</form>
	</div>
	
	<c:forEach var="dto" items="${list}">
		<div class="review_box" border="1" align="center">
		<div class="type_content"><pre>${dto.review_content}</pre></div>
		<div class="type_name">작성자 : ${dto.user_name}</div>
		<div>
		<form>
           <input type="hidden" value="${user_email.user_idx}" id="user_idx" name="user_idx">
			<input type="hidden" value="${dto.user_idx}" name="idx" id="review_user_idx" >
			비밀번호 <input type="password" name = "pwd">
			<input type="button" value="수정" onclick="modify(this.form)">
			<input type="button" value="삭제" onclick="del(this.form)">
		</form>
		</div>
		</div>

	</c:forEach>
</body>
</html>