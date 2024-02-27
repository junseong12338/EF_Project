<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>

<!-- 
<ul>
	<li>
  
	</li>
	<li onclick="naverLogout(); return false;">
      <a href="javascript:void(0)">
          <span>네이버 로그아웃</span>
      </a>
	</li>
</ul> -->
	<input type ="button" value="로그아웃" onclick="naverLogout();">

</body>

<script type="text/javascript">
function openPopUp() {
    testPopUp= window.open("https://nid.naver.com/nidlogin.logout");
    testPopUp.document.body.style.visibility = 'hidden';
}
function closePopUp(){
    testPopUp.close();
}

function naverLogout() {
	openPopUp();
	setTimeout(function() {
		closePopUp();
		}, 0);
    location.href = 'naver_out';

}
</script>

</html>