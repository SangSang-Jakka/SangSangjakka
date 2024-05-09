<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
	<%@include file="/WEB-INF/views/dashboard/dashboard_template/asset.jsp"%>
	
	<link rel="stylesheet" type="text/css" href="/sangsangjakka/resources/plugins/datatables/css/dataTables.bootstrap4.min.css">
	<link rel="stylesheet" type="text/css" href="/sangsangjakka/resources/plugins/datatables/css/responsive.bootstrap4.min.css">
	<link rel="stylesheet" type="text/css" href="/sangsangjakka/resources/vendors/styles/suggestions.css">
	<link rel="stylesheet" type="text/css" href="/sangsangjakka/resources/vendors/styles/boardStatistics.css">
	
	<style>
	
	
	</style>
	</head>
	<body>
		
		<!-- 헤더 -->
		<%@include file="/WEB-INF/views/dashboard/dashboard_template/header.jsp"%>
	
	    <!-- 왼쪽 사이드바 -->
		<%@include file="/WEB-INF/views/dashboard/dashboard_template/left_sidebar.jsp"%>
	
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
	                                   <li class="breadcrumb-item active" aria-current="page">동화책 신고 관리</li>
	                               </ol>
	                           </nav>
	                       </div>
	                   </div>
	               </div>
	
	
	              
	
	
	
	               
				<!-- Simple Datatable start -->
				
				<form  method ="POST" action="/sangsangjakka/admin/edit.do">
				<div class="pd-20 card-box mb-30">
					<div class="clearfix">
						<h4 class="text-blue h4">관리자 정보</h4>
						
					<button type="submit" class="btn btn-primary pull-right">확인</button>
					</div>
					<div class="wizard-content">
						
							
						
								<div class="row">
									<div class="col-md-6">
										<div class="form-group">
											<label >이름 :</label>
											<input type="text" class="form-control" name ="adName" value="${adminedit.adName}">
										</div>
									</div>
									<div class="col-md-6">
										<div class="form-group">
											<label >주소 : </label>
											<input type="text" class="form-control" name ="adAddress" value="${adminedit.adAddress}">
										</div>
									</div>
								</div>
								<div class="row">
									<div class="col-md-6">
										<div class="form-group">
											<label>전화번호 :</label>
											<input type="text" class="form-control" name ="adTel" value="${adminedit.adTel}">
										</div>
									</div>
									<div class="col-md-6">
										<div class="form-group">
											<label>닉네임 :</label>
											<input type="text" class="form-control" name="adNick" value="${adminedit.adNick}">
										</div>
									</div>
								</div>
								<div class="row">
									<div class="col-md-6">
										<div class="form-group">
											<label >아이디 :</label>
											<input type="text" class="form-control" name ="adId" value="${adminedit.adId}">
										</div>
									</div>
									<div class="col-md-6">
										<div class="form-group">
											<label >비밀번호 : </label>
											<input type="text" class="form-control" name="adPw" value="${adminedit.adPw}">
										</div>
									</div>
								</div>
							 <div>

                       <button class="btn btn-danger" onclick="del(adId);">삭제</button>
                        //<button type="submit" class="btn btn-danger">계정 삭제</button>
                        <button type="button" class="btn btn-primary pull-right" onclick ="location.href='/sangsangjakka/admin/edit.do?id=${adminedit.adId}';"> 돌아가기</button>
                  		
                    </div>
							<!-- Step 2 -->
                           
						</form>
                        
					</div>
				</div>
			
			<!-- Simple Datatable End -->
	
			
			<!-- 푸터 -->
			<%@include file="/WEB-INF/views/dashboard/dashboard_template/footer.jsp"%>
			
		</div>
	</div>

	<!-- js -->
	<script src="https://code.jquery.com/jquery-3.7.1.js"></script>
	<%@include file="/WEB-INF/views/dashboard/dashboard_template/javascript.jsp"%>
	<script src="/sangsangjakka/resources/plugins/datatables/js/jquery.dataTables.min.js"></script>
	<script src="/sangsangjakka/resources/plugins/datatables/js/dataTables.bootstrap4.min.js"></script>
	<script src="/sangsangjakka/resources/plugins/datatables/js/dataTables.responsive.min.js"></script>
	<script src="/sangsangjakka/resources/plugins/datatables/js/responsive.bootstrap4.min.js"></script>
	
	<!-- buttons for Export datatable -->
	<script src="/sangsangjakka/resources/plugins/datatables/js/dataTables.buttons.min.js"></script>
	<script src="/sangsangjakka/resources/plugins/datatables/js/buttons.bootstrap4.min.js"></script>
	<script src="/sangsangjakka/resources/plugins/datatables/js/buttons.print.min.js"></script>
	<script src="/sangsangjakka/resources/plugins/datatables/js/buttons.html5.min.js"></script>
	<script src="/sangsangjakka/resources/plugins/datatables/js/buttons.flash.min.js"></script>
	<script src="/sangsangjakka/resources/plugins/datatables/js/pdfmake.min.js"></script>
	<script src="/sangsangjakka/resources/plugins/datatables/js/vfs_fonts.js"></script>
	
	<!-- Datatable Setting js -->
	<script src="/sangsangjakka/resources/vendors/scripts/datatable-setting-ver2.js"></script>

	<script src="https://cdnjs.cloudflare.com/ajax/libs/moment.js/2.29.1/moment.min.js"></script>
	<script>
	
	
	</script>
	
	<script>
	
	function del(adId) {
	    if (confirm("정말로 삭제하시겠습니까?")) {
	        $.ajax({
	            type: "POST",
	            url: "/sangsangjakka/admin/admin/edit.do",
	            data: { action: 'delete', adId: adId },
	            dataType: 'json',
	            success: function(data) {
	                if (data.result === 1) {
	                    window.location.href = "/sangsangjakka/admin/list.do";
	                } else {
	                    alert('삭제에 실패했습니다.');
	                }
	            },
	            error: function() {
	                alert("서버와의 통신 중 문제가 발생했습니다.");
	            }
	        });
	    }
	}
	
	</script>
	</body>
</html>