<%@ page language="java" contentType="text/html; charset=UTF-8"
pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
  <head>
    <meta charset="UTF-8" />
    <meta name="viewport"content="width=device-width, initial-scale=1, shrink-to-fit=no"/>
    <title>Document</title>
</head>
<body>
    <input type="hidden" value="${status}" id="status">
    <script>
		window.onload = function(){
			const status = document.getElementById("status").value;
            if(status == 0){
                status_one();
            }
		}        
		
		function status_one(){
	        alert("로그인이 필요합니다.");
	        location.href="login_form";			
		}
	</script>
</body>
</html>