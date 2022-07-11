<%@page import="com.khl.gentledentalcare.models.AccountError"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%
    AccountError accountError = (AccountError) request.getAttribute("FORGOT_ACCOUNT_ERROR");

    if (accountError == null) {
        accountError = new AccountError();
    }
%>
<!DOCTYPE html>
<html>
    <head>
        <meta charset="UTF-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title>Forgot Password | Gentle Dental Care</title>
        <!-- TẠO ICON TRÊN THANH WEB -->
        <link rel="icon" href="./images/favicon-100x100.png" type="image/png" sizes="200x138" />
        <!-- LINK BOOTSTRAP 5 -->
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet" />
        <!-- LINK STYLE -->
        <link href="./css/Loader.css" rel="stylesheet" type="text/css"/>
        <link href="./css/ForgotPassword.css" rel="stylesheet" type="text/css"/>
    </head>
    <body>
        <jsp:include page="./../layouts/Loader.html"></jsp:include>
        
        <div class="container forgot-password" style="margin-top: ${CHANGE_PAGE_PASSWORD != null ? '1%' : '8%'}">
            <img src="./images/forgot_password_background.png" alt="" class="forgot-password__triangle">
            <div class="row">
                <div class="col-md-6">
                    <div class="forgot-password__left">
                        <div class="background">
                            <lottie-player src="https://assets1.lottiefiles.com/packages/lf20_xvrofzfk.json"
                                background="transparent" speed="1" loop autoplay class="animation-image"></lottie-player>
                        </div>
                    </div>
                </div>
                <div class="col-md-6 login-form">
                    <div class="login_form_in">
                        <div class="auth_branding">
                            <a class="auth_branding_in" href="#">
                                <img src="./images/favicon-100x100.png" alt='Gentle Dental Care'>
                            </a>
                        </div>
                        <h1 class="auth_title text-left">Password Reset</h1>
                        <form action="${pageContext.request.contextPath}/forgot-password" method="post">
                            <div class="alert alert-success bg-soft-primary border-0" role="alert">
                                Enter the account for which you want to retrieve the password.
                            </div>
                            <c:if test="${CURRENT_PAGE != null}">
                                <div class="setting-form_content">
                                    <label for="email" class="setting-form_label">Email</label>
                                    <div class="setting-form_input">
                                        <input type="text" name="email" id="email" value="${EMAIL != null ? EMAIL : ""}" placeholder="Enter Email">
                                        <i class='bx bx-check-circle' id="email-icon-check"></i>
                                        <i class='bx bx-error-circle' id="email-icon-error"></i>
                                    </div>
                                    <div class="message">
                                        <span class="error-message" id="email-error"><%=accountError.getEmailError()%></span>
                                    </div>
                                </div>
                            </c:if>
                            <c:if test="${CHANGE_PAGE_PASSWORD != null}">
                                <div class="setting-form_content">
                                    <label for="newPassword" class="setting-form_label">New Password</label>
                                    <div class="setting-form_input">
                                        <input type="password" name="newPassword" id="newPassword" placeholder="Enter New Password">
                                        <i class='bx bx-check-circle' id="newPassword-icon-check"></i>
                                        <i class='bx bx-error-circle' id="newPassword-icon-error"></i>
                                    </div>
                                    <div class="message">
                                        <span class="error-message" id="newPassword-error"><%=accountError.getNewPasswordError()%></span>
                                    </div>
                                </div>
                                <div class="setting-form_content">
                                    <label for="confirmPassword" class="setting-form_label">Confirm Password</label>
                                    <div class="setting-form_input">
                                        <input type="password" name="confirmPassword" id="confirmPassword" placeholder="Enter Confirm Password">
                                        <i class='bx bx-check-circle' id="confirmPassword-icon-check"></i>
                                        <i class='bx bx-error-circle' id="confirmPassword-icon-error"></i>
                                    </div>
                                    <div class="message">
                                        <span class="error-message" id="confirmPassword-error"><%=accountError.getConfirmPasswordError()%></span>
                                    </div>
                                </div>
                            </c:if>
                            <c:if test="${CHANGE_PAGE_VERIFY != null}">
                                <div class="setting-form_content">
                                    <label for="inputVerifyCode" class="setting-form_label">Verify Code</label>
                                    <div class="setting-form_input">
                                        <input type="text" name="verifySMS" id="inputVerifyCode" placeholder="Enter Your Verify Code">
                                        <i class='bx bx-check-circle' id="verify-code-icon-check"></i>
                                        <i class='bx bx-error-circle' id="verify-code-icon-error"></i>
                                    </div>
                                    <div class="message">
                                        <span class="error-message" id="verify-code-error"><%=accountError.getVerifySMSError()%></span>
                                    </div>
                                </div>
                            </c:if>
                            <div class="button-action">
                                <button type="submit" class="reset-password">${CHANGE_PAGE_VERIFY == null ? "RESET PASSWORD" : "SEND CODE"}</button>
                            </div>
                            <p class="Login_dontHaveAcc">
                                Already have an account ? <button type="button" onclick="window.location.href = '${pageContext.request.contextPath}/login'" class="sign_in_btn">Sign in</button>
                            </p>
                        </form>
                    </div>
                </div>
            </div>
        </div>

        <img src="./images/triangleB_background.png" alt="" class="forgot-password__triangleB">
        
        <!-- Lottie Files -->
        <script src="https://unpkg.com/@lottiefiles/lottie-player@latest/dist/lottie-player.js"></script>
        <!-- LINK BOOTSTRAP 5 -->
        <script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.10.2/dist/umd/popper.min.js"></script>
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.min.js"></script>
        <script src="./js/ForgotPassword.js"></script>
    </body>
</html>
