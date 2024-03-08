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
    <script src="https://code.jquery.com/jquery-3.5.1.js"></script>

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

  <script>
    function updateTime() {
      var now = new Date();
      var year = now.getFullYear();
      var month = now.getMonth() + 1; // getMonth()는 0부터 시작하므로 1을 더합니다.
      var day = now.getDate();
      var hours = now.getHours();
      var minutes = now.getMinutes();
      var seconds = now.getSeconds();

      // 필요에 따라 월과 일이 한 자리 수일 경우 앞에 0을 추가합니다.
      month = month < 10 ? "0" + month : month;
      day = day < 10 ? "0" + day : day;
      hours = hours < 10 ? "0" + hours : hours;
      minutes = minutes < 10 ? "0" + minutes : minutes;
      seconds = seconds < 10 ? "0" + seconds : seconds;

      var currentTimeString =
        year +
        "-" +
        month +
        "-" +
        day +
        " " +
        hours +
        ":" +
        minutes +
        ":" +
        seconds;
      document.getElementById("current-time").textContent =
        " " + currentTimeString;
    }

    setInterval(updateTime, 100); // 매 초마다 현재 시간 업데이트
    updateTime(); // 페이지가 로드될 때 초기 시간 설정
  </script>
  <body style="font-family: 'Montserrat', sans-serif';">
    <%@ include file="menu.jsp" %>

    <div class="container">
      <div class="row">
        <div class="col-lg-12">
          <div class="page-content">
            <!-- ***** Banner Start ***** -->
            <div class="main-banner">
              <div class="row">
                <div class="col-lg-7">
                  <div class="header-text">
                    <h6>Welcome To EZ_Funding</h6>
                    <h4>
                      <em>Join the Quest</em><br />
                      Fund Your Next Adventure!
                    </h4>
                    <div class="main-button">
                      <a href="browse.html">후원하기</a>
                    </div>
                  </div>
                </div>
              </div>
            </div>
            <!-- ***** Banner End ***** -->

            <!-- ***** Featured Games Start ***** -->
            <div class="row" style="margin-top: 30px">
              <div class="col-lg-8">
                <div class="featured-games header-text">
                  <div class="heading-section">
                    <p id="current-time"></p>
                    <h4><em>실시간</em> 급상승</h4>
                  </div>
                  <div class="owl-features owl-carousel">
                    <div class="item">
                      <div class="thumb">
                        <img
                          src="resources/assets/images/featured-01.jpg"
                          alt=""
                        />
                        <div class="hover-effect">
                          <h6>2.4K Streaming</h6>
                        </div>
                      </div>
                      <h4>CS-GO<br /><span>29K Downloads</span></h4>
                      <ul>
                        <li>
                          <i class="fa fa-heart" style="color: #ec6090"></i> 4.8
                        </li>
                        <li><i class="fa fa-download"></i> 2.3M</li>
                      </ul>
                    </div>
                  </div>
                </div>
              </div>
              <!-- ***** Featured Games End ***** -->

              <div class="col-lg-4">
                <div class="top-streamers">
                  <div class="heading-section">
                    <h4><em>인기</em> 프로젝트</h4>
                  </div>
                  <ul>
                    <li>
                      <span>01</span>
                      <img
                        src="resources/assets/images/avatar-01.jpg"
                        alt=""
                        style="
                          max-width: 46px;
                          border-radius: 50%;
                          margin-right: 15px;
                        "
                      />
                      <h6><i class="fa fa-check"></i> LahutaM</h6>
                      <div class="main-button">
                        <a href="#">Follow</a>
                      </div>
                    </li>
                    <li>
                      <span>02</span>
                      <img
                        src="resources/assets/images/avatar-02.jpg"
                        alt=""
                        style="
                          max-width: 46px;
                          border-radius: 50%;
                          margin-right: 15px;
                        "
                      />
                      <h6><i class="fa fa-check"></i> Kengan</h6>
                      <div class="main-button">
                        <a href="#">Follow</a>
                      </div>
                    </li>
                    <li>
                      <span>03</span>
                      <img
                        src="resources/assets/images/avatar-03.jpg"
                        alt=""
                        style="
                          max-width: 46px;
                          border-radius: 50%;
                          margin-right: 15px;
                        "
                      />
                      <h6><i class="fa fa-check"></i> Areluwa</h6>
                      <div class="main-button">
                        <a href="#">Follow</a>
                      </div>
                    </li>
                    <li>
                      <span>04</span>
                      <img
                        src="resources/assets/images/avatar-04.jpg"
                        alt=""
                        style="
                          max-width: 46px;
                          border-radius: 50%;
                          margin-right: 15px;
                        "
                      />
                      <h6><i class="fa fa-check"></i> Omeg</h6>
                      <div class="main-button">
                        <a href="#">Follow</a>
                      </div>
                    </li>
                    <li>
                      <span>05</span>
                      <img
                        src="resources/assets/images/avatar-01.jpg"
                        alt=""
                        style="
                          max-width: 46px;
                          border-radius: 50%;
                          margin-right: 15px;
                        "
                      />
                      <h6><i class="fa fa-check"></i> GangTeam</h6>
                      <div class="main-button">
                        <a href="#">Follow</a>
                      </div>
                    </li>
                  </ul>
                </div>
              </div>
            </div>
            <!-- ***** Featured Games End ***** -->

            <!-- ***** Most Popular Start ***** -->
            <div class="most-popular">
              <div class="row">
                <div class="col-lg-12">
                  <div class="heading-section">
                    <h4><em>신규 등록</em> 프로젝트</h4>
                  </div>
                  <div class="row">
                    <div class="col-lg-3 col-sm-6">
                      <div class="item">
                        <img
                          src="resources/assets/images/popular-01.jpg"
                          alt=""
                        />
                        <h4>Fortnite<br /><span>Sandbox</span></h4>
                        <ul>
                          <li><i class="fa fa-star"></i> 4.8</li>
                          <li><i class="fa fa-download"></i> 2.3M</li>
                        </ul>
                      </div>
                    </div>
                    <div class="col-lg-3 col-sm-6">
                      <div class="item">
                        <img
                          src="resources/assets/images/popular-02.jpg"
                          alt=""
                        />
                        <h4>PubG<br /><span>Battle S</span></h4>
                        <ul>
                          <li><i class="fa fa-star"></i> 4.8</li>
                          <li><i class="fa fa-download"></i> 2.3M</li>
                        </ul>
                      </div>
                    </div>
                    <div class="col-lg-3 col-sm-6">
                      <div class="item">
                        <img
                          src="resources/assets/images/popular-03.jpg"
                          alt=""
                        />
                        <h4>Dota2<br /><span>Steam-X</span></h4>
                        <ul>
                          <li><i class="fa fa-star"></i> 4.8</li>
                          <li><i class="fa fa-download"></i> 2.3M</li>
                        </ul>
                      </div>
                    </div>
                    <div class="col-lg-3 col-sm-6">
                      <div class="item">
                        <img
                          src="resources/assets/images/popular-04.jpg"
                          alt=""
                        />
                        <h4>CS-GO<br /><span>Legendary</span></h4>
                        <ul>
                          <li><i class="fa fa-star"></i> 4.8</li>
                          <li><i class="fa fa-download"></i> 2.3M</li>
                        </ul>
                      </div>
                    </div>
                    <div class="col-lg-3 col-sm-6">
                      <div class="item">
                        <img
                          src="resources/assets/images/popular-04.jpg"
                          alt=""
                        />
                        <h4>CS-GO<br /><span>Legendary</span></h4>
                        <ul>
                          <li><i class="fa fa-star"></i> 4.8</li>
                          <li><i class="fa fa-download"></i> 2.3M</li>
                        </ul>
                      </div>
                    </div>

                    <div class="col-lg-3 col-sm-6">
                      <div class="item">
                        <img
                          src="resources/assets/images/popular-04.jpg"
                          alt=""
                        />
                        <h4>CS-GO<br /><span>Legendary</span></h4>
                        <ul>
                          <li><i class="fa fa-star"></i> 4.8</li>
                          <li><i class="fa fa-download"></i> 2.3M</li>
                        </ul>
                      </div>
                    </div>
                    <div class="col-lg-3 col-sm-6">
                      <div class="item">
                        <img
                          src="resources/assets/images/popular-07.jpg"
                          alt=""
                        />
                        <h4>Warface<br /><span>Max 3D</span></h4>
                        <ul>
                          <li><i class="fa fa-star"></i> 4.8</li>
                          <li><i class="fa fa-download"></i> 2.3M</li>
                        </ul>
                      </div>
                    </div>
                    <div class="col-lg-3 col-sm-6">
                      <div class="item">
                        <img
                          src="resources/assets/images/popular-08.jpg"
                          alt=""
                        />
                        <h4>Warcraft<br /><span>Legend</span></h4>
                        <ul>
                          <li><i class="fa fa-star"></i> 4.8</li>
                          <li><i class="fa fa-download"></i> 2.3M</li>
                        </ul>
                      </div>
                    </div>
                    <div class="col-lg-12">
                      <div class="main-button">
                        <a href="browse.html">프로젝트 전체보기</a>
                      </div>
                    </div>
                  </div>
                </div>
              </div>
            </div>
            <!-- ***** Most Popular End ***** -->

            <!-- ***** Gaming Library Start ***** -->
            <div class="gaming-library">
              <div class="col-lg-12">
                <div class="heading-section">
                  <h4><em>등록 예정</em> 프로젝트</h4>
                </div>
                <div class="item">
                  <ul>
                    <li>
                      <img
                        src="resources/assets/images/game-01.jpg"
                        alt=""
                        class="templatemo-item"
                      />
                    </li>
                    <li>
                      <h4>Dota 2</h4>
                      <span>Sandbox</span>
                    </li>
                    <li>
                      <h4>Date Added</h4>
                      <span>24/08/2036</span>
                    </li>
                    <li>
                      <h4>Hours Played</h4>
                      <span>634 H 22 Mins</span>
                    </li>
                    <li>
                      <h4>Currently</h4>
                      <span>Downloaded</span>
                    </li>
                    <li>
                      <div class="main-border-button">
                        <a href="#">Donwload</a>
                      </div>
                    </li>
                  </ul>
                </div>
                <div class="item">
                  <ul>
                    <li>
                      <img
                        src="resources/assets/images/game-02.jpg"
                        alt=""
                        class="templatemo-item"
                      />
                    </li>
                    <li>
                      <h4>Fortnite</h4>
                      <span>Sandbox</span>
                    </li>
                    <li>
                      <h4>Date Added</h4>
                      <span>22/06/2036</span>
                    </li>
                    <li>
                      <h4>Hours Played</h4>
                      <span>740 H 52 Mins</span>
                    </li>
                    <li>
                      <h4>Currently</h4>
                      <span>Downloaded</span>
                    </li>
                    <li>
                      <div class="main-border-button">
                        <a href="#">Donwload</a>
                      </div>
                    </li>
                  </ul>
                </div>
                <div class="item last-item">
                  <ul>
                    <li>
                      <img
                        src="resources/assets/images/game-03.jpg"
                        alt=""
                        class="templatemo-item"
                      />
                    </li>
                    <li>
                      <h4>CS-GO</h4>
                      <span>Sandbox</span>
                    </li>
                    <li>
                      <h4>Date Added</h4>
                      <span>21/04/2036</span>
                    </li>
                    <li>
                      <h4>Hours Played</h4>
                      <span>892 H 14 Mins</span>
                    </li>
                    <li>
                      <h4>Currently</h4>
                      <span>Downloaded</span>
                    </li>
                    <li>
                      <div class="main-border-button">
                        <a href="#">Donwload</a>
                      </div>
                    </li>
                  </ul>
                </div>
              </div>
              <div class="col-lg-12">
                <div class="main-button">
                  <a href="profile.html">등록 예정 프로젝트 전체보기</a>
                </div>
              </div>
            </div>
            <!-- ***** Gaming Library End ***** -->
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
