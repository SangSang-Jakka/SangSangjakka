<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
	<head>
	<meta charset="UTF-8">
	<title>Insert title here</title>
	<link rel="stylesheet" href="http://pinnpublic.dothome.co.kr/cdn/example-min.css">
	<%@include file="/WEB-INF/views/template/asset.jsp"%>
	<link rel="stylesheet" href="/sangsangjakka/resources/css/board/award/award_list.css">
	<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.5.2/css/all.min.css">
	<style>
	
	
	</style>
	</head>
	<body>
		<%@include file="/WEB-INF/views/template/header.jsp"%>
		
		<div class="topScroll">
			<button class="top"><i class="fa-solid fa-sort-up"></i></button>
			<button class="bottom"><i class="fa-solid fa-caret-down"></i></button>
		</div>
	
		<div class="award">
			<p>명예의 전당</p>
	
		</div>
	
		<div class="awardContainer">
			<div class="awardBox">
				<div class="awardItems">

				</div>
				<div class="awardItems">
	
				</div>
				<div class="awardItems">
	
				</div>
			</div>
			<div class="awardlistBox">
				<div class="secondline">
					
				</div>
				<div class="secondline">
					
				</div>
			</div>
		</div>

		<%@include file="/WEB-INF/views/template/footer.jsp"%>
		
	<script src="https://code.jquery.com/jquery-3.7.1.js"></script>
	<script>
	</script>
	</body>
</html>