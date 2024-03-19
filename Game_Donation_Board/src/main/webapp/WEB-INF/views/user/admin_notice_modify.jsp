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
       width: 246px;
        height: 300px;
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
      
      td{       
	    width: 150px;
	    height: 30px;
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
              id="admin_notice_editor"
            >
            <input type="hidden" id="admin_notice_idx" name="admin_notice_idx" value="${dto.ad_notice_idx}">
              
              <div id="project_editor">
                <div class="title-box">
                  <div id="title">
                    제목 :
                    <input
                      type="text"
                      name="admin_notice_title"
                      id="admin_notice_title"
                      value="${dto.ad_notice_title}"
                    />
                  </div>
                </div>

                <div class="content-box">
                  <textarea id="summernote" name="editordata">${dto.ad_notice_content}</textarea>
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
            ["insert", ["picture","video"]],
            // 코드보기, 확대해서보기, 도움말
            ["view", ["help"]],
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
              console.log("callbacks함수 호출");
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
        $(".btn.btn-primary.note-btn.note-btn-primary.note-video-btn").attr("id","video_btn");
        
      	//썸머노트 동영상 링크 처리
        $(document).on('click', '#video_btn', function(){
          // 주어진 YouTube URL
          const url = $(".note-video-url").val();
          // YouTube 동영상 ID를 추출하는 정규표현식
          var videoIdRegex = /(?:https?:\/\/)?(?:www\.)?(?:youtube\.com\/(?:[^\/\n\s]+\/\S+\/|(?:v|e(?:mbed)?)\/|\S*?[?&]v=)|youtu\.be\/)([a-zA-Z0-9_-]{11})/;

          // 동영상 ID 추출
          var match = url.match(videoIdRegex);
          var videoId = match && match[1];
          
          //URL유효성 검사

          const iframeUrl = "https://www.youtube.com/embed/" + videoId;

          console.log("videoId : " + videoId);
          var iframe = $('<iframe>', {
              src: iframeUrl, // 아이프레임의 소스 URL
              frameborder: '0', // 테두리 없음
              width: '100%', // 가로 크기 설정
              height: '500px' // 세로 크기 설정
          });

          if(!(videoId === null)){
        	  console.log("1");
          	$('.note-editable').append(iframe);
        	  $(".note-video-url").val("");    	  
          }else{
        	  console.log("2");
        	  alert('올바르지않는 형식입니다.');
        	  $(".note-video-url").val("");    	  
          }
        })

      });//도큐먼트 레디 닫히는부분

      

      //수정완료 함수
      function send(f) {
        const title = document.getElementById("admin_notice_title");
        const content = document.getElementById("summernote");
        

        if (title.value == "") {
          alert("제목을 입력해주세요");
          title.focus();
          return;
        }

        if (content.value == "") {
          alert("내용을 입력해주세요");
          content.focus();
          return;
        }

        
        removeBeforeUnload();
        f.action = "admin_summernote_modify";
        f.submit();
      }

      function removeBeforeUnload() {
          $(window).off('beforeunload');
      }
      
      $(window).on('beforeunload', function() {
    	    return "정말로 벗어나시겠습니까?";
    	});

    	$(window).on('unload', function() {
    	    // 더미 데이터 삭제 작업 수행
    	    $.ajax({
    	    	
    	        url: "pageOutDelete",
    	        type: "POST",
    	        success: function(response) {
    	            console.log("더미 데이터 삭제 성공");
    	        },
    	        error: function(xhr, status, error) {
    	            console.error("더미 데이터 삭제 실패:", error);
    	        }
    	    });
    	});
    </script>
  </body>
</html>
