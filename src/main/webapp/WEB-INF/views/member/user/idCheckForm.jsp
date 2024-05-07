<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" href="http://pinnpublic.dothome.co.kr/cdn/example-min.css">
<style>

</style>
</head>
<body onload="pValue()">
	<div id="checkWrap">
		<br />
		<h1>아이디 중복 체크</h1>

		<br />
		<div id="checkWrap">
			<form id="checkForm" >
				<input type="text" name="idInput" id="userId" />
				<input type="button" value="중복확인" onclick="idCheck()" />
			</form>
			<input type="button" id="cancelBtn" value="취소" onclick="window.close()" />
			<input type="button" id="useBtn" value="사용하기" onclick="sendCheckValue()" disabled />
		</div>
	</div>
	
	<script src=https://code.jquery.com/jquery-3.7.1.js></script>
	<script>
    function pValue() {
        document.getElementById("userId").value = opener.document.getElementById("inputId").value;
    }
    
    function idCheck() {
        var id = document.getElementById("userId").value;
        
        //alert(id);

        // AJAX 요청
        var xhr = new XMLHttpRequest();
        xhr.open("POST", "/sangsangjakka/user/idcheck.do", true); // 서버의 해당 URL로 POST 요청
        xhr.setRequestHeader("Content-Type", "application/x-www-form-urlencoded");
        xhr.onreadystatechange = function () {
            if (xhr.readyState == 4 && xhr.status == 200) {
                var response = xhr.responseText;
                // 받은 응답에 따라 처리
                if (response === "true") {
                    alert("이미 사용중인 아이디입니다.");
                    document.getElementById("useBtn").disabled = true; // 이미 사용 중인 경우 버튼 비활성화
                } else {
                    alert("사용 가능한 아이디입니다.");
                    document.getElementById("useBtn").disabled = false; // 사용 가능한 경우 버튼 활성화
                }
            }
        };
        xhr.send("id=" + encodeURIComponent(id)); // 아이디를 POST 요청으로 서버에 전송
    }
    
   
    //중복체크 검사
    function sendCheckValue() {
    // 사용하기 버튼이 비활성화된 경우(이미 사용 중인 아이디인 경우), 아무것도 수행하지 않음
    if (document.getElementById("useBtn").disabled) {
        return;
    }
    
    // 부모 창의 요소 값을 변경합니다.
    opener.document.getElementById("idHidden").value = "idChecked";
    opener.document.getElementById("inputId").value = document.getElementById("userId").value;
    
    // 현재 창을 닫습니다.
    window.close();
}

</script>

</body>
</html>