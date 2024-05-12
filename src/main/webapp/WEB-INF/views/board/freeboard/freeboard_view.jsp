<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<%@include file="/WEB-INF/views/template/asset.jsp"%>
<link rel="stylesheet"
	href="/sangsangjakka/resources/css/board/freeboard/freeboard_view.css">
<style>
</style>
</head>
<body>

	<!-- header -->
	<%@include file="/WEB-INF/views/template/header.jsp"%>

	<section class="notice">
		<div class="pageTitle">
			<div>
				<h3>자유 게시판</h3>
			</div>
		</div>



		<!-- board list area -->
		<div>

			<table class="boardTitle">
				<tbody>
					<tr>
						<td scope="col" class="tdTitle">${dto.boardTitle}</td>
						<td scope="col" class="tdDate">${dto.boardRegdate}</td>
						<td scope="col" class="tdViews">${dto.boardCnt}</td>
					</tr>
				</tbody>
			</table>

			<div class="file">
				<i class="fa-regular fa-file"></i> 파일이 없습니다
			</div>

			<div class="boardMain">${dto.boardContents}</div>



			<div class="conditioneWrap">
				<div class="condtionContainer">
					<button class="reportIcon">
						<i class="fa-regular fa-bell"></i>
					</button>
					<span class="reportName">신고</span> <span class="reportCount">${dto.boardReportCnt}</span>
				</div>



				<div class="condtionContainer">
					<button class="commentIcon">
						<i class="fa-regular fa-message"></i>
					</button>
					<span class="commentName">댓글</span> <span class="commentCount">${dto.cmntCnt}</span>
				</div>
			</div>



			<div class="btnWrap">
				<c:if test="${dto.userSeq == userSeq}">
					<input type="button" class="btnEdit" value="수정"
						onclick="location.href='/sangsangjakka/board/freeboard/edit.do?no=' + ${dto.boardSeq}">
					<input type="button" class="btnDel" value="삭제">
				</c:if>
			</div>


			<div class="commentWrap">
				<!-- 전체 -->
				<div class="commmentContainer">

					<c:forEach items="${list}" var="dto">
						<ul class="commentArea">
							<li>
								<div class="commentNick">
									<i class="fa-solid fa-user"></i>${dto.userNick}</div>
								<div class="commentBtnBlock">
									<c:if test="${dto.userSeq == userSeq}">
										<button class="reviewEdit"
											onclick="showEditArea(${dto.cmntSeq});">수정하기</button>
										<button class="reviewDel"
											onclick="reviewDelete(${dto.cmntSeq});">삭제하기</button>
									</c:if>
								</div>
								<div class="reviewContents" id="reviewContent_${dto.cmntSeq}">${dto.cmntContents}</div>
								<div class="reviewEditArea" id="editArea_${dto.cmntSeq}"
									style="display: none;">
									<textarea name="editReviewContents"
										id="textarea_${dto.cmntSeq}" rows="4" cols="50"
										style="resize: none; width: 1250px; height: 50px; margin-top: 20px; padding: 15px; font-size: 16px; border-radius: 10px;">${dto.cmntContents}</textarea>
									<div class="reviewEditBtn">
										<button class="saveReviewBtn"
											onclick="saveReview(${dto.cmntSeq});">저장하기</button>
										<button class="cancelReviewBtn"
											onclick="cancelReview(${dto.cmntSeq});">취소하기</button>
									</div>
								</div>
								<div class="commentInfo">
									<span class="commentInfoDate">${dto.cmntRegdate}</span>
								</div>
							</li>
						</ul>
					</c:forEach>


				</div>

				<!-- 댓글 추가 창 -->
				<div class="commentAddBox">
					<div class="commentAdd">
						<div class="commentAddNick">${userNick}</div>
						<textarea class="commentAddText" name="commentText"></textarea>
					</div>

					<input type="button" name="commentContent" value="등록"
						class="btnCommentAdd">


				</div>
			</div>
		</div>


		<div class="listWrap">
			<table class="listTable">
				<tbody class="listBox">
					<tr class="preBox">
						<th scope="col" class="listOrder">이전글</th>
						<th scope="col" class="listTitle"><a href="">동화책 만드는 거
								재밌네요</a></th>
						<th scope="col" class="listDate">2024-01-04</th>
						<th scope="col" class="listViews">50</th>
					</tr>
					<tr>
						<th scope="col" class="listOrder">다음글</th>
						<th scope="col" class="listTitle"><a href="">유치원 질문</a></th>
						<th scope="col" class="listDate">2024-05-06</th>
						<th scope="col" class="listViews">10</th>
					</tr>
				</tbody>

			</table>
		</div>


		<div class="btnListTable">
			<input type="button" class="btnList" value="목록"
				onclick="location.href='/sangsangjakka/board/freeboard/list.do'">
		</div>


		<input type="hidden" name=delUserSeq value="${dto.userSeq}">


	</section>


	<!-- footer -->
	<%@include file="/WEB-INF/views/template/footer.jsp"%>


	<script src="https://code.jquery.com/jquery-3.7.1.js"></script>
	<script src="https://kit.fontawesome.com/8a0ce49d35.js"
		crossorigin="anonymous"></script>

	<script>
	   // 삭제하기
    function showEditArea(cmntSeq) {
     var editArea = $('#editArea_' + cmntSeq);
     if (editArea.length > 0) {
         editArea.slideDown('slow');
         $('.reviewEdit').hide();
         $('.reviewDel').hide();
     } else {
         console.error('요소를 찾을 수 없습니다: editArea_' + cmntSeq);
     }
 }
 
 function cancelReview(cmntSeq) {
     var editArea = $('#editArea_' + cmntSeq);
     if (editArea.length > 0) {
         editArea.slideUp('slow');
         $('.reviewEdit').show();
         $('.reviewDel').show();
     } else {
         console.error('요소를 찾을 수 없습니다: editArea_' + cmntSeq);
     }
 }
 
 function saveReview(cmntSeq) {
     var reviewContents = $('#textarea_' + cmntSeq).val();
     var boardSeq = ${dto.boardSeq}; // 게시글 일련번호를 가져와야 함
 
     var url = '/sangsangjakka/board/freeboard/editcomment.do';
 
     var editReview = {
         cmntContents: reviewContents,
         cmntSeq: cmntSeq,
         boardSeq: boardSeq
     };
 
     $.ajax({
         url: url,
         type: 'POST',
         data: editReview,
         success: function(response) {
             var updatedReviewContents = response.reviewContents;
             var reviewContentElement = $('#reviewContent_' + cmntSeq);
             reviewContentElement.text(updatedReviewContents);
             $('#editArea_' + cmntSeq).slideUp('slow', function() {
                 $(this).hide();
             });
             console.log('댓글이 성공적으로 저장되었습니다.');
         },
         error: function(xhr, status, error) {
             console.error('댓글 저장 중 오류가 발생했습니다:', error);
         }
     });
 }
 
 function reviewDelete(cmntSeq) {
     if (confirm("정말 삭제 하시겠습니까?") == true) {
         $.ajax({
             type: "POST",
             url: "/sangsangjakka/board/freeboard/delcomment.do",
             data: {
                 cmntSeq: cmntSeq,
                 boardSeq: ${dto.boardSeq},
                 userSeq: ${userSeq} 
             },
             dataType: "JSON",
             beforeSend: function(xhr) {
                 xhr.setRequestHeader("AJAX", "true");
             },
             success: function(response) {
                 if (response.code === 0) {
                     alert(response.message);
                     location.href = '/sangsangjakka/board/freeboard/view.do?no=' + ${dto.boardSeq};
                 } else {
                     alert(response.message);
                 }
             },
             error: function(xhr, status, error) {
                 alert("AJAX 요청 중 오류가 발생했습니다: " + error);
             }
         });
     }
 }
 
 $(".btnDel").on("click", function() {
     if(confirm("정말 삭제 하시겠습니까?") == true) {
         // 정말 삭제하겠다고 했을 때, ajax 통신
         $.ajax({
             type: "POST",
             url: "/sangsangjakka/board/freeboard/del.do",
             data: {
                 boardSeq: '${dto.boardSeq}',
                 userSeq: '${dto.userSeq}'
             },
             dataType: "JSON",
             beforeSend: function(xhr) {
                 xhr.setRequestHeader("AJAX", "true");
             },
             success: function(response) {
                 if (response.code === 0) {
                     alert(response.message);
                     location.href = "/sangsangjakka/board/freeboard/list.do";
                 } else {
                     alert(response.message);
                 }
             },
             error: function(xhr, status, error) {
                 alert("AJAX 요청 중 오류가 발생했습니다: " + error);
             }
         });
     }
 });
 
 $('.btnCommentAdd').on("click", function() {
    $.ajax({
       type: "POST",
       url: "/sangsangjakka/board/freeboard/view.do",
       data: {
          userSeq:  '${dto.userSeq}',
          boardSeq: '${dto.boardSeq}',
          cmntContents: $('.commentAddText').val().trim()
       },
       dataType: "JSON",
       beforeSend: function(xhr) {
          xhr.setRequestHeader("AJAX", "true");
       },
       success: function(response) {
          if(response.code === 0) {
             alert(response.message);
             location.href = "/sangsangjakka/board/freeboard/view.do?no=${dto.boardSeq}";
          } else {
             alert(response.message);
          }
       },
       error: function(xhr, status, error) {
          alert("댓글 내용을 입력해주세요: " + error);
       }
    });
 });

	</script>
</body>
</html>
