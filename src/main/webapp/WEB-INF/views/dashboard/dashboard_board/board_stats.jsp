<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
	<%@include file="/WEB-INF/views/dashboard/dashboard_template/asset.jsp"%>
	<link rel="stylesheet" type="text/css" href="/sangsangjakka/resources/vendors/styles/dashboard.css">
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
						<select id="conditionSelect">
							<option value="all">전체</option>
							<option value="option">고정글</option>
						</select>
						<!--  기간 조회 -->
						<div class="date-range-container">
							<input type="date" id="min" name="min" class="date-input">
							<span class="date-separator">~</span>
							<input type="date" id="max" name="max" class="date-input">
						</div>
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
	
			</div>
			<!-- 푸터 -->
			<%@include file="/WEB-INF/views/dashboard/dashboard_template/footer.jsp"%>
			
		</div>
	</div>
-->
	<%@include file="/WEB-INF/views/dashboard/dashboard_template/javascript.jsp"%>
	
</body>
</html>