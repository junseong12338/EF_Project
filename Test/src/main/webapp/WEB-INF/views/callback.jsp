<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>Insert title here</title>
    <script src="https://code.jquery.com/jquery-3.6.0.js"></script>
</head>
<body>
 
<script type="text/javascript" src="https://static.nid.naver.com/js/naveridlogin_js_sdk_2.0.0.js" charset="utf-8"></script>
<script>
    var naverLogin = new naver.LoginWithNaverId({
        clientId: "TXFmtdFUoER8uvpoJOJm", // ë³¸ì¸ê±¸ë¡ ìì , ëì´ì°ê¸° ê¸ì§.
        callbackUrl: "http://localhost:9090/test/home", // ìë¬´ê±°ë ì¤ì 
        isPopup: false,
        callbackHandle: true
    });
    naverLogin.init();
 
    window.addEventListener('load', function () {
    naverLogin.getLoginStatus(function (status) {
 
    if (status) {
        /* console.log(naverLogin.user); */
        var age = naverLogin.user.getAge();
        var birthday = naverLogin.user.getBirthday();
        var email = naverLogin.user.getEmail();
        var gender = naverLogin.user.getGender();
        var id = naverLogin.user.getId();
        var name = naverLogin.user.getName();
        var nickName = naverLogin.user.getNickName();
 
        $.ajax({
            type: 'post',
            url: 'naverSave',
            data: {'n_age':age, 'n_birthday':birthday, 'n_email':email, 'n_gender':gender, 'n_id':id, 'n_name':name, 'n_nickName':nickName},
            dataType: 'text',
            success: function(result) {
                if(result=='ok') {
  
                    location.replace("http://localhost:9090/test/ok") 
                } else if(result=='no') {
                    console.log('ì¤í¨')
                    location.replace("http://localhost:8080/test/home1.do")
                }
            },
            error: function(result) {
                console.log('ì¤ë¥ ë°ì')
            }
        })
 
    } else {
        console.log("callback ì²ë¦¬ì ì¤í¨íììµëë¤.");
    }
    });
});
</script>
</body>
</html>