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
	
	
		
	<section class="notice">
	
		<div class="pageTitle">
          <div>
              <h3>동화 공유 게시판 </h3>
          </div>
      </div>
      
      <div class="pageBanner">
      		<div class="pageWrap">
      			<div class="noticeInfo">
      				<div class="noticeHeader">동화 공유 게시판 안내</div>
      				<div class="noticeContents">아이들의 상상력이 담긴 동화책을 볼 수 있습니다.</div>
      			</div>
      			<div class="noticeImg">
      				<img src="/sangsangjakka/resources/img/book.png" alt="" />
      			</div>
      		</div>
      </div>
      
      <div>
        <form>
           <div class="bookOrderWrap">
           		<div class="bookOrder">
	               <button class="bookNew">최신순</button>
	               <button class="bookGood">인기순</button>
	               <button class="bookComment">댓글순</button>
	               <button class="bookBasic">가나다순</button>
               </div>
           </div>
       </form>
       </div>
       

       
  <div class="bookShareWrap">
  

       <c:forEach items="${list}" var="dto">
		  		<div class="bookShareContainer" onclick="location.href='/sangsangjakka/board/book/view.do?no=${dto.bookSeq}';">
				    <div class="bookImg">
				      <img src="${dto.bookCover}" alt="책 표지 이미지" />
				    </div>
				    <div class="bookDetails">
				      <div class="bookDate">
				        <div class="createDate">${dto.bookRegdate}</div>
				      </div>
				      <div class="bookOwner">
				      	<i class="fa-regular fa-user"></i>
				       <div class="createAuthor">${dto.userNick}</div>
					  </div>
				      <div class="bookTitle">
				        <div class="createTitle">${dto.bookTitle}</div>
				      </div>
				      <div class="bookContents">
				        <div class="createContents">${dto.bookInfo}</div>
				      </div>
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
				  </div>
		  </c:forEach>

		  </div>
       
<!--   <div class="bookShareWrap">
  <div class="bookShareContainer">
    <div class="bookImg">
      <img src="/sangsangjakka/resources/img/book1.jpg" alt="책 표지 이미지" />
    </div>
    <div class="bookDetails">
      <div class="bookDate">
        <div class="createDate">2024-05-09</div>
      </div>
      <div class="bookOwner">
      	<i class="fa-regular fa-user user-icon"></i>
       <div class="createAuthor">예지야놀자</div>
	  </div>
      <div class="bookTitle">
        <div class="createTitle">To the Sea</div>
      </div>
      <div class="bookContents">
        <div class="createContents">여러 귀여운 동물들의 보물찾기 대탐험! 직접 그린 그림입니다 ㅎㅎ 재미있게 봐주세요.</div>
      </div>
      <div class="iconItems">
        <div class="subItems">
          <i class="fa-solid fa-bookmark bookmarker"></i>
          <p>20</p>
        </div>
        <div class="subItems">
          <i class="fa-solid fa-heart heart"></i>
          <p>30</p>
        </div>
        <div class="subItems">
          <i class="fa-solid fa-comment comment"></i>
          <p>10</p>
        </div>
      </div>
    </div>
  </div>
  
  <div class="bookShareContainer">
    <div class="bookImg">
      <img src="/sangsangjakka/resources/img/book4.jpg" alt="책 표지 이미지" />
    </div>
    <div class="bookDetails">
      <div class="bookDate">
        <div class="createDate">2024-05-09</div>
      </div>
      <div class="bookOwner">
      	<i class="fa-regular fa-user"></i>
       <div class="createAuthor">예지야놀자</div>
	  </div>
      <div class="bookTitle">
        <div class="createTitle">To the Sea</div>
      </div>
      <div class="bookContents">
        <div class="createContents">여러 귀여운 동물들의 보물찾기 대탐험! 직접 그린 그림입니다 ㅎㅎ 재미있게 봐주세요.</div>
      </div>
      <div class="iconItems">
        <div class="subItems">
          <i class="fa-solid fa-bookmark bookmarker"></i>
          <p>20</p>
        </div>
        <div class="subItems">
         <i class="fa-solid fa-heart heart"></i>
          <p>30</p>
        </div>
        <div class="subItems">
          <i class="fa-solid fa-comment comment"></i>
          <p>10</p>
        </div>
      </div>
    </div>
  </div>
  
  <div class="bookShareContainer">
    <div class="bookImg">
      <img src="/sangsangjakka/resources/img/book3.jpg" alt="책 표지 이미지" />
    </div>
    <div class="bookDetails">
      <div class="bookDate">
        <div class="createDate">2024-05-09</div>
      </div>
      <div class="bookOwner">
      	<i class="fa-regular fa-user"></i>
       <div class="createAuthor">예지야놀자</div>
	  </div>
      <div class="bookTitle">
        <div class="createTitle">To the Sea</div>
      </div>
      <div class="bookContents">
        <div class="createContents">여러 귀여운 동물들의 보물찾기 대탐험! 직접 그린 그림입니다 ㅎㅎ 재미있게 봐주세요.</div>
      </div>
      <div class="iconItems">
        <div class="subItems">
          <i class="fa-solid fa-bookmark bookmarker"></i>
          <p>20</p>
        </div>
        <div class="subItems">
          <i class="fa-solid fa-heart heart"></i>
          <p>30</p>
        </div>
        <div class="subItems">
          <i class="fa-solid fa-comment comment"></i>
          <p>10</p>
        </div>
      </div>
    </div>
  </div>
  
    <div class="bookShareContainer">
    <div class="bookImg">
      <img src="/sangsangjakka/resources/img/book2.jpg" alt="책 표지 이미지" />
    </div>
    <div class="bookDetails">
      <div class="bookDate">
        <div class="createDate">2024-05-09</div>
      </div>
      <div class="bookOwner">
      	<i class="fa-regular fa-user"></i>
       <div class="createAuthor">예지야놀자</div>
	  </div>
      <div class="bookTitle">
        <div class="createTitle">To the Sea</div>
      </div>
      <div class="bookContents">
        <div class="createContents">여러 귀여운 동물들의 보물찾기 대탐험! 직접 그린 그림입니다 ㅎㅎ 재미있게 봐주세요.</div>
      </div>
      <div class="iconItems">
        <div class="subItems">
          <i class="fa-solid fa-bookmark bookmarker"></i>
          <p>20</p>
        </div>
        <div class="subItems">
          <i class="fa-solid fa-heart heart"></i>
          <p>30</p>
        </div>
        <div class="subItems">
          <i class="fa-solid fa-comment comment"></i>
          <p>10</p>
        </div>
      </div>
    </div>
  </div>
  
  
  
</div> -->
    

     


      
      
      
		    
	 </section>
		
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