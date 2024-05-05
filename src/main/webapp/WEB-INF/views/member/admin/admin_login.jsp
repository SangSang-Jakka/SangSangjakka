<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html>
	<head>
	<!-- Basic Page Info -->
	<meta charset="utf-8">
	<title>상상자까</title>

	<!-- Mobile Specific Metas -->
	<meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">

	<link rel="icon" href="/sangsangjakka/resources/img/favicon.png">

	<!-- Google Font -->
	<link href="https://fonts.googleapis.com/css2?family=Inter:wght@300;400;500;600;700;800&display=swap" rel="stylesheet">
	<!-- CSS -->
	<link rel="stylesheet" type="text/css" href="/sangsangjakka/resources/vendors/styles/core.css">
	<link rel="stylesheet" type="text/css" href="/sangsangjakka/resources/vendors/styles/icon-font.min.css">
	<link rel="stylesheet" type="text/css" href="/sangsangjakka/resources/vendors/styles/style.css">

	<!-- Global site tag (gtag.js) - Google Analytics -->
	<script async src="https://www.googletagmanager.com/gtag/js?id=UA-119386393-1"></script>
	<script>
		window.dataLayer = window.dataLayer || [];
		function gtag(){dataLayer.push(arguments);}
		gtag('js', new Date());

		gtag('config', 'UA-119386393-1');
	</script>
	</head>
	<body>
	
		<body class="login-page">
	<div class="login-header box-shadow">
		<div class="container-fluid d-flex justify-content-between align-items-center">
			<div class="brand-logo">
				<img src="/sangsangjakka/resources/vendors/images/admin_logo.png" alt="">
			</div>
			<div class="login-menu">
				<ul>
					<li><a href="/sangsangjakka/index.do">Back</a></li>
				</ul>
			</div>
		</div>
	</div>
	<div class="login-wrap d-flex align-items-center flex-wrap justify-content-center">
		<div class="container">
			<div class="row align-items-center">
				<div class="col-md-6 col-lg-7">
					<img src="/sangsangjakka/resources/vendors/images/admin_login_page.png" alt="">
				</div>
				<div class="col-md-6 col-lg-5">
					<div class="login-box bg-white box-shadow border-radius-10">
						<div class="login-title">
							<h2 class="text-center text-primary">상상자까<br>관리자 Login</h2>
						</div>

						<form method="POST" action="/sangsangjakka/admin/login.do">	
							<div class="input-group custom">
								<input type="text" name="adId" id="adId" class="form-control form-control-lg" placeholder="아이디를 입력하세요">
								<div class="input-group-append custom">
									<span class="input-group-text"><i class="icon-copy dw dw-user1"></i></span>
								</div>
							</div>
							<div class="input-group custom">
								<input type="password" name="adPw" id="adPw" class="form-control form-control-lg" placeholder="**********">
								<div class="input-group-append custom">
									<span class="input-group-text"><i class="dw dw-padlock1"></i></span>
								</div>
							</div>
							<div class="row">
								<div class="col-sm-12">
									<div class="input-group mb-0">
										<!--
											use code for form submit
											<input class="btn btn-primary btn-lg btn-block" type="submit" value="Sign In">
										
										<a class="btn btn-primary btn-lg btn-block">Sign In</a> -->
										<button type="submit" class="btn btn-primary">로그인</button>
									</div>
								</div>
							</div>
						</form>
					</div>
				</div>
			</div>
		</div>
	</div>
	
	<!-- js -->
	<script src="https://code.jquery.com/jquery-3.7.1.js"></script>
	<script src="/sangsangjakka/resources/vendors/scripts/core.js"></script>
    <script src="/sangsangjakka/resources/vendors/scripts/script.min.js"></script>
    <script src="/sangsangjakka/resources/vendors/scripts/process.js"></script>
    <script src="/sangsangjakka/resources/vendors/scripts/layout-settings.js"></script>
    <script src="/sangsangjakka/resources/plugins/apexcharts/apexcharts.min.js"></script>
    <script src="/sangsangjakka/resources/vendors/scripts/apexcharts-setting.js"></script>
	<script>
	</script>
	</body>
</html>