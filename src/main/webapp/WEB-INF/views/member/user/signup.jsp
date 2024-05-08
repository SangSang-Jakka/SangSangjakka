<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<%@include file="/WEB-INF/views/template/asset.jsp"%>
<link rel="stylesheet" href="/sangsangjakka/resources/css/screens/signUp.css">
	<style>
	
	
	</style>
</head>
<body>
	
	<div class="container">
        <!-- Heading -->
        <div class="headerLogo">
            <a href="/sangsangjakka/index.do"><img src="https://ld-wp73.template-help.com/wordpress/prod_26992/v5/wp-content/uploads/2019/12/Group-178.svg" class="jet-logo__img" alt="Funcare" width="205" height="78"></a>
        </div>
        
        
        <form method="POST" class="signUpForm">
            <div class="member-container">
                <div class="header">
                    <div>회원 가입을 위해</div>
                    <div>정보를 입력해주세요</div>
                </div>
                <div class="user-info">
                    <div class="user-info-email">
                        <span>이메일 </span><span>*</span>
                        <div class="emailWrap">
                            <input type="text" class="userEmail" name="userEmail" required/> 
                            <span>@</span>
                            <input type="text" placeholder="직접 입력하기" class="userInputEmail" name="userDomain" required>
                            <select class="box" id="domain-list" name="domain">
                                <option value="naver.com">naver.com</option>
                                <option value="google.com">google.com</option>
                                <option value="hanmail.net">hanmail.net</option>
                                <option value="nate.com">nate.com</option>
                                <option value="kakao.com">kakao.com</option>
                                <option value="직접 입력">직접 입력</option>
                            </select>
                    	</div>
                    </div>
                    


                    <div class="user-info-nickName title">
                            <span>닉네임 </span><span>*</span>
                        <div class="nickWrap">
                            <div class="nickInput inputBorder">
                            <input type="text" id="inputNick" name="userNick" required/>
                            </div>
                            <div class="nickTest">
                            <input type="button" value="중복검사" id="nickNameCheck" onclick="openNickCheck()">
                            </div>
                            <input type="hidden" id="nickHidden" name="nicknameDuplication" value="nicknameUncheck" />
                        </div>
                        <div class="info">
                        <i class="fa-solid fa-circle-info infoIcon"></i>
                        <div class="infoContents">4~10 글자로 한글, 영어소문자, 숫자만 입력 가능합니다.</div>
                        </div>
                    </div>

                    <div class="user-info-id title">
                        <span>아이디 </span><span>*</span>
                    <div class="idWrap">
                        <div class="idInput inputBorder">
                            <input type="text" id="inputId" name="userId" required/>
                        </div>
                        <div class="idTest">
                        <input type="button" value="중복검사" id="idCheck" onclick="openIdCheck()">
                        </div>
                        <input type="hidden" id=idHidden name="idDuplication" value="idUncheck" />
                    </div>
                    <div class="info">
                    <i class="fa-solid fa-circle-info infoIcon"></i>
                    <div class="infoContents">4~12 글자로 영어, 숫자만 입력 가능합니다.</div>
                    </div>
                </div>


                    <div class="user-info-pw title">
                        <span>비밀번호 </span><span>*</span>
                        <div class="pwWrap">
                            <div class="pwInput inputBorder">
                                <input id="passwordInput" name="userPw" type="password" required/>
                                <div class="togglePassword" onclick="togglePasswordVisibility('passwordInput', 'toggleIcon')">
                                    <i id="toggleIcon" class="fa-solid fa-eye-slash"></i>
                                </div>
                            </div>
                        </div>
                        <div class="info">
                            <i class="fa-solid fa-circle-info infoIcon"></i>
                            <div class="infoContents">
                                8~16 글자로 한글, 영어소문자/대문자, 숫자, 특수문자(~, !, @, #, *, ^, ?)만 입력 가능합니다.
                            </div>
                        </div>
                    </div>
                    
                    <div class="user-info-pw-check title">
                        <span>비밀번호 확인 </span><span>*</span>
                        <div class="pwWrap">
                            <div class="pwInput inputBorder">
                                <input id="passwordInputCheck" type="password" required/>
                                <div class="togglePassword" onclick="togglePasswordVisibility('passwordInputCheck', 'toggleIconCheck')">
                                    <i id="toggleIconCheck" class="fa-solid fa-eye-slash"></i>
                                </div>
                            </div>
                        </div>
                    </div>

                    <div class="user-info-name title">
                        <span>이름 </span><span>*</span>
                        <div class="nameWrap">
                            <div class="nameInput inputBorder">
                              <input id="nameInput" name="userName" type="text"  />
                            </div>
                          </div>
                    </div>


                    <div class="user-info-ssn title">
                        <span>주민등록번호 </span><span>*</span>
                        <div class="ssnWrap">
                            <div class="ssnInput">
                                <input type="text" maxlength="6" class="leftSsn inputBorder" name="userLeftSSN" oninput="formatSSN(this)" required/>
                                <span>-</span>
                                <input type="password" maxlength="7" class="rightSsn" name="userRightSSN" oninput="formatSSN(this)" required />
                                <div class="toggleSSN" onclick="toggleSSNVisibility()">
                                    <i id="toggleSSNIcon" class="fa-solid fa-eye-slash"></i>
                            </div>
                            </div>
                        </div>
                    </div>



                    <div class="user-info-tel title">
                        <span>연락처 </span><span>*</span>
                        <div class="telWrap">
                            <div class="telInput">
                                <select class="box" id="phonePrefix" name="phonePrefix">
                                    <option value="010">010</option>
                                    <option value="011">011</option>
                                    <option value="016">016</option>
                                    <option value="017">017</option>
                                </select>
                                <input type="text" id="phoneInput1" name="phoneInput1" maxlength="4" oninput="validateInput(this)" required>
                                <span>-</span>
                                <input type="text" id="phoneInput2" name="phoneInput2" maxlength="4" oninput="validateInput(this)" required>
                            </div>
                        </div>
                    </div>


                    <div class="user-info-address title">
                        <span>주소 </span><span>*</span>
                        <div class="addressWrap">
                            <div class="addressInput">
                                <div class="addressFind">
                                    <div class="inputBorder postNum" >
                                        <input type="text" id="sample6_postcode" placeholder="우편번호" required>
                                    </div>
                                    <div class="addressTest">
                                        <input type="button" onclick="sample6_execDaumPostcode()" value="우편번호 찾기"  >
                                    </div>
                                </div>
                                <div class="inputBorder">
                                    <input type="text" id="sample6_address" name="userAddress" placeholder="주소"  required>
                                </div>
                                <div class="inputBorder">
                                    <input type="text" id="sample6_detailAddress" placeholder="상세주소" required>
                                </div>
                                <div class="inputBorder">
                                    <input type="text" id="sample6_extraAddress" placeholder="참고항목" required>
                                </div>
                            </div>
                        </div>
                    </div>

                    <div class="user-info-childrenAge title">
                        <span>자녀 연령대 </span><span>*</span>
                        <div class="childrenAgeWrap">
                            <div>
                                <input type="radio" id="childYes" name="childOption" value="예" onchange="showChildInput()" onclick="toggleChildIDRequired(true)" > 
                                <label for="childYes">예</label>
                            </div>
                            <div>
                                <input type="radio" id="childNo" name="childOption" value="아니요" onchange="showChildInput()" onclick="toggleChildIDRequired(false)" > 
                                <label for="childNo">아니요</label>
                            </div>
                    </div>
                    	<div id="childInputSSN" style="display: none;">
                    	<div class="childinputWrap">
	    					 <div class="childInput">
					        	자녀 생년월일(yyyymmdd): <input type="password" id="childID" name="childSsn" maxlength="8" onkeypress="allowOnlyNumbers(event)" >
					    	</div>
					    	<div class="toggleSSN" onclick="togglechildSSNVisibility()">
	                              <i id="childToggleSSNIcon" class="fa-solid fa-eye-slash"></i>
	                        </div>
                        </div>
                      </div>
                </div>


                <div class="user-info-inFlow title">
                    <span>유입경로 </span><span>*</span>
                    <div class="inFlowWrap">
                        <div>
                            <input type="checkbox" id="internetSearch" name="inflow" value="인터넷 검색">
                            <label for="internetSearch">인터넷 검색</label>
                        </div>
                        <div>
                            <input type="checkbox" id="friendReferral" name="inflow" value="지인 소개">
                            <label for="friendReferral">지인 소개</label>
                        </div>
                        <div>
                            <input type="checkbox" id="cafe" name="inflow" value="카페">
                            <label for="cafe">카페</label>
                        </div>
                        <div>
                            <input type="checkbox" id="blog" name="inflow" value="블로그">
                            <label for="blog">블로그</label>
                        </div>
                        <div>
                            <input type="checkbox" id="socialMedia" name="inflow" value="소셜 미디어 플랫폼">
                            <label for="socialMedia">소셜 미디어 플랫폼</label>
                        </div>
                        <div>
                            <input type="checkbox" id="advertisement" name="inflow" value="광고지">
                            <label for="advertisement">광고지</label>
                        </div>
                        <div>
                            <input type="checkbox" id="other" name="inflow" value="기타">
                            <label for="other">기타</label>
                            <input type="text" id="otherInput" placeholder="기타 유입경로 입력" style="display: none;">
                        </div>
                    </div>
                </div>


                </div>

                <!-- <div class="gender">
                    <input type="radio" name="gender" id="women" />
                    <label for="women">여성</label>
                    <input type="radio" name="gender" id="men" />
                    <label for="men">남성</label>
                </div> -->




                <div class="agree-check">
                    <input type="checkbox" /> <a href="#!">이용약관</a>과 <a href="#!">개인정보 정책</a>에 
                    </br>따른 수집 및 이용, 마케팅 활용
                    선택에 모두 동의합니다.
                </div>


                <div class="btn">
                    <input type="submit" value="가입하기" onclick="return signUp()"></input>
                </div>
            </div>
        </form>
        
      
 

         <!--  <button class="signup__btn" onclick="signUp()" style="display: none;">
            회원가입
          </button> -->







    </div>
	
	<script src="https://kit.fontawesome.com/4b3c3b390b.js" crossorigin="anonymous"></script>
      <script src=https://code.jquery.com/jquery-3.7.1.js></script>
      <script src="//t1.daumcdn.net/mapjsapi/bundle/postcode/prod/postcode.v2.js"></script>
      <script>
       // select 박스에서 값이 변경될 때 이벤트 처리
        document.getElementById('domain-list').addEventListener('change', function() {
            // 선택된 값 가져오기
            var selectedValue = this.value;

            // 두 번째 input 박스를 읽기 전용으로 설정 또는 해제
            var userInputEmail = document.querySelector('.userInputEmail');
            if (selectedValue === "직접 입력") {
                userInputEmail.value = "";
                userInputEmail.readOnly = false;
            } else {
                userInputEmail.value = selectedValue;
                userInputEmail.readOnly = true;
            }
        });

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

            function formatSSN(input) {
            // 숫자 이외의 문자 제거
            input.value = input.value.replace(/\D/g, '');

            // 뒷자리가 7자리 이상인 경우에는 7자리까지만 허용
            if (input.value.length > 7) {
                input.value = input.value.slice(0, 7);
            }
            }


            function toggleSSNVisibility() {
            var rightSSNInput = document.querySelector('.rightSsn');
            var toggleSSNIcon = document.getElementById('toggleSSNIcon');

            if (rightSSNInput.type === "password") {
                rightSSNInput.type = "text";
                toggleSSNIcon.classList.remove("fa-eye-slash");
                toggleSSNIcon.classList.add("fa-eye");
            } else {
                rightSSNInput.type = "password";
                toggleSSNIcon.classList.remove("fa-eye");
                toggleSSNIcon.classList.add("fa-eye-slash");
            }
    }


        function validateInput(input) {
        // 입력된 값이 숫자가 아니면 삭제
        input.value = input.value.replace(/\D/g, '');
        }

        // 입력창 자동 이동 함수
        document.getElementById('phoneInput1').addEventListener('input', function() {
            if (this.value.length >= this.maxLength) {
                document.getElementById('phoneInput2').focus();
            }
        });

        document.getElementById('phoneInput2').addEventListener('input', function() {
            if (this.value.length === 0) {
                document.getElementById('phoneInput1').focus();
            }
        });


        function sample6_execDaumPostcode() {
        new daum.Postcode({
            oncomplete: function(data) {
                // 팝업에서 검색결과 항목을 클릭했을때 실행할 코드를 작성하는 부분.

                // 각 주소의 노출 규칙에 따라 주소를 조합한다.
                // 내려오는 변수가 값이 없는 경우엔 공백('')값을 가지므로, 이를 참고하여 분기 한다.
                var addr = ''; // 주소 변수
                var extraAddr = ''; // 참고항목 변수

                //사용자가 선택한 주소 타입에 따라 해당 주소 값을 가져온다.
                if (data.userSelectedType === 'R') { // 사용자가 도로명 주소를 선택했을 경우
                    addr = data.roadAddress;
                } else { // 사용자가 지번 주소를 선택했을 경우(J)
                    addr = data.jibunAddress;
                }

                // 사용자가 선택한 주소가 도로명 타입일때 참고항목을 조합한다.
                if(data.userSelectedType === 'R'){
                    // 법정동명이 있을 경우 추가한다. (법정리는 제외)
                    // 법정동의 경우 마지막 문자가 "동/로/가"로 끝난다.
                    if(data.bname !== '' && /[동|로|가]$/g.test(data.bname)){
                        extraAddr += data.bname;
                    }
                    // 건물명이 있고, 공동주택일 경우 추가한다.
                    if(data.buildingName !== '' && data.apartment === 'Y'){
                        extraAddr += (extraAddr !== '' ? ', ' + data.buildingName : data.buildingName);
                    }
                    // 표시할 참고항목이 있을 경우, 괄호까지 추가한 최종 문자열을 만든다.
                    if(extraAddr !== ''){
                        extraAddr = ' (' + extraAddr + ')';
                    }
                    // 조합된 참고항목을 해당 필드에 넣는다.
                    document.getElementById("sample6_extraAddress").value = extraAddr;
                
                } else {
                    document.getElementById("sample6_extraAddress").value = '';
                }

                // 우편번호와 주소 정보를 해당 필드에 넣는다.
                document.getElementById('sample6_postcode').value = data.zonecode;
                document.getElementById("sample6_address").value = addr;
                // 커서를 상세주소 필드로 이동한다.
                document.getElementById("sample6_detailAddress").focus();
            }
        }).open();
    }


            document.getElementById('other').addEventListener('change', function() {
            var otherInput = document.getElementById('otherInput');
            if (this.checked) {
                otherInput.style.display = 'inline-block';
            } else {
                otherInput.style.display = 'none';
            }
        });


        //유효성 검사
        //1. 닉네임 유효성 검사
        document.addEventListener("DOMContentLoaded", function() {
        let inputNickName = document.getElementById("inputNick");

        inputNickName.addEventListener("keydown", function() {
        let value = inputNickName.value;
        let isValidLength = nickNameLength(value);
        let isValidNickName = checkNickName(value);

        // 이전에 추가된 모든 에러 메시지 삭제
        removeErrorMessages();

        // 글자수와 닉네임 형식이 모두 유효하지 않은 경우 메시지 표시
        if (!isValidLength && !isValidNickName) {
            showErrorMessage("  4~10 글자로 한글, 영어소문자, 숫자만 입력 가능합니다.");
        } else {
            // 글자수가 유효하지 않은 경우 메시지 표시
            if (!isValidLength) {
                showErrorMessage("  4~10 글자로 입력해주세요.");
            }

            // 닉네임 형식이 유효하지 않은 경우 메시지 표시
            if (!isValidNickName) {
                showErrorMessage("  한글, 영어소문자, 숫자만 입력 가능합니다.");
            }
        }
    });  

        function nickNameLength(value) {
            return value.length >= 4 && value.length <= 10;
        }

        function checkNickName(value) {
            return /^[a-zA-Z0-9가-힣]*$/.test(value);   
        }

        function showErrorMessage(message) {
            let errorMessage = document.createElement("span");
            errorMessage.textContent = message;
            errorMessage.style.color = "red";
            errorMessage.style.fontSize = "0.8em";
            errorMessage.id = "nickNameErrorMessage";

            let asterisk = document.querySelector(".user-info-nickName.title > span:nth-child(2)");
            asterisk.appendChild(errorMessage);
        }

        function removeErrorMessages() {
            let errorMessages = document.querySelectorAll("#nickNameErrorMessage");
            errorMessages.forEach(errorMessage => errorMessage.remove());
        }
    });

        //아이디 유효성 검사
        document.addEventListener("DOMContentLoaded", function() {
            let inputNickName = document.getElementById("inputId");

            inputNickName.addEventListener("keydown", function() {
                let value = inputNickName.value;
                let isValidLength = idLength(value);
                let isValidId = checkId(value);

            // 이전에 추가된 모든 에러 메시지 삭제
            removeErrorMessages();
            // 글자수와 닉네임 형식이 모두 유효하지 않은 경우 메시지 표시
            if (!isValidLength && !isValidId) {
                showErrorMessage("  4~12 글자로 영어, 숫자만 입력 가능합니다.");
            } else {
                // 글자수가 유효하지 않은 경우 메시지 표시
                if (!isValidLength) {
                    showErrorMessage("  4~12 글자로 입력해주세요.");
                }

                // 닉네임 형식이 유효하지 않은 경우 메시지 표시
                if (!isValidId) {
                    showErrorMessage("  영어,숫자만 입력 가능합니다.");
                }
            }
        });

        function idLength(value) {
            return value.length >= 4 && value.length <= 12;
        }

        function checkId(value) {
            return /^[a-zA-Z0-9]*$/.test(value);   
        }

        function showErrorMessage(message) {
            let errorMessage = document.createElement("span");
            errorMessage.textContent = message;
            errorMessage.style.color = "red";
            errorMessage.style.fontSize = "0.8em";
            errorMessage.id = "idErrorMessage";

            let asterisk = document.querySelector(".user-info-id.title > span:nth-child(2)");
            asterisk.appendChild(errorMessage);
        }

        function removeErrorMessages() {
            let errorMessages = document.querySelectorAll("#idErrorMessage");
            errorMessages.forEach(errorMessage => errorMessage.remove());
        }
    });

    //비밀번호 유효성 검사
    document.addEventListener("DOMContentLoaded", function() {
    let inputPw = document.getElementById("passwordInput");

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
                if (!isValidPw) {
                    showErrorMessage("  특수문자는 (~, !, @, #, *, ^, ?) 만 사용가능합니다.");
                }
            } else if (value.length > 16) {
                showErrorMessage("  16글자 이하로 입력해주세요.");
                if (!isValidPw) {
                    showErrorMessage("  특수문자는 (~, !, @, #, *, ^, ?) 만 사용가능합니다.");
                }
            }
        } 
    });

        function checkPwLength(value) {
            return value.length >= 8 && value.length <= 16;
        }

        function checkPw(value) {
            return /^[a-zA-Z0-9가-힣~!@#*^?]+$/.test(value);   
        }

        function showErrorMessage(message) {
            let errorMessage = document.createElement("span");
            errorMessage.textContent = message;
            errorMessage.style.color = "red";
            errorMessage.style.fontSize = "0.8em";
            errorMessage.id = "idErrorMessage";

            let asterisk = document.querySelector(".user-info-pw.title > span:nth-child(2)");
            asterisk.appendChild(errorMessage);
        }

        function removeErrorMessages() {
            let errorMessages = document.querySelectorAll("#idErrorMessage");
            errorMessages.forEach(errorMessage => errorMessage.remove());
        }
    });


    //비밀번호 같은 지 확인하기
    document.addEventListener("DOMContentLoaded", function() {
        let submitBtn = document.querySelector('.btn input[type="submit"]');
        let inputPw = document.getElementById("passwordInput");
        let inputPwEquals = document.getElementById("passwordInputCheck");
        let errorMessage = document.createElement("span");
        errorMessage.textContent = "비밀번호가 일치하지 않습니다.";
        errorMessage.style.color = "red";
        errorMessage.style.fontSize = "0.8em";
        errorMessage.id = "pwEqualErrorMessage";
        
        submitBtn.addEventListener("click", function(event) {
            let value = inputPw.value;
            let checkValue = inputPwEquals.value;
            
            // 이전에 추가된 모든 에러 메시지 삭제
            removeErrorMessages();

            // 비밀번호 확인이 일치하지 않은 경우 메시지 표시
            if (checkValue !== value) {
                event.preventDefault(); // 폼 제출 막기
                let asterisk = document.querySelector(".user-info-pw-check > span:nth-child(2)");
                asterisk.appendChild(errorMessage);
                
                // 에러 메시지가 있는 위치로 스크롤 이동
                errorMessage.scrollIntoView({ behavior: 'smooth', block: 'center' });
            }
        });

        function removeErrorMessages() {
            let errorMessages = document.querySelectorAll("#pwEqualErrorMessage");
            errorMessages.forEach(errorMessage => errorMessage.remove());
        }
    });


    //자녀 나이대 입력
	 function showChildInput() {
	    var childYesRadio = document.getElementById("childYes");
	    var childNoRadio = document.getElementById("childNo");
	    var childInputDiv = document.getElementById("childInputSSN");
	    
	    if (childYesRadio.checked) {
	        childInputDiv.style.display = "block"; // 자녀 주민등록번호 입력창 표시
	    } else {
	        childInputDiv.style.display = "none"; // 자녀 주민등록번호 입력창 숨김
	    }
	
	    if (childNoRadio.checked) {
	        childInputDiv.style.display = "none"; // 자녀 주민등록번호 입력창 숨김
	    }
	}



    
    //자녀 주민등록번호 토글
    function togglechildSSNVisibility() {
            var childSSNInput = document.getElementById('childID');
            var toggleSSNIcon = document.getElementById('childToggleSSNIcon');

            if (childSSNInput.type === "password") {
            	childSSNInput.type = "text";
                toggleSSNIcon.classList.remove("fa-eye-slash");
                toggleSSNIcon.classList.add("fa-eye");
            } else {
            	childSSNInput.type = "password";
                toggleSSNIcon.classList.remove("fa-eye");
                toggleSSNIcon.classList.add("fa-eye-slash");
            }
    }
    
    //자녀 주민등록번호 숫자만
    function allowOnlyNumbers(evt) {
            var charCode = (evt.which) ? evt.which : evt.keyCode;
            if (charCode > 31 && (charCode < 48 || charCode > 57)) {
                evt.preventDefault();
            }
        }

	//예 일때만 required
	 function toggleChildIDRequired(required) {
        var childIDField = document.getElementById('childID');
        childIDField.required = required;
    }
	
	//중복검사
	function signUp() {
    var idDuplicationValue = document.getElementById("idHidden").value;
    var nickDuplicationValue = document.getElementsById("nickHidden").value;

    if (idDuplicationValue !== "idChecked") {
        alert("아이디 중복검사를 먼저 실행해주세요.");
        return false;
    }

    if (nickDuplicationValue !== "nickChecked") {
        alert("닉네임 중복검사를 먼저 실행해주세요.");
        return false;
    }

    var selectedValues = []; // 선택된 값들을 저장할 배열

    // 모든 체크 박스 요소를 가져옴
    var checkboxes = document.querySelectorAll('input[name="inflow"]:checked');

    // 각 체크 박스에서 선택된 값을 배열에 추가
    checkboxes.forEach(function(checkbox) {
        selectedValues.push(checkbox.value);
    });

    // 기타 입력란이 보이고 값이 있으면 해당 값을 추가
    var otherInput = document.getElementById('otherInput');
    if (otherInput.style.display !== 'none' && otherInput.value.trim() !== '') {
        selectedValues.push(otherInput.value.trim());
    }
    
    var childSsn = document.getElementById('childID').value;

    // AJAX를 사용하여 서버에 선택된 체크 박스의 값을 전송합니다.
	   $.ajax({
	    type: 'POST',
	    url: '/user/signok.do', // 서버의 URL을 입력합니다.
	    data: { 
	    	selectedValues: selectedValues,
	    	childSsn: childSsn	
	    }, 
	    success: function(response) {
	        // 서버로부터의 응답을 처리하는 부분입니다. 필요에 따라 추가적인 로직을 작성할 수 있습니다.
	        console.log('서버로부터의 응답:', response);
	    },
	    error: function(xhr, status, error) {
	        // 에러가 발생한 경우 처리하는 부분입니다.
	        console.error('에러:', error);
	    }
	});

    // 가입 처리 로직 등을 진행하고, 필요한 경우 true를 반환하여 폼이 제출되도록 함
    // 여기서는 간단히 true를 반환하여 예시로 제공
    return true;
}


	
	//아이디 중복체크 화면 
	function openIdCheck() {
		window.name = "parentForm";
		window.open("/sangsangjakka/user/idcheck.do",
				"checkFrom","width=500, height=300, resizable=no, scrollbars=no");
	}
	
	//닉네임 중복체크 화면
	function openNickCheck() {
		window.name = "parent2Form";
		window.open("/sangsangjakka/user/nickcheck.do",
				"checkFrom","width=500, height=300, resizable=no, scrollbars=no");
	}	
	

        
      </script>
</body>
</html>