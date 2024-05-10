<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
    <%@include file="/WEB-INF/views/template/asset.jsp"%>
    <link rel="stylesheet" href="/sangsangjakka/resources/css/board/bookshare/default.css" />
    <link rel="stylesheet" href="/sangsangjakka/resources/css/board/bookshare/bookblock.css" />
    <link rel="stylesheet" href="/sangsangjakka/resources/css/board/bookshare/bookshare_view.css">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.0/jquery.min.js"></script>
    <script src="/sangsangjakka/resources/js/modernizr.custom.js"></script>
    <script src="https://kit.fontawesome.com/e075b9b5dc.js" crossorigin="anonymous"></script>
    <script src="/sangsangjakka/resources/js/jquerypp.custom.js"></script>
    <script src="/sangsangjakka/resources/js/jquery.bookblock.js"></script>
</head>
<body>
	
	<!-- header -->
	<%@include file="/WEB-INF/views/template/header.jsp"%>

<section class="notice">

<div class="pageTitle">
          <div>
              <h3>동화 공유 게시판 </h3>
          </div>
      </div>

     <div class="makedPageViewer">
        <div class="containerLeft">
            <nav class="flex">
                <a id="bb-nav-prev" href="#" class="bb-custom-icon pointer">
                    <i class="fa-solid fa-angle-left"></i>
                </a>
                <a id="bb-nav-first" href="#" class="bb-custom-icon pointer">
                    <i class="fa-solid fa-angles-left"></i>
                </a>
            </nav>
        </div>
        <div class="bb-custom-wrapper">
            <div id="bb-bookblock" class="bb-bookblock">
                <c:forEach items="${pageMap}" var="entry">
                    <div class="bb-item">
                        <div class="pageImage" style="background-image: url('${entry.value.pageUrl}');"></div>
                        <div class="pageContents">${entry.value.pageContents}</div>
                    </div>
                </c:forEach>
            </div>
        </div>
        <div class="containerRight">
            <nav class="flex">
                <a id="bb-nav-next" href="#" class="bb-custom-icon pointer">
                    <i class="fa-solid fa-angle-right"></i>
                </a>
                <a id="bb-nav-last" href="#" class="bb-custom-icon pointer">
                    <i class="fa-solid fa-angles-right"></i>
                </a>
            </nav>
        </div>
    </div>
    
    
    <div class="userBestSellerTitle">
         <div class="mainTitle">📖 책 정보</div>
    </div>
    <div class="createBookWrap"> 
    	<div class="createBookContainer">
    	<div class="createBookImg">
    		<img class="bookImage" src="${dto.bookCover}" alt="책 표지 이미지" />
    	</div>
    	<div class="createBookInfo">
    		<div class="createDate">${dto.bookRegdate}</div>
    		<div class="userNick">
    		<i class="fa-regular fa-user"></i>
			${dto.userNick}</div>
    		<div class="createTitle">${dto.bookTitle}</div>
    		<div class="createContents">${dto.bookInfo}</div>
    		<div class="iconItems">
			<div class="subItems">
				<i class="fa-solid fa-bookmark bookmarker"></i>
				<p id="scrapCnt">${dto.bookScrapCnt}</p>
			</div>
			<div class="subItems" >
				 <i class="fa-solid fa-heart heart"></i>
				 <p id="likeCnt">${dto.likeCnt}</p>
			</div>
			<div class="subItems">
				 <i class="fa-solid fa-comment comment"></i>
				 <p>${reviewTotal}</p>
			</div>
		</div>
    	</div>
    	<div class="bookReviewWrap">
  			<ul>
  				<li>🎀 아이의 창의력이 매우 뛰어납니다!! 그림도 너무 이뻐요</li>
  				<li>🎀 우리 아이 최애 동화책 입니다. 하루종일 이것만 봐요</li>
  				<li>🎀 우리 가족과 비슷한 느낌이라 따뜻하고 좋았습니다.</li>
  				<li>🎀 우리 아이도 이 동화책 같이 멋진 동화책을 만들 수 있겠죠?</li>
  				<li>🎀 동화책이 상상력을 높여주는거 같아 좋았습니다.</li>
  			</ul>
    	</div>
    	</div>
    	<div class="btnWrap">
    		<c:if test="${result == true}">
    		<button class="like" id="likeBtn">좋아요를 눌렀어요!</button>
    		</c:if>
    		<c:if test="${result == false}">
    		<button class="like" id="likeBtn">동화책이 좋아요!</button>
    		</c:if>
    		
    		<button class="like reviewBtn" >동화책이 이상해요!</button>	
    		
    		<c:if test="${resultScrap == true}">
    		<button class="like saveBtn" id="scrapBtn">동화책을 저장했어요!</button>
    		</c:if>
    		<c:if test="${resultScrap == false}">
    		<button class="like saveBtn" id="scrapBtn">동화책을 저장할래요!</button>
    		</c:if>
    	
    	</div>
    	
    </div>
    
    
    
    
    <div class="userBestSellerTitle">
         <div class="mainTitle">📜 소중한 리뷰</div>
    </div>
    
    <form method="POST" action="/sangsangjakka/board/book/view.do">
    <div class="reviewWriteWrap">
  <!--   	<div class="reviewWriteTitle">
    		어떤 점이 좋았나요?
    	</div> -->
    	<div class="reviewWriteContents">
    		<textarea name="writeContents" id="reviewContents" cols="30" rows="10" placeholder="내용을 입력해주세요. 상세하게 적을수록 작가에게 큰 도움이 됩니다."></textarea>
    		<div class="charCount">최대 200자까지 입력 가능합니다.</div>
    	</div>
    </div>
    
    <input type="hidden" name="bookSeq" value="${bookSeq}"/>
    
    <div class="btnWriteWrap">
	    <button type="submit" class="btnWrite" >
	         <div class="write">작성하기</div>
	    </button>
    </div>
    </form>
    
   	<div class="reviewTotal">
   		<div class="reviewCnt">전체 리뷰 27건</div>
   	</div>
   	
    <div id="reviewContainer">
        <!-- 최초 5개의 리뷰 렌더링 -->
        <c:forEach items="${reviewList}" var="review" begin="0" end="4">
            <div class="reviewListWrap">
                <div class="reviewListContainer">
                    
                    <div class="reviewUserWrap">
                    	<div class="reviewUser">
	                        <i class="fa-regular fa-user"></i>
	                        <div class="reviewNick">${review.userNick}</div>
                        </div>
                        <div class="reviewDate">${review.reviewRegdate}</div>
                    </div>
                    <div class="reviewContents">${review.reviewContents}</div>
                    <form action="">
                        <div class="reviewListBtn">
                            <div class="reviewLike">
                                <button><i class="fa-regular fa-heart"></i></button>
                            </div>
                            <div class="reviewReport">
                                <button><i class="fa-regular fa-bell"></i></button>
                            </div>
                        </div>
                    </form>
                </div>
            </div>
        </c:forEach>
        
         <!-- 나머지 리뷰는 display:none으로 숨김 -->
            <c:forEach items="${reviewList}" var="review" begin="5">
                 <div class="reviewListWrap hidden">
                <div class="reviewListContainer">       
                    <div class="reviewUserWrap">
                    	<div class="reviewUser">
	                        <i class="fa-regular fa-user"></i>
	                        <div class="reviewNick">${review.userNick}</div>
                        </div>
                        <div class="reviewDate">${review.reviewRegdate}</div>
                    </div>
                    <div class="reviewContents">${review.reviewContents}</div>
                    <form action="">
                        <div class="reviewListBtn">
                            <div class="reviewLike">
                                <button><i class="fa-regular fa-heart"></i></button>
                            </div>
                            <div class="reviewReport">
                                <button><i class="fa-regular fa-bell"></i></button>
                            </div>
                        </div>
                    </form>
                </div>
            </div>
            </c:forEach>
    </div>

    <button id="showMoreBtn">더보기</button>
   	
     </section>
    
    
    
    
    <!-- footer -->
	<%@include file="/WEB-INF/views/template/footer.jsp"%>
	
 <script>
	    var Page = (function() {
	        var config = {
	            $bookBlock: $('#bb-bookblock'),
	            $navNext: $('#bb-nav-next'),
	            $navPrev: $('#bb-nav-prev'),
	            $navFirst: $('#bb-nav-first'),
	            $navLast: $('#bb-nav-last')
	        },
	        init = function() {
	            config.$bookBlock.bookblock({
	                speed: 800,
	                shadowSides: 0.8,
	                shadowFlip: 0.7
	            });
	            initEvents();
	        },
	        initEvents = function() {
	            var $slides = config.$bookBlock.children();
	
	            // add navigation events
	            config.$navNext.on('click touchstart', function() {
	                config.$bookBlock.bookblock('next');
	                return false;
	            });
	
	            config.$navPrev.on('click touchstart', function() {
	                config.$bookBlock.bookblock('prev');
	                return false;
	            });
	
	            config.$navFirst.on('click touchstart', function() {
	                config.$bookBlock.bookblock('first');
	                return false;
	            });
	
	            config.$navLast.on('click touchstart', function() {
	                config.$bookBlock.bookblock('last');
	                return false;
	            });
	
	            // add swipe events
	            $slides.on({
	                'swipeleft': function(event) {
	                    config.$bookBlock.bookblock('next');
	                    return false;
	                },
	                'swiperight': function(event) {
	                    config.$bookBlock.bookblock('prev');
	                    return false;
	                }
	            });
	
	            // add keyboard events
	            $(document).keydown(function(e) {
	                var keyCode = e.keyCode || e.which,
	                    arrow = {
	                        left: 37,
	                        up: 38,
	                        right: 39,
	                        down: 40
	                    };
	
	                switch (keyCode) {
	                    case arrow.left:
	                        config.$bookBlock.bookblock('prev');
	                        break;
	                    case arrow.right:
	                        config.$bookBlock.bookblock('next');
	                        break;
	                }
	            });
	        };
	
	        return { init: init };
	    })();
	
	    $(document).ready(function() {
	        Page.init();
	    });
	    
	    
	    
	    var startIndex = 5; // 초기 startIndex 설정
        var increment = 5; // 한 번에 보여줄 리뷰 수
        var hiddenReviews = $('.hidden');

        $('#showMoreBtn').click(function() {
            showMoreReviews();
        });

        function showMoreReviews() {
            var visibleReviews = $('.review-list-wrap:not(.hidden)').length;
            var endIndex = startIndex + increment - 1;

            for (var i = startIndex; i <= endIndex; i++) {
                if (i < hiddenReviews.length) {
                    $(hiddenReviews[i - 5]).removeClass('hidden');
                } else {
                    $('#showMoreBtn').hide(); // 모든 리뷰를 보여주었으면 더보기 버튼 숨김
                    break;
                }
            }

            startIndex = endIndex + 1;
        }
        
        
        $(document).ready(function() {
            $("#likeBtn").click(function() {
                var bookSeq = ${bookSeq}; // bookSeq 변수가 정의된 곳에서 값을 가져와야 함
                var userSeq = ${userSeq}; // userSeq 변수가 정의된 곳에서 값을 가져와야 함
				//var pressLike = $("input[name='pressLike']").val();
                
                console.log("bookSeq:", bookSeq);
                console.log("userSeq:", userSeq);

                $.ajax({
                    type: "POST",
                    url: "/sangsangjakka/board/book/like.do",
                    data: { bookSeq: bookSeq, userSeq: userSeq },
                    success: function(response) {
                        if (response === "success") {
                            // 성공적으로 INSERT 되었을 때
                            console.log("성공");
                            var updatedLikeCnt = parseInt($("#likeCnt").text()) + 1; // 좋아요 개수 증가
                            $("#likeCnt").text(updatedLikeCnt); // 좋아요 개수 업데이트
                            $("#likeBtn").text("좋아요를 눌렀어요!"); // 버튼 텍스트 변경
                            $("#likeBtn").addClass("newClass");

                            
                        } else {
                            // 실패했을 때
                            alert("좋아요 등록에 실패하였습니다.");
                        }
                    },
                    error: function(xhr, status, error) {
                        console.error(error);
                    }
                });
            });
        });
        
        $(document).ready(function() {
            $("#scrapBtn").click(function() {
                var bookSeq = ${bookSeq}; // bookSeq 변수가 정의된 곳에서 값을 가져와야 함
                var userSeq = ${userSeq}; // userSeq 변수가 정의된 곳에서 값을 가져와야 함

                
                console.log("bookSeq:", bookSeq);
                console.log("userSeq:", userSeq);

                $.ajax({
                    type: "POST",
                    url: "/sangsangjakka/board/book/scrap.do",
                    data: { bookSeq: bookSeq, userSeq: userSeq },
                    success: function(response) {
                        if (response === "success") {
                            // 성공적으로 INSERT 되었을 때
                            console.log("성공");
                            var updatedScrapCnt = parseInt($("#scrapCntㄴ").text()) + 1; // 좋아요 개수 증가
                            $("#scrapCnt").text(updatedScrapCnt); // 좋아요 개수 업데이트
                            $("#scrapBtn").text("동화책을 저장했어요!"); // 버튼 텍스트 변경
                            $("#scrapBtn").addClass("newClass");

                            
                        } else {
                            // 실패했을 때
                            alert("동화책 저장에 실패하였습니다.");
                        }
                    },
                    error: function(xhr, status, error) {
                        console.error(error);
                    }
                });
            });
        });

        
    </script>

</body>
</html>