<%@ page language="java" contentType="text/html; charset=UTF-8"
pageEncoding="UTF-8"%> <%@ taglib prefix="c"
uri="http://java.sun.com/jsp/jstl/core" %> <%@ taglib prefix="fn"
uri="http://java.sun.com/jsp/jstl/functions" %>
<!DOCTYPE html>
<html>
  <head>
    <meta charset="UTF-8" />
    <title>Insert title here</title>
    <!-- 내꺼 -->
    <script></script>
  </head>
  <body>
    <input
      type="button"
      onclick="location.href='editor_test'"
      value="에디터 페이지 이동"
    />
    <form action="project_test" method="get">
      <input type="text" name="idx" placeholder="글번호" />
      <input type="submit" value="글보기" />
    </form>
  </body>
</html>
