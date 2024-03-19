<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>

<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<form>
		<table border="1" align="center">
			<caption>::공지 작성::</caption>
			
			<tr>
				<th>작성자</th>
				<td><input name="user_name" style="width:250px;"></td>
			</tr>
			<tr>
				<th>내용</th>
				<td>
					<textarea name="notice_content" style="resize:none;"wrap="on"></textarea>
				</td>
			</tr>
			
			<tr>
				<td colspan="2" align="center">
				<input type="button" value="등록하기" onclick="send(this.form);">
				<input type="button" value="목록으로" onclick="location.href='notice_list'">
			</td>
			</tr>
		
		</table>
	
	</form>
	
	<script>
function send(f){
		
		
		f.action="insert"
		f.submit();
	}
	</script>
	
</body>
</html>