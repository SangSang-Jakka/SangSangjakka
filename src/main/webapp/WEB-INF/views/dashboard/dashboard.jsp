<%@page import="com.jakka.model.dto.admin.AdminDTO"%>
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
</head>

<%
	
	
	 AdminDAO year = DAOManager.getAdminDAO();
	 List<String> yearList = year.getYear();
	 System.out.println(yearList); //[23]
			 
	 Date nows = new Date();

	    // 날짜 형식 지정
	 SimpleDateFormat formatters = new SimpleDateFormat("yyyy/MM");
	  String currentYear = formatters.format(nows);
	  System.out.println("년도: " + currentYear);
	    
	  //AdminDAO current = DAOManager.getAdminDAO();
	  List<AdminDTO>inflowCount = year.getInflowCountData(currentYear);
	  System.out.println("안돼?: " + inflowCount);
	
	  
	
	%>
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

		<!-- 배너 -->
        <%@include file="/WEB-INF/views/dashboard/dashboard_template/admin_dashboard_banner.jsp"%>


		
		<div class="bg-white pd-20 card-box mb-30">
					
					<select class="selectpicker form-control" id ="yearSelect" data-size="5" data-style="btn-outline-info" data-selected-text-format="count" name="year" onchange="sendSelectedOptionValue(this)">
            <c:forEach var="year" items="<%= yearList %>">
              <option value="${year}">20${year}</option>
            </c:forEach>
          </select>
       
      			<canvas id="memberChart"></canvas> 
				</div>
             
<!--                 <div class="bg-white pd-20 card-box mb-30">  -->
<!--                 <div class="col-md-4 col-sm-12"> -->
<!-- <!--                  <div class="col-lg-4 col-md-6 col-sm-12 mb-30">  --> 
<!-- <!-- 					<div class="card-box pd-30 pt-10 height-100-p">  --> 
                    
                   
<!-- 			    <div class="browser-visits"> -->
<!-- 			    <table class="table table-striped"> -->
						


<!-- 					  <tbody> -->
<!-- 					    <tr> -->
<!-- 					      	<th scope="col">#</th>	 -->
<!-- 							<th scope="col">유입경로</th> -->
<!-- 							<th scope="col">선택 수</th> -->
					      
<!-- 					    </tr> -->
<!-- 					  </tbody> -->
					  
<%-- 					    <c:forEach items="${inflowCount}" var="admin" varStatus="loop"> --%>
<!-- 					      <tr> -->
<%-- 					        <td>${loop.index + 1}</td> --%>
<%-- 					        <td>${admin.inflowname}</td> --%>
<%-- 					        <td>${admin.inflowCount}</td> --%>
					       
<!-- 					      </tr> -->
<%-- 					    </c:forEach> --%>
					  
<!-- 					</table> -->
<!-- 				</div> -->
				
<%-- <%-- 				<canvas id="memberChart"></canvas> --%> 
			
<!--                 </div> -->
<!--                 </div> -->
<!--                 <div class="col-lg-4 col-md-6 col-sm-12 mb-30"> -->
<!-- 					<div class="card-box pd-30 pt-10 height-100-p"> -->
						
<!-- 						<div class="browser-visits2"> -->
							 
<!--                     <div class="form-group has-success"> -->
<!-- 				<label class="form-control-label"> DATE</label> -->
<%-- 				<input type="text" id="monthPicker2" name="monthPicker2" class="form-control form-control-success" value="<%= currentYear%> "> --%>
<!-- 			    <h2 class="mb-30 h4">Browser Visit</h2> -->
<!-- 			    <div class="browser-visits siwon"> -->
<!-- 			    <table class="table table-striped"> -->
						


<!-- 					  <tbody> -->
<!-- 					    <tr> -->
<!-- 					      	<th scope="col">#</th>	 -->
<!-- 							<th scope="col">유입경로</th> -->
<!-- 							<th scope="col">선택 수</th> -->
					      
<!-- 					    </tr> -->
<!-- 					  </tbody> -->
					  
<%-- 					    <c:forEach items="${inflowCount}" var="admin" varStatus="loop"> --%>
<!-- 					      <tr> -->
<%-- 					        <td>${loop.index + 1}</td> --%>
<%-- 					        <td>${admin.inflowname}</td> --%>
<%-- 					        <td>${admin.inflowCount}</td> --%>
					       
<!-- 					      </tr> -->
<%-- 					    </c:forEach> --%>
					  
<!-- 					</table> -->
<!-- 				</div> -->
			    

<!-- 			    </div> -->
<!-- 						</div> -->
<!-- 					</div> -->
<!-- 				</div> -->
				
<!-- 				 <div class="form-group has-success"> -->
<!-- 				<label class="form-control-label"> DATE</label> -->
<%-- 				<input type="text" id="monthPicker" name="monthPicker" class="form-control form-control-success" value="<%= currentYear%> "> --%>
<!-- 			    </div> -->
				
				<div class="row">
				<div class="col-lg-4 col-md-6 col-sm-12 mb-30">
					<div class="card-box pd-30 pt-10 height-100-p">
						<h2 class="mb-30 h4">Browser Visit</h2>
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
                
               
                
<!--                 <div class="row"> -->
<!--                     <div class="col-md-6 mb-30"> -->
<!--                         <div class="pd-20 card-box height-100-p"> -->
<!--                             <h4 class="h4 text-blue">Pie Chart</h4> -->
<!--                             <div id="chart8"></div> -->
<!--                         </div> -->
<!--                     </div> -->
<!--                     <div class="col-md-6 mb-30"> -->
<!--                         <div class="pd-20 card-box height-100-p"> -->
<!--                             <h4 class="h4 text-blue">Radial Bar Chart</h4> -->
<!--                             <div id="chart9"></div> -->
<!--                         </div> -->
<!--                     </div> -->
<!--                 </div> -->
            </div>
            
            <!-- 푸터 -->
			<%@include file="/WEB-INF/views/dashboard/dashboard_template/footer.jsp"%>
        </div>
    </div>
    
    <!-- js -->
	
	<%@include file="/WEB-INF/views/dashboard/dashboard_template/javascript.jsp"%> 
	<script src="https://cdnjs.cloudflare.com/ajax/libs/Chart.js/2.4.0/Chart.min.js"></script> 
	
	<!-- jquery UI -->
<!-- 	<script src="//code.jquery.com/jquery.min.js"></script> -->
<!-- 	<script src="//code.jquery.com/ui/1.11.4/jquery-ui.min.js"></script> -->
	<script src="/sangsangjakka/resources/vendors/scripts/jquery.mtz.monthpicker.js"></script>


<script>
	$(document).ready(function() {
		  var options = {
		    pattern: 'yyyy/mm', // input태그에 표시될 형식
		    selectedYear: <%= currentYear %>, // 선택할 연도
		    startYear: 20<%= yearList.get(0) %>, // 시작연도
		    finalYear: 20<%= yearList.get(yearList.size() - 1) %>, // 마지막연도
		    monthNames: ['1월', '2월', '3월', '4월', '5월', '6월', '7월', '8월', '9월', '10월', '11월', '12월'], // 화면에 보여줄 월이름
		    openOnFocus: true, // focus시에 달력이 보일지 유무
		    disableMonths: [] // 월 비활성화
		  };

		  // 방법1) options 따로 지정
		  $("#monthPicker").monthpicker(options);
		  $("#monthPicker2").monthpicker(options);
		 
	
	
	
		

		  // 입력 필드 값 변경 이벤트 핸들러 등록
		  $("#monthPicker").on("change", function() {
		    var newValue = $(this).val();
		    console.log("새로운 값:", newValue);
		    
		 // Ajax POST 요청 전송
		    $.ajax({
		          type: "POST",
		          url: "/sangsangjakka/admin/dashboard.do", // 서버 측 스크립트 경로
		          data: { selectedMonth: newValue },
		          success: function(response) {
		        	  var inflowCountData = response; 
		              
		              // 받아온 데이터를 콘솔에 출력하여 확인
		              console.log("서버 응답:", inflowCountData);
		              // Browser Visit 제목을 담을 변수
		              //var browserVisitTitle = '<h2 class="mb-30 h4">'+ response[0].registrationMonth + '</h2>'; // 첫 번째 항목의 registrationMonth 값을 가져와 제목에 추가

		              // HTML을 저장할 변수
		              var html = ''; // 제목을 먼저 추가

		              // Browser Visit div 요소를 생성하여 HTML 변수에 추가
		              
						html += '<tbody>'	
						html += '<tr>'	
						html += '<th scope="col">#</th>'	
						html += '<th scope="col">유입경로</th>'	
						html += '<th scope="col">선택 수</th>'	
							
						html += '</tr>'	
						html += '</body>'
		              
		              var rank =1;
		              
		              // JSON 데이터를 반복하여 HTML 생성
		              response.forEach(function(item) {
 		         

							
							
						html += '<tr>'	
						html += '<td>' + rank + '</td>'	
						html += '<td>' + item.inflowname + '</td>'	
						html += '<td>' + item.inflowCount + '</td>'	
							
						html += '</tr>'
					
		                  
		                  rank++;
		              });
		              
		              // 생성된 HTML을 적용할 요소에 추가
//		            
		              $('.bg-white.pd-20.card-box.mb-30 table tbody').html(html);

		              
		           
		          },
		          error: function(xhr, status, error) {
		            console.error("Ajax 요청 실패:", error);
		          }
		        });
		      });
		  
		  
		  
		  
		  $("#monthPicker2").on("change", function() {
			    var newValue = $(this).val();
			    console.log("새로운 값:", newValue);
			    
			 // Ajax POST 요청 전송
			    $.ajax({
			          type: "POST",
			          url: "/sangsangjakka/admin/dashboard.do", // 서버 측 스크립트 경로
			          data: { selectedMonth: newValue },
			          success: function(response) {
			        	  var inflowCountData = response; 
			              
			              // 받아온 데이터를 콘솔에 출력하여 확인
			              console.log("서버 응답:", inflowCountData);
			              // Browser Visit 제목을 담을 변수
			              //var browserVisitTitle = '<h2 class="mb-30 h4">'+ response[0].registrationMonth + '</h2>'; // 첫 번째 항목의 registrationMonth 값을 가져와 제목에 추가

			              // HTML을 저장할 변수
			              var html = ''; // 제목을 먼저 추가

			              // Browser Visit div 요소를 생성하여 HTML 변수에 추가
			              
							html += '<tbody>'	
							html += '<tr>'	
							html += '<th scope="col">#</th>'	
							html += '<th scope="col">유입경로</th>'	
							html += '<th scope="col">선택 수</th>'	
								
							html += '</tr>'	
							html += '</body>'
			              
			              var rank =1;
			              
			              // JSON 데이터를 반복하여 HTML 생성
			              response.forEach(function(item) {
	 		         

								
								
							html += '<tr>'	
							html += '<td>' + rank + '</td>'	
							html += '<td>' + item.inflowname + '</td>'	
							html += '<td>' + item.inflowCount + '</td>'	
								
							html += '</tr>'
						
			                  
			                  rank++;
			              });
			              
			              // 생성된 HTML을 적용할 요소에 추가
//			            
			             // $('.col-lg-4 col-md-6 col-sm-12 mb-30 table tbody').html(html);
			              $('.browser-visits.siwon table tbody').html(html);
			              
			           
			          },
			          error: function(xhr, status, error) {
			            console.error("Ajax 요청 실패:", error);
			          }
			        });
			      });
		    });
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
          responsive: true,
          maintainAspectRatio: false,
          
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