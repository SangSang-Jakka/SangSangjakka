<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<%@include file="/WEB-INF/views/dashboard/dashboard_template/asset.jsp"%>

<link rel="stylesheet" type="text/css"
	href="/sangsangjakka/resources/plugins/datatables/css/dataTables.bootstrap4.min.css">
<link rel="stylesheet" type="text/css"
	href="/sangsangjakka/resources/plugins/datatables/css/responsive.bootstrap4.min.css">
<!--   <link rel="stylesheet" type="text/css"
	href="/sangsangjakka/resources/vendors/styles/suggestions.css"> -->


<style>
.center {
	text-align: center;
	margin-top: 20px;
	font-size: 1.7em;
}

.buttonItem {
	float: right;
	margin-right: 10px;
	margin-top: -20px;
}

.left {
	margin-right: 10px;
	margin-top: -20px;
}

.footer {
	margin-top: 50px;
}

.filter-container {
	display: flex;
	justify-content: flex-end;
	margin-bottom: -30px;
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
	margin-right: 14px;
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
								<h4>동화책 수상 관리</h4>
							</div>
							<nav aria-label="breadcrumb" role="navigation">
								<ol class="breadcrumb">
									<li class="breadcrumb-item"><a href="index.html">Home</a></li>
									<li class="breadcrumb-item active" aria-current="page">동화책
										수상 관리</li>
								</ol>
							</nav>
						</div>
					</div>
				</div>


				<!-- 배너 -->
				<%-- 				<%@include --%>
				<%-- 					file="/WEB-INF/views/dashboard/dashboard_template/banner.jsp"%> --%>





				<!-- Simple Datatable start -->
				<div class="card-box mb-30">
					<div class="pd-20">
						<h4 class="text-blue h4 center">동화책 리스트</h4>
						<div class="filter-container">
							<!--  조건별 조회
							<select id="conditionSelect">
								<option value="all">전체</option>
								<option value="option">조건</option>
							</select>  -->
							<!--  기간 조회 -->
							<div class="date-range-container">
								<input type="date" id="min" name="min" class="date-input">
								<span class="date-separator">~</span> <input type="date"
									id="max" name="max" class="date-input">
							</div>
						</div>
					</div>
					<div class="pb-20">
						<table class="data-table table stripe hover nowrap" id="myTable">
							<thead>
								<tr>
									<th class="table-plus">번호</th>
									<th>동화책명</th>
									<th>작성자</th>
									<th>등록일자</th>
									<th>조회수</th>
									<th>좋아요수</th>
									<th>저장수</th>
									<th>소감수</th>
									<th>총점</th>
									<th class="datatable-nosort">선택</th>
								</tr>
							</thead>
							<tbody>
								<c:forEach var="book" items="${bookList }">
									<tr>
										<td>${book.bookSeq}</td>
										<td>${book.bookTitle}</td>
										<td>${book.userNick}</td>
										<td>${book.bookRegdate}</td>
										<td>${book.bookCnt}</td>
										<td>${book.likeCnt}</td>
										<td>${book.bookScrapCnt}</td>
										<td>${book.bookReviewCnt}</td>
										<td>${book.likeCnt+book.bookScrapCnt+book.bookReviewCnt}</td>

										<td>
											<div class="bookSelection">
												<input type="checkbox" name="selectedBooks"
													value="${book.bookSeq}"> <select
													name="selectedRanks">
													<option value="1">1등</option>
													<option value="2">2등</option>
													<option value="3">3등</option>
													<option value="4">4등</option>
													<option value="5">5등</option>
												</select>
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

			<button type="button" class="btn btn-primary left"
				onclick="location='/sangsangjakka/admin/dashboard/book/award.do'">목록</button>
			<span class="buttonItem">
				<button type="button" class="btn btn-primary"
					onclick="registerSelectedBooks()">수상작 등록</button>
			</span>
			<!-- 푸터 -->
			<%@include
				file="/WEB-INF/views/dashboard/dashboard_template/footer.jsp"%>
		</div>
	</div>


	<script>
		function registerSelectedBooks() {
			var selectedBooks = [];
			var selectedRanks = [];

			// 모든 체크박스 요소에 대해 반복
			var checkboxes = document.getElementsByName('selectedBooks');
			checkboxes.forEach(function(checkbox) {

				if (checkbox.checked) {
					// 체크된 도서의 값(도서 번호)과 수상 순위를 배열에 추가
					selectedBooks.push(checkbox.value);
					var rank = checkbox.closest('tr').querySelector(
							'select[name="selectedRanks"]');
					selectedRanks.push(rank.value);
				}
			});

			// JSON 데이터 생성
			var jsonData = {
				selectedBooks : selectedBooks,
				selectedRanks : selectedRanks

			};

			var xhr = new XMLHttpRequest();
			xhr.open('POST',
					'/sangsangjakka/admin/dashboard/book/awardview.do', true);
			xhr.setRequestHeader('Content-Type', 'application/json');
			xhr.onreadystatechange = function() {
				if (xhr.readyState === XMLHttpRequest.DONE) {
					if (xhr.status === 200) {
						// 성공적으로 처리된 경우
						alert('수상작이 성공적으로 등록되었습니다.');
					} else {

						alert('등록 중 오류가 발생했습니다. 다시 시도해주세요.');

					}

				}

			};
			// JSON 데이터 전송
			xhr.send(JSON.stringify(jsonData));

		}
	</script>

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

</body>
</html>