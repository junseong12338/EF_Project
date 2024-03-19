function toggleApprovalStatus(element) {
    var approvalStatus = element.innerText.trim();
    var projectStatus = element.getAttribute('data-project-status');
    
    if (approvalStatus === "승인 대기") {
        element.innerText = "승인 확인";
        element.classList.add("active");
        element.style.backgroundColor = "#fff";
        element.style.color = "#e75e8d";
        element.setAttribute('data-project-status', '1'); // 새로운 속성 추가하여 프로젝트 상태 표시
        
    } else if(approvalStatus === "승인 확인") {
        element.innerText = "승인 대기";
        element.classList.remove("active");
        element.style.backgroundColor = "";
        element.style.color = "";
        element.setAttribute('data-project-status', '0');
    }
}

function toggleRevertApprovalStatus(element) {
    var approvalStatus = element.innerText.trim();
    
    if (approvalStatus === "해제 대기") {
        element.innerText = "해제 확인";
        element.classList.add("active");
        element.style.backgroundColor = "#fff";
        element.style.color = "#e75e8d";
        element.setAttribute('data-project-status', '0');
        
    } else if(approvalStatus === "해제 확인") {
        element.innerText = "해제 대기";
        element.classList.remove("active");
        element.style.backgroundColor = "";
        element.style.color = "";
        element.setAttribute('data-project-status', '1');
    }
}

function toggleAllApprovalStatus() {
    var toggleButton = document.querySelector('.main-border-button a');
    var isApproveAll = toggleButton.innerText.trim() === "전체 선택";
    toggleButton.innerText = isApproveAll ? "전체 해제" : "전체 선택";

    var approvalButtons = document.querySelectorAll('.main-border-button a');

    if (isApproveAll) {
        approvalButtons.forEach(function(button) {
            toggleApprovalStatus(button);
        });
    } else {
        approvalButtons.forEach(function(button) {
            if (button.innerText.trim() === "승인 확인" || button.innerText.trim() === "해제 확인") {
                toggleApprovalStatus(button);
            }
        });
    }
}

function toggleAllRevertApprovalStatus() {

    var toggleButton = document.querySelector('.main-border-button a');
    var isApproveAll = toggleButton.innerText.trim() === "전체 선택";
    toggleButton.innerText = isApproveAll ? "전체 해제" : "전체 선택";

    var approvalButtons = document.querySelectorAll('.main-border-button a');

    if (isApproveAll) {
        approvalButtons.forEach(function(button) {
            toggleRevertApprovalStatus(button);
        });
    } else {
        approvalButtons.forEach(function(button) {
            if (button.innerText.trim() === "승인 확인" || button.innerText.trim() === "해제 확인") {
                toggleRevertApprovalStatus(button);
            }
        });
    }
}





function applyApproval(status, pageUrl) {
    let approvedProjects;
    
    if (status == 1) 
        approvedProjects = document.querySelectorAll('.main-border-button a[data-project-status="1"]');
    else 
        approvedProjects = document.querySelectorAll('.main-border-button a[data-project-status="0"]');
    
    let approvedProjectData = [];
    
    approvedProjects.forEach(function(project) { 
        let project_idx = project.getAttribute('data-project-idx');
        let project_status = project.getAttribute('data-project-status');
        approvedProjectData.push({ project_idx: project_idx, project_status: project_status });
    });
    
    // JSON 형식으로 데이터 변환
    var requestData = JSON.stringify(approvedProjectData);
    
    // 서버로 POST 요청 전송
    $.ajax({
        type: "POST",
        url: "Status",
        contentType: "application/json", // 요청의 데이터 형식을 JSON으로 설정
        data: requestData, // JSON 형식으로 변환된 데이터 전송
        success: function(response) {
            console.log("서버 요청이 성공적으로 전송되었습니다.");
            alert("적용되었습니다.");
            fetchPageData(pageUrl); // 페이지 데이터를 새로 고침
        },
        error: function(xhr, status, error) {
            console.error("서버 요청이 실패하였습니다.");
        }
    });
}

function fetchPageData(pageUrl) {
    // AJAX 호출을 통해 해당 페이지의 HTML 정보 요청
    $.ajax({
        type: 'GET',
        url: pageUrl,
        success: function(data) {
            var itemHtml = $(data).find('#admin-page-content').html(); // .item 클래스를 가진 요소의 HTML 가져오기
            var paginationHtml = $(data).find('.pagination-container').html(); // .pagination-container 클래스를 가진 요소의 HTML 가져오기

            $('#admin-page-content').html(itemHtml); // .item 클래스를 가진 요소의 HTML을 페이지에 적용
            $('#pagination-container').html(paginationHtml); // .pagination-container 클래스를 가진 요소의 HTML을 페이지에 적용
        },
        error: function(xhr, status, error) {
            console.error('AJAX 호출이 실패하였습니다.');
            // 실패 시 처리할 내용 추가
        }
    });
}

function fetchStatusData(pageUrl) {
    // AJAX 호출을 통해 해당 페이지의 HTML 정보 요청
    $.ajax({
        type: 'GET',
        url: pageUrl,
        success: function(data) {
            var itemHtml = $(data).find('#admin-page-content').html(); // .item 클래스를 가진 요소의 HTML 가져오기
            var paginationHtml = $(data).find('.pagination-container').html(); // .pagination-container 클래스를 가진 요소의 HTML 가져오기
            let statusHtml = $(data).find('#status').html(); 
            
            $('#admin-page-content').html(itemHtml); // .item 클래스를 가진 요소의 HTML을 페이지에 적용
            $('#pagination-container').html(paginationHtml); // .pagination-container 클래스를 가진 요소의 HTML을 페이지에 적용
            $('#status').html(statusHtml);
        },
        error: function(xhr, status, error) {
            console.error('AJAX 호출이 실패하였습니다.');
        }
    });
}
