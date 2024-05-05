<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<form action="/sangsangjakka/user/signout.do" method="POST">
	<div class="outContainer">
		<h2>회원 탈퇴</h2>
		<div class="inputBox">
			비밀번호를 입력하세요 : <input type="text" class="inputPw" name="inputPw" placeholder="비밀번호를 입력하세요" required> 
		</div>
	</div>
		<input type="submit" value="탈퇴하기" class="signout">
	</form>
</body>
</html>