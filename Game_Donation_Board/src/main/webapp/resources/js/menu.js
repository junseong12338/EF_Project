// 드롭다운 토글 함수
function toggleDropdown(event) {
    event.stopPropagation(); // 이벤트 버블링을 막습니다.
    let dropdownContainer = document.getElementById('dropdown-container');
    
    // 드롭다운 메뉴의 표시 여부를 토글
    if (dropdownContainer.style.display === 'block') {
        dropdownContainer.style.display = 'none';
        // 드롭다운이 닫힐 때 원래 상태로 복원
        document.querySelector('.dropdown-toggle').style.backgroundColor = '';
        document.querySelector('.dropdown-toggle').style.color = '';
    } else {
        dropdownContainer.style.display = 'block';
        // 드롭다운이 열릴 때 색 변경
        document.querySelector('.dropdown-toggle').style.backgroundColor = '#e75e8d';
        document.querySelector('.dropdown-toggle').style.color = '#fff';
    }
}

// 문서 클릭 이벤트 핸들러
document.addEventListener('click', function(event) {
    let dropdownContainer = document.getElementById('dropdown-container');
    let target = event.target;
    
    // 클릭된 요소가 드롭다운 메뉴 내부가 아닌 경우 드롭다운 메뉴를 숨김
    if (!dropdownContainer.contains(target)) {
        dropdownContainer.style.display = 'none';
        // 클릭된 요소가 드롭다운 토글 버튼인 경우 원래 상태로 복원
        document.querySelector('.dropdown-toggle').style.backgroundColor = '';
        document.querySelector('.dropdown-toggle').style.color = '';
    }
});


// JavaScript 코드
document.addEventListener("DOMContentLoaded", function() {
    var dropdownItems = document.querySelectorAll(".dropdown-list li");

    dropdownItems.forEach(function(item) {
        item.addEventListener("mouseenter", function() {
            var link = this.querySelector("a");
            link.style.backgroundColor = "transparent";
        });

        item.addEventListener("mouseleave", function() {
            var link = this.querySelector("a");
            link.style.backgroundColor = ""; // 기본값으로 돌아가게 함
        });
    });
});
