<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
	<%@include file="/WEB-INF/views/template/asset.jsp"%>
	<link rel="stylesheet" href="/sangsangjakka/resources/css/board/notice/notice_list.css">
	<style>
	
	
	</style>
	</head>
	<body>
	
		<!-- header -->
		<%@include file="/WEB-INF/views/template/header.jsp"%>
	
		<section class="notice">
    <div class="pageTitle">
          <div>
              <h3>공지사항</h3>
          </div>
      </div>
      
      <div class="pageBanner">
      		<div class="pageWrap">
      			<div class="noticeInfo">
      				<div class="noticeHeader">공지사항 안내</div>
      				<div class="noticeContents">상상자까의 새로운 소식을 알려드립니다.</div>
      			</div>
      			<div class="noticeImg">
      				<img src="/sangsangjakka/resources/img/notice_banner_img.png" alt="" />
      			</div>
      		</div>
      </div>

     
    <div>
       
           <div class="bookOrderWrap">
			  <div class="bookOrder">
			    <button type="button" class="bookNew" onclick="redirectTo('newest')">최신순</button>
				<button type="button" class="bookClick" onclick="redirectTo('view_count')">조회순</button>


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
				  
				  <c:if test="${not empty fix}">
					  <c:forEach items="${fix}" var="dto">
					  	<tr>
					  	  	<td>📌공지</td>
	                      	<th><a href="/sangsangjakka/board/notice/view.do?no=${dto.noticeSeq}">${dto.noticeTitle}</a></th>
	                      	<td>${dto.noticeRegdate}</td>
	                      	<td>${dto.noticeCnt}</td>
	                  	</tr>
					  </c:forEach>
                  </c:if>
                  
                  <c:if test="${not empty list}">
	                  <c:forEach items="${list}" var="dto">
	                  	  <tr>
		                      <td>공지</td>
		                      <th><a href="/sangsangjakka/board/notice/view.do?no=${dto.noticeSeq}">${dto.noticeTitle}</a></th>
		                      <td>${dto.noticeRegdate}</td>
		                      <td>${dto.noticeCnt}</td>
		                  </tr>
					  </c:forEach>
				  </c:if>
                  
                  </tbody>
              </table>
          </div>
      </div>


      <!-- board seach area -->
      <div id="boardSearch">
        <div>
            <div class="searchWindow">
                <form method="GET" action="/sangsangjakka/board/notice/list.do">
                    <div class="searchWrap">
                        <label for="search" class="blind">공지사항 내용 검색</label>
                          <select class="column" name="column">
                              <option value="noticeTitle">제목</option>
                              <option value="noticeContents">내용</option>
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
	</script>
	</body>
</html>