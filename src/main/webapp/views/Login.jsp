<%@page import="com.khl.gentledentalcare.models.AccountError"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%
    AccountError signinAccountError = (AccountError) request.getAttribute("LOGIN_ACCOUNT_ERROR");
    AccountError signupAccountError = (AccountError) request.getAttribute("SIGN_UP_ACCOUNT_ERROR");
    
    if (signinAccountError == null) {
        signinAccountError = new AccountError();
    }
    
    if(signupAccountError == null) {
        signupAccountError = new AccountError();
    }
%>
<!DOCTYPE html>
<html>
    <head>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title>Login | Gentle Dental Care</title>
        <!-- TẠO ICON TRÊN THANH WEB -->
        <link rel="icon" href="./images/favicon-100x100.png" type="image/png" sizes="200x138" />
        <!-- LINK BOXICONS -->
        <link href='https://unpkg.com/boxicons@2.0.7/css/boxicons.min.css' rel='stylesheet'>
        <!-- LINK FONTAWESOME -->
        <link rel="stylesheet" href="https://use.fontawesome.com/releases/v6.1.1/css/all.css">
        <!-- LINK STYLE -->
        <link href="./css/Loader.css" rel="stylesheet" type="text/css"/>
        <link href="./css/Login.css" rel="stylesheet" type="text/css"/>
    </head>
    <body>
        <jsp:include page="./../layouts/Loader.html"></jsp:include>
        
        <div class="page-container" id="page-container">
            <!-- FORM SECTION -->
            <div class="container-row">
                <!-- SIGN UP -->
                <div class="container-col align-items-center flex-col sign-up">
                    <div class="form-wrapper align-items-center">
                        <form action="${pageContext.request.contextPath}/register" method="post" class="sign-up">
                            <div class="input-group">
                                <i class='bx bxs-user'></i>
                                <input type="text" placeholder="Full Name" name="fullname" id="fullname" oninput="CheckSignUpFullname()" onblur="CheckSignUpFullname()" value="${USERNAME_REGISTER != null ? USERNAME_REGISTER : ""}">
                                <i class='bx bx-check-circle signup_fullname_icon_check' id="signup_fullname_icon_check"></i>
                                <i class='bx bx-error-circle signup_fullname_icon_error' id="signup_fullname_icon_error"></i>
                            </div>
                            <div class="message">
                                <span class="error_message" id="fullname_error_signup"><%=signupAccountError.getFullNameError()%></span>
                            </div>
                            <div class="input-group">
                                <i class='bx bx-mail-send'></i>
                                <input type="email" placeholder="Email" name="email" id="email" oninput="CheckSignUpEmail()" onblur="CheckSignUpEmail()" value="${EMAIL_REGISTER != null ? EMAIL_REGISTER : ""}">
                                <i class='bx bx-check-circle signup_email_icon_check' id="signup_email_icon_check"></i>
                                <i class='bx bx-error-circle signup_email_icon_error' id="signup_email_icon_error"></i>
                            </div>
                            <div class="message">
                                <span class="error_message" id="email_error_signup"><%=signupAccountError.getEmailError()%></span>
                            </div>
                            <div class="input-group">
                                <i class='bx bxs-lock-alt'></i>
                                <input type="password" placeholder="Password" name="password" id="password" oninput="CheckSignUpPassword()" onblur="CheckSignUpPassword()" onkeyup="PasswordSignUpKeyUp()">
                                <i class="fa-solid fa-eye-slash" id="show-signup-password-icon"></i>
                                <i class='bx bx-check-circle signup_password_icon_check' id="signup_password_icon_check"></i>
                                <i class='bx bx-error-circle signup_password_icon_error' id="signup_password_icon_error"></i>
                            </div>
                            <div class="message">
                                <span class="error_message" id="password_error_signup"><%=signupAccountError.getPasswordError()%></span>
                            </div>
                            <div class="input-group">
                                <i class='bx bxs-lock-alt'></i>
                                <input type="password" placeholder="Confirm password" name="confirmpassword" id="confirmpassword" oninput="CheckSignUpConfirmPassword()" onblur="CheckSignUpConfirmPassword()" onkeyup="ConfirmPasswordSignUpKeyUp()">
                                <i class="fa-solid fa-eye-slash" id="show-signup-confirm-password-icon"></i>
                                <i class='bx bx-check-circle signup_confirmpassword_icon_check' id="signup_confirmpassword_icon_check"></i>
                                <i class='bx bx-error-circle signup_confirmpassword_icon_error' id="signup_confirmpassword_icon_error"></i>
                            </div>
                            <div class="message">
                                <span class="error_message" id="confirmpassword_error_signup"><%=signupAccountError.getConfirmPasswordError()%></span>
                            </div>
                            <button type="submit" class="form-button">Sign up</button>
                            <p class="social_text">Or Sign up with social platforms</p>
                            <div class="social_media sign-up">
                                <a href="https://accounts.google.com/o/oauth2/auth?scope=email&redirect_uri=http://localhost:8080/GentleDentalCare/login-google&response_type=code&client_id=950091116693-1kvu1ecb619h5dpuabdu33046704ecfj.apps.googleusercontent.com&approval_prompt=force" class="social_icon">
                                    <i class='bx bxl-google'></i>
                                </a>
                                <a href="#" class="social_icon">
                                    <i class='bx bxl-facebook'></i>
                                </a>
                                <a href="#" class="social_icon">
                                    <i class='bx bxl-microsoft'></i>
                                </a>
                            </div>
                            <p class="Login_dontHaveAcc">
                                Already have an account ? <button type="button" onclick="toggle()" class="sign_up_btn">Sign in</button>
                            </p>
                        </form>
                    </div>
                </div>
                <!-- END SIGN UP -->

                <!-- SIGN IN -->
                <div class="container-col align-items-center flex-col sign-in">
                    <div class="form-wrapper align-items-center">
                        <form action="${pageContext.request.contextPath}/login" method="post" class="sign-in">
                            <div class="input-group">
                                <i class='bx bx-mail-send'></i>
                                <input type="email" placeholder="Email" name="email" id="signin_email" oninput="CheckSignInEmail()" onblur="CheckSignInEmail()" value="${EMAIL != null ? EMAIL : ""}">
                                <i class='bx bx-check-circle signin_email_icon_check' id="signin_email_icon_check"></i>
                                <i class='bx bx-error-circle signin_email_icon_error' id="signin_email_icon_error"></i>
                            </div>
                            <div class="message">
                                <span class="error_message" id="email_error_signin"><%=signinAccountError.getEmailError()%></span>
                            </div>
                            <div class="input-group">
                                <i class='bx bxs-lock-alt'></i>
                                <input type="password" placeholder="Password" name="password" id="signin_password" oninput="CheckSignInPassword()" onblur="CheckSignInPassword()" onkeyup="PasswordSignInKeyUp()">
                                <i class="fa-solid fa-eye-slash" id="show-signin-password-icon"></i>
                                <i class='bx bx-check-circle signin_password_icon_check' id="signin_password_icon_check"></i>
                                <i class='bx bx-error-circle signin_password_icon_error' id="signin_password_icon_error"></i>
                            </div>
                            <div class="message">
                                <span class="error_message" id="password_error_signin"><%=signinAccountError.getPasswordError()%></span>
                            </div>
                            <div class="form-options">
                                <div class="remember-me">
                                    <input type="checkbox" id="rememberMe" name="rememberMe" value="Y">
                                    <label for="rememberMe">Remember me</label>
                                </div>
                                <div class="forgot_link">
                                    <a href="${pageContext.request.contextPath}/forgot-password">Forgot password</a>
                                </div>
                            </div>
                            <button type="submit" class="form-button">Sign in</button>
                            <p class="social_text">Or Sign in with social platforms</p>
                            <div class="social_media sign-in">
                                <a href="https://accounts.google.com/o/oauth2/auth?scope=email&redirect_uri=http://localhost:8080/GentleDentalCare/login-google&response_type=code&client_id=950091116693-1kvu1ecb619h5dpuabdu33046704ecfj.apps.googleusercontent.com&approval_prompt=force" class="social_icon">
                                    <i class='bx bxl-google'></i>
                                </a>
                                <a href="#" class="social_icon">
                                    <i class='bx bxl-facebook'></i>
                                </a>
                                <a href="#" class="social_icon">
                                    <i class='bx bxl-microsoft'></i>
                                </a>
                            </div>
                            <p class="Login_dontHaveAcc">
                                Do not have an account ? <button type="button" onclick="toggle()" class="sign_up_btn">Sign up</button>
                            </p>
                        </form>
                    </div>
                </div>
                <!-- END SIGN IN -->
            </div>
            <!-- END FORM SECTION -->

            <!-- CONTENT SECTION -->
            <div class="container-row content-row">
                <!-- SIGN IN CONTENT -->
                <div class="container-col align-items-center flex-col">
                    <div class="content-text sign-in">
                        <h2>Welcome back</h2>
                        <p>
                            Horizon is the leading website selling computer components in Vietnam. Quickly create an account and let's explore Horizon!
                        </p>
                    </div>
                    <div class="content-image sign-in">
                        <img src="./images/undraw_different_love_a3rg.svg" alt="welcome">
                    </div>
                </div>
                <!-- END SIGN IN CONTENT -->

                <!-- SIGN UP CONTENT -->
                <div class="container-col align-items-center flex-col">
                    <div class="content-image sign-up">
                        <img src="./images/undraw_creative_team_r90h.svg" alt="welcome">
                    </div>
                    <div class="content-text sign-up">
                        <h2>Join with us</h2>
                        <p>
                            Lorem ipsum, dolor sit amet consectetur adipisicing elit. Impedit obcaecati, accusantium
                            molestias, laborum, aspernatur deserunt officia voluptatum tempora dicta quo ab ullam. Assumenda
                            enim harum minima possimus dignissimos deserunt rem.
                        </p>
                    </div>
                </div>
                <!-- END SIGN UP CONTENT -->
            </div>
            <!-- END CONTENT SECTION -->
        </div>
        
        <script src="./js/Login.js"></script>
        <script>
            CheckURL("${sessionScope.VALUE_LOGIN}");
        </script>
    </body>
</html>
