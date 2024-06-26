<%@page import="java.util.List"%>
<%@page import="com.jakka.model.dao.admin.AdminDAO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
	<%@include file="/WEB-INF/views/dashboard/dashboard_template/asset.jsp"%>
	<link rel="stylesheet" type="text/css" href="/sangsangjakka/resources/vendors/styles/dashboard.css">
	<link rel="stylesheet" type="text/css" href="/sangsangjakka/resources/plugins/datatables/css/dataTables.bootstrap4.min.css">
	<link rel="stylesheet" type="text/css" href="/sangsangjakka/resources/plugins/datatables/css/responsive.bootstrap4.min.css">
</head>


<body>
	
	<!-- 헤더 -->
	<!-- 헤더 -->
		<%@include file="/WEB-INF/views/dashboard/dashboard_template/header.jsp"%>
	
	    <!-- 왼쪽 사이드바 -->
		<%@include file="/WEB-INF/views/dashboard/dashboard_template/left_sidebar.jsp"%>
		
		<!-- 컨텐츠 시작 -->
	    <div class="main-container">
	            <div class="pd-ltr-20 xs-pd-20-10">
	                <div class="min-height-200px">
	                    <div class="page-header">
	                        <div class="row">
	                            <div class="col-md-12 col-sm-12">
	                                <div class="title">
	                                    <h4>게시판 통계</h4>
	                                </div>
	                                <nav aria-label="breadcrumb" role="navigation">
	                                    <ol class="breadcrumb">
	                                        <li class="breadcrumb-item"><a href="index.html">Home</a></li>
	                                        <li class="breadcrumb-item active" aria-current="page">게시판 통계</li>
	                                    </ol>
	                                </nav>
	                            </div>
	                        </div>
	                    </div>
	
	<div class="pd-ltr-20 xs-pd-20-10">
            <div class="min-height-200px">


		<!-- 배너 -->
        <%@include file="/WEB-INF/views/dashboard/dashboard_template/board_banner.jsp"%>

           <div class="card-box mb-30">
					<div class="pd-20">
						<h4 class="text-blue h4 center">자유 게시판</h4>
						<div class="filter-container">
						<!--  조건별 조회 -->
						
					</div>
					</div>
					<div class="pb-20">
						<table class="data-table table stripe hover nowrap" id="myTable">
							<thead>
								<tr>
									<th class="table-plus datatable-nosort">번호</th>
									<th>제목</th>
									<th>작성자</th>
									<th>작성일</th>
									<th>조회수</th>
									<th>신고수</th>
									<th class="datatable-nosort">Action</th>
								</tr>
							</thead>
						
							<tbody>
							<c:forEach var="board" items="${boardList}">
								<tr>
									<td class="table-plus">${board.boardSeq}</td>
									<td><a href="/sangsangjakka/admin/dashboard/freeboard/manageview.do?seq=${board.boardSeq}">${board.boardTitle}</a></td>
									<td>${board.userNick}</td>
									<td>${board.boardRegdate}</td>
									<td>${board.boardCnt}</td>
									<td>${board.boardReportCnt}</td>
									<td>
										<div class="dropdown">
											<a class="btn btn-link font-24 p-0 line-height-1 no-arrow dropdown-toggle" href="#" role="button" data-toggle="dropdown">
												<i class="dw dw-more"></i>
											</a>
											<div class="dropdown-menu dropdown-menu-right dropdown-menu-icon-list">
												<a class="dropdown-item" href="/sangsangjakka/admin/dashboard/freeboard/manageview.do?seq=${board.boardSeq}"><i class="dw dw-eye"></i> View</a>
												<a class="dropdown-item" href="#"><i class="dw dw-edit2"></i> Edit</a>
												<a class="dropdown-item" href="#"><i class="dw dw-delete-3"></i> Delete</a>
											</div>
										</div>
									</td>
								</tr>
								</c:forEach>
							</tbody>
					
						</table>
					</div>
				</div>
			<!-- Simple Datatable End -->
	
	
	<div class="card-box mb-30">
					<div class="pd-20">
						<h4 class="text-blue h4 center">건의사항 게시판</h4>
						<div class="filter-container">
						<!--  조건별 조회 -->
						
					</div>
					</div>
					<div class="pb-20">
						<table class="data-table table stripe hover nowrap" id="myTable">
							<thead>
								<tr>
									<th class="table-plus datatable-nosort">번호</th>
									<th>구분</th>
									<th>제목</th>
									<th>작성자</th>
									<th>작성일</th>
									<th>조회수</th>
									<th class="datatable-nosort">Action</th>
								</tr>
							</thead>
				
							<tbody>
							<c:forEach var="suggestion" items="${suggestionList}">
								<tr>
									<td class="table-plus">${suggestion.sgstSeq}</td>
									<td>${suggestion.sgstSecretYN eq 'y' ? '비밀' : '일반'}</td>
									<td><a href ="/sangsangjakka/admin/dashboard/suggestion/manageview.do?seq=${suggestion.sgstSeq}">${suggestion.sgstTitle}</a></td>
									<td>${suggestion.userNick}</td>
									<td>${suggestion.sgstRegdate}</td>
									<td>${suggestion.sgstCnt}</td>
									<td>
										<div class="dropdown">
											<a class="btn btn-link font-24 p-0 line-height-1 no-arrow dropdown-toggle" href="#" role="button" data-toggle="dropdown">
												<i class="dw dw-more"></i>
											</a>
											<div class="dropdown-menu dropdown-menu-right dropdown-menu-icon-list">
												<a class="dropdown-item" href="/sangsangjakka/admin/dashboard/suggestion/manageview.do?seq=${suggestion.sgstSeq}"><i class="dw dw-eye"></i> View</a>
												<a class="dropdown-item" href="#"><i class="dw dw-edit2"></i> Edit</a>
												<a class="dropdown-item" href="#"><i class="dw dw-delete-3"></i> Delete</a>
											</div>
										</div>
									</td>
								</tr>
								</c:forEach>
							</tbody>
					
						</table>
					</div>
				</div>
				
				<div class="card-box mb-30">
					<div class="pd-20">
						<h4 class="text-blue h4 center">댓글 관리</h4>
						<div class="filter-container">
						
					</div>
					</div>
					<div class="pb-20">
						<table class="data-table table stripe hover nowrap" id="myTable">
							<thead>
								<tr>
									<th class="table-plus datatable-nosort">번호</th>
									<th>작성자</th>
									<th>작성일</th>
									<th>댓글내용</th>
									<th>신고수</th>
									<th class="datatable-nosort">Action</th>
								</tr>
							</thead>
						
							<tbody>
							<c:forEach var="cmnt" items="${cmntList}">
								<tr>
									<td class="table-plus">${cmnt.cmntSeq}</td>
									<td>${cmnt.userNick}</td>
									<td>${cmnt.cmntRegdate}</td>
									<td>${cmnt.cmntContents}</td>
									<td>${cmnt.cmntReportCnt}</td>
									<td>
										<div class="dropdown">
											<a class="btn btn-link font-24 p-0 line-height-1 no-arrow dropdown-toggle" href="#" role="button" data-toggle="dropdown">
												<i class="dw dw-more"></i>
											</a>
											<div class="dropdown-menu dropdown-menu-right dropdown-menu-icon-list">
												<a class="dropdown-item" href="#"><i class="dw dw-eye"></i> View</a>
												<a class="dropdown-item" href="#"><i class="dw dw-edit2"></i> Edit</a>
												<a class="dropdown-item" href="#"><i class="dw dw-delete-3"></i> Delete</a>
											</div>
										</div>
									</td>
								</tr>
								</c:forEach>
							</tbody>
					
						</table>
					</div>
					
					
				</div>
	
	
			</div>
			<!-- 푸터 -->
			<%@include file="/WEB-INF/views/dashboard/dashboard_template/footer.jsp"%>
			
		</div>
	</div>

	
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
	<script src="/sangsangjakka/resources/vendors/scripts/board.js"></script>

	<script src="https://cdnjs.cloudflare.com/ajax/libs/moment.js/2.29.1/moment.min.js"></script>
	
</body>
</html>