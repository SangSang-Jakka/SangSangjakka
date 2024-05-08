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
								<h4>자유게시판</h4>
							</div>
							<nav aria-label="breadcrumb" role="navigation">
								<ol class="breadcrumb">
									<li class="breadcrumb-item"><a href="index.html">Home</a></li>
									<li class="breadcrumb-item active" aria-current="page">자유게시판</li>
								</ol>
							</nav>
						</div>
					</div>
				</div>

				<!-- 게시글 상세보기 -->
				<div class="row">
					<div class="viewContainer">
						<h2>게시글 상세</h2>

						<span class="right"> 
						<button type="button" class="btn btn-primary" onclick="activationPage('${dto.boardSeq}')">공개</button>
						<button type="button" class="btn btn-primary" onclick="disablePage('${dto.boardSeq}')">비공개</button>
						</span>

						<table>
							<tr>
								<th>제목</th>
								<td id="postTitle">${dto.boardTitle}</td>
								<th>조회수</th>
								<td>${dto.boardCnt}</td>
							</tr>
							<tr>
								<th>작성자</th>
								<td>${dto.userNick}</td>
								<th>작성일</th>
								<td>${dto.boardRegdate}</td>
							</tr>
							<tr>
								<th>내용</th>
								<td colspan="3" id="postContent"><textarea>${dto.boardContents}</textarea></td>
							</tr>
						</table>

						<!-- 댓글 컨테이너 -->
						<div class="commentContainer">
							<c:forEach var="cmnt" items="${cmntList}">
								<div class="commentItem">
									<div>
										<div class="commentHeader">
											<div class="commentWriter">${cmnt.userNick}</div>
											<div class="commentActions">
												<button class="btnEdit" onclick="activation('${cmnt.cmntSeq}')">공개</button>
												<button class="btnDel" onclick="disableComment('${cmnt.cmntSeq}')">비공개</button>
											</div>
										</div>
										<div class="commentTime">${cmnt.cmntRegdate}</div>
										<div class="commentReport">신고수 : ${cmnt.cmntReportCnt}</div>
										<div class="commentContent">${cmnt.cmntContents}</div>
									</div>
									</div>
							</c:forEach>
						


						
				<span class="left"> <input type="button" value="이전"
							class="btn btn-primary"> <input type="button" value="다음"
							class="btn btn-primary">
						</span>
						<span class="right"> <input type="button" value="목록"
							class="btn btn-primary pull-right"
							onclick="location='/sangsangjakka/admin/dashboard/freeboard/manage.do'">
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
	
	// 게시물 활성화
	
	function activationPage(boardSeq) {
		
		
		var xhr = new XMLHttpRequest();
	    xhr.open('POST', '/sangsangjakka/admin/dashboard/freeboard/managedel.do', true);
	    xhr.setRequestHeader('Content-Type', 'application/x-www-form-urlencoded');
	    xhr.onreadystatechange = function() {
	        if (xhr.readyState === 4 && xhr.status === 200) {
	            // 서블릿으로부터 받은 응답 처리
	            alert(xhr.responseText);
	            // 페이지 리로드 또는 다른 작업 수행
	        }
	    };
	    // 서블릿으로 전달할 파라미터 설정
	    var params = 'boardSeq=' + encodeURIComponent(boardSeq) + '&action=activationPage';
	    xhr.send(params);
	
	}
	
	
	
	
	// 게시물 비활성화
	
	function disablePage(boardSeq) {
		
		var xhr = new XMLHttpRequest(); 
        xhr.open('POST', '/sangsangjakka/admin/dashboard/freeboard/managedel.do', true);
        xhr.setRequestHeader('Content-Type', 'application/x-www-form-urlencoded');
        xhr.onreadystatechange = function() {
            if (xhr.readyState === 4 && xhr.status === 200) {
                // 서블릿으로부터 받은 응답 처리
                alert(xhr.responseText);
                // 페이지 리로드 또는 다른 작업 수행
            }
        };
        
        var params = 'boardSeq=' + encodeURIComponent(boardSeq) + '&action=disablePage';
	    xhr.send(params);
		
	}

	
	
	// 댓글 활성화
	function activation(cmntSeq) {
		
		
		var xhr = new XMLHttpRequest();
	    xhr.open('POST', '/sangsangjakka/admin/dashboard/freeboard/manageview.do', true);
	    xhr.setRequestHeader('Content-Type', 'application/x-www-form-urlencoded');
	    xhr.onreadystatechange = function() {
	        if (xhr.readyState === 4 && xhr.status === 200) {
	            // 서블릿으로부터 받은 응답 처리
	            alert(xhr.responseText);
	            // 페이지 리로드 또는 다른 작업 수행
	        }
	    };
	    // 서블릿으로 전달할 파라미터 설정
	    var params = 'cmntSeq=' + encodeURIComponent(cmntSeq) + '&action=activation';
	    xhr.send(params);
	
		
	}
	
	// 댓글 비활성화
	
	 function disableComment(cmntSeq) {
	        var xhr = new XMLHttpRequest();
	        xhr.open('POST', '/sangsangjakka/admin/dashboard/freeboard/manageview.do', true);
	        xhr.setRequestHeader('Content-Type', 'application/x-www-form-urlencoded');
	        xhr.onreadystatechange = function() {
	            if (xhr.readyState === 4 && xhr.status === 200) {
	                // 서블릿으로부터 받은 응답 처리
	                alert(xhr.responseText);
	                // 페이지 리로드 또는 다른 작업 수행
	            }
	        };
	        
	        var params = 'cmntSeq=' + encodeURIComponent(cmntSeq) + '&action=disableComment';
		    xhr.send(params);
	
	    }
	
	
	
	
	
	/*
	function hidePost() {
	    var boardSeq = '${dto.boardSeq}'; 
	    var data = {
	        boardSeq: boardSeq
	    };

	    $.ajax({
	        type: 'POST',
	        url: '/sangsangjakka/admin/dashboard/freeboard/managedel.do',
	        data: data,
	        success: function(response) {
	            // 서버로부터의 응답을 받아 처리합니다.
	            if (response === 'success') {
	                // 제목과 내용을 변경합니다.
	                document.getElementById('postTitle').innerText = "비공개 처리되었습니다";
	                document.getElementById('postContent').innerText = "비공개 처리되었습니다";
	            } else {
	                alert("게시물 비공개 처리에 실패했습니다.");
	            }
	        },
	        error: function() {
	            alert("서버와의 통신 중 문제가 발생했습니다.");
	        }
	    });
	}
	*/
	
	
	
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