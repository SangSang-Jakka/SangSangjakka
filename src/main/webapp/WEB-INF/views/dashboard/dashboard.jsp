<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
	<%@include file="/WEB-INF/views/dashboard/dashboard_template/asset.jsp"%>
</head>
<body>
	
	<!-- 헤더 -->
	<%@include file="/WEB-INF/views/dashboard/dashboard_template/header.jsp"%>

    <!-- 왼쪽 사이드바 -->
	<%@include file="/WEB-INF/views/dashboard/dashboard_template/left_sidebar.jsp"%>

    <!-- 컨텐츠 -->
    <div class="main-container">

        <div class="card-box pd-20 height-100-p mb-30">
            <div class="row align-items-center">
                <div class="col-md-4">
                    <img src="/sangsangjakka/resources/vendors/images/banner-img.png" alt="">
                </div>
                <div class="col-md-8">
                    <h4 class="font-20 weight-500 mb-10 text-capitalize">
                        상상자까 <div class="weight-600 font-30 text-blue">관리자 페이지</div>
                    </h4>
                    <p class="font-18 max-width-600">관리자 페이지입니다.</p>
                </div>
            </div>
        </div>



        <div class="pd-ltr-20 xs-pd-20-10">
            <div class="min-height-200px">

                <div class="row">

                    <div class="col-xl-3 mb-30">
                        <div class="card-box height-100-p widget-style1">
                            <div class="d-flex flex-wrap align-items-center">
                                <div class="widget-data">
                                    <div class="h4 mb-0">새로운 동화책</div>
                                    <div class="weight-600 font-14">Book</div>
                                </div>
                            </div>
                        </div>
                    </div>
                    <div class="col-xl-3 mb-30">
                        <div class="card-box height-100-p widget-style1">
                            <div class="d-flex flex-wrap align-items-center">
                                <div class="widget-data">
                                    <div class="h4 mb-0">새로운 게시글</div>
                                    <div class="weight-600 font-14">Post</div>
                                </div>
                            </div>
                        </div>
                    </div>
                    <div class="col-xl-3 mb-30">
                        <div class="card-box height-100-p widget-style1">
                            <div class="d-flex flex-wrap align-items-center">
                                <div class="widget-data">
                                    <div class="h4 mb-0">새로운 유저</div>
                                    <div class="weight-600 font-14">User</div>
                                </div>
                            </div>
                        </div>
                    </div>
                    <div class="col-xl-3 mb-30">
                        <div class="card-box height-100-p widget-style1">
                            <div class="d-flex flex-wrap align-items-center">
                                <div class="widget-data">
                                    <div class="h4 mb-0">새로운 건의사항</div>
                                    <div class="weight-600 font-14">Suggestion</div>
                                </div>
                            </div>
                        </div>
                    </div>



                </div>


                      <div class="row">
        <div class="col-md-6 col-lg-3">
          <div class="widget-small primary coloured-icon"><i class="icon fa fa-users fa-3x"></i>
            <div class="info">
              <h4>Users</h4>
              <p><b>5</b></p>
            </div>
          </div>
        </div>
        <div class="col-md-6 col-lg-3">
          <div class="widget-small info coloured-icon"><i class="icon fa fa-thumbs-o-up fa-3x"></i>
            <div class="info">
              <h4>Likes</h4>
              <p><b>25</b></p>
            </div>
          </div>
        </div>
        <div class="col-md-6 col-lg-3">
          <div class="widget-small warning coloured-icon"><i class="icon fa fa-files-o fa-3x"></i>
            <div class="info">
              <h4>Uploades</h4>
              <p><b>10</b></p>
            </div>
          </div>
        </div>
        <div class="col-md-6 col-lg-3">
          <div class="widget-small danger coloured-icon"><i class="icon fa fa-star fa-3x"></i>
            <div class="info">
              <h4>Stars</h4>
              <p><b>500</b></p>
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
            <div class="footer-wrap pd-20 mb-20">
                copyrightⓒ 2024 All rights reserved by 상상자까
            </div>
        </div>
    </div>
    
    <!-- js -->
	<%@include file="/WEB-INF/views/dashboard/dashboard_template/javascript.jsp"%>
</body>
</html>