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
	                               <h4>공지사항</h4>
	                           </div>
	                           <nav aria-label="breadcrumb" role="navigation">
	                               <ol class="breadcrumb">
	                                   <li class="breadcrumb-item"><a href="index.html">Home</a></li>
	                                   <li class="breadcrumb-item active" aria-current="page">공지사항</li>
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
                        <button type="button" class="btn btn-primary" onclick="location.href='/sangsangjakka/admin/dashboard/notice/manageedit.do?seq=${dto.noticeSeq}';">수정</button>
                        <input type="button" value="삭제" class="btn btn-primary">
                        </span>
                
                        <table>
                        <tr>
                            <th>제목</th>
                            <td>${dto.noticeTitle}</td>
                            <th>조회수</th>
                            <td>${dto.noticeCnt}</td>
                        </tr>
                        <tr>
                            <th>작성자</th>
                            <td>${dto.adId}</td>
                            <th>작성일</th>
                            <td>${dto.noticeRegdate}</td>
                        </tr>
                        <tr>
                            <th>내용</th>
                            <td colspan="3">
                                <textarea>${dto.noticeContents}</textarea>
                            </td>
                        </tr>
                        </table>

                        <span class="left">
                            <input type="button" value="이전" class="btn btn-primary">
                            <input type="button" value="다음" class="btn btn-primary">
                        </span>

                        <span class="right">
                        <button type="button" class="btn btn-primary" onclick="location.href='/sangsangjakka/admin/dashboard/notice/manage.do'">
                        목록
                    </button>
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