    var IMP = window.IMP; 
          IMP.init("imp52373275"); 
        
          var today = new Date();   
          var hours = today.getHours(); // 시
          var minutes = today.getMinutes();  // 분
          var seconds = today.getSeconds();  // 초
          var milliseconds = today.getMilliseconds();
          var makeMerchantUid = hours +  minutes + seconds + milliseconds;
          
  
          function requestPay() {
        	  if(confirm('결제하시겠습니까?')){
        		  let payment = document.getElementById("payment").value;
                  IMP.request_pay({
                      pg : 'kakaopay',
                      merchant_uid: "IMP"+makeMerchantUid, 
                      name : '컴퓨터',
                      amount : Number(payment),
                      buyer_email : 'tyghqkr456@naver.com',
                      buyer_name : '석진',
                      buyer_tel : '010-1234-5678',
                      buyer_addr : '인천광역시 부평',
                      buyer_postcode : '123-456'
                  }, function (rsp) { // callback
                      if (rsp.success) {
                          console.log(rsp);
                          alert('결제완료');
                      } else {
                          console.log(rsp);
                          alert('결제실패');
                      }
                  });
        	  }
        	 
          }