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
								<h4>건의사항</h4>
							</div>
							<nav aria-label="breadcrumb" role="navigation">
								<ol class="breadcrumb">
									<li class="breadcrumb-item"><a href="index.html">Home</a></li>
									<li class="breadcrumb-item active" aria-current="page">건의사항
										게시판</li>
								</ol>
							</nav>
						</div>
					</div>
				</div>



				<!-- 게시글 상세보기 -->
				<div class="row">
					<div class="viewContainer">
						<h2>게시글 상세</h2>
						<!-- 
						<span class="right"> <input type="button" value="삭제"
							class="btn btn-primary">
						</span>

					 -->

						<table>
							<tr>
								<th>제목</th>
								<td>${dto.sgstTitle}</td>
								<th>조회수</th>
								<td>${dto.sgstCnt}</td>
							</tr>
							<tr>
								<th>작성자</th>
								<td>${dto.userSeq}</td>
								<th>작성일</th>
								<td>${dto.sgstRegdate}</td>
							</tr>
							<tr>
								<th>내용</th>
								<td colspan="3"><textarea>${dto.sgstContents}</textarea></td>
							</tr>
							<tr>
								<th>첨부파일</th>
								<td colspan="3"></td>
							</tr>
						</table>

						<!-- 답변 컨테이너 -->
						<div class="commentContainer">
							<c:forEach var="answer" items="${answerList}">
								<div class="commentItem">
									<div>
										<div class="commentHeader">
											<div class="commentWriter">${answer.adId}</div>
											<div class="commentActions">
												<button class="btnEdit" onclick="edit(${answer.answSeq}, '${answer.sgstAnsw}')">수정</button>
												<button class="btnDel" onclick="del(${answer.answSeq}, '${answer.adId}');">삭제</button>
											</div>
											<div class="commentTime">${answer.sgstRegdate}</div>
										</div>
										<div class="commentContent" id="change">${answer.sgstAnsw}</div>
									</div>
								</div>
							</c:forEach>

							<!-- 답변 입력 폼 -->
							<div class="commentInput">
								<form method="POST"
									action="/sangsangjakka/admin/dashboard/suggestion/manageview.do">
									<input type="hidden" name="seq" value="${dto.sgstSeq}">
									<input type="text" name="sgstAnsw" placeholder="답변을 입력하세요.">
									<input type="submit" class="btn btn-primary" value="답변">
								</form>
							</div>
						</div>

						<span class="left"> <input type="button" value="이전"
							class="btn btn-primary"> <input type="button" value="다음"
							class="btn btn-primary">
						</span> <span class="right"> <input type="button" value="목록"
							class="btn btn-primary pull-right"
							onclick="location='/sangsangjakka/admin/dashboard/suggestion/manage.do'"></span>

					</div>
				</div>

			</div>
			<!-- 푸터 -->
			<%@include
				file="/WEB-INF/views/dashboard/dashboard_template/footer.jsp"%>

		</div>
	</div>
	
	<script>


	
	// 답변 삭제
	
function del(answSeq, adId) {
	    if (confirm("정말로 삭제하시겠습니까?")) { // 삭제 전 사용자 확인
	        $.ajax({
	            type: "POST",
	            url: "/sangsangjakka/admin/dashboard/suggestionanswer/managedel.do",
	            data: { answSeq: answSeq, adId: adId}, 
	            success: function(response) {
	                if (response === "success") {
	                    alert("답변이 삭제되었습니다.");
	                    // 삭제 성공 시 필요한 처리
	                    window.location.href = "/sangsangjakka/admin/dashboard/suggestion/manageview.do?seq=${dto.sgstSeq}";
	                } else {
	                    alert("답변 삭제에 실패했습니다.");
	                }
	            },
	            error: function() {
	                alert("서버와의 통신 중 문제가 발생했습니다.");
	            }
	        });
	    }
	}



	function edit(answSeq, sgstAnsw) {
	    $('.commentEditRow').remove();

	    let commentItem = $(event.target).parents('.commentItem');
	    let content = $(event.target).parents('.commentItem').find('.commentContent').text(); 
	    
	    // 1
	   alert(content);

	    $(event.target).parents('.commentItem').after(`
	        <div class="commentEditRow">
	            <div>
	                <input type="text" name="content" class="full" required value="\${content}" id="txtComment">
	            </div>
	            <div class="commentEdit">
	                <button class="btnEdit" onclick="editComment(\${answSeq}, '${sgstAnsw}');">수정</button>
	                <button class="btnDel" onclick="$(event.target).parents('.commentEditRow').remove();">취소</button>
	            </div>
	        </div>
	    `);
	}

	function editComment(answSeq, sgstAnsw) {
		
		// 2
		alert(answSeq);
		// 3 
		alert($('#txtComment').val());
		
	    let editedContent = $('#txtComment').val();
	    let commentItem = $(event.target).parents('.commentItem');

	    $.ajax({
	        type: 'POST',
	        url: '/sangsangjakka/admin/dashboard/suggestionanswer/manageedit.do',
	        data: {
	        	answSeq : answSeq,
	            sgstAnsw: $('#txtComment').val()
	        },
	        dataType: 'json',
	        success: function(result) {
	            if (result.result == '1') {
	            	
	            	// alert('성공');
	            	
	            	 commentItem.find('.commentContent').text(editedContent); // 수정된 내용으로 변경
                commentItem.find('.commentEditRow').remove(); // 수정 창 제거
	            	
	              // commentItem.find('.commentContent').text(editedContent);
	              // commentItem.find('.commentEditRow').remove();
	                
	            } else {
	                alert('답변 수정을 실패했습니다.');
	            }
	        },
	        error: function(a,b,c) {
	            console.log(a,b,c);
	        }
	    });
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
		
</body>
</html>