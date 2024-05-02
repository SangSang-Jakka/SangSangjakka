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

	    <h2>이상하고 신비한 동물 사전</h2>
	    <p class="contentItem" style="text-align: center;">여러 귀여운 동물들의 보물찾기 대탐험! 직접 그린 그림입니다 ㅎㅎ 재밌게 봐주세요</p>
	        
	    <div class="commentsBox">
	        <div class="commentsline">
	            <hr>
	                <p>Comments</p>
	            <hr>
	        </div>
	    <div class="left">
	        <div class="infoItems">
	            <i class="fa-regular fa-user"></i>
	            <p>Peldi</p>
	        </div>
	        <!--여기서부터-->
	        <div class="aaa">
	            <span>Peldi commented 2 days ago</span>
	        
	            <span>신고하기</span>
	            <p>Lorem ipsum dolor sit amet consectetur, adipisicing elit. Cumque, repellat? Maxime accusamus, doloribus quisquam accusantium rerum nostrum at! Impedit repellat sapiente nihil iure et illum corrupti ullam sequi, pariatur assumenda.</p>
	
	        </div>
	    </div>
	        
	        <div class="feelItems">
	            <div class="replyItems">
	                <i class="fa-regular fa-comment-dots"></i>
	                <p>20</p>
	            </div>
	            
	            <div class="likeItems">
	                <i class="fa-solid fa-heart"></i>
	                <p>22</p>
	            </div>
	
	        </div>
	    
	    <div class="right"></div>
	        <p>Peldi commented 2 days ago</p>
	        
	            <span>신고하기</span>
	            <p>Lorem ipsum dolor sit amet consectetur, adipisicing elit. Cumque, repellat? Maxime accusamus, doloribus quisquam accusantium rerum nostrum at! Impedit repellat sapiente nihil iure et illum corrupti ullam sequi, pariatur assumenda.</p>
	            <input type="button" value="답변보기">
	            <input type="button" value="답변달기">
	    </div>
	
	        
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
	        <p><input type="button" value="Submit Comments" style="color:white"></p>
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

	<script src="https://code.jquery.com/jquery-3.7.1.js"></script>
	<script>
	</script>
	</body>
</html>