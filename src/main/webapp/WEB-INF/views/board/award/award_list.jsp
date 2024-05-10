<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
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

    <div class="award">
        <p>명예의 전당</p>
    </div>
    
    <div class="pageBanner">
      		<div class="pageWrap">
      			<div class="noticeInfo">
      				<div class="noticeHeader">명예의 전당 안내</div>
      				<div class="noticeContents">인기 작품을 소개합니다</div>
      			</div>
      			<div class="noticeImg">
      				<img src="/sangsangjakka/resources/img/ImgAwardTitle.png" alt="" />
      			</div>
      		</div>
      </div>
       </section>


    <article id="article">
        <div class="articleLeft"></div>
        <div class="articleMain">

            <div id="userBestSeller">
                <div class="userBestSellerWrap"> 
                    <div class="userBestSellerTitle">
                        <div class="mainTitle">🏆 현재 가장 인기있는 작품이에요 🏆
                        </div>
                    </div>
                    <div class="userBestSellerList">
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
    
    
        <div id="userBestSeller">
            <div class="userBestSellerWrap"> 
                <div class="userBestSellerTitle">
                    <div class="mainTitle">🏆 4월 인기 작품이에요 🏆</div>
                </div>
                <div class="userBestSellerList">
                    <ul>
                        <li class="bookWrap">
                            <a href="https://naver.com/">
                                <img src="/sangsangjakka/resources/img/book1.jpg"  id="imgBeforeFirst" class="imgLast">
                                <div class="bookInfo infoFirst">
                                    <p>제목</p>
                                    <p>한 줄 소개?</p>
                                </div>
                            </a>
                        </li>
                        <li class="bookWrap">
                            <a href="https://naver.com/">
                                <img src="/sangsangjakka/resources/img/book2.jpg" alt="" class="imgLast">
                                <div class="bookInfo">
                                    <p>제목</p>
                                    <p>한 줄 소개?</p>
                                </div>
                            </a>
                        </li>
                        <li class="bookWrap">
                            <a href="https://naver.com/">
                                <img src="/sangsangjakka/resources/img/book3.jpg" alt="" class="imgLast">
                                <div class="bookInfo">
                                    <p>제목</p>
                                    <p>한 줄 소개?</p>
                                </div>
                            </a>
                        </li>
                        <li class="bookWrap">
                            <a href="https://naver.com/">
                                <img src="/sangsangjakka/resources/img/book4.jpg" alt="" class="imgLast">
                                <div class="bookInfo">
                                    <p>제목</p>
                                    <p>한 줄 소개?</p>
                                </div>
                            </a>
                        </li>
                        <li class="bookWrap">
                            <a href="https://naver.com/">
                                <img src="/sangsangjakka/resources/img/book5.jpg" id="imgBeforeLast" class="imgLast">
                                <div class="bookInfo">
                                    <p>제목</p>
                                    <p>한 줄 소개?</p>
                                </div>
                            </a>
                        </li>
                    </ul>
                </div>
            </div>
        </div>

        <div id="userBestSeller">
            <div class="userBestSellerWrap"> 
                <div class="userBestSellerTitle">
                    <div class="mainTitle">🏆 3월 인기 작품이에요 🏆</div>
                </div>
                <div class="userBestSellerList">
                    <ul>
                        <li class="bookWrap">
                            <a href="https://naver.com/">
                                <img src="/sangsangjakka/resources/img/book1.jpg"  id="imgBeforeBeforeFirst" class="imgLast" alt="">
                                <div id="InfoBeforeBeforeFirst" class="bookInfo infoFirst">
                                    <p>제목</p>
                                    <p>한 줄 소개?</p>
                                </div>
                            </a>
                        </li>
                        <li class="bookWrap">
                            <a href="https://naver.com/">

                                <img src="/sangsangjakka/resources/img/book2.jpg"  class="imgLast" alt="">
                                <div class="bookInfo">
                                    <p>제목</p>
                                    <p>한 줄 소개?</p>
                                </div>
                            </a>
                        </li>
                        <li class="bookWrap">
                            <a href="https://naver.com/">

                                <img src="/sangsangjakka/resources/img/book3.jpg"  class="imgLast" alt="">
                                <div class="bookInfo">
                                    <p>제목</p>
                                    <p>한 줄 소개?</p>
                                </div>
                            </a>
                        </li>
                        <li class="bookWrap">
                            <a href="https://naver.com/">

                                <img src="/sangsangjakka/resources/img/book4.jpg"  class="imgLast" alt="">
                                <div class="bookInfo">
                                    <p>제목</p>
                                    <p>한 줄 소개?</p>
                                </div>
                            </a>
                        </li>
                        <li class="bookWrap">
                            <a href="https://naver.com/">
                                <img src="/sangsangjakka/resources/img/book5.jpg"  id="imgBeforeBeforeLast" class="imgLast" alt="">
                                <div class="bookInfo">
                                    <p>제목</p>
                                    <p>한 줄 소개?</p>
                                </div>
                            </a>
                        </li>
                    </ul>
                </div>
            </div>
        </div>
        </div>
       
    </article>
  

    <!-- footer -->
		<%@include file="/WEB-INF/views/template/footer.jsp"%>
		
	<script src="https://code.jquery.com/jquery-3.7.1.js"></script>
	<script>
	</script>
	</body>
</html>