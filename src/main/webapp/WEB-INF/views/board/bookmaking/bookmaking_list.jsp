<!-- bookmaking_list.jsp -->
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
    <%@include file="/WEB-INF/views/template/asset.jsp"%>
    <link rel="stylesheet" href="/sangsangjakka/resources/css/board/bookmaking/bookmaking_list.css">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.0/jquery.min.js"></script>
    <script src="https://kit.fontawesome.com/e075b9b5dc.js" crossorigin="anonymous"></script>
    <script src="/sangsangjakka/resources/js/bookmaking/bookmaking_list.js"></script>
</head>
<body>

    <%@include file="/WEB-INF/views/template/header.jsp"%>

    <div id="booklistGrid">
        <div class="booklistGridLeft"></div>
        
        <div class="booklistGridCenter">
        	<h2>만들던 동화책!</h2>
            <div class="booklistSliderContainer">
                <button class="sliderBtn left">&nbsp;&#10094;&nbsp;</button>
                <div class="slider">
                    <div class="sliderItemBox">
                        <c:forEach items="${bookDtoList}" var="dto" varStatus="index">
			                <div class="sliderItem pointer" data-page-url="${pageDtoList[index.index].pageUrl}">
			                    ${dto.bookTitle}
			                </div>
			            </c:forEach>
                    </div>
                </div>
                <button class="sliderBtn right">&nbsp;&#10095;&nbsp;</button>
            </div>
            <div class="makeNewBook">
            	<a href="/sangsangjakka/board/bookmaking/view.do">
            	<input type="button" value="새 동화책 만들기"/>
            	</a>
            </div>
        </div>
        
        <div class="booklistGridRight"></div>
    </div>

    <%@include file="/WEB-INF/views/template/footer.jsp"%>
	
</body>
</html>
