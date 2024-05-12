<!-- bookmaking_fin.jsp -->
<%@ page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
    <%@include file="/WEB-INF/views/template/asset.jsp"%>
    <link rel="stylesheet" href="/sangsangjakka/resources/css/board/bookmaking/bookmaking_fin.css">
	<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.0/jquery.min.js"></script>
	<script src="https://kit.fontawesome.com/e075b9b5dc.js"	crossorigin="anonymous"></script>
    <script src="/sangsangjakka/resources/js/bookmaking/bookmaking_fin.js"></script>
</head>
<body>

	<!-- header -->
	<%@include file="/WEB-INF/views/template/header.jsp"%>

	<div id="makedbookGrid">
		<div class="makedbookGridLeft"></div>
		<div class="makedbookGridCenter">
			<div class="mainTitle">
				<h2>🎉축하합니다🎉</h2>
				<h2>나만의 동화책이 완성 되었습니다!</h2>			
			</div>
			<div class="finBookWrap">
				<div class="bookCoverBox">
					<div class="bookCoverItem" style="background-image: url('${bookCover}');">
					</div>
				</div>
				<div class="bookInfoBox">
					<span class="bookInfoItem">${bookInfo}</span>
				</div>
			</div>
		</div>
		<div class="makedbookGridRight"></div>
	</div>

	<!-- footer -->
	<%@include file="/WEB-INF/views/template/footer.jsp"%>

</body>
</html>