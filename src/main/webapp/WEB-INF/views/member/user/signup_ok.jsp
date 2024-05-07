<!-- bookmaking_fin.jsp -->
<%@page import="com.jakka.model.dto.user.UserDTO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
    <%@include file="/WEB-INF/views/template/asset.jsp"%>
    <link rel="stylesheet" href="/sangsangjakka/resources/css/board/bookmaking/bookmaking_fin.css">
	<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.0/jquery.min.js"></script>
	<script src="https://kit.fontawesome.com/e075b9b5dc.js"	crossorigin="anonymous"></script>
    <script src="/sangsangjakka/resources/js/bookmaking/bookmaking_fin.js"></script>
</head>
<style>
	.signUpIdWrap{
		display: flex;
    	justify-content: center;
	}
	.signUpId {
		font-size: 20px;
		margin-bottom : 30px;
	}
	.signUpId span {
		font-weight:bold;
		font-size: 25px;
	}
</style>
<body>

	<!-- header -->
	<%@include file="/WEB-INF/views/template/header.jsp"%>

	<div id="makedbookGrid">
		<div class="makedbookGridLeft"></div>
		<div class="makedbookGridCenter">
			<h2>회원가입을 축하합니다!!</h2>
			<%-- <div class=signUpId>아이디: <%= request.getAttribute("newUserId") %> </div> --%>
			<div class=signUpIdWrap>
				<div class=signUpId>
					아이디: <span><%= request.getAttribute("newUserId") %></span>
				</div>
			</div>
		</div>
		<div class="makedbookGridRight"></div>
	</div>

	<!-- footer -->
	<%@include file="/WEB-INF/views/template/footer.jsp"%>

</body>


</html>