<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<%@include file="/WEB-INF/views/template/asset.jsp"%>
<link rel="stylesheet" href="/sangsangjakka/resources/css/screens/findIdAfter.css">
	<style>
	
	
	</style>
</head>
<body>

	<div class="container">
        <!-- Heading -->
        <%@include file="/WEB-INF/views/member/user/user_template/heading.jsp"%>
        
        
        <div class="findUserIdSuccess">
            <div class="findUserId">
                찾으신 고객님의 아이디는
                <span>'hong1234'</span>
                입니다.
            </div>
            <div class="fingUserRegdate">
                가입일: <span>2024.04.29</span>
            </div>
        </div>
 
        <div class="successBtn">
            <div class="login"><a href="/sangsangjakka/user/login.do">로그인</a></div>
            <div class="changePw"><a href="/sangsangjakka/user/find_pw.do">비밀번호 재설정</a></div>
        </div>


        
        
        
    </div>

<script src="https://kit.fontawesome.com/4b3c3b390b.js" crossorigin="anonymous"></script>
<script src="https://code.jquery.com/jquery-3.7.1.js"></script>
</body>
</html>