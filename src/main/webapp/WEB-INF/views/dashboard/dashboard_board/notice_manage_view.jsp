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
.topright {
	float: right;
	margin-bottom: 20px;
}

.topleft {
	float: left;
	margin-bottom: 20px;
}

.fixbtn {
	background-color: red;
	padding-left: 35px;
	font-size: 16px;
}

.topleft .btn {
	background-color: #ff0000;
	border-color: #ff0000;
	color: white;
}

/* 마우스를 올리고 있는 동안 */
.topleft .btn:hover {
	background-color: #cc0000;
	border-color: #cc0000;
	color: white;
}

/* 마우스를 클릭하고 있는 동안 */
.topleft .btn:active {
	background-color: #cc0000;
	border-color: #cc0000;
	color: white;
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
						<h2>게시글 상세</h2>


						<span class="topleft">
							<button type="button" class="btn btn-primary"
								onclick="activationFix('${dto.noticeSeq}')">고정</button>
							<button type="button" class="btn btn-primary"
								onclick="unFix('${dto.noticeSeq}')">고정 해제</button>
						</span> <span class="topright"> <c:if test="${not empty adId}">
								<button type="button" class="btn btn-primary"
									onclick="location.href='/sangsangjakka/admin/dashboard/notice/manageedit.do?seq=${dto.noticeSeq}';">수정</button>
								<button type="button" class="btn btn-primary"
									onclick="deleteNotice(${dto.noticeSeq}, '${dto.adId}');">삭제</button>
							</c:if>
						</span>

						<table>
							<tr>
								<th>제목</th>
								<td>${dto.noticeTitle}</td>
								<th>조회수</th>
								<td>${dto.noticeCnt}</td>
							</tr>
							<tr>
								<th>작성자</th>
								<td>${dto.adId}</td>
								<th>작성일</th>
								<td>${dto.noticeRegdate}</td>
							</tr>
							<tr>
								<th>내용</th>
								<td colspan="3"><textarea>${dto.noticeContents}</textarea>
								</td>
							</tr>
						</table>

						<span class="left"> <a
							href="/sangsangjakka/admin/dashboard/notice/manageview.do?seq=${dto.noticeSeq - 1}"
							class="btn btn-primary">이전</a> <a
							href="/sangsangjakka/admin/dashboard/notice/manageview.do?seq=${dto.noticeSeq + 1}"
							class="btn btn-primary">다음</a>
						</span> <span class="right">
							<button type="button" class="btn btn-primary"
								onclick="location.href='/sangsangjakka/admin/dashboard/notice/manage.do'">
								목록</button>
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
	
	// 고정
	function activationFix(noticeSeq) {
		
		
		var xhr = new XMLHttpRequest();
	    xhr.open('POST', '/sangsangjakka/admin/dashboard/notice/manageview.do', true);
	    xhr.setRequestHeader('Content-Type', 'application/x-www-form-urlencoded');
	    xhr.onreadystatechange = function() {
	        if (xhr.readyState === 4 && xhr.status === 200) {
	            // 서블릿으로부터 받은 응답 처리
	            alert(xhr.responseText);
	            // 페이지 리로드 또는 다른 작업 수행
	        }
	    };
	    // 서블릿으로 전달할 파라미터 설정
	    var params = 'noticeSeq=' + encodeURIComponent(noticeSeq) + '&action=activationFix';
	    xhr.send(params);
	
		
	}
	
	
	// 고정 해제
	function unFix(noticeSeq) {
		
		
		var xhr = new XMLHttpRequest();
	    xhr.open('POST', '/sangsangjakka/admin/dashboard/notice/manageview.do', true);
	    xhr.setRequestHeader('Content-Type', 'application/x-www-form-urlencoded');
	    xhr.onreadystatechange = function() {
	        if (xhr.readyState === 4 && xhr.status === 200) {
	            // 서블릿으로부터 받은 응답 처리
	            alert(xhr.responseText);
	            // 페이지 리로드 또는 다른 작업 수행
	        }
	    };
	    // 서블릿으로 전달할 파라미터 설정
	    var params = 'noticeSeq=' + encodeURIComponent(noticeSeq) + '&action=unFix';
	    xhr.send(params);
	
		
	}
	
	
	// 삭제
	function deleteNotice(noticeSeq, adId) {
	    if (confirm("정말로 삭제하시겠습니까?")) { // 삭제 전 사용자 확인
	        $.ajax({
	            type: "POST",
	            url: "/sangsangjakka/admin/dashboard/notice/managedel.do",
	            data: { noticeSeq: noticeSeq, adId: adId}, 
	            success: function(response) {
	                if (response === "success") {
	                    alert("게시물이 삭제되었습니다.");
	                    // 삭제 성공 시 필요한 처리
	                    window.location.href = "/sangsangjakka/admin/dashboard/notice/manage.do";
	                } else {
	                    alert("게시물 삭제에 실패했습니다.");
	                }
	            },
	            error: function() {
	                alert("서버와의 통신 중 문제가 발생했습니다.");
	            }
	        });
	    }
	}
	
	
	/* 버튼 색깔 변경
	document.addEventListener('DOMContentLoaded', function() {
	    var btns = document.querySelectorAll('.topleft .btn');

	    btns.forEach(function(btn) {
	        btn.addEventListener('click', function() {
	            btn.style.backgroundColor = '#cc0000';
	            btn.style.borderColor = '#cc0000';
	            btn.style.color = 'white';
	        });
	    });
	});
	*/


	document.addEventListener('DOMContentLoaded', function() {
	    var btns = document.querySelectorAll('.topleft .btn');

	    btns.forEach(function(btn) {
	        btn.addEventListener('click', function() {
	            // 현재 색상 확인
	            var currentColor = btn.style.backgroundColor || window.getComputedStyle(btn).backgroundColor;

	            // 현재 색상이 빨간색이거나 짙은 빨간색인지 확인
	            if (currentColor === 'rgb(255, 0, 0)' || currentColor === '#ff0000') {
	                // 현재 색상이 빨간색이면 짙은 빨간색으로 변경
	                btn.style.backgroundColor = '#cc0000';
	                btn.style.borderColor = '#cc0000';
	                btn.style.color = 'white';
	            } else {
	                // 현재 색상이 짙은 빨간색이면 빨간색으로 변경
	                btn.style.backgroundColor = '#ff0000';
	                btn.style.borderColor = '#ff0000';
	                btn.style.color = 'white';
	            }
	        });
	    });
	});

	
	</script>

	<!-- js -->
	<script src="https://code.jquery.com/jquery-3.7.1.js"></script>
	<%@include
		file="/WEB-INF/views/dashboard/dashboard_template/javascript.jsp"%>

	<script>
	</script>
</body>
</html>