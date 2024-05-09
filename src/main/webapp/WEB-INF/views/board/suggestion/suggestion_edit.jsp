<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
	<%@include file="/WEB-INF/views/template/asset.jsp"%>
<link rel="stylesheet" href="/sangsangjakka/resources/css/board/suggestion/suggestion_edit.css">
<link rel="stylesheet" href="/sangsangjakka/resources/css/summernote/summernote-lite.css">

<script src="https://code.jquery.com/jquery-3.7.1.js"></script>
<script src="/sangsangjakka/resources/js/summernote/summernote-lite.js"></script>
<script src="/sangsangjakka/resources/js/summernote/lang/summernote-ko-KR.js"></script>
<style>
	
</style>
</head>
<body>
	<!-- header -->
		<%@include file="/WEB-INF/views/template/header.jsp"%>
	
	<section class="Suggestion">
        <div class="pageTitle">
              <div>
                  <h3>건의 사항
                    <small>수정하기</small>
                    </h3>
              </div>
          </div>


          <form action="/sangsangjakka/board/suggestion/edit.do?sgstSeq=${dto.sgstSeq}" method="POST">
            <div class="freeboardWrap">
                <div class="freeTitleContainer">
                    <div class="freeTitle">제목</div>
                    <input type="text" name="subject" class="subject" value="${dto.sgstTitle}" required class="text"></td>
                </div>
                <div class="freeFileContainer">
                    <div class="freeTitle">파일</div>
                    <label for="file-input" class="file-label" name="attach">파일 선택 ( 💡 클릭하면 파일을 첨부 할 수 있습니다 💡)</label>
                <span id="file-name"></span>
                    <input type="file" id="file-input" name="attach" class="file-input">
                </div>
                <div class="freeContentContainer">
                    <div class="freeTitle">내용</div>
                    <textarea id="summernote" name="content" class="content" required class="text">${dto.sgstContents}</textarea>
                </div>
          	</div>


            <div class="btnBox">
                <button class="btnBack" onclick="location.href='/sangsangjakka/board/suggestion/list.do';">
                    <i class="fa-solid fa-rotate-left"></i> 돌아가기
                </button>

                <button type="submit" class="btnWrite" onclick="location.href='';">
                    <i class="fa-solid fa-pencil"></i> 수정
                </button>

            	</div>
            <input type="hidden" name="reply" value="${reply}">
            <input type="hidden" name="thread" value="${thread}">
            <input type="hidden" name="depth" value="${depth}">
            <input type="hidden" name="sgstSeq" value="${dto.sgstSeq}">	<!-- 글 번호 -->
            <input type="hidden" name="secret" value="${dto.sgstSecretYN}">	<!-- 비밀 유무 -->
            
            </form>
      
        
  


    </section>
  
  <!-- footer -->
		<%@include file="/WEB-INF/views/template/footer.jsp"%>
		
		
  <script src="https://kit.fontawesome.com/8a0ce49d35.js" crossorigin="anonymous"></script>
  
	<script src="https://code.jquery.com/jquery-3.7.1.js"></script>
	<script>
	<script>
    $('#summernote').summernote({
        // 에디터 높이
        height: 370,
        // 에디터 한글 설정
        lang: "ko-KR",
        // 에디터에 커서 이동 (input창의 autofocus라고 생각)
        focus : true,
        toolbar: [
            // 글꼴 설정
            ['fontname', ['fontname']],
            // 글자 크기 설정
            ['fontsize', ['fontsize']],
            // 굵기, 기울임꼴, 밑줄,취소 선, 서식지우기
            ['style', ['bold', 'italic', 'underline','strikethrough', 'clear']],
            // 글자색
            ['color', ['forecolor','color']],
            // 표만들기
            ['table', ['table']],
            // 글머리 기호, 번호매기기, 문단정렬
            ['para', ['ul', 'ol', 'paragraph']],
            // 줄간격
            ['height', ['height']],
        ],
        // 추가한 글꼴
        fontNames: ['Arial', 'Arial Black', 'Comic Sans MS', 'Courier New','맑은 고딕','궁서','굴림체','굴림','돋음체','바탕체'],
        // 추가한 폰트사이즈
        fontSizes: ['8','9','10','11','12','14','16','18','20','22','24','28','30','36','50','72']
    });
    
    document.getElementById('file-input').addEventListener('change', function(e) {
        var fileName = "첨부된 파일: " + e.target.files[0].name;
        document.getElementById('file-name').textContent = fileName;
    });
    
    $('form').submit(function() {
        var content = $('#summernote').summernote('code').replace(/<\/?[^>]+(>|$)/g, '');
        $('#summernote').val(content);
    });
	</script>
</body>
</html>
