<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<%@include file="/WEB-INF/views/dashboard/dashboard_template/asset.jsp"%>

<link rel="stylesheet" type="text/css"
	href="/sangsangjakka/resources/vendors/styles/core.css">
<link rel="stylesheet" type="text/css"
	href="/sangsangjakka/resources/vendors/styles/icon-font.min.css">
<link rel="stylesheet" type="text/css"
	href="/sangsangjakka/resources/vendors/styles/style.css">
<link rel="stylesheet" type="text/css"
	href="/sangsangjakka/resources/vendors/styles/boardView.css">


<style>
.center {
	text-align: center;
}

/* 모달 */
.modal {
	display: none;
	position: fixed;
	z-index: 1000;
	left: 0;
	top: 0;
	width: 100%;
	height: 100%;
	overflow: auto;
	background-color: rgba(0, 0, 0, 0.4);
}

/* 모달 내부 */
.modal-content {
	background-color: #fefefe;
	margin: 10% auto;
	padding: 20px;
	border: 1px solid #888;
	width: 35%;
	box-shadow: 0 4px 8px 0 rgba(0, 0, 0, 0.2);
	border-radius: 10px;
}

/* 모달 닫기 버튼 */
.close {
	color: #aaa;
	float: right;
	font-size: 30px;
	font-weight: bold;
}

.close:hover, .close:focus {
	color: black;
	text-decoration: none;
	cursor: pointer;
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
								<h4>개인 용량 관리</h4>
							</div>
							<nav aria-label="breadcrumb" role="navigation">
								<ol class="breadcrumb">
									<li class="breadcrumb-item"><a href="index.html">Home</a></li>
									<li class="breadcrumb-item active" aria-current="page">개인
										용량 관리</li>
								</ol>
							</nav>
						</div>
					</div>
				</div>



				<!-- 게시글 상세보기 -->
				<div class="row">
					<div class="viewContainer">
						<h2>개인 용량 관리</h2>

						<!-- 버튼과 모달 템플릿 -->
						<span class="right">
							<button type="button" class="btn btn-primary"
								onclick="openModal('${dto.userSeq}')">용량 변경</button>
						</span>

						<!-- 모달 -->
						<div id="myModal" class="modal">
							<div class="modal-content">
								<span class="close" onclick="closeModal()">&times;</span>
								<form id="capacityForm" method="POST"
									action="/sangsangjakka/admin/dashboard/book/capacityview.do">
									<input type="hidden" name="userSeq" id="userSeqInput" value="">
									<label for="newCapacity">새로운 용량 입력:</label> <input
										type="number" id="newCapacity" name="newCapacity" required>
									<button type="submit" class="btn btn-primary">확인</button>
								</form>
								<!--  결과  -->
								<div id="resultMessage"></div>
							</div>
						</div>


						<table>
							<tr>
								<th>사용자</th>
								<td>${dto.userId}</td>
								<th>동화책수</th>
								<td>${dto.numBooks}</td>
							</tr>
							<tr>
								<th>남은 용량</th>
								<!--  대략 250MB 로 계산  -->
								<td>${dto.limitStorage - (dto.numBooks * 262144000)}</td>
								<th>전체 용량</th>
								<td>${dto.limitStorage}</td>
							</tr>

						</table>

						<table class="table table-striped">
							<thead>
								<tr>
									<th scope="col">번호</th>
									<th scope="col">동화책명</th>
									<th scope="col">등록일</th>
									<th scope="col">사용용량</th>
								</tr>
							</thead>
							<tbody>
								<c:forEach var="capacity" items="${capacityList}"
									varStatus="status">
									<tr class="center">
										<th>${status.count}</th>
										<td>${capacity.bookTitle}</td>
										<td>${capacity.bookRegdate}</td>
										<td>250MB</td>
									</tr>
								</c:forEach>
							</tbody>
						</table>


						<span class="right"> <input type="button" value="목록"
							class="btn btn-primary pull-right"
							onclick="location='/sangsangjakka/admin/dashboard/book/capacity.do'">
						</span>

					</div>
				</div>
			</div>
			<!-- 푸터 -->
			<%@include
				file="/WEB-INF/views/dashboard/dashboard_template/footer.jsp"%>

		</div>
	</div>

	<script>
		function openModal(userSeq) {
			document.getElementById('userSeqInput').value = userSeq;
			document.getElementById('myModal').style.display = 'block';
			// 모달이 열릴 때 결과 메시지 영역을 초기화
			document.getElementById('resultMessage').innerText = "";
		}

		function closeModal() {
			document.getElementById('myModal').style.display = 'none';

		}

		// 서블릿에서 받은 결과 메시지를 모달 내에 표시
		function displayResultMessage(message) {
			document.getElementById('resultMessage').innerText = message;
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
	<script
		src="/sangsangjakka/resources/vendors/scripts/datatable-setting-ver2.js"></script>

	<script
		src="https://cdnjs.cloudflare.com/ajax/libs/moment.js/2.29.1/moment.min.js"></script>
	<script>
		
	</script>
</body>
</html>