//닉네임

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