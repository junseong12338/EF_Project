<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script>
	function send(f){
		
		let pwd = f.pwd.value;
		if(pwd ==''){
			alert("비밀번호를 입력하세요")
			return;
		}
		
		
		f.action="modify";
		f.method ="post";
		f.submit();
	}
</script>

</head>
<body>
	<form>
		<!-- 수정할때 어떤 방명록글에 할껀지에 대한 식별자 -->
		<input type="hidden" name="idx" value="${dto.idx}">
		
		<table border="1" align="center">
			<caption>:::방명록 수정:::</caption>
			<tr>
				<th>작성자</th>
				<td>${dto.name}</td>
			</tr>
			<tr>
				<th>내용</th>
				<td><textarea row="5" cols="50" name="content" 	style="resize:none;"wrap="on">${dto.content }</textarea></td>
			</tr>
			<tr>
				<th>비밀번호</th>
				<td><input type="password" name="pwd" value="${dto.pwd }"></td>
			</tr>
			<tr>
				<td colspan="2" align="center">
					<input type="button" value="수정" onclick="send(this.form)">
					<input type="button" value="취소" onclick="location.href='list'">
				</td>
			</tr>
		</table>
	</form>
</body>
</html>