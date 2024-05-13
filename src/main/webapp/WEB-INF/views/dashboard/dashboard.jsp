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
				
				
			
             

				
				<div class="row">
				
				<div class="col-lg-4 col-md-6 col-sm-12 mb-30">
					<div class="card-box pd-30 pt-10 height-100-p">
						
<!-- 					<div class="pd-20 card-box mb-30"> -->
<!-- 					<div class="clearfix mb-20"> -->
						
						<div class="form-group has-success">
				<label class="form-control-label"> DATE</label>
				<input type="text" id="monthPicker2" name="monthPicker2" class="form-control form-control-success">
				
			    </div>
						
<!-- 					</div> -->
					<table class="table table-striped">
						<thead>
							<tr>
								<th scope="col">#</th>
								<th scope="col">유입경로</th>
								<th scope="col">선택수</th>
								
							</tr>
						</thead>
						<tbody id="tableBody">
							
						</tbody>
					</table>
					
<!-- 				</div> -->
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

$(document).ready(function() {
    // 현재 연도와 월 가져오기
    var currentDate = new Date();
    var currentYear = currentDate.getFullYear();
    var currentMonth = currentDate.getMonth() + 1; // 월은 0부터 시작하므로 1을 더해줍니다.

    // 월 값이 한 자리 수인 경우 앞에 0을 붙여 두 자리로 만듭니다.
    var currentMonthStr = currentMonth < 10 ? '0' + currentMonth : currentMonth;

    // 현재 연도와 월을 YYYY/MM 형식으로 합쳐줍니다.
    var currentDateString = currentYear + '/' + currentMonthStr;

    var options = {
        pattern: 'yyyy/mm', // input태그에 표시될 형식
        selectedYear: currentYear, // 선택할 연도
        selectedMonth: currentMonthStr, // 선택할 월
        startYear: 20<%= yearList.get(0) %>, // 시작연도
        finalYear: 20<%= yearList.get(yearList.size() - 1) %>, // 마지막연도
        monthNames: ['1월', '2월', '3월', '4월', '5월', '6월', '7월', '8월', '9월', '10월', '11월', '12월'], // 화면에 보여줄 월이름
        openOnFocus: true, // focus시에 달력이 보일지 유무
        disableMonths: [] // 월 비활성화
    };

    
    // 월 선택 기능을 위한 monthpicker 초기화
    $("#monthPicker2").monthpicker(options);
    
    $("#monthPicker2").val(currentDateString);

    // 페이지 로드 시 현재 연도와 월의 데이터를 불러옵니다.
    loadData(currentDateString);

    // 월 선택이 변경될 때마다 데이터를 불러옵니다.
    $("#monthPicker2").on("change", function() {
        var newValue = $(this).val();
        console.log("새로운 값:", newValue);
        loadData(newValue);
    });

    // 데이터를 불러와서 화면에 표시하는 함수
    function loadData(selectedMonth) {
        $.ajax({
            type: "POST",
            url: "/sangsangjakka/admin/dashboard.do", // 서버 측 스크립트 경로
            data: { selectedMonth: selectedMonth },
            success: function(response) {
                var rank = 1;
                console.log("들어왔나? :", response)
                var tbody = $('#tableBody');
                tbody.empty(); // 기존 데이터를 비웁니다.
                response.forEach(function(book) {
                    var row = '<tr>' +
                        '<td class="table-plus">' + rank + '</td>' +
                        '<td>' + book.inflowname + '</td>' +
                        '<td>' + book.inflowCount + '</td>' +
                        '</tr>';
                    rank++;
                    tbody.append(row);
                });
            },
            error: function(xhr, status, error, response) {
                console.error("Ajax 요청 실패:", error);
            }
        });
    }
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