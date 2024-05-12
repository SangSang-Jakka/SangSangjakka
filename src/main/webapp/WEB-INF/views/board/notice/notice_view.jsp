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


                     

                      <div class="listWrap">
                        <table class="listTable">
                            <tbody class="listBox">
                              <tr class="preBox">
                                <th scope="col" class="listOrder">이전글</th>
                                <th scope="col" class="listTitle">
                                 
                                  <c:if test="${not empty prev.noticeTitle}">
	                                  <a href="/sangsangjakka/board/notice/view.do?no=${prev.noticeSeq}">${prev.noticeTitle}</a>
     	                             <th scope="col" class="listDate">${prev.noticeRegdate}</th>
	                                 <th scope="col" class="listViews">${prev.noticeCnt}</th>
                                  </c:if>
                                  
                                  <c:if test="${empty prev}">
                                  	  <a href="#!">이전글이 없습니다.</a>
                                  	  <th scope="col" class="listDate"></th>
	                                  <th scope="col" class="listViews"></th>
                                  </c:if>
                                
                                </th>
                              </tr>
                              <tr>
                                <th scope="col" class="listOrder">다음글</th>
                                <th scope="col" class="listTitle">
                                
                                <c:if test="${not empty next.noticeTitle}">
                                  <a href="/sangsangjakka/board/notice/view.do?no=${next.noticeSeq}">${next.noticeTitle}</a>
	                                <th scope="col" class="listDate">${next.noticeRegdate}</th>
		                            <th scope="col" class="listViews">${next.noticeCnt}</th>
                                </c:if>
                                
                                <c:if test="${empty next}">
                                  <a href="#!">다음글이 없습니다.</a>
	                                <th scope="col" class="listDate"></th>
		                            <th scope="col" class="listViews"></th>
                                </c:if>
                                
                                </th>
                                
                              </tr>
                            </tbody>

                        </table>
                      </div>
                    </div>
                    
                     <div class="btnTable">
                        <input type="button" class="btnList" value="목록" onclick="location.href='/sangsangjakka/board/notice/list.do';">
                      </div>
                    
  </section>

	<!-- footer -->
		<%@include file="/WEB-INF/views/template/footer.jsp"%>
		
	<script src="https://code.jquery.com/jquery-3.7.1.js"></script>
	<script>
		
	</script>
</body>
</html>
