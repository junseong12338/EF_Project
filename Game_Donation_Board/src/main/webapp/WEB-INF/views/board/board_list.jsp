<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<!DOCTYPE html>
<html>
 <!-- jQuery -->
        <script type="text/javascript" src="https://code.jquery.com/jquery-1.12.4.min.js" ></script>
        <!-- iamport.payment.js -->
        <script type="text/javascript" src="https://cdn.iamport.kr/js/iamport.payment-1.2.0.js"></script>
        <script>
          var IMP = window.IMP; 
          IMP.init("imp52373275"); 
          
          var today = new Date();   
          var hours = today.getHours(); // 시
          var minutes = today.getMinutes();  // 분
          var seconds = today.getSeconds();  // 초
          var milliseconds = today.getMilliseconds();
          var makeMerchantUid = hours +  minutes + seconds + milliseconds;

         
  
          function requestPay() {
        	 	  let payment = document.getElementById("payment").value;
        	 	  if(Number(payment)>0){
        	 		  IMP.request_pay({
                          pg : 'kakaopay',
                          merchant_uid: "IMP"+makeMerchantUid, 
                          name : '포인트결제',
                          amount : Number(payment),
                          buyer_email : 'tyghqkr456@naver.com',
                          buyer_name : '석진',
                          buyer_tel : '010-1234-5678',
                          buyer_addr : '인천광역시 부평',
                          buyer_postcode : '123-456'
                      }, function (rsp) { // callback
                          if (rsp.success) {
                              console.log(rsp);
                          } else {
                              console.log(rsp);
                          }
                      });
        	 	  }else{
        	 		  alert("금액이 0원 이하일수없습니다.");
        	 		 document.getElementById("payment").value="";
        	 	  }
        		
          }
          $(document).ready(function() {			
              $("#payment").keyup(function() {
                  var replace_text = $(this).val().replace(/[^-0-9]/g, '');
                  $(this).val(replace_text);
              });
          });
      </script>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<style>
	a{text-decoration: none;}
	table{
		border-collapse: collapse;
		margin:0 auto;
	}
	
	img{
		cursor: pointer;
	}
</style>
</head>
<body>
석진 테스트
	<table border="1">
		<tr>
			<td colspan="5"><img src="resources/img/title_04.gif"></td>
		</tr>
		<tr>
			<td colspan = "5" align="right">
			<c:choose>
				<c:when test="${empty id }">
					<input type ="button" value="로그인" onclick="location.href='login_form'">
					<input type ="button" value="회원가입" onclick="location.href='member_insert_form'">
				</c:when>
				<c:when test="${not empty id }">
					<input type ="button" value="로그아웃" onclick="location.href='logout'">
				</c:when>
			</c:choose>
			</td>
		</tr>
		<tr>
			<th>번호</th>
			<th>제목</th>
			<th>작성자</th>
			<th>작성일</th>
			<th>조회수</th>
		</tr>
		<c:forEach var="dto" items="${list}">
		<tr>
			<td>${dto.idx}</td>
			<td>
				<!-- 댓글일 경우 들여쓰기 -->
				<c:forEach begin="1" end="${dto.depth}">&nbsp;</c:forEach>
				<!-- 댓글기호 -->
				<c:if test="${dto.depth ne 0 }">ㄴ</c:if>
				
				
				<!-- 삭제되지 않은 글일 경우 클릭이 가능 -->
				<c:if test="${dto.del_info ne -1}">
				<a href="view?idx=${dto.idx}&page=${param.page}">
					<font color="black">${dto.subject}</font>
				</a>
				</c:if>
				
				<!-- 삭제된 게시글을 클릭할 수 없도록 처리 -->
				<c:if test="${dto.del_info eq -1}">
					<font color="gray">${dto.subject}</font>
				</c:if>
					
			</td>
			<td>${dto.name}</td>
			
			
			<!-- 삭제가 되지 않은 게시물은 정상적으로 표시 -->
			<c:if test="${dto.del_info ne -1}">
			<td>${fn:split(dto.regdate,' ')[0]}</td>
			</c:if>
			
			<!-- 삭제가 된 게시물은 unkown으로 표시 -->
			<c:if test="${dto.del_info eq -1}">
			<td>unkown</td>
			</c:if>
			
			<td>${dto.readhit}</td>
		</tr>
		</c:forEach>
		<tr>
			<td colspan="5" align="center">
					${pageMenu} <!-- 나중에 페이징 처리를 하기 위해 자리를 잡아주자  -->
			</td>
		</tr>
		<tr>
			<td colspan="5" align="right">
				<img src="resources/img/btn_reg.gif" onclick="location.href='insert_form'">
			</td>
		</tr>
		
		<tr>
			<td colspan="5" align="left">
				<button onclick="requestPay()" style="margin-left: 135px; background-color: #bb4ab1; color: #fff; border: none; padding: 5px 10px; border-radius: 5px; cursor: pointer;">충전하기</button>
			</td>
		</tr>
		<tr>
			<td colspan="5" align="left">
				<input type="number" id="payment" placeholder = "숫자만 입력가능"/>
			</td>
		</tr>
	</table>
</body>
</html>









