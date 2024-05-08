<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
	<%@include file="/WEB-INF/views/template/asset.jsp"%>
	<link rel="stylesheet" href="/sangsangjakka/resources/css/board/bookshare/bookshare_view.css">
	<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.5.2/css/all.min.css" integrity="sha512-SnH5WK+bZxgPHs44uWIX+LLJAJ9/2PkPKZ5QiAj6Ta86w+fsb2TkcmfRyVX3pBnMFcV7oQPJkl9QevSCWr3W6A==" crossorigin="anonymous" referrerpolicy="no-referrer" />
	<style>
	
	
	</style>
	</head>
	<body>
		
		<!-- header -->
		<%@include file="/WEB-INF/views/template/header.jsp"%>

		<h2>동화 공유 게시판</h2>
		
		<div class="slideContainer">
        <div class="prevbuttonBox">
            <div class="prev-button"><i class="fas fa-duotone fa-backward-step" style="font-size:70px;"></i></div>
        </div>
        <div class="shareContainer">
            <div class="carousel">
            	
                <div class="cell"><img src="/sangsangjakka/resources/img/book1.jpg" alt="book1"></div>
                <div class="cell"><img src="/sangsangjakka/resources/img/book2.jpg" alt="book2"></div>
                <div class="cell"><img src="/sangsangjakka/resources/img/book3.jpg" alt="book3"></div>
                <div class="cell"><img src="/sangsangjakka/resources/img/book4.jpg" alt="book4"></div>
                <div class="cell"><img src="/sangsangjakka/resources/img/book5.jpg" alt="book5"></div>    
            </div>
            
        </div>
       
          <div class="shareContainer">
            <div class="carousel">
                <div class="cell"><img src="/sangsangjakka/resources/img/book1.jpg" alt="book1"></div>
                <div class="cell"><img src="/sangsangjakka/resources/img/book2.jpg" alt="book2"></div>
                <div class="cell"><img src="/sangsangjakka/resources/img/book3.jpg" alt="book3"></div>
                <div class="cell"><img src="/sangsangjakka/resources/img/book4.jpg" alt="book4"></div>
                <div class="cell"><img src="/sangsangjakka/resources/img/book5.jpg" alt="book5"></div>    
            </div>
        </div>
<!--       </div> -->
      
            
              

            
        
        <div class="nextbuttonBox">
            <div class="next-button"><i class="fas fa-duotone fa-forward-step" style="font-size:70px;"></i></div>
        </div>    
    </div>
        <!-- <div class="buttonBox">
            <div class="prev-button"><i class="fas fa-duotone fa-backward-step"></i></div>
            <div class="next-button"><i class="fas fa-duotone fa-forward-step"></i></div>
        </div>    -->
        
	    <h2>${dto.bookTitle}</h2>
	    <p class="contentItem" style="text-align: center;">${dto.bookInfo}</p>
	    
		<div class="commentline">
            <hr>
        </div>
        
        <div class="buttonWrap">
            <input type="button" value="좋아요" class="sss">
            <input type="button" value="스크랩" class="sss">
            <input type="button" value="신고" class="sss">
        </div>

        <div class="writeComment">
            <textarea class="write"></textarea>
            <input type="button" value="작성하기" class="addComment">
        </div>

        <div class="add">
            
        </div>
        
	    <div class="commentsBox">
	        <div class="commentsline">
	            <hr>
	                <p>Comments</p>
	            <hr>
	        </div>
	    <div class="left">
	        <div class="infoItems">
	        	<div class="userIcon">
	            	<i class="fa-regular fa-user"></i>
	            	<p>Peldi</p>
	        	</div>
	        <div class="feelItems">
                        <div class="replyItems">
                            <i class="fa-regular fa-comment-dots"></i>
                            <p class="commentCount">20</p>
                        </div>
                        <div class="likeItems">
                            <i class="fa-solid fa-heart"></i>
                            <p>22</p>
                        </div>
                    </div>
                </div>


                <div class="loremItems">
                    <span class="writeItems">Peldi commented 2 days ago</span>
                    <br>
                    <span>Lorem ipsum dolor sit amet consectetur adipisicing elit. Velit, ea illo! Quaerat, obcaecati
                        neque
                        quo maiores veniam voluptas, accusamus error quod itaque corporis, ipsa inventore magnam
                        voluptate
                        deserunt culpa ea.</p>
                        <br>
                        <span>Lorem ipsum dolor sit amet, consectetur adipisicing elit. Culpa, veritatis inventore?
                            Assumenda a recusandae inventore perspiciatis molestiae expedita aperiam minus, quam at.
                            Quisquam, corporis similique rerum eius quos dignissimos impedit.</span>
                </div>
                <div class="replyButton">
                    <div class="replyButtons">
                        <input type="button" value="답변보기" class="reply">
                        <input type="button" value="답변달기" class="reply">
                    </div>
                </div>

            </div>
            <div class="commentBoxLine">
                <hr>
            </div>
            <div class="left">
                <div class="infoItems">
                    <div class="userIcon">
                        <i class="fa-regular fa-user"></i>
                        <p>Peldi</p>
                    </div>
                    <div class="feelItems">
                        <div class="replyItems">
                            <i class="fa-regular fa-comment-dots"></i>
                            <p class="commentCount">20</p>
                        </div>
                        <div class="likeItems">
                            <i class="fa-solid fa-heart"></i>
                            <p>22</p>
                        </div>
                    </div>
                </div>


                <div class="loremItems">
                    <span class="writeItems">Peldi commented 2 days ago</span>
                    <br>
                    <span>Lorem ipsum dolor sit amet consectetur adipisicing elit. Velit, ea illo! Quaerat, obcaecati
                        neque
                        quo maiores veniam voluptas, accusamus error quod itaque corporis, ipsa inventore magnam
                        voluptate
                        deserunt culpa ea.</p>
                        <br>
                        <span>Lorem ipsum dolor sit amet, consectetur adipisicing elit. Culpa, veritatis inventore?
                            Assumenda a recusandae inventore perspiciatis molestiae expedita aperiam minus, quam at.
                            Quisquam, corporis similique rerum eius quos dignissimos impedit.</span>
                </div>
                <div class="replyButton">
                    <div class="replyButtons">
                        <input type="button" value="답변보기" class="reply">
                        <input type="button" value="답변달기" class="reply">
                    </div>
                </div>
            </div>
   		<div>

        <div class="commentBox">
            <p class="commentTitle">Leave a comment</p>
            <hr>
            <div class="userItems">
                <i class="fa-regular fa-user"></i>
                <span class="usernameItems">Mike</span>
            </div>
            <textarea class="textItems"></textarea>
        </div>
        <div class="submitItems">
            <p><input type="button" value="Submit Comments" style="color:white" class="submit"></p>
        </div>

        <div class="footerline">
            <hr>
        </div>

        <div class="controlContainer">
            <div class="buttonItems">
                <input type="button" value="다음" class="nextItems">
                <input type="button" value="이전" class="beforeItems">
            </div>
            <div class="listBox">
                <input type="button" value="목록" class="listItems">
            </div>
        </div>

</div>
</div>

	<script src="https://code.jquery.com/jquery-3.7.1.js"></script>
	<script>
	   const CAROUSEL_LENGTH = document.querySelectorAll(".cell").length - 1;
	    let current = 0;

	    // 캐러셀의 갯수와 현재 캐러셀이 몇번째인지 나타내는 변수
	    const $carousel = document.querySelector(".carousel");
	    const $prevButton = document.querySelector(".prev-button");
	    const $nextButton = document.querySelector(".next-button");

	    const nextEvent = () => {
	    if(current !== CAROUSEL_LENGTH) {
	        \$carousel.style.transform = `translateX(\${(current + 1) * -400}px)`;
	        current++;
	        console.log(current);
	    } else {
	        current = 0;
	        \$carousel.style.transform = `translateX(0px)`;
	        console.log(current);
	        }
	    };

	    const prevEvent = () => {
	        if(current !== 0) {
	            current--;
	            $carousel.style.transform = `translateX(${current * -400}px)`;
	            console.log(current);
	        } else {
	            current = CAROUSEL_LENGTH;
	            $carousel.style.transform = `translateX(${CAROUSEL_LENGTH * -400}px)`;
	            console.log(current);
	        }
	    };

	    $nextButton.addEventListener('click', nextEvent);
	    $prevButton.addEventListener('click', prevEvent);

	
	</script>
	</body>
</html>