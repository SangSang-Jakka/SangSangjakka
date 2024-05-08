<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" href="http://pinnpublic.dothome.co.kr/cdn/example-min.css">
<style>
	
</style>
</head>
<body>

		
	<form action="/sangsangjakka/board/suggestion/del.do" method="POST">
	<div id="main">
		<div class="">
			<h3>삭제하시겠습니까?</h3>
			<button type="submit">삭제하기</button>
			<input type="button" value="돌아가기" onclick="location.href='/sangsangjakka/board/suggestion/list.do';">
		</div>
	</div>
	
		<input type="hidden" name="seq" value="${seq}">
	</form>
	
		
	<script src="https://code.jquery.com/jquery-3.7.1.js"></script>
	<script>
		
	</script>
</body>
</html>