<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
	<%@include file="/WEB-INF/views/template/asset.jsp"%>
	<link rel="stylesheet" href="/sangsangjakka/resources/css/board/freeboard/freeboard_edit.css">
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

     
      
    <!-- board list area -->
      <div>

              <table class="boardTitle">
                <tbody>
                    <tr>
                        <td scope="col" class="tdTitle">날씨가 좋네요</td>
                        <td scope="col" class="tdDate">2017.06.15</td>
                        <td scope="col" class="tdViews">23</td>
                    </tr>
                </tbody>
                  </table>

                  <div class="file">
                    @ 파일이 없습니다
                  </div>

                      <div class="gonjiMain">
                        소풍 가기 딱 좋아요~~~
                      </div>

                      <div class="btnWrap">
                        <div class="btnContainer">
                            <input type="button" class="btnEdit" value="수정">
                            <input type="button" class="btnDel" value="삭제">
                        </div>
                            <input type="button" class="btnReview" value="답글">
                      </div>
            


                      <div class="btnListTable">
                        <input type="button" class="btnList" value="목록">
                      </div>

                      <div class="listWrap">
                        <table class="listTable">
                            <tbody class="listBox">
                              <tr class="preBox">
                                <th scope="col" class="listOrder">이전글</th>
                                <th scope="col" class="listTitle">
                                  <a href="">동화책 만드는 거 재밌네요</a>
                                </th>
                                <th scope="col" class="listDate">2024-01-04</th>
                                <th scope="col" class="listViews">50</th>
                              </tr>
                              <tr>
                                <th scope="col" class="listOrder">다음글</th>
                                <th scope="col" class="listTitle">
                                  <a href="">유치원 질문</a>
                                </th>
                                <th scope="col" class="listDate">2024-05-06</th>
                                <th scope="col" class="listViews">10</th>
                              </tr>
                            </tbody>

                        </table>
                      </div>
                    </div>
  </section>
		
		

  
  		<!-- footer -->
		<%@include file="/WEB-INF/views/template/footer.jsp"%>

	<script src="https://code.jquery.com/jquery-3.7.1.js"></script>
	<script>
	</script>
	</body>
</html>