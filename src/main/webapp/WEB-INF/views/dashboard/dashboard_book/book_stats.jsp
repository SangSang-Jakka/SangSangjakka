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
	
	               
	                
	                <div class="row">
				<div class="col-lg-4 col-md-6 col-sm-12 mb-30">
					<div class="card-box pd-30 pt-10 height-100-p">
						<h2 class="mb-30 h4">최대 공유 동화책</h2>
						<div class="form-group has-success">
				<label class="form-control-label"> DATE</label>
				<input type="text" id="monthPicker2" name="monthPicker2" class="form-control form-control-success">
			    </div>
						<div class="browser-visits">
							<table class="table table-striped">
							<tbody>
							</tbody>
							</table>
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
	

	 <script>
    // getBarChartOptions 함수 사용
    var options = getBarChartOptions();

    // 차트 생성
    var chart = new ApexCharts(document.querySelector("#chart"), options);
    chart.render();
  </script>
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
		  
		  $("#monthPicker2").monthpicker(options);
	
		  $("#monthPicker2").on("change", function() {
			    var newValue = $(this).val();
			    console.log("새로운 값:", newValue);
			    
			 // Ajax POST 요청 전송
			    $.ajax({
			          type: "POST",
			          url: "/sangsangjakka/admin/dashboard/book/stats.do", // 서버 측 스크립트 경로
			          data: { selectedMonth: newValue },
			          success: function(response) {
			        	  var shareCount = response; 
			              
			              // 받아온 데이터를 콘솔에 출력하여 확인
			              console.log("서버 응답:", shareCount);
			              // Browser Visit 제목을 담을 변수
			              //var browserVisitTitle = '<h2 class="mb-30 h4">'+ response[0].registrationMonth + '</h2>'; // 첫 번째 항목의 registrationMonth 값을 가져와 제목에 추가

			              // HTML을 저장할 변수
			              var html = ''; // 제목을 먼저 추가

			              // Browser Visit div 요소를 생성하여 HTML 변수에 추가
			              
							html += '<tbody>'	
							html += '<tr>'	
							html += '<th scope="col">#</th>'	
							html += '<th scope="col">제목</th>'	
							html += '<th scope="col">공유 수</th>'	
								
							html += '</tr>'	
							html += '</body>'
			              
			              var rank =1;
			              
			              // JSON 데이터를 반복하여 HTML 생성
			              response.forEach(function(item) {
	 		         

								
								
							html += '<tr>'	
							html += '<td>' + rank + '</td>'	
							html += '<td>' + item.bookTitle + '</td>'	
							html += '<td>' + item.shareCnt + '</td>'	
								
							html += '</tr>'
						
			                  
			                  rank++;
			              });
			              
			              // 생성된 HTML을 적용할 요소에 추가
//			            
			             // $('.bg-white.pd-20.card-box.mb-30 table tbody').html(html);
			              $('.browser-visits table tbody').html(html);
			              
			           
			          },
			          error: function(xhr, status, error) {
			            console.error("Ajax 요청 실패:", error);
			          }
			        });
			      });
	
	
	 });
  </script>
	
	</body>
</html>