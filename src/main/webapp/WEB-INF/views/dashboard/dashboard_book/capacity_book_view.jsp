<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
	<%@include file="/WEB-INF/views/dashboard/dashboard_template/asset.jsp"%>
	
	<link rel="stylesheet" type="text/css" href="/sangsangjakka/resources/plugins/datatables/css/dataTables.bootstrap4.min.css">
	<link rel="stylesheet" type="text/css" href="/sangsangjakka/resources/plugins/datatables/css/responsive.bootstrap4.min.css">
	<link rel="stylesheet" type="text/css" href="/sangsangjakka/resources/vendors/styles/suggestions.css">
	<link rel="stylesheet" type="text/css" href="/sangsangjakka/resources/vendors/styles/boardStatistics.css">
	
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
	
	
	              
	
	
	
	               
				<!-- Simple Datatable start -->
				<div class="pd-20 card-box mb-30">
					<div class="clearfix">
						<h4 class="text-blue h4">사용자 용량 </h4>
						
					</div>
					<div class="wizard-content">
						<form class="tab-wizard wizard-circle wizard">
							
							<section>
								<div class="row">
									<div class="col-md-6">
										<div class="form-group">
											<label >사용자</label>
											<input type="text" class="form-control">
										</div>
									</div>
									<div class="col-md-3">
										<div class="form-group">
											<label >사용용량</label>
											<input type="text" class="form-control" readonly>
										</div>
									</div>
                                    <div class="col-md-3">
										<div class="form-group">
											<label >전체용량</label>
											<input type="number" class="form-control">
										</div>
									</div>
								</div>
								<div class="pd-20 card-box mb-30">
                                    <div class="clearfix mb-20">
                                        
                                       
                                    </div>
                                    <table class="table table-striped">
                                        <p>동화책 목록</p>
                                        <thead>
                                            <tr>
                                                <th scope="col">번호</th>
                                                <th scope="col">동화책명</th>
                                                <th scope="col">용량</th>
                                                <th scope="col">관리</th>
                                               
                                            </tr>
                                        </thead>
                                        <tbody>
                                            <tr>
                                                <th scope="row">1</th>
                                                <td>신데렐라</td>
                                                <td>150MB</td>
                                                <td></td>
                                                
                                            </tr>
                                            <tr>
                                                <th scope="row">2</th>
                                                <td>피노키오</td>
                                                <td>150MB</td>
                                                <td></td>
                                                
                                            </tr>
                                            <tr>
                                                <th scope="row">3</th>
                                                <td>콩쥐팥쥐</td>
                                                <td>350MB</td>
                                                <td></td>
                                                
                                            </tr>
                                            <tr>
                                                <th scope="row">2</th>
                                                <td>알라딘</td>
                                                <td>150MB</td>
                                                <td></td>
                                                
                                            </tr>
                                            <tr>
                                                <th scope="row">3</th>
                                                <td>라푼젤</td>
                                                <td>450MB</td>
                                                <td></td>
                                                
                                            </tr>
                                        </tbody>
                                    </table>

							
							
						</form>
					</div>
				</div>
                <button type="button" class="btn btn-primary">목록으로</button>
				<button type="button" class="btn btn-primary pull-right" >확인</button>
			<!-- Simple Datatable End -->
	
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