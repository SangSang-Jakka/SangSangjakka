<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
	<%@include file="/WEB-INF/views/dashboard/dashboard_template/asset.jsp"%>
	<link rel="stylesheet" type="text/css" href="/sangsangjakka/resources/vendors/styles/dashboard.css">
</head>
<body>
	
	<!-- 헤더 -->
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
	                                    <h4>게시판 통계</h4>
	                                </div>
	                                <nav aria-label="breadcrumb" role="navigation">
	                                    <ol class="breadcrumb">
	                                        <li class="breadcrumb-item"><a href="index.html">Home</a></li>
	                                        <li class="breadcrumb-item active" aria-current="page">게시판 통계</li>
	                                    </ol>
	                                </nav>
	                            </div>
	                        </div>
	                    </div>
	
	<div class="pd-ltr-20 xs-pd-20-10">
            <div class="min-height-200px">


		<!-- 배너 -->
        <%@include file="/WEB-INF/views/dashboard/dashboard_template/board_banner.jsp"%>

                <div class="bg-white pd-20 card-box mb-30">
                    <h4 class="h4 text-blue">line Chart</h4>
                    <div id="chart1"></div>
                </div>
                <div class="bg-white pd-20 card-box mb-30">
                    <h4 class="h4 text-blue">Area Chart</h4>
                    <div id="chart2"></div>
                </div>
                <div class="bg-white pd-20 card-box mb-30">
                    <h4 class="h4 text-blue">Column Chart</h4>
                    <div id="chart3"></div>
                </div>
                <div class="bg-white pd-20 card-box mb-30">
                    <h4 class="h4 text-blue">Bar Chart</h4>
                    <div id="chart4"></div>
                </div>
                <div class="bg-white pd-20 card-box mb-30">
                    <h4 class="h4 text-blue">Mixed Chart</h4>
                    <div id="chart5"></div>
                </div>
                <div class="bg-white pd-20 card-box mb-30">
                    <h4 class="h4 text-blue">Timeline Chart</h4>
                    <div id="chart6"></div>
                </div>
                <div class="bg-white pd-20 card-box mb-30">
                    <h4 class="h4 text-blue">Candlestick Chart</h4>
                    <div id="chart7"></div>
                </div>
                <div class="row">
                    <div class="col-md-6 mb-30">
                        <div class="pd-20 card-box height-100-p">
                            <h4 class="h4 text-blue">Pie Chart</h4>
                            <div id="chart8"></div>
                        </div>
                    </div>
                    <div class="col-md-6 mb-30">
                        <div class="pd-20 card-box height-100-p">
                            <h4 class="h4 text-blue">Radial Bar Chart</h4>
                            <div id="chart9"></div>
                        </div>
                    </div>
                </div>
            </div>
            
            <!-- 푸터 -->
			<%@include file="/WEB-INF/views/dashboard/dashboard_template/footer.jsp"%>
        </div>
    </div>
    
    <!-- js -->
	<%@include file="/WEB-INF/views/dashboard/dashboard_template/javascript.jsp"%>
	
</body>
</html>