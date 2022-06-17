<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Verify | Gentle Dental Care</title>
        <!-- TẠO ICON TRÊN THANH WEB -->
        <link rel="icon" href="./images/icon.png" type="image/png" sizes="200x138" />
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
                                        Welcome to Gentle Dental Care!
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
                                                                <img src="https://cdn.discordapp.com/email_assets/127c95bbea39cd4bc1ad87d1500ae27d.png"
                                                                     alt="Party Wumpus" title="None">
                                                            </p>

                                                            <h2>
                                                                Hey ${sessionScope.LOGIN_USER.fullName},
                                                            </h2>
                                                            <p>
                                                                Wowwee! Thanks for registering an account with Discord!
                                                                You're the coolest person in all the land (and I've met a
                                                                lot of really cool people).
                                                            </p>
                                                            <p>Before we get started, we'll need to verify your email.</p>
                                                        </div>
                                                    </td>
                                                </tr>
                                                <tr>
                                                    <td class="table-content__footer">
                                                        <table role="presentation" cellpadding="0" cellspacing="0">
                                                            <tbody>
                                                                <tr>
                                                                    <td>
                                                                        <button onclick="window.location.href = '${pageContext.request.contextPath}/login'" type="button" class="button-verify">
                                                                            Verify Email
                                                                        </button>
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
    </body>
</html>
