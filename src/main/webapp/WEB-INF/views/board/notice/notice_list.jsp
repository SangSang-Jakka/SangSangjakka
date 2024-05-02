<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
	<%@include file="/WEB-INF/views/template/asset.jsp"%>
	<link rel="stylesheet" href="/sangsangjakka/resources/css/board/notice/notice_list.css">
	<style>
	
	
	</style>
	</head>
	<body>
	
		<!-- header -->
		<%@include file="/WEB-INF/views/template/header.jsp"%>
	
		<section class="notice">
    		<div class="pageTitle">
          		<div>
              		<h3>공지사항</h3>
          		</div>
      		</div>

     
      
    	<!-- board list area -->
	      <div>
	          <div>
	              <table class="boardTable">
	                  <thead>
	                    <tr>
	                        <th scope="col" class="thTitle">상상자까 시스템 점검 안내 (4월 6일 00:00-06:00)</th>
	                        <th scope="col" class="thDate">2024-03-29</th>
	                        <th scope="col" class="thViews">24</th>
	                    </tr>
	                  </thead>
	                  <tbody>
	                  <tr class>
	                      <td>
	                        공지사항 세부내용 아직 미완성
	                      </td>
	                  </tr>
	                  </tbody>
	              </table>
	          </div>
	      </div>
	
	
	     
	   
	
	    <div class="paging">
	      <a href=""><<</a>
	      <a href=""><</a>
	      <a href="">1</a>
	      <a href="">2</a>
	      <a href="">3</a>
	      <a href="">4</a>
	      <a href="">5</a>
	      <a href="">6</a>
	      <a href="">7</a>
	      <a href="">8</a>
	      <a href="">9</a>
	      <a href="">10</a>
	      <a href="">></a>
	      <a href="">>></a>
	    </div>
	  
	  </section>
		
		<!-- footer -->
		<%@include file="/WEB-INF/views/template/footer.jsp"%>
		
	<script src="https://code.jquery.com/jquery-3.7.1.js"></script>
	<script>
	</script>
	</body>
</html>