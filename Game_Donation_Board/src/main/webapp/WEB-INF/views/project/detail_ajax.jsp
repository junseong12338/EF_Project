<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<c:choose>
	<c:when test="${wanted eq 0 }">
		<p>${content }</p>
	</c:when>
	<c:when test="${wanted eq 1 }">
		<p>${review }</p>
	</c:when>
</c:choose>