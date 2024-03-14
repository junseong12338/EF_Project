<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" href="resources/css/review_list.css" />
<script src="${pageContext.request.contextPath}/resources/js/httpRequest.js"></script>
<script type="text/javascript">


function send(f){
	
	
	f.action="insert"
	f.submit();
}

function del(f){
	var url = "delete";
	var param = "review_idx="+f.review_idx.value;
	
	sendRequest(url,param,resultFn,"post");
}
function resultFn(){
	if(xhr.readyState == 4 && xhr.status== 200){
		var data = xhr.responseText;
		var json = (new Function('return'+data))();
		
		if(json[0].res == 'no'){
			alert("삭제실패");
			return;
		} 
			alert("삭제성공");
			location.href="/board/review_list";
	}
}
</script>
</head>
<body>
	<div id="main_box">	<!-- 스프링에서는 jsp에서 jsp로 이동할 수 없다. -->
		<form>
           <input type="hidden" value="${user_email.user_idx}" id="user_idx" name="user_idx">
		<table align="left">
			<caption>::리뷰::</caption>
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
	<br><br><br><br>
	<c:forEach var="dto" items="${list}">
	<form action="f">
		<div class="review_box" border="1" align="left">
		<div class="user_img">
			<img src="${user_email.user_img}" alt="image"/>
		</div>
		<div class="user_info">
		<span>&nbsp;&nbsp;${dto.user_name}</span>
		<c:if test="${dto.user_idx eq user_email.user_idx}">
           <input type="hidden" value="${user_email.user_idx}" id="user_idx" name="user_idx">
			<input type="hidden" value="${dto.user_idx}" name="idx" id="review_user_idx" >
			<input type="hidden" value="${dto.review_idx}" name="idx" id="review_idx">
			<input type="button" value="수정" onclick="modify(this.form)">
			<input type="button" value="삭제" onclick="del(this.form)"><br><br>
		</c:if>
		</div>
		<div class="type_content">${dto.review_content}</div><br><br>
		<div>
		<input type="hidden" value="${dto.regdate}" name="regdate" id="regdate" >
		<script>
		var today = new Date();   
		var hours = today.getHours(); // 시
		var minutes = today.getMinutes();  // 분
		var seconds = today.getSeconds();  // 초
		var milliseconds = today.getMilliseconds();
		var makeMerchantUid = hours +  minutes + seconds + milliseconds;
		let regdate = document.getElementById("regdate").value;
		console.log(regdate);
		</script>
		<p id="regdate_write"></p>
		<p>-------------------------------------------------------------------------</p>
		</div>
		</div>
	</form>
	</c:forEach>
</body>
</html>