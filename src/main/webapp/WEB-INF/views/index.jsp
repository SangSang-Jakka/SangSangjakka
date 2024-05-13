<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
	<%@include file="/WEB-INF/views/template/asset.jsp"%>
	<style> 
	
	
	</style>
	</head>
	<body>
	
		<!-- header -->
		<%@include file="/WEB-INF/views/template/header.jsp"%>
		
		<div id="banner">
            <div class="bannerWrap">
                <div class="bannerContainer">
                    <div class="bannerTitle" style="font-family: 'SOYOMapleBoldTTF';">아이의 상상력을 현실로! <br> AI 동화책 제작</div>
                    <div class="bannerContents" style="font-family: 'SOYOMapleBoldTTF';">상상자까는 아이들의 상상력을 현실로 만들어주는 특별한 경험을 선사합니다. <br>아이들은 작가가 되어 자신만의 이야기를 책으로 만날 수 있어요.</div>
                    <button class="bannerbtn" style="font-family: 'SOYOMapleBoldTTF';" onclick="window.location.href='/sangsangjakka/board/bookmaking/add.do';">나만의 동화책 제작하기</button>
                    <div class="bannerTxt"> + Wake a imagination in a child</div>
                </div>
                <div class="bannerImg"></div>
            </div>
        </div>

        <article id="article">
            <div class="articleLeft"></div>
            <div class="articleMain">
            <div id="userBestSeller">
                <div class="userBestSellerWrap"> 
                    <div class="userBestSellerTitle">
                        <div class="mainTitle">📍 어제의 인기도서</div>
                    </div>
                    <div class="userBestSellerList">
                        <ul>
                            <div class="bookWrap">
                                <li>
                                    <img src="/sangsangjakka/resources/img/book1.jpg" alt="">
                                    <div class="bookInfo">Lorem ipsum dolor sit amet consectetur adipisicing elit. Eius, hic.</div>
                                </li>
                            </div>
                            <div class="bookWrap">
                                <li>
                                    <img src="/sangsangjakka/resources/img/book2.jpg" alt="">
                                    <div class="bookInfo">Lorem ipsum dolor sit amet consectetur adipisicing elit. Eius, hic.</div>
                                </li>
                            </div>
                            <div class="bookWrap">
                                <li>
                                    <img src="/sangsangjakka/resources/img/book3.jpg" alt="">
                                    <div class="bookInfo">Lorem ipsum dolor sit amet consectetur adipisicing elit. Eius, hic.</div>
                                </li>
                            </div>
                            <div class="bookWrap">
                                <li>
                                    <img src="/sangsangjakka/resources/img/book4.jpg" alt="">
                                    <div class="bookInfo">Lorem ipsum dolor sit amet consectetur adipisicing elit. Eius, hic.</div>
                                </li>
                            </div>
                            <div class="bookWrap">
                                <li>
                                    <img src="/sangsangjakka/resources/img/book5.jpg" alt="">
                                    <div class="bookInfo">Lorem ipsum dolor sit amet consectetur adipisicing elit. Eius, hic.</div>
                                </li>
                            </div>
                        </ul>
                    </div>
                </div>
            </div>


                <div class="recommendBookWrap"> 
                    <div class="recommendBookTitle">
                        <div class="mainTitle">🪄 이 책은 어떠세요?</div>
                    </div>
                </div>
                <div class="recommendBookWrap">
                    <div class="carousel"></div>
                    <div class="bookDescription">
                        <div class="recommendBookContainer">
                        <div class="recommendbookTitle"> 제목: 화가 나서 그랬어!</div>
                        <div class="recommendbookIntro"> 한 줄 소개: 화가 나는데 어떡해요..감정을 표현하는 방법을 알려드립니다.</div>
                        <div class="recommendbookGenre"> 태그: #귀여운 #장난스러운 #재미있는
                        </div>
                        </div>
                    </div>
                </div>


                <div class="bookAwardWrap">
                    <div class="bookAwardWrapTitle">
                        <div class="mainTitle">🏆 명예의 전당</div>
                    </div>
                    <div class="bookAwardContainer">
                        <div class="bookAwardPrev">
                            <button type="button"><i class="fa-solid fa-chevron-left"></i></button>
                        </div>
                        <div class="bookAwardImg">
                            <img src="/sangsangjakka/resources/img/book1.jpg" alt="">
                        </div>
                        <div class="bookAwardInfo">
                            <div class="bookAwardInfoContainer">
                                <div class="bookAwardInfoTitile">제목 : To The Sea</div>
                                <div class="bookAwardInfoIntro"> 한줄 소개: 고래를 타고 바다로 나아가는 태리의 여정입니다.</div>
                            </div>
                            <div class="bookAwardInfoReview">
                                <div class="Review">소중한 소감평</div>
                                <ul>
                                    <li>고래를 만나 교감하는게 감동 깊었어요!</li>
                                    <li>태리를 계속 응원했어요!</li>
                                    <li>마지막에 눈물나요 ㅠㅠ </li>
                                    <li>와 프론트 눈물나요 ㅠㅠ </li>
                                    <li>너무 어려워요 ㅠㅠ </li>
                                </ul>
                            </div>
                        </div>
                        <div class="bookAwardNext">
                            <button type="button"><i class="fa-solid fa-chevron-right"></i></button>
                            
                        </div>
                    </div>
                </div>


                <div class="websiteIntroWrap">
                    <div class="websiteIntroWrapTitle">
                        <div class="mainTitle">왜 이 사이트가 필요할까요?</div>
                    </div>
                    <div class="websiteIntroContainer">
                        <div class="websiteIntroItem">
                            <img src="/sangsangjakka/resources/img/1.svg" alt="">
                            <div class="websiteIntroItemTitle">독서의 즐거움</div>
                            <div class="websiteIntroItemContents">쉽고 재미있는 동화책으로 아이의 창의성과 독서 습관을 길러줍니다.</div>
                        </div>
                        
                        <div class="websiteIntroItem">
                            <img src="/sangsangjakka/resources/img/2.svg" alt="">
                        <div class="websiteIntroItemTitle">다양한 선택지</div>
                            <div class="websiteIntroItemContents">다양한 주제와 캐릭터를 선택하여 아이의 흥미를 끌어냅니다.</div>
                        </div>

                        <div class="websiteIntroItem">
                            <img src="/sangsangjakka/resources/img/3.svg" alt="">
                            <div class="websiteIntroItemTitle">맞춤형 선물</div> 
                            <div class="websiteIntroItemContents">부모와 아이가 함께 작성한 이야기를 맞춤형 동화책으로 전달합니다
                            </div>
                        </div>
                
                        <div class="websiteIntroItem">
                            <img src="/sangsangjakka/resources/img/4.svg" alt="">
                            <div class="websiteIntroItemTitle">공간과 자원을 절약</div>
                            <div class="websiteIntroItemContents">동화책의 구입으로 인한 공간적, 자원적 낭비를 줄여줍니다.</div>
                        </div>



                        <div class="websiteIntroItem">
                            <img src="/sangsangjakka/resources/img/5.svg" alt="">
                        <div class="websiteIntroItemTitle">디지털 미디어 중독 방지</div>
                            <div class="websiteIntroItemContents">상상력과 집중력을 유발하는 동화책 경험을 제공하여 디지털 미디어 중독을 예방합니다.
                            </div>
                        </div>
                        
                        <div class="websiteIntroItem">
                            <img src="/sangsangjakka/resources/img/6.svg" alt="">
                        <div class="websiteIntroItemTitle">재정적 부담 완화</div>
                            <div class="websiteIntroItemContents">맞춤형 동화책을 무료로 제작하여 아이 성장에 따른 책 구입에 따른 재정적 어려움을 완화합니다.</div>
                        </div>
                </div>
                
            </div>
            </div>


            <div class="articleRight"></div>

        </article>
		
		
		<!-- footer -->
		<%@include file="/WEB-INF/views/template/footer.jsp"%>
	
	<script defer src="/sangsangjakka/resources/js/carousel.js"></script>
    <script src="https://kit.fontawesome.com/4b3c3b390b.js" crossorigin="anonymous"></script>
	<script src="https://code.jquery.com/jquery-3.7.1.js"></script>
	<script>
	

		$(document).ready(function() {
		    $('#dashboard').on('click', function(event) {
		      if (event.ctrlKey) {
		        // 관리자 로그인 페이지로 이동
		        window.location.href = '/sangsangjakka/admin/login.do'; // 적절한 URL로 변경해야 합니다.
		      }
		    });
		  });

		
		
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

    let currentIndex = 0;

    function updateBookInfo() {
        const bookTitle = document.querySelector(".bookAwardInfoTitile");
        const bookIntro = document.querySelector(".bookAwardInfoIntro");
        const reviewList = document.querySelector(".bookAwardInfoReview ul");
        const bookImage = document.querySelector(".bookAwardImg img");

        bookTitle.textContent = bookInfo[currentIndex].title;
        bookIntro.textContent = bookInfo[currentIndex].intro;

        // 리뷰 목록을 초기화하고 새로운 리뷰를 추가합니다.
        reviewList.innerHTML = "";
        bookInfo[currentIndex].reviews.forEach(review => {
            const li = document.createElement("li");
            li.textContent = review;
            reviewList.appendChild(li);
        });

        // 이미지 주소 업데이트
        bookImage.src = bookInfo[currentIndex].image;
    }

    const prevButton = document.querySelector(".bookAwardPrev button");
    const nextButton = document.querySelector(".bookAwardNext button");

    prevButton.addEventListener("click", function() {
        currentIndex = (currentIndex === 0) ? bookInfo.length - 1 : currentIndex - 1;
        updateBookInfo();
    });

    nextButton.addEventListener("click", function() {
        currentIndex = (currentIndex === bookInfo.length - 1) ? 0 : currentIndex + 1;
        updateBookInfo();
    });

    // 초기 책 정보 표시
    updateBookInfo();
});
	</script>
    </script>
	</script>
	</body>
</html>