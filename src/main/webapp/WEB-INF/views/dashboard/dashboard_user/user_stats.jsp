<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
	<%@include file="/WEB-INF/views/dashboard/dashboard_template/asset.jsp"%>
	<link rel="stylesheet" type="text/css" href="/sangsangjakka/resources/vendors/styles/boardStatistics.css">
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
	                                    <li class="breadcrumb-item active" aria-current="page">사용자 통계</li>
	                                </ol>
	                            </nav>
	                        </div>
	                    </div>
	                </div>
	
	                <div class="row">
	
	                    <div class="postBox">
	                        <div class="today">
	                            <i class="icon-copy dw dw-pencil "></i>
	                            <h4>신규 가입자수</h4>
	                            <p>+${userCnt}</p>
	                        </div>
	                        <div class="yesterday">
	                            <i class="icon-copy dw dw-pencil "></i>
	                            <h4>차단된 사용자수</h4>
	                            <p>3</p>
	                        </div>
	                        <div class="accumulate">
	                            <i class="icon-copy dw dw-pencil "></i>
	                            <h4>탈퇴회원수</h4>
	                            <p>300</p>
	                        </div>
	                    </div>
	                </div>
	
	                <div class="boardTotal">
	                    <div class="boardSection">
	                        <h4>동화공유게시판</h4>
	                        <p>10</p>
	                    </div>
	                    <div class="boardSection">
	                        <h4>자유게시판</h4>
	                        <p>85</p>
	                    </div>
	                    <div class="boardSection">
	                        <h4>건의사항</h4>
	                        <p>5</p>
	                    </div>
	                </div>
	
<!--  	                <div class="chartContainer">  -->
<!-- 	                 	  <div id="boardChart"></div> -->
 	               <div>
	                	<div style="width: 300px; height: 300px; background-color: white;
	                	border-radius: 10px;">
       					 <canvas id="myChart"></canvas>
    					</div>
    				</div>
<!-- 	 				</div>  -->
	
	            <!-- 푸터 -->
				<%@include file="/WEB-INF/views/dashboard/dashboard_template/footer.jsp"%>
				
	        	</div>
	    	</div>
	    </div>
	
	<!-- js -->
	<script src="https://code.jquery.com/jquery-3.7.1.js"></script>
	<%@include file="/WEB-INF/views/dashboard/dashboard_template/javascript.jsp"%>
	<script src="https://code.highcharts.com/highcharts.js"></script>
	  <script src="https://cdnjs.cloudflare.com/ajax/libs/Chart.js/2.4.0/Chart.min.js"></script>
	<script>
		
	Highcharts.chart('boardChart', {

	    title: {
	    text: '인기 게시판 통계'
	},

	subtitle: {
	    text: '최근 7일 기준'
	},

	yAxis: {
	    title: {
	        text: '작성수'
	    }
	},

	xAxis: {
	    title: {
	        text: '날짜'
	    },

	    categories: ['4월 30일', '5월 1일', '5월 2일', '5월 3일', '5월 4일', '5월 5일', '5월 7일', '5월 8일']
	},
	    
	/* 범례를 우측 세로로 정렬 */
	legend: {
	    layout: 'vertical',
	    align: 'right',
	    verticalAlign: 'middle'
	},

	series: [{
	    name: '자유게시판',
	    data: [10, 15, 30, 15, 10, 16, 11, 8]
	}, {
	    name: '동화 공유 게시판',
	    data: [5, 8, 7, 10, 15, 11, 3, 6]
	}, {
	    name: '건의사항',
	    data: [1, 0, 3, 2, 5, 1, 2, 1]
	}],
	});
	
	</script>
	
	<script type="text/javascript">
        // 서버에서 받은 데이터를 사용하여 차트를 그립니다.
        var chartData = <%= request.getAttribute("jsonData") %>;
        // 차트 생성을 위한 설정
        var context = document.getElementById('myChart').getContext('2d');
        var myChart = new Chart(context, {
            type: 'doughnut', // 도넛 차트 설정
            data: {
            	
                labels: chartData.labels, // 차트의 라벨을 설정합니다.
                datasets: [{
                	
                	
                    label: chartData.label, // 데이터셋의 라벨을 설정합니다.
                    data: chartData.data, // 차트에 표시될 데이터를 설정합니다.
                    backgroundColor: [
                        //색상
                        
                        'rgba(54, 162, 235, 0.2)',
                        'rgba(255, 99, 132, 0.2)'
                        
                    ],
                    borderColor: [
                        //경계선 색상
                       
                        'rgba(54, 162, 235, 1)',
                        'rgba(255, 99, 132, 1)'
                        
                    ],
 // 데이터의 테두리 색을 설정합니다.
                    borderWidth: 1 // 데이터의 테두리 두께를 설정합니다.
                }]
            },
            options: {
                // 차트의 옵션 설정을 추가할 수 있습니다.
            }
        });
    </script>
	
	

  
	</body>
</html>