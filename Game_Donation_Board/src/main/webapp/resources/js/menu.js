 
//드롭다운 토글 함수
function toggleDropdown() {
    let dropdownContainer = document.getElementById('dropdown-container');
    let isVisible = dropdownContainer.style.display === 'block';
    // 드롭다운 메뉴의 표시 여부를 토글
    dropdownContainer.style.display = isVisible ? 'none' : 'block';
    event.stopPropagation();

}

// 문서 클릭 이벤트 핸들러
document.addEventListener('click', function(event) {
	let dropdownContainer = document.getElementById('dropdown-container');
	let target = event.target;
    
    // 클릭된 요소가 드롭다운 메뉴 내부가 아닌 경우 드롭다운 메뉴를 숨김
    if (!dropdownContainer.contains(target)) {
        dropdownContainer.style.display = 'none';
    }
});