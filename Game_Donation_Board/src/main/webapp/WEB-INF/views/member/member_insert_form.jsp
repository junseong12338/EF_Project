<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script src="resources/js/httpRequest.js"></script>
<script type="text/javascript">

	let b_idCheck = false;
	
	function check_id(){
		let id = document.getElementById("id").value.trim();
		
		let url = "check_id";
		let param = "id="+id;
		
		sendRequest(url,param,resultFn,"POST");
	}
	
	function resultFn(){
		if(xhr.readyState== 4 && xhr.status == 200){
			let span = document.getElementById("check");
			let data = xhr.responseText;
			
			let json = (new Function('return' + data))();
			
			if(json[0].res == 'no'){
				span.innerHTML = "이미 있는 아이디 입니다.";
				b_idCheck = false;
				
			} else if(json[0].res == 'yes'){
				span.innerHTML = "사용가능한 아이디 입니다.";
				b_idCheck = true;
			}
			
		}
	}
	
	function send(f){
		let id = f.id.value.trim();
		let pwd = f.pwd.value.trim();
		
		if(id == ''){
			alert('아이디를 입력해주세요');
			return;
		}
		
		if(pwd == ''){
			alert('비밀번호를 입력해주세요');
			return;
		}
		
		if(!b_idCheck){
			alert('다른 아이디를 사용해주세요');
			return;
		}
		
		f.action = "member_insert";
		f.method = "POST";
		f.submit();
	}
</script>
</head>
<body>
	<form>
		<table border="1" align="center">
			<caption>:::회원가입:::</caption>
			<tr>
				<th>아이디</th>
				<td><input name="id" id="id" oninput="check_id()"><span id="check"></span></td>
			</tr>
			<tr>
				<th>비밀번호</th>
				<td><input name="pwd" type="password"></td>
			</tr>
			<tr>
				<th>이름</th>
				<td><input name="name"></td>
			</tr>
			<tr>
				<th>이메일</th>
				<td><input name="email"></td>
			</tr>
			<tr>
				<td colspan="2" align="center">
					<input type="button" value="가입" onclick="send(this.form)">
					<input type="button" value="취소" onclick="location.href='board_list'">
				</td>
			</tr>
				
		</table>
	</form>
</body>
</html>
