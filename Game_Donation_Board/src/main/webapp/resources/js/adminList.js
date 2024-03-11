	function toggleApprovalStatus(element) {
	    var approvalStatus = element.innerText.trim();
	    if (approvalStatus === "승인 대기") {
	        element.innerText = "승인 확인";
	        element.classList.add("active");
	        element.style.backgroundColor = "#fff";
	        element.style.color = "#e75e8d";
	                element.dataset.projectStatus = "1"; // 새로운 속성 추가하여 프로젝트 상태 표시
	        
	        
	    } else {
	        element.innerText = "승인 대기";
	        element.classList.remove("active");
	        element.style.backgroundColor = "";
	        element.style.color = "";
	                element.dataset.projectStatus = "0"; // 새로운 속성 추가하여 프로젝트 상태 표시
	        
	        
	    }
	}

	function toggleAllApprovalStatus() {
	    var toggleButton = document.querySelector('.main-border-button a');
	    var isApproveAll = toggleButton.innerText.trim() === "전체 승인";
	    toggleButton.innerText = isApproveAll ? "전체 해제" : "전체 승인";
	    toggleButton.setAttribute("onclick", isApproveAll ? "toggleAllRevertApprovalStatus()" : "toggleAllApprovalStatus()");

	    var approvalButtons = document.querySelectorAll('.main-border-button a');
	    var targetStatus = isApproveAll ? "승인 대기" : "승인 확인";
	    approvalButtons.forEach(function(button) {
	        if (button.innerText.trim() === targetStatus) {
	            toggleApprovalStatus(button);
	        }
	    });
	}

	function toggleAllRevertApprovalStatus() {
	    var toggleButton = document.querySelector('.main-border-button a');
	    toggleButton.innerText = "전체 승인";
	    toggleButton.setAttribute("onclick", "toggleAllApprovalStatus()");

	    var approvalButtons = document.querySelectorAll('.main-border-button a');
	    approvalButtons.forEach(function(button) {
	        if (button.innerText.trim() === "승인 확인") {
	            toggleApprovalStatus(button);
	        }
	    });
	}