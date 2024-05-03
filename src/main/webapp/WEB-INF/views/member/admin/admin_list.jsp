<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
	<%@include file="/WEB-INF/views/dashboard/dashboard_template/asset.jsp"%>
	
	<link rel="stylesheet" type="text/css" href="/sangsangjakka/resources/plugins/datatables/css/dataTables.bootstrap4.min.css">
	<link rel="stylesheet" type="text/css" href="/sangsangjakka/resources/plugins/datatables/css/responsive.bootstrap4.min.css">
	<link rel="stylesheet" type="text/css" href="/sangsangjakka/resources/vendors/styles/suggestions.css">
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
	                                   <li class="breadcrumb-item active" aria-current="page">동화책 신고 관리</li>
	                               </ol>
	                           </nav>
	                       </div>
	                   </div>
	               </div>
	
	
	               <div class="row clearfix">
	                   <div class="col-xl-3 mb-30">
	                       <div class="card-box height-100-p widget-style1">
	                           <div class="d-flex flex-wrap align-items-center">
	                               <div class="widget-data">
	                                   <div class="h4 mb-0">새로운 동화책</div>
	                                   <div class="weight-600 font-14">Book</div>
	                               </div>
	                           </div>
	                       </div>
	                   </div>
	                   <div class="col-xl-3 mb-30">
	                       <div class="card-box height-100-p widget-style1">
	                           <div class="d-flex flex-wrap align-items-center">
	                               <div class="widget-data">
	                                   <div class="h4 mb-0">새로운 게시글</div>
	                                   <div class="weight-600 font-14">Post</div>
	                               </div>
	                           </div>
	                       </div>
	                   </div>
	                   <div class="col-xl-3 mb-30">
	                       <div class="card-box height-100-p widget-style1">
	                           <div class="d-flex flex-wrap align-items-center">
	                               <div class="widget-data">
	                                   <div class="h4 mb-0">새로운 유저</div>
	                                   <div class="weight-600 font-14">User</div>
	                               </div>
	                           </div>
	                       </div>
	                   </div>
	                   <div class="col-xl-3 mb-30">
	                       <div class="card-box height-100-p widget-style1">
	                           <div class="d-flex flex-wrap align-items-center">
	                               <div class="widget-data">
	                                   <div class="h4 mb-0">새로운 건의사항</div>
	                                   <div class="weight-600 font-14">Suggestion</div>
	                               </div>
	                           </div>
	                       </div>
	                   </div>
				</div>
	
	
				
				<!-- Simple Datatable start -->
				<!-- Simple Datatable start -->
				<div class="card-box mb-30">
					<div class="pd-20">
					
						<h4 class="text-blue h4">관리자 멤버</h4>
						
                        <!-- <input type="button" value="멤버 추가하기" class="btn btn-primary" onclick="location.href='/sangsangjakka/admin/add.do'"> -->
                        <span class="right">
						 <!-- <button type="button" class="btn btn-primary pull-right" onclick="location.href='/sangsangjakka/admin/add.do'">멤버 추가하기</button> -->
						 <input type="button" value="멤버 추가하기" class="btn btn-primary" onclick="location.href='/sangsangjakka/admin/add.do'">
						 </span>
					</div>
                    
					<div class="pb-20">
					 
						<table class="data-table table stripe hover nowrap" id="myTable">
						
							<thead>
								<tr>
									<th class="table-plus datatable-nosort">번호</th>
									<th>관리자명</th>
									<th>역할</th>
									
									<th>등록일</th>
									
								</tr>
							</thead>
						
							<tbody>
								<tr>
									<td class="table-plus">1</td>
									<td>
									
									
									<a href ="/sangsangjakka/admin/view.do">
									ghkdtl12</td>
									<td>관리자</td>
									
									<td>2024/03/01</td>
									
								</tr>
								<tr>
									<td class="table-plus">2</td>
									<td>tldnjs33</td>
									<td>관리자</td>
									
									<td>2024/03/28</td>
									
								</tr>
							</tbody>
					
						</table>
					</div>
				</div>
			<!-- Simple Datatable End -->
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