<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
	<%@include file="/WEB-INF/views/dashboard/dashboard_template/asset.jsp"%>

	<link rel="stylesheet" type="text/css" href="/sangsangjakka/resources/vendors/styles/core.css">
	<link rel="stylesheet" type="text/css" href="/sangsangjakka/resources/vendors/styles/icon-font.min.css">
	<link rel="stylesheet" type="text/css" href="/sangsangjakka/resources/vendors/styles/style.css">
	<link rel="stylesheet" type="text/css" href="/sangsangjakka/resources/vendors/styles/noticeAdd.css">
	
	
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
	                                   <li class="breadcrumb-item active" aria-current="page">공지사항 작성</li>
	                               </ol>
	                           </nav>
	                       </div>
	                   </div>
	               </div>
	
	
	    
				<!-- 작성 -->
                <form method="POST" action="/sangsangjakka/admin/dashboard/notice/manageadd.do">
				 <div class="row">
                    <div class="writeContainer">
                        <h2>공지사항 작성</h2>
                        <table>
                            <tr>
                                <th>제목</th>
                                <td><input type="text" name="noticeTitle" class="subjectNotice"></td>
                            </tr>
                            <tr>
                                <th>내용</th>
                                <td><textarea name="noticeContents" class="contentNotice"></textarea></td>
                            </tr>
                            <tr>
                            <!--  
                                <th>파일</th>
                                <td><input type="file" name="attach" class="attachNotice"></td>
                                -->
                            </tr>
                        </table>
                    </div>
                </div>

                <div class="btnBox">
                    <button type="button" class="btn btn-primary" onclick="location.href='/sangsangjakka/admin/dashboard/notice/manage.do'">
                        <i class="fa-solid fa-rotate-left"></i> 취소
                    </button>
                    <button type="submit" class="btn btn-primary">
                        <i class="fa-solid fa-pencil"></i> 작성
                    </button>
                </div>
	
			</div>
			
                </form>
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
	

	<script src="https://kit.fontawesome.com/8a0ce49d35.js" crossorigin="anonymous"></script>
	<script>
	</script>
	</body>
</html>