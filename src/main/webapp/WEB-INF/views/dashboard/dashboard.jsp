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
                    <h4 class="h4 text-blue">line Chart</h4>
                    <div class="col-md-4 col-sm-12">
			<div class="form-group">
				
<!-- 				<input type="text" id="monthPicker" name="monthPicker" class="form-control"> -->
			</div>
		</div>
                    
                   
                </div>
                <div class="bg-white pd-20 card-box mb-30">
                <div class="col-md-4 col-sm-12">
                    <h4 class="h4 text-blue">Area Chart</h4>
                    <div class="form-group has-success">
				<label class="form-control-label"> DATE</label>
				<input type="text" id="monthPicker" name="monthPicker" class="form-control form-control-success" value="<%= currentYear%> ">
				</div>
			</div>
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
	<!-- jquery UI -->
	<script src="//code.jquery.com/jquery.min.js"></script>
	<script src="//code.jquery.com/ui/1.11.4/jquery-ui.min.js"></script>
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
		        console.log("서버 응답:", response);
		        // 추가 작업 수행
		      },
		      error: function(xhr, status, error) {
		        console.error("Ajax 요청 실패:", error);
		      }
		    });  
		    
		    
		    
		    
		  });
		});

	
	</script>

<script>
 
</script>

</body>
</html>