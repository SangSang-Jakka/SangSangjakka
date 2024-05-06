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
	
		<section class="suggestion">
    	<div class="pageTitle">
          <div>
              <h3>건의사항</h3>
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
                  <tr>
                      <td>7</td>
                      <th><a href="/sangsangjakka/board/suggestion/View.do"><i class="fa-solid fa-lock"></i> 비밀글입니다</a><p>[3]</p></th>
                      <td>홍길동</td>
                      <td>2017.06.15</td>
                      <td>23</td>
                  </tr>
                  <tr>
                      <td>6</td>
                      <th><a href="#!">동화책 오류</a><p>[1]</p></th>
                      <td>홍길동</td>
                      <td>2017.06.15</td>
                      <td>0</td>
                  </tr>
                  <tr>
                      <td>5</td>
                      <th><a href="#!"><i class="fa-solid fa-lock"></i> 비밀글입니다</a></th>
                      <td>홍길동</td>
                      <td>2017.06.15</td>
                      <td>0</td>
                  </tr>
                  <tr>
                    <td>4</td>
                    <th><a href="#!"><i class="fa-solid fa-lock"></i> 비밀글입니다</a><p>[3]</p></th>
                    <td>홍길동</td>
                    <td>2017.06.15</td>
                    <td>0</td>
                </tr>
                <tr>
                    <td>3</td>
                    <th><a href="#!"><i class="fa-solid fa-lock"></i> 비밀글입니다</a></th>
                    <td>홍길동</td>
                    <td>2017.06.15</td>
                    <td>0</td>
                </tr>
  
                <tr>
                    <td>2</td>
                    <th><a href="#!"><i class="fa-solid fa-lock"></i> 비밀글입니다</a><p>[5]</p></th>
                    <td>홍길동</td>
                    <td>2017.06.15</td>
                    <td>0</td>
                </tr>
  
                <tr>
                    <td>1</td>
                    <th><a href="#!"><i class="fa-solid fa-lock"></i> 비밀글입니다</a><p>[1]</p></th>
                    <td>홍길동</td>
                    <td>2017.06.15</td>
                    <td>0</td>
                </tr>
                  </tbody>
              </table>
          </div>
      </div>
      
     <!-- 작성 -->

      <div class="WrittenBox">
          <input type="button" class="btnWritten" value="쓰기" onclick="location.href='/sangsangjakka/board/suggestion/add.do'">
      </div>
      
      <!-- board seach area -->
      <div id="boardSearch">
        <div>
            <div class="searchWindow">
                <form action="">
                    <div class="searchWrap">
                        <label for="search" class="blind">건의사항 내용 검색</label>
                          <select class="column">
                              <option value="subject">제목</option>
                              <option value="subject">내용</option>
                          </select>
                        <input id="search" type="search" placeholder="검색어를 입력해주세요." value="">
                        <input type="button" class="btnSearch" value="검색">
                    </div>
                </form>
            </div>
        </div>
    </div>

    <div class="paging">
      <a href=""><<</a>
      <a href=""><</a>
      <a href="">1</a>
      <a href="">2</a>
      <a href="">3</a>
      <a href="">4</a>
      <a href="">5</a>
      <a href="">6</a>
      <a href="">7</a>
      <a href="">8</a>
      <a href="">9</a>
      <a href="">10</a>
      <a href="">></a>
      <a href="">>></a>
    </div>
  
  </section>
		
		<!-- footer -->
		<%@include file="/WEB-INF/views/template/footer.jsp"%>
		
	<script src="https://code.jquery.com/jquery-3.7.1.js"></script>
	 <script src="https://kit.fontawesome.com/8a0ce49d35.js" crossorigin="anonymous"></script>
	<script>
		// 현재 로그인 한 사용자
		var userId = '<%=session.getAttribute("userId")%>';
		
		var article = document.querySelectorAll("a");
		article.addEventListener('click', function() {
			// 수정중
			if() {
				
				alert('비밀글로 설정된 글입니다.');
				location.href='/sangsangjakka/board/suggestion/list.do';
			} else {
				location.href='sangsangjakka/board/suggestion/View.do';
			}
		});
	</script>
	</body>
</html>