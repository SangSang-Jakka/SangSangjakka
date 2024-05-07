<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
	<%@include file="/WEB-INF/views/template/asset.jsp"%>
	<link rel="stylesheet" href="/sangsangjakka/resources/css/board/notice/notice_view.css">
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

     
      
    <!-- board list area -->
      <div>

              <table class="boardTitle">
                <tbody>
                    <tr>
                        <td scope="col" class="tdTitle">${dto.noticeTitle}</td>
                        <td scope="col" class="tdDate">${dto.noticeRegdate}</td>
                        <td scope="col" class="tdViews">${dto.noticeCnt}</td>
                    </tr>
                </tbody>
                  </table>

                  <div class="file">
                    @ 파일이 없습니다
                  </div>

                      <div class="gonjiMain">
                        ${dto.noticeContents}
                      </div>


                      <div class="btnTable">
                        <input type="button" class="btnList" value="목록">
                      </div>

                      <div class="listWrap">
                        <table class="listTable">
                            <tbody class="listBox">
                              <tr class="preBox">
                                <th scope="col" class="listOrder">이전글</th>
                                <th scope="col" class="listTitle">
                                  <a href="">고객 블랙리스트</a>
                                </th>
                                <th scope="col" class="listDate">2024-01-04</th>
                                <th scope="col" class="listViews">50</th>
                              </tr>
                              <tr>
                                <th scope="col" class="listOrder">다음글</th>
                                <th scope="col" class="listTitle">
                                  <a href="">동화 제작 오류 수정 안내</a>
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
