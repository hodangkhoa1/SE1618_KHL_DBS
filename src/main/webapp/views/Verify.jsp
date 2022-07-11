<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Verify | Gentle Dental Care</title>
        <!-- TẠO ICON TRÊN THANH WEB -->
        <link rel="icon" href="./images/favicon-100x100.png" type="image/png" sizes="200x138" />
        <!-- LINK GOOGLE FONT -->
        <link rel="stylesheet" href="https://fonts.googleapis.com/css?family=Ubuntu:300,400,500,700" type="text/css">
        <!-- LINK STYLE -->
        <link rel="stylesheet" href="./css/Verify.css">
    </head>
    <body>
        <div class="verify-container">
            <div class="verify-body">
                <div class="verify-body__header" style="background-image: url(./images/verifyBackground.png);">
                    <table role="presentation" cellpadding="0" cellspacing="0" style="background-image: url(./images/verifyBackground.png);">
                        <tbody>
                            <tr>
                                <td>
                                    <div class="verify-body__header-title">
                                        <c:choose>
                                            <c:when test = "${VERIFY_STATUS.equals('Successful')}">
                                                Welcome to Gentle Dental Care!
                                            </c:when>

                                            <c:when test = "${VERIFY_STATUS.equals('Error')}">
                                                Gentle Dental Care!
                                            </c:when>
                                        </c:choose>
                                    </div>
                                </td>
                            </tr>
                        </tbody>
                    </table>
                </div>

                <div class="verify-body__footer">
                    <table role="presentation" cellpadding="0" cellspacing="0" class="footer-table__content">
                        <tbody>
                            <tr>
                                <td class="table-content-td">
                                    <div aria-labelledby="mj-column-per-100" class="mj-column-per-100 outlook-group-fix">
                                        <table role="presentation" cellpadding="0" cellspacing="0">
                                            <tbody>
                                                <tr>
                                                    <td class="table-content__header">
                                                        <div>
                                                            <p>
                                                                <c:choose>
                                                                    <c:when test = "${VERIFY_STATUS.equals('Successful')}">
                                                                       <img src="https://cdn.discordapp.com/email_assets/127c95bbea39cd4bc1ad87d1500ae27d.png"
                                                                            alt="Party Wumpus" title="None">
                                                                    </c:when>

                                                                    <c:when test = "${VERIFY_STATUS.equals('Error')}">
                                                                        <lottie-player src="https://assets9.lottiefiles.com/packages/lf20_pNx6yH.json"
                                                                            background="transparent" speed="1" loop autoplay style="width: 60%; position: relative; left: 50%; transform: translateX(-50%);"></lottie-player>
                                                                    </c:when>
                                                                </c:choose>
                                                            </p>

                                                            <h2>
                                                                Hey ${sessionScope.LOGIN_USER.fullName},
                                                            </h2>
                                                            <c:choose>
                                                                <c:when test = "${VERIFY_STATUS.equals('Successful')}">
                                                                   <p>
                                                                        Wowwee! Thanks for registering an account with Discord!
                                                                        You're the coolest person in all the land (and I've met a
                                                                        lot of really cool people).
                                                                    </p>
                                                                    <p>Before we get started, we'll need to verify your email.</p>
                                                                </c:when>

                                                                <c:when test = "${VERIFY_STATUS.equals('Error')}">
                                                                    <p>
                                                                        Your account has expired. Please click the button below to be redirected to the page to reactivate your account.
                                                                    </p>
                                                                </c:when>
                                                            </c:choose>
                                                        </div>
                                                    </td>
                                                </tr>
                                                <tr>
                                                    <td class="table-content__footer">
                                                        <table role="presentation" cellpadding="0" cellspacing="0">
                                                            <tbody>
                                                                <tr>
                                                                    <td>
                                                                        <c:choose>
                                                                            <c:when test = "${VERIFY_STATUS.equals('Successful')}">
                                                                                <button onclick="window.location.href = '${pageContext.request.contextPath}/login'" type="button" class="button-verify">
                                                                                    Verify Email
                                                                                </button>
                                                                            </c:when>

                                                                            <c:when test = "${VERIFY_STATUS.equals('Error')}">
                                                                                <button onclick="window.location.href = '${pageContext.request.contextPath}/forgot-password'" type="button" class="button-verify">
                                                                                    Reactive account
                                                                                </button>
                                                                            </c:when>
                                                                        </c:choose>
                                                                    </td>
                                                                </tr>
                                                            </tbody>
                                                        </table>
                                                    </td>
                                                </tr>
                                            </tbody>
                                        </table>
                                    </div>
                                </td>
                            </tr>
                        </tbody>
                    </table>
                </div>
            </div>
        </div>
        
        <!-- Lottie Files -->
        <script src="https://unpkg.com/@lottiefiles/lottie-player@latest/dist/lottie-player.js"></script>
    </body>
</html>
