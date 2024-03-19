<%@ page language="java" contentType="text/html; charset=UTF-8"
pageEncoding="UTF-8"%> <%@ taglib prefix="c"
uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
  <head>
    <meta charset="UTF-8" />
    <meta
      name="viewport"
      content="width=device-width, initial-scale=1, shrink-to-fit=no"
    />
    <title>EZ Funding</title>
    <!-- Bootstrap core CSS -->
    <link
      href="resources/vendor/bootstrap/css/bootstrap.min.css"
      rel="stylesheet"
    />
    <!-- Additional CSS Files -->
    <link rel="stylesheet" href="resources/assets/css/fontawesome.css" />
    <link
      rel="stylesheet"
      href="resources/assets/css/templatemo-cyborg-gaming.css"
    />
    <link rel="stylesheet" href="resources/assets/css/owl.css" />
    <link rel="stylesheet" href="resources/assets/css/animate.css" />
    <link
      rel="stylesheet"
      href="https://unpkg.com/swiper@7/swiper-bundle.min.css"
    />
    <link rel="stylesheet" href="resources/assets/css/side-bar.css" />
    <link
      rel="stylesheet"
      href="https://fonts.googleapis.com/css?family=Montserrat:400,800"
    />
  </head>
  <body style="font-family: 'Montserrat', sans-serif';">
    <%@ include file="menu.jsp" %>

    <div class="container">
      <div class="row">
        <div class="col-lg-12">
          <div class="page-content">
            <h1>글 내용</h1>
            <div id="content">${dto.project_content}</div>
          </div>
        </div>
      </div>
    </div>

    <footer>
      <div class="container">
        <div class="row">
          <div class="col-lg-12">
            <p>
              Copyright © 2036 Cyborg Gaming Company. All rights reserved.
              <br />Design: TemplateMo Distributed By ThemeWagon
            </p>
          </div>
        </div>
      </div>
    </footer>

    <!-- Bootstrap core JavaScript -->
    <script src="resources/vendor/jquery/jquery.min.js"></script>
    <script src="resources/vendor/bootstrap/js/bootstrap.min.js"></script>

    <script src="resources/assets/js/isotope.min.js"></script>
    <script src="resources/assets/js/owl-carousel.js"></script>
    <script src="resources/assets/js/tabs.js"></script>
    <script src="resources/assets/js/popup.js"></script>
    <script src="resources/assets/js/custom.js"></script>
  </body>
</html>
