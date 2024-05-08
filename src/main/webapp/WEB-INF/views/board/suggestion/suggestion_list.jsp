<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
	<%@include file="/WEB-INF/views/template/asset.jsp"%>
	<link rel="stylesheet" href="/sangsangjakka/resources/css/board/suggestion/suggestion_list.css">
	<style>
	
	
	</style>
	</head>
	<body>
	
		<!-- header -->
		<%@include file="/WEB-INF/views/template/header.jsp"%>
	
		<section class="notice">
    	<div class="pageTitle">
          <div>
              <h3>건의사항</h3>
          </div>
      	</div>
      	
      	<div class="pageBanner">
      		<div class="pageWrap">
      			<div class="noticeInfo">
      				<div class="noticeHeader">건의사항 안내</div>
      				<div class="noticeContents">여러분의 소중한 의견을 들려주세요.</div>
      			</div>
      			<div class="noticeImg">
      				<img src="/sangsangjakka/resources/img/email.png" alt="" />
      			</div>
      		</div>
      </div>

    	<!-- board list area -->
	   <div>
        <form>
           <select class="suggestion">
               <option value="recent">최신순</option>
               <option value="view">조회순</option>
           </select>
       </form>
       </div>
  
  
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
                  <c:if test="${list.size()} == 0">
                  <tr>
                  	<td colspan="5">조회할 게시물이 없습니다.</td>
                  </c:if>
                  
                  <c:forEach items="${list}" var="dto">
                  	<tr>
                  		<!-- 글 번호 -->
                  		<td>${dto.sgstSeq}</td>
                  		<!-- 제목 -->
                  		<td><a href="/sangsangjakka/board/suggestion/View.do?page=${dto.sgstSeq}">${dto.sgstTitle}</a></td>
                  		<!-- 작성자 -->
                  		<td>${dto.userNick}</td>
                  		<!-- 등록일 -->
                  		<td>${dto.sgstRegdate}</td>
                  		<!-- 조회수 -->
                  		<td>${dto.sgstCnt}</td>
                  	</tr>	
                  </c:forEach>

                  </tbody>
                  <footer></footer>
              </table>
          </div>
      </div>
      
     <!-- 작성 -->

      <div class="WrittenBox">
          <input type="button" class="btnWritten" value="쓰기">
          <!--  onclick="location.href='/sangsangjakka/board/suggestion/add.do'" -->
          
      </div>
      
      <!-- board seach area -->
      <div id="boardSearch">
        <div>
            <div class="searchWindow">
                <form action="/sangsangjakka/board/suggestion/list.do" method="GET">
                    <div class="searchWrap">
                        <label for="search" class="blind">건의사항 내용 검색</label>
                          <select class="column" name="column">
                              <option value="sgstTitle">제목</option>
                              <option value="sgstContents">내용</option>
                          </select>
                        <input id="search" type="search" name="word" placeholder="검색어를 입력해주세요." value="${map.word}">
                        <input type="button" class="btnSearch" value="검색">
                    </div>
                </form>
            </div>
        </div>
    </div>

    <div class="paging">
      <div id="pagebar">${pageBar}</div>
    </div>
  
  </section>
		
		<!-- footer -->
		<%@include file="/WEB-INF/views/template/footer.jsp"%>
		
	<script src="https://code.jquery.com/jquery-3.7.1.js"></script>
	 <script src="https://kit.fontawesome.com/8a0ce49d35.js" crossorigin="anonymous"></script>
	<script>
		// 현재 로그인 한 사용자
		var userId = '<%=session.getAttribute("userId")%>';
		
		// 글을 눌렀을 때 클릭하면 동적으로 제한 > 수정중
// 		var article = document.querySelectorAll("a");
// 		article.addEventListener('click', function() {

// 			if() {
				
// 				alert('비밀글로 설정된 글입니다.');
// 				location.href='/sangsangjakka/board/suggestion/list.do';
// 			} else {
// 				location.href='sangsangjakka/board/suggestion/View.do';
// 			}
// 		});
		
		// 쓰기 버튼을 눌렀을 때 제한
		var write = document.querySelector('.btnWritten');
        write.addEventListener('click', function() {
        	
        	if(userId == "null") {
    			const result = confirm('회원만 작성 가능합니다. 로그인 창으로 이동하시겠습니까?');
    			if(result) {
    				location.href='/sangsangjakka/user/login.do';
    			} else {
    				location.href='/sangsangjakka/board/suggestion/list.do';
    			}
    		} else {
    			location.href='/sangsangjakka/board/suggestion/add.do';
    		}
            
        });
	</script>
	</body>
</html>