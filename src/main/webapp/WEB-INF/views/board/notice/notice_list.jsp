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

     
      <div>
        <form>
           <select class="notice">
               <option value="recent">최신순</option>
               <option value="view">조회순</option>
           </select>
       </form>
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
                  <tr class>
                      <td>공지</td>
                      <th><a href="/sangsangjakka/board/notice/view.do">공지사항 안내입니다. 이용해주셔서 감사합니다</a></th>
                      <td>2017.06.15</td>
                      <td>23</td>
                  </tr>
                  <tr>
                      <td>6</td>
                      <th><a href="#!">공지사항 안내입니다. 이용해주셔서 감사합니다</a></th>
                      <td>2017.06.15</td>
                      <td>0</td>
                  </tr>
                  <tr>
                      <td>5</td>
                      <th><a href="#!">공지사항 안내입니다. 이용해주셔서 감사합니다</a></th>
                      <td>2017.06.15</td>
                      <td>0</td>
                  </tr>
                  <tr>
                      <td>4</td>
                      <th><a href="#!">공지사항 안내입니다. 이용해주셔서 감사합니다</a></th>
                      <td>2017.06.15</td>
                      <td>0</td>
                  </tr>
                  <tr>
                      <td>3</td>
                      <th>
                        <a href="#!">공지사항 안내입니다. 이용해주셔서 감사합니다</a>
                        <p>테스트</p>
                      </th>
                      <td>2017.07.13</td>
                      <td>0</td>
                  </tr>
  
                  <tr>
                      <td>2</td>
                      <th><a href="#!">공지사항 안내입니다. 이용해주셔서 감사합니다</a></th>
                      <td>2017.06.15</td>
                      <td>0</td>
                  </tr>
  
                  <tr>
                      <td>1</td>
                      <th><a href="#!">공지사항 안내입니다. 이용해주셔서 감사합니다</a></th>
                      <td>2017.06.15</td>
                      <td>0</td>
                  </tr>
                  </tbody>
              </table>
          </div>
      </div>


      <!-- board seach area -->
      <div id="boardSearch">
        <div>
            <div class="searchWindow">
                <form action="">
                    <div class="searchWrap">
                        <label for="search" class="blind">공지사항 내용 검색</label>
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
	<script>
	</script>
	</body>
</html>