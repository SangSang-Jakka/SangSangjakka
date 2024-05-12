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
	                        <td scope="col" class="tdTitle">${dto.sgstTitle}</td>
	                        <td scope="col" class="tdDate">${dto.sgstRegdate}</td>
	                        <td scope="col" class="tdViews">${dto.sgstCnt}</td>
	                    </tr>
	                </tbody>
	                  </table>
	
	                  <div class="file">
	                    <i class="fa-regular fa-file"></i>
	                    파일이 없습니다
	                  </div>
	
	                      <div class="boardMain">
	                        <c:out value="${dto.sgstContents}" escapeXml="false"/>
	                      </div>
	
	                      
	                      
	                     
	                  
	                  <c:if test="${dto.userSeq == userSeq}">
	                  <div class="btnWrap">
	                        <input type="button" class="btnEdit" value="수정" onclick="location.href='/sangsangjakka/board/suggestion/edit.do?sgstseq=${dto.sgstSeq}';">
	                        <input type="button" class="btnDel" value="삭제">
	                  </div>
	              	  </c:if>
                  
                  


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



                      <div class="listWrap">
                        <table class="listTable">
                            <tbody class="listBox">
                            </tbody>
                        </table>
                      </div>
                   

                      <div class="btnListTable">
                        <input type="button" class="btnList" value="목록" onclick="location.href='/sangsangjakka/board/suggestion/list.do';">
                      </div>

     

  </section>
  
  <!-- footer -->
		<%@include file="/WEB-INF/views/template/footer.jsp"%>
		
		
  <script src="https://kit.fontawesome.com/8a0ce49d35.js" crossorigin="anonymous"></script>
  
	<script src="https://code.jquery.com/jquery-3.7.1.js"></script>
	<script>
	$(".btnDel").on("click", function() {
	    if(confirm("정말 삭제 하시겠습니까?") == true) {
	        // 정말 삭제하겠다고 했을 때, ajax 통신
	        $.ajax({
	            type: "POST",
	            url: "/sangsangjakka/board/suggestion/del.do",
	            data: {
	                sgstSeq: '${dto.sgstSeq}',
	                userSeq: '${dto.userSeq}'
	            },
	            dataType: "JSON",
	            beforeSend: function(xhr) {
	                xhr.setRequestHeader("AJAX", "true");
	            },
	            success: function(response) {
	                if (response.code === 0) {
	                    alert(response.message);
	                    location.href = "/sangsangjakka/board/suggestion/list.do";
	                } else {
	                    alert(response.message);
	                }
	            },
	            error: function(xhr, status, error) {
	                alert("AJAX 요청 중 오류가 발생했습니다: " + error);
	            }
	        });
	    }
	});
	</script>
</body>
</html>
