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

/* 임시 */
.commentContainer {
	margin-top: 20px;
}

.commentItem {
	border-bottom: 1px solid #DEE2E6;
	padding: 10px;
	position: relative;
}

.commentHeader {
	display: flex;
	align-items: center;
}

.commentWriter {
	font-weight: bold;
}

.commentTime {
	color: #666;
	font-size: 12px;
	position: absolute;
	top: 10px;
	right: 10px;
}

.commentReport {
	color: #b81414;
	font-size: 12px;
	position: absolute;
	top: 30px;
	right: 10px;
}

.commentLike {
	color: #b81414;
	font-size: 12px;
	position: absolute;
	top: 30px;
	right: 80px;
}

.commentContent {
	margin-top: 5px;
}

.commentInput {
	margin-top: 20px;
	margin-bottom: 20px;
}

.commentInput input[type="text"] {
	width: calc(100% - 80px);
	height: 40px;
	padding: 8px;
	border: 1px solid #DEE2E6;
	border-radius: 5px 0 0 5px;
}

/* 댓글 수정, 삭제 */
.commentActions {
	display: flex;
	align-items: center;
	/* 여기부터 기존*/
	margin-left: 10px;
	display: flex;
}

.btnEdit, .btnDel {
	padding: 3px 8px;
	margin-left: 5px;
	cursor: pointer;
	border: none;
	border-radius: 3px;
	background-color: #007bff;
	color: #fff;
	font-size: 10px;
	margin-left: 5px;
}

.btnEdit:hover, .btnDel:hover {
	background-color: #0056b3;
}

.full {
	margin-top: 10px;
	width: 100%;
	height: 40px;
	padding: 8px;
	border: 1px solid #DEE2E6;
	border-radius: 5px;
	margin-right: 5px;
	margin-bottom: 5px;
}

.left {
	float: left;
	margin-top: 10px;
}

.right {
	float: right;
	margin-top: 10px;
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
								<h4>동화책 신고 관리</h4>
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



				<!-- 동화책 상세보기 -->
				<div class="row">
					<div class="viewContainer">
						<h2>신고 상세 내역</h2>

						<span class="right"> 
						<input type="button" value="공개" class="btn btn-primary" onclick="activationBook('${dto.bookSeq}')">
						<input type="button" value="비공개" class="btn btn-primary" onclick="disableBook('${dto.bookSeq}')">
						</span>
                
                        <table>
                        <tr>
                            <th>동화책명</th>
                            <td>${dto.bookTitle}</td>
                            <th>작성자</th>
                            <td>${dto.userNick}</td>
                       </tr>
                       <tr>
                            <th>등록일</th>
                            <td>${dto.bookRegdate}</td>
                             <th>신고수</th>
                            <td>${dto.bookReportCnt}</td>
                        </tr>
                     
                        
                        </table>
                        
                       <table class="table table-striped">
                                        
                                        <thead>
                                            <tr>
                                                <th scope="col">번호</th>
                                                <th scope="col">신고자</th>
                                                <th scope="col">신고일</th>
                                               
                                            </tr>
                                        </thead>
                                        <tbody>
                                        <c:forEach var="report" items="${reportList}" varStatus="status">
                                            <tr>
                                                <th scope="row">${status.count}</th>
                                                <td>${report.userNick}</td>
                                                <td>${report.reportDate }</td>
                                            </tr>
                                        </tbody>
                                        </c:forEach>
                                    </table>


							<span class="left"> <input type="button" value="이전"
								class="btn btn-primary"> <input type="button" value="다음"
								class="btn btn-primary">
							</span> <span class="right"> <input type="button" value="목록"
								class="btn btn-primary pull-right"
								onclick="location='/sangsangjakka/admin/dashboard/book/report.do'">
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
		
		// 동화책  활성화
		
		function activationBook(bookSeq) {
			
			
			var xhr = new XMLHttpRequest();
		    xhr.open('POST', '/sangsangjakka/admin/dashboard/bookshare/managedel.do', true);
		    xhr.setRequestHeader('Content-Type', 'application/x-www-form-urlencoded');
		    xhr.onreadystatechange = function() {
		        if (xhr.readyState === 4 && xhr.status === 200) {
		            // 서블릿으로부터 받은 응답 처리
		            alert(xhr.responseText);
		            // 페이지 리로드 또는 다른 작업 수행
		        }
		    };
		    // 서블릿으로 전달할 파라미터 설정
		    var params = 'bookSeq=' + encodeURIComponent(bookSeq) + '&action=activationBook';
		    xhr.send(params);
		
		}
		
		
		// 동화책 비활성화
		
		function disableBook(bookSeq) {
			
			var xhr = new XMLHttpRequest(); 
	        xhr.open('POST', '/sangsangjakka/admin/dashboard/bookshare/managedel.do', true);
	        xhr.setRequestHeader('Content-Type', 'application/x-www-form-urlencoded');
	        xhr.onreadystatechange = function() {
	            if (xhr.readyState === 4 && xhr.status === 200) {
	                // 서블릿으로부터 받은 응답 처리
	                alert(xhr.responseText);
	                // 페이지 리로드 또는 다른 작업 수행
	            }
	        };
	        
	        var params = 'bookSeq=' + encodeURIComponent(bookSeq) + '&action=disableBook';
		    xhr.send(params);
			
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


</body>
</html>