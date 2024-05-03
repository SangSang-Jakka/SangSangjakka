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
                        <h2>신고글 상세</h2>

                        <span class="right">
                        
                        <input type="button" value="확인" class="btn btn-primary">
                        </span>
                
                        <table>
                        <tr>
                            <th>동화책명</th>
                            <td>신데렐라</td>
                            <th>작성자</th>
                            <td>tldnjs12</td>
                        </tr>
                        <tr>
                            <th>누적횟수</th>
                            <td>5</td>
                            <th>작성일</th>
                            <td>2024/04/29</td>
                        </tr>
                        
                        </table>
                        
                        <table class="table table-striped">
                                        	
                                        <thead>
                                            <tr>
                                                <th scope="col">번호</th>
                                                <th scope="col">제목</th>
                                                <th scope="col">내용</th>
                                                <th scope="col">등록일자</th>
                                               
                                            </tr>
                                        </thead>
                                        <tbody>
                                            <tr>
                                                <th scope="row">1</th>
                                                <td>잔인해요</td>
                                                <td>연령대설정이 잘못된거 같아요 너무 잔인합니다.</td>
                                                <td>2024/05/01</td>
                                                
                                            </tr>
                                            <tr>
                                                <th scope="row">2</th>
                                                <td>표절</td>
                                                <td>내용이 이상해요</td>
                                                <td>2024/05/01</td>
                                                
                                            </tr>
                                            <tr>
                                                <th scope="row">3</th>
                                                <td>노잼</td>
                                                <td>삭제해주세요</td>
                                                <td>2024/05/01</td>
                                                
                                            </tr>
                                            <tr>
                                                <th scope="row">4</th>
                                                <td>노잼</td>
                                                <td>싫어요</td>
                                                <td>2024/05/01</td>
                                                
                                            </tr>
                                            
                                        </tbody>
                                    </table>

                        <!-- 댓글 컨테이너 -->
                       


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