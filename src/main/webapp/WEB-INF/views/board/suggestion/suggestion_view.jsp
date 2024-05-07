<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
	<%@include file="/WEB-INF/views/template/asset.jsp"%>
<link rel="stylesheet" href="/sangsangjakka/resources/css/board/suggestion/suggestion_view.css">
<style>
	
</style>
</head>
<body>
	<!-- header -->
		<%@include file="/WEB-INF/views/template/header.jsp"%>
	
	<section class="notice">
    <div class="pageTitle">
          <div>
              <h3>건의 게시판</h3>
          </div>
      </div>

     
      
    <!-- board list area -->
	      <div>
				
	              <table class="boardTitle">
	                <tbody>
	                    <tr>
	                        <td scope="col" class="tdTitle">동화책 제작하는데 오류가 나요</td>
	                        <td scope="col" class="tdDate">2017.06.15</td>
	                        <td scope="col" class="tdViews">1</td>
	                    </tr>
	                </tbody>
	                  </table>
	
	                  <div class="file">
	                    <i class="fa-regular fa-file"></i>
	                    파일이 없습니다
	                  </div>
	
	                      <div class="boardMain">
	                        새로고침하면 날라가는데 고쳐주세요
	                      </div>
	
	                      
	                      
	                     
	                  
	                  
	                  <div class="btnWrap">
	                        <input type="button" class="btnEdit" value="수정" data-userseq="as">
	                        <input type="button" class="btnDel" value="삭제">
	                  </div>
                  
                  


                  <div class="commentWrap">
                        <!-- 전체 -->
                        <div class="commmentContainer">
                          <ul class="commentArea">
                            <li>
                              <div class="commentNick">관리자</div>
                                
                           
                              <div class="commentText">오류 해결했습니다</div>
                              <div class="commentInfo">
                                <span class="commentInfoDate">2024.05.03</span>
                              </div>
                            </li>
                            
                          </ul>
                        </div>
                      </div>
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
                                  <a href="">서버 관련</a>
                                </th>
                                <th scope="col" class="listDate">2024-01-04</th>
                                <th scope="col" class="listViews">50</th>
                              </tr>
                              <tr>
                                <th scope="col" class="listOrder">다음글</th>
                                <th scope="col" class="listTitle">
                                  <a href="">회원탈퇴 하고싶어요</a>
                                </th>
                                <th scope="col" class="listDate">2024-05-06</th>
                                <th scope="col" class="listViews">10</th>
                              </tr>
                            </tbody>

                        </table>
                      </div>
                   


     

  </section>
  
  <!-- footer -->
		<%@include file="/WEB-INF/views/template/footer.jsp"%>
		
		
  <script src="https://kit.fontawesome.com/8a0ce49d35.js" crossorigin="anonymous"></script>
  
	<script src="https://code.jquery.com/jquery-3.7.1.js"></script>
	<script>
                  var editContent = document.querySelector('.btnEdit');
                  editContent.addEventListener('click', function() {
                  var userId = <%=(String)session.getAttribute("userId")%>
                  var userSeq = this.getAttribute('data-userseq');
                  		if(userId != userSeq) {
                  			alert('수정 권한이 없습니다. 본인만 수정 가능합니다.');
                  		} else {
                  			location.href='/sangsangjakka/board/suggestion/edit.do';
                  		}
                  		location.href='/sangsangjakka/board/suggestion/edit.do?sgstSeq=' + 7;
                  });
    </script>
</body>
</html>
