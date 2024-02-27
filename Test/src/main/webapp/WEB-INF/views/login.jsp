<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script type="text/javascript" src="https://static.nid.naver.com/js/naverLogin_implicit-1.0.2.js" charset="utf-8"></script>
<script type="text/javascript" src="http://code.jquery.com/jquery-1.11.3.min.js"></script>
</head>

    
    <section class="bg-light">
        <div class="container py-4">
            <div class="row align-items-center justify-content-between">
                <a class="navbar-brand h1 text-center" href="index.do">
                    <span class="text-dark h4">도시</span> <span class="text-primary h4">거북</span>                 
                </a>
            </div>
          <c:if test="${signIn == null}">
            <form action="${cpath}/signIn.do" method="post">
                <div class="form-group">
                    <input type="text" class="form-control" name="mb_id" placeholder="아이디">
                </div>
                <div class="form-group">
                    <input type="password" class="form-control" name="mb_pwd" placeholder="비밀번호">
                </div>
                <div class="form-check">
                    <input class="form-check-input" type="checkbox" value="" id="flexCheckDefault">
                    <label class="form-check-label text-secondary" for="flexCheckDefault">
                      로그인 상태 유지
                    </label>
                </div>
                <div class="d-grid gap-2">
                    <button class="btn btn-primary btn-lg" type="submit">로그인</button>
                </div>
            </form>
                <div class="otherButton text-center">
                    <span class="text-secondary">다른 계정으로 로그인</span>
                    <button type ="button" class = "btn" onclick="location.href='${urlNaver}'">네이버</button>
                    <button type ="button" class = "btn"><img src='./resources/img/logoKakao4.png'></button>
                    <button type ="button" class = "btn"><img src='./resources/img/logoGoogle4.png'></button>
                </div>
                <div class="row">
                  <div class="col-lg-6 col-sm-12 text-lg-start text-center">
                      <button type="button" class="btn text-secondary" onclick="location.href='signUp.do'">회원가입</button>
                  </div>
                  <div class="col-lg-6 col-sm-12 text-lg-end text-center">
                      <button type="button" class="btn text-secondary">아이디/비밀번호 찾기</button>
                  </div>
                </div>
              </c:if>
               <c:if test="${signIn != null}">
                      <button type="button" class="btn btn-primary btn-lg" onclick="location.href='logout.do'">로그아웃</button>
               </c:if>
        </div>
    </section>
</html>