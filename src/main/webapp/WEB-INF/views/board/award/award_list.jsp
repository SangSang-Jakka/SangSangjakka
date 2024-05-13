<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="com.jakka.model.dto.book.BookDTO" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page import="java.util.ArrayList" %>


<!DOCTYPE html>
<html>
   <%@include file="/WEB-INF/views/template/asset.jsp"%>
   <link rel="stylesheet" href="/sangsangjakka/resources/css/board/award/award_list.css">
   <style>
   
   
   </style>
   </head>
   <body>
   
      <%@include file="/WEB-INF/views/template/header.jsp"%>
      

      <!-- <div class="topScroll">
        <button class="top"><i class="fa-solid fa-sort-up"></i></button>
        <button class="bottom"><i class="fa-solid fa-caret-down"></i></button>
    </div> -->



	<section class="notice">
    <div class="pageTitle">
          <div>
              <h3>명예의 전당</h3>
          </div>
      </div>
      
      
      <div class="awardWrap">
      		<div class="awardBookWrap">
      			<c:forEach var="item" items="${list}">
				    <div class="awardBookImg" style=" background-image: url('${item.bookCover}');"></div>
				</c:forEach>
      		</div>
      		<div class="awardBookBtnWrap">
      			<button class="bookPrev">이전</button>
      			<button class="bookNext">다음</button>
      		</div>
      </div>
    
        <div class="articleLeft"></div>
        <div class="articleMain">

            <div id="userBestSeller">
                <div class="userBestSellerWrap"> 
                    <div class="userBestSellerTitle">
                        <div class="mainTitle">🏆 현재 가장 인기있는 작품이에요 🏆
                        </div>
                    </div>
                    
                  
                    <div class="bookAwardContainer">
                        <div class="bookAwardPrev">
                            <button type="button" class="rightBtn"><i class="fa-solid fa-chevron-left"></i></button>
                        </div>
                        <div class="bookAwardImg">
                            <img src="/sangsangjakka/resources/img/book1.jpg" alt="">
                        </div>
                        <div class="bookAwardInfo">
                            <div class="bookAwardInfoContainer">
                                <%
						            ArrayList<BookDTO> list = (ArrayList<BookDTO>) request.getAttribute("list");
						            if (list != null && !list.isEmpty()) {
						                BookDTO book = list.get(0);
						        %>
						            <div class="bookAwardInfoTitile"><%= book.getBookTitle() %></div>
						            <div class="bookAwardInfoUserNick">닉네임: <%= book.getUserNick() %></div>
						            <div class="bookAwardInfoIntro">한줄 소개: <%= book.getBookInfo() %></div>
						       <% } else { %>
					                <div class="bookAwardInfoTitile">책 정보 없음</div>
					            <% } %>
                            </div>
                            
                        </div>
                        <div class="bookAwardNext">
                            <button type="button"><i class="fa-solid fa-chevron-right"></i></button>
                            
                        </div>
                    </div>
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
					
					
                        <ul>
                            <li class="bookWrap">
                                <a href="https://naver.com/">
                                    <img src="/sangsangjakka/resources/img/book1.jpg" id="imgThisFirst" alt="">
                                </a>
                                    <div class="category textFirst">카테고리</div>
                                    <div class="titleLink textFirst">
                                        <p><a href="https://naver.com/">To the Sea</a></p>
                                    </div>
                                <!-- <div class="bookInfo">Lorem ipsum dolor sit amet consectetur adipisicing elit. Eius, hic.</div> -->
                                <img src="/sangsangjakka/resources/img/medal1stver2.png" alt="" class="medal1st medalImg"/>
                            </li>
                            <li class="bookWrap">
                                <a href="https://naver.com/">
                                    <img src="/sangsangjakka/resources/img/book2.jpg" class="imgThis" alt="">
                                </a>
                                <div class="category">카테고리</div>
                                <div class="titleLink">
                                    <p><a href="동화나라 세부정보 링크">당근 유치원</a></p>
                                </div>
                                <!-- <div class="bookInfo">Lorem ipsum dolor sit amet consectetur adipisicing elit. Eius, hic.</div> -->
                                <img src="/sangsangjakka/resources/img/medal2ndver2.png" alt="" class="medal2nd medalImg"/>
                            </li>
                            <li class="bookWrap">
                                <a href="https://naver.com/">
                                    <img src="/sangsangjakka/resources/img/book3.jpg" class="imgThis" alt="">
                                </a>
                                <div class="category">카테고리</div>
                                <div class="titleLink">
                                    <p><a href="동화나라 세부정보 링크">화가 나서 그랬어!</a></p>
                                </div>
                                <!-- <div class="bookInfo">Lorem ipsum dolor sit amet consectetur adipisicing elit. Eius, hic.</div> -->
                                <img src="/sangsangjakka/resources/img/medal3rdver2.png" alt="" class="medal3rd medalImg"/>
                            </li>
                            <li class="bookWrap">
                                <a href="https://naver.com/">
                                    <img src="/sangsangjakka/resources/img/book4.jpg" class="imgThis" alt="">
                                </a>
                                <div class="category">카테고리</div>
                                <div class="titleLink">
                                    <p><a href="동화나라 세부정보 링크">할아버지와 순돌이는</a></p>
                                </div>
                                <!-- <div class="bookInfo">Lorem ipsum dolor sit amet consectetur adipisicing elit. Eius, hic.</div> -->
                                <img src="/sangsangjakka/resources/img/medal45.png" alt="" class="medal4"/>
                            </li>
                            <li class="bookWrap">
                                <a href="https://naver.com/">
                                    <img src="/sangsangjakka/resources/img/book5.jpg" alt="" id="imgThisLast">
                                </a>
                                <div class="category textLast">카테고리</div>
                                <div class="titleLink textLast">
                                    <p><a href="동화나라 세부정보 링크">세상에서 두 번째로 신기한</a></p>
                                </div>
                                <!-- <div class="bookInfo">Lorem ipsum dolor sit amet consectetur adipisicing elit. Eius, hic.</div> -->
                                <img src="/sangsangjakka/resources/img/medal45.png" alt="" class="medal5"/>
                            </li>
                        </ul>
                    </div>
                </div>
            </div>

         </section>
       

  

    <!-- footer -->
		<%@include file="/WEB-INF/views/template/footer.jsp"%>
		
	<script src="https://code.jquery.com/jquery-3.7.1.js"></script>
	<script src="https://kit.fontawesome.com/4b3c3b390b.js" crossorigin="anonymous"></script>
	<script>
	
	
	$(document).ready(function() {
	    $('#dashboard').on('click', function(event) {
	      if (event.ctrlKey) {
	        // 관리자 로그인 페이지로 이동
	        window.location.href = '/sangsangjakka/admin/login.do'; // 적절한 URL로 변경해야 합니다.
	      }

	
	
    document.addEventListener("DOMContentLoaded", function() {
const bookInfo = [
    {
        title: "제목: To The Sea",
        intro: "한줄 소개: 고래를 타고 바다로 나아가는 태리의 여정입니다.",
        reviews: [
            "고래를 만나 교감하는게 감동 깊었어요!",
            "태리를 계속 응원했어요!",
            "마지막에 눈물나요 ㅠㅠ",
            "와 프론트 눈물나요 ㅠㅠ",
            "너무 어려워요 ㅠㅠ"
        ],
        image: "/sangsangjakka/resources/img/book1.jpg"
    },
    {
        title: "제목: 당근 유치원",
        intro: "한줄 소개: 토끼 선생님과 함께하는 당근 유치원이야기 입니다!",
        reviews: [
            "고래를 만나 교감하는게 감동 깊었어요!",
            "태리를 계속 응원했어요!",
            "마지막에 눈물나요 ㅠㅠ",
            "와 프론트 눈물나요 ㅠㅠ",
            "너무 어려워요 ㅠㅠ"
        ],
        image: "/sangsangjakka/resources/img/book2.jpg"
    },
    {
        title: "제목: 화가 나서 그랬어!",
        intro: "한줄 소개: 화가 나는데 어떡해요..감정을 표현하는 방법을 알려드립니다.",
        reviews: [
            "고래를 만나 교감하는게 감동 깊었어요!",
            "태리를 계속 응원했어요!",
            "마지막에 눈물나요 ㅠㅠ",
            "와 프론트 눈물나요 ㅠㅠ",
            "너무 어려워요 ㅠㅠ"
        ],
        image: "/sangsangjakka/resources/img/book3.jpg"
    },
    {
        title: "제목: 할아버지와 순돌이는 닮았어요.",
        intro: "한줄 소개: 세상에서 가장 따뜻한 강아지와 할아버지의 함께 살아가는 이야기입니다.",
        reviews: [
            "고래를 만나 교감하는게 감동 깊었어요!",
            "태리를 계속 응원했어요!",
            "마지막에 눈물나요 ㅠㅠ",
            "와 프론트 눈물나요 ㅠㅠ",
            "너무 어려워요 ㅠㅠ"
        ],
        image: "/sangsangjakka/resources/img/book4.jpg"
    },
    {
        title: "제목: 세상에서 두 번째로 신기한 일",
        intro: "한줄 소개: 첫 번째로 신기한 일은 과연 무엇이었을까요? 맞춰보세요!",
        reviews: [
            "고래를 만나 교감하는게 감동 깊었어요!",
            "태리를 계속 응원했어요!",
            "마지막에 눈물나요 ㅠㅠ",
            "와 프론트 눈물나요 ㅠㅠ",
            "너무 어려워요 ㅠㅠ"
        ],
        image: "/sangsangjakka/resources/img/book5.jpg"
    },

];



	
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
	  const selectedMonth = monthSelect.value;
	  
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