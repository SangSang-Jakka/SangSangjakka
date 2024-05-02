<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<%@include file="/WEB-INF/views/template/asset.jsp"%>
<link rel="stylesheet" href="/sangsangjakka/resources/css/screens/findId.css">
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
                <a href="#" id="signin">아이디 찾기</a>
            </li>
            <li>
                <a href="#" id="reset">초기화</a>
            </li>
            </li>
        </ul>
        
        <!-- Form -->
        <form action="/sangsangjakka/user/find_pw_ok.do" method="post">

            <!-- id input -->
            <div class="first-input input__block first-input__block">
                <input type="text" placeholder="이름을 입력해주세요." class="input" id="name" required>
            </div>

            <!-- password input -->
            <!-- password input -->
            <!-- password input -->
            <div class="input__block">
                <div class="emailInput inputBorder">
                    <input type="email" placeholder="이메일을 입력해주세요." class="input" id="email" required>
                </div>
            </div>



            <!-- sign in button -->
            <button class="signin__btn">아이디 찾기</button>

        </form>



        <!-- Separator -->
		<%@include file="/WEB-INF/views/member/user/user_template/separator.jsp"%>
		
		<!-- Footer -->
		<%@include file="/WEB-INF/views/member/user/user_template/footer.jsp"%>
    </div>

<script src="https://kit.fontawesome.com/4b3c3b390b.js" crossorigin="anonymous"></script>
<script src="https://code.jquery.com/jquery-3.7.1.js"></script>
<script>

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
	}   
	
	$(document).ready(function(){
	let signup = $(".links").find("li").find("#signup");
	let signin = $(".links").find("li").find("#signin");
	let reset  = $(".links").find("li").find("#reset");
	let first_input = $("form").find(".first-input");
	let hidden_input = $("form").find(".input__block").find("#repeat__password");
	let signin_btn  = $("form").find(".signin__btn");
	
	//----------- sign in ---------------------
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
	
	//----------- reset ---------------------
	reset.on("click",function(e){
	    e.preventDefault();
	    $(this).parent().parent().siblings("form").find(".input__block").find(".input").val("");
	})
	
	
	});
	
</script>
</body>
</html>