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
         <div class="mainTitle">📍 어제의 인기도서</div>
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