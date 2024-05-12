<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<%@include file="/WEB-INF/views/dashboard/dashboard_template/asset.jsp"%>
<link rel="stylesheet" type="text/css"
	href="/sangsangjakka/resources/vendors/styles/dashboard.css">

<link rel="stylesheet" type="text/css"
	href="/sangsangjakka/resources/plugins/datatables/css/dataTables.bootstrap4.min.css">
<link rel="stylesheet" type="text/css"
	href="/sangsangjakka/resources/plugins/datatables/css/responsive.bootstrap4.min.css">
<!--   <link rel="stylesheet" type="text/css" href="/sangsangjakka/resources/vendors/styles/suggestions.css"> -->
<style>
.center {
	text-align: center;
	margin-top: 20px;
	font-size: 1.7em;
}

.buttonItem {
	float: right;
	margin-right: 10px;
	margin-top: 10px;
}

.footer {
	margin-top: 50px;
}

.filter-container {
	display: flex;
	justify-content: flex-end;
	margin-bottom: -10px;
}

/* 기간 조회 스타일 */
.date-range-container {
	display: inline-flex;
	align-items: center;
	margin-bottom: 20px;
}

.date-input {
	padding: 10px;
	font-size: 14px;
	border: 1px solid #ccc;
	border-radius: 3px;
	background-color: #fff;
	width: 150px;
	height: 38px;
	margin-right: 10px;
	transition: border-color 0.3s;
}

.date-separator {
	font-size: 16px;
	margin-right: 10px;
}

/* 조건 조회 스타일 */
#conditionSelect {
	padding: 10px;
	font-size: 14px;
	border: 1px solid #ccc;
	border-radius: 3px;
	background-color: #fff;
	width: auto;
	height: 38px;
	margin-right: 10px;
	transition: border-color 0.3s;
	margin-bottom: 10px;
}

#conditionSelect:focus {
	outline: none;
	border-color: #6c757d;
}

#conditionSelect option {
	background-color: #fff;
	color: #333;
	font-size: 14px;
}

#conditionSelect option:checked {
	background-color: #007bff;
	color: #fff;
}

/* 검색창 */
#myTable_filter input {
	margin-right: -5px;
}
</style>
</head>
<body>

	<!-- 헤더 -->
	<%@include
		file="/WEB-INF/views/dashboard/dashboard_template/header.jsp"%>

	<!-- 왼쪽 사이드바 -->
	<%@include
		file="/WEB-INF/views/dashboard/dashboard_template/left_sidebar.jsp"%>

	<!-- 컨텐츠 -->
	<div class="main-container">
		<div class="pd-ltr-20 xs-pd-20-10">
			<div class="min-height-200px">
				<div class="page-header">
					<div class="row">
						<div class="col-md-12 col-sm-12">
							<div class="title">
								<h4>관리자 계정 목록</h4>
							</div>
							<nav aria-label="breadcrumb" role="navigation">
								<ol class="breadcrumb">
									<li class="breadcrumb-item"><a href="index.html">Home</a></li>
									<li class="breadcrumb-item active" aria-current="page">관리자 계정 목록
								</li>
								</ol>
							</nav>
						</div>
					</div>
				</div>
				<div class="pd-ltr-20 xs-pd-20-10">
					<div class="min-height-200px">


						<!-- 배너 -->
						<%@include
							file="/WEB-INF/views/dashboard/dashboard_template/admin_dashboard_banner.jsp"%>







						<!-- Simple Datatable start -->
						<div class="card-box mb-30">
							<div class="pd-20">

								<h4 class="text-blue h4 center">관리자 멤버</h4>

								<!-- <input type="button" value="멤버 추가하기" class="btn btn-primary" onclick="location.href='/sangsangjakka/admin/add.do'"> -->
								<span class="right"> <!-- <button type="button" class="btn btn-primary pull-right" onclick="location.href='/sangsangjakka/admin/add.do'">멤버 추가하기</button> -->
									<input type="button" value="멤버 추가하기" class="btn btn-primary"
									onclick="location.href='/sangsangjakka/admin/add.do'">
								</span>

								<div class="filter-container">

									<!--  조건별 조회
							<select id="conditionSelect">
								<option value="all">전체</option>
								<option value="option">고정글</option>
							</select> -->
									<!--  기간 조회 -->
									
									<div class="date-range-container">
										<!--  <input type="date" id="min" name="min" class="date-input">
										<span class="date-separator">~</span> <input type="date"
											id="max" name="max" class="date-input"> -->
									</div>
									
								</div>
								<div class="pb-20">

									<table class="data-table table stripe hover nowrap"
										id="myTable">

										<thead>
											<tr>
												<th class="table-plus datatable-nosort">번호</th>
												<th>관리자명</th>
												<th>역할</th>
												<th>주소</th>
												<th>전화번호</th>

											</tr>
										</thead>

										<tbody>

											<c:forEach var="admin" items="${adminList}" varStatus="loop">

												<tr>
													<td class="table-plus">${loop.index +1}</td>




													<td>
														<!-- 									<a href ="/sangsangjakka/admin/view.do"> -->
														<a href="/sangsangjakka/admin/view.do?id=${admin.adId}">${admin.adName}</a>
													</td>
													<td>${admin.adNick}</td>

													<td>${admin.adAddress}</td>
													<td>${admin.adTel}</td>

												</tr>
											</c:forEach>
										</tbody>


									</table>
								</div>
							</div>
						</div>

						<!-- Simple Datatable End -->
						<!-- 푸터 -->
						<%@include
							file="/WEB-INF/views/dashboard/dashboard_template/footer.jsp"%>

					</div>
				</div>

				<!-- js -->
				<script src="https://code.jquery.com/jquery-3.7.1.js"></script>
				<%@include
					file="/WEB-INF/views/dashboard/dashboard_template/javascript.jsp"%>
				<script
					src="/sangsangjakka/resources/plugins/datatables/js/jquery.dataTables.min.js"></script>
				<script
					src="/sangsangjakka/resources/plugins/datatables/js/dataTables.bootstrap4.min.js"></script>
				<script
					src="/sangsangjakka/resources/plugins/datatables/js/dataTables.responsive.min.js"></script>
				<script
					src="/sangsangjakka/resources/plugins/datatables/js/responsive.bootstrap4.min.js"></script>

				<!-- buttons for Export datatable -->
				<script
					src="/sangsangjakka/resources/plugins/datatables/js/dataTables.buttons.min.js"></script>
				<script
					src="/sangsangjakka/resources/plugins/datatables/js/buttons.bootstrap4.min.js"></script>
				<script
					src="/sangsangjakka/resources/plugins/datatables/js/buttons.print.min.js"></script>
				<script
					src="/sangsangjakka/resources/plugins/datatables/js/buttons.html5.min.js"></script>
				<script
					src="/sangsangjakka/resources/plugins/datatables/js/buttons.flash.min.js"></script>
				<script
					src="/sangsangjakka/resources/plugins/datatables/js/pdfmake.min.js"></script>
				<script
					src="/sangsangjakka/resources/plugins/datatables/js/vfs_fonts.js"></script>

				<!-- Datatable Setting js -->
				<script src="/sangsangjakka/resources/vendors/scripts/test.js"></script>

				<script
					src="https://cdnjs.cloudflare.com/ajax/libs/moment.js/2.29.1/moment.min.js"></script>
				<script>
					
				</script>
</body>
</html>