<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
	<%@include file="/WEB-INF/views/dashboard/dashboard_template/asset.jsp"%>
	<link rel="stylesheet" type="text/css" href="/sangsangjakka/resources/vendors/styles/boardStatistics.css">
	<link rel="stylesheet" type="text/css" href="/sangsangjakka/resources/vendors/styles/dashboard.css">
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
	
	               
	                
	                <div class="col-xl-8 mb-30">
					<div class="card-box height-100-p pd-20">
						<h2 class="h4 mb-20">Activity</h2>
						<div id="chart"></div>
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

	 <script>
    // getBarChartOptions 함수 사용
    var options = getBarChartOptions();

    // 차트 생성
    var chart = new ApexCharts(document.querySelector("#chart"), options);
    chart.render();
  </script>
	
	</body>
</html>