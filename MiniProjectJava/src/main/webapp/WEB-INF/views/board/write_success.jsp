<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib prefix="c" uri ="http://java.sun.com/jsp/jstl/core" %>

<c:set var = 'root' value ='${pageContext.request.contextPath }/'/>
<script>
	alert('저장 되었습니다.')
	location.href = '${root}board/read?board_info_idx=${writeContentBean.content_board_idx}&content_idx=${writeContentBean.content_idx}'
</script>