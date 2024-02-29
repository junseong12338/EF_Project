<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" href ="resources/css/visit.css">
<script src="${pageContext.request.contextPath}/resources/js/httpRequest.js"></script>
<script>
	function del(f){
		let idx = f.idx.value;
		let pwd = f.pwd.value;
		if(pwd ==''){
			alert("비밀번호를 입력하세요")
			return;
		}
		
		if(!confirm("삭제하시겠습니까?")){
			return;
		}
		
		var url = "delete";
		var param = "idx="+idx+"&pwd="+encodeURIComponent(pwd);
		
		sendRequest(url,param,resultFn,'GET');
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
				location.href="list";
		}
	}

	
	function modify(f){
		let ori_pwd = f.ori_pwd.value;
		let pwd = f.pwd.value;
		if(pwd != ori_pwd){
			alert("비밀번호 불일치")
			return;
		}
		
		if(!confirm("수정하시겠습니까?")){
			return;
		}
		

		f.action = "modify_form";
		f.method = "post";
		f.submit();
	}
	
	
	function apiList(){
		let url = "http://kobis.or.kr/kobisopenapi/webservice/rest/boxoffice/searchDailyBoxOfficeList.json?key=f5eef3421c602c6cb7ea224104795888&targetDt=20120101";
		let param = "";
		
		sendRequest(url,param,resultApi,"GET");
		
	}
	
	function resultApi(){
		if(xhr.readyState == 4 && xhr.status== 200){
			let data = xhr.responseText;
			let json = (new Function('return'+data))();
			let res = json.boxOfficeResult.dailyBoxOfficeList[0].movieNm;

			let api = document.getElementById("api");
			api.value = res;
		}
	}
	
</script>

</head>
<body>
	<div id="main_box">
		<h1>방명록 리스트</h1>	
		<input id = "api">
		<input type="button" value="API" onclick="apiList()">
		<input type="button" value="글쓰기" onclick="location.href='insert_form'">
	</div>
	
	<c:forEach var="dto" items="${list}">
		<div class="visit_box">
			<div class="type_content"><pre>${dto.content}</pre>
			<c:if test = "${dto.filename ne 'no_file' }">
				<img src = "resources/upload/${dto.filename }"><br>
			</c:if>
			</div>
			<div class="type_name">작성자 : ${dto.name}(${dto.ip})</div>
			<div class="type_regdate">작성일 ${dto.regdate }</div>			
			<div>	
			
		<form>
			<input type="hidden" name="idx" value="${dto.idx }">
			<input type="hidden" name="ori_pwd" value="${dto.pwd }">
			
			비밀번호 <input type="password" name = "pwd">
			<input type="button" value="수정" onclick="modify(this.form)">
			<input type="button" value="삭제" onclick="del(this.form)">
		</form>
	
			</div>
		</div>

	</c:forEach>
</body>
</html>