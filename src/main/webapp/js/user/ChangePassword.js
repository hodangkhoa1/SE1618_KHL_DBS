function CheckOldPassword() {
    const inputOldPassword = document.querySelector("#oldPassword"),
            oldPasswordIconCheck = document.querySelector("#oldPassword-icon-check"),
            oldPasswordIconError = document.querySelector("#oldPassword-icon-error"),
            oldPasswordErrorMessage = document.querySelector("#oldPassword-error");

    if (inputOldPassword.value === "") {
        inputOldPassword.style.border = "1px solid red";
        oldPasswordErrorMessage.innerHTML = "Please enter your old password!";
        oldPasswordIconError.style.display = "block";
        oldPasswordIconCheck.style.display = "none";
    } else {
        inputOldPassword.style.border = "1px solid green";
        oldPasswordErrorMessage.innerHTML = "";
        oldPasswordIconError.style.display = "none";
        oldPasswordIconCheck.style.display = "block";
    }
}

function OldPasswordKeyUp() {
    const inputOldPassword = document.querySelector("#oldPassword"),
            showOldPassword = document.querySelector("#show-old-password"),
            showOldPasswordIcon = document.querySelector("#show-old-password-icon");

    if (inputOldPassword.value !== "") {
        showOldPassword.style.display = "block";
        showOldPassword.onclick = function () {
            if (inputOldPassword.type === "password") {
                showOldPasswordIcon.className = "fas fa-eye";
                inputOldPassword.type = "text";
            } else {
                showOldPasswordIcon.className = "fas fa-eye-slash";
                inputOldPassword.type = "password";
            }
        };
    } else {
        showOldPassword.style.display = "none";
    }
}

function CheckNewPassword() {
    const inputNewPassword = document.querySelector("#newPassword"),
            newPasswordIconCheck = document.querySelector("#newPassword-icon-check"),
            newPasswordIconError = document.querySelector("#newPassword-icon-error"),
            newPasswordErrorMessage = document.querySelector("#newPassword-error");

    if (inputNewPassword.value === "") {
        inputNewPassword.style.border = "1px solid red";
        newPasswordErrorMessage.innerHTML = "Please enter your new password!";
        newPasswordIconError.style.display = "block";
        newPasswordIconCheck.style.display = "none";
    } else {
        inputNewPassword.style.border = "1px solid green";
        newPasswordErrorMessage.innerHTML = "";
        newPasswordIconError.style.display = "none";
        newPasswordIconCheck.style.display = "block";
    }
}

function NewPasswordKeyUp() {
    const inputNewPassword = document.querySelector("#newPassword"),
            showNewPassword = document.querySelector("#show-new-password"),
            showNewPasswordIcon = document.querySelector("#show-new-password-icon");

    if (inputNewPassword.value !== "") {
        showNewPassword.style.display = "block";
        showNewPassword.onclick = function () {
            if (inputNewPassword.type === "password") {
                showNewPasswordIcon.className = "fas fa-eye";
                inputNewPassword.type = "text";
            } else {
                showNewPasswordIcon.className = "fas fa-eye-slash";
                inputNewPassword.type = "password";
            }
        };
    } else {
        showNewPassword.style.display = "none";
    }
}

function CheckConfirmPassword() {
    const inputConfirmPassword = document.querySelector("#confirmPassword"),
            confirmPasswordIconCheck = document.querySelector(
                    "#confirmPassword-icon-check"
                    ),
            confirmPasswordIconError = document.querySelector(
                    "#confirmPassword-icon-error"
                    ),
            confirmPasswordErrorMessage = document.querySelector(
                    "#confirmPassword-error"
                    ),
            inputNewPassword = document.querySelector("#newPassword");

    if (inputConfirmPassword.value === "") {
        inputConfirmPassword.style.border = "1px solid red";
        confirmPasswordErrorMessage.innerHTML =
                "Please enter your confirm password!";
        confirmPasswordIconError.style.display = "block";
        confirmPasswordIconCheck.style.display = "none";
    } else if (inputConfirmPassword.value !== inputNewPassword.value) {
        inputConfirmPassword.style.border = "1px solid red";
        confirmPasswordErrorMessage.innerHTML =
                "Confirm password is not correct!";
        confirmPasswordIconError.style.display = "block";
        confirmPasswordIconCheck.style.display = "none";
    } else {
        inputConfirmPassword.style.border = "1px solid green";
        confirmPasswordErrorMessage.innerHTML = "";
        confirmPasswordIconError.style.display = "none";
        confirmPasswordIconCheck.style.display = "block";
    }
}

function ConfirmPasswordKeyUp() {
    const inputConfirmPassword = document.querySelector("#confirmPassword"),
            showConfirmPassword = document.querySelector("#show-confirm-password"),
            showConfirmPasswordIcon = document.querySelector(
                    "#show-confirm-password-icon"
                    );

    if (inputConfirmPassword.value !== "") {
        showConfirmPassword.style.display = "block";
        showConfirmPassword.onclick = function () {
            if (inputConfirmPassword.type === "password") {
                showConfirmPasswordIcon.className = "fas fa-eye";
                inputConfirmPassword.type = "text";
            } else {
                showConfirmPasswordIcon.className = "fas fa-eye-slash";
                inputConfirmPassword.type = "password";
            }
        };
    } else {
        showConfirmPassword.style.display = "none";
    }
}