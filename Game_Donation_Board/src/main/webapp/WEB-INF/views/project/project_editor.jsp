<%@ page language="java" contentType="text/html; charset=UTF-8"
pageEncoding="UTF-8"%> <%@ taglib prefix="c"
uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="en">
  <head>
    <meta charset="UTF-8" />
    <meta
      name="viewport"
      content="width=device-width, initial-scale=1, shrink-to-fit=no"
    />
    <title>EZ Funding</title>

    <!-- Bootstrap core CSS -->
    <link
      rel="stylesheet"
      href="https://cdn.jsdelivr.net/npm/bootstrap@3.4.1/dist/css/bootstrap.min.css"
    />

    <!-- Additional CSS Files -->
    <!-- <link rel="stylesheet" href="resources/assets/css/fontawesome.css" /> -->
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

    <!-- 썸머노트 관련 js, css, cdn -->
    <link
      href="https://cdn.jsdelivr.net/npm/summernote@0.8.18/dist/summernote-lite.min.css"
      rel="stylesheet"
    />
    <style>
      input[type="number"]::-webkit-outer-spin-button,
      input[type="number"]::-webkit-inner-spin-button {
        -webkit-appearance: none;
        margin: 0;
      }
      #project_editor {
        display: flex;
        flex-direction: column;
      }

      form div {
        margin-bottom: 20px;
        color: white;
      }

      form div input {
        color: black;
      }

      form .title-box {
        display: flex;
        flex-direction: row;
        justify-content: center;
      }

      form .submit-button {
        display: flex;
        justify-content: right;
      }

      form .project-setting {
        display: flex;
        justify-content: space-between;
      }
      .image-container {
        width: 300px;
        height: 200px;
        overflow: hidden;
      }

      .image-container img {
        width: 100%;
        height: 100%;
      }

      form .image-category-setting {
        display: flex;
        justify-content: space-between;
      }
    </style>
  </head>
  <body style="font-family: 'Montserrat', sans-serif">
    <%@ include file="../board/menu.jsp" %>

    <div class="container">
      <div class="row">
        <div class="col-lg-12">
          <div class="page-content">
            <form
              method="post"
              enctype="multipart/form-data"
              id="project_editor"
            >
              <input
                type="hidden"
                name="project_main_img"
                id="project_main_img"
              />
              <div id="project_editor">
                <div class="title-box">
                  <div id="title">
                    제목 :
                    <input
                      type="text"
                      name="project_title"
                      id="project_title"
                    />
                  </div>
                </div>

                <div class="content-box">
                  <textarea id="summernote" name="editordata"></textarea>
                </div>
                <div class="project-setting">
                  <div class="date-setting">
                    시작날짜 :
                    <input
                      type="date"
                      name="start_date"
                      id="start_date"
                      onchange="set_min_endDate()"
                    />
                    종료날짜 :
                    <input type="date" name="end_date" id="end_date" />
                  </div>
                  <div class="targetmoney-setting">
                    목표 후원금액 :
                    <input
                      type="number"
                      name="target"
                      id="target"
                      onchange="target_maxvalue()"
                    />
                  </div>
                </div>
                <div class="image-category-setting">
                  <div class="image-setting-container">
                    <div class="image-container">
                      <img
                        src="resources/img/preview.jpg"
                        class="img-rounded"
                        id="main_image_show"
                      />
                    </div>
                    메인이미지를 선택해주세요 :
                    <input
                      type="file"
                      name="main_image"
                      id="main_image"
                      onchange="main_image_send()"
                    />
                  </div>
                  <div class="category-container">
                    카테고리 설정<br />
                    <input type="checkbox" name="category" value="1" />
                    1 &nbsp;
                    <input type="checkbox" name="category" value="2" />
                    2<br />
                    <input type="checkbox" name="category" value="3" />
                    3 &nbsp;
                    <input type="checkbox" name="category" value="4" />
                    4<br />
                    <input type="checkbox" name="category" value="5" />
                    5 &nbsp;
                    <input type="checkbox" name="category" value="6" />
                    6<br />
                    <input type="checkbox" name="category" value="7" />
                    7 &nbsp;
                    <input type="checkbox" name="category" value="8" />
                    8<br />
                    <input type="checkbox" name="category" value="9" />
                    9 &nbsp;
                    <input type="checkbox" name="category" value="10" />
                    10<br />
                  </div>
                </div>
                <div class="submit-button">
                  <button
                    type="button"
                    class="btn btn-default"
                    onclick="send(this.form)"
                  >
                    작성 완료
                  </button>
                </div>
              </div>
            </form>
          </div>
        </div>
      </div>
    </div>

    <footer>
      <div class="container">
        <div class="row">
          <div class="col-lg-12">
            <p>
              Copyright © 2036 Cyborg Gaming Company. All rights reserved.<br />
              Design: TemplateMo Distributed By ThemeWagon
            </p>
          </div>
        </div>
      </div>
    </footer>

    <!-- jQuery -->
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>

    <!-- 부트스트랩 3.4.1 -->
    <!-- Latest compiled and minified CSS -->
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@3.4.1/dist/js/bootstrap.min.js"></script>

    <!-- 썸머노트 관련 js, cdn -->
    <script src="https://cdn.jsdelivr.net/npm/summernote@0.8.18/dist/summernote.min.js"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/summernote/0.8.18/lang/summernote-ko-KR.min.js"></script>
    <script src="resources/js/summernote-lite.js"></script>
    <script src="resources/js/summernote-ko-KR.js"></script>

    <script>
      $(document).ready(function () {
        // 현재 날짜를 가져오는 함수
        function getCurrentDate() {
          const today = new Date();
          const year = today.getFullYear();
          let month = today.getMonth() + 1;
          let day = today.getDate();

          // 월과 일이 한 자리 숫자인 경우 앞에 0을 추가
          month = month < 10 ? "0" + month : month;
          day = day < 10 ? "0" + day : day;
          return year + "-" + month + "-" + day;
        }

        // 현재 날짜 이후의 날짜만 선택 가능하도록 설정
        const futureDateInput = document.getElementById("start_date");
        futureDateInput.min = getCurrentDate();

        $("#summernote").summernote({
          height: 700, // 에디터 높이
          minHeight: null, // 최소 높이
          maxHeight: null, // 최대 높이
          focus: true, // 에디터 로딩후 포커스를 맞출지 여부
          lang: "ko-KR", // 한글 설정
          toolbar: [
            // 글꼴 설정
            ["fontname", ["fontname"]],
            // 글자 크기 설정
            ["fontsize", ["fontsize"]],
            // 굵기, 기울임꼴, 밑줄,취소 선, 서식지우기
            [
              "style",
              ["bold", "italic", "underline", "strikethrough", "clear"],
            ],
            // 글자색
            ["color", ["forecolor", "color"]],
            // 표만들기
            ["table", ["table"]],
            // 글머리 기호, 번호매기기, 문단정렬
            ["para", ["ul", "ol", "paragraph"]],
            // 줄간격
            ["height", ["height"]],
            // 그림첨부, 링크만들기, 동영상첨부
            ["insert", ["picture", "link", "video"]],
            // 코드보기, 확대해서보기, 도움말
            ["view", ["codeview", "fullscreen", "help"]],
          ],
          // 추가한 글꼴
          fontNames: [
            "Arial",
            "Arial Black",
            "Comic Sans MS",
            "Courier New",
            "맑은 고딕",
            "궁서",
            "굴림체",
            "굴림",
            "돋음체",
            "바탕체",
          ],

          //콜백함수
          callbacks: {
            onImageUpload: function (files, editor, welEditable) {
              //파일 다중 업로드
              for (var i = files.length - 1; i >= 0; i--) {
                uploadSummernoteImageFile(files[i], this);
              }
            },

            onMediaDelete: function ($target, editor, $editable) {
              var deletedImageUrl = $target.attr("src").split("/").pop();
              deleteSummernoteImageFile(deletedImageUrl);
            },
          },
        });

        //섬머노트 이미지,파일 업로드 함수
        function uploadSummernoteImageFile(file, el) {
          data = new FormData();
          data.append("file", file);

          $.ajax({
            data: data,
            type: "POST",
            url: "uploadSummernoteImageFile",
            contentType: false,
            enctype: "multipart/form-data",
            processData: false,
            success: function (data) {
              console.log(data.url);
              $(el).summernote("editor.insertImage", data.url);
            },
          });
        }
        // 이미지,파일 삭제 함수
        function deleteSummernoteImageFile(imageName) {
          data = new FormData();
          data.append("file", imageName);
          $.ajax({
            data: data,
            type: "POST",
            url: "deleteSummernoteImageFile",
            contentType: false,
            enctype: "multipart/form-data",
            processData: false,
          });
        }
      });

      //메인이미지(썸네일) 등록 ajax함수
      function main_image_send() {
        var preview = new FileReader();
        preview.onload = function (e) {
          // img id 값
          document.getElementById("main_image_show").src = e.target.result;
        };
        // input id 값
        preview.readAsDataURL(document.getElementById("main_image").files[0]);

        data = new FormData();

        data.append("file", document.getElementById("main_image").files[0]);

        $.ajax({
          data: data,
          type: "POST",
          url: "uploadSummernoteImageFile",
          contentType: false,
          enctype: "multipart/form-data",
          processData: false,
          success: function (data) {
            console.log(data.url);
            document.getElementById("project_main_img").value = data.url;
          },
        });
      }

      //작성완료 함수
      function send(f) {
        const title = document.getElementById("project_title").value;
        const content = document.getElementById("summernote").value;
        const target = document.getElementById("target").value;
        const main_image = document.getElementById("main_image").value;
        const start_date = document.getElementById("start_date").value;
        const end_date = document.getElementById("end_date").value;
        const category = [];
        const checkboxes =
          document.getElementById("project_editor").elements["category"];

        for (let i = 0; i < checkboxes.length; i++) {
          const checkbox = checkboxes[i];

          // 체크된 체크박스의 값을 배열에 추가
          if (checkbox.checked) {
            category.push(checkbox.value);
          }
        }

        console.log("title : " + title);
        console.log("content : " + content);
        console.log("target : " + target);
        console.log("main_image : " + main_image);
        console.log("start_date : " + start_date);
        console.log("end_date : " + end_date);
        console.log("category : " + category);

        if (content == "") {
          alert("내용을 입력해주세요");
          return;
        }

        // f.action = "summernote_send";
        // f.submit();
      }

      //목표금액 최대수치 초과 시 value 재설정
      function target_maxvalue() {
        console.log(document.getElementById("target").value);
        if (document.getElementById("target").value > 100000000) {
          alert("목표금액은 100.000.000원을 초과할 수 없습니다.");
          document.getElementById("target").value = 100000000;
        }
      }

      function set_min_endDate() {
        const start_date = document.getElementById("start_date").value;

        const dateObject = new Date(start_date);

        const year = dateObject.getFullYear();
        let month = dateObject.getMonth() + 1;
        let day = dateObject.getDate() + 1;

        // 월과 일이 한 자리 숫자인 경우 앞에 0을 추가
        month = month < 10 ? "0" + month : month;
        day = day < 10 ? "0" + day : day;

        const futureDateInput = document.getElementById("end_date");
        futureDateInput.min = year + "-" + month + "-" + day;
      }
    </script>
  </body>
</html>