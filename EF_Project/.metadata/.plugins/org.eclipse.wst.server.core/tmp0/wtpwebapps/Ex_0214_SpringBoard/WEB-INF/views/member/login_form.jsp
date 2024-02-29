<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>

<script src="resources/js/httpRequest.js"></script>
<script>
	function send(f){
		let id = f.id.value.trim();
		let pwd = f.pwd.value.trim();
		
		//유효성체크
		if(id==''){
			alert("아이디를 입력해주세요");
			return;
		}
		
		if(pwd==''){
			alert("비밀번호를 입력하세요");
			return;
		}
		
		let url = "login";
		let param ="id="+encodeURIComponent(id)+"&pwd="+encodeURIComponent(pwd);
		// 함수 호출시 함수가 정의된곳 으로 값을 가지고 간다.
		sendRequest(url,param,myCheck,"POST");
	}
	
	//콜백메서드
function myCheck(){
	if(xhr.readyState == 4 && xhr.status == 200){
		let data = xhr.responseText;
		let json = eval(data);

		if(json[0].param == 'no_id'){
			alert("아이디가 존재하지 않습니다.");
		}else if(json[0].param == 'no_pwd'){
			alert("비밀번호가 맞지 않습니다.");
		}else {
			alert("로그인 성공");
			location.href="board_list";

		}
	}
}
</script>
</head>
<body>
	<form>
		<table border="1" align="center">
			<caption>:::로그인:::</caption>
			<tr>
				<th>아이디</th>
				<td><input name="id"></td>
			</tr>
			<tr>
				<th>비밀번호</th>
				<td><input name="pwd" type="password"></td>
			</tr>
			<tr>
				<td colspan="2" align="center">
					<input type="button" value="로그인" onclick="send(this.form)">
				</td>
			</tr>
		</table>
	</form>

</body>
</html>