<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
	<%@include file="/WEB-INF/views/dashboard/dashboard_template/asset.jsp"%>
<!-- 	<link rel="stylesheet" type="text/css" href="/sangsangjakka/resources/vendors/styles/boardStatistics.css"> -->
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
	                                    <li class="breadcrumb-item active" aria-current="page">사용자 통계</li>
	                                </ol>
	                            </nav>
	                        </div>
	                    </div>
	                </div>
	
		<div class="pd-ltr-20 xs-pd-20-10">
		 <div class="min-height-200px">
	
		<!-- 배너 -->
        <%@include file="/WEB-INF/views/member/user/user_template/user_banner.jsp"%>

<!--  	               <p>성별</p> -->
<!--            			<div style="width: 300px; height: 300px; background-color: white; -->
<!--            			border-radius: 10px;"> -->
<%-- 				 	<canvas id="genderChart"></canvas> --%>
<!--    					</div> -->
    			
    			
    		<div class="row">
				<div class="col-xl-4 mb-30">
					<div class="card-box height-100-p pd-20">
						<h2 class="h4 mb-20">성별</h2>
						<canvas id="genderChart"></canvas>
					</div>
				</div>
				<div class="col-xl-8 mb-30">
					<div class="card-box height-100-p pd-20">
						<h2 class="h4 mb-20">자녀연령대</h2>
						<canvas id="AgeChart"></canvas>
					</div>
				</div>
			</div>
			
			
				
				
				<div class="row">
				<div class="col-xl-4 mb-30">
					<div class="card-box height-100-p pd-20">
						<h2 class="h4 mb-20">가입자수, 탈퇴자수</h2>
							<p>날짜 입력</p>
					<input type="text" id="monthPicker" name="monthPicker">
   					<input type="text" id="monthPicker3" name="monthPicker3">
    				<button id="sendButton">Send</button>
						 <canvas id="userChart" width="800" height="400"></canvas>
					</div>
				</div>
				<div class="col-xl-8 mb-30">
					<div class="card-box height-100-p pd-20">
						<h2 class="h4 mb-20">사용자연령대</h2>
						<canvas id="myChart"></canvas>
					</div>
				</div>
			</div>


			       
<!-- 			        <p>가입자수, 탈퇴자수</p> -->
<!-- 					<div style="width: 800px; height: 400px; background-color: white; -->
<!-- 			        border-radius: 10px;"> -->
<%-- 			             <canvas id="userChart" width="800" height="400"></canvas> --%>
<!-- 			        </div> -->
					
<!-- 					<p>자녀 연령대</p>	         -->
<!-- 			        <div style="width: 800px; height: 400px; background-color: white;"> -->
<!-- 					차트가 그려질 부분 -->
<%-- 					<canvas id="AgeChart"></canvas> --%>
<!-- 					</div> -->
					
<!-- 				     <p>사용자 연령대</p> -->
<!-- 					<div style="width: 800px; height: 400px; background-color: white;"> -->
<!-- 					차트가 그려질 부분 -->
<%-- 					<canvas id="myChart"></canvas> --%>
<!-- 					</div> -->
					
<!-- 					  <div class="col-xl-8 mb-30"> -->
<!-- 					<div class="card-box height-100-p pd-20"> -->
<!-- 						<h2 class="h4 mb-20">Activity</h2> -->
<!-- 						<div id="chart"></div> -->
<!-- 					</div> -->
<!-- 				</div> -->
			        
	            <!-- 푸터 -->
				<%@include file="/WEB-INF/views/dashboard/dashboard_template/footer.jsp"%>
				</div>
				</div>
				
	        	</div>
	    	</div>
	    </div>
	
	<!-- js -->
	<script src="https://code.jquery.com/jquery-3.7.1.js"></script>
	<%@include file="/WEB-INF/views/dashboard/dashboard_template/javascript.jsp"%>
	
 <script src="https://cdnjs.cloudflare.com/ajax/libs/Chart.js/2.4.0/Chart.min.js"></script> 
	<!-- jquery UI -->

<!-- <script src="//code.jquery.com/jquery.min.js"></script> -->
<script src="//code.jquery.com/ui/1.11.4/jquery-ui.min.js"></script>

<script src="/sangsangjakka/resources/vendors/scripts/jquery.mtz.monthpicker.js"></script>
<script src="/sangsangjakka/resources/js/dashboard/chart.js"></script>

	<script src="/sangsangjakka/resources/plugins/apexcharts/apexcharts.min.js"></script>
	<script src="/sangsangjakka/resources/vendors/scripts/dashboard-chart.js"></script>
<script>
    // 성별
	var genderChartData = <%= request.getAttribute("jsonData") %>;
	drawGenderChart(genderChartData);
    // 자녀 연령대
	var childAgeChartData = <%= request.getAttribute("jsonAgeData") %>;
	drawChildAgeChart(childAgeChartData);
    // 사용자 연령대
	var userAgeChartData = <%= request.getAttribute("jsonUserAgeData") %>;
	drawUserAgeChart(userAgeChartData);

</script>

<script>
$(document).ready(function(){

    var currentYear = new Date().getFullYear();
    
    var options = {
        pattern: 'yyyy-mm',      // input 태그에 표시될 형식
        selectedYear: 2019,      // 선택할 연도
        startYear: 2020,         // 시작 연도
        finalYear: currentYear,  // 현재 년도를 마지막 연도로 설정
        monthNames: ['1월', '2월', '3월', '4월', '5월', '6월', '7월', '8월', '9월', '10월', '11월', '12월'], // 화면에 보여줄 월 이름
        openOnFocus: true,       // focus시에 달력이 보일지 유무
        disableMonths: []        // 비활성화할 월
    };


   
    $("#monthPicker").monthpicker(options);
    $("#monthPicker3").monthpicker(options);
  
    

  
    
    
    $('#sendButton').click(function() {
        var selectedMonth1 = $("#monthPicker").val();
        var selectedMonth2 = $("#monthPicker3").val();

        // 선택된 월 값을 서블릿 파일로 전송
        $.ajax({
            type: "POST",
            url: "/sangsangjakka/admin/dashboard/user/stats.do", // 여기에 서블릿 URL을 입력하세요
            data: {
                month1: selectedMonth1,
                month2: selectedMonth2
            },
            success: function(response) {
                // 서블릿에서 받은 응답 처리
            	  console.log("Data received successfully");
                  console.log(response);
                 

                  // 받은 데이터를 차트에 반영하는 로직
                  var context = document.getElementById('userChart').getContext('2d');
                  var myChart = new Chart(context, {
                	  type: 'bar',
                	    data: {
                	        labels: response.months,
                	        datasets: [
                	            {
                	                label: response.labels[0], // "가입자수"
                	                data: response.data.map(item => item[0]), // [date1, date2]
                	                backgroundColor: '#00C7E2',
                	                maxBarThickness: 30
                	            },
                	            {
                	                   label: response.labels[1], // "탈퇴자수"
                	                   data: response.data.map(item => item[1]), // 두 번째 요소만 추출 (탈퇴자 수)
                	                   backgroundColor: '#FF6384',
                	                   maxBarThickness: 30
                	               }
                	        ]
                
                      },
                      options: {
                          responsive: false,
                          maintainAspectRatio: false,
                          plugins: {
                              tooltip: {
                                  enabled: true,
                                  backgroundColor: '#000',
                                  padding: 10
                              },
                              legend: {
                                  display: true,
                                  position: 'bottom'
                              }
                          },
                          scales: {
                              x: {
                                  grid: {
                                      display: false,
                                  }
                              },
                              y: {
                                  min: 0,
                                  max: 50,
                                  border: {
                                      dash: [5, 5]
                                  },
                              }
                          }
                      }
                  });
            },
            error: function(xhr, status, error) {
                console.error("Error occurred while sending data: " + error);
            }
        });
    });

});


</script>

 <script>
    // getBarChartOptions 함수 사용
    var options = getBarChartOptions();

    // 차트 생성
    var chart = new ApexCharts(document.querySelector("#chart"), options);
    chart.render();
  </script>
  
	</body>
</html>