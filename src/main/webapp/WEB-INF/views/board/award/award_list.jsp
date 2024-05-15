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
    
    <div class="userBestSellerTitle">
                        <div class="mainTitle">🏆 지난 인기 작품도 살펴봐요 🏆
                        </div>	
                    </div>
                    
                    
                    <div class="userBestSellerList">
        
					        <div class="month-selector">
							  <label for="monthSelect">월 선택:</label>
							  <select id="monthSelect"></select>
							</div>
					
					
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
					
                        <ul>
						    <c:forEach var="book" items="${listMonth}">
						        <li class="bookWrap">
						            <a href="#">
						                <img src="${book.bookCover}" alt="">
						            </a>
						            <div class="userNick">${book.userNick}</div>
						            <div class="titleLink">
						                <p><a href="#">${book.bookTitle}</a></p>
						                <p>${book.userNick}</p>
						            </div>
						        </li>
						    </c:forEach>
						</ul>
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
    
    /*  다른 달  */
    
    
    const monthSelect = document.getElementById('monthSelect');
    const currentMonth = new Date().getMonth() + 1; // 0부터 시작하므로 1을 더해줌
    const prevMonth = currentMonth === 1 ? 12 : currentMonth - 1; // 이전 달 계산
    const months = ['1월', '2월', '3월', '4월', '5월', '6월', '7월', '8월', '9월', '10월', '11월', '12월'];

    // 이전 달부터 현재 월 전까지의 옵션을 내림차순으로 추가
    for (let i = 1; i <= 6; i++) {
      const month = (currentMonth - i + 12) % 12 || 12;
      const option = document.createElement('option');
      option.value = month;
      option.text = months[month - 1];
      monthSelect.add(option);
    }
    
 // 디폴트로 이전 달 선택
    monthSelect.value = prevMonth;


    monthSelect.addEventListener('change', () => {
    	const selectedMonth = monthSelect.value.replace('월', '');
    	  
    	  // AJAX 요청으로 선택된 월의 리스트를 가져옴
    	  fetch(`/getListByMonth?month=${selectedMonth}`)
    	    .then(response => response.text())
    	    .then(data => {
    	      // 기존 리스트를 비우고 새로운 리스트로 교체
    	      userBestSellerList.innerHTML = data;
    	    })
    	    .catch(error => console.error(error));
    	});



</script>

</body>
</html>
