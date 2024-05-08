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
		    	
		    	 <!-- board seach area -->
			      <div id="boardSearch">
			        <div>
			            <div class="searchWindow">
			                <form method="GET" action="/sangsangjakka/board/freeboard/list.do">
			                    <div class="searchWrap">
			                        <label for="search" class="blind">공지사항 내용 검색</label>
			                          <select class="column" name="column">
			                              <option value="boardTitle">제목</option>
			                              <option value="boardContents">내용</option>
			                              <option value="userNick">닉네임</option>
			                          </select>
			                        <input id="search" type="text" name="word" placeholder="검색어를 입력해주세요." required value="${map.word}">
			                        <input type="submit" class="btnSearch" value="검색">
			                    </div>
			                </form>
			            </div>
			        </div>
			    </div>
					    
				<div class="paging">
			      <div id="pagebar">${pagebar}</div>
			    </div>
		    	
		    	
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