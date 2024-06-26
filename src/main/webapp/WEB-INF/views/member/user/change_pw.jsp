<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<%@include file="/WEB-INF/views/template/asset.jsp"%>
<link rel="stylesheet" href="/sangsangjakka/resources/css/screens/changePw.css">
	<style>
	
	
	</style>
</head>
<body>
	
	<div class="container">
        <!-- Heading -->
        <%@include file="/WEB-INF/views/member/user/user_template/heading.jsp"%>
        
        <!-- Links -->
        <ul class="links">
            <li>
                <a href="#" id="signin">비밀번호 변경</a>
            </li>
            <li>
                <a href="#" id="reset">초기화</a>
            </li>
            </li>
        </ul>
        
        <!-- Form -->
        <form action="/sangsangjakka/user/change_pw.do" method="POST">
            <!-- password input -->
            <div class="first-input input__block first-input__block">
                <div class="pwCheckInput inputBorder">
                    <input type="password" placeholder="새 비밀번호를 입력해주세요." class="input" id="pwInput" name="pwInput">
                    <div class="togglePassword" onclick="togglePasswordVisibility('pwInput', 'toggleIconCheck1')">
                        <i id="toggleIconCheck1" class="fas fa-eye-slash"></i>
                    </div>
                </div>
            </div>
                    
            <!-- password input -->
            <div class="input__block second-input__block">
                <div class="pwCheckInput inputBorder">
                    <input type="password" placeholder="비밀번호를 다시 입력해주세요." class="input" id="pwCheck" name="pwCheck"> 
                    <div class="togglePassword" onclick="togglePasswordVisibility('pwCheck', 'toggleIconCheck2')">
                        <i id="toggleIconCheck2" class="fas fa-eye-slash"></i>
                    </div>
                </div>
            </div>
        
            <!-- sign in button -->
            <button class="signin__btn" type="submit">비밀번호 찾기</button>
        </form>

        <!-- Separator -->
		<%@include file="/WEB-INF/views/member/user/user_template/separator.jsp"%>
		
		<!-- Footer -->
		<%@include file="/WEB-INF/views/member/user/user_template/footer.jsp"%>
    </div>

<script src="https://kit.fontawesome.com/4b3c3b390b.js" crossorigin="anonymous"></script>
<script src="https://code.jquery.com/jquery-3.7.1.js"></script>
<script>
        // 데이터베이스에서 아이디를 가져와서 변수에 저장
        let fetchedId = "hong1234";

        // HTML 요소를 가져옵니다.
        let signinLink = document.getElementById("signin");

        // 가져온 아이디 값을 HTML 요소에 삽입합니다.
        signinLink.textContent += " (ID: " + fetchedId + ")";

        function togglePasswordVisibility(inputId, toggleIconId) {
            var passwordInput = document.getElementById(inputId);
            var toggleIcon = document.getElementById(toggleIconId);

            if (passwordInput.type === "password") {
                passwordInput.type = "text";
                toggleIcon.classList.remove("fa-eye-slash");
                toggleIcon.classList.add("fa-eye");
            } else {
                passwordInput.type = "password";
                toggleIcon.classList.remove("fa-eye");
                toggleIcon.classList.add("fa-eye-slash");
            }

            // 아이콘의 위치를 조정합니다.
            var iconMargin = (passwordInput.offsetHeight - toggleIcon.offsetHeight) / 2;
            toggleIcon.style.marginTop = iconMargin + "px";
        }

        $(document).ready(function(){
            let signin = $(".links").find("li").find("#signin");
            let reset  = $(".links").find("li").find("#reset");
            let first_input = $("form").find(".first-input");
            let hidden_input = $("form").find(".input__block").find("#pwCheck");
            let signin_btn  = $("form").find(".signin__btn");

            // sign in 버튼 클릭 시
            signin.on("click",function(e){
                e.preventDefault();
                $(this).parent().parent().siblings("h1").text("SIGN IN");
                $(this).parent().css("opacity","1");
                $(this).parent().siblings().css("opacity",".6");
                first_input.addClass("first-input__block").removeClass("signup-input__block");
                hidden_input.css({
                    "opacity" : "0",
                    "display" : "none"
                });
                signin_btn.text("Sign in");
            });

            // reset 버튼 클릭 시
            reset.on("click",function(e){
                e.preventDefault();
                $(this).parent().parent().siblings("form").find(".input__block").find(".input").val("");
            })
        });

        // 비밀번호 확인
        document.addEventListener("DOMContentLoaded", function() {
            let submitBtn = document.querySelector('.signin__btn');
            let inputPw = document.getElementById("pwInput");
            let inputPwEquals = document.getElementById("pwCheck");

            submitBtn.addEventListener("click", function(event) {
                let value = inputPw.value;
                let checkValue = inputPwEquals.value;
                let errorMessage = document.createElement("span");
                errorMessage.textContent = "비밀번호가 일치하지 않습니다.";
                errorMessage.style.color = "red";
                errorMessage.style.fontSize = "0.8em";
                errorMessage.style.marginLeft = "30px";
                errorMessage.classList.add("error-message");

                // 이전에 추가된 모든 에러 메시지 삭제
                removeErrorMessages();

                // 비밀번호 확인이 일치하지 않은 경우 메시지 표시
                if (checkValue !== value) {
                    event.preventDefault(); // 폼 제출 막기
                    let inputBlock = document.querySelector(".second-input__block");
                    inputBlock.appendChild(errorMessage);
                    
                }
            });

            function removeErrorMessages() {
                let errorMessages = document.querySelectorAll(".error-message");
                errorMessages.forEach(errorMessage => errorMessage.remove());
            }
        });

        // 비밀번호 유효성 검사
        document.addEventListener("DOMContentLoaded", function() {
            let inputPw = document.getElementById("pwInput");

            inputPw.addEventListener("input", function() {
                let value = inputPw.value;
                let isValidLength = checkPwLength(value);
                let isValidPw = checkPw(value);

                // 이전에 추가된 모든 에러 메시지 삭제
                removeErrorMessages();

                // 비밀번호 길이가 유효하지 않은 경우 메시지 표시
                if (!isValidLength) {
                    if (value.length < 8) {
                        showErrorMessage("  8글자 이상으로 입력해주세요.");
                    } else if (value.length > 16) {
                        showErrorMessage("  16글자 이하로 입력해주세요.");
                    }
                } 
                
                // 비밀번호에 특수 문자가 포함되지 않은 경우 메시지 표시
                if (!isValidPw) {
                    showErrorMessage("  특수문자는 (~, !, @, #, *, ^, ?) 중 하나 이상을 사용해주세요.");
                }
            }); 
        });

        function checkPwLength(password) {
            return password.length >= 8 && password.length <= 16;
        }

        function checkPw(password) {
            return /[~!@#*^?]/.test(password);
        }

        function showErrorMessage(message) {
            let inputBlock = document.querySelector(".first-input");
            let errorMessage = document.createElement("span");
            errorMessage.textContent = message;
            errorMessage.style.color = "red";
            errorMessage.style.fontSize = "0.8em";
            errorMessage.classList.add("error-message");

            // 첫 번째 오류 메시지에 margin 추가
            if (!document.querySelector(".error-message")) {
                errorMessage.style.marginLeft = "30px";
            }

            inputBlock.appendChild(errorMessage);
        }

        function removeErrorMessages() {
            let errorMessages = document.querySelectorAll(".error-message");
            errorMessages.forEach(errorMessage => errorMessage.remove());
        }

</script>
</body>
</html>