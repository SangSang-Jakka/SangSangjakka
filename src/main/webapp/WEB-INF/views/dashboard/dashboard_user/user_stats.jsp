<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
	<%@include file="/WEB-INF/views/dashboard/dashboard_template/asset.jsp"%>
	<link rel="stylesheet" type="text/css" href="/sangsangjakka/resources/vendors/styles/boardStatistics.css">
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

<!-- 				날짜 구하기 -->
					<input type="text" id="monthPicker" name="monthPicker">
   					<input type="text" id="monthPicker3" name="monthPicker3">
    				<button id="sendButton">Send</button>
			      
				<div style="width: 400px; height: 400px; background-color: white;
			        border-radius: 10px;">
			             <canvas id="userChart" width="400" height="400"></canvas>
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
	  <script src="https://cdnjs.cloudflare.com/ajax/libs/Chart.js/2.4.0/Chart.min.js"></script>
	<!-- jquery UI -->

<script src="//code.jquery.com/jquery.min.js"></script>
<script src="//code.jquery.com/ui/1.11.4/jquery-ui.min.js"></script>
<!-- // jquery UI -->
<script src="/sangsangjakka/resources/vendors/scripts/jquery.mtz.monthpicker.js"></script>

	
	<script type="text/javascript">
        // 서버에서 받은 데이터를 사용하여 차트를 그립니다.
        var chartData = <%= request.getAttribute("jsonData") %>;
        console.log(chartData);
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


    // 방법1) options 따로 지정
    $("#monthPicker").monthpicker(options);
    $("#monthPicker3").monthpicker(options);
  
    

     // 선택한 월 값을 변수에 저장
   // var selectedMonth1 = $("#monthPicker").val();
    //var selectedMonth2 = $("#monthPicker2").val();
    
    
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
                	        labels: response.month,
                	        datasets: [
                	            {
                	                label: response.labels[0], // "가입자수"
                	                data: response.data, // [date1, date2]
                	                backgroundColor: '#00C7E2',
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


  
	</body>
</html>