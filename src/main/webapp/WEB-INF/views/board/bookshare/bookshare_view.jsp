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
				<p>${dto.bookScrapCnt}</p>
			</div>
			<div class="subItems">
				 <i class="fa-solid fa-heart heart"></i>
				 <p>${dto.likeCnt}</p>
			</div>
			<div class="subItems">
				 <i class="fa-solid fa-comment comment"></i>
				 <p>${dto.bookReportCnt}</p>
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
    		<button class="like">동화책이 좋아요!</button>
    		<button class="like reviewBtn">동화책이 이상해요!</button>	
    		<button class="like saveBtn">동화책을 저장할래요!</button>
    	</div>
    	
    </div>
    
    
    
    
    <div class="userBestSellerTitle">
         <div class="mainTitle">📜 소중한 리뷰</div>
    </div>
    
    <form action="">
    <div class="reviewWriteWrap">
  <!--   	<div class="reviewWriteTitle">
    		어떤 점이 좋았나요?
    	</div> -->
    	<div class="reviewWriteContents">
    		<textarea name="writeContents" id="reviewContents" cols="30" rows="10" placeholder="내용을 입력해주세요. 상세하게 적을수록 작가에게 큰 도움이 됩니다."></textarea>
    		<div class="charCount">최대 200자까지 입력 가능합니다.</div>
    	</div>
    </div>
    
    <div class="btnWriteWrap">
	    <button type="submit" class="btnWrite" onclick="" >
	         <div class="write">작성하기</div>
	    </button>
    </div>
    </form>
    
   	<div class="reviewTotal">
   		<div class="reviewCnt">전체 리뷰 27건</div>
   	</div>
   	
   	<div class="reviewListWrap">
   		<div class="reviewListContainer">
	   		<div class="reviewDate">2024-05-10</div>
	   		<div class="reviewUser">
	   			<i class="fa-regular fa-user"></i>
	   			<div class="reviewNick">상상주하</div>
	   		</div>
	   		<div class="reviewContents">Lorem ipsum dolor sit amet, consectetur adipisicing elit. Repudiandae expedita dolorum voluptatibus aliquam quam eius dolores cum earum temporibus illum doloribus aspernatur at laboriosam facere natus perferendis distinctio eveniet odit voluptatum libero aliquid saepe adipisci. Sed quas perspiciatis natus debitis quidem suscipit quibusdam aut esse necessitatibus illo voluptates consequatur voluptatum. Adipisci voluptatem consequatur cupiditate non nihil pariatur explicabo impedit expedita illum aperiam totam repudiandae provident. Explicabo perspiciatis iste quidem voluptatem tempora in dolore recusandae veniam eius dignissimos laudantium sequi nulla? Incidunt enim doloremque distinctio autem ipsa ipsam ratione in. Sit amet adipisci ipsa excepturi at numquam aperiam facere cum culpa.</div>
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
    </script>

</body>
</html>