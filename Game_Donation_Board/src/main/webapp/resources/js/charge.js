 var IMP = window.IMP; 
        
          var today = new Date();   
          var hours = today.getHours(); // 시
          var minutes = today.getMinutes();  // 분
          var seconds = today.getSeconds();  // 초
          var milliseconds = today.getMilliseconds();
          var makeMerchantUid = hours +  minutes + seconds + milliseconds;
          


          function kakaoPay(f) {
              IMP.init("imp52373275");
    		  let email = document.getElementById("user_email").value;
    		  let name = document.getElementById("user_name").value;
    		  let addr = document.getElementById("user_addr").value;
    		  let point = document.getElementById("user_point").value;
    		  let idx = document.getElementById("user_idx").value;
    		  let payment = document.getElementById("payment").value;
    		  if(payment < 1){
    			  alert('0원 이하로 결제 할 수 없습니다.');
              	  history.go(0);
    		  }else{
        	  if(confirm('결제하시겠습니까?')){
            		  IMP.request_pay({
                          pg : 'kakaopay',
                          merchant_uid: "IMP"+makeMerchantUid, 
                          name : payment+' 포인트',
                          amount : Number(payment),
                          buyer_email : email,
                          buyer_name : name,
                          buyer_tel : '010-1234-5678',
                          buyer_addr : addr,
                          buyer_postcode : '123-456'
            	  }
            	  , function (rsp) { // callback
                      if (rsp.success) {
                    	  console.log('success');
                     	   $.ajax({
                     		    url: "user_point_update",
                     		    data: {"user_email" : email,"payment": payment},
                     		    type: "POST"
                     		  });
                    	  alert("결제성공");
                    	  location.href = "/board/mypage_view";
                      } else {
                        	  console.log(rsp.error_msg);
                          	  alert('결제실패');
                        	  history.go(0);
                      }
                  });
        	  }
    			  
    			  
    		  }
          }
          
          function kgpay(f) {
              IMP.init("imp60461863");
    		  let email = document.getElementById("user_email").value;
    		  let name = document.getElementById("user_name").value;
    		  let addr = document.getElementById("user_addr").value;
    		  let point = document.getElementById("user_point").value;
    		  let idx = document.getElementById("user_idx").value;
    		  let payment = document.getElementById("payment").value;
        	  //class가 btn_payment인 태그를 선택했을 때 작동한다.
    		  if(payment < 1){
    			  alert('0원 이하로 결제 할 수 없습니다.');
              	  history.go(0);
    		  }else{
    			  if(confirm('결제하시겠습니까?')){
    	        		IMP.request_pay({
    					      pg : 'html5_inicis', 
    	  				      pay_method : 'card',
    	                      merchant_uid: "IMP"+makeMerchantUid, 
    	                      name : payment+' 포인트',
    	                      amount : Number(payment),
    	                      buyer_email : email,
    	                      buyer_name : name,
    	                      buyer_tel : '010-1234-5678',
    	                      buyer_addr : addr,
    	                      buyer_postcode : '123-456'
    	        			}, function (rsp) { // callback
    	                            if (rsp.success) {
    	                          	  console.log('success');
    	                           	   $.ajax({
    	                           		    url: "user_point_update",
    	                           		    data: {"user_email" : email,"payment": payment},
    	                           		    type: "POST"
    	                           		  });
    	                          	  alert("결제성공");
    	                          	  location.href = "/board/mypage_view";
    	                            } else {
    	                          	  console.log(rsp.error_msg);
    	                          	  alert('결제실패');
    	                        	  history.go(0);
    	                            }
    	                        });
    	        			}
    			  
    		  }
        	  
        	 
          }