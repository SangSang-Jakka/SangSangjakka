<%@page import="com.jakka.model.dto.admin.AdminDTO"%>
<%@page import="java.util.List"%>
<%@page import="com.jakka.model.dao.admin.AdminDAO"%>
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
	
	<%
	
	
	 AdminDAO year = DAOManager.getAdminDAO();
	 List<String> yearList = year.getYear();
	 System.out.println("나오는거 : " + yearList); //[23]
			 
	 Date nows = new Date();

	    // 날짜 형식 지정
	 SimpleDateFormat formatters = new SimpleDateFormat("yyyy/MM");
	  String currentYear = formatters.format(nows);
	  System.out.println("년도: " + currentYear);
	    
	  //AdminDAO current = DAOManager.getAdminDAO();
	  List<AdminDTO>inflowCount = year.getInflowCountData(currentYear);
	  System.out.println("안돼?: " + inflowCount);
	
	
	%>
	</head>
	<body>
	
		<!-- 헤더 -->
		<%@include file="/WEB-INF/views/dashboard/dashboard_template/header.jsp"%>
	
	    <!-- 왼쪽 사이드바 -->
		<%@include file="/WEB-INF/views/dashboard/dashboard_template/left_sidebar.jsp"%>
	
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
        <%@include file="/WEB-INF/views/dashboard/dashboard_template/book_banner.jsp"%>
        
        
        	<div class="form-group has-success">
				<label class="form-control-label"> DATE</label>
				<input type="text" id="monthPicker2" name="monthPicker2" class="form-control form-control-success">
				
			    </div>
        <!-- Striped table start -->
				<div class="pd-20 card-box mb-30">
					<div class="clearfix mb-20">
						<div class="pull-left">
							<h4 class="text-blue h4">최다 공유 동화책</h4>	
						</div>
						
					</div>
					<table class="table table-striped">
						<thead>
							<tr>
								<th scope="col">#</th>
								<th scope="col">제목</th>
								<th scope="col">정보</th>
								<th scope="col">등록날짜</th>
								<th scope="col">공유수</th>
							</tr>
						</thead>
						<tbody id="tableBody">
							
						</tbody>
					</table>
					
				</div>
				<!-- Striped table End -->
				<div class="bg-white pd-20 card-box mb-30">
					
					<select class="selectpicker form-control" id ="yearSelect" data-size="5" data-style="btn-outline-info" data-selected-text-format="count" name="year" onchange="sendSelectedOptionValue(this)">
            <c:forEach var="year" items="<%= yearList %>">
              <option value="${year}">20${year}</option>
            </c:forEach>
          </select>
       
      			<canvas id="lineChart"></canvas> 
				</div>

<!--  조건별 조회 -->
					
           
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
	
	
	<script src="https://cdnjs.cloudflare.com/ajax/libs/Chart.js/2.4.0/Chart.min.js"></script> 
	
	<!-- jquery UI -->
<!-- 	<script src="//code.jquery.com/jquery.min.js"></script> -->
<!-- 	<script src="//code.jquery.com/ui/1.11.4/jquery-ui.min.js"></script> -->

	
	

	

<script>
$(document).ready(function() {
	var currentDate = new Date();
    var currentYear = currentDate.getFullYear();
    var currentMonth = currentDate.getMonth() + 1; // 월은 0부터 시작하므로 1을 더해줍니다.

    // 현재 연도와 월을 합친 문자열 생성
    var currentDateString = currentYear + '/' + (currentMonth < 10 ? '0' : '') + currentMonth;
    console.log(currentDateString);

    $("#monthPicker2").val(currentDateString);
    // 페이지 로딩시 현재 연도의 데이터를 보여주는 함수 호출
     renderData(currentDateString);

    var options = {
        pattern: 'yyyy/mm', // input태그에 표시될 형식
        selectedYear: currentDateString, // 선택할 연도
        startYear: 20<%= yearList.get(0) %>, // 시작연도
        finalYear: 20<%= yearList.get(yearList.size() - 1) %>, // 마지막연도
        monthNames: ['1월', '2월', '3월', '4월', '5월', '6월', '7월', '8월', '9월', '10월', '11월', '12월'], // 화면에 보여줄 월이름
        openOnFocus: true, // focus시에 달력이 보일지 유무
        disableMonths: [], // 월 비활성화
       
    };

    // monthPicker2 초기화
    $("#monthPicker2").monthpicker(options);

    // monthPicker2의 change 이벤트 핸들러
    $("#monthPicker2").on("change", function() {
        var newValue = $(this).val();
        console.log("새로운 값:", newValue);
        renderData(newValue);
    });
});

// 데이터를 가져와 표시하는 함수
function renderData(selectedMonth) {
    // Ajax POST 요청 전송
    $.ajax({
        type: "POST",
        url: "/sangsangjakka/admin/dashboard/book/stats.do", // 서버 측 스크립트 경로
        data: { selectedMonth: selectedMonth },
        success: function(response) {
            var rank = 1;
            console.log("들어왔나? :", response)
            var tbody = $('#tableBody');
            tbody.empty(); // 기존 데이터를 비웁니다.
            response.forEach(function(book) {
                var row = '<tr>' +
                    '<td class="table-plus">' + rank + '</td>' +
                    '<td><a href="/sangsangjakka/admin/dashboard/bookshare/manageview.do?seq=' + book.bookSeq + '">' + book.bookTitle + '</a></td>' +
                    '<td>' + book.bookInfo + '</td>' +
                    '<td>' + book.bookRegdate + '</td>' +
                    '<td>' + book.shareCnt + '</td>' +
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

	
  </script>	
  
  

<script>
// 현재 연도 가져오기
var currentYear = new Date().getFullYear();
var currentYearShort = currentYear.toString().slice(-2);
console.log(currentYearShort);
console.log("이거 뭐지" , currentYear);

$("#yearSelect").val(currentYearShort);
// 현재 연도 차트 초기 렌더링
renderChart(currentYear);

resp.setContentType("application/json");
resp.getWriter().write(jsonmakeBookData);

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