<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
	<%@include file="/WEB-INF/views/template/asset.jsp"%>
	<link rel="stylesheet" href="/sangsangjakka/resources/css/board/freeboard/freeboard_add.css">
	<style>
	
	
	</style>
	</head>
	<body>
	
		<!-- header -->
		<%@include file="/WEB-INF/views/template/header.jsp"%>
		
		 <section class="freeBoard">
        <div class="pageTitle">
              <div>
                  <h3>자유 게시판
                    <small>쓰기</small>
                    </h3>
              </div>
          </div>


          <form>
            <table>
                <tr>
                    <th>제목</th>
                    <td><input type="text" name="subject" class="subject" required class="text"></td>
                </tr>
                <tr>
                    <th>내용</th>
                    <td><textarea name="content" class="content" required class="text"></textarea></td>
                </tr>

                <tr>
                    <th>파일</th>
                    <td><input type="file" name="attach"></td>
                </tr>
                
            </table>
            <div class="btnBox">
                <button class="btnBack" onclick="location.href='/toy/board/list.do';">
                    <i class="fa-solid fa-rotate-left"></i> 돌아가기
                </button>

                <button class="btnWrite" onclick="location.href='/toy/board/list.do';">
                    <i class="fa-solid fa-pencil"></i> 쓰기
                </button>
            </div>
            
            <input type="hidden" name="reply" value="${reply}">
            <input type="hidden" name="thread" value="${thread}">
            <input type="hidden" name="depth" value="${depth}">
            </form>
      
        


    </section>
    
  
  		<!-- footer -->
		<%@include file="/WEB-INF/views/template/footer.jsp"%>

	<script src="https://code.jquery.com/jquery-3.7.1.js"></script>
	<script src="https://kit.fontawesome.com/8a0ce49d35.js" crossorigin="anonymous"></script>
	<script>
	</script>
	</body>
</html>