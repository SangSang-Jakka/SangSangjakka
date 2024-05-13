<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="com.jakka.model.dto.book.BookDTO" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page import="java.util.ArrayList" %>


<!DOCTYPE html>
<html>
<head>
    <%@include file="/WEB-INF/views/template/asset.jsp"%>
    <link rel="stylesheet" href="/sangsangjakka/resources/css/board/award/award_list.css">
    <style>
    </style>
</head>
<body>

<%@include file="/WEB-INF/views/template/header.jsp"%>

<section class="notice">
    <div class="pageTitle">
        <div>
            <h3>🏆 5월 명예의 전당 🏆</h3>
        </div>
    </div>

    <div class="awardWrap">
        <div class="awardBookWrap">
            <c:forEach var="item" items="${list}" varStatus="loop">
			    <div class="awardBookWrapContainer">
			        <div class="awardBookImg" style="background-image: url('${item.bookCover}');" data-index="${loop.index}">
			            <div class="inputDiv">
			                <div class="bookListContents">
			                	<div class="bookListRegdateWrap">
			                		<div class="bookListRegdate">${item.bookRegdate}</div>
			                	</div>
			                	<div class="bookListUserWrap">
			                		<div class="bookListUser">🎀 작가님: ${item.userNick} 🎀</div>
			                	</div>
			                	<div class="bookListTitleWrap">
			                		<div class="bookListTitle">${item.bookTitle}</div>
			                	</div>
			                	<div class="bookListContentWrap">
			                		<div class="bookListContent">${item.bookInfo}</div>
			                	</div>
			                	
			                </div>

			            </div>
			        </div>
			    </div>
			</c:forEach>
        </div>
        <div class="awardBookBtnWrap">
            <button class="bookPrev" onclick="handlePrev()">이전</button>
			<button class="bookNext" onclick="handleNext()">다음</button>

        </div>
    </div>

</section>

<%@include file="/WEB-INF/views/template/footer.jsp"%>

<script src="https://code.jquery.com/jquery-3.7.1.js"></script>
<script src="https://kit.fontawesome.com/4b3c3b390b.js" crossorigin="anonymous"></script>
<script>




    let currentIndex = 0;
    const images = $('.awardBookImg');
    const totalImages = images.length;

    // 초기에는 첫 번째 이미지만 보이도록 설정
    images.hide();
    images.eq(currentIndex).show();

    function handlePrev() {
        if (currentIndex > 0) {
            images.eq(currentIndex).hide();
            currentIndex--;
            images.eq(currentIndex).show();
        }
    }

    function handleNext() {
        if (currentIndex < totalImages - 1) {
            images.eq(currentIndex).hide();
            currentIndex++;
            images.eq(currentIndex).show();
        }
    }
    
    $('.awardBookImg').click(function() {
        // 이미지를 클릭하면 책 뒤집기
        $(this).toggleClass('flip');

        // 책 뒤집은 후, 책 뒤면에 텍스트 입력란 표시
        $(this).find('.inputDiv').toggle();
    });


    $('.saveBtn').click(function() {
        const content = $(this).siblings('textarea').val();
        // 입력된 내용을 저장하는 코드 추가
        console.log(content);
        // ...
    });



</script>

</body>
</html>
