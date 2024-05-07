<!-- bookmaking_list.jsp -->
<%@ page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
    <%@include file="/WEB-INF/views/template/asset.jsp"%>
    <link rel="stylesheet" href="/sangsangjakka/resources/css/board/bookmaking/bookmaking_list.css">
	<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.0/jquery.min.js"></script>
	<script src="https://kit.fontawesome.com/e075b9b5dc.js"	crossorigin="anonymous"></script>
    <script src="/sangsangjakka/resources/js/bookmaking/bookmaking_list.js"></script>
</head>
<body>

	<!-- header -->
	<%@include file="/WEB-INF/views/template/header.jsp"%>

	<div id="booklistGrid">
		<div class="booklistGridLeft"></div>
		
		<div class="booklistGridCenter">
			
			<div class="booklistSliderContainer">
			    <button class="sliderBtn left">&nbsp;&#10094;&nbsp;</button>
			    <div class="slider">
			        <div class="sliderItemBox">
			            <div class="sliderItem">Item 1</div>
			            <div class="sliderItem">Item 2</div>
			            <div class="sliderItem">Item 3</div>
			            <div class="sliderItem">Item 4</div>
			            <div class="sliderItem">Item 5</div>
			            <div class="sliderItem">Item 6</div>
			            <div class="sliderItem">Item 7</div>
			            <div class="sliderItem">Item 8</div>
			        </div>
			    </div>
			    <button class="sliderBtn right">&nbsp;&#10095;&nbsp;</button>
			</div>
			
		</div>
		
		<div class="booklistGridRight"></div>
	</div>

	<!-- footer -->
	<%@include file="/WEB-INF/views/template/footer.jsp"%>

</body>
</html>