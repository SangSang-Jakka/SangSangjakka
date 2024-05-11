<%@page import="com.jakka.model.DAOManager"%>
<%@page import="java.util.List"%>
<%@page import="com.jakka.model.dao.admin.AdminDAO"%>
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
	<%
	
	
	 AdminDAO year = DAOManager.getAdminDAO();
	 List<String> yearList = year.getYear();
	 System.out.println(yearList); //[23]
	
	
	%>
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



		<div class="bg-white pd-20 card-box mb-30">
					
					<select class="selectpicker form-control" id ="yearSelect" data-size="5" data-style="btn-outline-info" data-selected-text-format="count" name="year" onchange="sendSelectedOptionValue(this)">
            <c:forEach var="year" items="<%= yearList %>">
              <option value="${year}">20${year}</option>
            </c:forEach>
          </select>
       
      			<canvas id="memberChart"></canvas> 
				</div>
    			
    		<div class="row">
				<div class="col-xl-4 mb-30">
					<div class="card-box height-100-p pd-20">
						<h2 class="h4 mb-20">성별</h2>
						<canvas id="genderChart"></canvas>
					</div>
				</div>
			 <div class="col-xl-8 mb-30"> 
					<div class="card-box height-100-p pd-20"> 
						<h2 class="h4 mb-20">사용자연령대</h2> 
						<canvas id="myChart"></canvas>
					</div> 
				</div>
			</div>
			
			<div class="bg-white pd-20 card-box mb-30">
					<h4 class="h4 text-blue">자녀연령대</h4>
					<canvas id="AgeChart"></canvas>
			</div>
			
			
			
			
			
<!-- 			<div class="row"> -->
<!-- 				<div class="col-lg-4 col-md-6 col-sm-12 mb-30"> -->
<!-- 					<div class="card-box pd-30 pt-10 height-100-p"> -->
<!-- 						<h2 class="h4 mb-20">성별</h2> -->
<%-- 						<canvas id="genderChart"></canvas> --%>
						
<!-- 						</div> -->
<!-- 					</div> -->
				
<!-- 				<div class="col-lg-8 col-md-6 col-sm-12 mb-30"> -->
<!-- 					<div class="card-box pd-30 pt-10 height-100-p"> -->
<!-- 						<h2 class="h4 mb-20">사용자연령대</h2>  -->
<%-- 						<canvas id="myChart"></canvas> --%>
<!-- 					</div> -->
<!-- 				</div> -->
<!-- 			</div> -->
		
			
<!-- 			<div class="row"> -->
<!-- 				<div class="col-lg-7 col-md-12 col-sm-12 mb-30"> -->
<!-- 					<div class="card-box pd-30 height-100-p"> -->
<!-- 					<select class="selectpicker form-control" id ="yearSelect" data-size="5" data-style="btn-outline-info" data-selected-text-format="count" name="year" onchange="sendSelectedOptionValue(this)"> -->
<%--             <c:forEach var="year" items="<%= yearList %>"> --%>
<%--               <option value="${year}">20${year}</option> --%>
<%--             </c:forEach> --%>
<!--           </select> -->
       
<%--       			<canvas id="memberChart"></canvas> --%>
<!-- 					</div> -->
<!-- 				</div> -->
<!-- 				<div class="col-lg-5 col-md-12 col-sm-12 mb-30"> -->
<!-- 					<div class="card-box pd-30 height-100-p"> -->
<!-- 						<h4 class="h4 text-blue">자녀연령대</h4> -->
<%-- 					<canvas id="AgeChart"></canvas> --%>
<!-- 					</div> -->
<!-- 				</div> -->
<!-- 			</div> -->
			
			
<!-- 			<div class="row"> -->
<!-- 				<div class="col-xl-4 mb-30"> -->
<!-- 					<div class="card-box height-100-p pd-20"> -->
<!-- 						<h4 class="h4 text-blue">자녀연령대</h4> -->
<%-- 					<canvas id="AgeChart"></canvas> --%>
<!-- 					</div> -->
<!-- 				</div> -->
<!-- 			 <div class="col-xl-8 mb-30">  -->
<!-- 					<div class="card-box height-100-p pd-20">  -->
						
<!-- 					<select class="selectpicker form-control" id ="yearSelect" data-size="5" data-style="btn-outline-info" data-selected-text-format="count" name="year" onchange="sendSelectedOptionValue(this)"> -->
<%--             <c:forEach var="year" items="<%= yearList %>"> --%>
<%--               <option value="${year}">20${year}</option> --%>
<%--             </c:forEach> --%>
<!--           </select> -->
       
<%--       			<canvas id="memberChart"></canvas> --%>
<!-- 					</div>  -->
<!-- 				</div> -->
<!-- 			</div> -->
			
<!-- 			<div class="col-xl-4 mb-30"> -->
<!-- 					<div class="card-box height-100-p pd-20"> -->
<!-- 						<h2 class="h4 mb-20">가입자수, 탈퇴자수</h2> -->
<!-- 							<p>날짜 입력</p> -->
<!-- 					<input type="text" id="monthPicker" name="monthPicker"> -->
<!--    					<input type="text" id="monthPicker3" name="monthPicker3"> -->
<!--     				<button id="sendButton">Send</button> -->
<%-- 						 <canvas id="userChart" width="800" height="400"></canvas> --%>
<!-- 					</div> -->
<!-- 				</div> -->
			
				
				


<!-- <div class="row">  -->
<!--  <div class="col-xl-12 mb-30"> -->
<!--     <div class="card-box height-100-p pd-20"> -->
<!--       <div class="col-md-4 col-sm-12"> -->
<!--         <div class="form-group"> -->
<!--           <select class="selectpicker form-control" id ="yearSelect" data-size="5" data-style="btn-outline-info" data-selected-text-format="count" name="year" onchange="sendSelectedOptionValue(this)"> -->
<%--             <c:forEach var="year" items="<%= yearList %>"> --%>
<%--               <option value="${year}">20${year}</option> --%>
<%--             </c:forEach> --%>
<!--           </select> -->
<!--         </div> -->
<!--       </div> -->
<%--       <canvas id="memberChart"></canvas> --%>
<!--     </div> -->
<!--   </div> -->
  

<!--   </div> -->
			     
			   

			        
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
    // 현재 연도 가져오기
    var currentYear = new Date().getFullYear();
    var currentYearShort = currentYear.toString().slice(-2);
    console.log(currentYearShort);

    $("#yearSelect").val(currentYearShort);
    // 현재 연도 차트 초기 렌더링
    renderChart(currentYear.toString());

    function renderChart(year) {
      $.ajax({
        type: "POST",
        url: "/sangsangjakka/admin/dashboard/user/stats.do",
        data: { selectedValue: year },
        success: function(response) {
          console.log('Server response:', response);

          var labels = [];
          var joinData = [];
          var withdrawData = [];

          // JSON 데이터 파싱
          for (var i = 0; i < response.length; i++) {
            var monthData = response[i];
            labels.push(monthData.label);
            joinData.push(monthData.joinCount);
            withdrawData.push(monthData.withdrawCount);
          }

          var context = document.getElementById('memberChart').getContext('2d');
          if (window.myChart) {
            window.myChart.destroy(); // 기존 차트 제거
          }

          window.myChart = new Chart(context, {
            type: 'bar',
            data: {
              labels: labels,
              datasets: [{
                label: '가입자 수',
                data: joinData,
                backgroundColor: 'rgba(54, 162, 235, 0.2)',
                borderColor: 'rgba(54, 162, 235, 1)',
                borderWidth: 1
              }, {
                label: '탈퇴자 수',
                data: withdrawData,
                backgroundColor: 'rgba(255, 99, 132, 0.2)',
                borderColor: 'rgba(255, 99, 132, 1)',
                borderWidth: 1
              }]
            },
            options: {
              scales: {
                yAxes: [{
                  ticks: {
                    beginAtZero: true
                  }
                }]
              },
              hover: {
                mode: null // 마우스 호버 효과 비활성화
              },
              
              events: [] // 이벤트 핸들러 비활성화
            }
          });
        },
        error: function(xhr, status, error) {
          console.error('AJAX request error:', error);
        }
      });
    }

    function sendSelectedOptionValue(selectElement) {
      var selectedOption = selectElement.options[selectElement.selectedIndex];
      if (selectedOption) {
        var selectedValue = selectedOption.value;
        console.log(selectedValue);
        renderChart(selectedValue);
      } else {
        console.log('No option selected');
      }
    }
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