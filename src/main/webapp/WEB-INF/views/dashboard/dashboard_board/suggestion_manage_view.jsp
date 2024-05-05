<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
	<%@include file="/WEB-INF/views/dashboard/dashboard_template/asset.jsp"%>

	<link rel="stylesheet" type="text/css" href="/sangsangjakka/resources/vendors/styles/core.css">
	<link rel="stylesheet" type="text/css" href="/sangsangjakka/resources/vendors/styles/icon-font.min.css">
	<link rel="stylesheet" type="text/css" href="/sangsangjakka/resources/vendors/styles/style.css">
	<link rel="stylesheet" type="text/css" href="/sangsangjakka/resources/vendors/styles/boardView.css">
	
	
	<style>
	
	
	</style>
	</head>
	<body>
		
		<!-- 헤더 -->
		<%@include file="/WEB-INF/views/dashboard/dashboard_template/header.jsp"%>
	
	    <!-- 왼쪽 사이드바 -->
		<%@include file="/WEB-INF/views/dashboard/dashboard_template/left_sidebar.jsp"%>
	
		<!-- 컨텐츠 -->
		<div class="main-container">
	       <div class="pd-ltr-20 xs-pd-20-10">
	           <div class="min-height-200px">
	               <div class="page-header">
	                   <div class="row">
	                       <div class="col-md-12 col-sm-12">
	                           <div class="title">
	                               <h4>건의사항</h4>
	                           </div>
	                           <nav aria-label="breadcrumb" role="navigation">
	                               <ol class="breadcrumb">
	                                   <li class="breadcrumb-item"><a href="index.html">Home</a></li>
	                                   <li class="breadcrumb-item active" aria-current="page">건의사항 게시판</li>
	                               </ol>
	                           </nav>
	                       </div>
	                   </div>
	               </div>
	
	
	    
				<!-- 게시글 상세보기 -->
				<div class="row">
                    <div class="viewContainer">
                        <h2>게시글 상세</h2>

                        <span class="right">
                        <input type="button" value="답변" class="btn btn-primary">
                        <input type="button" value="삭제" class="btn btn-primary">
                        </span>
                
              <table>
                        <tr>
                            <th>제목</th>
                            <td>${dto.sgstTitle}</td>
                            <th>조회수</th>
                            <td>${dto.sgstCnt}</td>
                        </tr>
                        <tr>
                            <th>작성자</th>
                            <td>${dto.userSeq}</td>
                            <th>작성일</th>
                            <td>${dto.sgstRegdate}</td>
                        </tr>
                        <tr>
                            <th>내용</th>
                            <td colspan="3">
                                <textarea>${dto.sgstContents}</textarea>
                              
                            </td>
                        </tr>
                        <tr>
                            <th>첨부파일</th>
                            <td colspan="3">
                                
                            </td>
                        </tr>
                        </table>

                        <!-- 댓글 컨테이너 -->
                        <div class="commentContainer">
                        <div class="commentItem">
                            <div>
                                <div class="commentWriter">작성자 닉네임</div>
                                <div class="commentTime">2024-04-30</div>
                            </div>
                            <div class="commentContent">댓글내용댓글내용댓글내용댓글내용댓글내용댓글내용댓글내용댓글내용댓글내용댓글내용댓글내용....</div>
                        </div>
                        
                        <!-- 댓글 입력 폼 -->
                        <div class="commentInput">
                            <input type="text" placeholder="댓글을 입력하세요.">
                            <input type="button" class="btn btn-primary" value="작성">
                        </div>
                    </div>

                        <span class="left">
                            <input type="button" value="이전" class="btn btn-primary">
                            <input type="button" value="다음" class="btn btn-primary">
                        </span>

                        <span class="right">
                            <input type="button" value="목록" class="btn btn-primary pull-right">
                        </span>
                    
                </div>
            </div>
	
			</div>
			<!-- 푸터 -->
			<%@include file="/WEB-INF/views/dashboard/dashboard_template/footer.jsp"%>
			
		</div>
	</div>

	<!-- js -->
	<script src="https://code.jquery.com/jquery-3.7.1.js"></script>
	<%@include file="/WEB-INF/views/dashboard/dashboard_template/javascript.jsp"%>
	<script src="/sangsangjakka/resources/plugins/datatables/js/jquery.dataTables.min.js"></script>
	<script src="/sangsangjakka/resources/plugins/datatables/js/dataTables.bootstrap4.min.js"></script>
	<script src="/sangsangjakka/resources/plugins/datatables/js/dataTables.responsive.min.js"></script>
	<script src="/sangsangjakka/resources/plugins/datatables/js/responsive.bootstrap4.min.js"></script>
	
	<!-- buttons for Export datatable -->
	<script src="/sangsangjakka/resources/plugins/datatables/js/dataTables.buttons.min.js"></script>
	<script src="/sangsangjakka/resources/plugins/datatables/js/buttons.bootstrap4.min.js"></script>
	<script src="/sangsangjakka/resources/plugins/datatables/js/buttons.print.min.js"></script>
	<script src="/sangsangjakka/resources/plugins/datatables/js/buttons.html5.min.js"></script>
	<script src="/sangsangjakka/resources/plugins/datatables/js/buttons.flash.min.js"></script>
	<script src="/sangsangjakka/resources/plugins/datatables/js/pdfmake.min.js"></script>
	<script src="/sangsangjakka/resources/plugins/datatables/js/vfs_fonts.js"></script>
	
	<!-- Datatable Setting js -->
	<script src="/sangsangjakka/resources/vendors/scripts/datatable-setting-ver2.js"></script>

	<script src="https://cdnjs.cloudflare.com/ajax/libs/moment.js/2.29.1/moment.min.js"></script>
	<script>
	</script>
	</body>
</html>