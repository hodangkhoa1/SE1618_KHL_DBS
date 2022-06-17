let container = document.getElementById("page-container");

function toggle() {
    container.classList.toggle("sign-in");
    container.classList.toggle("sign-up");
    ClearDataInSignIn();
}

function CheckURL(urlPage) {
    if (urlPage.includes("VALUE_LOGIN")) {
        setTimeout(() => {
            container.classList.add("sign-in");
        }, 200);
    } else {
        setTimeout(() => {
            container.classList.add("sign-up");
        }, 200);
    }
}

// Function check input full name in Sign up
function CheckSignUpFullname() {
    const inputFullname = document.querySelector("#fullname"),
            signupFullnameIconCheck = document.querySelector(
                    "#signup_fullname_icon_check"
                    ),
            signupFullnameIconError = document.querySelector(
                    "#signup_fullname_icon_error"
                    ),
            fullnameErrorMessage = document.querySelector("#fullname_error_signup");

    if (inputFullname.value === "") {
        inputFullname.style.border = "1px solid red";
        fullnameErrorMessage.innerHTML = "Please enter your full name!";
        signupFullnameIconError.style.display = "block";
        signupFullnameIconCheck.style.display = "none";
    } else {
        inputFullname.style.border = "1px solid green";
        fullnameErrorMessage.innerHTML = "";
        signupFullnameIconError.style.display = "none";
        signupFullnameIconCheck.style.display = "block";
    }
}

//Function validation email
function validateEmail(email) {
    const re =
            /^(([^<>()[\]\\.,;:\s@"]+(\.[^<>()[\]\\.,;:\s@"]+)*)|(".+"))@((\[[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\])|(([a-zA-Z\-0-9]+\.)+[a-zA-Z]{2,}))$/;
    return re.test(String(email).toLowerCase());
}

// Function check input email in Sign up
function CheckSignUpEmail() {
    const inputEmail = document.querySelector("#email"),
            signupEmailIconCheck = document.querySelector("#signup_email_icon_check"),
            signupEmailIconError = document.querySelector("#signup_email_icon_error"),
            emailErrorMessage = document.querySelector("#email_error_signup");

    if (inputEmail.value === "") {
        inputEmail.style.border = "1px solid red";
        emailErrorMessage.innerHTML = "Please enter your email!";
        signupEmailIconError.style.display = "block";
        signupEmailIconCheck.style.display = "none";
    } else if (!validateEmail(inputEmail.value)) {
        inputEmail.style.border = "1px solid red";
        emailErrorMessage.innerHTML = "Please enter correct email format!";
        signupEmailIconError.style.display = "block";
        signupEmailIconCheck.style.display = "none";
    } else {
        inputEmail.style.border = "1px solid green";
        emailErrorMessage.innerHTML = "";
        signupEmailIconError.style.display = "none";
        signupEmailIconCheck.style.display = "block";
    }
}

// Function check input password in Sign up
function CheckSignUpPassword() {
    const inputPassword = document.querySelector("#password"),
            signupPasswordIconCheck = document.querySelector(
                    "#signup_password_icon_check"
                    ),
            signupPasswordIconError = document.querySelector(
                    "#signup_password_icon_error"
                    ),
            passwordErrorMessage = document.querySelector("#password_error_signup");

    if (inputPassword.value === "") {
        inputPassword.style.border = "1px solid red";
        passwordErrorMessage.innerHTML = "Please enter your password!";
        signupPasswordIconError.style.display = "block";
        signupPasswordIconCheck.style.display = "none";
    } else if (inputPassword.value.length < 8) {
        inputPassword.style.border = "1px solid red";
        passwordErrorMessage.innerHTML =
                "Your password must be at least 8 characters!";
        signupPasswordIconError.style.display = "block";
        signupPasswordIconCheck.style.display = "none";
    } else if (inputPassword.value.search(/[a-z]/i) < 0) {
        inputPassword.style.border = "1px solid red";
        passwordErrorMessage.innerHTML =
                "Your password must contain at least one letter!";
        signupPasswordIconError.style.display = "block";
        signupPasswordIconCheck.style.display = "none";
    } else if (inputPassword.value.search(/[0-9]/) < 0) {
        inputPassword.style.border = "1px solid red";
        passwordErrorMessage.innerHTML =
                "Your password must contain at least one digit!";
        signupPasswordIconError.style.display = "block";
        signupPasswordIconCheck.style.display = "none";
    } else {
        inputPassword.style.border = "1px solid green";
        passwordErrorMessage.innerHTML = "";
        signupPasswordIconError.style.display = "none";
        signupPasswordIconCheck.style.display = "block";
    }
}

// Function check input confirm password in Sign up
function CheckSignUpConfirmPassword() {
    const inputConfirmPassword = document.querySelector("#confirmpassword"),
            inputPassword = document.querySelector("#password"),
            signupConfirmPasswordIconCheck = document.querySelector(
                    "#signup_confirmpassword_icon_check"
                    ),
            signupConfirmPasswordIconError = document.querySelector(
                    "#signup_confirmpassword_icon_error"
                    ),
            confirmPasswordErrorMessage = document.querySelector(
                    "#confirmpassword_error_signup"
                    );

    if (inputConfirmPassword.value === "") {
        inputConfirmPassword.style.border = "1px solid red";
        confirmPasswordErrorMessage.innerHTML = "Please enter confirm Password!";
        signupConfirmPasswordIconError.style.display = "block";
        signupConfirmPasswordIconCheck.style.display = "none";
    } else if (inputConfirmPassword.value !== inputPassword.value) {
        inputConfirmPassword.style.border = "1px solid red";
        confirmPasswordErrorMessage.innerHTML = "Password does not match!";
        signupConfirmPasswordIconError.style.display = "block";
        signupConfirmPasswordIconCheck.style.display = "none";
    } else {
        inputConfirmPassword.style.border = "1px solid green";
        confirmPasswordErrorMessage.innerHTML = "";
        signupConfirmPasswordIconError.style.display = "none";
        signupConfirmPasswordIconCheck.style.display = "block";
    }
}

// Function check input email in Sign in
function CheckSignInEmail() {
    const inputEmail = document.querySelector("#signin_email"),
            signinEmailIconCheck = document.querySelector("#signin_email_icon_check"),
            signinEmailIconError = document.querySelector("#signin_email_icon_error"),
            emailErrorMessage = document.querySelector("#email_error_signin");

    if (inputEmail.value === "") {
        inputEmail.style.border = "1px solid red";
        emailErrorMessage.innerHTML = "Please enter your email!";
        signinEmailIconError.style.display = "block";
        signinEmailIconCheck.style.display = "none";
    } else if (!validateEmail(inputEmail.value)) {
        inputEmail.style.border = "1px solid red";
        emailErrorMessage.innerHTML = "Please enter correct email format!";
        signinEmailIconError.style.display = "block";
        signinEmailIconCheck.style.display = "none";
    } else {
        inputEmail.style.border = "1px solid green";
        emailErrorMessage.innerHTML = "";
        signinEmailIconError.style.display = "none";
        signinEmailIconCheck.style.display = "block";
    }
}

// Function check input password in Sign in
function CheckSignInPassword() {
    const inputPassword = document.querySelector("#signin_password"),
            signinPasswordIconCheck = document.querySelector(
                    "#signin_password_icon_check"
                    ),
            signinPasswordIconError = document.querySelector(
                    "#signin_password_icon_error"
                    ),
            passwordErrorMessage = document.querySelector("#password_error_signin");

    if (inputPassword.value === "") {
        inputPassword.style.border = "1px solid red";
        passwordErrorMessage.innerHTML = "Please enter your password!";
        signinPasswordIconError.style.display = "block";
        signinPasswordIconCheck.style.display = "none";
    } else {
        inputPassword.style.border = "1px solid green";
        passwordErrorMessage.innerHTML = "";
        signinPasswordIconError.style.display = "none";
        signinPasswordIconCheck.style.display = "block";
    }
}

// Function clear data in input
function ClearDataInSignIn() {
    const inputEmail = document.querySelector("#signin_email"),
            signinEmailIconCheck = document.querySelector("#signin_email_icon_check"),
            signinEmailIconError = document.querySelector("#signin_email_icon_error"),
            emailErrorMessage = document.querySelector("#email_error_signin"),
            inputPassword = document.querySelector("#signin_password"),
            signinPasswordIconCheck = document.querySelector(
                    "#signin_password_icon_check"
                    ),
            signinPasswordIconError = document.querySelector(
                    "#signin_password_icon_error"
                    ),
            passwordErrorMessage = document.querySelector("#password_error_signin"),
            inputConfirmPassword = document.querySelector("#confirmpassword"),
            signupConfirmPasswordIconCheck = document.querySelector(
                    "#signup_confirmpassword_icon_check"
                    ),
            signupConfirmPasswordIconError = document.querySelector(
                    "#signup_confirmpassword_icon_error"
                    ),
            confirmPasswordErrorMessage = document.querySelector(
                    "#confirmpassword_error_signup"
                    ),
            inputSignUpPassword = document.querySelector("#password"),
            signupPasswordIconCheck = document.querySelector(
                    "#signup_password_icon_check"
                    ),
            signupPasswordIconError = document.querySelector(
                    "#signup_password_icon_error"
                    ),
            passwordSignUpErrorMessage = document.querySelector(
                    "#password_error_signup"
                    ),
            signupInputEmail = document.querySelector("#email"),
            signupEmailIconCheck = document.querySelector("#signup_email_icon_check"),
            signupEmailIconError = document.querySelector("#signup_email_icon_error"),
            signupEmailErrorMessage = document.querySelector("#email_error_signup"),
            inputFullname = document.querySelector("#fullname"),
            signupFullnameIconCheck = document.querySelector(
                    "#signup_fullname_icon_check"
                    ),
            signupFullnameIconError = document.querySelector(
                    "#signup_fullname_icon_error"
                    ),
            fullnameErrorMessage = document.querySelector("#fullname_error_signup");

    inputEmail.style.border = "0.125rem solid var(--white)";
    inputEmail.value = "";
    emailErrorMessage.innerHTML = "";
    signinEmailIconError.style.display = "none";
    signinEmailIconCheck.style.display = "none";
    inputPassword.style.border = "0.125rem solid var(--white)";
    inputPassword.value = "";
    passwordErrorMessage.innerHTML = "";
    signinPasswordIconError.style.display = "none";
    signinPasswordIconCheck.style.display = "none";
    inputConfirmPassword.style.border = "0.125rem solid var(--white)";
    inputConfirmPassword.value = "";
    confirmPasswordErrorMessage.innerHTML = "";
    signupConfirmPasswordIconError.style.display = "none";
    signupConfirmPasswordIconCheck.style.display = "none";
    inputSignUpPassword.style.border = "0.125rem solid var(--white)";
    inputSignUpPassword.value = "";
    passwordSignUpErrorMessage.innerHTML = "";
    signupPasswordIconError.style.display = "none";
    signupPasswordIconCheck.style.display = "none";
    signupInputEmail.style.border = "0.125rem solid var(--white)";
    signupInputEmail.value = "";
    signupEmailErrorMessage.innerHTML = "";
    signupEmailIconError.style.display = "none";
    signupEmailIconCheck.style.display = "none";
    inputFullname.style.border = "0.125rem solid var(--white)";
    inputFullname.value = "";
    fullnameErrorMessage.innerHTML = "";
    signupFullnameIconError.style.display = "none";
    signupFullnameIconCheck.style.display = "none";
}

// Function show hide password in sign in
function PasswordSignInKeyUp() {
    const inputSignInPassword = document.querySelector("#signin_password"),
            showSignInPasswordIcon = document.querySelector(
                    "#show-signin-password-icon"
                    );

    if (inputSignInPassword.value !== "") {
        showSignInPasswordIcon.style.display = "block";
        showSignInPasswordIcon.onclick = function () {
            if (inputSignInPassword.type === "password") {
                showSignInPasswordIcon.className = "fa-solid fa-eye";
                inputSignInPassword.type = "text";
            } else {
                showSignInPasswordIcon.className = "fa-solid fa-eye-slash";
                inputSignInPassword.type = "password";
            }
        };
    } else {
        showSignInPasswordIcon.style.display = "none";
    }
}

// Function show hide password in sign up
function PasswordSignUpKeyUp() {
    const inputSignUpPassword = document.querySelector("#password"),
            showSignUpPasswordIcon = document.querySelector(
                    "#show-signup-password-icon"
                    );

    if (inputSignUpPassword.value !== "") {
        showSignUpPasswordIcon.style.display = "block";
        showSignUpPasswordIcon.onclick = function () {
            if (inputSignUpPassword.type === "password") {
                showSignUpPasswordIcon.className = "fa-solid fa-eye";
                inputSignUpPassword.type = "text";
            } else {
                showSignUpPasswordIcon.className = "fa-solid fa-eye-slash";
                inputSignUpPassword.type = "password";
            }
        };
    } else {
        showSignUpPasswordIcon.style.display = "none";
    }
}

// Function show hide confirm password in sign up
function ConfirmPasswordSignUpKeyUp() {
    const inputSignUpConfirmPassword = document.querySelector("#confirmpassword"),
            showSignUpConfirmPasswordIcon = document.querySelector(
                    "#show-signup-confirm-password-icon"
                    );

    if (inputSignUpConfirmPassword.value !== "") {
        showSignUpConfirmPasswordIcon.style.display = "block";
        showSignUpConfirmPasswordIcon.onclick = function () {
            if (inputSignUpConfirmPassword.type === "password") {
                showSignUpConfirmPasswordIcon.className = "fa-solid fa-eye";
                inputSignUpConfirmPassword.type = "text";
            } else {
                showSignUpConfirmPasswordIcon.className = "fa-solid fa-eye-slash";
                inputSignUpConfirmPassword.type = "password";
            }
        };
    } else {
        showSignUpConfirmPasswordIcon.style.display = "none";
    }
}

const loader = document.querySelector("#preloader");
window.addEventListener("load", function () {
  loader.style.display = "none";
});