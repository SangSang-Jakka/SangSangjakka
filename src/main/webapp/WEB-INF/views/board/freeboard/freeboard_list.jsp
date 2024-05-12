<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
	<%@include file="/WEB-INF/views/template/asset.jsp"%>
	<link rel="stylesheet" href="/sangsangjakka/resources/css/board/freeboard/freeboard_list.css">
	<style>
	
	
	</style>
	</head>
	<body>
	
		<!-- header -->
		<%@include file="/WEB-INF/views/template/header.jsp"%>
		
	<section class="notice">
    <div class="pageTitle">
          <div>
              <h3>자유 게시판</h3>
          </div>
      </div>

     <div class="pageBanner">
      		<div class="pageWrap">
      			<div class="noticeInfo">
      				<div class="noticeHeader">자유게시판 안내</div>
      				<div class="noticeContents">자유롭게 소통할 수 있는 공간입니다.</div>
      			</div>
      			<div class="noticeImg">
      				<img src="/sangsangjakka/resources/img/free.png" alt="" />
      			</div>
      		</div>
      </div>
      
      <div>
       
           <div class="bookOrderWrap">
			  <div class="bookOrder">
			    <button type="button" class="bookNew" onclick="redirectTo('newest')">최신순</button>
				<button type="button" class="bookClick" onclick="redirectTo('view_count')">조회순</button>
				<button type="button" class="bookComments" onclick="redirectTo('comment_count')">댓글순</button>


			  </div>
			</div>
    
       </div>
    <!-- board list area -->
      <div>
          <div>
              <table class="boardTable">
                  <thead>
                    <tr>
                        <th scope="col" class="thNum">번호</th>
                        <th scope="col" class="thTitle">제목</th>
                        <th scope="col" class="thWriter">작성자</th>
                        <th scope="col" class="thDate">등록일</th>
                        <th scope="col" class="thViews">조회수</th>
                    </tr>
                  </thead>
                  <tbody>
                  
                  <c:if test="${list.size() == 0}">
					<tr>
						<td colspan="5">게시물이 없습니다.</td>
					</tr>
				  </c:if>
				  
				  <c:forEach items="${list}" var="dto">
				  		<tr>
	                      <td>${dto.boardSeq}</td>
	                      <th><a href="/sangsangjakka/board/freeboard/view.do?no=${dto.boardSeq}">${dto.boardTitle }</a><p>${dto.cmntCnt}</p></th>
	                      <td>${dto.userNick}</td>
	                      <td>${dto.boardRegdate}</td>
	                      <td>${dto.boardCnt}</td>
	                   </tr>
				  </c:forEach>
         
                  </tbody>
              </table>
          </div>
      </div>

      <!-- 작성 -->

      <div class="WrittenBox">
        <input type="button" class="btnWritten" value="쓰기" onclick="location.href='/sangsangjakka/board/freeboard/add.do'">
    </div>


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
  
  </section>
  
  		<!-- footer -->
		<%@include file="/WEB-INF/views/template/footer.jsp"%>

	<script src="https://code.jquery.com/jquery-3.7.1.js"></script>
	<script>
		
		<c:if test="${map.search == 'y'}">
		//검색중 상태 유지
		$('input[name=word]').val('${map.word}');
		$('select[name=column]').val('${map.column}');
		</c:if>
		

		function redirectTo(orderBy) {
		    window.location.href = "http://localhost:8080/sangsangjakka/board/freeboard/list.do?orderBy=" + orderBy;
		}


			
			

		
	</script>
	</body>
</html>