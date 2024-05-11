<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
	<%@include file="/WEB-INF/views/dashboard/dashboard_template/asset.jsp"%>
	<link rel="stylesheet" type="text/css" href="/sangsangjakka/resources/vendors/styles/boardStatistics.css">
	<link rel="stylesheet" type="text/css" href="/sangsangjakka/resources/vendors/styles/dashboard.css">
	<link rel="stylesheet" href="//code.jquery.com/ui/1.11.4/themes/smoothness/jquery-ui.css">
	<style>
	
	
	</style>
	</head>
	<body>
	
		<!-- 헤더 -->
		<%@include file="/WEB-INF/views/dashboard/dashboard_template/header.jsp"%>
	
	    <!-- 왼쪽 사이드바 -->
		<%@include file="/WEB-INF/views/dashboard/dashboard_template/left_sidebar.jsp"%>
	
		<!-- 컨텐츠 시작 -->
	    <div class="main-container">
	        <div class="pd-ltr-20 xs-pd-20-10">
	            <div class="min-height-200px">
	                <div class="page-header">
	                    <div class="row">
	                        <div class="col-md-12 col-sm-12">
	                            <div class="title">
	                                <h4>사용자 통계</h4>
	                            </div>
	                            <nav aria-label="breadcrumb" role="navigation">
	                                <ol class="breadcrumb">
	                                    <li class="breadcrumb-item"><a href="index.html">Home</a></li>
	                                    <li class="breadcrumb-item active" aria-current="page">동화책 통계</li>
	                                </ol>
	                            </nav>
	                        </div>
	                    </div>
	                </div>
	
	                
	<div class="pd-ltr-20 xs-pd-20-10">
            <div class="min-height-200px">


		<!-- 배너 -->
 			<%@include file="/WEB-INF/views/dashboard/dashboard_template/book_banner.jsp"%>  
	
	               
	                
	                <div class="row">
				<div class="col-lg-4 col-md-6 col-sm-12 mb-30">
					<div class="card-box pd-30 pt-10 height-100-p">
						<h2 class="mb-30 h4">Browser Visit</h2>
						<div class="form-group has-success">
				<label class="form-control-label"> DATE</label>
				<input type="text" id="monthPicker2" name="monthPicker2" class="form-control form-control-success">
			    </div>
						<div class="browser-visits">
							<ul>
								<li class="d-flex flex-wrap align-items-center">
									<div class="icon"><img src="vendors/images/chrome.png" alt=""></div>
									<div class="browser-name">Google Chrome</div>
									<div class="visit"><span class="badge badge-pill badge-primary">50%</span></div>
								</li>
								<li class="d-flex flex-wrap align-items-center">
									<div class="icon"><img src="vendors/images/firefox.png" alt=""></div>
									<div class="browser-name">Mozilla Firefox</div>
									<div class="visit"><span class="badge badge-pill badge-secondary">40%</span></div>
								</li>
								<li class="d-flex flex-wrap align-items-center">
									<div class="icon"><img src="vendors/images/safari.png" alt=""></div>
									<div class="browser-name">Safari</div>
									<div class="visit"><span class="badge badge-pill badge-success">40%</span></div>
								</li>
								<li class="d-flex flex-wrap align-items-center">
									<div class="icon"><img src="vendors/images/edge.png" alt=""></div>
									<div class="browser-name">Microsoft Edge</div>
									<div class="visit"><span class="badge badge-pill badge-warning">20%</span></div>
								</li>
								<li class="d-flex flex-wrap align-items-center">
									<div class="icon"><img src="vendors/images/opera.png" alt=""></div>
									<div class="browser-name">Opera Mini</div>
									<div class="visit"><span class="badge badge-pill badge-info">20%</span></div>
								</li>
							</ul>
						</div>
					</div>
				</div>
				<div class="col-lg-8 col-md-6 col-sm-12 mb-30">
					<div class="card-box pd-30 pt-10 height-100-p">
						<h2 class="mb-30 h4">World Map</h2>
						<div id="browservisit" style="width:100%!important; height:380px"></div>
					</div>
				</div>
			</div>
                
	
	
	            <!-- 푸터 -->
				<%@include file="/WEB-INF/views/dashboard/dashboard_template/footer.jsp"%> 
				
	        	</div>
	    	</div>
	    </div>
	
	<!-- js -->
	<script src="https://code.jquery.com/jquery-3.7.1.js"></script>
	<%@include file="/WEB-INF/views/dashboard/dashboard_template/javascript.jsp"%> 
	<script src="https://code.highcharts.com/highcharts.js"></script>
<!-- 	<script src="/sangsangjakka/resources/vendors/scripts/dashboard.js"></script> -->
	<script src="/sangsangjakka/resources/plugins/apexcharts/apexcharts.min.js"></script>
	<script src="/sangsangjakka/resources/vendors/scripts/dashboard-chart.js"></script>
		<!-- jquery UI -->
<!-- 	<script src="//code.jquery.com/jquery.min.js"></script> -->
<!-- 	<script src="//code.jquery.com/ui/1.11.4/jquery-ui.min.js"></script> -->
	<script src="/sangsangjakka/resources/vendors/scripts/jquery.mtz.monthpicker.js"></script>
	

	 <script>
    // getBarChartOptions 함수 사용
    var options = getBarChartOptions();

    // 차트 생성
    var chart = new ApexCharts(document.querySelector("#chart"), options);
    chart.render();
  </script>
  <script>
 
	$(document).ready(function() {
		  var options = {
		    pattern: 'yyyy/mm', // input태그에 표시될 형식
		    selectedYear: 2019, // 선택할 연도
		    startYear: 2020, // 시작연도
		    finalYear: 2023, // 마지막연도
		    monthNames: ['1월', '2월', '3월', '4월', '5월', '6월', '7월', '8월', '9월', '10월', '11월', '12월'], // 화면에 보여줄 월이름
		    openOnFocus: true, // focus시에 달력이 보일지 유무
		    disableMonths: [] // 월 비활성화
		  };

		  // 방법1) options 따로 지정
		  
		  $("#monthPicker2").monthpicker(options);
	 });
  </script>
	
	</body>
</html>