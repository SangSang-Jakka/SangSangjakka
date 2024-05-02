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
	<%@include file="/WEB-INF/views/dashboard/dashboard_template/header.jsp"%>

    <!-- 왼쪽 사이드바 -->
	<%@include file="/WEB-INF/views/dashboard/dashboard_template/left_sidebar.jsp"%>

    <!-- 컨텐츠 -->
    <div class="main-container">

        <div class="bannerBox">

            <div class="ImageBox">
                <img src="/sangsangjakka/resources/vendors/images/ai-generated-8725235_1280.jpg" alt="관리자">
            </div>
            <div class="textBox">
                <p class="font-30 weight-600 mb-10 text-capitalize text-blue">상상자까 관리자 페이지</p>
                <p class="font-18"><b>관리자 페이지입니다.</b></p>
            </div>
        </div>

        <div class="pd-ltr-20 xs-pd-20-10">
            <div class="min-height-200px">


                <div class="updateContainer">
                    <div class="card-box mr">
                        <div class="widget-small primary">
                            <div class="coloured-icon-a"><i class="icon fa fa-book fa-3x" style="color: white"></i></div>
                            <div class="info">
                                <div class="titleElement">새로운 동화책</div>
                                <div class="countElement">10</div>
                            </div>
                        </div>
                    </div>
                    <div class="card-box mr">
                        <div class="widget-small danger">
                            <div class="coloured-icon-b"><i class="icon fa fa-star fa-3x" style="color: white"></i></div>
                            <div class="info">
                                <div class="titleElement">새로운 게시글</div>
                                <div class="countElement">10</div>
                            </div>
                        </div>
                    </div>
                    <div class="card-box mr">
                        <div class="widget-small primary">
                            <div class="coloured-icon-c"><i class="icon fa fa-users fa-3x" style="color: white"></i></div>
                            <div class="info">
                                <div class="titleElement">새로운 유저</div>
                                <div class="countElement">10</div>
                            </div>
                        </div>
                    </div>
                    <div class="card-box mr">
                        <div class="widget-small primary">
                            <div class="coloured-icon-d"><i class="icon fa fa-quora fa-3x" style="color: white"></i></div>
                            <div class="info">
                                <div class="titleElement">새로운 건의사항</div>
                                <div class="countElement">10</div>
                            </div>
                        </div>
                    </div>
                </div>


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