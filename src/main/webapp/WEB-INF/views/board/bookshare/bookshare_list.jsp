<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
	<%@include file="/WEB-INF/views/template/asset.jsp"%>
	<link rel="stylesheet" href="/sangsangjakka/resources/css/board/bookshare/bookshare_list.css">
	<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.5.2/css/all.min.css">
	<style>
	
	
	</style>
	</head>
	<body>
	
		<!-- header -->
		<%@include file="/WEB-INF/views/template/header.jsp"%>

			<!-- 동화책 공유 게시판 -->
		    <h2>동화 공유 게시판</h2>
		    <div class="boardContainer">
		    	
		    	<c:if test="${list.size() == 0}">
					<img>
					게시글이 없습니다.
				  </c:if>
		    	
		    	<!-- 동화책 표시 -->
		    	<c:forEach items="${list}" var="dto">
		    	
		    		<div class="boardBox">
						<img src="${dto.bookCover}">
						<p class="userItems"><i class="fas fa-user"></i>${dto.userNick} ${dto.bookRegdate}</p>
						<p class="titleItems">${dto.bookTitle}</p>
			        	<p class="contentItems">${dto.bookInfo}</p>
			        	<div class="iconItems">
			          		<div class="subItems">
			            		<i class="fas fa-check"></i>
			            		<p>${dto.bookReviewCnt}</p>
			            	</div>
			            	<div class="subItems">
			            		<i class="fas fa-heart"></i>
			            		<p>${dto.likeCnt}</p>
			            	</div>
			            		<div class="subItems">
			            		<i class="fas fa-comments"></i>
			            		<p>${dto.bookScrapCnt}</p>
			            	</div>
			        	</div>
			        <hr>	    		
		    		</div>
		    		
		    	</c:forEach>
		    	
		    	<!-- 검색 인터페이스 -->
				<form id="searchForm" method="GET" action="/sangsangjakka//board/book/list.do">
					<select name="column">
						<option value="subject">제목</option>
						<option value="content">내용</option>
						<option value="nick">닉네임</option>
					</select>
					<input type="text" name="word" class="long" required value="${map.word}">
					<input type="submit" value="검색하기">
				</form>
				
				<!-- 페이지바 -->
				<div id="pagebar">${pagebar}</div>
		    
			    <div class="boardBox1">
			        <img src="/sangsangjakka/resources/img/book1.jpg" class="book1Items">
			        <p class="userItems"><i class="fas fa-user"></i>예지공주 Dec 13, 2023</p>
			        
			        <p class="titleItems">이상하고 신비한 동물 사전</p>
			        <p class="contentItems">여러 귀여운 동물들의 보물찾기 대탐험!<br>직접 그린 그림입니다 ㅎㅎ 재밌게 봐주세요</p>
			        <div class="iconItems">
			            <div class="subItems">
			            	<i class="fas fa-check"></i>
			            <p>20</p>
			            </div>
			            <div class="subItems">
			            	<i class="fas fa-heart"></i>
			            	<p>22</p>
			            </div>
			            	<div class="subItems">
			            	<i class="fas fa-comments"></i>
			            	<p>23</p>
			            </div>
			        </div>
			        <hr>
			  	</div>

		        
				

		        <div class="boardBox2">
			        <img src="/sangsangjakka/resources/img/book2.jpg" class="book1Items">
			        <p class="userItems"><i class="fas fa-user"></i>예지공주 Dec 13, 2023</p>
			        
			        <p class="titleItems">이상하고 신비한 동물 사전</p>
			        <p class="contentItems">여러 귀여운 동물들의 보물찾기 대탐험!<br>직접 그린 그림입니다 ㅎㅎ 재밌게 봐주세요</p>
			        <div class="iconItems">
			            <div class="subItems">
			            <i class="fas fa-check"></i>
			            <p>20</p>
			            </div>
			            <div class="subItems">
			            <i class="fas fa-heart"></i>
			            <p>22</p>
			            </div>
			            <div class="subItems">
			            <i class="fas fa-comments"></i>
			            <p>23</p>
			            </div> 
			        </div>
			        <hr>
			     </div>
				
					

			        <div class="boardBox3">
			            <img src="/sangsangjakka/resources/img/book3.jpg" class="book1Items">
			            <p class="userItems"><i class="fas fa-user"></i>예지공주 Dec 13, 2023</p>
			            
			            <p class="titleItems">이상하고 신비한 동물 사전</p>
			            <p class="contentItems">여러 귀여운 동물들의 보물찾기 대탐험!<br>직접 그린 그림입니다 ㅎㅎ 재밌게 봐주세요</p>
			            <div class="iconItems">
			                <div class="subItems">
			                <i class="fas fa-check"></i>
			                <p>20</p>
			                </div>
			                <div class="subItems">
			                <i class="fas fa-heart"></i>
			                <p>22</p>
			                </div>
			                <div class="subItems">
			                <i class="fas fa-comments"></i>
			                <p>23</p>
			                </div>  
			            </div>
			            <hr>
			        </div>
			    
				
				
			
			        <div class="boardBox4">
			            <img src="/sangsangjakka/resources/img/book4.jpg" class="book1Items">
			            <p class="userItems"><i class="fas fa-user"></i>예지공주 Dec 13, 2023</p>
			                
			            <p class="titleItems">이상하고 신비한 동물 사전</p>
			            <p class="contentItems">여러 귀여운 동물들의 보물찾기 대탐험!<br>직접 그린 그림입니다 ㅎㅎ 재밌게 봐주세요</p>
			            <div class="iconItems">
			                <div class="subItems">
			                <i class="fas fa-check"></i>
			                <p>20</p>
			                </div>
			                <div class="subItems">
			                <i class="fas fa-heart"></i>
			                <p>22</p>
			                </div>
			                <div class="subItems">
			                <i class="fas fa-comments"></i>
			                <p>23</p>
			                </div>
			            </div>
			            <hr>
			        </div>
		        
		    
		        
		    </div>
		    <div class="pageItems">
		        <i class="fa-solid fa-angles-left"></i><i class="fas fa-angle-left"></i><i class="fas fa-regular fa-1"></i><i class="fas fa-angle-right"></i><i class="fa-solid fa-angles-right"></i>
		    </div>
		
		
		<!-- footer -->
		<%@include file="/WEB-INF/views/template/footer.jsp"%>

	<script src="https://code.jquery.com/jquery-3.7.1.js"></script>
	<script>
		var pics = document.querySelectorAll('img');
		
		
		pics.forEach(function(img) {
		    img.addEventListener('click', function() {
		    	if(${userId} == "null") {
		    		const result = confirm('회원만 볼 수 있는 페이지입니다. 회원 가입 창으로 이동하시겠습니까?');
		    		if(result) {
		    			location.href = "/sangsangjakka/user/signup.do";
		    		} else {
		    			location.href = "/sangsangjakka/board/book/list.do";
		    		}
		    	} else {
		        	location.href = "/sangsangjakka/board/book/view.do";
		    	}
		    });
		});
		
		<c:if test="${map.search == 'y'}">
			//검색중 상태 유지
			$('input[name=word]').val('${map.word}');
			$('select[name=column]').val('${map.column}');
		</c:if>
		
	</script>
	</body>
</html>